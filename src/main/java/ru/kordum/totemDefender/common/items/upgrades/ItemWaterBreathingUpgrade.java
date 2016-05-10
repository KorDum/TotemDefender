package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemWaterBreathingUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemWaterBreathingUpgrade(ConfigUpgrade config) {
        super("waterBreathingUpgrade", WATER_BREATHING, config);
    }
}
