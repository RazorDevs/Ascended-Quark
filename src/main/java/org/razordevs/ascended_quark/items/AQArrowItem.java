package org.razordevs.ascended_quark.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.violetmoon.zeta.util.BooleanSuppliers;

import java.util.function.BooleanSupplier;

public abstract class AQArrowItem extends ArrowItem {
    private BooleanSupplier enabledSupplier;

    public AQArrowItem() {
        super(new Item.Properties());
        this.enabledSupplier = BooleanSuppliers.TRUE;
    }

    public AQArrowItem setCondition(BooleanSupplier enabledSupplier) {
        this.enabledSupplier = enabledSupplier;
        return this;
    }

    public boolean doesConditionApply() {
        return this.enabledSupplier.getAsBoolean();
    }

    public static class Impl extends AQArrowItem {
        private final Impl.ArrowCreator creator;

        public Impl(Impl.ArrowCreator creator) {
            this.creator = creator;
        }

        public AbstractArrow createArrow(Level p_40513_, ItemStack p_40514_, LivingEntity p_40515_) {
            return this.creator.createArrow(p_40513_, p_40514_, p_40515_);
        }

        public interface ArrowCreator {
            AbstractArrow createArrow(Level var1, ItemStack var2, LivingEntity var3);
        }
    }
}
