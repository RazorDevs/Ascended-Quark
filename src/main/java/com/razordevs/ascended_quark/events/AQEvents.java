package com.razordevs.ascended_quark.events;


import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AetherStoolBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.quark.content.building.block.StoolBlock;

@Mod.EventBusSubscriber(modid = AscendedQuarkMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AQEvents {

    @SubscribeEvent
    public void itemUsed(PlayerInteractEvent.RightClickBlock event) {
        if(event.getEntity().isShiftKeyDown() && event.getItemStack().getItem() instanceof BlockItem && event.getFace() == Direction.UP) {
            BlockState state = event.getLevel().getBlockState(event.getPos());
            if(state.getBlock() instanceof AetherStoolBlock stool)
                stool.blockClicked(event.getLevel(), event.getPos());
        }
    }
}
