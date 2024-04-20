package org.razordevs.ascended_quark.config;

import com.google.common.collect.Lists;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.mixin.ModuleFinderAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModAnnotation;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.apache.commons.lang3.text.WordUtils;
import org.objectweb.asm.Type;
import org.spongepowered.asm.mixin.Unique;
import org.violetmoon.zeta.module.ZetaCategory;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Cursed Code
//Mixin didn't like the Type class idk
public class ModuleFinderHelper {
    private static final Pattern MODULE_CLASS_PATTERN = Pattern.compile("org.razordevs.ascended_quark.module.\\w+Module");
    Object finder;
    public void start(Object o) {
        this.finder = o;

        ModFileScanData scanData = ModList.get().getModFileById(AscendedQuark.MODID).getFile().getScanResult();

        scanData.getAnnotations().stream()
                .filter(annotationData -> Type.getType(LoadModuleButWithoutCategory.class).equals(annotationData.annotationType()))
                .sorted(Comparator.comparing(d -> d.getClass().getName()))
                .forEach(this::loadModule);
    }
    @Unique
    @SuppressWarnings("unchecked")
    private void loadModule(ModFileScanData.AnnotationData target) {
        try {
            Type type = target.clazz();
            String name = type.getClassName();

            Matcher m = MODULE_CLASS_PATTERN.matcher(name);
            if(!m.matches())
                throw new RuntimeException("Invalid module name " + name);

            Class<?> clazz = Class.forName(name, false, AscendedQuark.class.getClassLoader());
            AscendedQuark.LOGGER.info("Found Ascended Quark module class " + name);

            ZetaModule moduleObj = (ZetaModule) clazz.getDeclaredConstructor().newInstance();

            Map<String, Object> vals = target.annotationData();
            ZetaCategory category = getOrMakeCategory();

            if(vals.containsKey("name"))
                moduleObj.displayName = (String) vals.get("name");
            else
                moduleObj.displayName = WordUtils.capitalizeFully(clazz.getSimpleName().replaceAll("Module$", "").replaceAll("(?<=.)([A-Z])", " $1"));
            moduleObj.lowercaseName = moduleObj.displayName.toLowerCase(Locale.ROOT).replaceAll(" ", "_");

            if(vals.containsKey("description"))
                moduleObj.description = (String) vals.get("description");

            if(vals.containsKey("antiOverlap"))
                moduleObj.antiOverlap = (Set<String>) vals.get("antiOverlap");

            if(vals.containsKey("hasSubscriptions"))
                moduleObj.hasSubscriptions = (boolean) vals.get("hasSubscriptions");

            if(vals.containsKey("subscribeOn")) {
                Set<Dist> subscribeTargets = EnumSet.noneOf(Dist.class);

                List<ModAnnotation.EnumHolder> holders = (List<ModAnnotation.EnumHolder>) vals.get("subscribeOn");
                for (ModAnnotation.EnumHolder holder : holders)
                    subscribeTargets.add(Dist.valueOf(holder.getValue()));

                moduleObj.subscriptionTarget = Lists.newArrayList(subscribeTargets);
            }

            if(vals.containsKey("enabledByDefault"))
                moduleObj.enabledByDefault = (Boolean) vals.get("enabledByDefault");

            category.addModule(moduleObj);
            moduleObj.category = category;


            Map<Class<? extends ZetaModule>, ZetaModule> temp = ((ModuleFinderAccessor) finder).getFoundModules();
            temp.put((Class<? extends ZetaModule>) clazz, moduleObj);

            ((ModuleFinderAccessor) finder).setFoundModules(temp);

        } catch(ReflectiveOperationException e) {
            throw new RuntimeException("Failed to load Module " + target, e);
        }
    }

    private ZetaCategory getOrMakeCategory() {
        return ZetaCategory.unknownCategory("THE_AETHER");
    }
}
