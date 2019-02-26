package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

public class OverthrowChatMessage implements IMessage {
    
    private String overthrownPlayer;
    private String overthrower;

    public OverthrowChatMessage() { }

    public OverthrowChatMessage(String overthrownPlayer, String overthrower) {
        this.overthrownPlayer = overthrownPlayer;
        this.overthrower = overthrower;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.overthrownPlayer = ByteBufUtils.readUTF8String(buf);
        this.overthrower = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.overthrownPlayer);
        ByteBufUtils.writeUTF8String(buf, this.overthrower);
    }

    public static class Handler implements IMessageHandler<OverthrowChatMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(OverthrowChatMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            String overthrown = message.overthrownPlayer;
            String overthrower = message.overthrower;
            
            player.addChatMessage(new ChatComponentText(overthrower + " " + StatCollector.translateToLocal("message.overthrown1") + " " + overthrown + " " + StatCollector.translateToLocal("message.overthrown2")));
            
            return null;
        }
    }
    
}
