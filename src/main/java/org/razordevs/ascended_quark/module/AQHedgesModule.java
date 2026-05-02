package org.razordevs.ascended_quark.module;

import com.aetherteam.aether.block.AetherBlocks;
import io.github.razordevs.deep_aether.init.DABlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.razordevs.ascended_quark.blocks.AQHedgeBlock;
import org.razordevs.ascended_quark.util.RegistryUtil;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.client.AlikeColorHandler;
import org.violetmoon.zeta.client.event.load.ZAddBlockColorHandlers;
import org.violetmoon.zeta.client.event.load.ZAddItemColorHandlers;
import org.violetmoon.zeta.event.bus.LoadEvent;
import org.violetmoon.zeta.event.load.ZCommonSetup;
import org.violetmoon.zeta.event.load.ZRegister;
import org.violetmoon.zeta.module.ZetaLoadModule;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.VanillaWoods;

import java.util.ArrayList;
import java.util.List;

@ZetaLoadModule(category = "aether")
public class AQHedgesModule extends ZetaModule {
	public static TagKey<Block> hedgesTag;
	public static List<AQHedgeBlock> hedges = new ArrayList<>();

	@LoadEvent
	public final void register(ZRegister event) {
		RegistryUtil.createHedge("golden_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE,
				AetherBlocks.GOLDEN_OAK_LEAVES);
		RegistryUtil.createHedge("crystal_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE,
				AetherBlocks.CRYSTAL_LEAVES);
		RegistryUtil.createHedge("crystal_fruit_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE,
				AetherBlocks.CRYSTAL_FRUIT_LEAVES);
		RegistryUtil.createHedge("holiday_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE,
				AetherBlocks.HOLIDAY_LEAVES);
		RegistryUtil.createHedge("decorated_holiday_skyroot_hedge", this, AetherBlocks.SKYROOT_FENCE,
				AetherBlocks.DECORATED_HOLIDAY_LEAVES);
	}

	@LoadEvent
	public final void setup(ZCommonSetup event) {
		hedgesTag = Quark.asTagKey(Registries.BLOCK, "hedges");
	}

	@ZetaLoadModule(clientReplacement = true)
	public static class Client extends AQHedgesModule {

		@LoadEvent
		public void blockColorProviders(ZAddBlockColorHandlers event) {
			event.registerNamed(zeta(), b -> new AlikeColorHandler((AQHedgeBlock) b, AQHedgeBlock::getLeaf), "hedge");
		}

		@LoadEvent
		public void itemColorProviders(ZAddItemColorHandlers event) {
			event.registerNamed(zeta(), i -> new AlikeColorHandler(i, AQHedgeBlock::getLeaf), "hedge");
		}

	}
}
