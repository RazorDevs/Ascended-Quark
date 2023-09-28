package com.razordevs.ascended_quark.entity.block;

import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AQBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AscendedQuarkMod.MODID);

    public static final RegistryObject<BlockEntityType<SkyrootChestBlockEntity>> SKYROOT_CHEST = ENTITY_TYPES.register("skyroot_chest",
            () -> BlockEntityType.Builder.of(SkyrootChestBlockEntity::new, AQBlocks.SKYROOT_CHEST.get()).build(null));


}
