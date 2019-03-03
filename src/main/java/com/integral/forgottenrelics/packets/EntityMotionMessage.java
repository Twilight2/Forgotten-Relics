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

public class EntityMotionMessage implements IMessage {
    
	private int entityID;
    
    private double motX;
    private double motY;
    private double motZ;
    
    private boolean motionless;

    public EntityMotionMessage() { }

    public EntityMotionMessage(int ID, double motX, double motY, double motZ, boolean stopit) {
        this.entityID = ID;
        
        this.motX = motX;
        this.motY = motY;
        this.motZ = motZ;
        
        this.motionless = stopit;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.entityID = buf.readInt();
        
        this.motX = buf.readDouble();
        this.motY = buf.readDouble();
        this.motZ = buf.readDouble();
        
        this.motionless = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(this.entityID);
        
        buf.writeDouble(this.motX);
        buf.writeDouble(this.motY);
        buf.writeDouble(this.motZ);
        
        buf.writeBoolean(this.motionless);
    }

    public static class Handler implements IMessageHandler<EntityMotionMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(EntityMotionMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            Entity statedEntity = player.worldObj.getEntityByID(message.entityID);
            
            if (statedEntity instanceof EntityLivingBase) {
            	EntityLivingBase entity = (EntityLivingBase) statedEntity;
            	
            	if (statedEntity != player) {
            	
            	//entity.posX = message.x;
            	//entity.posY = message.y;
            	//entity.posZ = message.z;
            	
            	if (message.motionless)
            		entity.fallDistance = 0.0F;
            	
            	entity.setVelocity(message.motX, message.motY, message.motZ);
            	
            	entity.velocityChanged = true;
            	
            	
            	} else {

            		if (message.motionless)
                		player.fallDistance = 0.0F;
                	
                	player.setVelocity(message.motX, message.motY, message.motZ);
            		
            	}
            	
            }
            
            return null;
        }
    }
    
}
