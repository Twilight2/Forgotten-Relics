package com.integral.forgottenrelics.packets;

import java.util.ArrayList;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.lib.ClientTickEventsFML;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;

public class ForgottenResearchMessage implements IMessage {
    
    private String researchKey;

    public ForgottenResearchMessage() { }

    public ForgottenResearchMessage(String key) {
        this.researchKey = key;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.researchKey = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	ByteBufUtils.writeUTF8String(buf, this.researchKey);
    }

    public static class Handler implements IMessageHandler<ForgottenResearchMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(ForgottenResearchMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            if (message.researchKey != null && message.researchKey.length() > 0) {
                Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)Minecraft.getMinecraft().thePlayer, message.researchKey);
                
                if (message.researchKey.startsWith("@")) {
                    PlayerNotifications.addNotification(StatCollector.translateToLocal("notification.research_triggered"));
                    Minecraft.getMinecraft().thePlayer.playSound("thaumcraft:learn", 0.2f, 1.0f + Minecraft.getMinecraft().thePlayer.worldObj.rand.nextFloat() * 0.1f);
                }

                if (Minecraft.getMinecraft().currentScreen instanceof GuiResearchBrowser) {
                    ArrayList<String> al = GuiResearchBrowser.completedResearch.get(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
                    if (al == null) {
                        al = new ArrayList<String>();
                    }
                    al.add(message.researchKey);
                    GuiResearchBrowser.completedResearch.put(Minecraft.getMinecraft().thePlayer.getCommandSenderName(), al);
                    ((GuiResearchBrowser)Minecraft.getMinecraft().currentScreen).updateResearch();
                }
            }
            
            return null;
        }
    }
    
}
