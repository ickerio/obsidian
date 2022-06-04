package io.icker.obsidian.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.icker.obsidian.Obsidian;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;


@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "Lnet/minecraft/block/Block;getBlastResistance()F", at = @At("HEAD"), cancellable = true)
    private void getBlastResistance(CallbackInfoReturnable<Float> info) {
        String key = Registry.BLOCK.getId((Block) (Object) this).toString();

        if (Obsidian.CONFIG.containsKey(key)) {
            info.setReturnValue(Obsidian.CONFIG.get(key));
        }
    }
}
