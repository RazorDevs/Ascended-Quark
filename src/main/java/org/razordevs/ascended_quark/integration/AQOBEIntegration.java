package org.razordevs.ascended_quark.integration;

import fr.madu59.obe.client.api.registry.RegistryApi;
import fr.madu59.obe.client.util.BackportUtil;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.module.SkyrootQuarkBlocksModule;
import org.violetmoon.quark.content.building.client.render.be.VariantChestRenderer;
import org.violetmoon.quark.content.building.module.VariantChestsModule;

public class AQOBEIntegration {
	public static void init() {
		RegistryApi.registerBlockEntityType(SkyrootQuarkBlocksModule.aqChestTEType, "chest");
		RegistryApi.registerBlockEntityType(SkyrootQuarkBlocksModule.aqTrappedChestTEType, "chest");

		RegistryApi.registerMaterialProvider(SkyrootQuarkBlocksModule.aqChestTEType,
				AQOBEIntegration::getVariantChestMaterial);
		RegistryApi.registerMaterialProvider(SkyrootQuarkBlocksModule.aqTrappedChestTEType,
				AQOBEIntegration::getVariantChestMaterial);
	}

	public static ResourceLocation getVariantChestMaterial(BlockState state) {
		Block block = state.getBlock();
		ChestType chestType = BackportUtil.getValueOrElse(state, ChestBlock.TYPE, ChestType.SINGLE);
		boolean isTrap = block instanceof TrappedChestBlock;

		if (!(block instanceof VariantChestsModule.IVariantChest v))
			return null;

		StringBuilder tex = new StringBuilder(v.getTextureFolder()).append('/').append(v.getTexturePath()).append('/');
		if (isTrap)
			tex.append(VariantChestRenderer.choose(chestType, "trap", "trap_left", "trap_right"));
		else
			tex.append(VariantChestRenderer.choose(chestType, "normal", "left", "right"));
		return new Material(Sheets.CHEST_SHEET, AscendedQuark.asResource(tex.toString())).texture();
	}
}
