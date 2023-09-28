package com.razordevs.ascended_quark;

import com.mojang.logging.LogUtils;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import com.razordevs.ascended_quark.datagen.*;
import com.razordevs.ascended_quark.entity.AQEntityTypes;
import com.razordevs.ascended_quark.entity.block.AQBlockEntityTypes;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AscendedQuarkMod.MODID)
public class AscendedQuarkMod
{
    public static final String MODID = "ascended_quark";
    private static final Logger LOGGER = LogUtils.getLogger();
    public AscendedQuarkMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataSetup);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        AQBlocks.BLOCKS.register(modEventBus);
        AQItems.ITEMS.register(modEventBus);
        AQEntityTypes.ENTITY_TYPES.register(modEventBus);
        AQBlockEntityTypes.ENTITY_TYPES.register(modEventBus);
    }


    public void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();


        // Client Data
        generator.addProvider(event.includeClient(), new AQBlockstateData(event.getGenerator(), fileHelper));
        generator.addProvider(event.includeClient(), new AQItemModelData(event.getGenerator(), fileHelper));

        generator.addProvider(event.includeClient(), new AQLangData(event.getGenerator()));
        // Server Data
        //generator.addProvider(event.includeServer(), new DAWorldGenData(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new AQRecipeData(event.getGenerator()));
        //generator.addProvider(event.includeServer(), DALootTableData.create(packOutput));
        //DABlockTagData blockTags = new DABlockTagData(packOutput, lookupProvider, fileHelper);
        //generator.addProvider(event.includeServer(), blockTags);
        //generator.addProvider(event.includeServer(), new DAItemTagData(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
        //generator.addProvider(event.includeServer(), new DABiomeTagData(packOutput, lookupProvider, fileHelper));
        //generator.addProvider(event.includeServer(), new DAEntityTagData(packOutput, lookupProvider, fileHelper));
    }


}
