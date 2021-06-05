package org.mineplugin.locusazzurro.icaruswings.render;

import org.mineplugin.locusazzurro.icaruswings.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.Utils;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class FeatherWingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends ElytraLayer<T, M> {
	
	private static final ResourceLocation WINGS_LOCATION = new ResourceLocation(Utils.MOD_ID, "textures/entity/feather_wings.png");
	private final ElytraModel<T> elytraModel = new ElytraModel<>();
	public FeatherWingsLayer(IEntityRenderer<T, M> renderIn) {
		super(renderIn);
	}
	
	@Override
	public boolean shouldRender(ItemStack stack, T entity) {
	    return stack.getItem() == ItemRegistry.featherWings.get();
	}
	
	@Override
	public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
		return WINGS_LOCATION;
	}
	
}
