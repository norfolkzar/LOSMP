package net.shadow.losmp.config;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.command.GameRuleCommand;
import net.minecraft.world.GameRules;

public class ModConfigs {
    public static GameRules.Key<GameRules.BooleanRule> allowTaskBlockFails;
    public static Boolean isDrowningOn = true;
    public static Boolean isOilrigSoundOn = true;
    public static Boolean isKillingPlayerEnabled = true;
    public static Boolean isEngineWorking = true;
    public static Boolean isGyroZeppeliWorking = true;
    public static Boolean isFlairWorking = true;

    public static void init(){
        allowTaskBlockFails = GameRuleRegistry.register("allowTaskBlockFails", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    }
}
