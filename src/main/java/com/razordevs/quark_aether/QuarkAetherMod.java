package com.razordevs.quark_aether;

import com.mojang.logging.LogUtils;
import com.razordevs.quark_aether.blocks.QABlocks;
import com.razordevs.quark_aether.datagen.QABlockstateData;
import com.razordevs.quark_aether.datagen.QAItemModelData;
import com.razordevs.quark_aether.datagen.QALangData;
import com.razordevs.quark_aether.items.QAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(QuarkAetherMod.MODID)
public class QuarkAetherMod
{
    public static final String MODID = "quark_aether";
    private static final Logger LOGGER = LogUtils.getLogger();
    public QuarkAetherMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        QABlocks.BLOCKS.register(modEventBus);
        QAItems.ITEMS.register(modEventBus);
    }


    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();


        // Client Data
        generator.addProvider(event.includeClient(), new QABlockstateData(event.getGenerator(), fileHelper));
        generator.addProvider(event.includeClient(), new QAItemModelData(event.getGenerator(), fileHelper));

        generator.addProvider(event.includeClient(), new QALangData(event.getGenerator()));
        // Server Data
        //generator.addProvider(event.includeServer(), new DAWorldGenData(packOutput, lookupProvider));
        //generator.addProvider(event.includeServer(), new DARecipeData(packOutput));
        //generator.addProvider(event.includeServer(), DALootTableData.create(packOutput));
        //DABlockTagData blockTags = new DABlockTagData(packOutput, lookupProvider, fileHelper);
        //generator.addProvider(event.includeServer(), blockTags);
        //generator.addProvider(event.includeServer(), new DAItemTagData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
        //generator.addProvider(event.includeServer(), new DABiomeTagData(packOutput, lookupProvider, fileHelper));
        //generator.addProvider(event.includeServer(), new DAEntityTagData(packOutput, lookupProvider, fileHelper));
    }


}
