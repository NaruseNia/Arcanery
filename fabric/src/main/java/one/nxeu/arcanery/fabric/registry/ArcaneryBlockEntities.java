package one.nxeu.arcanery.fabric.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import one.nxeu.arcanery.Arcanery;
import one.nxeu.arcanery.fabric.block.entity.BrewersCauldronBlockEntity;

@SuppressWarnings("unused")
public class ArcaneryBlockEntities {
    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        final var id = Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void init() {
        // Intentionally left blank.
    }

    public static final BlockEntityType<BrewersCauldronBlockEntity> BREWERS_CAULDRON = register(
            "brewers_cauldron",
            BrewersCauldronBlockEntity::new,
            ArcaneryBlocks.BREWERS_CAULDRON
    );
}
