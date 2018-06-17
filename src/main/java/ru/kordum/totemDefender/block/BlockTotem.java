package ru.kordum.totemDefender.block;

import net.minecraft.block.Block;
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
import net.minecraft.util.EnumBlockRenderType;
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
import ru.kordum.totemDefender.config.ConfigTotem;
import ru.kordum.totemDefender.entity.TileEntityDiamondTotem;
import ru.kordum.totemDefender.entity.TileEntityGoldenTotem;
import ru.kordum.totemDefender.entity.TileEntityIronTotem;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.entity.TileEntityWoodenTotem;
import ru.kordum.totemDefender.handler.GuiHandler;

import javax.annotation.Nullable;

public class BlockTotem extends Block {
    private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25f, 0, 0.25f, 0.75f, 2, 0.75f);

    public BlockTotem() {
        super(Material.WOOD);
        setHardness(2);
        useNeighborBrightness = true;

        IBlockState state = blockState.getBaseState()
            .withProperty(VARIANT, EnumType.WOODEN)
            .withProperty(FACING, EnumFacing.NORTH);
        setDefaultState(state);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState()
            .withProperty(FACING, placer.getHorizontalFacing().getOpposite())
            .withProperty(VARIANT, EnumType.byMeta(meta));
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
        int side = (meta & 12) / 4;
        return getDefaultState()
            .withProperty(FACING, EnumFacing.getHorizontal(side))
            .withProperty(VARIANT, EnumType.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumType type = state.getValue(VARIANT);
        int i = type.ordinal();
        switch (state.getValue(FACING)) {
            case EAST:
                i |= 4;
                break;

            case SOUTH:
                i |= 8;
                break;

            case WEST:
                i |= 12;
                break;
        }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, VARIANT);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        EnumType type = state.getValue(VARIANT);
        switch (type) {
            case WOODEN:
                return new TileEntityWoodenTotem();

            case IRON:
                return new TileEntityIronTotem();

            case GOLDEN:
                return new TileEntityGoldenTotem();
        }
        return new TileEntityDiamondTotem();
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
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    public enum EnumType implements IStringSerializable {
        WOODEN(EnumType.LEVEL_1, 1, 1),
        IRON(EnumType.LEVEL_2, 2, 2),
        GOLDEN(EnumType.LEVEL_3, 3, 3),
        DIAMOND(EnumType.LEVEL_4, 4, 4);

        public static final int LEVEL_1 = 1;
        public static final int LEVEL_2 = 2;
        public static final int LEVEL_3 = 3;
        public static final int LEVEL_4 = 4;

        private String name;
        private int level;
        private int filterSlots;
        private int upgradeSlots;
        private ConfigTotem config;

        EnumType(int level, int filterSlots, int upgradeSlots) {
            name = name().toLowerCase();
            this.level = level;
            this.filterSlots = filterSlots;
            this.upgradeSlots = upgradeSlots;
        }

        public static EnumType byMeta(int meta) {
            meta &= 3;
            for (EnumType type : values()) {
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
            return config.getDamage();
        }

        public float getAttackSpeed() {
            return config.getAttackSpeed();
        }

        public int getRadius() {
            return config.getRadius();
        }

        @Override
        public String toString() {
            return name;
        }

        public void setConfig(ConfigTotem config) {
            this.config = config;
        }
    }
}