package one.nxeu.arcanery.fabric.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
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
import one.nxeu.arcanery.fabric.registry.ArcaneryDataComponents;
import one.nxeu.arcanery.fabric.registry.ArcaneryItems;
import one.nxeu.arcanery.fabric.util.ParticleUtil;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BrewersCauldronBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE_INSIDE = Block.column(12.0F, 4.0F, 16.0F);
    protected static final VoxelShape SHAPE = Util.make(() -> Shapes.join(Shapes.block(), Shapes.or(Block.column(16.0F, 8.0F, 0.0F, 3.0F), Block.column(8.0F, 16.0F, 0.0F, 3.0F), Block.column(12.0F, 0.0F, 3.0F), SHAPE_INSIDE), BooleanOp.ONLY_FIRST));

    public BrewersCauldronBlock(Properties properties) {
        super(properties);
    }

    public static void spawnSplash(Level level, BlockPos pos) {
        ParticleUtil.spawnServerParticle(
                level,
                ParticleTypes.SPLASH,
                pos.getX() + 0.5,
                pos.getY() + 1.0,
                pos.getZ() + 0.5,
                10,
                0.15,
                0.0,
                0.15,
                0.1
        );
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
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        final var entity = level.getBlockEntity(pos);
        if (entity instanceof BrewersCauldronBlockEntity brewersCauldron) {
            if (stack.isEmpty()) {
                final var containedElements = brewersCauldron.containedElements();
                player.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE);
                player.displayClientMessage(Component.literal(containedElements.toFormattedString()), true);
                spawnSplash(level, pos);
                return InteractionResult.SUCCESS;
            }
            if (stack.has(DataComponents.POTION_CONTENTS) && stack.getItem() instanceof PotionItem) {
                final var potion = stack.get(DataComponents.POTION_CONTENTS);
                final var elements = brewersCauldron.containedElements();
                if (potion != null && potion.hasEffects()) return InteractionResult.PASS;
                if (elements.isEmpty()) return InteractionResult.PASS;

                if (player.gameMode() != GameType.CREATIVE) stack.setCount(stack.getCount() - 1);
                final var result = ArcaneryItems.POTION_ITEM.getDefaultInstance();

                result.set(ArcaneryDataComponents.ELEMENTS, elements);
                level.playLocalSound(pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                ParticleUtil.spawnServerParticle(
                        level,
                        SpellParticleOption.create(ParticleTypes.EFFECT, 0xffffff, 0.5f),
                        pos.getX() + 0.5,
                        pos.getY() + 1.0,
                        pos.getZ() + 0.5,
                        10,
                        0.2,
                        0.1,
                        0.2,
                        0.05
                );
                brewersCauldron.clearContainedElements();

                if (!player.getInventory().add(result)) {
                    player.drop(result, false);
                }
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }
}
