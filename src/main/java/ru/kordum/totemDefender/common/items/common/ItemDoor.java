package ru.kordum.totemDefender.common.items.common;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;

public class ItemDoor extends net.minecraft.item.ItemDoor {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemDoor() {
        super(BlockManager.door);
        name = "totemTreeDoor";
        setUnlocalizedName(name);
        setCreativeTab(TotemDefender.tab);
        GameRegistry.registerItem(this, getName());
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
