package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class IconBadge extends Item{

    public IconBadge(){
        super(new Item.Properties()
                .stacksTo(1).fireResistant()
                .rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal(
                """
                        "Never regret thy fall,
                        O Icarus of the fearless flight,
                        For the greatest tragedy of them all,
                        Is never to feel the burning light."
                        - Oscar Wilde
                        """)
                .setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)));
    }
}
