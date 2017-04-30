package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;

import java.util.Random;

public class BlockSlab extends net.minecraft.block.BlockSlab {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockSlab(boolean isDouble) {
        super(isDouble, Material.wood);
        name = "totemTreeSlab";

        if (isDouble) {
            name += "Double";
        } else {
            setCreativeTab(TotemDefender.tab);
        }

        setBlockName(name);
        useNeighborBrightness = true;
        setHardness(4);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        if (world.getBlock(x, y - 1, z) == BlockManager.slab) {
            world.setBlock(x, y - 1, z, BlockManager.doubleSlab);
        } else if (world.getBlock(x, y + 1, z) == BlockManager.slab) {
            world.setBlock(x, y + 1, z, BlockManager.doubleSlab);
        } else {
            super.onBlockPlacedBy(world, x, y, z, entity, itemStack);
        }
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(BlockManager.slab, 2, 0);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public String func_150002_b(int i) {
        return getUnlocalizedName();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(TotemDefender.MODID + ":totemTreePlanks");
    }

    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
        return Item.getItemFromBlock(BlockManager.slab);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }
}
