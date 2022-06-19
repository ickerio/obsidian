package io.icker.obsidian.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.icker.obsidian.Obsidian;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;


@Mixin(Explosion.class)
public class ExplosionMixin {
    @Shadow
    public List<BlockPos> affectedBlocks;

    @Shadow
    public World world;

    @Inject(method = "collectBlocksAndDamageEntities", at = @At("TAIL"))
    private void collectBlocksAndDamageEntities(CallbackInfo info) {
        ArrayList<BlockPos> blocksToRemove = new ArrayList<BlockPos>();
        this.affectedBlocks.forEach(blockPos -> {
            String key = Registry.BLOCK.getId(this.world.getBlockState(blockPos).getBlock()).toString();

            if (Obsidian.CONFIG.containsKey(key)) {
                int blastCount = Obsidian.getBlastCount(this.world, blockPos, key);
                if (blastCount < Obsidian.CONFIG.get(key).get("count")) {
                    blocksToRemove.add(blockPos);
                    Obsidian.setBlastCount(this.world, blockPos, key, blastCount + 1);
                } else {
                    Obsidian.removeBlastCount(world, blockPos, key);
                }
            }
        });
        blocksToRemove.forEach(this.affectedBlocks::remove);
    }
}
