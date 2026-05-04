package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.server.level.ServerLevel;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

import java.util.function.Consumer;

public class MagicalAnemone extends Item {

    public MagicalAnemone(Item.Properties properties){
        super(properties.rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
        Double speed = stack.get(DataComponentRegistry.SPEED_TRACKER);
        if (speed != null){
            tooltipComponents.accept(Component.translatable("item.locusazzurro_icaruswings.magical_anemone.tooltip")
                    .append(Component.literal(String.format("%.2f", speed)))
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }
    }


    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel worldIn, Entity entityIn, EquipmentSlot slot) {
        if (entityIn instanceof Player && slot == EquipmentSlot.MAINHAND){
            itemStack.set(DataComponentRegistry.SPEED_TRACKER, entityIn.getDeltaMovement().length());
        }
    }
}
