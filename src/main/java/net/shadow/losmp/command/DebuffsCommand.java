package net.shadow.losmp.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.shadow.losmp.config.ModConfigs;


public class DebuffsCommand {


    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("losmp")
                .then(CommandManager.literal("sound")
                        .then(CommandManager.literal("oilrig").then(CommandManager.argument("oilrig_sound_enable",BoolArgumentType.bool()).executes((context -> runOilrigSound(context,BoolArgumentType.getBool(context,"oilrig_sound_enable")))))))
                .then(CommandManager.literal("debuffs")
                        .then(CommandManager.literal("player_killing_penalty").then(CommandManager.argument("player_killing_penalty_enable",BoolArgumentType.bool()).executes((context -> runPlayerKillingPenalty(context,BoolArgumentType.getBool(context,"player_killing_penalty_enable"))))))
                        .then(CommandManager.literal("drowning").then(CommandManager.argument("drowning_enable", BoolArgumentType.bool()).executes((context -> runDrowning(context,BoolArgumentType.getBool(context,"drowning_enable")))))))
                .then(CommandManager.literal("break")
                      .then(CommandManager.literal("gyro").executes((context -> gyroToggle(context,false))))
                      .then(CommandManager.literal("flair").executes((context -> flairToggle(context,false))))
                      .then(CommandManager.literal("engine").executes((context -> engineToggle(context,false)))))

                .then(CommandManager.literal("repair")
                        .then(CommandManager.literal("gyro").executes((context -> gyroToggle(context,true))))
                        .then(CommandManager.literal("flair").executes((context -> flairToggle(context,true))))
                        .then(CommandManager.literal("engine").executes((context -> engineToggle(context,true)))))


        );
    }


    public static int runOilrigSound(CommandContext<ServerCommandSource> context, boolean isOilrigEnabled) {
        ModConfigs.isOilrigSoundOn = isOilrigEnabled;
        if(isOilrigEnabled){
            context.getSource().sendFeedback(()-> Text.literal("Ambient Sounds Has Been Enabled"),true);
            return 1;
        }
        else context.getSource().sendFeedback(()-> Text.literal("Ambient Sounds Has Been Disabled"),true);
        return 1;
    }

    public static int runPlayerKillingPenalty(CommandContext<ServerCommandSource> context, boolean isPlayerKillingPenaltyEnabled) {
        ModConfigs.isKillingPlayerEnabled = isPlayerKillingPenaltyEnabled;
        if(isPlayerKillingPenaltyEnabled){
            context.getSource().sendFeedback(()-> Text.literal("Player Killing Penalty Has Been Enabled"),true);
            return 1;
        }
        else context.getSource().sendFeedback(()-> Text.literal("Player Killing Penalty Has Been Disabled"),true);
        return 1;
    }

    public static int runDrowning(CommandContext<ServerCommandSource> context, boolean isDrowningEnabled) {
        ModConfigs.isDrowningOn = isDrowningEnabled;
        if(isDrowningEnabled){
            context.getSource().sendFeedback(()-> Text.literal("Drowning Has Been Enabled"),true);
            return 1;
        }
        else context.getSource().sendFeedback(()-> Text.literal("Drowning Has Been Disabled"),true);
        return 1;
    }

    public static int engineToggle(CommandContext<ServerCommandSource> context, boolean isEngineEnabled) {
        ModConfigs.isEngineWorking = isEngineEnabled;
        if(isEngineEnabled){
            context.getSource().sendFeedback(()-> Text.literal("Engine Is Now Repaired"),true);
            return 1;
        }
        else context.getSource().sendFeedback(()-> Text.literal("Engine Is Now Broken"),true);
        return 1;
    }

    public static int flairToggle(CommandContext<ServerCommandSource> context, boolean isFlairEnabled) {
        ModConfigs.isFlairWorking = isFlairEnabled;
        if(isFlairEnabled){
            context.getSource().sendFeedback(()-> Text.literal("Flair Is Now Repaired"),true);
            return 1;
        }
        else context.getSource().sendFeedback(()-> Text.literal("Flair Is Now Broken"),true);
        return 1;
    }

    public static int gyroToggle(CommandContext<ServerCommandSource> context, boolean isGyroEnabled) {
        ModConfigs.isGyroZeppeliWorking = isGyroEnabled;
        if(isGyroEnabled){
            context.getSource().sendFeedback(()-> Text.literal("Gyro Is Now Repaired"),true);
            return 1;
        }
        else context.getSource().sendFeedback(()-> Text.literal("Gyro Is Now Broken"),true);
        return 1;
    }
}
