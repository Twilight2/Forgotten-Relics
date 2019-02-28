package com.integral.forgottenrelics.handlers;

import org.lwjgl.input.Keyboard;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.packets.DiscordKeybindMessage;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;

public class RelicsKeybindHandler {
	
	@SideOnly(Side.CLIENT)
	public static boolean checkVariable;
	
	public static KeyBinding discordRingKey;
	
	@SideOnly(Side.CLIENT)
	public static void registerKeybinds() {
		discordRingKey = new KeyBinding("key.discordRing", Keyboard.KEY_X, "key.categories.forgottenrelics");
		
		ClientRegistry.registerKeyBinding(discordRingKey);
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (this.discordRingKey.isPressed() & checkVariable == false) {
			Main.packetInstance.sendToServer(new DiscordKeybindMessage(true));
			checkVariable = true;
		} else if (!this.discordRingKey.isPressed() & checkVariable == true) {
			checkVariable = false;
		}
		
	}
	
	/*
	@SubscribeEvent
    public void onKeyInputLul(InputEvent.KeyInputEvent event) {
		//System.out.println("HEY BOY: " + event.getPhase());
		
		if (this.discordRingKey.isPressed()) {
			//Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Hello there! You've just pressed: " + Keyboard.getKeyName(discordRingKey.getKeyCode())));
			Main.packetInstance.sendToServer(new DiscordKeybindMessage(true));
		}
		
    }
	*/
}
