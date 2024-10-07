package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.particle.AetherParticleTypes;
import com.aetherteam.aether.item.AetherCreativeTabs;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.razordevs.ascended_quark.blocks.AQTrappedVariantChestBlock;
import org.razordevs.ascended_quark.blocks.AQVariantChestBlock;
import org.razordevs.ascended_quark.entity.block.AQVariantChestBlockEntity;
import org.razordevs.ascended_quark.entity.block.AQVariantTrappedChestBlockEntity;
import org.razordevs.ascended_quark.entity.render.AQVariantChestRenderer;
import org.razordevs.ascended_quark.proxy.AQClient;
import org.razordevs.ascended_quark.util.WoodSetContext;
import org.violetmoon.quark.base.util.BlockPropertyUtil;
import org.violetmoon.quark.mixin.mixins.accessor.AccessorAbstractChestedHorse;
import org.violetmoon.zeta.client.SimpleWithoutLevelRenderer;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.bus.PlayEvent;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.event.play.entity.ZEntityJoinLevel;
import org.violetmoon.zeta.event.play.entity.living.ZLivingDeath;
import org.violetmoon.zeta.event.play.entity.player.ZPlayerInteract;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.*;
import java.util.function.BooleanSupplier;

@ZetaLoadModule(category = "aether", description = "Enables skyroot woodset blocks for quark blocks, such as skyroot post. Disable if another mod already adds compat blocks. ", antiOverlap = "everycomp")
public class SkyrootQuarkBlocksModule extends ZetaModule {

    protected static final List<Block> aqRegularChests = new ArrayList();
    protected static final List<Block> aqTrappedChests = new ArrayList();
    public static BlockEntityType<AQVariantChestBlockEntity> aqChestTEType;
    public static BlockEntityType<AQVariantTrappedChestBlockEntity> aqTrappedChestTEType;

    private static final ThreadLocal<ItemStack> WAIT_TO_REPLACE_CHEST = new ThreadLocal<>();

