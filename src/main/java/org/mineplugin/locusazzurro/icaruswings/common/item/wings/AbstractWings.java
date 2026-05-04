package org.mineplugin.locusazzurro.icaruswings.common.item.wings;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.DataComponentRegistry;

@SuppressWarnings("unused")
public abstract class AbstractWings extends Item implements IcarusGlider {

    private static ResourceKey<EquipmentAsset> createAssetKey(String name) {
        return ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name + "_wings")
        );
    }

    protected WingsType type;

    protected AbstractWings(WingsType type) {
        this(type, null, new Properties());
    }

    protected AbstractWings(WingsType type, Rarity rarity) {
        this(type, rarity, new Properties());
    }

    protected AbstractWings(WingsType type, Properties properties) {
        this(type, null, properties);
    }

    protected AbstractWings(WingsType type, Rarity rarity, Properties properties) {
        super(createProperties(type, rarity, properties));
        this.type = type;
    }

    protected AbstractWings() {
        this(WingsTypes.FEATHER);
    }

    private static Properties createProperties(WingsType type, Rarity rarity, Properties properties) {
        Properties built = properties
            .durability(type.getDurability())
            .component(DataComponentRegistry.ICARUS_GLIDER, Unit.INSTANCE)
            .component(
                DataComponents.EQUIPPABLE,
                Equippable.builder(EquipmentSlot.CHEST)
                    .setEquipSound(type.getEquipSound())
                    .setDamageOnHurt(false)
                    .setAsset(createAssetKey(type.getName()))
                    .build()
            )
            .repairable(type.getRepairItem())
            .enchantable(15);
        if (rarity != null) {
            built = built.rarity(rarity);
        }
        return built;
    }

    public WingsType getType() {
        return this.type;
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }

    public SoundEvent getEquipSound() {
        return getType().getEquipSound().value();
    }

    public static boolean isEntityFloating(LivingEntity entity) {
        return entity.hasEffect(MobEffects.SLOW_FALLING) || entity.hasEffect(MobEffects.LEVITATION);
    }
}
