package net.sjhub.simplebikes.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.sjhub.simplebikes.registry.bikes.acrobike.entities.EntityAcrobike;
import net.sjhub.simplebikes.registry.bikes.acrobike.render.RenderAcroBike;
import net.sjhub.simplebikes.registry.bikes.machbike.entities.EntityMachbike;
import net.sjhub.simplebikes.registry.bikes.machbike.render.RenderMachbike;
import net.sjhub.simplebikes.registry.listeners.PlayerTickEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new PlayerTickEvent());
        registerRenderers();
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityAcrobike.class, RenderAcroBike::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMachbike.class, RenderMachbike::new);
    }
}
