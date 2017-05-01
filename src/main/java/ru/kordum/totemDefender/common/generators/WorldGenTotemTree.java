package ru.kordum.totemDefender.common.generators;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.kordum.totemDefender.common.BlockManager;

import java.util.Random;

public class WorldGenTotemTree extends WorldGenAbstractTree {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public WorldGenTotemTree() {
        super(false);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
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
                }
                else {
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
                        }
                        else {
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
                        }
                        else {
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
                            }
                            else {
                                double faceRand = rand.nextGaussian();
                                meta = rand.nextInt(4);

                                if (faceRand > 0.66) {
                                    log = BlockManager.face1Log;
                                }
                                else if (faceRand > 0.33) {
                                    log = BlockManager.face2Log;
                                }
                                else {
                                    log = BlockManager.face3Log;
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
    }
}
