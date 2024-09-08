package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.item.AetherCreativeTabs;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.violetmoon.zeta.block.IZetaBlock;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class MoreAetherBrickTypesModule extends ZetaModule {
    @Config(flag = "quicksoil_bricks", name = "Enable Quicksoil Bricks") public boolean quicksoil_bricks = true;
    @Config(flag = "aether_dirt_bricks", name = "Enable Aether Dirt Bricks") public boolean aether_dirt_bricks = true;
    @Config(flag = "icestone_bricks", name = "Enable  Polished Icestone and Icestone Bricks") public boolean icestone_bricks = true;



    @LoadEvent
    public void register(ZRegister event) {
        IZetaBlock polished = (IZetaBlock) (new ZetaBlock("polished_icestone", this, BlockBehaviour.Properties.of().mapColor(MapColor.ICE).instrument(NoteBlockInstrument.CHIME).strength(0.5F).randomTicks().sound(SoundType.GLASS).requiresCorrectToolForDrops())).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        IZetaBlock icestone_bricks = (IZetaBlock) (new ZetaBlock("icestone_bricks", this, BlockBehaviour.Properties.of().mapColor(MapColor.ICE).instrument(NoteBlockInstrument.CHIME).strength(0.5F).randomTicks().sound(SoundType.GLASS).requiresCorrectToolForDrops())).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        IZetaBlock quicksoil_bricks = (IZetaBlock) (new ZetaBlock("quicksoil_bricks", this, BlockBehaviour.Properties.copy(Blocks.SANDSTONE).requiresCorrectToolForDrops())).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());
        IZetaBlock aether_dirt_bricks =  (IZetaBlock) (new ZetaBlock("aether_dirt_bricks", this, Block.Properties.copy(Blocks.DIRT).requiresCorrectToolForDrops())).setCreativeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey());


        ImmutableSet.of(polished, icestone_bricks, quicksoil_bricks, aether_dirt_bricks).forEach((what)
                -> event.getVariantRegistry().addSlabStairsWall(what, AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey()));
    }
}
