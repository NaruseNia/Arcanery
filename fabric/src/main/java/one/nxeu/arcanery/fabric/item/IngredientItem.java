package one.nxeu.arcanery.fabric.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import one.nxeu.arcanery.fabric.data.ArcaneryDataComponents;
import one.nxeu.arcanery.fabric.data.ElementComponent;
import one.nxeu.arcanery.fabric.data.ElementType;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class IngredientItem extends Item {
    public ElementType elementType;
    public float potency;

    public IngredientItem(Properties properties) {
        super(properties);
        this.elementType = ElementType.NONE;
        this.potency = 0.0f;
    }

    public IngredientItem(Properties properties, ElementType elementType) {
        super(properties);
        this.elementType = elementType;
        this.potency = 0.0f;
    }

    public IngredientItem(Properties properties, ElementType elementType, float elementValue) {
        super(properties);
        this.elementType = elementType;
        this.potency = elementValue;
    }

    @Override
    public @NonNull ItemStack getDefaultInstance() {
        var stack = super.getDefaultInstance();
        stack.set(ArcaneryDataComponents.ELEMENT, new ElementComponent(elementType, potency));
        return stack;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext tooltipContext, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag) {
        consumer.accept(Component.translatable("itemTooltip.arcanery.ingredient.line0", Component.translatable("item.arcanery.element." + elementType.id()), potency).withStyle(ChatFormatting.AQUA));
    }
}
