package one.nxeu.arcanery.fabric;

import net.fabricmc.api.ModInitializer;
import one.nxeu.arcanery.Arcanery;
import one.nxeu.arcanery.fabric.registry.*;

public final class ArcaneryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Arcanery.init();

        ArcaneryDataComponents.init();

        ArcaneryBlockEntities.init();
        ArcaneryBlocks.init();
        ArcaneryItems.init();

        ArcaneryItemGroups.register();
    }
}
