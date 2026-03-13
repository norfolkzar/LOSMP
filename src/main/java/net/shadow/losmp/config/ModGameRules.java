package net.shadow.losmp.config;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGameRules {
    public static GameRules.Key<GameRules.BooleanRule> allowTaskBlockFails;

    public static void init(){
    allowTaskBlockFails = GameRuleRegistry.register("allowTaskBlockFails", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    }
}
