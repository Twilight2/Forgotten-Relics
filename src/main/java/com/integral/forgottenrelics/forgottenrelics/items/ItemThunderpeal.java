package com.integral.forgottenrelics.items;

import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.entities.EntityThunderpealOrb;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.WandManager;
import vazkii.botania.common.core.helper.Vector3;

public class ItemThunderpeal extends Item {
	
	 public static final int AerCost = (int) (135 * RelicsConfigHandler.thunderpealVisMult);
	 public static final int TerraCost = (int) (0 * RelicsConfigHandler.thunderpealVisMult);
	 public static final int IgnisCost = (int) (85 * RelicsConfigHandler.thunderpealVisMult);
	 public static final int AquaCost = (int) (0 * RelicsConfigHandler.thunderpealVisMult);
	 public static final int OrdoCost = (int) (0 * RelicsConfigHandler.thunderpealVisMult);
	 public static final int PerditioCost = (int) (0 * RelicsConfigHandler.thunderpealVisMult);

	public ItemThunderpeal() {
		super();
		setMaxStackSize(1);
		setUnlocalizedName("ItemThunderpeal");
		setCreativeTab(Main.tabForgottenRelics);
	}
	
	 @Override
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	 {
		 
		 if(GuiScreen.isShiftKeyDown()){
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal1.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal2.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal3_1.lore") + " " + (int)24.0F + " " + StatCollector.translateToLocal("item.ItemThunderpeal3_2.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal4.lore"));
			 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal5_1.lore") + " " + (int)16.0F + " " + StatCollector.translateToLocal("item.ItemThunderpeal5_2.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal6.lore"));
			 par3List.add(StatCollector.translateToLocal("item.ItemThunderpeal7.lore"));
		 } else if (GuiScreen.isCtrlKeyDown()) {
				par3List.add(StatCollector.translateToLocal("item.FRVisPerCast.lore"));
				par3List.add(" " + StatCollector.translateToLocal("item.FRAerCost.lore") + (this.AerCost/100.0D));
			 	par3List.add(" " + StatCollector.translateToLocal("item.FRIgnisCost.lore") + (this.IgnisCost/100.0D));
			 }
		 else {
			 par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore")); 
			 par3List.add(StatCollector.translateToLocal("item.FRViscostTooltip.lore"));
		 }

	 }


	 @Override
	 public EnumRarity getRarity(ItemStack itemStack)
	 {
		 return EnumRarity.epic;
	 }
	
	 @Override
	 public void registerIcons(IIconRegister iconRegister)
	 {
		 itemIcon = iconRegister.registerIcon("forgottenrelics:Thunderpeal");
	 }

	 public boolean spawnOrb(World world, EntityPlayer player) {
		
		if (!world.isRemote) {
			
			Vector3 originalPos = Vector3.fromEntityCenter(player);
			Vector3 vector = originalPos.add(new Vector3(player.getLookVec()).multiply(1.25F));
			vector.y += 0.5;
			
			Vector3 motion = new Vector3(player.getLookVec()).multiply(1.5F);
			
			EntityThunderpealOrb orb = new EntityThunderpealOrb(world, player);
            orb.setPosition(vector.x, vector.y, vector.z);
            
            orb.area += 2;
			orb.motionX = motion.x;
			orb.motionY = motion.y;
			orb.motionZ = motion.z;
			
			world.playSoundAtEntity((Entity)orb, "thaumcraft:zap", 1.0f, 1.0f + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2f);
			world.spawnEntityInWorld(orb);
			
			return true;
		}
			
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if (!SuperpositionHandler.isOnCoodown(player) & !world.isRemote)
		if (WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.AIR, this.AerCost).add(Aspect.FIRE, this.IgnisCost))){
			
			Container inventory = player.inventoryContainer;
			((EntityPlayerMP)player).sendContainerToPlayer(inventory);
			
			this.spawnOrb(world, player);
			SuperpositionHandler.setCasted(player, 30, true);
		}
		
		return stack;
	}

	@Override
	public boolean isFull3D() {
		return false;
	}
}