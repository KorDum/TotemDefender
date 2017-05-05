package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.handlers.GuiHandler;

import javax.annotation.Nullable;

public abstract class BlockTotem extends BlockContainer {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25f, 0, 0.25f, 0.75f, 2, 0.75f);

    public static final int LEVEL_WOODEN = 1;
    public static final int LEVEL_IRON = 2;
    public static final int LEVEL_GOLD = 3;
    public static final int LEVEL_DIAMOND = 4;

    private float damage;
    private float attackSpeed;
    private int radius;
    private int level;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockTotem(String name, int level, ConfigTotem config) {
        super(Material.WOOD);
        this.level = level;
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(2);
        useNeighborBrightness = true;

        attackSpeed = config.getAttackSpeed();
        damage = config.getDamage();
        radius = config.getRadius();
        setCreativeTab(TotemDefender.tab);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity target = world.getTileEntity(pos);
        if (target == null || player.isSneaking()) {
            return false;
        }

        TileEntityTotem tileEntity = (TileEntityTotem) target;
        if (!tileEntity.hasOwner()) {
            tileEntity.setOwner(player.getUniqueID());
        }

        if (!world.isRemote) {
            player.openGui(TotemDefender.instance, GuiHandler.BLOCK_TOTEM, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
        return getDefaultState()
            .withProperty(FACING, getDirectionFromEntityLiving(pos, placer));
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityTotem tileEntity = (TileEntityTotem) world.getTileEntity(pos);
        IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.getStackInSlot(slot);
            if (stack != null) {
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
        super.breakBlock(world, pos, state);
    }

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    public static EnumFacing getDirectionFromEntityLiving(BlockPos block, EntityLivingBase placer) {
        if (Math.abs(placer.posX - (double)((float)block.getX() + 0.5F)) < 2.0D && Math.abs(placer.posZ - (double)((float)block.getZ() + 0.5F)) < 2.0D) {
            double d0 = placer.posY + (double)placer.getEyeHeight();
            if (d0 - (double)block.getY() > 2.0D) {
                return EnumFacing.UP;
            }
            if ((double)block.getY() - d0 > 0.0D) {
                return EnumFacing.DOWN;
            }
        }
        return placer.getHorizontalFacing().getOpposite();
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    public float getDamage() {
        return damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getRadius() {
        return radius;
    }

    public int getLevel() {
        return level;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
}