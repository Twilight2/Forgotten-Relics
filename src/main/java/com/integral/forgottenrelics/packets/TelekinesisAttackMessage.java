package com.integral.forgottenrelics.packets;

import com.integral.forgottenrelics.Main;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TelekinesisAttackMessage implements IMessage {
    
    private boolean doIt;

    public TelekinesisAttackMessage() { }

    public TelekinesisAttackMessage(boolean doIt) {
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

    public static class Handler implements IMessageHandler<TelekinesisAttackMessage, IMessage> {
        
        //@SideOnly(Side.SERVER)
        public IMessage onMessage(TelekinesisAttackMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack == null)
            	return null;
            
            Item item = stack.getItem();
            
            if (message.doIt & item == Main.itemTelekinesisTome) {
            	Main.itemTelekinesisTome.leftClick(player);
            }
            
            return null;
        }
    }
    
}
