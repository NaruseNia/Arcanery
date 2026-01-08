package one.nxeu.arcanery.fabric.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import one.nxeu.arcanery.fabric.block.entity.BrewersCauldronBlockEntity;
import one.nxeu.arcanery.fabric.registry.ArcaneryBlockEntities;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BrewersCauldronBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE_INSIDE = Block.column(12.0F, 4.0F, 16.0F);
    protected static final VoxelShape SHAPE = Util.make(() -> Shapes.join(Shapes.block(), Shapes.or(Block.column(16.0F, 8.0F, 0.0F, 3.0F), Block.column(8.0F, 16.0F, 0.0F, 3.0F), Block.column(12.0F, 0.0F, 3.0F), SHAPE_INSIDE), BooleanOp.ONLY_FIRST));

    public BrewersCauldronBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BrewersCauldronBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos blockPos, @NonNull BlockState blockState) {
        return new BrewersCauldronBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NonNull Level level, @NonNull BlockState blockState, @NonNull BlockEntityType<T> blockEntityType) {
//        if (level.isClientSide()) return null;
        return createTickerHelper(blockEntityType, ArcaneryBlockEntities.BREWERS_CAULDRON, BrewersCauldronBlockEntity::tick);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        final var entity = level.getBlockEntity(pos);
        if (entity instanceof BrewersCauldronBlockEntity brewersCauldron) {
            final var containedElements = brewersCauldron.getContainedElements();
            player.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE, 1.0F, 1.0F);
            player.displayClientMessage(Component.literal("Contained Elements: " + containedElements.toString()), true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
