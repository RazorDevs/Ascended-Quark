package com.razordevs.quark_aether.blocks;

import com.aetherteam.aether.Aether;
import com.razordevs.quark_aether.QuarkAetherMod;
import com.razordevs.quark_aether.items.QAItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class QABlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, QuarkAetherMod.MODID);


    public static final RegistryObject<Block> AETHER_COARSE_DIRT = registerBlock("aether_coarse_dirt", () -> new AetherCoarseDirtBlock(Block.Properties.copy(Blocks.COARSE_DIRT)));






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return QAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
