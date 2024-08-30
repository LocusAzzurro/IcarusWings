package org.mineplugin.locusazzurro.icaruswings.common.item.transportcard;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.common.data.IcarusWingsConfig;

import java.util.List;


public abstract class AbstractTransportCard extends Item {

    private CardType type;

    public AbstractTransportCard(CardType type){
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
        this.type = type;
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!canUseCard(playerIn)) {return InteractionResultHolder.fail(itemstack);}
        else return InteractionResultHolder.consume(itemstack);
    }

    protected static boolean canUseCard(Player playerIn){
        switch (IcarusWingsConfig.TRANSPORT_CARD_PERMISSION_LEVEL.get()) {
            case 0: return false;
            case 1: return playerIn.hasPermissions(2);
            case 2: return playerIn.isCreative() || playerIn.hasPermissions(2);
            case 3: return true;
        }
        return false;
    }

    @Override
    public boolean isFoil(ItemStack stack){
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.locusazzurro_icaruswings.transport_card_"+ type +".tooltip")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
    }

    @Override
    public String getDescriptionId(){
        return "item." + IcarusWings.MOD_ID + ".transport_card";
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
