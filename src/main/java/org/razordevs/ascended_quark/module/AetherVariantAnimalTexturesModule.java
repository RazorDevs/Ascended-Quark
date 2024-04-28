package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.entity.monster.Cockatrice;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether.entity.passive.Aerbunny;
import com.aetherteam.aether.entity.passive.Phyg;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import org.razordevs.ascended_quark.AscendedQuark;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.violetmoon.zeta.client.event.load.ZClientSetup;
import org.violetmoon.zeta.config.Config;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZConfigChanged;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

@ZetaLoadModule(category = "aether")
public class AetherVariantAnimalTexturesModule extends ZetaModule {

    private static ListMultimap<Client.VariantTextureType, ResourceLocation> textures;
    private static final int PHYG_COUNT = 2;
    private static final int AERBUNNY_COUNT = 2;
    private static final int ZEPHYR_COUNT = 2;
    private static final int COCKATRICE_COUNT = 1;
    @Config
    public static boolean enablePhyg = true;
    @Config public static boolean enableAerbunny = true;
    @Config public static boolean enableZephyr = true;
    @Config public static boolean enableCockatrice = true;
    private static boolean isEnabled;
    @ZetaLoadModule(clientReplacement = true)
    public static class Client extends AetherVariantAnimalTexturesModule {

        @LoadEvent
        public void clientSetup(ZClientSetup event) {
            if (!enabled)
                return;
            textures = Multimaps.newListMultimap(new EnumMap<>(VariantTextureType.class), ArrayList::new);

            registerTextures(VariantTextureType.PHYG, PHYG_COUNT, new ResourceLocation(Aether.MODID, "textures/entity/mobs/phyg/phyg.png"));
            registerTextures(VariantTextureType.AERBUNNY, AERBUNNY_COUNT, new ResourceLocation(Aether.MODID, "textures/entity/mobs/aerbunny/aerbunny.png"));
            registerTextures(VariantTextureType.ZEPHYR, ZEPHYR_COUNT, new ResourceLocation(Aether.MODID, "textures/entity/mobs/zephyr/zephyr.png"));
            registerTextures(VariantTextureType.ZEPHYR_LAYER, ZEPHYR_COUNT, new ResourceLocation(Aether.MODID, "textures/entity/mobs/zephyr/zephyr_layer.png"));
            registerTextures(VariantTextureType.COCKATRICE, COCKATRICE_COUNT, new ResourceLocation(Aether.MODID, "textures/entity/mobs/cockatrice/cockatrice.png"));

        }

        @LoadEvent
        public void configChanged(ZConfigChanged event) {
            isEnabled = this.enabled;
        }

        @Nullable
        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getPhygTexture(Phyg entity) {
            if (!isEnabled || !enablePhyg)
                return null;
            return getTextureOrShiny(entity, VariantTextureType.PHYG);
        }

        @Nullable
        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getAerbunnyTexture(Aerbunny entity) {
            if (!isEnabled || !enableAerbunny)
                return null;
            return getTextureOrShiny(entity, VariantTextureType.AERBUNNY);
        }

        @Nullable
        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getZephyrTexture(Zephyr entity) {
            if (!isEnabled || !enableZephyr)
                return null;
            return getTextureOrShiny(entity, VariantTextureType.ZEPHYR);
        }

        @Nullable
        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getZephyrLayerTexture(Zephyr entity) {
            if (!isEnabled || !enableZephyr)
                return null;
            return getTextureOrShiny(entity, VariantTextureType.ZEPHYR_LAYER);
        }

        @Nullable
        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getCockatriceTexture(Cockatrice entity) {
            if (!isEnabled || !enableCockatrice || AetherConfig.CLIENT.legacy_models.get())
                return null;
            return getTextureOrShiny(entity, VariantTextureType.COCKATRICE);
        }

        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getTextureOrShiny(Entity e, VariantTextureType type) {
            return getTextureOrShiny(e, type, () -> getRandomTexture(e, type));
        }

        @OnlyIn(Dist.CLIENT)
        public static ResourceLocation getTextureOrShiny(Entity e, VariantTextureType type, Supplier<ResourceLocation> nonShiny) {
            return nonShiny.get();
        }

        @OnlyIn(Dist.CLIENT)
        private static ResourceLocation getRandomTexture(Entity e, VariantTextureType type) {
            List<ResourceLocation> styles = textures.get(type);

            UUID id = e.getUUID();
            long most = id.getMostSignificantBits();
            int choice = Math.abs((int) (most % styles.size()));
            return styles.get(choice);
        }

        @OnlyIn(Dist.CLIENT)
        private static void registerTextures(VariantTextureType type, int count, ResourceLocation vanilla) {
            String name = type.name().toLowerCase(Locale.ROOT);
            for (int i = 1; i < count + 1; i++)
                textures.put(type, new ResourceLocation(AscendedQuark.MODID, String.format("textures/model/entity/variants/%s%d.png", name, i)));

            if (vanilla != null)
                textures.put(type, vanilla);
        }
    }

    public enum VariantTextureType {
        PHYG,
        AERBUNNY,
        ZEPHYR,
        ZEPHYR_LAYER,
        COCKATRICE
    }
}
