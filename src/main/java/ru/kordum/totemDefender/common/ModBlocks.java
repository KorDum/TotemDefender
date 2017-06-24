package ru.kordum.totemDefender.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.blocks.BlockDiamondTotem;
import ru.kordum.totemDefender.common.blocks.BlockDoor;
import ru.kordum.totemDefender.common.blocks.BlockFence;
import ru.kordum.totemDefender.common.blocks.BlockFenceGate;
import ru.kordum.totemDefender.common.blocks.BlockGoldTotem;
import ru.kordum.totemDefender.common.blocks.BlockIronTotem;
import ru.kordum.totemDefender.common.blocks.BlockLeaves;
import ru.kordum.totemDefender.common.blocks.BlockLog;
import ru.kordum.totemDefender.common.blocks.BlockLogFace;
import ru.kordum.totemDefender.common.blocks.BlockPlanks;
import ru.kordum.totemDefender.common.blocks.BlockSapling;
import ru.kordum.totemDefender.common.blocks.BlockSlab;
import ru.kordum.totemDefender.common.blocks.BlockStairs;
import ru.kordum.totemDefender.common.blocks.BlockTotem;
import ru.kordum.totemDefender.common.blocks.BlockWoodenTotem;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.entities.TileEntityDiamondTotem;
import ru.kordum.totemDefender.common.entities.TileEntityGoldTotem;
import ru.kordum.totemDefender.common.entities.TileEntityIronTotem;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;

public class ModBlocks {
    public static BlockSapling sapling;
    public static BlockLeaves leaves;
    public static BlockLog log;
    public static BlockLogFace logFace1;
    public static BlockLogFace logFace2;
    public static BlockLogFace logFace3;
    public static BlockPlanks planks;
    public static BlockStairs stairs;
    public static BlockSlab slab;
    public static BlockSlab doubleSlab;
    public static BlockFence fence;
    public static BlockFenceGate fenceGate;
    public static BlockDoor door;

    public static BlockTotem woodenTotem;
    public static BlockTotem ironTotem;
    public static BlockTotem goldTotem;
    public static BlockTotem diamondTotem;

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public static void registerBlocks(Config config) {
        sapling = new BlockSapling("sapling");
        leaves = new BlockLeaves("leaf");
        log = new BlockLog("log");
        logFace1 = new BlockLogFace("log_face1");
        logFace2 = new BlockLogFace("log_face2");
        logFace3 = new BlockLogFace("log_face3");
        planks = new BlockPlanks("planks");
        stairs = new BlockStairs("stairs", planks.getDefaultState());
        slab = new BlockSlab.Half("slab");
        doubleSlab = new BlockSlab.Double("double_slab");
        fence = new BlockFence("fence");
        fenceGate = new BlockFenceGate("fence_gate");
        door = new BlockDoor("door");

        woodenTotem = new BlockWoodenTotem("wooden_totem", config.woodenTotem);
        ironTotem = new BlockIronTotem("iron_totem", config.ironTotem);
        goldTotem = new BlockGoldTotem("gold_totem", config.goldTotem);
        diamondTotem = new BlockDiamondTotem("diamond_totem", config.diamondTotem);

        GameRegistry.register(sapling);
        GameRegistry.register(leaves);
        GameRegistry.register(log);
        GameRegistry.register(logFace1);
        GameRegistry.register(logFace2);
        GameRegistry.register(logFace3);
        GameRegistry.register(planks);
        GameRegistry.register(stairs);
        GameRegistry.register(slab);
        GameRegistry.register(doubleSlab);
        GameRegistry.register(fence);
        GameRegistry.register(fenceGate);
        GameRegistry.register(door);

        GameRegistry.register(woodenTotem);
        GameRegistry.register(ironTotem);
        GameRegistry.register(goldTotem);
        GameRegistry.register(diamondTotem);

        GameRegistry.registerTileEntity(TileEntityWoodenTotem.class, woodenTotem.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityIronTotem.class, ironTotem.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityGoldTotem.class, goldTotem.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityDiamondTotem.class, diamondTotem.getUnlocalizedName());

        Blocks.FIRE.setFireInfo(leaves, 30, 60);
        Blocks.FIRE.setFireInfo(planks, 5, 20);
        Blocks.FIRE.setFireInfo(slab, 5, 20);
        Blocks.FIRE.setFireInfo(doubleSlab, 5, 20);
        Blocks.FIRE.setFireInfo(log, 5, 5);
        Blocks.FIRE.setFireInfo(logFace1, 5, 5);
        Blocks.FIRE.setFireInfo(logFace2, 5, 5);
        Blocks.FIRE.setFireInfo(logFace3, 5, 5);
        Blocks.FIRE.setFireInfo(fence, 5, 20);
        Blocks.FIRE.setFireInfo(fenceGate, 5, 20);
        Blocks.FIRE.setFireInfo(stairs, 5, 20);
        Blocks.FIRE.setFireInfo(sapling, 60, 100);
        Blocks.FIRE.setFireInfo(door, 5, 60);
        Blocks.FIRE.setFireInfo(woodenTotem, 5, 30);
        Blocks.FIRE.setFireInfo(ironTotem, 5, 100);
        Blocks.FIRE.setFireInfo(goldTotem, 5, 150);
        Blocks.FIRE.setFireInfo(diamondTotem, 5, 300);
    }

    public static void registerRenders() {
        registerRender(sapling);
        registerRender(leaves);
        registerRender(planks);
        registerRender(stairs);
        registerRender(log);
        registerRender(logFace1);
        registerRender(logFace2);
        registerRender(logFace3);
        registerRender(slab);
        registerRender(fence);
        registerRender(fenceGate);

        registerRender(woodenTotem);
        registerRender(ironTotem);
        registerRender(goldTotem);
        registerRender(diamondTotem);

        leaves.setGraphicsLevel(Minecraft.isFancyGraphicsEnabled());
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private static void registerRender(Block block) {
        String name = block.getUnlocalizedName().substring(5);
        ModelLoader.setCustomModelResourceLocation(
            Item.getItemFromBlock(block),
            0,
            new ModelResourceLocation(TotemDefender.MODID + ":" + name, "inventory")
        );
    }
}
