package ru.kordum.totemDefender.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.entity.EntityProjectile;
import ru.kordum.totemDefender.entity.TileEntityDiamondTotem;
import ru.kordum.totemDefender.entity.TileEntityGoldenTotem;
import ru.kordum.totemDefender.entity.TileEntityIronTotem;
import ru.kordum.totemDefender.entity.TileEntityWoodenTotem;
import ru.kordum.totemDefender.render.RenderTotem;

public class BlockRegistry {
    public static Block SAPLING;
    public static Block LEAVES;
    public static Block LOG;
    public static Block LOG_FACE1;
    public static Block LOG_FACE2;
    public static Block LOG_FACE3;
    public static Block PLANKS;
    public static Block STAIRS;
    public static Block SLAB;
    public static Block DOUBLE_SLAB;
    public static Block FENCE;
    public static Block FENCE_GATE;
    public static Block DOOR;

    public static Block WOODEN_TOTEM;
    public static Block IRON_TOTEM;
    public static Block GOLDEN_TOTEM;
    public static Block DIAMOND_TOTEM;

    private static List<Block> blockList;

    public static void init(Config config) {
        blockList = new ArrayList<>();
        SAPLING = prepareBlock(new BlockSapling(), "sapling");
        LEAVES = prepareBlock(new BlockLeaves(), "leaf");
        LOG = prepareBlock(new BlockLog(), "log");
        LOG_FACE1 = prepareBlock(new BlockLogFace(), "log_face1");
        LOG_FACE2 = prepareBlock(new BlockLogFace(), "log_face2");
        LOG_FACE3 = prepareBlock(new BlockLogFace(), "log_face3");
        PLANKS = prepareBlock(new BlockPlanks(), "planks");
        STAIRS = prepareBlock(new BlockStairs(PLANKS.getDefaultState()), "stairs");
        SLAB = prepareBlock(new BlockSlab.Half(), "slab");
        DOUBLE_SLAB = prepareBlock(new BlockSlab.Double(), "double_slab");
        FENCE = prepareBlock(new BlockFence(), "fence");
        FENCE_GATE = prepareBlock(new BlockFenceGate(), "fence_gate");
        DOOR = prepareBlock(new BlockDoor(), "door");

        WOODEN_TOTEM = prepareBlock(new BlockWoodenTotem(config.woodenTotem), "wooden_totem");
        IRON_TOTEM = prepareBlock(new BlockIronTotem(config.ironTotem), "iron_totem");
        GOLDEN_TOTEM = prepareBlock(new BlockGoldTotem(config.goldTotem), "golden_totem");
        DIAMOND_TOTEM = prepareBlock(new BlockDiamondTotem(config.diamondTotem), "diamond_totem");

        Blocks.FIRE.setFireInfo(LEAVES, 30, 60);
        Blocks.FIRE.setFireInfo(PLANKS, 5, 20);
        Blocks.FIRE.setFireInfo(SLAB, 5, 20);
        Blocks.FIRE.setFireInfo(DOUBLE_SLAB, 5, 20);
        Blocks.FIRE.setFireInfo(LOG, 5, 5);
        Blocks.FIRE.setFireInfo(LOG_FACE1, 5, 5);
        Blocks.FIRE.setFireInfo(LOG_FACE2, 5, 5);
        Blocks.FIRE.setFireInfo(LOG_FACE3, 5, 5);
        Blocks.FIRE.setFireInfo(FENCE, 5, 20);
        Blocks.FIRE.setFireInfo(FENCE_GATE, 5, 20);
        Blocks.FIRE.setFireInfo(STAIRS, 5, 20);
        Blocks.FIRE.setFireInfo(SAPLING, 60, 100);
        Blocks.FIRE.setFireInfo(DOOR, 5, 60);
        Blocks.FIRE.setFireInfo(WOODEN_TOTEM, 5, 30);
        Blocks.FIRE.setFireInfo(IRON_TOTEM, 5, 100);
        Blocks.FIRE.setFireInfo(GOLDEN_TOTEM, 5, 150);
        Blocks.FIRE.setFireInfo(DIAMOND_TOTEM, 5, 300);
    }

    public static void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldenTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDiamondTotem.class, new RenderTotem());

        RenderingRegistry.registerEntityRenderingHandler(
            EntityProjectile.class,
            manager -> new RenderSnowball<>(manager, Items.FIRE_CHARGE, Minecraft.getMinecraft().getRenderItem())
        );
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.registerAll(blockList.toArray(new Block[0]));
    }

    private static Block prepareBlock(Block block, String name) {
        blockList.add(block);
        block.setUnlocalizedName(name);
        block.setRegistryName(name);
        block.setCreativeTab(TotemDefender.tab);
        return block;
    }
}
