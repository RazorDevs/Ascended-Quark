package org.razordevs.ascended_quark.module;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

/**
 * Record class representing common woodset blocks for
 * compatibility use
 *
 * @param slab
 * @param planks
 * @param fence
 * @param log
 * @param leaves
 */
public record WoodSetContext(RegistryObject<? extends Block> slab, RegistryObject<? extends Block> planks,
                             RegistryObject<? extends Block> fence, RegistryObject<? extends Block> log,
                             RegistryObject<? extends Block> leaves) {
}
