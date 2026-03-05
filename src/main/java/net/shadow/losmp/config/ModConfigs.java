package net.shadow.losmp.config;


import com.mojang.datafixers.util.Pair;
import net.shadow.losmp.Losmp;


public class ModConfigs  {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String TEST;
    public static Boolean isDrowningOn;
    public static Boolean isOilrigSoundOn;
    public static Boolean isKillingPlayerEnabled;


    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Losmp.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("key.test.value1", "Just a Testing string!"), "String");//Just a test config variable
        configs.addKeyValuePair(new Pair<>("key.test.value2", false), "Makes escaping sea's/oceans almost impossible");//Drowning
        configs.addKeyValuePair(new Pair<>("key.test.value3",true),"Oilrig Sound below or above a certain height");//Oilrig Sounds
        configs.addKeyValuePair(new Pair<>("key.test.value4",true),"Player killing now has consequences..");
    }

    private static void assignConfigs() {
        TEST = CONFIG.getOrDefault("key.test.value1", "Nothing");
        isDrowningOn = CONFIG.getOrDefault("key.test.value2",false);
        isOilrigSoundOn = CONFIG.getOrDefault("key.test.value3",true);
        isKillingPlayerEnabled = CONFIG.getOrDefault("key.test.value4",true);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
