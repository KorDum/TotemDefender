package ru.kordum.totemDefender.common.items.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;
import ru.kordum.totemDefender.common.blocks.BlockDoor;

public class ItemDoor extends net.minecraft.item.ItemDoor {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemDoor() {
        super(Material.wood);
        name = "totemTreeDoor";
        setUnlocalizedName(name);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE HANDLERS
    //
    //---------------------------------------------------------------------------

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int meta, float p_onItemUse_8_, float p_onItemUse_9_, float p_onItemUse_10_) {
        if (meta != 1) {
            return false;
        }

        y++;
        BlockDoor door = BlockManager.door;

        if (player.canPlayerEdit(x, y, z, meta, itemStack) && player.canPlayerEdit(x, y + 1, z, meta, itemStack)) {
            if (!door.canPlaceBlockAt(world, x, y, z)) {
                return false;
            }
            else {
                int direction = MathHelper.floor_double((player.rotationYaw + 180) * 4 / 360 - 0.5) & 3;
                placeDoorBlock(world, x, y, z, direction, door);
                itemStack.stackSize--;
                return true;
            }
        }

        return false;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(TotemDefender.MODID + ":totemTreeDoor");
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return itemIcon;
    }

    public String getName() {
        return name;
    }
}
