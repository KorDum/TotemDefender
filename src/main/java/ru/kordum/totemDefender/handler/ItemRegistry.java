package ru.kordum.totemDefender.handler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.block.BlockSlab;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.config.ConfigUpgrade;
import ru.kordum.totemDefender.item.ItemCore;
import ru.kordum.totemDefender.item.ItemDoor;
import ru.kordum.totemDefender.item.ItemFilter;
import ru.kordum.totemDefender.item.ItemMode;
import ru.kordum.totemDefender.item.ItemSlab;
import ru.kordum.totemDefender.item.ItemTotem;
import ru.kordum.totemDefender.item.ItemUpgrade;
import ru.kordum.totemDefender.model.ICustomRenderModel;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static Item CORE;
    public static Item DOOR;
    public static Item SLAB;
    public static Item STAIRS;
    public static Item FENCE;
    public static Item FENCE_GATE;
    public static Item PLANKS;
    public static Item LOG;
    public static Item LOG_FACE1;
    public static Item LOG_FACE2;
    public static Item LOG_FACE3;
    public static Item SAPLING;
    public static Item LEAVES;
    public static Item TOTEM;
    public static Item UPGRADE;
    public static Item FILTER;
    public static Item MODE;

    private static List<Item> itemList;

    public static void init(Config config) {
        itemList = new ArrayList<>();
        CORE = prepareItem(new ItemCore(), "core");
        DOOR = prepareItem(new ItemDoor(BlockRegistry.DOOR), "door");
        SLAB = prepareItem(new ItemSlab(BlockRegistry.SLAB, (BlockSlab) BlockRegistry.SLAB, (BlockSlab) BlockRegistry.DOUBLE_SLAB), "slab");
        STAIRS = prepareItem(BlockRegistry.STAIRS);
        FENCE = prepareItem(BlockRegistry.FENCE);
        FENCE_GATE = prepareItem(BlockRegistry.FENCE_GATE);
        PLANKS = prepareItem(BlockRegistry.PLANKS);
        LOG = prepareItem(BlockRegistry.LOG);
        LOG_FACE1 = prepareItem(BlockRegistry.LOG_FACE1);
        LOG_FACE2 = prepareItem(BlockRegistry.LOG_FACE2);
        LOG_FACE3 = prepareItem(BlockRegistry.LOG_FACE3);
        SAPLING = prepareItem(BlockRegistry.SAPLING);
        LEAVES = prepareItem(BlockRegistry.LEAVES);
        TOTEM = prepareItem(new ItemTotem(BlockRegistry.TOTEM));
        UPGRADE = prepareItem(new ItemUpgrade(), "upgrade");
        FILTER = prepareItem(new ItemFilter(), "filter");
        MODE = prepareItem(new ItemMode(), "mode");

        for (ItemUpgrade.EnumType type : ItemUpgrade.EnumType.values()) {
            ConfigUpgrade configUpgrade = config.getConfigUpgrade(type.getName());
            type.setConfig(configUpgrade);
        }
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(itemList.toArray(new Item[0]));
    }

    private static Item prepareItem(Item item, String name) {
        itemList.add(item);
        item.setUnlocalizedName(name);
        item.setRegistryName(name);
        if (!item.getHasSubtypes()) {
            item.setCreativeTab(TotemDefender.tab);
        }
        return item;
    }

    private static Item prepareItem(Block block) {
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        item.setUnlocalizedName(block.getRegistryName().getResourcePath());
        itemList.add(item);
        return item;
    }

    private static Item prepareItem(ItemBlock item) {
        item.setRegistryName(item.getBlock().getRegistryName());
        item.setUnlocalizedName(item.getBlock().getRegistryName().getResourcePath());
        itemList.add(item);
        return item;
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        for (Item item : itemList) {
            if (item instanceof ICustomRenderModel) {
                ((ICustomRenderModel) item).registerRender();
            } else {
                registerRender(item);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Item item) {
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
    }
}
