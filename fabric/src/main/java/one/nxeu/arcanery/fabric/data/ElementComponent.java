package one.nxeu.arcanery.fabric.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Builder;

import java.util.HashMap;

@Builder(builderClassName = "Builder")
public record ElementComponent(float air, float death, float earth, float explosion, float fire, float life,
                               float magic, float water) {
    public static final Codec<ElementComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.fieldOf("air").forGetter(ElementComponent::air),
            Codec.FLOAT.fieldOf("death").forGetter(ElementComponent::death),
            Codec.FLOAT.fieldOf("earth").forGetter(ElementComponent::earth),
            Codec.FLOAT.fieldOf("explosion").forGetter(ElementComponent::explosion),
            Codec.FLOAT.fieldOf("fire").forGetter(ElementComponent::fire),
            Codec.FLOAT.fieldOf("life").forGetter(ElementComponent::life),
            Codec.FLOAT.fieldOf("magic").forGetter(ElementComponent::magic),
            Codec.FLOAT.fieldOf("water").forGetter(ElementComponent::water)
    ).apply(builder, ElementComponent::new));

    public static ElementComponent empty() {
        return new ElementComponent(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f);
    }

    public static ElementComponent of(float air, float death, float earth, float explosion, float fire, float life,
                                      float magic, float water) {
        return new ElementComponent(air, death, earth, explosion, fire, life, magic, water);
    }

    public static HashMap<String, Float> toMap(ElementComponent elements) {
        HashMap<String, Float> map = new HashMap<>();
        map.put("air", elements.air());
        map.put("death", elements.death());
        map.put("earth", elements.earth());
        map.put("explosion", elements.explosion());
        map.put("fire", elements.fire());
        map.put("life", elements.life());
        map.put("magic", elements.magic());
        map.put("water", elements.water());
        return map;
    }
}
