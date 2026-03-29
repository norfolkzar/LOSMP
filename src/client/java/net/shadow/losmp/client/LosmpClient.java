package net.shadow.losmp.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.shadow.losmp.Losmp;
import net.shadow.losmp.client.screen.EskyBlockScreen;
import net.shadow.losmp.item.ModItems;
import net.shadow.losmp.screen.ModScreenHandlers;

public class LosmpClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ESKY_BLOCK_SCREEN_HANDLER, EskyBlockScreen::new);
            ModelPredicateProviderRegistry.register(ModItems.FUEL_CANISTER_ITEM, new Identifier(Losmp.MOD_ID, "fuel_percentage"),
                    (stack, world, entity, seed) -> 1 - stack.getNbt().getInt("losmp.fuel"));
        }
}
