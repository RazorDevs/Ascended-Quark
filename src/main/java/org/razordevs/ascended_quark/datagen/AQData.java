package org.razordevs.ascended_quark.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.datagen.builders.loot.modifiers.AQLootDataProvider;
import org.razordevs.ascended_quark.datagen.compat.deep_aether.DACompBlockstateData;
import org.razordevs.ascended_quark.datagen.compat.deep_aether.DACompItemModelData;
import org.razordevs.ascended_quark.datagen.compat.deep_aether.DACompRecipeData;
import org.razordevs.ascended_quark.datagen.compat.deep_aether.tags.DACompBlockTagData;
import org.razordevs.ascended_quark.datagen.compat.deep_aether.tags.DACompItemTagData;
import org.razordevs.ascended_quark.datagen.normal.AQBlockstateData;
import org.razordevs.ascended_quark.datagen.normal.AQItemModelData;
import org.razordevs.ascended_quark.datagen.normal.AQLangData;
import org.razordevs.ascended_quark.datagen.normal.AQRecipeData;
import org.razordevs.ascended_quark.datagen.normal.loot.AQLootTableData;
import org.razordevs.ascended_quark.datagen.normal.tags.AQBlockTagData;
import org.razordevs.ascended_quark.datagen.normal.tags.AQItemTagData;
import org.razordevs.ascended_quark.datagen.provider.tags.AQBlockTagProvider;
import org.razordevs.ascended_quark.mixin.ZetaRegistryAccessor;
import org.violetmoon.zeta.module.IDisableable;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AQData {
    private static AQBlockTagProvider blockTags;

    public static void dataSetup(GatherDataEvent event) {
        ZetaRegistryAccessor accessor = (ZetaRegistryAccessor) AscendedQuark.ZETA.registry;

        HashMap<String, Block> fullBlockMap = new HashMap<>();
        HashMap<String, Item> fullItemBlockMap = new HashMap<>();
        HashMap<String, Item> fullItemMap = new HashMap<>();

        for (Object value : accessor.getInternalNames().keySet()) {
            ResourceLocation location = accessor.getInternalNames().get(value);
            if (value instanceof Block block)
                fullBlockMap.put(location.getPath(), block);
            else if(value instanceof Item item)
                fullItemBlockMap.put(location.getPath(), item);
        }

        fullItemBlockMap.forEach((s, item) -> {
            if(!fullBlockMap.containsKey(s))
                fullItemMap.put(s, item);
        });
        fullItemBlockMap.clear();

        //Separates the Block and Item Maps in order to generate different maps
        HashMap<String, Item> normalItemMap = new HashMap<>();
        HashMap<String, Item> deepAetherItemMap = new HashMap<>();
        fullItemMap.forEach(
                (s, item) -> {
                    if (Objects.equals(((IDisableable<?>) item).getModule().category().requiredMod, AscendedQuark.DEEP_AETHER)) {
                        deepAetherItemMap.put(s, item);
                    }
                    else {
                        normalItemMap.put(s, item);
                    }
                }
        );

        HashMap<String, Block> normalBlockMap = new HashMap<>();
        HashMap<String, Block> deepAetherBlockMap = new HashMap<>();
        fullBlockMap.forEach(
                (s, block) -> {
                    if (Objects.equals(((IDisableable<?>) block).getModule().category().requiredMod, AscendedQuark.DEEP_AETHER)) {
                        deepAetherBlockMap.put(s, block);
                    }
                    else {
                        normalBlockMap.put(s, block);
                    }
                }
        );

        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new AQLangData(event.getGenerator().getPackOutput(), fullItemMap, fullBlockMap));

        createNormalPack(event, normalBlockMap, normalItemMap);
        createDeepAetherPack(event, deepAetherBlockMap, deepAetherItemMap);
    }

    private static void createNormalPack(GatherDataEvent event, HashMap<String, Block> blockMap, HashMap<String, Item> itemMap) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Client Data
        generator.addProvider(event.includeClient(), new AQBlockstateData(output, fileHelper, blockMap));
        generator.addProvider(event.includeClient(), new AQItemModelData(output, fileHelper, itemMap, blockMap));

        // Server Data
        generator.addProvider(event.includeServer(), new AQRecipeData(output, lookupProvider, itemMap, blockMap));
        generator.addProvider(event.includeServer(), AQLootTableData.create(output, lookupProvider, blockMap));
        blockTags = new AQBlockTagData(output, lookupProvider, fileHelper, blockMap);
        generator.addProvider(event.includeServer(), new AQLootDataProvider(output, lookupProvider, itemMap));
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new AQItemTagData(output, lookupProvider, blockTags.contentsGetter(), fileHelper, itemMap, blockMap));
    }

    private static void createDeepAetherPack(GatherDataEvent event, HashMap<String, Block> blockMap, HashMap<String, Item> itemMap) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Client Pack Data
        Path builtinData = output.getOutputFolder().resolve("packs");
        DataGenerator.PackGenerator clientPack = generator.new PackGenerator(event.includeClient(), "deep_aether_compatibility_client", new PackOutput(builtinData.resolve("deep_aether_compatibility_client")));
        clientPack.addProvider(outPut -> new DACompBlockstateData(outPut, fileHelper, blockMap));
        clientPack.addProvider(outPut -> new DACompItemModelData(outPut,fileHelper, itemMap, blockMap));

        // Server Pack Data
        DataGenerator.PackGenerator serverPack = generator.new PackGenerator(event.includeServer(), "deep_aether_compatibility_server", new PackOutput(builtinData.resolve("deep_aether_compatibility_server")));
        serverPack.addProvider(outPut -> new DACompRecipeData(outPut, lookupProvider, itemMap, blockMap));
        serverPack.addProvider(outPut -> {
            blockTags = new DACompBlockTagData(outPut, lookupProvider, fileHelper, blockMap);
            return blockTags;
        });
        serverPack.addProvider(outPut -> new DACompItemTagData(outPut, lookupProvider, blockTags.contentsGetter(), fileHelper, itemMap, blockMap));
        serverPack.addProvider(outPut -> AQLootTableData.create(outPut, lookupProvider, blockMap));
    }
}
