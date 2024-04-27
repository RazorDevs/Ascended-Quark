package org.razordevs.ascended_quark;

import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import org.razordevs.ascended_quark.blocks.AQBlocks;
import org.razordevs.ascended_quark.datagen.AQItemModelData;
import org.razordevs.ascended_quark.datagen.AQLangData;
import org.razordevs.ascended_quark.datagen.AQRecipeData;
import org.razordevs.ascended_quark.datagen.loot.AQLootTableData;
import org.razordevs.ascended_quark.datagen.tags.AQBlockTagData;
import org.razordevs.ascended_quark.datagen.tags.AQItemTagData;
import org.razordevs.ascended_quark.entity.AQEntityTypes;
import org.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;
import org.razordevs.ascended_quark.items.AQItems;
import org.razordevs.ascended_quark.particle.AQParticles;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.razordevs.ascended_quark.datagen.AQBlockstateData;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.violetmoon.quark.base.proxy.ClientProxy;
import org.violetmoon.quark.base.proxy.CommonProxy;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zeta.util.Utils;
import org.violetmoon.zetaimplforge.ForgeZeta;

import java.util.concurrent.CompletableFuture;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

    //TODO: Handle better Documentation

    public static final String MODID = "ascended_quark";
    public static final String DEEP_AETHER = "deep_aether";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static AscendedQuark instance;
    public static ACCommonProxy proxy=new ACCommonProxy();
    public static final Zeta ZETA = new ForgeZeta(MODID, LogManager.getLogger("aq-zeta"));

    public AscendedQuark() {
        instance = this;
        ZETA.start();
        proxy.start();
        //proxy = Env.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        //proxy.start();
        //if (Utils.isDevEnv()) {
        //    MixinEnvironment.getCurrentEnvironment().audit();
        //}

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        AQBlocks.BLOCKS.register(modEventBus);
        AQItems.ITEMS.register(modEventBus);
        AQEntityTypes.ENTITY_TYPES.register(modEventBus);
        AQBlockEntityTypes.ENTITY_TYPES.register(modEventBus);
        AQParticles.PARTICLE_TYPES.register(modEventBus);
    }

    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // Client Data
        generator.addProvider(event.includeClient(), new AQBlockstateData(output, fileHelper));
        generator.addProvider(event.includeClient(), new AQItemModelData(output, fileHelper));
        generator.addProvider(event.includeClient(), new AQLangData(output));

        // Server Data
        generator.addProvider(event.includeServer(), new AQRecipeData(output));
        generator.addProvider(event.includeServer(), AQLootTableData.create(output));
        AQBlockTagData blockTags = new AQBlockTagData(output, lookupProvider, fileHelper);

        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new AQItemTagData(output, lookupProvider, blockTags.contentsGetter(), fileHelper));
    }
}
