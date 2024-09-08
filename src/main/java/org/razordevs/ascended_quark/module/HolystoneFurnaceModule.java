package org.razordevs.ascended_quark.module;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.razordevs.ascended_quark.RegistryUtil;
import org.violetmoon.quark.content.building.block.VariantFurnaceBlock;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

@ZetaLoadModule(category = "aether")
public class HolystoneFurnaceModule extends ZetaModule {

    @LoadEvent
    public void register(ZRegister register) {
        new VariantFurnaceBlock("holystone", this, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F).requiresCorrectToolForDrops().lightLevel(RegistryUtil.litBlockEmission(13)));
    }
}
