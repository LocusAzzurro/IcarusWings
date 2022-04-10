package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import java.util.List;

public class MagicalAnemone extends Item {

    public MagicalAnemone(){
        super(new Item.Properties().tab(ModGroup.itemGroup).rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (itemstack.getOrCreateTag().contains("Speed")){
            list.add(new TranslatableComponent("item.locusazzurro_icaruswings.magical_anemone.tooltip")
                    .append(new TextComponent(String.format("%.2f", itemstack.getOrCreateTag().getDouble("Speed"))))
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
