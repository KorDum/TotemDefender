package ru.kordum.totemDefender.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.common.blocks.BlockDoor;
import ru.kordum.totemDefender.common.blocks.BlockFence;
import ru.kordum.totemDefender.common.blocks.BlockFenceGate;
import ru.kordum.totemDefender.common.blocks.BlockPlanks;
import ru.kordum.totemDefender.common.blocks.BlockSlab;
import ru.kordum.totemDefender.common.blocks.BlockStairs;
import ru.kordum.totemDefender.common.config.Config;

public class BlockManager {
//    public static BlockSapling sapling;
//    public static BlockLeaves leaves;
//    public static BlockLog log;
//    public static BlockLogFace1 face1Log;
//    public static BlockLogFace2 face2Log;
//    public static BlockLogFace3 face3Log;
    public static BlockPlanks planks;
    public static BlockStairs stairs;
    public static BlockSlab slab;
    public static BlockSlab doubleSlab;
    public static BlockFence fence;
    public static BlockFenceGate fenceGate;
    public static BlockDoor door;

//    public static BlockWoodenTotem woodenTotem;
//    public static BlockIronTotem ironTotem;
//    public static BlockGoldTotem goldTotem;
//    public static BlockDiamondTotem diamondTotem;

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public static void registerBlocks(Config config) {
//        sapling = new BlockSapling();
//        leaves = new BlockLeaves();
//        log = new BlockLog();
//        face1Log = new BlockLogFace1();
//        face2Log = new BlockLogFace2();
//        face3Log = new BlockLogFace3();
        planks = new BlockPlanks("totem_tree_planks");
        stairs = new BlockStairs("totem_tree_stairs", planks.getDefaultState());
        slab = new BlockSlab.Half("totem_tree_slab");
        doubleSlab = new BlockSlab.Double("totem_tree_double_slab");
        fence = new BlockFence("totem_tree_fence");
        fenceGate = new BlockFenceGate("totem_tree_fence_gate");
        door = new BlockDoor("totem_tree_door");

//        woodenTotem = new BlockWoodenTotem(config.woodenTotem);
//        ironTotem = new BlockIronTotem(config.ironTotem);
//        goldTotem = new BlockGoldTotem(config.goldTotem);
//        diamondTotem = new BlockDiamondTotem(config.diamondTotem);

//        GameRegistry.register(sapling);
//        GameRegistry.register(leaves);
//        GameRegistry.register(log);
//        GameRegistry.register(face1Log);
//        GameRegistry.register(face2Log);
//        GameRegistry.register(face3Log);
        GameRegistry.register(planks);
        GameRegistry.register(stairs);
        GameRegistry.register(slab);
        GameRegistry.register(doubleSlab);
        GameRegistry.register(fence);
        GameRegistry.register(fenceGate);
        GameRegistry.register(door);

//        GameRegistry.registerBlock(woodenTotem, ItemTotem.class, woodenTotem.getName());
//        GameRegistry.registerTileEntity(TileEntityWoodenTotem.class, woodenTotem.getUnlocalizedName());

//        GameRegistry.registerBlock(ironTotem, ItemTotem.class, ironTotem.getName());
//        GameRegistry.registerTileEntity(TileEntityIronTotem.class, ironTotem.getUnlocalizedName());

//        GameRegistry.registerBlock(goldTotem, ItemTotem.class, goldTotem.getName());
//        GameRegistry.registerTileEntity(TileEntityGoldTotem.class, goldTotem.getUnlocalizedName());

//        GameRegistry.registerBlock(diamondTotem, ItemTotem.class, diamondTotem.getName());
//        GameRegistry.registerTileEntity(TileEntityDiamondTotem.class, diamondTotem.getUnlocalizedName());

//        Blocks.FIRE.setFireInfo(leaves, 30, 60);
        Blocks.FIRE.setFireInfo(planks, 5, 20);
        Blocks.FIRE.setFireInfo(slab, 5, 20);
        Blocks.FIRE.setFireInfo(doubleSlab, 5, 20);
//        Blocks.FIRE.setFireInfo(log, 5, 5);
//        Blocks.FIRE.setFireInfo(face1Log, 5, 5);
//        Blocks.FIRE.setFireInfo(face2Log, 5, 5);
//        Blocks.FIRE.setFireInfo(face3Log, 5, 5);
        Blocks.FIRE.setFireInfo(fence, 5, 20);
        Blocks.FIRE.setFireInfo(fenceGate, 5, 20);
        Blocks.FIRE.setFireInfo(stairs, 5, 20);
//        Blocks.FIRE.setFireInfo(sapling, 60, 100);
        Blocks.FIRE.setFireInfo(door, 5, 60);
//        Blocks.FIRE.setFireInfo(woodenTotem, 5, 30);
//        Blocks.FIRE.setFireInfo(ironTotem, 5, 100);
//        Blocks.FIRE.setFireInfo(goldTotem, 5, 150);
//        Blocks.FIRE.setFireInfo(diamondTotem, 5, 300);
    }

    public static void registerRenders() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
//        registerRender(mesher, sapling);
//        registerRender(mesher, leaves);
        registerRender(mesher, planks);
        registerRender(mesher, stairs);
//        registerRender(mesher, log);
//        registerRender(mesher, face1Log);
//        registerRender(mesher, face2Log);
//        registerRender(mesher, face3Log);
        registerRender(mesher, door);
        registerRender(mesher, slab);
        registerRender(mesher, doubleSlab);
        registerRender(mesher, fence);
        registerRender(mesher, fenceGate);

//        registerRender(mesher, woodenTotem);
//        registerRender(mesher, ironTotem);
//        registerRender(mesher, goldTotem);
//        registerRender(mesher, diamondTotem);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private static void registerRender(ItemModelMesher mesher, Block block) {
        mesher.register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
