package net.shadow.losmp.client.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftClient.class)
public interface ClientAccessor {
    @Invoker("openChatScreen")
    void invokeOpenChatScreen(String text);

    @Invoker("doAttack")
    boolean invokeAttack();

    @Invoker("doItemUse")
    void invokeItemUse();

    @Invoker("doItemPick")
    void invokeItemPick();

    @Invoker("handleBlockBreaking")
    void invokeBlockBreakingHandler(boolean breaking);

}