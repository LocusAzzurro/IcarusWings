package org.mineplugin.locusazzurro.icaruswings.datagen;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;

import java.util.Optional;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider extends EquipmentAssetProvider {

    public ModEquipmentAssetProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        armor(output, "feather");
        armor(output, "golden_feather");
        armor(output, "beeswax");
        armor(output, "linen");
        armor(output, "golden_fleece");
        armor(output, "herbal");
        armor(output, "synapse_tech");

        wings(output, "feather");
        wings(output, "colored_feather");
        wings(output, "golden_feather");
        wings(output, "synapse_alpha");
        wings(output, "synapse_beta");
        wings(output, "synapse_delta");
        wings(output, "synapse_epsilon");
        wings(output, "synapse_zeta");
        wings(output, "synapse_theta");
        wings(output, "paper");
        wings(output, "magic");
        wings(output, "philosopher_stone");
    }

    private static void armor(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output, String name) {
        var id = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
        var textureId = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name);
        output.accept(id, EquipmentClientInfo.builder().addHumanoidLayers(textureId).build());
    }

    private static void wings(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output, String name) {
        var id = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name + "_wings"));
        var textureId = Identifier.fromNamespaceAndPath(IcarusWings.MOD_ID, name);
        output.accept(id,
            EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.WINGS,
                    new EquipmentClientInfo.Layer(textureId, Optional.empty(), true))
                .build());
    }
}
