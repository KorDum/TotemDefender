package ru.kordum.totemDefender.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;

import java.util.Random;

public class BlockLeaves extends net.minecraft.block.BlockLeaves {
    private String name;
    private IIcon icon;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLeaves() {
        super();
        name = "totemTreeLeaves";
        setBlockName(name);
        setBlockTextureName(TotemDefender.MODID + ":" + name);
        field_150121_P = true;
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        icon = iconRegister.registerIcon(TotemDefender.MODID + ":" + name);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public String[] func_150125_e() {
        return new String[0];
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
        return Item.getItemFromBlock(BlockManager.sapling);
    }
}
