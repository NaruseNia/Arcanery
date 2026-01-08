package one.nxeu.arcanery.fabric.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import one.nxeu.arcanery.Arcanery;

public class ArcaneryItemGroups {
    public static final ResourceKey<CreativeModeTab> ARCANERY_INGREDIENTS_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, "arcanery_ingredients")
    );
    public static final CreativeModeTab ARCANERY_INGREDIENTS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ArcaneryItems.FIREBELL))
            .title(Component.translatable("itemGroup.arcanery.arcanery_ingredients"))
            .build();
    public static final ResourceKey<CreativeModeTab> ARCANERY_BLOCKS_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, "arcanery_blocks")
    );
    public static final CreativeModeTab ARCANERY_BLOCKS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ArcaneryBlocks.BREWERS_CAULDRON))
            .title(Component.translatable("itemGroup.arcanery.arcanery_blocks"))
            .build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ARCANERY_INGREDIENTS_KEY, ARCANERY_INGREDIENTS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ARCANERY_BLOCKS_KEY, ARCANERY_BLOCKS);
        ItemGroupEvents.modifyEntriesEvent(ARCANERY_INGREDIENTS_KEY).register(group -> {
            // > Herbs
            group.accept(ArcaneryItems.FIREBELL);
            group.accept(ArcaneryItems.WINDBLOOM);
            group.accept(ArcaneryItems.WATERBLOOM);
            group.accept(ArcaneryItems.TERRARIA);
            group.accept(ArcaneryItems.LIFELEAF);
            group.accept(ArcaneryItems.TANGLEWEED);
            group.accept(ArcaneryItems.GOLDTHORN);
            group.accept(ArcaneryItems.GOODBERRY);
            group.accept(ArcaneryItems.HAIRY_BANANA);
            group.accept(ArcaneryItems.ICEFRUIT);
            group.accept(ArcaneryItems.THUNDER_THISTLE);
            group.accept(ArcaneryItems.BLOODTHORN);
            group.accept(ArcaneryItems.DREAM_BEET);
            group.accept(ArcaneryItems.DRUIDS_ROSEMARY);
            group.accept(ArcaneryItems.FEATHERBLOOM);
            group.accept(ArcaneryItems.LAVA_ROOT);
            group.accept(ArcaneryItems.COLDLEAF);
            group.accept(ArcaneryItems.FLAMEWEED);
            group.accept(ArcaneryItems.GRASPING_ROOT);
            group.accept(ArcaneryItems.THORNSTICK);
            group.accept(ArcaneryItems.WHIRLWEED);
            group.accept(ArcaneryItems.BOOMBLOOM);
            group.accept(ArcaneryItems.DRAGON_PEPPER);
            group.accept(ArcaneryItems.FLUFFBLOOM);
            group.accept(ArcaneryItems.HEALERS_HEATHER);
            group.accept(ArcaneryItems.SPELLBLOOM);
            group.accept(ArcaneryItems.EVERGREEN_FERN);
            group.accept(ArcaneryItems.MAGEBERRY);
            group.accept(ArcaneryItems.TERROR_BUD);
            // > Mushrooms
            group.accept(ArcaneryItems.DRYADS_SADDLE);
            group.accept(ArcaneryItems.MAD_MUSHROOM);
            group.accept(ArcaneryItems.MARSHROOM);
            group.accept(ArcaneryItems.MUDSHROOM);
            group.accept(ArcaneryItems.STINK_MUSHROOM);
            group.accept(ArcaneryItems.SULPHUR_SHELF);
            group.accept(ArcaneryItems.WITCH_MUSHROOM);
            group.accept(ArcaneryItems.SHADOW_CHANTERELLE);
            group.accept(ArcaneryItems.WEIRD_SHROOM);
            group.accept(ArcaneryItems.FOGGY_PARASOL);
            group.accept(ArcaneryItems.GOBLIN_SHROOM);
            group.accept(ArcaneryItems.MOSS_SHROOM);
            group.accept(ArcaneryItems.PHANTOM_SKIRT);
            group.accept(ArcaneryItems.POOPSHROOM);
            group.accept(ArcaneryItems.WATERCAP);
            group.accept(ArcaneryItems.KRAKEN_MUSHROOM);
            group.accept(ArcaneryItems.MAGMA_MOREL);
            group.accept(ArcaneryItems.GRAVE_TRUFFLE);
            group.accept(ArcaneryItems.RAINBOW_CAP);
            // > Minerals
            group.accept(ArcaneryItems.CLOUD_CRYSTAL);
            group.accept(ArcaneryItems.EARTH_PYRITE);
            group.accept(ArcaneryItems.FROST_SAPPHIRE);
            group.accept(ArcaneryItems.FIRE_CITRINE);
            group.accept(ArcaneryItems.BLOOD_RUBY);
            group.accept(ArcaneryItems.ARCANE_CRYSTAL);
            group.accept(ArcaneryItems.LIFE_CRYSTAL);
            group.accept(ArcaneryItems.PLAGUE_STIBNITE);
            group.accept(ArcaneryItems.FABLE_BISMUTH);
        });

        ItemGroupEvents.modifyEntriesEvent(ARCANERY_BLOCKS_KEY).register(group -> {
            group.accept(ArcaneryBlocks.BREWERS_CAULDRON);
        });
    }
}
