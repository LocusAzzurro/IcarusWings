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
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

import java.util.List;

public class MagicalAnemone extends Item {

    public MagicalAnemone(){
        super(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        Double speed = stack.get(DataComponentRegistry.SPEED_TRACKER);
        if (speed != null){
            tooltipComponents.add(Component.translatable("item.locusazzurro_icaruswings.magical_anemone.tooltip")
                    .append(Component.literal(String.format("%.2f", speed)))
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }
    }


    @Override
    public void inventoryTick(ItemStack itemStack, Level worldIn, Entity entityIn, int inventorySlot, boolean isSelected) {
        if (entityIn instanceof Player && !worldIn.isClientSide() && isSelected){
            itemStack.set(DataComponentRegistry.SPEED_TRACKER, entityIn.getDeltaMovement().length());
        }
    }
}
