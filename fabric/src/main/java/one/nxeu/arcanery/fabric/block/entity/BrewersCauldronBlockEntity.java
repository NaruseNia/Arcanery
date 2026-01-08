package one.nxeu.arcanery.fabric.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import one.nxeu.arcanery.fabric.data.ElementData;
import one.nxeu.arcanery.fabric.registry.ArcaneryBlockEntities;
import one.nxeu.arcanery.fabric.registry.ArcaneryDataComponents;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BrewersCauldronBlockEntity extends BlockEntity implements SimpleInventoryContainer {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private ElementData containedElements = ElementData.empty();

    public BrewersCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ArcaneryBlockEntities.BREWERS_CAULDRON, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BrewersCauldronBlockEntity entity) {
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.BUBBLE_POP,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    3,
                    0.2,
                    0.0,
                    0.2,
                    0.0
            );
        }

        // Get nearby ingredient items and process them
        final var nearbyItems = level.getEntitiesOfClass(
                ItemEntity.class,
                state.getShape(level, pos).bounds().expandTowards(0.0, -0.8, 0.0).move(pos)
        );

        for (ItemEntity itemEntity : nearbyItems) {
            final var stack = itemEntity.getItem();
            if (!stack.isEmpty() && stack.has(ArcaneryDataComponents.ELEMENTS)) {
                final var itemElements = stack.get(ArcaneryDataComponents.ELEMENTS);
                if (itemElements == null) continue;

                entity.containedElements = entity.containedElements.merge(itemElements);
                entity.setChanged();
                itemEntity.setItem(stack.copyWithCount(stack.getCount() - 1));

                level.playLocalSound(pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
        }
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput view) {
        super.saveAdditional(view);

        containedElements.write(view);
        ContainerHelper.saveAllItems(view, inventory);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput view) {
        super.loadAdditional(view);

        this.containedElements = ElementData.readFrom(view);
        ContainerHelper.loadAllItems(view, inventory);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider provider) {
        return saveWithoutMetadata(provider);
    }

    @Override
    public @NonNull NonNullList<ItemStack> items() {
        return inventory;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ElementData containedElements() {
        return containedElements;
    }

    public void clearContainedElements() {
        this.containedElements = ElementData.empty();
        setChanged();
    }

    public void containedElements(ElementData elements) {
        this.containedElements = elements;
        setChanged();
    }
}
