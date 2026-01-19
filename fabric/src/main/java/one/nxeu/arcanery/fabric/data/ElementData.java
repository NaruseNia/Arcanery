package one.nxeu.arcanery.fabric.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.HashMap;

@Data
@Builder(builderClassName = "Builder")
@AllArgsConstructor(staticName = "of")
public class ElementData {
    public static final Codec<ElementData> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.FLOAT.fieldOf("air").forGetter(ElementData::air),
            Codec.FLOAT.fieldOf("death").forGetter(ElementData::death),
            Codec.FLOAT.fieldOf("earth").forGetter(ElementData::earth),
            Codec.FLOAT.fieldOf("explosion").forGetter(ElementData::explosion),
            Codec.FLOAT.fieldOf("fire").forGetter(ElementData::fire),
            Codec.FLOAT.fieldOf("life").forGetter(ElementData::life),
            Codec.FLOAT.fieldOf("magic").forGetter(ElementData::magic),
            Codec.FLOAT.fieldOf("water").forGetter(ElementData::water)
    ).apply(builder, ElementData::new));
    private float air;
    private float death;
    private float earth;
    private float explosion;
    private float fire;
    private float life;
    private float magic;
    private float water;

    public static ElementData empty() {
        return new ElementData(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f);
    }

    public static HashMap<String, Float> toMap(ElementData elements) {
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

    public static ElementData readFrom(ValueInput view) {
        final var air = view.getFloatOr("air", 0.0f);
        final var death = view.getFloatOr("death", 0.0f);
        final var earth = view.getFloatOr("earth", 0.0f);
        final var explosion = view.getFloatOr("explosion", 0.0f);
        final var fire = view.getFloatOr("fire", 0.0f);
        final var life = view.getFloatOr("life", 0.0f);
        final var magic = view.getFloatOr("magic", 0.0f);
        final var water = view.getFloatOr("water", 0.0f);

        return ElementData.of(air, death, earth, explosion, fire, life, magic, water);
    }

    public boolean isEmpty() {
        return air == 0f &&
                death == 0f &&
                earth == 0f &&
                explosion == 0f &&
                fire == 0f &&
                life == 0f &&
                magic == 0f &&
                water == 0f;
    }

    public ElementData merge(ElementData other) {
        return ElementData.of(
                this.air + other.air,
                this.death + other.death,
                this.earth + other.earth,
                this.explosion + other.explosion,
                this.fire + other.fire,
                this.life + other.life,
                this.magic + other.magic,
                this.water + other.water
        );
    }

    public ElementData multiply(float factor) {
        return ElementData.of(
                this.air * factor,
                this.death * factor,
                this.earth * factor,
                this.explosion * factor,
                this.fire * factor,
                this.life * factor,
                this.magic * factor,
                this.water * factor
        );
    }

    public void write(ValueOutput view) {
        view.putFloat("air", air);
        view.putFloat("death", death);
        view.putFloat("earth", earth);
        view.putFloat("explosion", explosion);
        view.putFloat("fire", fire);
        view.putFloat("life", life);
        view.putFloat("magic", magic);
        view.putFloat("water", water);
    }

    public String toFormattedString() {
        var builder = new StringBuilder();

        if (isEmpty()) {
            builder.append("No elements contained");
            return builder.toString();
        }

        builder.append("Air:").append(air)
                .append(" Death:").append(death)
                .append(" Earth:").append(earth)
                .append(" Explosion:").append(explosion)
                .append(" Fire:").append(fire)
                .append(" Life:").append(life)
                .append(" Magic:").append(magic)
                .append(" Water:").append(water);

        return builder.toString();
    }
}
