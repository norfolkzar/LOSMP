package net.shadow.losmp.events;

import com.mojang.authlib.minecraft.TelemetrySession;
import io.github.lounode.eventwrapper.event.entity.living.LivingEventWrapper;
import io.github.lounode.eventwrapper.eventbus.api.EventBusSubscriberWrapper;
import io.github.lounode.eventwrapper.eventbus.api.SubscribeEventWrapper;
import net.fabricmc.fabric.api.transfer.v1.item.PlayerInventoryStorage;
import net.minecraft.entity.ai.brain.sensor.NearestLivingEntitiesSensor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Random;

//livingEntity.hasPermissionLevel() to check if the player has OP or smthing
@EventBusSubscriberWrapper
public class ServerEvents {
    static Random random = new Random();
    private static double scarySoundCooldownForY0 = 20 * 60 * 3 + random.nextInt(10) * 20 * 6;
    private static double scarySoundCooldownForY80 = 20 * 60 * 3 + random.nextInt(10) * 20 * 6;
    private static double scarySoundCooldownForY115 = 20 * 60 * 3 + random.nextInt(10) * 20 * 6;
    @SubscribeEventWrapper
    public static void OnLivingEntityTick(LivingEventWrapper.LivingTickEvent event) {
        var livingEntity = event.getEntity();
        var level = livingEntity.getWorld();
        if (scarySoundCooldownForY0>0 && livingEntity.getY()<=0) {
            scarySoundCooldownForY0--;
        }
        if(scarySoundCooldownForY80>0 && livingEntity.getY() <= 80 && livingEntity.getY() > 0){
            scarySoundCooldownForY80--;
        }
        if(scarySoundCooldownForY115>0 && livingEntity.getY() >= 115){
            scarySoundCooldownForY115--;
        }
        if (livingEntity.getY() <= 0 && level.isClient() && scarySoundCooldownForY0 == 0 && livingEntity.isSubmergedInWater()) {
            level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfory0(), SoundCategory.AMBIENT, 1f, 1f);
        }
        if (livingEntity.getY() <= 80 &&  livingEntity.getY() > 0 && level.isClient() && scarySoundCooldownForY80 == 0 && livingEntity.isSubmergedInWater()) {
            level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfory80(), SoundCategory.AMBIENT, 1f, 1f);
        }
        if (livingEntity.getY() >= 115 && level.isClient() && scarySoundCooldownForY115 == 0) {
            level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfory115(), SoundCategory.AMBIENT, 1f, 1f);
        }
        if ((livingEntity instanceof PlayerEntity player) && livingEntity.isTouchingWater() && !player.isCreative()) {
            livingEntity.addVelocity(0,-0.045,0);
            if (player.isInSwimmingPose()) {
                player.addVelocity(0,-0.08,0);
            }
            if (player.getAir() <= 20 && player.isSubmergedInWater() && player.getAir() > 15) {
                level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfordrowning_subtle(), SoundCategory.AMBIENT, 1f, 1f);
            }
            if (player.getAir() <= 15 && player.getAir() > 10 && player.isSubmergedInWater()) {
                level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfordrowning_quiet(), SoundCategory.AMBIENT, 1f, 1f);
            }
            if (player.getAir() <= 10 && player.getAir() > 5 && player.isSubmergedInWater()) {
                level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfordrowning(), SoundCategory.AMBIENT, 1f, 1f);
            }
            if (player.getAir() <= 5) {
                level.playSound(null, livingEntity.getBlockPos(), placeHolderSoundfordrowning_loud(), SoundCategory.AMBIENT, 1f, 1f);
            }
        }

        if(livingEntity instanceof PlayerEntity player) {
            for (int i=0;i < 37;i++){
                itemTickInInventory(player.getInventory().getStack(i));
            }
        }
    }

    public static void itemTickInInventory(ItemStack itemStack){
        if(itemStack.isFood()){
            itemStack.getMaxDamage();
            itemStack.setDamage(1);
        }
    }

    public static SoundEvent placeHolderSoundfory0() {
        return SoundEvents.ENTITY_DONKEY_CHEST;
    }
    public static SoundEvent placeHolderSoundfory80() {
        return SoundEvents.ENTITY_DONKEY_CHEST;
    }
    public static SoundEvent placeHolderSoundfory115() {
        return SoundEvents.ENTITY_DONKEY_CHEST;
    }
    public static SoundEvent placeHolderSoundfordrowning() {
        return SoundEvents.BLOCK_BEACON_AMBIENT;
    }
    public static SoundEvent placeHolderSoundfordrowning_subtle() {
        return SoundEvents.BLOCK_BEACON_AMBIENT;
    }
    public static SoundEvent placeHolderSoundfordrowning_quiet() {
        return SoundEvents.BLOCK_BEACON_AMBIENT;
    }
    public static SoundEvent placeHolderSoundfordrowning_loud() {
        return SoundEvents.BLOCK_BEACON_AMBIENT;
    }
}
