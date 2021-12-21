package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;

public class SpearItem extends TieredItem implements IVanishable {

    private final float attackDamage;
    private final float attackSpeed;
    private final float attackRange;
    private static final float BASE_DAMAGE = 1.0f;
    private static final float BASE_ATTACK_SPEED = -1.0f;
    private static final float BASE_ATTACK_RANGE = 6.0f;

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

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public static float getBaseAttackDamage() {return BASE_DAMAGE;}

    @Override
    public int getEnchantmentValue() {
        return 1;
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

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return ActionResult.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return ActionResult.consume(itemstack);
        }
    }

    @Override
    public void releaseUsing(ItemStack itemStack, World worldIn, LivingEntity livingIn, int charge) {
        if (livingIn instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity)livingIn;
            int i = this.getUseDuration(itemStack) - charge;
            if (i >= 10) {
                if (!worldIn.isClientSide){
                    itemStack.hurtAndBreak(1, playerIn, (item) -> {
                        item.broadcastBreakEvent(livingIn.getUsedItemHand());
                    });
                }
                SpearEntity spearEntity = new SpearEntity(worldIn, playerIn, itemStack);
                spearEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 2.5F, 1.0F);
                if (playerIn.abilities.instabuild) {
                    spearEntity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                }

                worldIn.addFreshEntity(spearEntity);
                //todo sound effect
                worldIn.playSound((PlayerEntity) null, spearEntity, SoundEvents.TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!playerIn.abilities.instabuild) {
                    playerIn.inventory.removeItem(itemStack);
                }
            }
            playerIn.awardStat(Stats.ITEM_USED.get(this));

        }
    }


}

