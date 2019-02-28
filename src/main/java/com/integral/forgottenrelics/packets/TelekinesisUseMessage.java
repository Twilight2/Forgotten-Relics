package com.integral.forgottenrelics.packets;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
