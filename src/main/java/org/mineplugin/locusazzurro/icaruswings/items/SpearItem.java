package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.UseAction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

public class SpearItem extends TieredItem implements IVanishable {

    private final float attackDamage;
    private final float attackSpeed;
    private final float attackRange;
    private final float BASE_DAMAGE = 1.0f;
    private final float BASE_ATTACK_SPEED = -1.0f;
    private final float BASE_ATTACK_RANGE = 6.0f;

    public SpearItem(IItemTier tier) {
        super(tier, new Properties().tab(ModGroup.itemGroup));
        this.attackDamage = BASE_DAMAGE + tier.getAttackDamageBonus();
        this.attackSpeed = BASE_ATTACK_SPEED;
        this.attackRange = BASE_ATTACK_RANGE;
    }

    protected Multimap<Attribute, AttributeModifier> getModifiers(){
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ModData.WEAPON_ATTACK_DAMAGE_UUID,
                "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ModData.WEAPON_ATTACK_SPEED_UUID,
                "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ModData.WEAPON_ATTACK_RANGE_UUID,
                "Weapon modifier", this.attackRange, AttributeModifier.Operation.ADDITION));
        return builder.build();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType slot) {
        return slot == EquipmentSlotType.MAINHAND ? this.getModifiers() : super.getDefaultAttributeModifiers(slot);
    }

    public float getDamage() {
        return this.attackDamage;
    }

    @Override
    public boolean canAttackBlock(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, PlayerEntity p_195938_4_) {
        return !p_195938_4_.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        Material material = blockState.getMaterial();
        return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !blockState.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        itemStack.hurtAndBreak(1, attacker, (item) -> {
            item.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, World worldIn, BlockState blockState, BlockPos pos, LivingEntity user) {
        if (blockState.getDestroySpeed(worldIn, pos) != 0.0F) {
            itemStack.hurtAndBreak(2, user, (item) -> {
                item.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
            });
        }

        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockState) {
        return false;
    }

    @Override
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.SPEAR;
    }


}
