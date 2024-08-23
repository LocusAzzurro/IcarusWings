package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.mineplugin.locusazzurro.icaruswings.client.render.renderers.SpearItemStackTileEntityRenderer;
import org.mineplugin.locusazzurro.icaruswings.common.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

import java.util.function.Consumer;

public class SpearItem extends TieredItem{

    private final float attackDamage;
    private final float attackSpeed;
    private final float attackRange;
    private static final float BASE_DAMAGE = 2.5f;
    private static final float BASE_ATTACK_SPEED = -2.5f;
    private static final float BASE_ATTACK_RANGE = 1.5f;

    public SpearItem(Tier tier) {
        super(tier, new Properties());
        this.attackDamage = BASE_DAMAGE + tier.getAttackDamageBonus();
        this.attackSpeed = BASE_ATTACK_SPEED;
        this.attackRange = BASE_ATTACK_RANGE;
    }


    @Override
    @SuppressWarnings("deprecation")
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        ItemAttributeModifiers defaultAttributeModifiers = super.getDefaultAttributeModifiers();
        return defaultAttributeModifiers
                .withModifierAdded(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("attack_damage"), this.attackDamage, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .withModifierAdded(Attributes.ATTACK_SPEED, new AttributeModifier(ResourceLocation.withDefaultNamespace("attack_speed"), this.attackSpeed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)
                .withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ResourceLocation.withDefaultNamespace("entity_interaction_range"), this.attackRange, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND);
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
    public boolean canAttackBlock(BlockState p_195938_1_, Level p_195938_2_, BlockPos p_195938_3_, Player p_195938_4_) {
        return !p_195938_4_.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return 1.5f;
        //todo repliacte new sword mechanics
        /*Material material = blockState.getMaterial();
        return material != Material.PLANT && material != Material.REPLACEABLE_PLANT
                && !blockState.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;

         */
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        itemStack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level worldIn, BlockState blockState, BlockPos pos, LivingEntity user) {
        if (blockState.getDestroySpeed(worldIn, pos) != 0.0F) {
            itemStack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
        }

        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return ((enchantment.category == EnchantmentCategory.WEAPON
                && enchantment != Enchantments.SWEEPING_EDGE
                && enchantment != Enchantments.MOB_LOOTING
                && enchantment != Enchantments.FIRE_ASPECT)||
                super.canApplyAtEnchantingTable(stack, enchantment));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                return new SpearItemStackTileEntityRenderer();
            }
        });
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
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
            int i = this.getUseDuration(itemStack, livingIn) - charge;
            if (i >= 10) {
                if (!worldIn.isClientSide){
                    itemStack.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);
                }
                SpearEntity spearEntity = new SpearEntity(worldIn, playerIn, itemStack);
                spearEntity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 2.5F, 1.0F);
                if (playerIn.getAbilities().instabuild) {
                    spearEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                worldIn.addFreshEntity(spearEntity);
                worldIn.playSound(null, spearEntity, SoundRegistry.SPEAR_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!playerIn.getAbilities().instabuild) {
                    playerIn.getInventory().removeItem(itemStack);
                }
            }
            playerIn.awardStat(Stats.ITEM_USED.get(this));

        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level worldIn, Entity entityIn, int inventorySlot, boolean isSelected){
        if (entityIn instanceof LivingEntity && isSelected){
            if (((LivingEntity) entityIn).isUsingItem()){
                itemStack.getOrCreateTag().putBoolean("Throwing", true);
            }
            else {itemStack.getOrCreateTag().remove("Throwing");}
        }
    }


}

