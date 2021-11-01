package org.mineplugin.locusazzurro.icaruswings;

import org.mineplugin.locusazzurro.icaruswings.data.BlockRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.EffectRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.EnchantmentRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.EntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.SoundRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.TileEntityTypeRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.Utils;
import org.mineplugin.locusazzurro.icaruswings.entity.GoldenRamEntity;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class IcarusWings {
	public IcarusWings() {
		
	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
	ItemRegistry.ITEMS.register(bus);
	BlockRegistry.BLOCKS.register(bus);
	TileEntityTypeRegistry.TILE_ENTITIES.register(bus);
	SoundRegistry.SOUNDS.register(bus);
	EnchantmentRegistry.ENCHANTMENTS.register(bus);
	EffectRegistry.EFFECTS.register(bus);
	EntityTypeRegistry.ENTITIES.register(bus);
	}
	
}
