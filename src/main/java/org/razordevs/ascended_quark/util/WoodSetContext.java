package org.razordevs.ascended_quark.util;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

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
public record WoodSetContext(
        DeferredHolder<?, ? extends Block> slab, DeferredHolder<?, ? extends Block> planks,
        DeferredHolder<?, ? extends Block> fence, DeferredHolder<?, ? extends Block> log,
        DeferredHolder<?, ? extends Block> leaves) {
}
