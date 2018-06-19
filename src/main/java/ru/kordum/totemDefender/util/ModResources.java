package ru.kordum.totemDefender.util;

import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.TotemDefender;

public class ModResources {
    public static final String CATEGORY_GUI = "gui";
    public static final String CATEGORY_BLOCKS = "blocks";

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(TotemDefender.MODID, name);
    }

    public static ResourceLocation getResource(String category, String name) {
        return getResource("textures/" + category + "/" + name);
    }
}
