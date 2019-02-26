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
import vazkii.botania.common.core.helper.Vector3;

public class BanishmentCastingMessage implements IMessage {
    
    private double x;
    private double y;
    private double z;
    
    private int amount;

    public BanishmentCastingMessage() { }

    public BanishmentCastingMessage(double x, double y, double z, int amount) {
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

    public static class Handler implements IMessageHandler<BanishmentCastingMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(BanishmentCastingMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            Vector3 thisPos = new Vector3(message.x, message.y, message.z);
            
            for (int counter = 0; counter < message.amount; counter++) {
    			
   			 double calculatedPositionX = message.x + ((Math.random() - 0.5D) * 8.0D);
   			 double calculatedPositionY = message.y + ((Math.random() - 0.5D) * 8.0D);
   			 double calculatedPositionZ = message.z + ((Math.random() - 0.5D) * 8.0D);
   			
   			 Vector3 targetPos = new Vector3(calculatedPositionX, calculatedPositionY, calculatedPositionZ);
   			 Vector3 diff = thisPos.copy().sub(targetPos);
   			 
   			 diff.multiply(0.08F);
   			 
   			 float calculatedMotionX = (float) diff.x;
   			 float calculatedMotionY = (float) diff.y;
   			 float calculatedMotionZ = (float) diff.z;
   			 
   			 float r = 0.9F + (float) Math.random() * 0.1F;
   			 float g = 0.1F + (float) Math.random() * 0.15F;
   			 float b = 0;
   			 
   			 float s = 0.2F + (float) Math.random() * 0.2F;
   			 
   			 Botania.proxy.wispFX(player.worldObj, calculatedPositionX, calculatedPositionY, calculatedPositionZ, r, g, b, s, calculatedMotionX, calculatedMotionY, calculatedMotionZ, 0.5F);
   			 }
            
            return null;
        }
    }
    
}
