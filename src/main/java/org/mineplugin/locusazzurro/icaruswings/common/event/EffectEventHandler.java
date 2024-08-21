package org.mineplugin.locusazzurro.icaruswings.common.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.mineplugin.locusazzurro.icaruswings.common.data.ModData;
import org.mineplugin.locusazzurro.icaruswings.common.effect.AbstractEffect;
import org.mineplugin.locusazzurro.icaruswings.registry.EffectRegistry;

@EventBusSubscriber(modid = ModData.MOD_ID)
public class EffectEventHandler {

    // From AbstractEffect

    // 被移除
    @SubscribeEvent( priority = EventPriority.LOWEST )
    public static void eventRemove( MobEffectEvent.Remove event ) {
        MobEffectInstance effect = event.getEffectInstance( ) ;
        if ( effect != null && effect.getEffect( ) instanceof AbstractEffect)  {
            ( (AbstractEffect) ( effect.getEffect( ) ) ).onRemove( event.getEntity( ), effect );
        }
    }
    // 自然消失
    @SubscribeEvent( priority = EventPriority.LOWEST )
    public static void eventExpiry( MobEffectEvent.Expired event ) {
        MobEffectInstance effect = event.getEffectInstance( ) ;
        // 只处理CoreEffect的内容
        if ( effect != null && effect.getEffect( ) instanceof AbstractEffect)  {
            // 先移除
            event.getEntity( ).removeEffect( effect.getEffect( ) ) ;
            ( (AbstractEffect) ( effect.getEffect( ) ) ).onExpiry( event.getEntity( ), effect ) ;
        }

    }

    // Poison Immunity

    @SubscribeEvent
    public static void onPotionApplication(MobEffectEvent.Applicable e) {
        if (e.getEffectInstance().getEffect().equals(MobEffects.POISON)
                && (e.getEntity().hasEffect(EffectRegistry.POISON_IMMUNITY.get()))) {
            e.setResult(Event.Result.DENY);
        }
    }

    // Sensory Masking

    @SubscribeEvent
    public static void onSetTarget(LivingChangeTargetEvent e) {
        if (e.getNewTarget() != null) {
            LivingEntity targeter = e.getEntity();
            if (e.getNewTarget().hasEffect(EffectRegistry.SENSORY_MASKING.get()) && targeter instanceof Mob) {
                ((Mob) targeter).setTarget(e.getOriginalTarget());
            }
        }
    }


}
