package org.razordevs.ascended_quark.datagen.tags;

import com.aetherteam.aether.AetherTags;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import vazkii.quark.base.Quark;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class AQBlockTagData extends BlockTagsProvider {

    public AQBlockTagData(DataGenerator output,  @Nullable ExistingFileHelper helper) {
        super(output, AscendedQuark.MODID, helper);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Ascended Quark Block Tags";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {

        // Makes tool debuff work with all Ascended Quark blocks. Commented code can be used to remove blocks if necessary
        TagAppender<Block> tag = this.tag(AetherTags.Blocks.TREATED_AS_AETHER_BLOCK);
        Collection<RegistryObject<Block>> blocks = AQBlocks.BLOCKS.getEntries();
        // blocks.remove(Blocks.DIRT);
        for (RegistryObject<Block> block : blocks) {
            tag.add(block.get());
        }

        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                AQBlocks.AETHER_DIRT_BRICKS.get(),
                AQBlocks.AETHER_DIRT_BRICK_SLAB.get(),
                AQBlocks.AETHER_DIRT_BRICK_STAIRS.get(),
                AQBlocks.AETHER_DIRT_BRICK_WALL.get(),
                AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.QUICKSOIL_BRICKS.get(),
                AQBlocks.QUICKSOIL_BRICK_SLAB.get(),
                AQBlocks.QUICKSOIL_BRICK_STAIRS.get(),
                AQBlocks.QUICKSOIL_BRICK_WALL.get(),
                AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get()
        );

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                AQBlocks.HOLYSTONE_FURNACE.get(),
                AQBlocks.AMBROSIUM_LAMP.get(),
                AQBlocks.ANGELIC_VERTICAL_SLAB.get(),
                AQBlocks.HELLFIRE_VERTICAL_SLAB.get(),
                AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get(),
                AQBlocks.ICESTONE_VERTICAL_SLAB.get(),
                AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.AEROGEL_VERTICAL_SLAB.get(),
                AQBlocks.ICESTONE_BRICKS.get(),
                AQBlocks.ICESTONE_BRICK_SLAB.get(),
                AQBlocks.ICESTONE_VERTICAL_SLAB.get(),
                AQBlocks.ICESTONE_BRICK_STAIRS.get(),
                AQBlocks.ICESTONE_BRICK_WALL.get(),
                AQBlocks.SKYROOT_STICK_BLOCK.get()
        );

        tag(BlockTags.MINEABLE_WITH_AXE).add(
                AQBlocks.SKYROOT_HEDGE.get(),
                AQBlocks.GOLDEN_SKYROOT_HEDGE.get(),
                AQBlocks.CRYSTAL_SKYROOT_HEDGE.get(),
                AQBlocks.CRYSTAL_FRUIT_SKYROOT_HEDGE.get(),
                AQBlocks.HOLIDAY_SKYROOT_HEDGE.get(),
                AQBlocks.DECORATED_HOLIDAY_SKYROOT_HEDGE.get(),
                AQBlocks.HOLLOW_SKYROOT_LOG.get(),
                AQBlocks.SKYROOT_POST.get(),
                AQBlocks.STRIPPED_SKYROOT_POST.get(),
                AQBlocks.SKYROOT_CHEST.get(),
                AQBlocks.SKYROOT_LADDER.get(),
                AQBlocks.SKYROOT_STOOL.get(),
                AQBlocks.BLUE_BERRY_CRATE.get(),
                AQBlocks.SKYROOT_VERTICAL_SLAB.get()
        );

        tag(BlockTags.MINEABLE_WITH_HOE).add(
                AQBlocks.SKYROOT_LEAF_CARPET.get(),
                AQBlocks.GOLDEN_OAK_LEAF_CARPET.get(),
                AQBlocks.HOLIDAY_LEAF_CARPET.get(),
                AQBlocks.DECORATED_HOLIDAY_LEAF_CARPET.get(),
                AQBlocks.CRYSTAL_FRUIT_LEAF_CARPET.get(),
                AQBlocks.CRYSTAL_LEAF_CARPET.get()
        );

        tag(BlockTags.SLABS).add(
                AQBlocks.AETHER_DIRT_BRICK_SLAB.get(),
                AQBlocks.QUICKSOIL_BRICK_SLAB.get(),
                AQBlocks.ICESTONE_BRICK_SLAB.get(),
                AQBlocks.POLISHED_ICESTONE_SLAB.get()
        );

        tag(BlockTags.STAIRS).add(
                AQBlocks.AETHER_DIRT_BRICK_STAIRS.get(),
                AQBlocks.QUICKSOIL_BRICK_STAIRS.get(),
                AQBlocks.ICESTONE_BRICK_STAIRS.get(),
                AQBlocks.POLISHED_ICESTONE_STAIRS.get()
        );

        tag(BlockTags.WALLS).add(
                AQBlocks.AETHER_DIRT_BRICKS.get(),
                AQBlocks.QUICKSOIL_BRICK_WALL.get(),
                AQBlocks.ICESTONE_BRICK_WALL.get(),
                AQBlocks.POLISHED_ICESTONE_WALL.get()
        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "posts"))).add(
                AQBlocks.SKYROOT_POST.get(),
                AQBlocks.STRIPPED_SKYROOT_POST.get()

        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "stools"))).add(
                AQBlocks.SKYROOT_STOOL.get()
        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "hedges"))).add(
                AQBlocks.SKYROOT_HEDGE.get(),
                AQBlocks.GOLDEN_SKYROOT_HEDGE.get(),
                AQBlocks.CRYSTAL_SKYROOT_HEDGE.get(),
                AQBlocks.CRYSTAL_FRUIT_SKYROOT_HEDGE.get(),
                AQBlocks.HOLIDAY_SKYROOT_HEDGE.get(),
                AQBlocks.DECORATED_HOLIDAY_SKYROOT_HEDGE.get()
        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "hollow_logs"))).add(
                AQBlocks.HOLLOW_SKYROOT_LOG.get()
        );

        tag(Tags.Blocks.CHESTS_WOODEN).add(
                AQBlocks.SKYROOT_CHEST.get()
        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "ladders"))).add(
                AQBlocks.SKYROOT_LADDER.get()
        );

        tag(BlockTags.FALL_DAMAGE_RESETTING).add(
                AQBlocks.SKYROOT_LADDER.get()
        );

        tag(BlockTags.CLIMBABLE).add(
                AQBlocks.SKYROOT_LADDER.get()
        );

        tag(BlockTags.create(new ResourceLocation(Quark.MOD_ID, "vertical_slabs"))).add(
                AQBlocks.AEROGEL_VERTICAL_SLAB.get(),
                AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.ANGELIC_VERTICAL_SLAB.get(),
                AQBlocks.HELLFIRE_VERTICAL_SLAB.get(),
                AQBlocks.ICESTONE_VERTICAL_SLAB.get(),
                AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get(),
                AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.HOLYSTONE_VERTICAL_SLAB.get(),
                AQBlocks.SKYROOT_VERTICAL_SLAB.get(),
                AQBlocks.ICESTONE_BRICK_VERTICAL_SLAB.get(),
                AQBlocks.POLISHED_ICESTONE_VERTICAL_SLAB.get()
        );
    }
}
