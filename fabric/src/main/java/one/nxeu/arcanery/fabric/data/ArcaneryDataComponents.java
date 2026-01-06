package one.nxeu.arcanery.fabric.data;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import one.nxeu.arcanery.Arcanery;

@SuppressWarnings("unused")
public class ArcaneryDataComponents {
    public static DataComponentType<ElementComponent> ELEMENTS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, "elements"),
            DataComponentType.<ElementComponent>builder().persistent(ElementComponent.CODEC).build()
    );

    public static void init() {
        // Intentionally left blank.
    }
}
