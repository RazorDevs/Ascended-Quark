package com.razordevs.ascended_quark.events;


import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQStoolBlock;
import com.razordevs.ascended_quark.items.AQItems;
import com.razordevs.ascended_quark.items.AQSlimeInABucketItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import vazkii.arl.util.ItemNBTHelper;
import vazkii.quark.content.tools.item.SlimeInABucketItem;

import java.util.Collection;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = AscendedQuarkMod.MODID)
public class AQClientEvents {

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {

        Collection<RegistryObject<Item>> items = AQItems.ITEMS.getEntries();

        for (RegistryObject<Item> item : items) {
            if(item.get() instanceof AQSlimeInABucketItem slimeBucketItem) {
                event.enqueueWork(() -> ItemProperties.register(slimeBucketItem, new ResourceLocation("excited"),
                        (stack, world, e, id) -> ItemNBTHelper.getBoolean(stack, AQSlimeInABucketItem.TAG_EXCITED, false) ? 1 : 0));
            }
        }
    }
}
