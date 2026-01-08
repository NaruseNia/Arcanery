package one.nxeu.arcanery.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import one.nxeu.arcanery.Arcanery;
import one.nxeu.arcanery.fabric.block.BrewersCauldronBlock;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ArcaneryBlocks {
    public static final Block BREWERS_CAULDRON = register(
            "brewers_cauldron",
            BrewersCauldronBlock::new,
            BlockBehaviour.Properties.of()
    );

    private static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        final var blockKey = keyOfBlock(path);
        final var block = factory.apply(properties.setId(blockKey));

        if (shouldRegisterItem) {
            final var itemKey = keyOfItem(path);
            final var blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register(path, factory, properties, true);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, name));
    }

    public static void init() {
        // Intentionally left blank.
    }
}
