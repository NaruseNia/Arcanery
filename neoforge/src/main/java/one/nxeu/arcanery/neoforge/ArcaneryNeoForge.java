package one.nxeu.arcanery.neoforge;

import one.nxeu.arcanery.Arcanery;
import net.neoforged.fml.common.Mod;

@Mod(Arcanery.MOD_ID)
public final class ArcaneryNeoForge {
    public ArcaneryNeoForge() {
        // Run our common setup.
        Arcanery.init();
    }
}
