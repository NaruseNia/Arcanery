package one.nxeu.arcanery.fabric.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class ParticleUtil {
    public static void spawnServerParticle(Level level, ParticleOptions option, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double speed, boolean centered) {
        if (level instanceof ServerLevel serverLevel) {
            var corrected = centered ? 0.5 : 0.0;
            serverLevel.sendParticles(option, x + corrected, y + corrected, z + corrected, count, offsetX, offsetY, offsetZ, speed);
        }
    }

    public static void spawnServerParticle(Level level, ParticleOptions option, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double speed) {
        spawnServerParticle(level, option, x, y, z, count, offsetX, offsetY, offsetZ, speed, false);
    }

    public static void spawnServerParticle(Level level, ParticleOptions option, double x, double y, double z, int count) {
        spawnServerParticle(level, option, x, y, z, count, 0.0, 0.0, 0.0, 0.0);
    }
}