    @LoadEvent
    public void register(ZRegister register) {
        RegistryUtil.registerWoodsetExtension("skyroot", this, new WoodSetContext(AetherBlocks.SKYROOT_SLAB, AetherBlocks.SKYROOT_PLANKS, AetherBlocks.SKYROOT_FENCE, AetherBlocks.SKYROOT_LOG, AetherBlocks.SKYROOT_LEAVES));
        RegistryUtil.createLeafCarpetParticle("golden_oak_leaf_carpet", this, AetherBlocks.GOLDEN_OAK_LEAVES, AetherParticleTypes.GOLDEN_OAK_LEAVES);
        RegistryUtil.createLeafCarpetParticle("crystal_leaf_carpet", this, AetherBlocks.CRYSTAL_LEAVES, AetherParticleTypes.CRYSTAL_LEAVES);
        RegistryUtil.createLeafCarpetParticle("crystal_fruit_leaf_carpet", this, AetherBlocks.CRYSTAL_FRUIT_LEAVES, AetherParticleTypes.CRYSTAL_LEAVES);
        RegistryUtil.createLeafCarpetParticle("holiday_leaf_carpet", this, AetherBlocks.HOLIDAY_LEAVES, AetherParticleTypes.HOLIDAY_LEAVES);
        RegistryUtil.createLeafCarpetParticle("decorated_holiday_leaf_carpet", this, AetherBlocks.DECORATED_HOLIDAY_LEAVES, AetherParticleTypes.HOLIDAY_LEAVES);
        RegistryUtil.createHedge("golden_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE);
        RegistryUtil.createHedge("crystal_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE);
        RegistryUtil.createHedge("crystal_fruit_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE);
        RegistryUtil.createHedge("holiday_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE);
        RegistryUtil.createHedge("decorated_holiday_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE);
    }

    public static void makeChestBlocks(ZetaModule module, String name, Block base, @Nullable SoundType sound, BooleanSupplier condition) {
        BlockBehaviour.Properties props = BlockPropertyUtil.copyPropertySafe(base);
        if (sound != null) {
            props = props.sound(sound);
        }

        AQVariantChestBlock regularChest = (AQVariantChestBlock) (new AQVariantChestBlock(name, module, () -> aqChestTEType, props)).setCondition(condition);
        aqRegularChests.add(regularChest);

        AQTrappedVariantChestBlock trappedChest = (AQTrappedVariantChestBlock) (new AQTrappedVariantChestBlock(name, module, () -> aqTrappedChestTEType, props)).setCondition(condition);
        aqTrappedChests.add(trappedChest);

        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(), regularChest, AetherBlocks.CHEST_MIMIC, module);
        RegistryUtil.addCreativeModeTab(AetherCreativeTabs.AETHER_FUNCTIONAL_BLOCKS.getKey(), trappedChest, AetherBlocks.CHEST_MIMIC, module);

    }

    @LoadEvent
    public void postRegister(ZRegister.Post e) {
        aqChestTEType = BlockEntityType.Builder.of(AQVariantChestBlockEntity::new, aqRegularChests.toArray(new Block[0])).build(null);
        aqTrappedChestTEType = BlockEntityType.Builder.of(AQVariantTrappedChestBlockEntity::new, aqTrappedChests.toArray(new Block[0])).build(null);
        AscendedQuark.ZETA.registry.register(aqChestTEType, "skyroot_chest", Registries.BLOCK_ENTITY_TYPE);
        AscendedQuark.ZETA.registry.register(aqTrappedChestTEType, "skyroot_trapped_chest", Registries.BLOCK_ENTITY_TYPE);
    }

    @PlayEvent
    public void onClickEntity(ZPlayerInteract.EntityInteractSpecific event) {
        Entity target = event.getTarget();
        Player player = event.getEntity();
        ItemStack held = player.getItemInHand(event.getHand());
        if (!held.isEmpty() && target instanceof AbstractChestedHorse horse) {
            if (!horse.hasChest() && held.getItem() != Items.CHEST && held.is(net.minecraftforge.common.Tags.Items.CHESTS_WOODEN)) {
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(player.level().isClientSide));
                if (!target.level().isClientSide) {
                    ItemStack copy = held.copy();
                    copy.setCount(1);
                    held.shrink(1);
                    horse.getPersistentData().put("Quark:DonkChest", copy.serializeNBT());
                    horse.setChest(true);
                    horse.createInventory();
                    ((AccessorAbstractChestedHorse)horse).quark$playChestEquipsSound();
                }
            }
        }

    }

    @PlayEvent
    public void onDeath(ZLivingDeath event) {
        Entity target = event.getEntity();
        if (target instanceof AbstractChestedHorse horse) {
            ItemStack chest = ItemStack.of(horse.getPersistentData().getCompound("Quark:DonkChest"));
            if (!chest.isEmpty() && horse.hasChest()) {
                WAIT_TO_REPLACE_CHEST.set(chest);
            }
        }

    }

    @PlayEvent
    public void onEntityJoinWorld(ZEntityJoinLevel event) {
        Entity target = event.getEntity();
        if (target instanceof ItemEntity item) {
            if (item.getItem().getItem() == Items.CHEST) {
                ItemStack local = WAIT_TO_REPLACE_CHEST.get();
                if (local != null && !local.isEmpty()) {
                    ((ItemEntity)target).setItem(local);
                }

                WAIT_TO_REPLACE_CHEST.remove();
            }
        }

    }

    @ZetaLoadModule(
            clientReplacement = true
    )
    public static class Client extends SkyrootQuarkBlocksModule {
        public Client() {
        }

        @LoadEvent
        public final void clientSetup(ZClientSetup event) {
            BlockEntityRenderers.register(aqChestTEType, (ctx) -> new AQVariantChestRenderer(ctx, false));
            BlockEntityRenderers.register(aqTrappedChestTEType, (ctx) -> new AQVariantChestRenderer(ctx, true));
            Iterator<Block> var2 = aqRegularChests.iterator();

            Block b;
            while(var2.hasNext()) {
                b = var2.next();
                AQClient.ZETA_CLIENT.setBlockEntityWithoutLevelRenderer(b.asItem(), new SimpleWithoutLevelRenderer(aqChestTEType, b.defaultBlockState()));
            }

            var2 = aqTrappedChests.iterator();

            while(var2.hasNext()) {
                b = var2.next();
                AQClient.ZETA_CLIENT.setBlockEntityWithoutLevelRenderer(b.asItem(), new SimpleWithoutLevelRenderer(aqTrappedChestTEType, b.defaultBlockState()));
            }
        }
    }
}
