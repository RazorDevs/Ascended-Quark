package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.razordevs.ascended_quark.blocks.AQGlassBlock;
import org.razordevs.ascended_quark.blocks.AQGlassPaneBlock;
import org.violetmoon.quark.base.util.BlockPropertyUtil;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.item.ZetaItem;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class QuicksoilFramedGlassModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        Block block = new AQGlassBlock("quicksoil_framed_glass", BlockPropertyUtil.copyPropertySafe(Blocks.GLASS).friction(1.1F).lightLevel(s -> 11), this);
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), block, AetherBlocks.QUICKSOIL_GLASS, this);
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_BUILDING_BLOCKS.getKey(), new AQGlassPaneBlock("quicksoil_framed_glass_pane", BlockPropertyUtil.copyPropertySafe(block), this), AetherBlocks.QUICKSOIL_GLASS_PANE, this);
        new ZetaItem("quicksoil_glass_shard", this, new Item.Properties()).setCreativeTab(AetherCreativeTabs.AETHER_INGREDIENTS.getKey());
    }
}