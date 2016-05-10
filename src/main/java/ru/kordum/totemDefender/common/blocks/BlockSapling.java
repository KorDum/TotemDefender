package ru.kordum.totemDefender.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.generators.WorldGenTotemTree;

import java.util.Random;

public class BlockSapling extends BlockBush implements IGrowable {
    private IIcon icon;
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockSapling() {
        float f = 0.4F;
        name = "totemTreeSapling";
        setBlockName(name);
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setStepSound(soundTypeGrass);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);

            if (world.getBlockLightValue(x, y + 1, z) >= 9 && rand.nextInt(100) == 0) {
                generateTree(world, x, y, z, rand);
            }
        }
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private void generateTree(World world, int x, int y, int z, Random rand) {
        if (TerrainGen.saplingGrowTree(world, rand, x, y, z)) {
            WorldGenTotemTree worldGen = new WorldGenTotemTree();
            world.setBlock(x, y, z, Blocks.air, 0, 4);
            worldGen.generate(world, rand, x, y, z);
        }
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister itemRegister) {
        icon = itemRegister.registerIcon(TotemDefender.MODID + ":" + name);
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_) {
        return true;
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return (double) world.rand.nextFloat() < 0.05;
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        generateTree(world, x, y, z, rand);
    }

    public int damageDropped(int p_149692_1_) {
        return MathHelper.clamp_int(p_149692_1_ & 7, 0, 5);
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
        return icon;
    }
}
