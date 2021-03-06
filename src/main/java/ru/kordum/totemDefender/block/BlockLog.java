package ru.kordum.totemDefender.block;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class BlockLog extends net.minecraft.block.BlockLog implements IBlockWithSubTypes {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockLog() {
        super();
        IBlockState state = blockState.getBaseState()
            .withProperty(LOG_AXIS, EnumAxis.Y)
            .withProperty(VARIANT, EnumType.LOG);
        setDefaultState(state);
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS, VARIANT);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = getDefaultState().withProperty(VARIANT, EnumType.byMeta(meta));
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
        EnumType type = state.getValue(VARIANT);
        int meta = type.getMeta();
        switch (SwitchEnumAxis.AXIS_LOOKUP[state.getValue(LOG_AXIS).ordinal()]) {
            case 1:
                meta |= 4;
                break;

            case 2:
                meta |= 8;
                break;

            case 3:
                meta |= 12;
                break;
        }
        return meta;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

    static final class SwitchEnumAxis {
        static final int[] AXIS_LOOKUP = new int[EnumAxis.values().length];

        static {
            try {
                AXIS_LOOKUP[EnumAxis.X.ordinal()] = 1;
            } catch (NoSuchFieldError ex) {
                // ignored
            }

            try {
                AXIS_LOOKUP[EnumAxis.Z.ordinal()] = 2;
            } catch (NoSuchFieldError ex) {
                // ignored
            }

            try {
                AXIS_LOOKUP[EnumAxis.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError ex) {
                // ignored
            }
        }
    }

    public enum EnumType implements IStringSerializable {
        LOG,
        FACE_1,
        FACE_2,
        FACE_3;

        private String name;

        EnumType() {
            name = name().toLowerCase();
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

        public int getMeta() {
            return ordinal();
        }

        @Nonnull
        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
