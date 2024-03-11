package org.mineplugin.locusazzurro.icaruswings.registry;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import org.mineplugin.locusazzurro.icaruswings.client.render.models.*;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;

import java.util.Map;

public class ModelLayerRegistry {

    private static final String MOD_ID = ModData.MOD_ID;

    public static final ModelLayerLocation ARTEMIS_MISSILE = register("artemis_missile");
    public static final ModelLayerLocation SPEAR = register("spear");
    public static final ModelLayerLocation TIME_BOMB = register("time_bomb");
    public static final ModelLayerLocation GOLDEN_RAM = register("golden_ram");
    public static final ModelLayerLocation GOLDEN_RAM_FLEECE = register("golden_ram_fleece");

    public static Map<ModelLayerLocation, LayerDefinition> createLayerDefinitions() {
        ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder = ImmutableMap.builder();
        builder.put(ARTEMIS_MISSILE, ArtemisMissileModel.createBodyLayer());
        builder.put(SPEAR, SpearModel.createBodyLayer());
        builder.put(TIME_BOMB, TimeBombModel.createBodyLayer());
        builder.put(GOLDEN_RAM, GoldenRamModel.createBodyLayer());
        builder.put(GOLDEN_RAM_FLEECE, GoldenRamFleeceModel.createFurLayer());
        return builder.build();
    }

    private static ModelLayerLocation register(String pPath){
        return new ModelLayerLocation(new ResourceLocation(MOD_ID, pPath), "main");
    };

}
