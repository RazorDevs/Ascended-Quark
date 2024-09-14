package org.razordevs.ascended_quark.util;

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
import org.violetmoon.quark.content.building.block.VariantBookshelfBlock;
import org.violetmoon.quark.content.building.block.VariantLadderBlock;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;
import org.violetmoon.zeta.util.handler.ToolInteractionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class RegistryUtil {
    public static ArrayList<TabModel> TABS = new ArrayList<>();

    public static void registerWoodsetExtension(String type, ZetaModule module, RegistryObject<? extends Block> slab, RegistryObject<? extends Block> planks, RegistryObject<? extends Block> fence, RegistryObject<? extends Block> log, RegistryObject<? extends Block> leaves) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new ZetaBlock("vertical_" + type + "_planks", module, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS)), planks, module);
        SkyrootQuarkBlocksModule.makeChestBlocks(module, type, Blocks.CHEST, SoundType.WOOD, BooleanSuppliers.TRUE);
        createHedge(type + "_hedge", module, fence);
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHollowLogBlock("hollow_" + type + "_log", module), log, module);
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new VariantLadderBlock(type, module, BlockBehaviour.Properties.copy(Blocks.LADDER), true), planks, module);
        if(!type.equals("skyroot"))
            addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQVariantBookshelfBlock(type, module, true, SoundType.WOOD), planks, module);

        Block post = new AQWoodenPostBlock(type + "_post", module);
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), post, log, module);
        Block stripped = new AQWoodenPostBlock("stripped_" + type + "_post", module);
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), stripped, log, module);
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new CompAQVerticalSlabBlock(type + "_vertical_slab", slab, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS), module), planks, module);
        createLeafCarpet(type + "_leaf_carpet", module, leaves);
    }

    public static void registerDisabledWoodsetExtension(String type, ZetaModule module) {
        new ZetaBlock("vertical_" + type + "_planks", module, BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS));
        SkyrootQuarkBlocksModule.makeChestBlocks(module, type, Blocks.CHEST, SoundType.WOOD, BooleanSuppliers.TRUE);
        new AQHedgeBlock(type + "_hedge", module);
        new AQHollowLogBlock("hollow_" + type + "_log", module);
        new VariantLadderBlock(type, module, BlockBehaviour.Properties.copy(Blocks.LADDER), true);
        if(!type.equals("skyroot"))
            new AQVariantBookshelfBlock(type, module, true, SoundType.WOOD);
        Block post = new AQWoodenPostBlock(type + "_post", module);
        Block stripped = new AQWoodenPostBlock("stripped_" + type + "_post", module);
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
        new CompAQVerticalSlabBlock(type + "_vertical_slab", BlockPropertyUtil.copyPropertySafe(Blocks.OAK_PLANKS), module);
        new AQLeafCarpetBlock(type + "_leaf_carpet", module);
    }

    public static void addCreativeModeTab(ResourceKey<CreativeModeTab> tab, ItemLike item, RegistryObject<? extends ItemLike> parent, ZetaModule module) {
        if(!module.enabled)
            return;

        boolean flag = false;
        for(TabModel tm : TABS){
            if(tm.getTab().equals(tab)){
                tm.add(item, parent);
                flag = true;
            }
        }
        if(!flag){
            HashMap<ItemLike, Supplier<? extends ItemLike>> map = new HashMap<>();
            map.put(item, parent);
            TABS.add(new TabModel(tab, map));
        }
    }


    public static void createHedge(String name, ZetaModule module, RegistryObject<? extends Block> fence) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQHedgeBlock(name, module), fence, module);
    }

    public static void createLeafCarpet(String name, ZetaModule module, RegistryObject<? extends Block> leaves) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new AQLeafCarpetBlock(name, module), leaves, module);
    }

    public static void createLeafCarpetParticle(String name, ZetaModule module, RegistryObject<? extends Block> leaves, Supplier<? extends ParticleOptions> particle) {
        addCreativeModeTab(AetherCreativeTabs.AETHER_NATURAL_BLOCKS.getKey(), new LeafCarpetWithParticlesBlock(name, module, particle), leaves, module);
    }

    public static ToIntFunction<BlockState> litBlockEmission(int emission) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? emission : 0;
    }
}

