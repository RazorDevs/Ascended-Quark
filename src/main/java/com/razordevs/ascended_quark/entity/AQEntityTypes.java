package com.razordevs.ascended_quark.entity;

import com.razordevs.ascended_quark.AscendedQuarkMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class AQEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AscendedQuarkMod.MODID);


    public static final RegistryObject<EntityType<Stool>> STOOL = ENTITY_TYPES.register("stool",
            () -> EntityType.Builder.of(Stool::new, MobCategory.MISC)
            .sized(6F / 16F, 0.5F)
				.clientTrackingRange(3)
				.updateInterval(Integer.MAX_VALUE) // update interval
				.setShouldReceiveVelocityUpdates(false)
				.setCustomClientFactory((spawnEntity, world) -> new Stool(AQEntityTypes.STOOL.get(), world))
            .build("stool"));
	public static final RegistryObject<EntityType<AmbrosiumTorchArrow>> AMBROSIUM_TORCH_ARROW = ENTITY_TYPES.register("ambrosium_torch_arrow",
			() -> EntityType.Builder.<AmbrosiumTorchArrow>of(AmbrosiumTorchArrow::new, MobCategory.MISC)
			.sized(0.5F, 0.5F)
			.clientTrackingRange(4)
			.updateInterval(20) // update interval
			.build("ambrosium_torch_arrow"));
}
