package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

public class ItemHungryUpgrade extends ItemModifierUpgrade {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemHungryUpgrade(ConfigUpgrade config) {
        super("hungryUpgrade", HUNGRY, config);
    }
}
