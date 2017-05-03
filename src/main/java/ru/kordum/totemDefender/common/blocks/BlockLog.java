package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import ru.kordum.totemDefender.TotemDefender;

public class BlockLog extends net.minecraft.block.BlockLog {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLog(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(TotemDefender.tab);
        setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = getDefaultState();
        switch (meta & 12) {
            case 0:
                return state.withProperty(LOG_AXIS, EnumAxis.Y);

            case 4:
                return state.withProperty(LOG_AXIS, EnumAxis.X);

            case 8:
                return state.withProperty(LOG_AXIS, EnumAxis.Z);
        }
        return state.withProperty(LOG_AXIS, EnumAxis.NONE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        switch (SwitchEnumAxis.AXIS_LOOKUP[state.getValue(LOG_AXIS).ordinal()]) {
            case 1:
                i |= 4;
                break;

            case 2:
                i |= 8;
                break;

            case 3:
                i |= 12;
        }
        return i;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    static final class SwitchEnumAxis {
        static final int[] AXIS_LOOKUP = new int[EnumAxis.values().length];

        static {
            try {
                AXIS_LOOKUP[EnumAxis.X.ordinal()] = 1;
            } catch (NoSuchFieldError ignored) {
            }

            try {
                AXIS_LOOKUP[EnumAxis.Z.ordinal()] = 2;
            } catch (NoSuchFieldError ignored) {
            }

            try {
                AXIS_LOOKUP[EnumAxis.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError ignored) {
            }
        }
    }
}
