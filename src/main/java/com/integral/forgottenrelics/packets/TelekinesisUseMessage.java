package com.integral.forgottenrelics.packets;

import com.integral.forgottenrelics.Main;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TelekinesisUseMessage implements IMessage {

    public TelekinesisUseMessage() { }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<TelekinesisUseMessage, IMessage> {
        
        //@SideOnly(Side.SERVER)
        public IMessage onMessage(TelekinesisUseMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack stack = player.getCurrentEquippedItem();
            
            if (stack != null)
            if (stack.getItem() == Main.itemTelekinesisTome) {
            	Main.itemTelekinesisTome.onUsingTickAlt(stack, player, 0);
            }
           
            
            return null;
        }
    }
    
}
