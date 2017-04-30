package ru.kordum.totemDefender.common.generators;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;
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
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int l = rand.nextInt(4) + 6;
        int i1 = 1 + rand.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + rand.nextInt(2);
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256) {
            int i2;
            int l3;

            for (int block1 = y; block1 <= y + 1 + l && flag; ++block1) {
                if (block1 - y < i1) {
                    l3 = 0;
                } else {
                    l3 = k1;
                }

                for (i2 = x - l3; i2 <= x + l3 && flag; i2++) {
                    for (int i = z - l3; i <= z + l3 && flag; i++) {
                        if (block1 >= 0 && block1 < 256) {
                            Block k2 = world.getBlock(i2, block1, i);

                            if (!k2.isAir(world, i2, block1, i) && !k2.isLeaves(world, i2, block1, i)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (flag) {
                Block blockSoil = world.getBlock(x, y - 1, z);
                boolean isSoil = blockSoil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, BlockManager.sapling);

                if (isSoil && y < 256 - l - 1) {
                    blockSoil.onPlantGrow(world, x, y - 1, z, x, y, z);
                    l3 = rand.nextInt(2);
                    i2 = 1;
                    byte var23 = 0;

                    for (int i4 = 0; i4 <= j1; ++i4) {
                        int yOffset = y + l - i4;

                        for (int block2 = x - l3; block2 <= x + l3; ++block2) {
                            int i3 = block2 - x;

                            for (int j3 = z - l3; j3 <= z + l3; j3++) {
                                int k3 = j3 - z;
                                if (Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0 &&
                                    world.getBlock(block2, yOffset, j3).canBeReplacedByLeaves(world, block2, yOffset, j3)) {
                                    setBlockAndNotifyAdequately(world, block2, yOffset, j3, BlockManager.leaves, 0);
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

                    for (int yOffset = 0; yOffset < l - i4; ++yOffset) {
                        Block var25 = world.getBlock(x, y + yOffset, z);

                        if (var25.isAir(world, x, y + yOffset, z) || var25.isLeaves(world, x, y + yOffset, z)) {
                            Block log;
                            int meta = 0;

                            if (rand.nextDouble() > 0.05) {
                                log = BlockManager.log;
                            } else {
                                double faceRand = rand.nextGaussian();
                                meta = rand.nextInt(4);

                                if (faceRand > 0.66) {
                                    log = BlockManager.face1Log;
                                } else if (faceRand > 0.33) {
                                    log = BlockManager.face2Log;
                                } else {
                                    log = BlockManager.face3Log;
                                }
                            }

                            setBlockAndNotifyAdequately(world, x, y + yOffset, z, log, meta);
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
