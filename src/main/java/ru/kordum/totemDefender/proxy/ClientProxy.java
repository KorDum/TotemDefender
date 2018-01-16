package ru.kordum.totemDefender.proxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;

import ru.kordum.totemDefender.TotemDefender;

public class ClientProxy extends CommonProxy {
    @Override
    public void init() {
        super.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(TotemDefender.instance, new GuiHandler());
    }
}
