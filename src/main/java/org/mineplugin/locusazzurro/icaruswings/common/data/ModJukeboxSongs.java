package org.mineplugin.locusazzurro.icaruswings.common.data;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongs;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.mineplugin.locusazzurro.icaruswings.IcarusWings;
import org.mineplugin.locusazzurro.icaruswings.registry.SoundRegistry;

public class ModJukeboxSongs {

    public static ResourceKey<JukeboxSong> FALLEN_DOWN = key("fallen_down");
    public static ResourceKey<JukeboxSong> RING_MY_BELL = key("ring_my_bell");

    public static void bootstrap(BootstrapContext<JukeboxSong> context){

        register(context, FALLEN_DOWN, SoundRegistry.TRACK_FALLEN_DOWN, 240, 1);
        register(context, RING_MY_BELL, SoundRegistry.TRACK_RING_MY_BELL, 90, 2);

        JukeboxSongs.bootstrap(context);
    }

    private static ResourceKey<JukeboxSong> key(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(IcarusWings.MOD_ID, name));
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float)lengthInSeconds, comparatorOutput));
    }

    public static Holder<JukeboxSong> getHolder(Level level, ResourceKey<JukeboxSong> song) {
        return level.holderLookup(song.registryKey()).getOrThrow(song);
    }

}
