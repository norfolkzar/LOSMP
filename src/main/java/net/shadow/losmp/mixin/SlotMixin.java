package net.shadow.losmp.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class SlotMixin {
    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    private void awooga(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        Slot slot = (Slot) (Object) this;
        if ((player.currentScreenHandler instanceof PlayerScreenHandler)) {
            int index = slot.getIndex();
            if (index == 3 || index == 4 || index == 5) {
                cir.setReturnValue(true);
            } else {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void awooga(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Slot slot = (Slot)(Object)this;
        int index = slot.getIndex();
        if (index == 3 || index == 4 || index == 5) {
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }
}