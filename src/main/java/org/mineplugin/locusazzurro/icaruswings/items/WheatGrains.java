package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber
public class WheatGrains extends Item{

	private final static float ACQUIRE_FEATHER_CHANCE = 0.5f;
	
	public WheatGrains() {
		super(new Properties().tab(ModGroup.itemGroup).food(food));
		
	}
	
	private static final Food food = (new Food.Builder())
			.saturationMod(0)
			.nutrition(2)
			.build();
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player,
			LivingEntity target, Hand hand) {
		World world = player.level;
		
		if (stack.getItem() == ItemRegistry.wheatGrains.get() && target instanceof ParrotEntity) {
			if (!world.isClientSide)
			{
				if (!player.isCreative())
					stack.shrink(1);
				target.playSound(SoundEvents.PARROT_EAT, 2.0f, 1.3f);
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
					ItemHandlerHelper.giveItemToPlayer(player, feather);
				}
			}
			return ActionResultType.sidedSuccess(world.isClientSide);
		}
		
		return ActionResultType.PASS;
	}

	@SubscribeEvent
	public static void tamedParrotHandler(PlayerInteractEvent.EntityInteractSpecific evt){
		if (evt.getTarget() instanceof ParrotEntity
				&& evt.getPlayer().getItemInHand(evt.getHand()).getItem() instanceof WheatGrains){
			ParrotEntity parrot = (ParrotEntity) evt.getTarget();
			ItemStack grains = evt.getPlayer().getItemInHand(evt.getHand());
			if (!parrot.isFlying() && parrot.isTame() && !evt.getWorld().isClientSide()) {
				grains.interactLivingEntity(evt.getPlayer(), parrot, evt.getHand());
				parrot.setOrderedToSit(!parrot.isOrderedToSit());
			}
			evt.setResult(Event.Result.ALLOW);
		}
	}

}
