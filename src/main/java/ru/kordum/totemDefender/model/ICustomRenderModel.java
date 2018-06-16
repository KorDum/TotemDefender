package ru.kordum.totemDefender.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomRenderModel {
    @SideOnly(Side.CLIENT)
    void registerRender();
}
