package com.integral.forgottenrelics.items;

import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.packets.BurstMessage;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.research.ScanResult;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ScanManager;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;

public class ItemGhastlySkull extends Item implements IWarpingGear {
	
 public ItemGhastlySkull() {

	 this.maxStackSize = 1;
	 this.setUnlocalizedName("ItemGhastlySkull");
	 this.setCreativeTab(Main.tabForgottenRelics);

 }


 @Override
 public void registerIcons(IIconRegister iconRegister)
 {
 itemIcon = iconRegister.registerIcon("forgottenrelics:Ghastly_Skull");
 }


 @Override
 @SideOnly(Side.CLIENT)
 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
 {
	 if(GuiScreen.isShiftKeyDown()){
		 par3List.add(StatCollector.translateToLocal("item.ItemGhastlySkull1.lore")); 
		 par3List.add(StatCollector.translateToLocal("item.ItemGhastlySkull2.lore")); 
	 }
	 else {
		 par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore")); 
		
	 }
	 
	 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore")); 
 }
 
 	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setHealth(1);
		
		Vector3 vec = Vector3.fromEntityCenter(player);
		
		//if (world.isRemote)
		//PlayerNotifications.addNotification(StatCollector.translateToLocal("notification.soul_gain") + " " + 20.0F);
		
		if (!world.isRemote) {
			Main.packetInstance.sendToAllAround(new BurstMessage(vec.x, vec.y, vec.z, 1.0F), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 128.0D));
			Main.castingCooldowns.put(player, 20);
			//player.attackEntityFrom(new DamageRegistryHandler.DamageSourceMagic(player), 100.0F);
		}
 		return stack;
 		
 		/*
 		System.out.println("Research list: " + ResearchCategories.researchCategories);
 		System.out.println("Researches in BASICS category: " + ResearchCategories.getResearchList("BASICS").research);
 		System.out.println("Researches in THAUMATURGY category: " + ResearchCategories.getResearchList("THAUMATURGY").research);
 		System.out.println("Researches in ALCHEMY category: " + ResearchCategories.getResearchList("ALCHEMY").research);
 		System.out.println("Researches in ARTIFICE category: " + ResearchCategories.getResearchList("ARTIFICE").research);
 		System.out.println("Researches in GOLEMANCY category: " + ResearchCategories.getResearchList("GOLEMANCY").research);
 		System.out.println("Researches in BASICS category: " + ResearchCategories.getResearchList("ELDRITCH").research);
		System.out.println("Lul: " + player.inventory.getStackInSlot(0));
		return stack;
		*/
	}


 @Override
 public EnumRarity getRarity(ItemStack itemStack)
 {
 return EnumRarity.epic;
 }

@Override
public int getWarp(ItemStack arg0, EntityPlayer arg1) {
	
	return 3;
}
 
 
}
