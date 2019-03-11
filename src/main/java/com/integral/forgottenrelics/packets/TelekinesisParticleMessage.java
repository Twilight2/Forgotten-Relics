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

public class TelekinesisParticleMessage implements IMessage {
    
    private double x;
    private double y;
    private double z;
    
    private float modifier;

    public TelekinesisParticleMessage() { }

    public TelekinesisParticleMessage(double x, double y, double z, float modifier) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.modifier = modifier;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        
        this.modifier = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        
        buf.writeFloat(this.modifier);
    }

    public static class Handler implements IMessageHandler<TelekinesisParticleMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(TelekinesisParticleMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            int wisps = (int) (1.0*message.modifier);
            int supers = (int) (3.0*message.modifier);
            
            for(int i = 0; i <= wisps; i++) {
				float r = 0.2F + (float) Math.random() * 0.3F;
				float g = 0.0F;
				float b = 0.5F + (float) Math.random() * 0.2F;
				float s = 0.2F + (float) Math.random() * 0.1F;
				float m = 0.15F;
				float xm = ((float) Math.random() - 0.5F) * m;
				float ym = ((float) Math.random() - 0.5F) * m;
				float zm = ((float) Math.random() - 0.5F) * m;
				
				
				Botania.proxy.wispFX(player.worldObj, message.x, message.y, message.z, r, g, b, s, xm, ym, zm);
			}
			
			
			for (int counter = 0; counter <= supers; counter ++)
				Main.proxy.spawnSuperParticle(player.worldObj, "portalstuff", message.x, message.y, message.z, (Math.random() - 0.5D)*3.0D, (Math.random() - 0.5D)*3.0D, (Math.random() - 0.5D)*3.0D, 1.0F, 64);
            
            return null;
        }
    }
    
}
