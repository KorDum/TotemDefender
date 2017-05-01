package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import ru.kordum.totemDefender.TotemDefender;

public class BlockLog extends net.minecraft.block.BlockLog {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLog() {
        super();
        this.name = "totemTreeLog";
        setUnlocalizedName(name);
        setCreativeTab(TotemDefender.tab);
        setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    protected BlockState createBlockState() {
        return new BlockState(this, LOG_AXIS);
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

            default:
                return state.withProperty(LOG_AXIS, EnumAxis.NONE);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        switch (SwitchEnumAxis.AXIS_LOOKUP[((EnumAxis) state.getValue(LOG_AXIS)).ordinal()]) {
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

    public String getName() {
        return name;
    }

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
