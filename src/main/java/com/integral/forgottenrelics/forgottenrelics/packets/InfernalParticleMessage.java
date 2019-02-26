package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.botania.common.Botania;

public class InfernalParticleMessage implements IMessage {
    
    private double x;
    private double y;
    private double z;
    
    private int amount;

    public InfernalParticleMessage() { }

    public InfernalParticleMessage(double x, double y, double z, int amount) {
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

    public static class Handler implements IMessageHandler<InfernalParticleMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(InfernalParticleMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            for(int i = 0; i <= message.amount; i++) {
        		float r = 0.9F + (float) Math.random() * 0.1F;
        		float g = 0.1F + (float) Math.random() * 0.15F;
        		float b = 0;
        		float s = 0.4F + (float) Math.random() * 0.4F;
        		float m = 0.5F;
        		float xm = ((float) Math.random() - 0.5F) * m;
        		float ym = ((float) Math.random() - 0.5F) * m;
        		float zm = ((float) Math.random() - 0.5F) * m;
        		
        		Botania.proxy.setWispFXDistanceLimit(false);
        		Botania.proxy.wispFX(player.worldObj, message.x, message.y, message.z, r, g, b, s, xm, ym, zm, 1.0F);
        		Botania.proxy.setWispFXDistanceLimit(true);
        	}
            
            return null;
        }
    }
    
}
