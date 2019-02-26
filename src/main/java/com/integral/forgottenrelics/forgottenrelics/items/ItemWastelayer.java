package com.integral.forgottenrelics.items;

import java.util.List;

import com.integral.forgottenrelics.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IWarpingGear;

/**
 * We'll leave it for the better times, shall we?
 * @author Integral
 */

public class ItemWastelayer extends ItemSword implements IRepairable, IWarpingGear {
	
	public ItemWastelayer (ToolMaterial m) {
		super(m);
		this.setCreativeTab(Main.tabForgottenRelics);
		this.setTextureName("forgottenrelics:Wastelayer");
		this.setUnlocalizedName("ItemWastelayer");
	}
	
	@Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		// ???
		
		return true;
	}
	
	 @Override
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	 {
		 if(GuiScreen.isShiftKeyDown()){
			 par3List.add(StatCollector.translateToLocal("item.ItemWastelayer1.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.ItemWastelayer2.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.ItemWastelayer3.lore"));
		 }
		 else {
			 par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore")); 
			
		 }
		 //par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
	 }

	@Override
	public EnumRarity getRarity (ItemStack stack) {
		return EnumRarity.epic;
	}
	
	@Override
	public void onUpdate (ItemStack stack, World world, Entity entity, int i, boolean b) {
		
		
	}

	@Override
	public int getWarp(ItemStack arg0, EntityPlayer arg1) {
		return 10;
	}
}