package org.razordevs.ascended_quark.entity.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQBlocks;

public class AQBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AscendedQuark.MODID);

    public static final RegistryObject<BlockEntityType<SkyrootChestBlockEntity>> SKYROOT_CHEST = ENTITY_TYPES.register("skyroot_chest",
            () -> BlockEntityType.Builder.of(SkyrootChestBlockEntity::new, AQBlocks.SKYROOT_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<SkyrootTrappedChestBlockEntity>> SKYROOT_TRAPPED_CHEST = ENTITY_TYPES.register("skyroot_trapped_chest",
            () -> BlockEntityType.Builder.of(SkyrootTrappedChestBlockEntity::new, AQBlocks.SKYROOT_TRAPPED_CHEST.get()).build(null));


    public static final RegistryObject<BlockEntityType<HolystoneFurnaceBlockEntity>> HOLYSTONE_FURNACE = ENTITY_TYPES.register("holystone_furnace",
            () -> BlockEntityType.Builder.of(HolystoneFurnaceBlockEntity::new, AQBlocks.HOLYSTONE_FURNACE.get()).build(null));


}
