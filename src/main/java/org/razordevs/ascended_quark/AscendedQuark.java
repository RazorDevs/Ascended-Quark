package org.razordevs.ascended_quark;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.razordevs.ascended_quark.datagen.AQData;
import org.razordevs.ascended_quark.datagen.builders.loot.modifiers.AQGlobalLootModifiers;
import org.razordevs.ascended_quark.proxy.ACClientProxy;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zetaimplforge.ForgeZeta;

import java.nio.file.Path;
import java.util.stream.Stream;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

    //TODO: Pickarangs LootTables

    public static final String MODID = "ascended_quark";
    public static final String AETHER = "aether";
    public static final String DEEP_AETHER = "deep_aether";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static AscendedQuark instance;
    public static ACCommonProxy proxy;
    public static final Zeta ZETA = new ForgeZeta(MODID, LogManager.getLogger("aq-zeta"));

    public AscendedQuark() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        instance = this;
        ZETA.start();

        proxy = Env.unsafeRunForDist(() -> ACClientProxy::new, () -> ACCommonProxy::new);
        proxy.start();

        MinecraftForge.EVENT_BUS.addListener(this::missingMappings);
        bus.addListener(AQData::dataSetup);
        bus.addListener(this::addAdditionalResourcesPack);

        AQGlobalLootModifiers.LOOT_MODIFIERS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }


    @Deprecated(forRemoval = true)
    @ApiStatus.ScheduledForRemoval(inVersion = "1.21.1")
    public void missingMappings(MissingMappingsEvent event) {
        Stream<MissingMappingsEvent.Mapping<Block>> stream = event.getMappings(ForgeRegistries.Keys.BLOCKS, AscendedQuark.MODID).stream();
        stream.filter(mapping -> mapping.getKey().getPath().contains("brick_"))
                .forEach(blockMapping -> {
                    Block block = ForgeRegistries.BLOCKS.getValue(asResource(blockMapping.getKey().getPath().replace("brick_", "bricks_")));
                    if(block != null)
                        blockMapping.remap(block);

                });

        Stream<MissingMappingsEvent.Mapping<Item>> itemSteam = event.getMappings(ForgeRegistries.Keys.ITEMS, AscendedQuark.MODID).stream();
        itemSteam.filter(mapping -> mapping.getKey().getPath().contains("brick_"))
                .forEach(itemMapping -> {
                    Block block = ForgeRegistries.BLOCKS.getValue(asResource(itemMapping.getKey().getPath().replace("brick_", "bricks_")));
                    if(block != null)
                        itemMapping.remap(block.asItem());
                });
    }

    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> base, String name) {
        return ResourceKey.create(base, asResource(name));
    }

    public void addAdditionalResourcesPack(AddPackFindersEvent event) {
        if(ModList.get().isLoaded(DEEP_AETHER)) {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                setupCompatPack("deep_aether_compatibility_client", "Deep Aether Compatibility Client", event, PackType.CLIENT_RESOURCES, PackSource.BUILT_IN);
            } else  if(event.getPackType() == PackType.SERVER_DATA){
                setupCompatPack("deep_aether_compatibility_server", "Deep Aether Compatibility Server", event, PackType.SERVER_DATA, PackSource.SERVER);
            }
        }
    }

    private static void setupCompatPack(String location, String name, AddPackFindersEvent event, PackType type, PackSource source) {
        Path resourcePath = ModList.get().getModFileById(AscendedQuark.MODID).getFile().findResource("packs/"+location);
        Pack pack = Pack.readMetaAndCreate("builtin/"+location, Component.literal(name), true,
                path -> new PathPackResources(path, resourcePath, true), type, Pack.Position.BOTTOM, source);
        event.addRepositorySource(consumer -> consumer.accept(pack));
    }
}
