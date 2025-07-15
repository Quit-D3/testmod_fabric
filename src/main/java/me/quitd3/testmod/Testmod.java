package me.quitd3.testmod;

import me.quitd3.testmod.block.ModBlocks;
import me.quitd3.testmod.item.ModItemGroups;
import me.quitd3.testmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Testmod implements ModInitializer {

    //设定"MOD_ID"指向testmod字符串
    public static final String MOD_ID = "testmod";
    //设定输出LOG的MOD名字
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        //设定输出LOG的内容
        LOGGER.info("Hello Fabric world!");
        //注册ModItems
        ModItems.registerModItems();
        //注册ModBlocks
        ModBlocks.registerModBlocks();
        //注册ModItemGroups
        ModItemGroups.registerModItemGroups();











    }
}