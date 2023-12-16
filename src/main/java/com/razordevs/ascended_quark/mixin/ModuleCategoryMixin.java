package com.razordevs.ascended_quark.mixin;

import com.razordevs.ascended_quark.config.AetherModuleCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.base.module.ModuleCategory;

import java.util.ArrayList;
import java.util.Arrays;

//Based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
@Mixin(value = ModuleCategory.class, remap = false)
abstract class ModuleCategoryMixin {


    @Mutable
    @Shadow@Final private static ModuleCategory[] $VALUES;

    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static ModuleCategory newVariant(String internalName, int internalId, String name, Item item) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lvazkii/quark/base/module/ModuleCategory;$VALUES:[Lvazkii/quark/base/module/ModuleCategory;", shift = At.Shift.AFTER))
    private static void addCustomVariant(CallbackInfo ci) {
        var variants = new ArrayList<>(Arrays.asList(ModuleCategory.values()));
        var aether = newVariant("THE_AETHER", variants.get(variants.size() - 1).ordinal() + 1, "the_aether", Blocks.GLOWSTONE.asItem());
        AetherModuleCategory.THE_AETHER = aether;
        variants.add(aether);
        $VALUES = variants.toArray(new ModuleCategory[0]);
    }
}