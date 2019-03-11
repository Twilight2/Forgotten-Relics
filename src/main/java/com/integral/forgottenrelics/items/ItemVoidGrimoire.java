package com.integral.forgottenrelics.items;

import java.util.HashMap;
import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;
import com.integral.forgottenrelics.packets.EntityMotionMessage;
import com.integral.forgottenrelics.packets.OverthrowChatMessage;
import com.integral.forgottenrelics.packets.PacketVoidMessage;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.utils.EntityUtils;
import vazkii.botania.common.core.helper.Vector3;

/**
 * ???
 * @author Integral
 */

public class ItemVoidGrimoire extends Item implements IWarpingGear {
	
	 public static final int AerCost = (int) (0 * RelicsConfigHandler.voidGrimoireVisMult);
	 public static final int TerraCost = (int) (0 * RelicsConfigHandler.voidGrimoireVisMult);
	 public static final int IgnisCost = (int) (0 * RelicsConfigHandler.voidGrimoireVisMult);
	 public static final int AquaCost = (int) (0 * RelicsConfigHandler.voidGrimoireVisMult);
	 public static final int OrdoCost = (int) (9 * RelicsConfigHandler.voidGrimoireVisMult);
	 public static final int PerditioCost = (int) (16 * RelicsConfigHandler.voidGrimoireVisMult);
	 
	 static int localCooldown = 0;
	 
	 static HashMap<EntityPlayer, EntityLivingBase> targetList = new HashMap<EntityPlayer, EntityLivingBase>();

	 public ItemVoidGrimoire() {
		setMaxStackSize(1);
		setUnlocalizedName("ItemVoidGrimoire");
		setCreativeTab(Main.tabForgottenRelics);
	 }
	
	 @Override
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		 
		 if(GuiScreen.isShiftKeyDown()){
			 par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire1.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire2.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire3.lore"));
			 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire4.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire5.lore"));
			 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemVoidGrimoire6.lore"));
		 } else if (GuiScreen.isCtrlKeyDown()) {
				par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
			 	par3List.add(" " + StatCollector.translateToLocal("item.FROrdoCost.lore") + (this.OrdoCost/100.0D));
			 	par3List.add(" " + StatCollector.translateToLocal("item.FRPerditioCost.lore") + (this.PerditioCost/100.0D));
			 }
		 else {
			 par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
		 }
		 
		 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));

	 }


	 @Override
	 public EnumRarity getRarity(ItemStack itemStack)
	 {
		 return EnumRarity.epic;
	 }
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("forgottenrelics:Void_Grimoire");
	}
	 
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		 return 100;
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
		
		if (world.isRemote)
			if (this.localCooldown > 0)
				this.localCooldown--;
		
	}
	
	public void overthrow(EntityLivingBase entity, EntityPlayer overthrower) {
		
		if (overthrower.worldObj.isRemote) {
			entity.setDead();
			return;
		}
		
		double x = (Math.random()-0.5D) * 20002;
		double z = (Math.random()-0.5D) * 20002;
		double y = (-100000) + ((Math.random()-0.5D) * 20002);
		
		entity.setPositionAndUpdate(x, y, z);
		
		if (!(entity instanceof EntityPlayer)) {
			entity.setDead();
		} else {
			if (!overthrower.worldObj.isRemote)
				Main.packetInstance.sendToAll(new OverthrowChatMessage(((EntityPlayer) entity).getDisplayName(), overthrower.getDisplayName(), 1));
		}
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
	if (world.isRemote & count != 1) {
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
		}
		
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if (!WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.ORDER, this.OrdoCost).add(Aspect.ENTROPY, this.PerditioCost))) {
			player.stopUsingItem();
			return;
		}
		
		boolean UltimaSide;
		
		try {
		if (MinecraftServer.getServer().isDedicatedServer())
			UltimaSide = true;
		else
			UltimaSide = false;
		} catch (NullPointerException ex) {
			UltimaSide = false;
		}
		
		
		if (!this.targetList.containsKey(player)) {
			this.targetList.put(player, null);
			player.stopUsingItem();
			return;
		}
		
		EntityLivingBase target = this.targetList.get(player);
		
		if (target != null) {
		target.fallDistance = 0F;
		
		try {
			target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 100, true));
		} catch (Exception ex) {}
		
		if (UltimaSide) {
		
		target.motionY = 0.09D;
		target.velocityChanged = true;
		
		if (!player.worldObj.isRemote)
			Main.packetInstance.sendToAllAround(new EntityMotionMessage(target.getEntityId(), target.motionX, target.motionY, target.motionZ, true), new TargetPoint(player.dimension, target.posX, target.posY, target.posZ, 64.0D));
		
		} else {
			target.motionY = 0.03D;
			target.velocityChanged = true;
			
			if (!player.worldObj.isRemote)
				Main.packetInstance.sendToAllAround(new EntityMotionMessage(target.getEntityId(), target.motionX, target.motionY, target.motionZ, true), new TargetPoint(player.dimension, target.posX, target.posY, target.posZ, 64.0D));
			
		}
		
		
		Vector3 thisPos = Vector3.fromEntityCenter(target);
		thisPos.y += 0.03D;
		
		if (!player.worldObj.isRemote & count == stack.getMaxItemUseDuration()) {
			 player.worldObj.playSoundEffect(thisPos.x, thisPos.y, thisPos.z, "forgottenrelics:sound.mdcharge", 4.0F, 0.75F);
		}
		
		if (!player.worldObj.isRemote)
			Main.packetInstance.sendToAllAround(new PacketVoidMessage(thisPos.x, thisPos.y, thisPos.z, false), new TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 64.0D));
        
        if (count == 1) {
        	
        	if (!player.worldObj.isRemote) {
        		SuperpositionHandler.imposeBurst(target.worldObj, target.dimension, thisPos.x, thisPos.y, thisPos.z, 2.0F);
        		Main.packetInstance.sendToAllAround(new PacketVoidMessage(thisPos.x, thisPos.y, thisPos.z, true), new TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 128.0D));
        	}
        	
        	target.worldObj.playSoundEffect(target.posX, target.posY, target.posZ, "thaumcraft:craftfail", 4.0F, 0.8F + this.itemRand.nextFloat() * 0.2F);
        	
        	this.overthrow(target, player);
        	
        	SuperpositionHandler.setCasted(player, 30, false);
        	
        	if (player.worldObj.isRemote)
        		this.localCooldown = 30;
        }
        
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (SuperpositionHandler.isOnCoodown(player))
			return stack;
		
		if (world.isRemote)
			if (this.localCooldown != 0)
				return stack;
		
		Entity pointedEntity = EntityUtils.getPointedEntity(world, player, 0.0D, 64.0D, 3F);
		
		if (pointedEntity instanceof EntityLivingBase) {
			this.targetList.put(player, (EntityLivingBase) pointedEntity);
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			
		} else
			this.targetList.put(player, null);
		
		return stack;
	}

	@Override
	public boolean isFull3D() {
		return false;
	}

	@Override
	public int getWarp(ItemStack arg0, EntityPlayer arg1) {
		return 3;
	}
}