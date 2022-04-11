package org.mineplugin.locusazzurro.icaruswings.render.layers;

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsExpandable;
import org.mineplugin.locusazzurro.icaruswings.render.IWingsTranslucent;
import org.mineplugin.locusazzurro.icaruswings.render.models.WingsModel;

@OnlyIn(Dist.CLIENT)
public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
	
	private static final net.minecraft.resources.ResourceLocation WINGS_FEATHER = new net.minecraft.resources.ResourceLocation(ModData.MOD_ID, "textures/entity/feather_wings.png");
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
	public ResourceLocation getElytraTexture(net.minecraft.world.item.ItemStack stack, T entity) {
		Item item = stack.getItem();
		if (item instanceof AbstractWings)
		{	
			if(entity.isFallFlying() && (item instanceof IWingsExpandable))
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
			net.minecraft.resources.ResourceLocation resourcelocation;
			if (entityLivingBaseIn instanceof AbstractClientPlayer) {
				AbstractClientPlayer abstractclientplayerentity = (AbstractClientPlayer) entityLivingBaseIn;
				if (abstractclientplayerentity.isElytraLoaded()
						&& abstractclientplayerentity.getElytraTextureLocation() != null) {
					resourcelocation = abstractclientplayerentity.getElytraTextureLocation();
				} else if (abstractclientplayerentity.isCapeLoaded()
						&& abstractclientplayerentity.getCloakTextureLocation() != null
						&& abstractclientplayerentity.isModelPartShown(PlayerModelPart.CAPE)) {
					resourcelocation = abstractclientplayerentity.getCloakTextureLocation();
				} else {
					resourcelocation = getElytraTexture(itemstack, entityLivingBaseIn);
				}
			} else {
				resourcelocation = getElytraTexture(itemstack, entityLivingBaseIn);
			}
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.0D, 0.0D, 0.125D);
			if(itemstack.getItem() instanceof IWingsExpandable && entityLivingBaseIn.isFallFlying()) {
				float s = ((IWingsExpandable) itemstack.getItem()).getExpansionFactor();
				matrixStackIn.scale(s, s, 1.0f);
				//matrixStackIn.mulPose(new Quaternion(1f,0f,0f,0f));
			}
			this.getParentModel().copyPropertiesTo(this.elytraModel);
			this.elytraModel.setupAnim(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			VertexConsumer ivertexbuilder;
			if(itemstack.getItem() instanceof IWingsTranslucent) {
			ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.entityTranslucent(resourcelocation), false, false);
			}
			else {
			ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
			}
			this.elytraModel.renderToBuffer(matrixStackIn, ivertexbuilder, packetLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			
			matrixStackIn.popPose();
		}
	}

}
