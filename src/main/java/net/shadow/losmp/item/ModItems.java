package net.shadow.losmp.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.losmp.Losmp;
import net.shadow.losmp.item.custom.ShotgunItem;
import net.shadow.losmp.item.custom.ShotgunAmmoItem;

public class ModItems {

    public static final Item SHOTGUN = registerItems("shotgun",
            new ShotgunItem(new Item.Settings().maxCount(1)));

    public static final Item SHOTGUN_AMMO = registerItems("shotgun_ammo",
            new ShotgunAmmoItem(new Item.Settings()));

    private static void addItemsToCreativeTab(FabricItemGroupEntries entries){
        entries.add(SHOTGUN);
    }

    private static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(Losmp.MOD_ID,name),item);
    }
    public static void registerModItems(){
        Losmp.LOGGER.info("Registering Items for " + Losmp.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToCreativeTab);
    }
}
