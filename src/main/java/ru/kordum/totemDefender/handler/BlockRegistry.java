package ru.kordum.totemDefender.handler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.registries.IForgeRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.block.BlockDoor;
import ru.kordum.totemDefender.block.BlockFence;
import ru.kordum.totemDefender.block.BlockFenceGate;
import ru.kordum.totemDefender.block.BlockLeaves;
import ru.kordum.totemDefender.block.BlockLog;
import ru.kordum.totemDefender.block.BlockLogFace;
import ru.kordum.totemDefender.block.BlockPlanks;
import ru.kordum.totemDefender.block.BlockSapling;
import ru.kordum.totemDefender.block.BlockSlab;
import ru.kordum.totemDefender.block.BlockStairs;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.block.IBlockWithSubTypes;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.config.ConfigTotem;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static Block SAPLING;
    public static Block LEAVES;
    public static Block LOG;
    public static Block PLANKS;
    public static Block STAIRS;
    public static Block SLAB;
    public static Block DOUBLE_SLAB;
    public static Block FENCE;
    public static Block FENCE_GATE;
    public static Block DOOR;
    public static Block TOTEM;

    private static List<Block> blockList;

    public static void init(Config config) {
        blockList = new ArrayList<>();
        SAPLING = prepareBlock(new BlockSapling(config.sapling), "sapling");
        LEAVES = prepareBlock(new BlockLeaves(config.sapling.getDropChance()), "leaf");
        LOG = prepareBlock(new BlockLog(), "log");
        PLANKS = prepareBlock(new BlockPlanks(), "planks");
        STAIRS = prepareBlock(new BlockStairs(PLANKS.getDefaultState()), "stairs");
        SLAB = prepareBlock(new BlockSlab.Half(), "slab");
        DOUBLE_SLAB = prepareBlock(new BlockSlab.Double(), "double_slab");
        FENCE = prepareBlock(new BlockFence(), "fence");
        FENCE_GATE = prepareBlock(new BlockFenceGate(), "fence_gate");
        DOOR = prepareBlock(new BlockDoor(), "door");
        TOTEM = prepareBlock(new BlockTotem(), "totem");

        Blocks.FIRE.setFireInfo(LEAVES, 30, 60);
        Blocks.FIRE.setFireInfo(PLANKS, 5, 20);
        Blocks.FIRE.setFireInfo(SLAB, 5, 20);
        Blocks.FIRE.setFireInfo(DOUBLE_SLAB, 5, 20);
        Blocks.FIRE.setFireInfo(LOG, 5, 5);
        Blocks.FIRE.setFireInfo(FENCE, 5, 20);
        Blocks.FIRE.setFireInfo(FENCE_GATE, 5, 20);
        Blocks.FIRE.setFireInfo(STAIRS, 5, 20);
        Blocks.FIRE.setFireInfo(SAPLING, 60, 100);
        Blocks.FIRE.setFireInfo(DOOR, 5, 60);
        Blocks.FIRE.setFireInfo(TOTEM, 5, 30);

        for (BlockTotem.EnumType type : BlockTotem.EnumType.values()) {
            ConfigTotem configTotem = config.getConfigTotem(type.getName());
            type.setConfig(configTotem);
        }
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.registerAll(blockList.toArray(new Block[0]));
    }

    private static Block prepareBlock(Block block, String name) {
        blockList.add(block);
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        if (!(block instanceof IBlockWithSubTypes)) {
            block.setCreativeTab(TotemDefender.tab);
        }
        return block;
    }

    public static void registerRenders() {
        ((BlockLeaves) LEAVES).setGraphicsLevel(Minecraft.isFancyGraphicsEnabled());
    }
}
