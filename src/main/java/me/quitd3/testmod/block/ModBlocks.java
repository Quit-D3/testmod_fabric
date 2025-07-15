package me.quitd3.testmod.block;

import me.quitd3.testmod.Testmod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import java.util.function.Function;
import static me.quitd3.testmod.Testmod.MOD_ID;
import static me.quitd3.testmod.item.ModItemGroups.TESTMOD_GROUP;

public class ModBlocks {
    //初始化注册方块
    public static Block registerBlock(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }
    //注册TEST_BLOCK
    public static final Block JUICE = registerBlock("juice", Block::new, Block.Settings.create()
            .strength(4.0f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE));

    public static void registerModBlocks() {
        //设定输出LOG的内容
        Testmod.LOGGER.info("Registering Mod Blocks for " + MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(TESTMOD_GROUP).register(entries ->
                entries.add(ModBlocks.JUICE));
    }
}
