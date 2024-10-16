package com.razordevs.ascended_quark.datagen.tags;

import com.aetherteam.aether.AetherTags;
import com.razordevs.ascended_quark.AscendedQuark;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.base.Quark;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class AQItemTagData extends ItemTagsProvider {

    public AQItemTagData(DataGenerator output, BlockTagsProvider blocks, @Nullable ExistingFileHelper helper) {
        super(output, blocks, AscendedQuark.MODID, helper);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Ascended Quark Item Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {

        // Makes tool debuff work with all Deep Aether blocks. Commented code can be used to remove blocks if necessary
        TagAppender<Item> tag = this.tag(AetherTags.Items.TREATED_AS_AETHER_ITEM);
        Collection<RegistryObject<Item>> items = AQItems.ITEMS.getEntries();
        // blocks.remove(Blocks.DIRT);
        for (RegistryObject<Item> item : items) {
            tag.add(item.get());
        }

        tag(AetherTags.Items.PLANKS_CRAFTING).add(
                AQBlocks.VERTICAL_SKYROOT_PLANKS.get().asItem()
        );

        tag(ItemTags.SLABS).add(
                AQBlocks.AETHER_DIRT_BRICK_SLAB.get().asItem()
        );

        tag(ItemTags.STAIRS).add(
                AQBlocks.AETHER_DIRT_BRICK_STAIRS.get().asItem()
        );

        tag(ItemTags.WALLS).add(
                AQBlocks.AETHER_DIRT_BRICKS.get().asItem()
        );

        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "posts"))).add(
                AQBlocks.SKYROOT_POST.get().asItem(),
                AQBlocks.STRIPPED_SKYROOT_POST.get().asItem()
        );

        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "stools"))).add(
                AQBlocks.SKYROOT_STOOL.get().asItem()
        );

        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "hedges"))).add(
            AQBlocks.SKYROOT_HEDGE.get().asItem()
        );

        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "hollow_logs"))).add(
                AQBlocks.HOLLOW_SKYROOT_LOG.get().asItem()
        );

        tag(Tags.Items.CHESTS_WOODEN).add(
                AQBlocks.SKYROOT_CHEST.get().asItem()
        );

        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "ladders"))).add(
                AQBlocks.SKYROOT_LADDER.get().asItem()
        );

        tag(ItemTags.ARROWS).add(
                AQItems.AMBROSIUM_TORCH_ARROW.get()
        );
        tag(ItemTags.create(new ResourceLocation(Quark.MOD_ID, "vertical_slabs"))).add(
                AQBlocks.AEROGEL_VERTICAL_SLAB.get().asItem(),
                AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get().asItem(),
                AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get().asItem(),
                AQBlocks.ANGELIC_VERTICAL_SLAB.get().asItem(),
                AQBlocks.HELLFIRE_VERTICAL_SLAB.get().asItem(),
                AQBlocks.ICESTONE_VERTICAL_SLAB.get().asItem(),
                AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get().asItem(),
                AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get().asItem(),
                AQBlocks.HOLYSTONE_VERTICAL_SLAB.get().asItem(),
                AQBlocks.SKYROOT_VERTICAL_SLAB.get().asItem()
        );
    }
}
