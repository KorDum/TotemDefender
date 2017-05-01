package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemWitherUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemWitherUpgrade(ConfigUpgrade config) {
        super("witherUpgrade", WITHER, config);
    }
}
