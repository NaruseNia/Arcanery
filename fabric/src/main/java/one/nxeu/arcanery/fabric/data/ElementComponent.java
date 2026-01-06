package one.nxeu.arcanery.fabric.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ElementComponent(ElementType type, float potency) {
    public static final Codec<ElementComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                ElementType.CODEC.fieldOf("type").forGetter(ElementComponent::type),
                Codec.FLOAT.fieldOf("potency").forGetter(ElementComponent::potency)
        ).apply(builder, ElementComponent::new);
    });
}
