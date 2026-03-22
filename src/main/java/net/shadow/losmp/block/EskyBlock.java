package net.shadow.losmp.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.shadow.losmp.block.entity.EskyBlockEntity;
import org.jetbrains.annotations.Nullable;

public class EskyBlock extends BlockWithEntity implements BlockEntityProvider {
    public EskyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
