package ru.kordum.totemDefender.common.utils;

import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.TotemDefender;

public class ModResources {
    public static final String CATEGORY_GUI = "gui";
    public static final String CATEGORY_BLOCKS = "blocks";

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public static ResourceLocation getResource(String category, String name) {
        return new ResourceLocation(TotemDefender.MODID, "textures/" + category + "/" + name);
    }
}
