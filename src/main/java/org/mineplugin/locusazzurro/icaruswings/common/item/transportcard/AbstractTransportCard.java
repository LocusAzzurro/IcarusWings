package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.server.permissions.Permissions;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.IcarusWingsConfig;

import java.util.function.Consumer;


public abstract class AbstractTransportCard extends Item {

    private final CardType type;

    public AbstractTransportCard(CardType type){
        this(type, new Item.Properties()
                .rarity(Rarity.UNCOMMON)
                .overrideDescription("item." + IcarusWings.MOD_ID + ".transport_card"));
    }

    public AbstractTransportCard(CardType type, Item.Properties properties) {
        super(properties.rarity(Rarity.UNCOMMON).overrideDescription("item." + IcarusWings.MOD_ID + ".transport_card"));
        this.type = type;
    }

    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {return InteractionResult.FAIL;}
        else return InteractionResult.CONSUME;
    }

    protected static boolean canUseCard(Player playerIn){
        return switch (IcarusWingsConfig.TRANSPORT_CARD_PERMISSION_LEVEL.get()) {
            case 0 -> false;
            case 1 -> playerIn.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER);
            case 2 -> playerIn.isCreative() || playerIn.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER);
            case 3 -> true;
            default -> false;
        };
    }

    @Override
    public boolean isFoil(ItemStack stack){
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
        tooltipComponents.accept(Component.translatable("item.locusazzurro_icaruswings.transport_card_"+ type +".tooltip")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
    }

    public CardType getType(){
        return this.type;
    }

    public enum CardType implements StringRepresentable {
        BASE("base"),
        ARTEMIS_HOMING("artemis_homing"),
        ARTEMIS_SCATTER("artemis_scatter"),
        CHRONO_EXPLOLSION("chrono_explosion"),
        TELEPORT("teleport"),
        MASKING_FIELD("masking_field"),
        INTERDICTION_FIELD("interdiction_field"),
        TREASURE("treasure"),;

        private final String name;

        CardType(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

}
