package com.integral.forgottenrelics.packets;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DiscordKeybindMessage implements IMessage {
    
    private boolean doIt;

    public DiscordKeybindMessage() { }

    public DiscordKeybindMessage(boolean doIt) {
        this.doIt = doIt;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.doIt = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.doIt);
    }

    public static class Handler implements IMessageHandler<DiscordKeybindMessage, IMessage> {
        
        //@SideOnly(Side.SERVER)
        public IMessage onMessage(DiscordKeybindMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack stack = SuperpositionHandler.findFirst(player, Main.itemTeleportationTome);
            
            if (message.doIt & stack != null & SuperpositionHandler.hasBauble(player, Main.itemDiscordRing)) {
            	Main.itemTeleportationTome.onItemRightClick(stack, player.worldObj, player);
            }
            
            return null;
        }
    }
    
}
