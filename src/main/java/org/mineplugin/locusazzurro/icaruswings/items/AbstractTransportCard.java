package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import java.util.List;


public abstract class AbstractTransportCard extends Item {

    private CardType type;

    public AbstractTransportCard(CardType type){
        super(new Properties().tab(ModGroup.itemGroup).rarity(Rarity.UNCOMMON));
        this.type = type;
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn){
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        //TODO: add coonfig to disable
        return ActionResult.pass(itemstack);
    }

    @Override
    public boolean isFoil(ItemStack stack){
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        CardType type = ((AbstractTransportCard)itemstack.getItem()).getType();
        list.add(new TranslationTextComponent("item.locusazzurro_icaruswings.transport_card_"+ type +".tooltip")
                .setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
    }

    @Override
    public String getDescriptionId(){
        return "item." + ModData.MOD_ID + ".transport_card";
    }
    //TODO add keys in lang file

    public CardType getType(){
        return this.type;
    }

    public enum CardType implements IStringSerializable {
        BASE("base"),
        TELEPORT("teleport"),
        ARTEMIS_HOMING("artemis_homing"),
        ARTEMIS_SCATTER("artemis_scatter"),
        CHRONO_EXPLOLSION("chrono_explosion"),;

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