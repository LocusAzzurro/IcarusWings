package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import java.util.List;

public class MagicalAnemone extends Item {

    public MagicalAnemone(){
        super(new Item.Properties().tab(ModGroup.itemGroup).rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (itemstack.getOrCreateTag().contains("Speed")){
            list.add(new TranslationTextComponent("item.locusazzurro_icaruswings.magical_anemone.tooltip")
                    .append(new StringTextComponent(String.format("%.2f", itemstack.getOrCreateTag().getDouble("Speed"))))
                    .setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
        }

    }

    @Override
    public void inventoryTick(ItemStack itemStack, World worldIn, Entity entityIn, int inventorySlot, boolean isSelected) {
        if (entityIn instanceof PlayerEntity && !worldIn.isClientSide() && isSelected){
            itemStack.getOrCreateTag().putDouble("Speed", entityIn.getDeltaMovement().length());
        }
    }
}
