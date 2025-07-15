package me.quitd3.testmod.item;

import me.quitd3.testmod.Testmod;
import static me.quitd3.testmod.Testmod.MOD_ID;
import static me.quitd3.testmod.item.ModItems.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    //声明自定义物品组的KEY
    public static final RegistryKey<ItemGroup> TESTMOD_GROUP = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MOD_ID));

    public static void registerModItemGroups() {
        //设定输出LOG的内容
        Testmod.LOGGER.info("Registering Mod Item Groups for " + MOD_ID);

        //初始化自定义物品组
        Registry.register(Registries.ITEM_GROUP, TESTMOD_GROUP, FabricItemGroup.builder()
                        .icon(() -> new ItemStack(Items.ENCHANTED_GOLDEN_APPLE))
                        .displayName(Text.translatable("itemGroup." + MOD_ID + ".testmod_group"))
                        .build());
    }
}
