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
import org.mineplugin.locusazzurro.icaruswings.data.ModGroup;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

@Mod.EventBusSubscriber
public class WheatGrains extends Item{

	private final static float ACQUIRE_FEATHER_CHANCE = 0.5f;
	
	public WheatGrains() {
		super(new Properties().tab(ModGroup.itemGroup).food(food));
		
	}
	
	private static final FoodProperties food = (new FoodProperties.Builder())
			.saturationMod(0)
			.nutrition(2)
			.build();
	
	@Override
	public InteractionResult interactLivingEntity(net.minecraft.world.item.ItemStack stack, Player player,
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
					
					int variant = ((Parrot) target).getVariant();
					new ItemStack(Items.FEATHER);
					ItemStack feather = switch (variant) {
						case 0 -> new ItemStack(ItemRegistry.redFeather.get());
						case 1 -> new ItemStack(ItemRegistry.blueFeather.get());
						case 2 -> new ItemStack(ItemRegistry.greenFeather.get());
						case 3 -> new ItemStack(ItemRegistry.cyanFeather.get());
						case 4 -> new ItemStack(ItemRegistry.grayFeather.get());
						default -> new ItemStack(Items.FEATHER);
					};
					ItemHandlerHelper.giveItemToPlayer(player, feather);
				}
			}
			return InteractionResult.sidedSuccess(world.isClientSide);
		}
		
		return InteractionResult.PASS;
	}

	@SubscribeEvent
	public static void tamedParrotHandler(PlayerInteractEvent.EntityInteractSpecific evt){
		if (evt.getTarget() instanceof Parrot
				&& evt.getPlayer().getItemInHand(evt.getHand()).getItem() instanceof WheatGrains){
			Parrot parrot = (Parrot) evt.getTarget();
			net.minecraft.world.item.ItemStack grains = evt.getPlayer().getItemInHand(evt.getHand());
			if (!parrot.isFlying() && parrot.isTame() && !evt.getWorld().isClientSide()) {
				grains.interactLivingEntity(evt.getPlayer(), parrot, evt.getHand());
				parrot.setOrderedToSit(!parrot.isOrderedToSit());
			}
			evt.setResult(Event.Result.ALLOW);
		}
	}

}
