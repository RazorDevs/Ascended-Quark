package com.razordevs.ascended_quark.datagen;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.quark.content.building.block.VerticalSlabBlock;


import java.util.Map;


public class AQBlockstateData extends BlockStateProvider {
    public AQBlockstateData(DataGenerator output, ExistingFileHelper helper) {
        super(output, AscendedQuarkMod.MODID, helper);
    }

    @Override
    public void registerStatesAndModels() {
        this.block(AQBlocks.AETHER_DIRT_BRICKS.get());
        this.slab(AQBlocks.AETHER_DIRT_BRICK_SLAB.get(), AQBlocks.AETHER_DIRT_BRICKS.get());
        this.stairs(AQBlocks.AETHER_DIRT_BRICK_STAIRS.get(), AQBlocks.AETHER_DIRT_BRICKS.get());
        this.wallBlock(AQBlocks.AETHER_DIRT_BRICK_WALL.get(), AQBlocks.AETHER_DIRT_BRICKS.get());

        this.block(AQBlocks.QUICKSOIL_BRICKS.get());
        this.slab(AQBlocks.QUICKSOIL_BRICK_SLAB.get(), AQBlocks.QUICKSOIL_BRICKS.get());
        this.stairs(AQBlocks.QUICKSOIL_BRICK_STAIRS.get(), AQBlocks.QUICKSOIL_BRICKS.get());
        this.wallBlock(AQBlocks.QUICKSOIL_BRICK_WALL.get(), AQBlocks.QUICKSOIL_BRICKS.get());

        this.block(AQBlocks.POLISHED_ICESTONE.get());
        this.slab(AQBlocks.POLISHED_ICESTONE_SLAB.get(), AQBlocks.POLISHED_ICESTONE.get());
        this.stairs(AQBlocks.POLISHED_ICESTONE_STAIRS.get(), AQBlocks.POLISHED_ICESTONE.get());
        this.wallBlock(AQBlocks.POLISHED_ICESTONE_WALL.get(), AQBlocks.POLISHED_ICESTONE.get());

        this.block(AQBlocks.ICESTONE_BRICKS.get());
        this.slab(AQBlocks.ICESTONE_BRICK_SLAB.get(), AQBlocks.ICESTONE_BRICKS.get());
        this.stairs(AQBlocks.ICESTONE_BRICK_STAIRS.get(), AQBlocks.ICESTONE_BRICKS.get());
        this.wallBlock(AQBlocks.ICESTONE_BRICK_WALL.get(), AQBlocks.ICESTONE_BRICKS.get());

        this.translucentBlock(AQBlocks.QUICKSOIL_FRAMED_GLASS.get());
    }


    public ResourceLocation texture(String name) {
        return this.modLoc("block/"  + name);
    }
    public ResourceLocation texture(String name, String suffix) {
        return this.modLoc("block/"  + name + suffix);
    }

    public void fence(FenceBlock block, Block baseBlock) {
        this.fenceBlock(block, this.texture(this.name(baseBlock)));
        this.fenceColumn(block, this.name(baseBlock));
    }
    public void translucentBlock(Block block) {
        this.simpleBlock(block, this.cubeAllTranslucent(block));
    }

    public ModelFile cubeAllTranslucent(Block block) {
        return (this.models().cubeAll(this.name(block), this.texture(this.name(block)))).renderType(new ResourceLocation("translucent"));
    }
    public void fenceColumn(CrossCollisionBlock block, String side) {
        String baseName = this.name(block);
        this.fourWayBlock(block,
                this.models().fencePost(baseName + "_post", this.texture(side)),
                this.models().fenceSide(baseName + "_side", this.texture(side)));
    }

    public ModelFile cubeBottomTop(String block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        return this.models().cubeBottomTop(block, side, bottom, top);
    }

    public void fenceGateBlock(FenceGateBlock block, Block baseBlock) {
        this.fenceGateBlockInternal(block, this.name(block), this.texture(this.name(baseBlock)));
    }

    private void fenceGateBlockInternal(FenceGateBlock block, String baseName, ResourceLocation texture) {
        ModelFile gate = models().fenceGate(baseName, texture);
        ModelFile gateOpen = models().fenceGateOpen(baseName + "_open", texture);
        ModelFile gateWall = models().fenceGateWall(baseName + "_wall", texture);
        ModelFile gateWallOpen = models().fenceGateWallOpen(baseName + "_wall_open", texture);
        fenceGateBlock(block, gate, gateOpen, gateWall, gateWallOpen);
    }

