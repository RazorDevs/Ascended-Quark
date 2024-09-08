package org.razordevs.ascended_quark;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.razordevs.ascended_quark.proxy.ACClientProxy;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zeta.util.Utils;
import org.violetmoon.zetaimplforge.ForgeZeta;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

    //TODO: Handle better Documentation

    public static final String MODID = "ascended_quark";
    public static final String DEEP_AETHER = "deep_aether";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static AscendedQuark instance;
    public static ACCommonProxy proxy;
    public static final Zeta ZETA = new ForgeZeta(MODID, LogManager.getLogger("aq-zeta"));

    public AscendedQuark() {
        instance = this;
        ZETA.start();

        proxy = Env.unsafeRunForDist(() -> ACClientProxy::new, () -> ACCommonProxy::new);
        proxy.start();
        //if (Utils.isDevEnv()) {
        //    MixinEnvironment.getCurrentEnvironment().audit();
        //}


        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> base, String name) {
        return ResourceKey.create(base, asResource(name));
    }
}
