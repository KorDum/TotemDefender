package ru.kordum.totemDefender.common.generators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenTotemTree extends WorldGenAbstractTree {
    /*private static final IBlockState TRUNK = Blocks.LOG.getDefaultState()
        .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.TOTEM);

    private static final IBlockState LEAF = Blocks.LEAVES.getDefaultState()
        .withProperty(BlockLeaves.VARIANT, BlockPlanks.EnumType.TOTEM)
        .withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));*/

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public WorldGenTotemTree() {
        super(false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = i - j;
        int l = 2 + rand.nextInt(2);
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
            for (int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1) {
                int j1;

                if (i1 - position.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
                for (int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1) {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {
                        if (i1 >= 0 && i1 < worldIn.getHeight()) {
                            IBlockState state = worldIn.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1));

                            if (!state.getBlock().isAir(state, worldIn, blockpos$mutableblockpos.setPos(k1, i1, l1)) && !state.getBlock().isLeaves(state, worldIn, blockpos$mutableblockpos.setPos(k1, i1, l1))) {
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
            IBlockState state = worldIn.getBlockState(down);

            if (state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1) {
                state.getBlock().onPlantGrow(state, worldIn, down, position);
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
                                state = worldIn.getBlockState(blockpos);

                                if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos)) {
//                                    this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                }
                            }
                        }
                    }

                    if (i3 >= j3) {
                        i3 = k3;
                        k3 = 1;
                        ++j3;

                        if (j3 > l) {
                            j3 = l;
                        }
                    } else {
                        ++i3;
                    }
                }

                int i4 = rand.nextInt(3);

                for (int k4 = 0; k4 < i - i4; ++k4) {
                    BlockPos upN = position.up(k4);
                    state = worldIn.getBlockState(upN);

                    if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN)) {
//                        this.setBlockAndNotifyAdequately(worldIn, position.up(k4), TRUNK);
                    }
                }

                return true;
            }
        }
        return false;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    /*@Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int l = rand.nextInt(4) + 6;
        int i1 = 1 + rand.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + rand.nextInt(2);
        boolean flag = true;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (y >= 1 && y + l + 1 <= 256) {
            int i2;
            int l3;

            for (int block1 = y; block1 <= y + 1 + l && flag; block1++) {
                if (block1 - y < i1) {
                    l3 = 0;
                } else {
                    l3 = k1;
                }

                for (i2 = x - l3; i2 <= x + l3 && flag; i2++) {
                    for (int i = z - l3; i <= z + l3 && flag; i++) {
                        if (block1 >= 0 && block1 < 256) {
                            BlockPos checkPos = new BlockPos(i2, block1, i);
                            Block k2 = world.getBlockState(checkPos).getBlock();

                            if (!k2.isAir(world, checkPos) && !k2.isLeaves(world, checkPos)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (flag) {
                BlockPos downPos = pos.down();
                Block blockSoil = world.getBlockState(downPos).getBlock();
                boolean isSoil = blockSoil.canSustainPlant(world, downPos, EnumFacing.UP, BlockManager.sapling);

                if (isSoil && y < 256 - l - 1) {
                    blockSoil.onPlantGrow(world, downPos, pos);
                    l3 = rand.nextInt(2);
                    i2 = 1;
                    byte var23 = 0;

                    for (int i4 = 0; i4 <= j1; ++i4) {
                        int yOffset = y + l - i4;

                        for (int block2 = x - l3; block2 <= x + l3; ++block2) {
                            int i3 = block2 - x;

                            for (int j3 = z - l3; j3 <= z + l3; j3++) {
                                int k3 = j3 - z;
                                BlockPos replaceablePos = new BlockPos(block2, yOffset, j3);

                                if (Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0 &&
                                    world.getBlockState(replaceablePos).getBlock().canBeReplacedByLeaves(world, replaceablePos)) {
                                    func_175905_a(world, replaceablePos, BlockManager.leaves, 0);
                                }
                            }
                        }

                        if (l3 >= i2) {
                            l3 = var23;
                            var23 = 1;
                            i2++;

                            if (i2 > k1) {
                                i2 = k1;
                            }
                        } else {
                            l3++;
                        }
                    }

                    int i4 = rand.nextInt(3);

                    for (int yOffset = 0; yOffset < l - i4; yOffset++) {
                        BlockPos yOffsetPos = pos.up(yOffset);
                        Block var25 = world.getBlockState(yOffsetPos).getBlock();

                        if (var25.isAir(world, yOffsetPos) || var25.isLeaves(world, yOffsetPos)) {
                            Block log;
                            int meta = 0;

                            if (rand.nextDouble() > 0.05) {
                                log = BlockManager.log;
                            } else {
                                double faceRand = rand.nextGaussian();
                                meta = rand.nextInt(4);

                                if (faceRand > 0.66) {
                                    log = BlockManager.logFace1;
                                } else if (faceRand > 0.33) {
                                    log = BlockManager.logFace2;
                                } else {
                                    log = BlockManager.logFace3;
                                }
                            }

                            func_175905_a(world, pos.up(yOffset), log, meta);
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }*/
}
