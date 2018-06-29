package ru.kordum.totemDefender.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

import ru.kordum.totemDefender.block.BlockLeaves;
import ru.kordum.totemDefender.block.BlockLog;
import ru.kordum.totemDefender.handler.BlockRegistry;

public class WorldGenTotemTree extends WorldGenAbstractTree {
    private final IBlockState LOG = BlockRegistry.LOG.getDefaultState()
        .withProperty(BlockLog.VARIANT, BlockLog.EnumType.LOG);
    private final IBlockState LOG_FACE1 = BlockRegistry.LOG.getDefaultState()
        .withProperty(BlockLog.VARIANT, BlockLog.EnumType.FACE_1);
    private final IBlockState LOG_FACE2 = BlockRegistry.LOG.getDefaultState()
        .withProperty(BlockLog.VARIANT, BlockLog.EnumType.FACE_2);
    private final IBlockState LOG_FACE3 = BlockRegistry.LOG.getDefaultState()
        .withProperty(BlockLog.VARIANT, BlockLog.EnumType.FACE_3);

    private static final IBlockState LEAF = BlockRegistry.LEAVES.getDefaultState()
        .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

    public WorldGenTotemTree() {
        super(false);
    }

    public boolean generate(World world, Random rand, BlockPos position) {
        int i = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = i - j;
        int l = 2 + rand.nextInt(2);
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getHeight()) {
            for (int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
                int j1;

                if (i1 - position.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
                for (int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1) {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {
                        if (i1 >= 0 && i1 < world.getHeight()) {
                            IBlockState state = world.getBlockState(blockPos.setPos(k1, i1, l1));
                            if (!state.getBlock().isAir(state, world, blockPos.setPos(k1, i1, l1))
                                && !state.getBlock().isLeaves(state, world, blockPos.setPos(k1, i1, l1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            }

            BlockPos down = position.down();
            IBlockState state = world.getBlockState(down);

            if (state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, (IPlantable) BlockRegistry.SAPLING) && position.getY() < world.getHeight() - i - 1) {
                state.getBlock().onPlantGrow(state, world, down, position);
                int i3 = rand.nextInt(2);
                int j3 = 1;
                int k3 = 0;

                for (int l3 = 0; l3 <= k; ++l3) {
                    int j4 = position.getY() + i - l3;
                    for (int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2) {
                        int j2 = i2 - position.getX();
                        for (int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2) {
                            int l2 = k2 - position.getZ();
                            if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                                BlockPos blockpos = new BlockPos(i2, j4, k2);
                                state = world.getBlockState(blockpos);
                                if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
                                    setBlockAndNotifyAdequately(world, blockpos, LEAF);
                                }
                            }
                        }
                    }

                    if (i3 >= j3) {
                        i3 = k3;
                        k3 = 1;
                        j3++;
                        if (j3 > l) {
                            j3 = l;
                        }
                    } else {
                        i3++;
                    }
                }

                int i4 = rand.nextInt(3);
                for (int k4 = 0; k4 < i - i4; ++k4) {
                    BlockPos upN = position.up(k4);
                    state = world.getBlockState(upN);
                    if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
                        IBlockState log;
                        if (rand.nextDouble() > 0.05) {
                            log = LOG;
                        } else {
                            double faceRand = rand.nextGaussian();
                            if (faceRand > 0.66) {
                                log = LOG_FACE1;
                            } else if (faceRand > 0.33) {
                                log = LOG_FACE2;
                            } else {
                                log = LOG_FACE3;
                            }
                        }
                        setBlockAndNotifyAdequately(world, position.up(k4), log);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
