package net.shadow.losmp.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.Console;

@Mixin(Slot.class)
public abstract class SlotMixin {

    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    private void awooga(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        Slot slot = (Slot) (Object) this;
        if ((player.currentScreenHandler instanceof PlayerScreenHandler)) {
            int index = slot.getIndex();
            if (!(slot.inventory instanceof PlayerInventory) || (index >= 3 && index <= 5)) {
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
        if (!(slot.inventory instanceof PlayerInventory) || (index >= 3 && index <= 5)) {
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canBeHighlighted", at = @At("HEAD"), cancellable = true)
    private void abooga(CallbackInfoReturnable<Boolean> cir) {
        Slot slot = (Slot)(Object)this;
        int index = slot.getIndex();
        cir.setReturnValue((!(slot.inventory instanceof PlayerInventory) || (index >= 3 && index <= 5)));
    }
}