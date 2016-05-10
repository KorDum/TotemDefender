package ru.kordum.totemDefender.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import ru.kordum.totemDefender.TotemDefender;

public abstract class ItemBase extends Item {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setTextureName(TotemDefender.MODID + ":" + name);
        setCreativeTab(TotemDefender.tab);
        GameRegistry.registerItem(this, name);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }
}
