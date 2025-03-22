package org.razordevs.ascended_quark.datagen.compat.deep_aether;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.razordevs.ascended_quark.datagen.normal.AQBlockstateData;
import teamrazor.deepaether.init.DABlocks;

import java.util.HashMap;

public class DACompBlockstateData extends AQBlockstateData {
    public DACompBlockstateData(PackOutput output, ExistingFileHelper helper, HashMap<String, Block> blockMap) {
        super(output, helper, blockMap);
    }

    @Override
    public void registerStatesAndModels() {
        this.woodset("roseroot", DABlocks.ROSEROOT_LOG.get(), DABlocks.STRIPPED_ROSEROOT_LOG.get(), DABlocks.ROSEROOT_PLANKS.get(), DABlocks.ROSEROOT_LEAVES.get());
        this.leafCarpet("blue_roseroot", DABlocks.BLUE_ROSEROOT_LEAVES.get());
        this.leafCarpet("flowering_blue_roseroot", DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get());
        this.leafCarpet("flowering_roseroot", DABlocks.FLOWERING_ROSEROOT_LEAVES.get());

        this.hedge("blue_roseroot", DABlocks.BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get());
        this.hedge("flowering_blue_roseroot", DABlocks.FLOWERING_BLUE_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get());
        this.hedge("flowering_roseroot", DABlocks.FLOWERING_ROSEROOT_LEAVES.get(), DABlocks.ROSEROOT_LOG.get());

        this.woodset("cruderoot", DABlocks.CRUDEROOT_LOG.get(), DABlocks.STRIPPED_CRUDEROOT_LOG.get(), DABlocks.CRUDEROOT_PLANKS.get(), DABlocks.CRUDEROOT_LEAVES.get());
        this.woodset("sunroot", DABlocks.SUNROOT_LOG.get(), DABlocks.STRIPPED_SUNROOT_LOG.get(), DABlocks.SUNROOT_PLANKS.get(), DABlocks.SUNROOT_LEAVES.get());
        this.woodset("yagroot", DABlocks.YAGROOT_LOG.get(), DABlocks.STRIPPED_YAGROOT_LOG.get(), DABlocks.YAGROOT_PLANKS.get(), DABlocks.YAGROOT_LEAVES.get());
        this.woodset("conberry", DABlocks.CONBERRY_LOG.get(), DABlocks.STRIPPED_CONBERRY_LOG.get(), DABlocks.CONBERRY_PLANKS.get(), DABlocks.CONBERRY_LEAVES.get());

        this.compressed("goldenleaf_berries_crate");


        this.verticalSlab("mossy_holystone_tile", DABlocks.MOSSY_HOLYSTONE_TILES.get());
        this.verticalSlab("holystone_tile", DABlocks.HOLYSTONE_TILES.get());
        this.verticalSlab("big_holystone_bricks", DABlocks.BIG_HOLYSTONE_BRICKS.get());
        this.verticalSlab("aseterite", DABlocks.ASETERITE.get());
        this.verticalSlab("polished_aseterite", DABlocks.POLISHED_ASETERITE.get());
        this.verticalSlab("aseterite_bricks", DABlocks.ASETERITE_BRICKS.get());
        this.verticalSlab("raw_clorite", DABlocks.RAW_CLORITE.get());
        this.verticalSlab("clorite", DABlocks.CLORITE.get());
        this.verticalSlab("polished_clorite", DABlocks.POLISHED_CLORITE.get());
        this.verticalSlab("aether_mud_bricks", DABlocks.AETHER_MUD_BRICKS.get());
        this.verticalSlab("nimbus", DABlocks.NIMBUS_STONE.get());

        this.blockCutout("aether_mud_brick_lattice");
        this.pillar((RotatedPillarBlock) blockMap.get("aether_mud_pillar"));
    }
}