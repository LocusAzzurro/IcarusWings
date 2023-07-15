package org.mineplugin.locusazzurro.icaruswings.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

@Mod.EventBusSubscriber
public class WheatGrains extends Item{

	private final static float ACQUIRE_FEATHER_CHANCE = 0.5f;
	
	public WheatGrains() {
		super(new Properties().food(food));
		
	}
	
	private static final FoodProperties food = (new FoodProperties.Builder())
			.saturationMod(0)
			.nutrition(2)
			.build();
	
	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player,
                                                  LivingEntity target, InteractionHand hand) {
		Level world = player.level;
		
		if (stack.getItem() == ItemRegistry.wheatGrains.get() && target instanceof Parrot) {
			if (!world.isClientSide)
			{
				if (!player.isCreative()) {
					stack.shrink(1);
				}
				target.playSound(SoundEvents.PARROT_EAT, 2.0f, 1.3f);
				if (Math.random() > ACQUIRE_FEATHER_CHANCE) {

					Parrot.Variant var = ((Parrot) target).getVariant();
					new ItemStack(Items.FEATHER);
					ItemStack feather = switch (var) {
						case RED_BLUE -> new ItemStack(ItemRegistry.redFeather.get());
						case BLUE -> new ItemStack(ItemRegistry.blueFeather.get());
						case GREEN -> new ItemStack(ItemRegistry.greenFeather.get());
						case YELLOW_BLUE -> new ItemStack(ItemRegistry.cyanFeather.get());
						case GRAY -> new ItemStack(ItemRegistry.grayFeather.get());
					};
					ItemHandlerHelper.giveItemToPlayer(player, feather);
				}
			}
			return InteractionResult.sidedSuccess(world.isClientSide);
		}
		
		return InteractionResult.PASS;
	}

	@SubscribeEvent
	public static void tamedParrotHandler(PlayerInteractEvent.EntityInteract evt){
		if (evt.getTarget() instanceof Parrot parrot
				&& evt.getEntity().getItemInHand(evt.getHand()).getItem() instanceof WheatGrains){
			ItemStack grains = evt.getEntity().getItemInHand(evt.getHand());
			if (!parrot.isFlying() && parrot.isTame() && !evt.getLevel().isClientSide()) {
				grains.interactLivingEntity(evt.getEntity(), parrot, evt.getHand());
				parrot.setOrderedToSit(!parrot.isOrderedToSit());
			}
			evt.setResult(Event.Result.ALLOW);
		}
	}

}
