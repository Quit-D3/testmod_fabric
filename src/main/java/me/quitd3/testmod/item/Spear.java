package me.quitd3.testmod.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

//定义Spear继承Item类
public class Spear extends Item {

    //声明构造Spear传入参数Settings类型的参数settings，并super调用父类Item的构造方法
    public Spear(Settings settings) {super(settings);}

    //覆写，使用旧appendTooltip方法，添加物品的自定义的提示信息
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        //添加自定义的提示信息"____"
        textConsumer.accept(Text.of("This is a spear Hello Fabric World!"));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }

    //覆写，调用ActionResult的use方法，实现用户对物品操作时得到的响应
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        //在主手使用时
        if (hand == Hand.MAIN_HAND) {
            //播放音效，发送信息
            user.playSound(SoundEvents.ENTITY_WITHER_SHOOT, 1.0f, 1.0f);
            user.sendMessage(Text.of("You speared someone!"), false);
        }
        //在副手使用时
        else {
            //播放音效，发送信息，设置玩家死亡
            user.playSound(SoundEvents.ENTITY_ENDER_PEARL_THROW, 1.0f, 1.0f);
            user.sendMessage(Text.of("You speared yourself!"), true);
            user.setHealth(0);
        }
        //返回成功
        return ActionResult.SUCCESS;
    }
}