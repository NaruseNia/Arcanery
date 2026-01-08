package one.nxeu.arcanery.fabric.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import one.nxeu.arcanery.fabric.data.ElementData;
import one.nxeu.arcanery.fabric.registry.ArcaneryDataComponents;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class IngredientItem extends Item {
    public ElementData elements = ElementData.empty();

    public IngredientItem(Properties properties) {
        super(properties.component(ArcaneryDataComponents.ELEMENTS, ElementData.empty()));
    }

    public IngredientItem(Properties properties, ElementData elements) {
        super(properties.component(ArcaneryDataComponents.ELEMENTS, elements));
        this.elements = elements;
    }

    @Override
    public @NonNull ItemStack getDefaultInstance() {
        var stack = super.getDefaultInstance();
        stack.set(ArcaneryDataComponents.ELEMENTS, elements);
        return stack;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext tooltipContext, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag) {
        var elements = itemStack.get(ArcaneryDataComponents.ELEMENTS);
        if (elements == null) return;
        if (elements.isEmpty()) {
            consumer.accept(Component.translatable("item.arcanery.element.empty"));
            return;
        }
        var elementsMap = ElementData.toMap(elements);
        elementsMap.forEach((element, value) -> {
            if (value > 0f) {
                consumer.accept(Component.translatable("itemTooltip.arcanery.ingredient.line0", Component.translatable("item.arcanery.element." + element), value));
            }
        });
    }
}
