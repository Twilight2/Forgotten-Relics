package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class ICanSwingMySwordMessage implements IMessage {
    
    private boolean swingTheSword;

    public ICanSwingMySwordMessage() { }

    public ICanSwingMySwordMessage(boolean swingTheSword) {
        this.swingTheSword = swingTheSword;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.swingTheSword = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.swingTheSword);
    }

    public static class Handler implements IMessageHandler<ICanSwingMySwordMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ICanSwingMySwordMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            if (message.swingTheSword)
            	player.swingItem();
            
            return null;
        }
    }
    
}
