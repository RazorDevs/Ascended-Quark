package com.razordevs.quark_aether;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(QuarkAetherMod.MODID)
public class QuarkAetherMod
{
    public static final String MODID = "quark_aether";
    private static final Logger LOGGER = LogUtils.getLogger();
    public QuarkAetherMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }

}
