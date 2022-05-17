package org.mineplugin.locusazzurro.icaruswings.event;

import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class KayrosScheduler {

    private static KayrosScheduler instance;

    public KayrosScheduler(){
        instance = this;
    }

    public static KayrosScheduler getSchedulerInstance(){
        return instance;
    }



    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event){

    }


}
