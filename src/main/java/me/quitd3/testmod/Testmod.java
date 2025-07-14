package me.quitd3.testmod;

import me.quitd3.testmod.items.Spear;
import net.fabricmc.api.ModInitializer;
//import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Function;


public class Testmod implements ModInitializer {

    //设定输出LOG的MOD名字
    public static final Logger LOGGER = LoggerFactory.getLogger("Testmod");

    //注册物品在游戏内ID为testmod:____
    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("testmod", path));
        return Items.register(registryKey, factory, settings);}
    //注册banana物品
    public static final Item BANANA = register("banana", Item::new, new Item.Settings()
            //性质为食物，最大堆叠30个，营养值4，饱腹度0.4
            .maxCount(30).food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.4f).build()));
    //注册spear物品
    public static final Item SPEAR = register("spear", Spear::new, new Item.Settings()
            //性质不声明则默认为一般物品，最大堆叠1个
            .maxCount(1));

    //以RegistryKey的方式注册物品组，可被onInitialize()调用
//    public static final RegistryKey<ItemGroup> TESTMOD_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("testmod", "testmod_group"));
    //以ItemGroup的方式注册物品组，不可被onInitialize()调用
    public static final ItemGroup TESTMOD_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of("testmod", "testmod_group"),
            //调用FabricItemGroup.builder()方法创建物品组，并设置图标、显示名称、直接用entries方法添加物品
            FabricItemGroup.builder()
                    //图标为Items.APPLE即minecraft:apple
                    .icon(() -> new ItemStack(Items.APPLE))
                    //显示名称为"Hey"
                    .displayName(Text.of("Hey"))
                    //调用entries方法直接添加物品
                    .entries(((displayContext, entries) -> {
                        entries.add(new ItemStack(Testmod.BANANA));
                        entries.add(new ItemStack(Testmod.SPEAR));
                    }))
            .build());

    @Override
    public void onInitialize() {

        //设定输出LOG的内容
        LOGGER.info("Hello Fabric world!");

        //新版物品描述写法，暂未实验成功
//        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
//            if (!itemStack.isOf(Testmod.SPEAR)) {
//                return;
//            }
//            list.add(Text.translatable("item.testmod.spear.tooltip Hello Fabric World!"));
//        });

        //将BANANA物品添加到物品组Items.NATURAL的末尾//add()方法默认向后排序
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(
                entries -> {
            entries.add(BANANA);
        });
        //将BANANA物品添加到物品组Items.FOOD_AND_DRINK中的Items.APPLE之后//addAfter(A, B)方法，A已在物品组中，将B添加到A之后
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(
                entries -> {
            entries.addAfter(Items.APPLE, BANANA);
        });
        //将SPEAR物品添加到物品组Items.COMBAT中的Items.NETHERITE_SWORD之前//addBefore(A，B)方法，A已在物品组中，将B添加到A之前
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(
                entries -> {
            entries.addBefore(Items.NETHERITE_SWORD, SPEAR);
        });

        //初始化物品组TESTMOD_GROUP_KEY，注册到Registries.ITEM_GROUP中，需要以RegistryKey方式注册物品组
//        Registry.register(
//                Registries.ITEM_GROUP, TESTMOD_GROUP_KEY,
//                FabricItemGroup.builder()
//                        .icon(() -> new ItemStack(Items.APPLE))
//                        .displayName(Text.of("Hey"))
//                        .entries(((displayContext, entries) -> {
//                            entries.add(new ItemStack(Testmod.BANANA));
//                            entries.add(new ItemStack(Testmod.SPEAR));
//                        }))
//                        .build()
//        );

        //将BANANA和SPEAR物品添加到物品组TESTMOD_GROUP中，排序按先后顺序，需要调用RegistryKey
//        ItemGroupEvents.modifyEntriesEvent(TESTMOD_GROUP_KEY).register(
//                entries -> {
//            entries.add(BANANA);
//            entries.add(SPEAR);
//        });









    }
}