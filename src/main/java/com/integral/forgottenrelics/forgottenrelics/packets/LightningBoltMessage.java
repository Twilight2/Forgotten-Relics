package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;

public class LightningBoltMessage implements IMessage {
    
    private double x;
    private double y;
    private double z;
    
    private int amount;

    public LightningBoltMessage() { }

    public LightningBoltMessage(double x, double y, double z, int amount) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        
        this.amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        
        buf.writeInt(this.amount);
    }

    public static class Handler implements IMessageHandler<LightningBoltMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(LightningBoltMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            for (int counter = message.amount; counter > 0; counter--)
            	player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, message.x,  message.y,  message.z));
            
            return null;
        }
    }
    
}
