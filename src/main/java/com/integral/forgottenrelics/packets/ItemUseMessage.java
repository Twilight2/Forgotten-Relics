package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemUseMessage implements IMessage {
    
    private int duration;

    public ItemUseMessage() { }

    public ItemUseMessage(int duration) {
        this.duration = duration;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.duration = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.duration);
    }

    public static class Handler implements IMessageHandler<ItemUseMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ItemUseMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            ItemStack stack = player.getHeldItem();
            
            if (stack != null)
            player.setItemInUse(stack, message.duration);
            
            return null;
        }
    }
    
}
