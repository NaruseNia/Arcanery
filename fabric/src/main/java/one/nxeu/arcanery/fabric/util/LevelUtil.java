package one.nxeu.arcanery.fabric.util;

import net.minecraft.world.level.Level;

public class LevelUtil {
    public static void whenClient(Level level, Runnable action) {
        if (level.isClientSide()) {
            action.run();
        }
    }

    public static void whenServer(Level level, Runnable action) {
        if (!level.isClientSide()) {
            action.run();
        }
    }
}
