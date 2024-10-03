package net.sjhub.simplebikes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.sjhub.simplebikes.proxy.CommonProxy;
import net.sjhub.simplebikes.registry.entities.SimpleEntities;
import net.sjhub.simplebikes.registry.items.SimpleItems;
import net.sjhub.simplebikes.registry.sounds.SimpleSounds;
import net.sjhub.simplebikes.simplebikes.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class SimpleBike {

    public static final String CLIENT = "net.sjhub.simplebikes.proxy.ClientProxy";
    public static final String COMMON = "net.sjhub.simplebikes.proxy.CommonProxy";
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.Instance(Tags.MOD_ID)
    public static SimpleBike instance;

    @SidedProxy(clientSide = CLIENT, serverSide = COMMON)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        SimpleItems.registerItems();
        SimpleEntities.registerEntities();
        SimpleSounds.registerSounds();

        MinecraftForge.EVENT_BUS.register(SimpleItems.class);
        MinecraftForge.EVENT_BUS.register(SimpleEntities.class);
        MinecraftForge.EVENT_BUS.register(SimpleSounds.class);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
