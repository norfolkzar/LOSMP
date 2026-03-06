package net.shadow.losmp.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.shadow.losmp.Losmp;

public class ModItems {

    public static final Item AWOOGA_ITEM = registerItems("awooga",new Item(new Item.Settings()));

    private static void addItemsToCreativeTab(FabricItemGroupEntries entries){
        entries.add(AWOOGA_ITEM);
    }

    private static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(Losmp.MOD_ID,name),item);
    }
    public static void registerModItems(){
        Losmp.LOGGER.info("Registering Items for " + Losmp.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToCreativeTab);
    }
}
