package one.nxeu.arcanery.fabric.block.entity;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.slot.SlotCollection;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface
public interface SimpleInventoryContainer extends WorldlyContainer {
    static SimpleInventoryContainer of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    static SimpleInventoryContainer of(int size) {
        return () -> NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    default int @NonNull [] getSlotsForFace(@NonNull Direction direction) {
        var result = new int[items().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    default @Nullable SlotAccess getSlot(int i) {
        return WorldlyContainer.super.getSlot(i);
    }

    @Override
    default boolean canPlaceItem(int i, @NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    default boolean canTakeItem(@NonNull Container container, int i, @NonNull ItemStack itemStack) {
        return true;
    }

    @Override
    default int getContainerSize() {
        return items().size();
    }

    @Override
    default boolean isEmpty() {
        for (ItemStack itemStack : items()) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    default @NonNull ItemStack getItem(int slot) {
        return items().get(slot);
    }

    @Override
    default @NonNull ItemStack removeItem(int slot, int count) {
        return ContainerHelper.removeItem(items(), slot, count);
    }

    @Override
    default @NonNull ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items(), slot);
    }

    @Override
    default void setItem(int i, @NonNull ItemStack itemStack) {
        items().set(i, itemStack);
        if (itemStack.getCount() > getMaxStackSize()) {
            itemStack.setCount(getMaxStackSize());
        }
    }

    @Override
    default void clearContent() {
        items().clear();
    }

    @Override
    default void setChanged() {
    }

    @Override
    default boolean stillValid(@NonNull Player player) {
        return true;
    }

    @Override
    default boolean hasAnyMatching(Predicate<ItemStack> predicate) {
        return WorldlyContainer.super.hasAnyMatching(predicate);
    }

    @Override
    default boolean hasAnyOf(Set<Item> set) {
        return WorldlyContainer.super.hasAnyOf(set);
    }

    @Override
    default void startOpen(ContainerUser containerUser) {
        WorldlyContainer.super.startOpen(containerUser);
    }

    @Override
    default void stopOpen(ContainerUser containerUser) {
        WorldlyContainer.super.stopOpen(containerUser);
    }

    @Override
    default int getMaxStackSize() {
        return WorldlyContainer.super.getMaxStackSize();
    }

    @Override
    default int getMaxStackSize(ItemStack itemStack) {
        return WorldlyContainer.super.getMaxStackSize(itemStack);
    }

    @Override
    default List<ContainerUser> getEntitiesWithContainerOpen() {
        return WorldlyContainer.super.getEntitiesWithContainerOpen();
    }

    @Override
    default Iterator<ItemStack> iterator() {
        return WorldlyContainer.super.iterator();
    }

    @Override
    default SlotCollection getSlotsFromRange(IntList intList) {
        return WorldlyContainer.super.getSlotsFromRange(intList);
    }

    @Override
    default Spliterator<ItemStack> spliterator() {
        return WorldlyContainer.super.spliterator();
    }

    @Override
    default int countItem(@NonNull Item item) {
        return WorldlyContainer.super.countItem(item);
    }

    @Override
    default void forEach(Consumer<? super ItemStack> action) {
        WorldlyContainer.super.forEach(action);
    }

    @Override
    default boolean canTakeItemThroughFace(int i, @NonNull ItemStack itemStack, @NonNull Direction direction) {
        return true;
    }

    @Override
    default boolean canPlaceItemThroughFace(int i, @NonNull ItemStack itemStack, @Nullable Direction direction) {
        return true;
    }

    @NonNull NonNullList<ItemStack> items();
}
