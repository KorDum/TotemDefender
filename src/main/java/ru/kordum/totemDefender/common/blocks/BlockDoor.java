package ru.kordum.totemDefender.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.ItemManager;

import java.util.Random;

public class BlockDoor extends net.minecraft.block.BlockDoor {
    private String name;

    @SideOnly(Side.CLIENT)
    private IIcon[] upperIcons;

    @SideOnly(Side.CLIENT)
    private IIcon[] lowerIcons;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockDoor() {
        super(Material.wood);
        name = "blockTotemTreeDoor";
        setBlockName(name);
        setHardness(4);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister itemRegister) {
        upperIcons = new IIcon[2];
        lowerIcons = new IIcon[2];
        upperIcons[0] = itemRegister.registerIcon(TotemDefender.MODID + ":" + name + "Upper");
        lowerIcons[0] = itemRegister.registerIcon(TotemDefender.MODID + ":" + name + "Lower");
        upperIcons[1] = new IconFlipped(upperIcons[0], true, false);
        lowerIcons[1] = new IconFlipped(lowerIcons[0], true, false);
        blockIcon = itemRegister.registerIcon(TotemDefender.MODID + ":totemTreePlanks");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if (side != 1 && side != 0) {
            int meta = func_150012_g(world, x, y, z);
            int halfMeta = meta & 3;
            boolean flag = (meta & 4) != 0;
            boolean flipped;

            if (flag) {
                flipped = (halfMeta == 0 && side == 2) ||
                    (halfMeta == 1 && side == 5) ||
                    (halfMeta == 2 && side == 3) ||
                    (halfMeta == 3 && side == 4);
            } else {
                flipped = (halfMeta == 0 && side == 5) ||
                    (halfMeta == 1 && side == 3) ||
                    (halfMeta == 2 && side == 4) ||
                    (halfMeta == 3 && side == 2);

                if ((meta & 16) != 0) {
                    flipped = !flipped;
                }
            }

            return ((meta & 8) != 0) ?
                upperIcons[flipped ? 1 : 0] :
                lowerIcons[flipped ? 1 : 0];
        }

        return lowerIcons[0];
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
    public IIcon getIcon(int side, int meta) {
        return blockIcon;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
        return ItemManager.door;
    }
}
