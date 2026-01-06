package one.nxeu.arcanery.fabric.data;

import com.mojang.serialization.Codec;

public enum ElementType {
    NONE,
    FIRE,
    WATER,
    EARTH,
    AIR,
    LIGHT,
    DARKNESS;

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
