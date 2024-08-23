package org.mineplugin.locusazzurro.icaruswings.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModFoods;
import org.mineplugin.locusazzurro.icaruswings.registry.ItemRegistry;

@EventBusSubscriber
public class WheatGrains extends Item{

	private final static float ACQUIRE_FEATHER_CHANCE = 0.5f;
	
	public WheatGrains() {
		super(new Properties().food(ModFoods.WHEAT_GRAINS));
		
	}
	
	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player,
                                                  LivingEntity target, InteractionHand hand) {
		Level world = player.level();
		
		if (stack.getItem() == ItemRegistry.WHEAT_GRAINS.get() && target instanceof Parrot) {
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
						case RED_BLUE -> new ItemStack(ItemRegistry.RED_FEATHER.get());
						case BLUE -> new ItemStack(ItemRegistry.BLUE_FEATHER.get());
						case GREEN -> new ItemStack(ItemRegistry.GREEN_FEATHER.get());
						case YELLOW_BLUE -> new ItemStack(ItemRegistry.CYAN_FEATHER.get());
						case GRAY -> new ItemStack(ItemRegistry.GRAY_FEATHER.get());
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
			evt.setCanceled(true);
			evt.setCancellationResult(InteractionResult.CONSUME);
		}
	}

}
