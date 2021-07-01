package org.mineplugin.locusazzurro.icaruswings.items;

import org.mineplugin.locusazzurro.icaruswings.data.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber()
public class WheatGrains extends Item{

	private final static float ACQUIRE_FEATHER_CHANCE = 0.5f;
	
	public WheatGrains() {
		super(new Properties().tab(ModGroup.itemGroup).food(food));
		
	}
	
	private static final Food food = (new Food.Builder())
			.saturationMod(0)
			.nutrition(2)
			.build();
	
	@SubscribeEvent
	public static void onPlayerRightClick(PlayerInteractEvent.EntityInteract event) {
		
		PlayerEntity player = event.getPlayer();
		Entity target = event.getTarget();
		World world = event.getWorld();
		if (!world.isClientSide() && player.getItemInHand(Hand.MAIN_HAND).getItem() == ItemRegistry.wheatGrains.get()) {
			if (target instanceof ParrotEntity)
			{
				player.getItemInHand(Hand.MAIN_HAND).shrink(1);
				target.playSound(SoundEvents.PARROT_EAT, 2.0f, 1.3f);
				//world.playSound(player, target, SoundEvents.PARROT_EAT, SoundCategory.VOICE, 2.0f, 1.3f);
				//world.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.PARROT_EAT, SoundCategory.VOICE, 2.0f, 1.3f, false);
				if (Math.random() > ACQUIRE_FEATHER_CHANCE) {
					
					int variant = ((ParrotEntity) target).getVariant();
					ItemStack feather = new ItemStack(Items.FEATHER);
					switch (variant) {
					case 0:	feather = new ItemStack(ItemRegistry.redFeather.get()); break;
					case 1:	feather = new ItemStack(ItemRegistry.blueFeather.get()); break;
					case 2: feather = new ItemStack(ItemRegistry.greenFeather.get()); break;
					case 3: feather = new ItemStack(ItemRegistry.cyanFeather.get()); break;
					case 4: feather = new ItemStack(ItemRegistry.grayFeather.get()); break;
					default: feather = new ItemStack(Items.FEATHER); break;
					}
					player.inventory.add(feather);
				}
			}
		}
		
		
	}
}
