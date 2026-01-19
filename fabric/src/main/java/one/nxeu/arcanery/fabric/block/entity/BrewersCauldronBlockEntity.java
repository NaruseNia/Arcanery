package one.nxeu.arcanery.fabric.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import one.nxeu.arcanery.fabric.block.BrewersCauldronBlock;
import one.nxeu.arcanery.fabric.data.ElementData;
import one.nxeu.arcanery.fabric.registry.ArcaneryBlockEntities;
import one.nxeu.arcanery.fabric.registry.ArcaneryDataComponents;
import one.nxeu.arcanery.fabric.util.LevelUtil;
import one.nxeu.arcanery.fabric.util.ParticleUtil;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BrewersCauldronBlockEntity extends BlockEntity {
    private ElementData containedElements = ElementData.empty();
    private int ticksSinceLast = 0;

    public BrewersCauldronBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ArcaneryBlockEntities.BREWERS_CAULDRON, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BrewersCauldronBlockEntity entity) {
        if (!entity.containedElements.isEmpty()) {
            ParticleUtil.spawnServerParticle(
                    level,
                    ParticleTypes.BUBBLE_POP,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    1,
                    0.15,
                    0.0,
                    0.15,
                    0.0
            );
        }

        final var ticks = entity.increaseTicks();
        if (ticks < 5) return;
        entity.resetTicks();

        // Get nearby ingredient items and process them
        final var nearbyItems = level.getEntitiesOfClass(
                ItemEntity.class,
                state.getShape(level, pos).bounds().expandTowards(0.0, -0.8, 0.0).move(pos)
        );
        if (!nearbyItems.isEmpty()) {
            final var itemEntity = nearbyItems.stream().findFirst().orElse(null);
            final var stack = itemEntity.getItem();
            if (!stack.isEmpty() && stack.has(ArcaneryDataComponents.ELEMENTS)) {
                final var itemElements = stack.get(ArcaneryDataComponents.ELEMENTS);
                if (itemElements == null) return;

                level.playSound(null, pos, SoundEvents.GENERIC_SWIM, SoundSource.BLOCKS, 0.5f, 1.0f);

                LevelUtil.whenServer(level, () -> {
                    BrewersCauldronBlock.spawnSplash(level, pos);
                    entity.processItems(stack, stack.getCount(), true);
                });
            }
        }
    }

    public boolean processItems(ItemStack input) {
        return processItems(input, 1, true);
    }

    public boolean processItems(ItemStack input, boolean decreaseStack) {
        return processItems(input, 1, decreaseStack);
    }

    public boolean processItems(ItemStack input, int consumedCount, boolean decreaseStack) {
        if (input.isEmpty() || !input.has(ArcaneryDataComponents.ELEMENTS)) return false;

        final var itemElements = input.get(ArcaneryDataComponents.ELEMENTS);
        if (itemElements == null) return false;

        if (this.getLevel() != null && !this.getLevel().isClientSide()) {
            this.containedElements(this.containedElements().merge(itemElements.multiply(consumedCount)));
            if (decreaseStack) input.setCount(input.getCount() - consumedCount);
            this.setChanged();
            return true;
        }
        return false;
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput view) {
        view.putInt("ticks_since_last", ticksSinceLast);
        containedElements.write(view);

        super.saveAdditional(view);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput view) {
        super.loadAdditional(view);

        this.ticksSinceLast = view.getIntOr("ticks_since_last", 0);
        this.containedElements = ElementData.readFrom(view);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider provider) {
        return saveCustomOnly(provider);
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

    public int increaseTicks() {
        return ++this.ticksSinceLast;
    }

    public void resetTicks() {
        this.ticksSinceLast = 0;
    }
}
