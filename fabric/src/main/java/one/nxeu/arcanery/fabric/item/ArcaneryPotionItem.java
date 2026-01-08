package one.nxeu.arcanery.fabric.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import one.nxeu.arcanery.fabric.data.ElementData;
import one.nxeu.arcanery.fabric.registry.ArcaneryDataComponents;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class ArcaneryPotionItem extends Item {
    public ArcaneryPotionItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    @Override
    public void onUseTick(@NonNull Level level, @NonNull LivingEntity livingEntity, @NonNull ItemStack itemStack, int tick) {
        if (tick % 4 == 0) {
            level.playLocalSound(livingEntity, SoundEvents.GENERIC_DRINK.value(), livingEntity.getSoundSource(), 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public @NonNull ItemStack finishUsingItem(@NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player) livingEntity : null;

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
        }

        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
        }

        if (player == null || !player.getAbilities().instabuild) {
            if (itemStack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            if (player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return itemStack;
    }

    @Override
    public @NonNull ItemUseAnimation getUseAnimation(@NonNull ItemStack itemStack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public int getUseDuration(@NonNull ItemStack itemStack, @NonNull LivingEntity livingEntity) {
        return 32;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(@NonNull ItemStack itemStack, @NonNull TooltipContext tooltipContext, @NonNull TooltipDisplay tooltipDisplay, @NonNull Consumer<Component> consumer, @NonNull TooltipFlag tooltipFlag) {
        var elements = itemStack.get(ArcaneryDataComponents.ELEMENTS);
        if (elements == null) return;
        var elementsMap = ElementData.toMap(elements);
        elementsMap.forEach((element, value) -> {
            if (value > 0f) {
                consumer.accept(Component.translatable("itemTooltip.arcanery.ingredient.line0", Component.translatable("item.arcanery.element." + element), value));
            }
        });
    }
}
