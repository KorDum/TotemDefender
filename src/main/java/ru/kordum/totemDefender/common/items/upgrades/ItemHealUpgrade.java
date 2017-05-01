package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemHealUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemHealUpgrade(ConfigUpgrade config) {
        super("healUpgrade", HEAL, config);
    }
}
