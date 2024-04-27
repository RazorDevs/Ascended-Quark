package org.razordevs.ascended_quark.items;

import com.aetherteam.aether.entity.AetherEntityTypes;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.entity.AmbrosiumTorchArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.violetmoon.quark.content.tools.module.TorchArrowModule;
import org.violetmoon.zeta.item.ZetaArrowItem;

public class AQItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AscendedQuark.MODID);

    public static final RegistryObject<AQSlimeInABucketItem> SLIME_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("slime_in_a_skyroot_bucket", () -> new AQSlimeInABucketItem(EntityType.SLIME, true));

    public static final RegistryObject<AQSwetInABucketItem> BLUE_SWET_IN_A_BUCKET_ITEM = ITEMS.register("blue_swet_in_a_bucket", () -> new AQSwetInABucketItem(AetherEntityTypes.BLUE_SWET, false));
    public static final RegistryObject<AQSwetInABucketItem> BLUE_SWET_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("blue_swet_in_a_skyroot_bucket", () -> new AQSwetInABucketItem(AetherEntityTypes.BLUE_SWET, true));

    public static final RegistryObject<AQSwetInABucketItem> GOLDEN_SWET_IN_A_BUCKET_ITEM = ITEMS.register("golden_swet_in_a_bucket", () -> new AQSwetInABucketItem(AetherEntityTypes.GOLDEN_SWET, false));
    public static final RegistryObject<AQSwetInABucketItem> GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("golden_swet_in_a_skyroot_bucket", () -> new AQSwetInABucketItem(AetherEntityTypes.GOLDEN_SWET, true));

    public static final RegistryObject<Item> AMBROSIUM_TORCH_ARROW = ITEMS.register("ambrosium_torch_arrow", () -> new ZetaArrowItem.Impl("ambrosium_torch_arrow", new TorchArrowModule(), (level, stack, living) -> new AmbrosiumTorchArrow(level, living)));
    public static final RegistryObject<Item> QUICKSOIL_GLASS_SHARD = ITEMS.register("quicksoil_glass_shard", () -> new Item(new Item.Properties()));
}
