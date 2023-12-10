package com.razordevs.ascended_quark.items;

import com.aetherteam.aether.item.AetherItems;
import com.mojang.datafixers.util.Pair;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.events.AQEvents;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import vazkii.arl.util.ItemNBTHelper;

import javax.annotation.Nonnull;
import java.util.Collection;

public abstract class AQEntityInABucketItem extends Item {
    public static final String TAG_ENTITY_DATA = "slime_nbt";
    public static final String TAG_EXCITED = "excited";

    private final EntityType bucketEntity;

    public AQEntityInABucketItem(EntityType bucketEntity, boolean isSkyroot) {
        super(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).craftRemainder(AetherItems.SKYROOT_BUCKET.get()));
        this.bucketEntity = bucketEntity;
        if (isSkyroot)
            AQEvents.SLIME_WITH_BUCKET_ITEM_SKYROOT.add(new Pair<>(bucketEntity, this));
        else AQEvents.SLIME_WITH_BUCKET_ITEM.add(new Pair<>(bucketEntity, this));
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull Level world, @Nonnull Entity entity, int itemSlot, boolean isSelected) {
        if (world instanceof ServerLevel serverLevel) {
            Vec3 pos = entity.position();
            int x = Mth.floor(pos.x);
            int z = Mth.floor(pos.z);
            boolean slime = isSlimeChunk(serverLevel, x, z);
            boolean excited = ItemNBTHelper.getBoolean(stack, TAG_EXCITED, false);
            if (excited != slime)
                ItemNBTHelper.setBoolean(stack, TAG_EXCITED, slime);
        }
    }

    @Nonnull
    @Override
    public Component getName(@Nonnull ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag cmp = ItemNBTHelper.getCompound(stack, TAG_ENTITY_DATA, false);
            if (cmp != null && cmp.contains("CustomName")) {
                Component custom = Component.Serializer.fromJson(cmp.getString("CustomName"));
                return Component.translatable("item.quark.slime_in_a_bucket.named", custom);
            }
        }

        return super.getName(stack);
    }

    public static boolean isSlimeChunk(ServerLevel world, int x, int z) {
        ChunkPos chunkpos = new ChunkPos(new BlockPos(x, 0, z));
        return WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
    }

    public EntityType getBucketEntity() {
        return bucketEntity;
    }
}
