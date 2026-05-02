package org.razordevs.ascended_quark;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.razordevs.ascended_quark.datagen.AQData;
import org.razordevs.ascended_quark.datagen.builders.loot.modifiers.AQGlobalLootModifiers;
import org.razordevs.ascended_quark.proxy.ACClientProxy;
import org.razordevs.ascended_quark.proxy.ACCommonProxy;
import org.razordevs.ascended_quark.proxy.AQClient;
import org.violetmoon.zeta.Zeta;
import org.violetmoon.zeta.multiloader.Env;
import org.violetmoon.zetaimplforge.ForgeZeta;

import java.nio.file.Path;
import java.util.Optional;

@Mod(AscendedQuark.MODID)
public class AscendedQuark {

	// TODO: Pickarangs LootTables

	public static final String MODID = "ascended_quark";
	public static final String AETHER = "aether";
	public static final String DEEP_AETHER = "deep_aether";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static AscendedQuark instance;
	public static ACCommonProxy proxy;
	public static final Zeta ZETA = new ForgeZeta(MODID, LogManager.getLogger("aq-zeta"));

	public AscendedQuark(ModContainer mod, IEventBus bus, Dist dist) {
		instance = this;
		ZETA.start();

		proxy = Env.unsafeRunForDist(() -> ACClientProxy::new, () -> ACCommonProxy::new);
		proxy.start();

		bus.addListener(AQData::dataSetup);
		bus.addListener(AQClient::registerParticleFactories);
		bus.addListener(this::addAdditionalResourcesPack);

		AQGlobalLootModifiers.LOOT_MODIFIERS.register(bus);
	}

	public static ResourceLocation asResource(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name);
	}

	public void addAdditionalResourcesPack(AddPackFindersEvent event) {
		if (ModList.get().isLoaded(DEEP_AETHER)) {
			if (event.getPackType() == PackType.CLIENT_RESOURCES) {
				setupCompatPack("deep_aether_compatibility_client", "Deep Aether Compatibility Client", event,
						PackType.CLIENT_RESOURCES, PackSource.BUILT_IN, true);
			} else if (event.getPackType() == PackType.SERVER_DATA) {
				setupCompatPack("deep_aether_compatibility_server", "Deep Aether Compatibility Server", event,
						PackType.SERVER_DATA, PackSource.SERVER, true);
			}
		}
	}

	private static void setupCompatPack(String location, String name, AddPackFindersEvent event, PackType type,
			PackSource source, boolean force) {
		Path resourcePath = ModList.get().getModFileById(AscendedQuark.MODID).getFile()
				.findResource("packs/" + location);
		Pack pack = Pack.readMetaAndCreate(
				new PackLocationInfo("builtin/" + location, Component.literal(name), source, Optional.empty()),
				new PathPackResources.PathResourcesSupplier(resourcePath), type,
				new PackSelectionConfig(force, Pack.Position.TOP, false));
		event.addRepositorySource(consumer -> consumer.accept(pack));
	}
}