    public void wood(RotatedPillarBlock block, RotatedPillarBlock baseBlock) {
        this.axisBlock(block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }
    public void pottedPlant(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void pottedPlantFix(Block block, Block flower) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/"  + this.name(flower) + "_pot")).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void saplingBlock(Block block) {
        ModelFile sapling = models().cross(this.name(block), this.texture(this.name(block))).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(sapling).build(), SaplingBlock.STAGE);
    }

    public void stairs(Block block, Block baseBlock) {
        this.stairsBlock((StairBlock) block, this.texture(this.name(baseBlock)));
    }

    public void slab(Block block, Block baseBlock) {
        this.slabBlock((SlabBlock) block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void verticalSlabBlock(Block block, Block baseBlock) {
        this.verticalSlabBlock((VerticalSlabBlock) block, this.texture(this.name(baseBlock)), this.texture(this.name(baseBlock)));
    }

    public void verticalSlabBlock(VerticalSlabBlock block, ResourceLocation doubleslab, ResourceLocation texture) {
        verticalSlabBlock(block, doubleslab, texture, texture, texture);
    }

    public void verticalSlabBlock(VerticalSlabBlock block, ResourceLocation doubleslab, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        ModelFile vertical_slab = this.models().withExistingParent(this.name(block), new ResourceLocation("quark", "block/vertical_slab"))
                .texture("bottom", bottom).texture("top", top).texture("side", side);
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(vertical_slab));
    }

    public void wallBlock(Block block, Block baseBlock) {
        this.wallBlockInternal((WallBlock) block, this.name(block), this.texture(this.name(baseBlock)));
    }
    public void blockDoubleDrops(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept(state -> ConfiguredModel.builder().modelFile(this.cubeAll(block)).build(), AetherBlockStateProperties.DOUBLE_DROPS);
    }

    private void wallBlockInternal(WallBlock block, String baseName, ResourceLocation texture) {
        wallBlock(block, models().wallPost(baseName + "_post", texture),
                models().wallSide(baseName + "_side", texture),
                models().wallSideTall(baseName + "_side_tall", texture));
    }

    //GENESIS COMPAT
    protected BlockModelBuilder makeWallPostModel(int width, int height, String name) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(8 - width, 0.0F, 8 - width).to(8 + width, height, 8 + width)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    protected BlockModelBuilder makeWallSideModel(int length, int height, String name, ModelBuilder.FaceRotation faceRotation, int u1, int u2) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, height, length)
                .face(Direction.DOWN).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    public void logWallBlock(WallBlock block, Block baseBlock, String location, String modid, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlockInternal(block, this.name(block), new ResourceLocation(modid, "block/" + location + this.name(baseBlock)), postUsesTop, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
    }

    private void logWallBlockInternal(WallBlock block, String baseName, ResourceLocation texture, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlock(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post_short").parent(postShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_post_tall").parent(postTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side").parent(side).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt").parent(sideAlt).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall").parent(sideTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt").parent(sideTallAlt).texture("particle", texture).texture("top", texture).texture("side", texture)
        );

        this.logWallBlockWithPost(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post").parent(postBig).texture("particle", texture).texture("top", postUsesTop ? (texture + "_top") : texture.toString()).texture("side", texture),
                models().getBuilder(baseName + "_side_short").parent(sideShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt_short").parent(sideAltShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_short").parent(sideTallShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt_short").parent(sideTallAltShort).texture("particle", texture).texture("top", texture).texture("side", texture)
        );
    }

    public void logWallBlock(MultiPartBlockStateBuilder builder, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Smaller post when West & East are both short while North & South being none
                .part().modelFile(postShort).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.LOW).end().end()
                // Taller thinner post when West & East are both tall while North & South being none
                .part().modelFile(postTall).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.TALL).end().end()
                // Rotated small post when West & East are both none while North & South are short
                .part().modelFile(postShort).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.LOW).end().end()
                // Rotated small post when West & East are both none while North & South are tall
                .part().modelFile(postTall).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.TALL).end().end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, false);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, false);
                });
    }

    public void logWallBlockWithPost(MultiPartBlockStateBuilder builder, ModelFile postBig, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Big post for connections, typically including angled
                .part().modelFile(postBig).addModel()
                .condition(WallBlock.UP, true).end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, true);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, true);
                });
    }

    private void logWallSidePart(MultiPartBlockStateBuilder builder, ModelFile model, ModelFile modelAlt, Map.Entry<Direction, Property<WallSide>> entry, WallSide height, boolean hasPost) {
        int rotation = (((int) entry.getKey().toYRot()) + 180) % 360;
        builder.part()
                .modelFile(rotation < 180 ? model : modelAlt)
                .rotationY(rotation)
                .addModel()
                .condition(entry.getValue(), height).condition(WallBlock.UP, hasPost);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}