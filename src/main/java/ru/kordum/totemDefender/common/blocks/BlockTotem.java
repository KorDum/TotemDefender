package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;

import java.util.Random;

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
        //        setStepSound(soundTypeWood);

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity target = world.getTileEntity(pos);
        if (target == null || player.isSneaking()) {
            return false;
        }

        TileEntityTotem tileEntity = (TileEntityTotem) target;
        if (!tileEntity.hasOwner()) {
            tileEntity.setOwner(player.getUniqueID());
        }

        player.openGui(TotemDefender.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private void dropItems(World world, BlockPos pos) {
        Random rand = new Random();
        TileEntity tileEntity = world.getTileEntity(pos);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);
            if (item != null && item.getCount() > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(
                    world,
                    pos.getX() + rx,
                    pos.getY() + ry,
                    pos.getZ() + rz,
                    new ItemStack(item.getItem(), item.getCount(), item.getItemDamage())
                );

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound(item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntity(entityItem);
                item.setCount(0);
            }
        }
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropItems(world, pos);
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