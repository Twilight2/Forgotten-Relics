package com.integral.forgottenrelics.packets;

import com.integral.forgottenrelics.Main;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import thaumcraft.client.lib.PlayerNotifications;

public class NotificationMessage implements IMessage {
    
    private int type;

    public NotificationMessage() { }

    public NotificationMessage(int type) {
        this.type = type;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.type);
    }

    public static class Handler implements IMessageHandler<NotificationMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(NotificationMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            String notification;
            
            switch (message.type) {
            
            case 1: notification = StatCollector.translateToLocal("notification.fate_cooldown_over");
            	break;
            	
            case 2: notification = StatCollector.translateToLocal("notification.overdamage_block");
            	break;
            	
            default:
            	Main.log.error("Recived invalid notification!");
            	return null;
            
            }
            
            PlayerNotifications.addNotification(notification);
            
            return null;
        }
    }
    
}
