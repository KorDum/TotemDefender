package ru.kordum.totemDefender.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
