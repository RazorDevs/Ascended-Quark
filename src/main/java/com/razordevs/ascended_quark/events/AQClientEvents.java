package com.razordevs.ascended_quark.events;

import com.mojang.datafixers.util.Pair;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQStoolBlock;
import com.razordevs.ascended_quark.items.AQItems;
import com.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import com.razordevs.ascended_quark.particle.AQParticles;
import com.razordevs.ascended_quark.particle.custom.AmbrosiumShardParticle;
import net.minecraft.client.Minecraft;
import com.razordevs.ascended_quark.items.AQEntityInABucketItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.arl.util.ItemNBTHelper;


@Mod.EventBusSubscriber(modid = AscendedQuarkMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AQClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        for (Pair<EntityType, AQEntityInABucketItem> pair : AQEvents.SLIME_WITH_BUCKET_ITEM_SKYROOT) {
            ItemProperties.register(pair.getSecond(), new ResourceLocation("excited"),
                    (stack, world, e, id) -> ItemNBTHelper.getBoolean(stack, AQEntityInABucketItem.TAG_EXCITED, false) ? 1 : 0);
        }

        for (Pair<EntityType, AQEntityInABucketItem> pair : AQEvents.SLIME_WITH_BUCKET_ITEM) {
            ItemProperties.register(pair.getSecond(), new ResourceLocation("excited"),
                    (stack, world, e, id) -> ItemNBTHelper.getBoolean(stack, AQEntityInABucketItem.TAG_EXCITED, false) ? 1 : 0);
        }
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(AQParticles.AMBROSIUM_SHARD_PARTICLE.get(), AmbrosiumShardParticle.Provider::new);
    }
}
