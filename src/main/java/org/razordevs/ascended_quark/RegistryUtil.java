package org.razordevs.ascended_quark;

import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.RegistryObject;
import org.razordevs.ascended_quark.blocks.*;
import org.razordevs.ascended_quark.module.SkyrootQuarkBlocksModule;
import org.violetmoon.quark.base.util.BlockPropertyUtil;
import org.violetmoon.quark.content.building.block.VariantLadderBlock;
import org.violetmoon.quark.content.building.module.VariantChestsModule;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;
import org.violetmoon.zeta.util.handler.ToolInteractionHandler;

import java.util.HashMap;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class RegistryUtil {
    protected static HashMap<ResourceKey<CreativeModeTab>, HashMap<ItemLike, Supplier<? extends ItemLike>>> TABS = new HashMap<>();

    public static void registerWoodsetExtension(String type, ZetaModule module, RegistryObject<SlabBlock> slab, RegistryObject<Block> planks, RegistryObject<FenceBlock> fence, RegistryObject<RotatedPillarBlock> log, RegistryObject<Block> leaves) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new ZetaBlock("vertical_" + type + "_planks", module, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS)), planks);
        SkyrootQuarkBlocksModule.makeChestBlocks(module, type, Blocks.CHEST, SoundType.WOOD, BooleanSuppliers.TRUE);

        createHedge(type+"_hedge", module, leaves, fence);

        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHollowLogBlock("hollow_"+type+"_log", module), log);
        new VariantLadderBlock(type, module, BlockBehaviour.Properties.copy(Blocks.LADDER), true).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        Block post = new AQWoodenPostBlock(type + "_post", module).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), post, log);
        Block stripped = new AQWoodenPostBlock("stripped_"+type + "_post", module).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), stripped, log);
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);

        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new CompAQVerticalSlabBlock(type + "_vertical_slab", slab, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS), module), planks);

        createLeafCarpet(type + "_leaf_carpet", module, leaves);
    }

    public static void addCreativeModeTab(ResourceKey<CreativeModeTab> tab, ItemLike item, RegistryObject<? extends ItemLike> parent) {
        if (TABS.containsKey(tab)) {
            TABS.get(tab).put(item, parent);
        } else {
            HashMap<ItemLike, Supplier<? extends ItemLike>> map = new HashMap<>();
            map.put(item, parent);
            TABS.put(tab, map);
        }
    }


    public static void createHedge(String name, ZetaModule module, RegistryObject<? extends Block> leaves, RegistryObject<? extends Block> fence) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHedgeBlock(name, module, leaves), fence);
    }

    public static void createLeafCarpet(String name, ZetaModule module, RegistryObject<Block> leaves) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new AQLeafCarpetBlock(name, module), leaves);
    }

    public static void createLeafCarpetParticle(String name, ZetaModule module, RegistryObject<? extends Block> leaves, Supplier<? extends ParticleOptions> particle) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new LeafCarpetWithParticlesBlock(name, module, particle), leaves);
    }

    public static ToIntFunction<BlockState> litBlockEmission(int emission) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? emission : 0;
    }
}

