package com.razordevs.ascended_quark;

import com.google.common.collect.Lists;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.mixin.ModuleFinderAccessor;
import com.razordevs.ascended_quark.mixin.ModuleFinderMixin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModAnnotation;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.apache.commons.lang3.text.WordUtils;
import org.objectweb.asm.Type;
import org.spongepowered.asm.mixin.Unique;
import vazkii.quark.base.Quark;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.ModuleFinder;
import vazkii.quark.base.module.QuarkModule;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Cursed Code
//Mixin didn't like the type class idk
public class ModuleFinderHelper {
    private static final Type LOAD_MODULE_TYPE = Type.getType(LoadModule.class);
    private static final Pattern MODULE_CLASS_PATTERN = Pattern.compile("com.razordevs.ascended_quark.module.\\w+Module");
    Object finder;
    public void start(Object o) {
        this.finder = o;

        ModFileScanData scanData = ModList.get().getModFileById(AscendedQuarkMod.MODID).getFile().getScanResult();

        scanData.getAnnotations().stream()
                .filter(annotationData -> Type.getType(LoadModule.class).equals(annotationData.annotationType()))
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

            Class<?> clazz = Class.forName(name, false, AscendedQuarkMod.class.getClassLoader());
            AscendedQuarkMod.LOGGER.info("Found Ascended Quark module class " + name);

            QuarkModule moduleObj = (QuarkModule) clazz.getDeclaredConstructor().newInstance();

            Map<String, Object> vals = target.annotationData();
            ModuleCategory category = getOrMakeCategory((ModAnnotation.EnumHolder) vals.get("category"));

            //String categoryName = category.name;
            //String packageName = m.group(1);
            //if(!categoryName.equals(packageName))
            //    throw new RuntimeException("Module " + name + " is defined in " + packageName + " but in category " + categoryName);

            if(category.isAddon()) {
                String mod = category.requiredMod;
                if(mod != null && !mod.isEmpty() && !ModList.get().isLoaded(mod))
                    moduleObj.missingDep = true;
            }

            if(vals.containsKey("name"))
                moduleObj.displayName = (String) vals.get("name");
            else
                moduleObj.displayName = WordUtils.capitalizeFully(clazz.getSimpleName().replaceAll("Module$", "").replaceAll("(?<=.)([A-Z])", " $1"));
            moduleObj.lowercaseName = moduleObj.displayName.toLowerCase(Locale.ROOT).replaceAll(" ", "_");

            if(vals.containsKey("description"))
                moduleObj.description = (String) vals.get("description");

            if(vals.containsKey("antiOverlap"))
                moduleObj.antiOverlap = (List<String>) vals.get("antiOverlap");

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

            ((ModuleFinderAccessor) finder).getFoundModules().put((Class<? extends QuarkModule>) clazz, moduleObj);
        } catch(ReflectiveOperationException e) {
            throw new RuntimeException("Failed to load Module " + target, e);
        }
    }

    private ModuleCategory getOrMakeCategory(ModAnnotation.EnumHolder category) {
        return ModuleCategory.valueOf(category.getValue());
    }
}
