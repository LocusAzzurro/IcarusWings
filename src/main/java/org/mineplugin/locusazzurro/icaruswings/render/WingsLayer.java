package org.mineplugin.locusazzurro.icaruswings.render;

import org.mineplugin.locusazzurro.icaruswings.data.Utils;
import org.mineplugin.locusazzurro.icaruswings.items.AbstractWings;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
	
	private static final ResourceLocation WINGS_FEATHER = new ResourceLocation(Utils.MOD_ID, "textures/entity/feather_wings.png");
	private final WingsModel<T> elytraModel = new WingsModel<>();

	public WingsLayer(IEntityRenderer<T, M> renderIn) {
		super(renderIn);
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
			return ((AbstractWings) item).getType().getTexture();
		}
		return WINGS_FEATHER;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packetLightIn, T entityLivingBaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		ItemStack itemstack = entityLivingBaseIn.getItemBySlot(EquipmentSlotType.CHEST);
		if (shouldRender(itemstack, entityLivingBaseIn)) {
			ResourceLocation resourcelocation;
			if (entityLivingBaseIn instanceof AbstractClientPlayerEntity) {
				AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity) entityLivingBaseIn;
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
			}
			this.getParentModel().copyPropertiesTo(this.elytraModel);
			this.elytraModel.setupAnim(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
			this.elytraModel.renderToBuffer(matrixStackIn, ivertexbuilder, packetLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStackIn.popPose();
		}
	}

}
