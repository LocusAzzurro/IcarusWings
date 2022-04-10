package org.mineplugin.locusazzurro.icaruswings.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import org.mineplugin.locusazzurro.icaruswings.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class SpearItem extends TieredItem implements Vanishable {

    private final float attackDamage;
    private final float attackSpeed;
    private final float attackRange;
    private static final float BASE_DAMAGE = 1.0f;
    private static final float BASE_ATTACK_SPEED = 1.0f;
    private static final float BASE_ATTACK_RANGE = 6.0f;

    public SpearItem(Tier tier) {
        super(tier, new Properties().tab(ModGroup.itemGroup)
                //todo 不知道ister去哪了 .setISTER(() -> SpearItemStackTileEntityRenderer::new)
        );
        this.attackDamage = BASE_DAMAGE + tier.getAttackDamageBonus();
        this.attackSpeed = BASE_ATTACK_SPEED;
        this.attackRange = BASE_ATTACK_RANGE;
    }

    protected Multimap<Attribute, AttributeModifier> getModifiers(){
        ImmutableMultimap.Builder<net.minecraft.world.entity.ai.attributes.Attribute, net.minecraft.world.entity.ai.attributes.AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ModData.WEAPON_ATTACK_DAMAGE_UUID,
                "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new net.minecraft.world.entity.ai.attributes.AttributeModifier(ModData.WEAPON_ATTACK_SPEED_UUID,
                "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ModData.WEAPON_ATTACK_RANGE_UUID,
                "Weapon modifier", this.attackRange, AttributeModifier.Operation.ADDITION));
        return builder.build();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.getModifiers() : super.getDefaultAttributeModifiers(slot);
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public static float getBaseAttackDamage() {return BASE_DAMAGE;}

    @Override
    public int getEnchantmentValue() {
        return this.getTier().getEnchantmentValue();
    }

    @Override
    public boolean canAttackBlock(net.minecraft.world.level.block.state.BlockState p_195938_1_, Level p_195938_2_, BlockPos p_195938_3_, Player p_195938_4_) {
        return !p_195938_4_.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        Material material = blockState.getMaterial();
        // todo 珊瑚的Material改成了STONE了，你看看用不用调
        return material != net.minecraft.world.level.material.Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.STONE && !blockState.is(BlockTags.LEAVES) && material != net.minecraft.world.level.material.Material.VEGETABLE ? 1.0F : 1.5F;
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        itemStack.hurtAndBreak(1, attacker, (player) -> {
            player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level worldIn, net.minecraft.world.level.block.state.BlockState blockState, net.minecraft.core.BlockPos pos, LivingEntity user) {
        if (blockState.getDestroySpeed(worldIn, pos) != 0.0F) {
            itemStack.hurtAndBreak(2, user, (item) -> {
                item.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(net.minecraft.world.level.block.state.BlockState blockState) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return ((enchantment.category == EnchantmentCategory.WEAPON
                && enchantment != Enchantments.SWEEPING_EDGE
                && enchantment != net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING
                && enchantment != net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT)||
                super.canApplyAtEnchantingTable(stack, enchantment));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(net.minecraft.world.item.ItemStack p_77626_1_) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        net.minecraft.world.item.ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level worldIn, LivingEntity livingIn, int charge) {
        if (livingIn instanceof Player) {
            Player playerIn = (Player)livingIn;
            int i = this.getUseDuration(itemStack) - charge;
            if (i >= 10) {
                if (!worldIn.isClientSide){
                    itemStack.hurtAndBreak(1, playerIn, (player) -> {
                        player.broadcastBreakEvent(livingIn.getUsedItemHand());
                    });
                }
                SpearEntity spearEntity = new SpearEntity(worldIn, playerIn, itemStack);
                spearEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 2.5F, 1.0F);
                if (playerIn.getAbilities().instabuild) {
                    spearEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                worldIn.addFreshEntity(spearEntity);
                worldIn.playSound(null, spearEntity, SoundRegistry.spearThrow.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!playerIn.getAbilities().instabuild) {
                    playerIn.getInventory().removeItem(itemStack);
                }
            }
            playerIn.awardStat(Stats.ITEM_USED.get(this));

        }
    }

    @Override
    public void inventoryTick(net.minecraft.world.item.ItemStack itemStack, Level worldIn, Entity entityIn, int inventorySlot, boolean isSelected){
        if (entityIn instanceof LivingEntity && isSelected){
            if (((LivingEntity) entityIn).isUsingItem()){
                itemStack.getOrCreateTag().putBoolean("Throwing", true);
            }
            else {itemStack.getOrCreateTag().remove("Throwing");}
        }
    }


}

