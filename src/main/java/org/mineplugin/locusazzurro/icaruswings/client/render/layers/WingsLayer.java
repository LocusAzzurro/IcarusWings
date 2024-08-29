package org.mineplugin.locusazzurro.icaruswings.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.client.render.IWingsTranslucent;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.WingsModel;
import org.mineplugin.locusazzurro.icaruswings.common.item.AbstractWings;

@OnlyIn(Dist.CLIENT)
public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
	
	private static final ResourceLocation WINGS_FEATHER = ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, "textures/entity/feather_wings.png");
	private final WingsModel<T> elytraModel;

	public WingsLayer(RenderLayerParent<T, M> renderIn, EntityModelSet root) {
		super(renderIn, root);
		elytraModel = new WingsModel<>(root.bakeLayer(ModelLayers.ELYTRA));
	}
	
	@Override
	public boolean shouldRender(ItemStack stack, T entity) {
	    return (stack.getItem() instanceof AbstractWings);
	}
	
	@Override
	public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
		Item item = stack.getItem();
		if (item instanceof AbstractWings)
		{	
			if((entity.isFallFlying() && (item instanceof IWingsExpandable)) || AbstractWings.isEntityFloating(entity))
			{
				return ((AbstractWings) item).getType().getTextureReversed();
			}
			return ((AbstractWings) item).getType().getTexture();
		}
		return WINGS_FEATHER;
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packetLightIn, T entityLivingBaseIn,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {
		ItemStack itemstack = entityLivingBaseIn.getItemBySlot(EquipmentSlot.CHEST);
		if (shouldRender(itemstack, entityLivingBaseIn)) {
			ResourceLocation resourceLocation;
			if (entityLivingBaseIn instanceof AbstractClientPlayer player) {
				PlayerSkin skin = player.getSkin();
				if (itemstack.getItem() instanceof ElytraItem
						&& skin.elytraTexture() != null) {
					resourceLocation = skin.elytraTexture();
				} else if (player.isModelPartShown(PlayerModelPart.CAPE)
						&& skin.capeTexture() != null) {
					resourceLocation = skin.capeTexture();
				} else {
					resourceLocation = getElytraTexture(itemstack, entityLivingBaseIn);
				}
			} else {
				resourceLocation = getElytraTexture(itemstack, entityLivingBaseIn);
			}
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.0D, 0.0D, 0.125D);
			if((itemstack.getItem() instanceof IWingsExpandable wingsExpandable && entityLivingBaseIn.isFallFlying())) {
				double scale = wingsExpandable.getExpansionFactor();
				matrixStackIn.scale((float) scale, (float) scale, 1.0f);
			}
			this.getParentModel().copyPropertiesTo(this.elytraModel);
			this.elytraModel.setupAnim(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			boolean isWingsTranslucent = itemstack.getItem() instanceof IWingsTranslucent;
			VertexConsumer vertexBuilder = ItemRenderer.getArmorFoilBuffer(bufferIn,
					isWingsTranslucent ? RenderType.entityTranslucent(resourceLocation) : RenderType.armorCutoutNoCull(resourceLocation),
					!isWingsTranslucent && itemstack.hasFoil()
			);

			this.elytraModel.renderToBuffer(matrixStackIn, vertexBuilder, packetLightIn, OverlayTexture.NO_OVERLAY, 0xff_ff_ff_ff);
			
			matrixStackIn.popPose();
		}
	}

}
