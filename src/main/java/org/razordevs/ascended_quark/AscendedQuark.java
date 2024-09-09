package org.razordevs.ascended_quark;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.razordevs.ascended_quark.datagen.AQBlockstateData;
import org.razordevs.ascended_quark.datagen.AQItemModelData;
import org.razordevs.ascended_quark.datagen.AQLangData;
import org.razordevs.ascended_quark.datagen.AQRecipeData;
import org.razordevs.ascended_quark.datagen.loot.AQLootTableData;
import org.razordevs.ascended_quark.datagen.tags.AQBlockTagData;
import org.razordevs.ascended_quark.datagen.tags.AQItemTagData;
import org.razordevs.ascended_quark.mixin.ZetaRegistryAccessor;
import org.razordevs.ascended_quark.proxy.ACClientProxy;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zeta.util.Utils;
import org.violetmoon.zetaimplforge.ForgeZeta;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

    //TODO: ADD BLOCK REMAPS BEFORE RELEASE

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

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void dataSetup(GatherDataEvent event) {

        ZetaRegistryAccessor accessor = (ZetaRegistryAccessor) ZETA.registry;

        HashMap<String, Block> blockMap = new HashMap<>();
        HashMap<String, Item> itemMap = new HashMap<>();
        for (Object value : accessor.getInternalNames().keySet()) {
            ResourceLocation location = accessor.getInternalNames().get(value);
            if (value instanceof Block block)
                blockMap.put(location.getPath(), block);
            else if(value instanceof Item item)
                itemMap.put(location.getPath(), item);
        }

        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // Client Data
        generator.addProvider(event.includeClient(), new AQBlockstateData(output, fileHelper, blockMap));
        generator.addProvider(event.includeClient(), new AQItemModelData(output, fileHelper, itemMap, blockMap));
        generator.addProvider(event.includeClient(), new AQLangData(output, itemMap, blockMap));

        // Server Data
        //generator.addProvider(event.includeServer(), new AQRecipeData(output));
        generator.addProvider(event.includeServer(), AQLootTableData.create(output, blockMap));
        //AQBlockTagData blockTags = new AQBlockTagData(output, lookupProvider, fileHelper);

        //generator.addProvider(event.includeServer(), blockTags);
        //generator.addProvider(event.includeServer(), new AQItemTagData(output, lookupProvider, blockTags.contentsGetter(), fileHelper));
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> base, String name) {
        return ResourceKey.create(base, asResource(name));
    }
}
