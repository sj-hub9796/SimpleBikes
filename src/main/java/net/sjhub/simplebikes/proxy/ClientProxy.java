package net.sjhub.simplebikes.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.sjhub.simplebikes.registry.bikes.BikeJumpHandler;
import net.sjhub.simplebikes.registry.entities.BikeEntity;
import net.sjhub.simplebikes.registry.render.SimpleRenderer;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        registerEntityRenderers();
        MinecraftForge.EVENT_BUS.register(new BikeJumpHandler());
    }

    @Override
    public void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(BikeEntity.class, SimpleRenderer::new);
    }
}
