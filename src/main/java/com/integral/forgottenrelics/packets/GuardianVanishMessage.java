package com.integral.forgottenrelics.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.Botania;

public class GuardianVanishMessage implements IMessage {

    public GuardianVanishMessage() { }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<GuardianVanishMessage, IMessage> {
        
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(GuardianVanishMessage message, MessageContext ctx) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            
            player.addChatMessage(new ChatComponentTranslation("message.guardian_vanish", new Object[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_PURPLE)));
            
            return null;
        }
    }
    
}
