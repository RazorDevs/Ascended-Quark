package org.razordevs.ascended_quark.datagen.normal.loot;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import org.razordevs.ascended_quark.AscendedQuark;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AQLoot {

	private static final Set<ResourceKey<LootTable>> LOOT_TABLES = new HashSet<>();
	public static final Set<ResourceKey<LootTable>> IMMUTABLE_LOOT_TABLES = Collections.unmodifiableSet(LOOT_TABLES);

	// private static ResourceKey<LootTable> register(String id) {
	// return register(, id);
	// }
	private static ResourceKey<LootTable> register(ResourceKey<LootTable> id) {
		if (LOOT_TABLES.add(id)) {
			return id;
		} else {
			throw new IllegalArgumentException(id + " is already a registered built-in loot table");
		}
	}
}
