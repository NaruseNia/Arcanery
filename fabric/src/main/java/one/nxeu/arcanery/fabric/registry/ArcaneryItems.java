package one.nxeu.arcanery.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import one.nxeu.arcanery.Arcanery;
import one.nxeu.arcanery.fabric.data.ElementData;
import one.nxeu.arcanery.fabric.item.ArcaneryPotionItem;
import one.nxeu.arcanery.fabric.item.IngredientItem;

import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("unused")
public class ArcaneryItems {
    public static final Item TEST_ITEM = register("test_item", Item::new, new Item.Properties());
    public static final ArcaneryPotionItem POTION_ITEM = register("potion", ArcaneryPotionItem::new, new Item.Properties().stacksTo(16));

    // Ingredients ===================================
    // > Herbs
    public static final IngredientItem FIREBELL = registerIngredient("firebell", builder -> builder.fire(1.0f).explosion(0.5f));
    public static final IngredientItem WINDBLOOM = registerIngredient("windbloom", builder -> builder.air(1.0f));
    public static final IngredientItem WATERBLOOM = registerIngredient("waterbloom", builder -> builder.water(1.0f));
    public static final IngredientItem TERRARIA = registerIngredient("terraria", builder -> builder.earth(1.0f));
    public static final IngredientItem LIFELEAF = registerIngredient("lifeleaf", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem TANGLEWEED = registerIngredient("tangleweed", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem GOLDTHORN = registerIngredient("goldthorn", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem GOODBERRY = registerIngredient("goodberry", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem HAIRY_BANANA = registerIngredient("hairy_banana", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem ICEFRUIT = registerIngredient("icefruit", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem THUNDER_THISTLE = registerIngredient("thunder_thistle", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem BLOODTHORN = registerIngredient("bloodthorn", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem DREAM_BEET = registerIngredient("dream_beet", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem DRUIDS_ROSEMARY = registerIngredient("druids_rosemary", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FEATHERBLOOM = registerIngredient("featherbloom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem LAVA_ROOT = registerIngredient("lava_root", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem COLDLEAF = registerIngredient("coldleaf", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FLAMEWEED = registerIngredient("flameweed", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem GRASPING_ROOT = registerIngredient("grasping_root", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem THORNSTICK = registerIngredient("thornstick", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem WHIRLWEED = registerIngredient("whirlweed", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem BOOMBLOOM = registerIngredient("boombloom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem DRAGON_PEPPER = registerIngredient("dragon_pepper", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FLUFFBLOOM = registerIngredient("fluffbloom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem HEALERS_HEATHER = registerIngredient("healers_heather", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem SPELLBLOOM = registerIngredient("spellbloom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem EVERGREEN_FERN = registerIngredient("evergreen_fern", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem MAGEBERRY = registerIngredient("mageberry", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem TERROR_BUD = registerIngredient("terror_bud", IngredientItem::new, new IngredientItem.Properties());

    // > Mushrooms
    public static final IngredientItem DRYADS_SADDLE = registerIngredient("dryads_saddle", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem MAD_MUSHROOM = registerIngredient("mad_mushroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem MARSHROOM = registerIngredient("marshroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem MUDSHROOM = registerIngredient("mudshroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem STINK_MUSHROOM = registerIngredient("stink_mushroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem SULPHUR_SHELF = registerIngredient("sulphur_shelf", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem WITCH_MUSHROOM = registerIngredient("witch_mushroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem SHADOW_CHANTERELLE = registerIngredient("shadow_chanterelle", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem WEIRD_SHROOM = registerIngredient("weird_shroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FOGGY_PARASOL = registerIngredient("foggy_parasol", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem GOBLIN_SHROOM = registerIngredient("goblin_shroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem MOSS_SHROOM = registerIngredient("moss_shroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem PHANTOM_SKIRT = registerIngredient("phantom_skirt", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem POOPSHROOM = registerIngredient("poopshroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem WATERCAP = registerIngredient("watercap", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem KRAKEN_MUSHROOM = registerIngredient("kraken_mushroom", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem MAGMA_MOREL = registerIngredient("magma_morel", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem GRAVE_TRUFFLE = registerIngredient("grave_truffle", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem RAINBOW_CAP = registerIngredient("rainbow_cap", IngredientItem::new, new IngredientItem.Properties());

    // > Minerals
    public static final IngredientItem CLOUD_CRYSTAL = registerIngredient("cloud_crystal", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem EARTH_PYRITE = registerIngredient("earth_pyrite", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FROST_SAPPHIRE = registerIngredient("frost_sapphire", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FIRE_CITRINE = registerIngredient("fire_citrine", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem BLOOD_RUBY = registerIngredient("blood_ruby", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem ARCANE_CRYSTAL = registerIngredient("arcane_crystal", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem LIFE_CRYSTAL = registerIngredient("life_crystal", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem PLAGUE_STIBNITE = registerIngredient("plague_stibnite", IngredientItem::new, new IngredientItem.Properties());
    public static final IngredientItem FABLE_BISMUTH = registerIngredient("fable_bismuth", IngredientItem::new, new IngredientItem.Properties());
    // ===============================================

    public static <TItem extends Item> TItem register(String path, Function<Item.Properties, TItem> factory, Item.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, path));
        TItem item = factory.apply(settings.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);

        return item;
    }

    public static <TItem extends IngredientItem> TItem registerIngredient(String path, Function<IngredientItem.Properties, TItem> factory, IngredientItem.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, path));
        TItem item = factory.apply(settings.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);

        return item;
    }

    public static <TItem extends IngredientItem> TItem registerIngredient(String path, BiFunction<Item.Properties, ElementData, TItem> factory, Item.Properties settings, ElementData elements) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, path));
        TItem item = factory.apply(settings.setId(key), elements);
        Registry.register(BuiltInRegistries.ITEM, key, item);

        return item;
    }

    public static IngredientItem registerIngredient(String path, Function<ElementData.Builder, ElementData.Builder> elementsFactory) {
        ElementData.Builder builder = ElementData.builder();
        elementsFactory.apply(builder);
        ElementData elements = builder.build();

        return registerIngredient(path, IngredientItem::new, new IngredientItem.Properties(), elements);
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {

    }
}
