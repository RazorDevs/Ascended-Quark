package org.razordevs.ascended_quark;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.RegistryObject;
import org.razordevs.ascended_quark.blocks.*;
import org.violetmoon.quark.base.util.BlockPropertyUtil;
import org.violetmoon.quark.content.building.block.VariantLadderBlock;
import org.violetmoon.quark.content.building.module.VariantChestsModule;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;
import org.violetmoon.zeta.util.handler.ToolInteractionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class RegistryUtil {
    public static void registerWoodsetExtension(String type, ZetaModule module, RegistryObject<? extends Block> slab) {
        new ZetaBlock("vertical_" + type + "_planks", module, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS)).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        VariantChestsModule.makeChestBlocksExternal(module, type, Blocks.CHEST, SoundType.WOOD, BooleanSuppliers.TRUE);

        new AQHedgeBlock(type +"_hedge", module, AetherBlocks.SKYROOT_LEAVES).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());

        new AQHollowLogBlock("hollow_"+type+"_log", module).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        new VariantLadderBlock(type, module, BlockBehaviour.Properties.copy(Blocks.LADDER), true).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        Block post = new AQWoodenPostBlock(type + "_post", module).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        Block stripped = new AQWoodenPostBlock("stripped_"+type + "_post", module).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);

        new CompAQVerticalSlabBlock(type + "_vertical_slab", slab, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS), module).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());

        createLeafCarpet(type + "_leaf_carpet", module);
    }

    public static void createHedge(String name, ZetaModule module, Supplier<Block> leaves) {
        new AQHedgeBlock(name, module, leaves).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
    }

    public static void createLeafCarpet(String name, ZetaModule module) {
        new AQLeafCarpetBlock(name, module).setCreativeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey());
    }

    public static void createLeafCarpetParticle(String name, ZetaModule module, Supplier<? extends ParticleOptions> particle) {
        new LeafCarpetWithParticlesBlock(name, module, particle).setCreativeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey());
    }

    public static ToIntFunction<BlockState> litBlockEmission(int emission) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? emission : 0;
    }

    public static List<Block> dungeonBlocks = new ArrayList<>();
}

