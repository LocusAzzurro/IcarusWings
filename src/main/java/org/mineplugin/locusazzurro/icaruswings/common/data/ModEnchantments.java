package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;


public class ModEnchantments {

    public static final ResourceKey<Enchantment> COLLISION_PROTECTION = key("collision_protection");
    public static final ResourceKey<Enchantment> PYROTECHNIC_AFFINITY = key("pyrotechnic_affinity");
    public static final ResourceKey<Enchantment> BLESSING_OF_THE_SKY = key("blessing_of_the_sky");


    public static void bootstrap(BootstrapContext<Enchantment> context) {

        HolderGetter<Item> itemsRegistry = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantmentsRegistry = context.lookup(Registries.ENCHANTMENT);

        context.register(COLLISION_PROTECTION, Enchantment.enchantment(
                Enchantment.definition(
                        itemsRegistry.getOrThrow(ModTags.ELYTRA_ENCHANTABLE),
                        5, 3,
                        Enchantment.dynamicCost(10, 5),
                        Enchantment.dynamicCost(12, 5), 2,
                        EquipmentSlotGroup.ARMOR)
                )
                .withEffect(
                        EnchantmentEffectComponents.DAMAGE_PROTECTION,
                        new AddValue(LevelBasedValue.perLevel(3.0F)),
                        DamageSourceCondition.hasDamageSource(
                                DamageSourcePredicate.Builder.damageType()
                                .tag(TagPredicate.is(ModTags.IS_COLLISION))
                                .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                )) //todo add damage to armor
                .build(COLLISION_PROTECTION.location()));

        context.register(PYROTECHNIC_AFFINITY, Enchantment.enchantment(
                Enchantment.definition(
                        itemsRegistry.getOrThrow(ModTags.ELYTRA_ENCHANTABLE),
                        5, 3,
                        Enchantment.dynamicCost(10, 5),
                        Enchantment.dynamicCost(12, 5), 2,
                        EquipmentSlotGroup.ARMOR)
                ) //todo add propulsion via event
                .build(PYROTECHNIC_AFFINITY.location()));

        context.register(BLESSING_OF_THE_SKY, Enchantment.enchantment(
                Enchantment.definition(
                        itemsRegistry.getOrThrow(ModTags.ELYTRA_ENCHANTABLE),
                        2, 1,
                        Enchantment.constantCost(25),
                        Enchantment.constantCost(40), 5,
                        EquipmentSlotGroup.ARMOR)
                )
                .withEffect(
                        EnchantmentEffectComponents.DAMAGE_PROTECTION,
                        new AddValue(LevelBasedValue.perLevel(0.1F)),
                        AnyOfCondition.anyOf(
                                DamageSourceCondition.hasDamageSource(
                                DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION))
                                        .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                                ),
                                DamageSourceCondition.hasDamageSource(
                                        DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(DamageTypeTags.IS_FIRE))
                                        .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                                ),
                                DamageSourceCondition.hasDamageSource(
                                        DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE))
                                        .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                                ),
                                DamageSourceCondition.hasDamageSource(
                                        DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(DamageTypeTags.IS_LIGHTNING))
                                        .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                                )
                        )
                        ) //todo add stronger protection when flying
                .build(BLESSING_OF_THE_SKY.location()));

        Enchantments.bootstrap(context);
    }

    /* //todo propulsion
    @SubscribeEvent
    public static void fireworkExtender(EntityJoinLevelEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof FireworkRocketEntity){
            FireworkRocketEntity firework = (FireworkRocketEntity) entity;
            if (firework.isAttachedToEntity()
                    && firework.attachedToEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ElytraItem){
                int enchLvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.PYROTECHNIC_AFFINITY.get(), firework.attachedToEntity);
                firework.lifetime *= 1 + enchLvl * 0.1;
            }
        }
    }

     */


    public static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

    public static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> enchantment, Enchantment.Builder builder) {
        context.register(enchantment, builder.build(enchantment.location()));
    }

    public static Holder<Enchantment> getHolder(Level level, ResourceKey<Enchantment> enchantment) {
        return level.holderLookup(enchantment.registryKey()).getOrThrow(enchantment);
    }

}
