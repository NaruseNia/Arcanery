package one.nxeu.arcanery.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import one.nxeu.arcanery.Arcanery;
import one.nxeu.arcanery.fabric.item.ArcaneryPotionItem;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ArcaneryItems {
    public static final Item TEST_ITEM = register("test_item", Item::new, new Item.Properties());
    public static final Item POTION_ITEM = register("potion", ArcaneryPotionItem::new, new Item.Properties().stacksTo(16));

    public static <TItem extends Item> TItem register(String path, Function<Item.Properties, TItem> factory, Item.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, path));
        TItem item = factory.apply(settings.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);

        return item;
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {

    }
}
