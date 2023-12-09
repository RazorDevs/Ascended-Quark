package com.razordevs.ascended_quark.items;

import com.aetherteam.aether.item.miscellaneous.bucket.SkyrootBucketItem;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.content.tools.item.SlimeInABucketItem;

public class AQItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AscendedQuarkMod.MODID);


    public static final RegistryObject<AQSlimeInABucketItem> SLIME_IN_A_SKYROOT_BUCKET_ITEM = ITEMS.register("slime_in_a_skyroot_bucket", ()-> new AQSlimeInABucketItem(EntityType.SLIME));



}
