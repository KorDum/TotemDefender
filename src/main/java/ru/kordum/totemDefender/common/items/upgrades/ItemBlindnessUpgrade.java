package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemBlindnessUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemBlindnessUpgrade(ConfigUpgrade config) {
        super("blindnessUpgrade", BLINDNESS, config);
    }
}
