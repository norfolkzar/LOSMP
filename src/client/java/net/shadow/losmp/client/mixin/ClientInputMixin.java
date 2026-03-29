package net.shadow.losmp.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.option.Perspective;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftClient.class)

public class ClientInputMixin {
    @Unique
    MinecraftClient self = (MinecraftClient) (Object) this;

    /**
     * @author justxyz_h
     * @reason I need to somehow restrict player from doing hotbar shit
     */
    @Overwrite
    private void handleInputEvents() {
        assert self.player != null;
        assert self.interactionManager != null;
        while (self.options.togglePerspectiveKey.wasPressed()) {
            Perspective perspective = self.options.getPerspective();
            self.options.setPerspective(self.options.getPerspective().next());
            if (perspective.isFirstPerson() != self.options.getPerspective().isFirstPerson()) {
                self.gameRenderer.onCameraEntitySet(self.options.getPerspective().isFirstPerson() ? self.getCameraEntity() : null);
            }

            self.worldRenderer.scheduleTerrainUpdate();
        }

        while (self.options.smoothCameraKey.wasPressed()) {
            self.options.smoothCameraEnabled = !self.options.smoothCameraEnabled;
        }

        for (int i = 0; i < 9; i++) {
            boolean bl = self.options.saveToolbarActivatorKey.isPressed();
            boolean bl2 = self.options.loadToolbarActivatorKey.isPressed();
            if (self.options.hotbarKeys[i].wasPressed()) {
                if (self.player.isSpectator()) {
                    self.inGameHud.getSpectatorHud().selectSlot(i);
                } else if ((!self.player.isCreative() || self.currentScreen != null || !bl2 && !bl) & (i < 3)) {
                    self.player.getInventory().selectedSlot = i + 3;
                } else {
                    CreativeInventoryScreen.onHotbarKeyPress(self, i, bl2, bl);
                }
            }
        }

        while (self.options.inventoryKey.wasPressed()) {
            if (self.interactionManager.hasRidingInventory()) {
                assert self.player != null;
                self.player.openRidingInventory();
            } else {
                self.getTutorialManager().onInventoryOpened();
                self.setScreen(new InventoryScreen(self.player));
            }
        }

        while (self.options.advancementsKey.wasPressed()) {
            self.setScreen(new AdvancementsScreen(self.player.networkHandler.getAdvancementHandler()));
        }

        while (self.options.dropKey.wasPressed()) {
            if (!self.player.isSpectator() && self.player.dropSelectedItem(Screen.hasControlDown())) {
                self.player.swingHand(Hand.MAIN_HAND);
            }
        }

        while (self.options.chatKey.wasPressed()) {
            ((ClientAccessor) self).invokeOpenChatScreen("");
        }

        if (self.currentScreen == null && self.getOverlay() == null && self.options.commandKey.wasPressed()) {
            ((ClientAccessor) self).invokeOpenChatScreen("/");
        }

        boolean bl3 = false;
        if (self.player.isUsingItem()) {
            if (!self.options.useKey.isPressed()) {
                self.interactionManager.stopUsingItem(self.player);
            }

            while (self.options.attackKey.wasPressed()) {
            }

            while (self.options.useKey.wasPressed()) {
            }

            while (self.options.pickItemKey.wasPressed()) {
            }
        } else {
            while (self.options.attackKey.wasPressed()) {
                bl3 |= ((ClientAccessor) self).invokeAttack();
            }

            while (self.options.useKey.wasPressed()) {
                ((ClientAccessor) self).invokeItemUse();
            }

            while (self.options.pickItemKey.wasPressed()) {
                ((ClientAccessor) self).invokeItemPick();
            }
        }

        if (self.options.useKey.isPressed() && self.itemUseCooldown && !self.player.isUsingItem()) {
            ((ClientAccessor) self).invokeItemUse();
        }

        ((ClientAccessor) self).invokeBlockBreakingHandler(self.currentScreen == null && !bl3 && self.options.attackKey.isPressed() && self.mouse.isCursorLocked());
    }
}
