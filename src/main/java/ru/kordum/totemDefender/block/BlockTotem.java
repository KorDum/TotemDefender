package ru.kordum.totemDefender.block;

import com.google.common.base.Predicate;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.handler.GuiHandler;

import javax.annotation.Nullable;

public class BlockTotem extends BlockContainer {
    private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class, new Predicate<EnumType>() {
        public boolean apply(@Nullable EnumType type) {
            return true;
        }
    });
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25f, 0, 0.25f, 0.75f, 2, 0.75f);

    public BlockTotem() {
        super(Material.WOOD);
        setHardness(2);
        useNeighborBrightness = true;

        IBlockState state = blockState.getBaseState().withProperty(VARIANT, EnumType.WOODEN).withProperty(FACING, EnumFacing.NORTH);
        setDefaultState(state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.isSneaking()) {
            return false;
        }

        TileEntity target = world.getTileEntity(pos);
        if (target == null) {
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
            .withProperty(FACING, placer.getHorizontalFacing().getOpposite())
            .withProperty(VARIANT, EnumType.WOODEN);
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
        return getDefaultState()
            .withProperty(FACING, EnumFacing.getHorizontal(meta))
            .withProperty(VARIANT, EnumType.WOODEN);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, VARIANT);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        EnumType type = EnumType.byMeta(meta);
        TileEntityTotem tileEntity = new TileEntityTotem(type);
        tileEntity.updateState(this);
        return tileEntity;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public enum EnumType implements IStringSerializable {
        WOODEN("wooden", EnumType.LEVEL_1, 1, 1),
        IRON("iron", EnumType.LEVEL_2, 2, 2),
        GOLDEN("golden", EnumType.LEVEL_3, 3, 3),
        DIAMOND("diamond", EnumType.LEVEL_4, 4, 4);

        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;
        public static final int LEVEL_3 = 3;
        public static final int LEVEL_4 = 4;

        private String name;
        private int level;
        private int filterSlots;
        private int upgradeSlots;
        private float damage;
        private float attackSpeed;
        private int radius;

        EnumType(String name, int level, int filterSlots, int upgradeSlots) {
            this.name = name;
            this.level = level;
            this.filterSlots = filterSlots;
            this.upgradeSlots = upgradeSlots;
        }

        public static EnumType byMeta(int meta) {
            for (EnumType type : EnumType.values()) {
                if (type.ordinal() == meta) {
                    return type;
                }
            }
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public int getFilterSlots() {
            return filterSlots;
        }

        public int getUpgradeSlots() {
            return upgradeSlots;
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

        @Override
        public String toString() {
            return name;
        }
    }
}