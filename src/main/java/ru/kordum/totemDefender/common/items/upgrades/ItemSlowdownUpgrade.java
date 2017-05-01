package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemSlowdownUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemSlowdownUpgrade(ConfigUpgrade config) {
        super("slowdownUpgrade", SLOWDOWN, config);
    }
}
