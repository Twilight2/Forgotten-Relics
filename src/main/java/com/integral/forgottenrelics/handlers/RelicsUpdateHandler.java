package com.integral.forgottenrelics.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import com.integral.forgottenrelics.Main;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * Some code was borrowed from Tainted Magic update handler.
 * Hope that's not illegal.
 * @author Integral
 */

public class RelicsUpdateHandler {
	private static String currentVersion = Main.VERSION + " " + Main.RELEASE_TYPE;
	private static String newestVersion;
	public static String updateStatus = null;
	public static boolean show = false;
	static boolean worked = false;
	
	@SubscribeEvent
	public void onPlayerLogin(EntityJoinWorldEvent event) {
		if (!(event.entity instanceof EntityPlayer))
			return;
		
		if (!event.entity.worldObj.isRemote || !this.show)
			return;
		
		EntityPlayer player = (EntityPlayer) event.entity;
		
		if (this.show) {
			player.addChatMessage(new ChatComponentText(RelicsUpdateHandler.updateStatus));
			this.show = false;
		}
		
	}

	public static void init() {
		if (!RelicsConfigHandler.updateNotificationsEnabled) {
			show = false;
			return;
		}
		
		getNewestVersion();

		if (newestVersion != null)
		{
			if (newestVersion.equalsIgnoreCase(currentVersion))
			{
				show = false;
			}
			else if (!newestVersion.equalsIgnoreCase(currentVersion))
			{
				show = true;
				updateStatus = EnumChatFormatting.DARK_PURPLE + String.format(StatCollector.translateToLocal("status.outdated"), newestVersion);
			}
		}
		else
		{
			show = true;
			updateStatus = StatCollector.translateToLocal("status.noconnection");
		}
	}

	private static void getNewestVersion() {
		try
		{
			URL url = new URL("https://raw.githubusercontent.com/Extegral/Forgotten-Relics/master/version.txt");
			Scanner s = new Scanner(url.openStream());
			newestVersion = s.nextLine();
			s.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}