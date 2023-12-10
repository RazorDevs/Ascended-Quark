package com.razordevs.ascended_quark.items;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.entity.AmbrosiumTorchArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.base.item.QuarkArrowItem;
import vazkii.quark.content.tools.module.TorchArrowModule;

public class AQItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AscendedQuarkMod.MODID);

    public static final RegistryObject<AQSlimeInABucketItem> SLIME_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("slime_in_a_skyroot_bucket", ()-> new AQSlimeInABucketItem(EntityType.SLIME, true));

    public static final RegistryObject<AQSwetInABucketItem> BLUE_SWET_IN_A_BUCKET_ITEM = ITEMS.register("blue_swet_in_a_bucket", ()-> new AQSwetInABucketItem(AetherEntityTypes.BLUE_SWET.get(), false));
    public static final RegistryObject<AQSwetInABucketItem> BLUE_SWET_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("blue_swet_in_a_skyroot_bucket", ()-> new AQSwetInABucketItem(AetherEntityTypes.BLUE_SWET.get(), true));

    public static final RegistryObject<AQSwetInABucketItem> GOLDEN_SWET_IN_A_BUCKET_ITEM = ITEMS.register("golden_swet_in_a_bucket", ()-> new AQSwetInABucketItem(AetherEntityTypes.GOLDEN_SWET.get(), false));
    public static final RegistryObject<AQSwetInABucketItem> GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("golden_swet_in_a_skyroot_bucket", ()-> new AQSwetInABucketItem(AetherEntityTypes.GOLDEN_SWET.get(), true));

    public static final RegistryObject<Item> AMBROSIUM_TORCH_ARROW = ITEMS.register("ambrosium_torch_arrow", () -> new QuarkArrowItem.Impl("ambrosium_torch_arrow", new TorchArrowModule(), (level, stack, living) -> new AmbrosiumTorchArrow(level, living)));
}
