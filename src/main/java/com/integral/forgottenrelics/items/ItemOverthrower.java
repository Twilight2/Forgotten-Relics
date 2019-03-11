package com.integral.forgottenrelics.items;

import java.util.HashMap;
import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;
import com.integral.forgottenrelics.packets.BanishmentCastingMessage;
import com.integral.forgottenrelics.packets.InfernalParticleMessage;
import com.integral.forgottenrelics.packets.LightningBoltMessage;
import com.integral.forgottenrelics.packets.OverthrowChatMessage;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.utils.EntityUtils;
import vazkii.botania.common.core.helper.Vector3;

/**
 * You can't even imagine how much I've suffered
 * to get this stuff to work.
 * @author Integral
 */

public class ItemOverthrower extends Item implements IWarpingGear {
	
	 public static final int AerCost = (int) (0 * RelicsConfigHandler.overthrowerVisMult);
	 public static final int TerraCost = (int) (0 * RelicsConfigHandler.overthrowerVisMult);
	 public static final int IgnisCost = (int) (8 * RelicsConfigHandler.overthrowerVisMult);
	 public static final int AquaCost = (int) (0 * RelicsConfigHandler.overthrowerVisMult);
	 public static final int OrdoCost = (int) (5 * RelicsConfigHandler.overthrowerVisMult);
	 public static final int PerditioCost = (int) (5 * RelicsConfigHandler.overthrowerVisMult);
	 
	 static HashMap<EntityPlayer, EntityLivingBase> targetList = new HashMap<EntityPlayer, EntityLivingBase>();

	public ItemOverthrower() {
		setMaxStackSize(1);
		setUnlocalizedName("ItemOverthrower");
		setCreativeTab(Main.tabForgottenRelics);
	}
	
