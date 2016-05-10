package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemWeaknessUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemWeaknessUpgrade(ConfigUpgrade config) {
        super("weaknessUpgrade", WEAKNESS, config);
    }
}
