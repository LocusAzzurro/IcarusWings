package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.server.level.ServerLevel;
import org.mineplugin.locusazzurro.icaruswings.common.entity.SpearEntity;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;

import java.util.List;

public class SpearItem extends Item {

    private final ToolMaterial material;
    private final float attackDamage;
    private final float attackSpeed;
    private final float attackRange;
    private static final float BASE_DAMAGE = 2.5f;
    private static final float BASE_ATTACK_SPEED = -2.5f;
    private static final float BASE_ATTACK_RANGE = 1.5f;

    public SpearItem(ToolMaterial material, Properties properties) {
        super(material.applyToolProperties(properties, BlockTags.MINEABLE_WITH_PICKAXE, BASE_DAMAGE, BASE_ATTACK_SPEED, 0.0F)
            .component(
                DataComponents.TOOL,
                new Tool(
                    List.of(
                        Tool.Rule.deniesDrops(BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK).getOrThrow(material.incorrectBlocksForDrops())),
                        Tool.Rule.minesAndDrops(BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK).getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), material.speed())
                    ),
                    1.5F,
                    1,
                    true
                )
            )
            .attributes(
                ItemAttributeModifiers.builder()
                    .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, BASE_DAMAGE + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                    )
                    .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, BASE_ATTACK_SPEED, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                    )
                    .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(net.minecraft.resources.Identifier.withDefaultNamespace("entity_interaction_range"), BASE_ATTACK_RANGE, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                    )
                    .build()
            )
            .component(DataComponents.WEAPON, new Weapon(2))
            .enchantable(material.enchantmentValue()));
        this.material = material;
        this.attackDamage = BASE_DAMAGE + material.attackDamageBonus();
        this.attackSpeed = BASE_ATTACK_SPEED;
        this.attackRange = BASE_ATTACK_RANGE;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public static float getBaseAttackDamage() {
        return BASE_DAMAGE;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 1.5f;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity user) {
        if (state.getDestroySpeed(level, pos) != 0.0F) {
            stack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return false;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
            return InteractionResult.FAIL;
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int charge) {
        if (livingEntity instanceof Player player) {
            int used = this.getUseDuration(itemStack, livingEntity) - charge;
            if (used >= 10) {
                if (!level.isClientSide()) {
                    itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                SpearEntity spearEntity = new SpearEntity(level, player, itemStack);
                spearEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                if (player.getAbilities().instabuild) {
                    spearEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                level.addFreshEntity(spearEntity);
                level.playSound(null, spearEntity, SoundRegistry.SPEAR_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) {
                    player.getInventory().removeItem(itemStack);
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return true;
            }
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        if (entity instanceof LivingEntity living && slot == EquipmentSlot.MAINHAND) {
            stack.set(DataComponentRegistry.THROWING, living.isUsingItem());
        }
    }
}


