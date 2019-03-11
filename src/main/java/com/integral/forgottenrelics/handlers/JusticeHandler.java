package com.integral.forgottenrelics.handlers;

import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.packets.ForgottenResearchMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;

/**
 * Designed to reveal forgotten knowledge to player.
 * Due to possibly huge amount of checks, and since
 * I have no idea how well optimized Thaumcraft's
 * methods are, it was made as asynchronous
 * handler (might be useful someday).
 * 
 * It scans for all researches classified as forgotten knowledge,
 * and either reveals key to one of them to player (if they have
 * all required items scanned) or does nothing if no mathing
 * research exist at the moment.
 * 
 * @author Integral
 */

public class JusticeHandler extends Thread {
	
	public JusticeHandler(final EntityPlayer player) {
		this.player = player;
	}
	
	EntityPlayer player;
	
	@Override
	public void run() {
		
		try {
		
		if (player.worldObj.isRemote)
			return;
		
		for (String research : Main.forgottenKnowledge.keySet()) {
			ResearchItem researchItem = ResearchCategories.getResearch(research);
			
			if (researchItem == null)
				continue;
			if (!researchItem.isHidden() & !researchItem.isLost())
				continue;
				
			boolean clueUnlocked = ResearchManager.isResearchComplete(player.getDisplayName(), "@" + research);
			boolean researchCompleted = ResearchManager.isResearchComplete(player.getDisplayName(), research);
			
			if (!clueUnlocked & !researchCompleted) {
				List<ItemStack> triggerList = Main.forgottenKnowledge.get(research);
				boolean shouldBeUnlocked = true;
				
				for (ItemStack stack : triggerList) {
					ScanResult testItem = new ScanResult((byte) 1, stack.getItem().getIdFromItem(stack.getItem()), stack.getItemDamage(), player, "UndefinedPhenomena");
					if (ScanManager.hasBeenScanned(player, testItem)) {
						continue;
					} else {
						shouldBeUnlocked = false;
						break;
					}
				
				}
				
				if (shouldBeUnlocked) {
					//System.out.println("Brought justice: " + research);
					Main.packetInstance.sendTo(new ForgottenResearchMessage("@" + research), (EntityPlayerMP) player);
		            Thaumcraft.proxy.getResearchManager().completeResearch(player, "@" + research);
		            return;
				}
				
			}
			
		}
		
		} catch (Exception ex) {
			Main.log.error("Something has gone wrong while executing Justice Handler!");
		}
	}
	
	
}
