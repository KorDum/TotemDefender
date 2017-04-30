package ru.kordum.totemDefender.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import ru.kordum.totemDefender.TotemDefender;

public abstract class BlockLog extends net.minecraft.block.BlockLog {
    @SideOnly(Side.CLIENT)
    protected IIcon sideIcon;

    @SideOnly(Side.CLIENT)
    protected IIcon topIcon;

    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLog(String name) {
        super();
        this.name = name;
        setBlockName(name);
        setBlockTextureName(TotemDefender.MODID + ":" + name + "1");
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        sideIcon = iconRegister.registerIcon(TotemDefender.MODID + ":" + name + "1");
        topIcon = iconRegister.registerIcon(TotemDefender.MODID + ":" + name + "2");
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int side) {
        return sideIcon;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int side) {
        return topIcon;
    }
}
