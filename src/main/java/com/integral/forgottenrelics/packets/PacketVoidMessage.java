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
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;

public class PacketVoidMessage implements IMessage {
    
    private double x;
    private double y;
    private double z;
    
    private boolean finish;

    public PacketVoidMessage() { }

    public PacketVoidMessage(double x, double y, double z, boolean finish) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.finish = finish;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        
        this.finish = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        
        buf.writeBoolean(this.finish);
    }

    public static class Handler implements IMessageHandler<PacketVoidMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketVoidMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            Vector3 thisPos = new Vector3(message.x, message.y, message.z);
            
            if (!message.finish) {
            
            for (int counter = 0; counter < 8; counter++) {
    			
   			 double calculatedPositionX = thisPos.x + ((Math.random() - 0.5D) * 12.0D);
   			 double calculatedPositionY = thisPos.y + ((Math.random() - 0.5D) * 12.0D);
   			 double calculatedPositionZ = thisPos.z + ((Math.random() - 0.5D) * 12.0D);
   			
   			 Vector3 targetPos = new Vector3(calculatedPositionX, calculatedPositionY, calculatedPositionZ);
   			 Vector3 diff = thisPos.copy().sub(targetPos);
   			 
   			 diff.multiply(0.08F);
   			 
   			 float calculatedMotionX = (float) diff.x;
   			 float calculatedMotionY = (float) diff.y;
   			 float calculatedMotionZ = (float) diff.z;
   			 
   			 float r = 0.2F + (float) Math.random() * 0.3F;
   			 float g = 0.0F;
   			 float b = 0.5F + (float) Math.random() * 0.2F;
   			 
   			 float s = 0.2F + (float) Math.random() * 0.2F;
   	
   			 Botania.proxy.wispFX(player.worldObj, calculatedPositionX, calculatedPositionY, calculatedPositionZ, r, g, b, s, calculatedMotionX, calculatedMotionY, calculatedMotionZ, 0.45F);
   			 }
           
            for (int counter = 0; counter < 5; counter++)
            	Main.proxy.spawnSuperParticle(player.worldObj, "portalstuff", thisPos.x, thisPos.y, thisPos.z, ((Math.random() - 0.5D) * 8.0D), ((Math.random() - 0.5D) * 8.0D), ((Math.random() - 0.5D) * 8.0D), 1.0F, 64.0F);
            } else {
            	
            	for(int i = 0; i <= 128; i++) {
            		float r = 0.2F + (float) Math.random() * 0.3F;
            		float g = 0.0F;
            		float b = 0.5F + (float) Math.random() * 0.2F;
            		float s = 0.4F + (float) Math.random() * 0.4F;
            		float m = 0.5F;
            		float xm = ((float) Math.random() - 0.5F) * m;
            		float ym = ((float) Math.random() - 0.5F) * m;
            		float zm = ((float) Math.random() - 0.5F) * m;
            		
            		Botania.proxy.setWispFXDistanceLimit(false);
            		Botania.proxy.wispFX(player.worldObj, thisPos.x, thisPos.y, thisPos.z, r, g, b, s, xm, ym, zm, 1.0F);
            		Botania.proxy.setWispFXDistanceLimit(true);
            	}
            	
            }
            return null;
        }
    }
    
}
