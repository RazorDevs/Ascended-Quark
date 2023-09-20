package com.razordevs.quark_aether.datagen;

import com.aetherteam.nitrogen.data.generators.NitrogenLanguageData;
import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import com.razordevs.quark_aether.QuarkAetherMod;
import com.razordevs.quark_aether.blocks.QABlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;

public class QALangData extends NitrogenLanguageProvider {

    public QALangData(DataGenerator output) {
        super(output, QuarkAetherMod.MODID);
    }

    @Override
    protected void addTranslations() {
        this.add(QABlocks.AETHER_COARSE_DIRT.get());
    }

    private void add(Block block) {
        String name = block.toString();
        char[] nameCharArray = name.toCharArray();
        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) == '_') {
                nameCharArray[i] = ' ';
                String temp = String.valueOf(nameCharArray[i-1]);
                nameCharArray[i-1] =  temp.toUpperCase().toCharArray()[0];
            }
        }

        System.out.println(nameCharArray.toString());
        this.add(block, toString());
    }

}
