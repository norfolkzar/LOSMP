package net.shadow.losmp.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "getMaxDamage",at=@At("HEAD"),cancellable = true)
    private void awooga(CallbackInfoReturnable<Integer> cir){
        Item self = (Item) (Object) this;
        if(self.isFood()){
            cir.setReturnValue(100);
        }
    }
    @Inject(method = "isDamageable", at = @At("RETURN"),cancellable = true)
    private void e(CallbackInfoReturnable<Boolean> cir){
        Item self = (Item) (Object) this;
        cir.setReturnValue(true);
    }
}
