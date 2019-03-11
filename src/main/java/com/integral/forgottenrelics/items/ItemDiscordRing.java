package com.integral.forgottenrelics.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.RelicsKeybindHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemDiscordRing extends ItemBaubleBase implements IBauble {

 public ItemDiscordRing() {
	 super("ItemDiscordRing");
	 this.maxStackSize = 1;
	 this.setUnlocalizedName("ItemDiscordRing");
	 this.setCreativeTab(Main.tabForgottenRelics);

 }


 @Override
 public void registerIcons(IIconRegister iconRegister)
 {
 itemIcon = iconRegister.registerIcon("forgottenrelics:Discord_Ring");
 }


 @Override
 @SideOnly(Side.CLIENT)
 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
 {
	 String keyName = "???";
	 
	 try {
		 keyName = Keyboard.getKeyName(RelicsKeybindHandler.discordRingKey.getKeyCode());
	 } catch (Exception ex) {}
	 
	 if(GuiScreen.isShiftKeyDown()){
		 par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing1.lore")); 
		 par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing2.lore"));
		 par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing3.lore"));
		 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
		 par3List.add(StatCollector.translateToLocal("item.ItemDiscordRing4.lore") + " " + StatCollector.translateToLocal("item.FRCode6.lore") + keyName);
		 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
		 par3List.add(SuperpositionHandler.getBaubleTooltip(this.getBaubleType(par1ItemStack)));
	 }
	 else {
		 par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore")); 
		
	 }
 }


 @Override
 public EnumRarity getRarity(ItemStack itemStack) {
	 return EnumRarity.epic;
 }

@Override
public BaubleType getBaubleType(ItemStack arg0) {
	return BaubleType.RING;
}

@Override
public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
	super.onWornTick(itemstack, entity);
}
 
 
}
