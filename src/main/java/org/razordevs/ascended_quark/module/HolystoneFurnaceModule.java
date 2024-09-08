package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.razordevs.ascended_quark.blocks.AQVariantFurnaceBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class HolystoneFurnaceModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(), new AQVariantFurnaceBlock("holystone", this, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).requiresCorrectToolForDrops().lightLevel(RegistryUtil.litBlockEmission(13))), AetherBlocks.ALTAR);
    }
}
