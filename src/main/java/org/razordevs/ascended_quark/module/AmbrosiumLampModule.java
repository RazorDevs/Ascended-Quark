package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.razordevs.ascended_quark.RegistryUtil;
import org.razordevs.ascended_quark.blocks.AmbrosiumLampBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class AmbrosiumLampModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(),  new AmbrosiumLampBlock("ambrosium_lamp", this, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).requiresCorrectToolForDrops().lightLevel((blockState) -> AmbrosiumLampBlock.getScaledChargeLevel(blockState, 16))), AetherBlocks.AMBROSIUM_TORCH);
    }
}
