package org.razordevs.ascended_quark.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.base.proxy.CommonProxy;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.module.ModuleFinder;
import org.violetmoon.zeta.module.ZetaCategory;

import java.util.List;

@Mixin(CommonProxy.class)
public class QuarkCommonProxyMixin {

    @Shadow public static boolean jingleTheBells;

    /**
     * @return
     * @author TunefulTurnip
     * @reason Lazy solution, to change before release
     */
    @ModifyReceiver(method = "start",at = @At(value = "INVOKE", target = "Lorg/violetmoon/zeta/Zeta;loadModules(Ljava/lang/Iterable;Lorg/violetmoon/zeta/module/ModuleFinder;Ljava/lang/Object;)V", remap = false), remap = false)
    public Zeta start(Zeta instance, @Nullable Iterable<ZetaCategory> categories, @Nullable ModuleFinder finder, Object rootPojo) {
        categories = List.of(new ZetaCategory("aether", Blocks.GLOWSTONE.asItem()), new ZetaCategory("automation", Items.REDSTONE), new ZetaCategory("building", Items.BRICKS), new ZetaCategory("management", Items.CHEST), new ZetaCategory("tools", Items.IRON_PICKAXE), new ZetaCategory("tweaks", Items.NAUTILUS_SHELL), new ZetaCategory("world", Items.GRASS_BLOCK), new ZetaCategory("mobs", Items.PIG_SPAWN_EGG), new ZetaCategory("client", Items.ENDER_EYE), new ZetaCategory("experimental", Items.TNT), new ZetaCategory("oddities", Items.CHORUS_FRUIT, Quark.ODDITIES_ID));
        return instance;
    }
}
