package net.shadow.losmp.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.shadow.losmp.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class ShotgunItem extends Item {
    public ShotgunItem(Settings settings) {
        super(settings);
    }
    private int RELOAD_TIME = 160;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
            if (!(itemStack.getNbt().getInt("losmp.ammo")== 0)) {
                EntityHitResult result = hitScanEntities(player, 15);
                if (itemStack.getNbt().getInt("losmp.ammo") > 0 && !world.isClient) {
                    player.getItemCooldownManager().set(this, 100);
                    player.getStackInHand(hand);
                    world.playSound(null,player.getBlockPos(),SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE,SoundCategory.AMBIENT,10f,10f);
                    itemStack.getOrCreateNbt().putInt("losmp.ammo", itemStack.getNbt().getInt("losmp.ammo") - 1);
                    Entity hit = result.getEntity();
                    hit.damage(player.getDamageSources().playerAttack(player), 100.0F);
                    return TypedActionResult.success(itemStack, world.isClient());
                }
                return TypedActionResult.success(itemStack, world.isClient());
            }
            else {
                player.setCurrentHand(hand);
                return TypedActionResult.consume(itemStack);
            }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 140;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }


    @Override
    public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity user) {
        if ((user instanceof PlayerEntity player)) {
            if (reloadForPlayer(player)) {
                itemStack.getNbt().putInt("losmp.ammo", 1);
                if (partialReload(player)) {
                    itemStack.getNbt().putInt("losmp.ammo", 2);
                }
                if (!world.isClient) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundCategory.PLAYERS, 1f, 1f);
                    player.getItemCooldownManager().set(this, 20);
                }
            }
        }
        return super.finishUsing(itemStack, world, user);
    }

    @Override
    public void postProcessNbt(NbtCompound nbt) {
        super.postProcessNbt(nbt);
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return super.allowNbtUpdateAnimation(player, hand, oldStack, newStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!stack.hasNbt() || !stack.getNbt().contains("losmp.ammo")) {
            stack.getOrCreateNbt().putInt("losmp.ammo", 2);
        }
    }

    @Override
    public boolean isNbtSynced() {
        return super.isNbtSynced();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String ammo = "Current ammo is " + stack.getNbt().getInt("losmp.ammo");
        tooltip.add(Text.literal(ammo));
    }

    public static EntityHitResult hitScanEntities(PlayerEntity player, double range) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d end = start.add(direction.multiply(range));
        Box searchBox = player.getBoundingBox().stretch(direction.multiply(range)).expand(1.0D);
        Predicate<Entity> filter = entity -> !entity.isSpectator() && entity.isAlive() && entity != player;
        return ProjectileUtil.raycast(player, start, end, searchBox, filter, range * range);
    }

    public static Boolean reloadForPlayer(PlayerEntity player) {
        boolean hasReloaded = false;
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if (!itemStack.isEmpty() && itemStack.isOf(ModItems.SHOTGUN_AMMO)){
                itemStack.decrement(1);
                hasReloaded = true;
                break;
            }
        }
        return hasReloaded;
    }

    public static Boolean partialReload(PlayerEntity player) {
        boolean secondshell = false;
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if (!itemStack.isEmpty() && itemStack.isOf(ModItems.SHOTGUN_AMMO)){
                itemStack.decrement(2);
                secondshell = true;
                break;
            }
        }
        return secondshell;
    }
}