	 @Override
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		 
		 if(GuiScreen.isShiftKeyDown()){
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower1.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower2.lore"));
			 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower3.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower4.lore"));
			 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower5.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower6.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemOverthrower7.lore"));
		 } else if (GuiScreen.isCtrlKeyDown()) {
				par3List.add(StatCollector.translateToLocal("item.FRVisPerTick.lore"));
			 	par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (this.IgnisCost/100.0D));
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
		itemIcon = iconRegister.registerIcon("forgottenrelics:Just_Stop_Blaming_Me_For_Stealing_Calamity_Textures_Already");
	}
	 
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		 return 150;
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
		
	}
	
	public boolean overthrow(EntityLivingBase entity, EntityPlayer overthrower) {

		int x = (int) ((Math.random()-0.5D) * 20002.0D);
		int z = (int) ((Math.random()-0.5D) * 20002.0D);
		int y = 124;
		
		DimensionManager.getWorld(-1).getChunkProvider().loadChunk(x >> 4, z >> 4);
		
		for (int counter = 124; counter > 0; counter--) {
			boolean valid = SuperpositionHandler.validatePosition(DimensionManager.getWorld(-1), x, counter, z);
			
			if (valid) {
				y = counter;
				break;
			} else
				continue;
		}
		
		
		if (y != 124) {
			
			if (!(entity instanceof EntityPlayerMP)) {
				NBTTagCompound nbt = new NBTTagCompound();
				entity.writeToNBTOptional(nbt);
				
				try {
				EntityLivingBase overthrownEntity;
				overthrownEntity = (EntityLivingBase) EntityList.createEntityFromNBT(nbt, DimensionManager.getWorld(-1));
				overthrownEntity.dimension = -1;

				overthrownEntity.setPositionAndUpdate(x, y, z);
				DimensionManager.getWorld(-1).spawnEntityInWorld(overthrownEntity);
				//System.out.println("Overthrown entity spawned: " + overthrownEntity + ", " + overthrownEntity.dimension);
				} catch (Exception ex) {}
				
				entity.setDead();
				
				for (int a = 0; a < 12; ++a) {
	                int xx;
	                int yy;
	                int zz;
	                for (xx = MathHelper.floor_double(entity.posX) + this.itemRand.nextInt(4) - this.itemRand.nextInt(4), yy = MathHelper.floor_double(entity.posY) + 4, zz = MathHelper.floor_double(entity.posZ) + this.itemRand.nextInt(4) - this.itemRand.nextInt(4); entity.worldObj.isAirBlock(xx, yy, zz) && yy > MathHelper.floor_double(entity.posY) - 4; --yy) {}
	                if (entity.worldObj.isAirBlock(xx, yy + 1, zz) && !entity.worldObj.isAirBlock(xx, yy, zz) && entity.worldObj.getBlock(xx, yy + 1, zz) != Blocks.fire && EntityUtils.canEntityBeSeen(entity, xx + 0.5, yy + 1.5, zz + 0.5)) {
	                	entity.worldObj.setBlock(xx, yy + 1, zz, Blocks.fire, 0, 3);
	                }
	            }

				return true;
			} else {
				((EntityPlayerMP)entity).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, -1);
				entity.setPositionAndUpdate(x, y, z);
				Main.packetInstance.sendToAll(new OverthrowChatMessage(((EntityPlayer) entity).getDisplayName(), overthrower.getDisplayName(), 0));
				Main.log.info(overthrower.getDisplayName() + " has overthrown " + ((EntityPlayer) entity).getDisplayName() + " into the Nether.");
				return true;
			}
			
		} else {
			return false;
		}
		
	
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		if (player.worldObj.isRemote)
			return;
		
		if (!this.targetList.containsKey(player) || !WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.FIRE, this.IgnisCost).add(Aspect.ORDER, this.OrdoCost).add(Aspect.ENTROPY, this.PerditioCost))) {
			player.stopUsingItem();
			return;
		}
			
		EntityLivingBase target = this.targetList.get(player);
		
		if (target != null) {
		if (!target.isDead) {
			 
			 if (!player.worldObj.isRemote & count % 10 == 0 & count != stack.getMaxItemUseDuration()) {
				 player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "thaumcraft:fireloop", 0.33F, 2.0F);
				 target.worldObj.playSoundEffect(target.posX, target.posY, target.posZ, "thaumcraft:fireloop", 0.33F, 2.0F);
			 }
			 
			 EntityLivingBase theTarget = (EntityLivingBase) target;
			 Vector3 thisPos = Vector3.fromEntityCenter(target);
			
			 if (!player.worldObj.isRemote)
			 Main.packetInstance.sendToAllAround(new BanishmentCastingMessage(thisPos.x, thisPos.y, thisPos.z, 5), new TargetPoint(target.dimension, target.posX, target.posY, target.posZ, 64.0D));
			 
			 try {
				 theTarget.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30, 2, true));
			 } catch (Exception ex) {}
			 
			 if (count == 1) {
				 if (theTarget.dimension != -1 & !player.worldObj.isRemote) {
					boolean gotEffect = false;
					for (int counter = 8; counter >= 0; counter--) {
						if (this.overthrow(theTarget, player)) {
							gotEffect = true;
							break;
						}
						else
							continue;
					}
					
					if (!gotEffect & !(theTarget instanceof EntityPlayerMP)) {
						theTarget.setPositionAndUpdate(0, 0, 0);
						theTarget.setDead();	
					}
					
					
				 } else if (player.worldObj.isRemote & theTarget.dimension != -1) {
					 theTarget.setPositionAndUpdate(0, 0, 0);
					 theTarget.setDead();
				 }
				 if (!player.worldObj.isRemote) {
					 for (int counter = 3; counter > 0; counter--)
					 player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, thisPos.x-0.5D, thisPos.y - (theTarget.height/2), thisPos.z-0.5D));
					 Main.packetInstance.sendToAllAround(new LightningBoltMessage(thisPos.x-0.5D, thisPos.y - (theTarget.height/2), thisPos.z-0.5D, 3), new TargetPoint(player.dimension, thisPos.x, thisPos.y - (theTarget.height/2), thisPos.z, 128.0D));
					 Main.packetInstance.sendToAllAround(new InfernalParticleMessage(thisPos.x, thisPos.y, thisPos.z, 128), new TargetPoint(player.dimension, thisPos.x, thisPos.y, thisPos.z, 128.0D));
				 }
				 
				 return;
			 }
			 
			 
		} else {
			this.targetList.put(player, null);
			player.stopUsingItem();
			return;
		} 
		
		} else {
			this.targetList.put(player, null);
			player.stopUsingItem();
			return;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if (player.dimension == -1)
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
		return 2;
	}
}