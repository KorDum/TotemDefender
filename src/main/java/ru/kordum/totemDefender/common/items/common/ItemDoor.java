package ru.kordum.totemDefender.common.items.common;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;

public class ItemDoor extends net.minecraft.item.ItemDoor {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemDoor(String name) {
        super(BlockManager.door);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(TotemDefender.tab);
        GameRegistry.register(this);
    }
}
