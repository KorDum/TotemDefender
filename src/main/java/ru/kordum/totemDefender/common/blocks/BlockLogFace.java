package ru.kordum.totemDefender.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;

public abstract class BlockLogFace extends BlockDirectional {
    private String blockLogName;
    private String name;

    @SideOnly(Side.CLIENT)
    private IIcon faceIcon;

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLogFace(String name) {
        super(Material.wood);
        this.name = name;
        blockLogName = "totemTreeLog";
        setBlockName(name);
        setHardness(2.0F);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(TotemDefender.MODID + ":" + blockLogName + "1");
        topIcon = iconRegister.registerIcon(TotemDefender.MODID + ":" + blockLogName + "2");
        faceIcon = iconRegister.registerIcon(TotemDefender.MODID + ":" + name);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        int l = MathHelper.floor_double(entity.rotationYaw * 4.0 / 360.0 + 2.5) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1 || side == 0) {
            return topIcon;
        }
        else if ((meta == 2 && side == 2) ||
            (meta == 3 && side == 5) ||
            (meta == 0 && side == 3) ||
            (meta == 1 && side == 4)) {
            return faceIcon;
        }

        return blockIcon;
    }

    public String getName() {
        return name;
    }
}
