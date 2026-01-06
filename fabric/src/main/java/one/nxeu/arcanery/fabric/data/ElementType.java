package one.nxeu.arcanery.fabric.data;

import com.mojang.serialization.Codec;

public enum ElementType {
    NONE,
    AIR,
    DEATH,
    EARTH,
    EXPLOSION,
    FIRE,
    LIFE,
    MAGIC,
    WATER;

    public static final Codec<ElementType> CODEC = Codec.stringResolver(
            ElementType::name,
            ElementType::valueOf
    );

    private final String id;

    ElementType() {
        this.id = this.name().toLowerCase();
    }

    public String id() {
        return id;
    }
}
