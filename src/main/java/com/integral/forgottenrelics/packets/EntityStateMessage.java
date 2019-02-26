package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityStateMessage implements IMessage {
    
	private int entityID;
	
    private double x;
    private double y;
    private double z;
    
    private float rotationYaw;
    private float rotationPitch;
    private float rotationYawHead;
    
    private boolean motionless;

    public EntityStateMessage() { }

    public EntityStateMessage(int ID, double x, double y, double z, float yaw, float pitch, float yawHead, boolean stopit) {
        this.entityID = ID;
    	
    	this.x = x;
        this.y = y;
        this.z = z;
        
        this.rotationYaw = yaw;
        this.rotationPitch = pitch;
        this.rotationYawHead = yawHead;
        
        this.motionless = stopit;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.entityID = buf.readInt();
    	
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        
        this.rotationYaw = buf.readFloat();
        this.rotationPitch = buf.readFloat();
        this.rotationYawHead = buf.readFloat();
        
        this.motionless = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(this.entityID);
    	
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        
        buf.writeFloat(this.rotationYaw);
        buf.writeFloat(this.rotationPitch);
        buf.writeFloat(this.rotationYawHead);
        
        buf.writeBoolean(this.motionless);
    }

    public static class Handler implements IMessageHandler<EntityStateMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(EntityStateMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            //System.out.println("Message received!");
            
            Entity statedEntity = player.worldObj.getEntityByID(message.entityID);
            
            //System.out.println("Message received: " + statedEntity);
            
            if (statedEntity instanceof EntityLivingBase) {
            	EntityLivingBase entity = (EntityLivingBase) statedEntity;
            	
            	if (statedEntity != player) {
            	
            	entity.posX = message.x;
            	entity.posY = message.y;
            	entity.posZ = message.z;
            	
            	entity.rotationYaw = message.rotationYaw;
            	entity.rotationPitch = message.rotationPitch;
            	entity.rotationYawHead = message.rotationYawHead;
            	
            	entity.setPositionAndRotation(message.x, message.y, message.z, message.rotationYaw, message.rotationPitch);
            	entity.setRotationYawHead(message.rotationYawHead);
            	
            	if (message.motionless) {
            		double y = entity.motionY;
            		
            		if (entity.motionY > 0)
            			y = 0D;
            			
            		entity.setVelocity(0D, y, 0D);
            	}
            	
            	entity.velocityChanged = true;
            	
            	} else {
            		// DO NOTHING
            	}
            	
            }
            
            return null;
        }
    }
    
}
