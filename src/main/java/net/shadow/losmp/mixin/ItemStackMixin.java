package net.shadow.losmp.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract void setCount(int count);

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private <T extends LivingEntity> void onTick(World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;
        if (self.isFood()&&entity instanceof ServerPlayerEntity player) {
            if (self.getDamage() >= self.getMaxDamage()) {
                this.setCount(0);
                if(entity instanceof ServerPlayerEntity player1){
                    player.getInventory().setStack(slot,new ItemStack(Items.ROTTEN_FLESH));
                }
            }
        }
    }
}