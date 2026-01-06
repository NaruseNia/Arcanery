package one.nxeu.arcanery.fabric.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import one.nxeu.arcanery.fabric.data.ArcaneryDataComponents;
import one.nxeu.arcanery.fabric.data.ElementComponent;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class IngredientItem extends Item {
    public ElementComponent elements;

    public IngredientItem(Properties properties) {
        super(properties);
        this.elements = ElementComponent.empty();
    }

    public IngredientItem(Properties properties, ElementComponent elements) {
        super(properties);
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
        var elementsMap = ElementComponent.toMap(elements);
        elementsMap.forEach((element, value) -> {
            if (value > 0f) {
                consumer.accept(Component.translatable("itemTooltip.arcanery.ingredient.line0", Component.translatable("item.arcanery.element." + element), value));
            }
        });
    }
}
