package net.shadow.losmp;

import io.github.lounode.eventwrapper.fabric.AutoEventSubscriberRegistryFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.ModContainerImpl;

public class Losmp implements ModInitializer {
public static final String MOD_ID = "losmp";
    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(mod -> {
            if (mod instanceof ModContainerImpl impl) {
                AutoEventSubscriberRegistryFabric.register(impl);
            }
        });
    }
}
