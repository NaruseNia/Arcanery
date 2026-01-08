package one.nxeu.arcanery.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import one.nxeu.arcanery.Arcanery;
import one.nxeu.arcanery.fabric.data.ElementData;

@SuppressWarnings("unused")
public class ArcaneryDataComponents {
    public static DataComponentType<ElementData> ELEMENTS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Arcanery.MOD_ID, "elements"),
            DataComponentType.<ElementData>builder().persistent(ElementData.CODEC).build()
    );

    public static void init() {
        // Intentionally left blank.
    }
}
