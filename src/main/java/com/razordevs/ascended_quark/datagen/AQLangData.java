package com.razordevs.ascended_quark.datagen;

import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class AQLangData extends NitrogenLanguageProvider {

    public AQLangData(DataGenerator output) {
        super(output, AscendedQuarkMod.MODID);
    }

    @Override
    protected void addTranslations() {
        Collection<RegistryObject<Block>> blocks = AQBlocks.BLOCKS.getEntries();
        Collection<RegistryObject<Item>> items = AQItems.ITEMS.getEntries();
        // blocks.remove(Blocks.DIRT);
        for (RegistryObject<Block> block : blocks)
        {
            this.add(block.get(), this.getName(block.getId().getPath()));
        }

        //for (RegistryObject<Item> item : items)
        //{
        //    this.add(item.get(), this.getName(item.getId().getPath()));
        //}
    }

    private String getName(String name) {
        char[] nameCharArray = name.toCharArray();
        String temp;
        temp = String.valueOf(nameCharArray[0]);
        nameCharArray[0] = temp.toUpperCase().toCharArray()[0];
        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) == '_') {
                nameCharArray[i] = ' ';
                temp = String.valueOf(nameCharArray[i+1]);
                nameCharArray[i+1] =  temp.toUpperCase().toCharArray()[0];
            }
        }

        String name2 = "";
        for(int i = 0; i < nameCharArray.length; i++) {
            name2 = name2 + nameCharArray[i];
        }

        return name2;
    }

}
