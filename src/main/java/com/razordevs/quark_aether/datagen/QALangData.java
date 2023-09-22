package com.razordevs.quark_aether.datagen;

import com.aetherteam.nitrogen.data.generators.NitrogenLanguageData;
import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import com.razordevs.quark_aether.QuarkAetherMod;
import com.razordevs.quark_aether.blocks.QABlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

public class QALangData extends NitrogenLanguageProvider {

    public QALangData(DataGenerator output) {
        super(output, QuarkAetherMod.MODID);
    }

    @Override
    protected void addTranslations() {
        this.add(QABlocks.AETHER_COARSE_DIRT);
    }

    private void add(RegistryObject<Block> block) {
        String name = block.getId().getPath();
        System.out.println(name);
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
        System.out.println(name2);
        this.add(block.get(), name2);
    }

}
