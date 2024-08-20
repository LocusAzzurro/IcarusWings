package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {

    public static ModConfigSpec CONFIG;
    public static ModConfigSpec.DoubleValue WINGS_SPEED_MOD;
    public static ModConfigSpec.IntValue TRANSPORT_CARD_PERMISSION_LEVEL;
    public static ModConfigSpec.BooleanValue ALLOW_DEMETER_CHANGE_TERRAIN;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        b.comment("Icarus Wings Config File").push("config");
        WINGS_SPEED_MOD = b
                .comment("Speed factor for Synapse Wings")
                .defineInRange("wings_speed_mod", 1.0d, 0.0d, Double.MAX_VALUE);
        TRANSPORT_CARD_PERMISSION_LEVEL = b
                .comment("Transport card use permission level")
                .defineInRange("transport_card_permission_level", 2, 0, 3);
        ALLOW_DEMETER_CHANGE_TERRAIN = b
                .comment("Does Demeter projectile change terrain")
                .define("allow_demeter_change_terrain", false);
        b.pop();
        CONFIG = b.build();
    }
}
