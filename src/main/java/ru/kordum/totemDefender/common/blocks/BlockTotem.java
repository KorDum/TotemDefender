package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;

import java.util.Random;

public abstract class BlockTotem extends BlockContainer {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    private String name;

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
        super(Material.wood);
        this.name = name;
        this.level = level;
        setUnlocalizedName(name);
        setBlockBounds(0.25f, 0, 0.25f, 0.75f, 2, 0.75f);
        setHardness(2);
        setStepSound(soundTypeWood);

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
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

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(
                    world,
                    pos.getX() + rx,
                    pos.getY() + ry,
                    pos.getZ() + rz,
                    new ItemStack(item.getItem(), item.stackSize, item.getItemDamage())
                );

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
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

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
    }

    protected BlockState createBlockState() {
        return new BlockState(this, FACING);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public float getDamage() {
        return damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getRadius() {
        return radius;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }
}
