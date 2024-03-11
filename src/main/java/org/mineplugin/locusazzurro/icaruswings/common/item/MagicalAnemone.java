package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MagicalAnemone extends Item {

    public MagicalAnemone(){
        super(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (itemstack.getOrCreateTag().contains("Speed")){
            list.add(Component.translatable("item.locusazzurro_icaruswings.magical_anemone.tooltip")
                    .append(Component.literal(String.format("%.2f", itemstack.getOrCreateTag().getDouble("Speed"))))
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }

    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level worldIn, Entity entityIn, int inventorySlot, boolean isSelected) {
        if (entityIn instanceof Player && !worldIn.isClientSide() && isSelected){
            itemStack.getOrCreateTag().putDouble("Speed", entityIn.getDeltaMovement().length());
        }
    }
}
