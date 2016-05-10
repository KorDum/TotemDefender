package ru.kordum.totemDefender.common;

import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.TotemDefender;

public class ResourceManager {
    public static final String CATEGORY_GUI = "gui";
    public static final String CATEGORY_BLOCKS = "blocks";

    private static ResourceLocation guiTotem;

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public static ResourceLocation getResource(String category, String name) {
        return new ResourceLocation(TotemDefender.MODID.toLowerCase(), "textures/" + category + "/" + name);
    }

    public static ResourceLocation getResourceWithExt(String category, String name) {
        return getResource(category, name + ".png");
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public static ResourceLocation getGuiTotem() {
        if (guiTotem == null) {
            guiTotem = getResourceWithExt(CATEGORY_GUI, "guiTotem");
        }
        return guiTotem;
    }
}
