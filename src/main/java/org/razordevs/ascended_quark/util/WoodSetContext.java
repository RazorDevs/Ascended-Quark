package org.razordevs.ascended_quark.util;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Record class representing common woodset blocks for compatibility use
 *
 * @param slab
 * @param planks
 * @param fence
 * @param log
 * @param leaves
 */
public record WoodSetContext(DeferredHolder<Block, ? extends Block> slab, DeferredHolder<Block, ? extends Block> planks,
		DeferredHolder<Block, ? extends Block> fence, DeferredHolder<Block, ? extends Block> log,
		DeferredHolder<Block, ? extends Block> leaves) {
}
