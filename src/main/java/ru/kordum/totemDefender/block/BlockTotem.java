package ru.kordum.totemDefender.block;

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
import ru.kordum.totemDefender.config.ConfigTotem;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.handler.GuiHandler;

public abstract class BlockTotem extends BlockContainer {
    private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25f, 0, 0.25f, 0.75f, 2, 0.75f);

    public static final int LEVEL_WOODEN = 1;
    public static final int LEVEL_IRON = 2;
    public static final int LEVEL_GOLD = 3;
    public static final int LEVEL_DIAMOND = 4;

    private float damage;
    private float attackSpeed;
    private int radius;
    private int level;

    public BlockTotem(int level, ConfigTotem config) {
        super(Material.WOOD);
        this.level = level;
        setHardness(2);
        useNeighborBrightness = true;

        attackSpeed = config.getAttackSpeed();
        damage = config.getDamage();
        radius = config.getRadius();
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

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

        if (!world.isRemote) {
            player.openGui(TotemDefender.instance, GuiHandler.BLOCK_TOTEM, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState()
            .withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityTotem tileEntity = (TileEntityTotem) world.getTileEntity(pos);
        IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.getStackInSlot(slot);
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
    
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

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}