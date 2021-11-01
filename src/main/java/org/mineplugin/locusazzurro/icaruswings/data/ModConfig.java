package org.mineplugin.locusazzurro.icaruswings.data;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    public static ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.DoubleValue WINGS_SPEED_MOD;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        b.comment("Icarus Wings Config File").push("config");
        WINGS_SPEED_MOD = b
                .comment("Speed factor for Synapse Wings")
                .defineInRange("wings_speed_mod", 1.0d, 0.0d, Double.MAX_VALUE);
        b.pop();
        CONFIG = b.build();
    }
}
