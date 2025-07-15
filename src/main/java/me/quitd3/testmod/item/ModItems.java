package me.quitd3.testmod.item;

import me.quitd3.testmod.Testmod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;
import static me.quitd3.testmod.Testmod.MOD_ID;
import static me.quitd3.testmod.item.ModItemGroups.*;

public class ModItems {
    //初始化注册物品
    private static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, path));
        return Items.register(registryKey, factory, settings);}
    //注册banana物品
    public static final Item BANANA = register("banana", Item::new, new Item.Settings()
            //性质为食物，最大堆叠30个，营养值4，饱腹度0.4
            .maxCount(32)
            .food(new FoodComponent.Builder()
                    .nutrition(4)
                    .saturationModifier(0.4f)
                    .build()));
    //注册spear物品
    public static final Item SPEAR = register("spear", Spear::new, new Item.Settings()
            //性质不声明则默认为一般物品，最大堆叠1个
            .maxCount(1));

    public static void registerModItems() {
        //设定输出LOG的内容
        Testmod.LOGGER.info("Registering Mod Items for "+ MOD_ID);
        //将物品加入原版物品组
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(
                entries -> entries.add(BANANA));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(
                entries -> entries.addAfter(Items.APPLE, BANANA));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(
                entries -> entries.addBefore(Items.NETHERITE_SWORD, SPEAR));
        //将物品加入自定义物品组
        ItemGroupEvents.modifyEntriesEvent(TESTMOD_GROUP).register(
                entries -> {
                    entries.add(BANANA);
                    entries.add(SPEAR);
                    });
    }
}
