package com.integral.forgottenrelics.items;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IWarpingGear;

public class ItemOblivionStone extends Item implements IWarpingGear {

	public ItemOblivionStone() {
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemOblivionStone");
		this.setCreativeTab(Main.tabForgottenRelics);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("forgottenrelics:Oblivion_Stone");
	}

	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.epic;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		int damage = itemstack.getItemDamage();

		if (player.isSneaking()) {

			if (damage < 100) {
				damage += 100;
				player.playSound("dftoolkit:sound.hhoff", 1.0F, 1.0F);
			} else {
				player.playSound("dftoolkit:sound.hhon", 1.0F, 1.0F);
				damage -= 100;
			}

			itemstack.setItemDamage(damage);

		} else {

			if (damage == 0 || damage == 1 || damage == 100 || damage == 101) {
				damage += 1;
			} else if (damage == 2 || damage == 102) {
				damage -= 2;
			}

			player.playSound("random.orb", 1.0F, (float) (0.8F + (Math.random() * 0.2F)));

			itemstack.setItemDamage(damage);
		}

		player.swingItem();

		return itemstack;
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b) {
		if (!(entity instanceof EntityPlayer) || !(entity.ticksExisted % 10 == 0))
			return;

		EntityPlayer player = (EntityPlayer) entity;
		
		int damage = itemstack.getItemDamage();
		if (damage >= 100 || !itemstack.hasTagCompound())
			return;

		NBTTagCompound nbt = itemstack.getTagCompound();
		int[] arr = nbt.getIntArray("SupersolidID");
		int[] meta = nbt.getIntArray("SupersolidMetaID");
		
		this.consumeStuff(player, arr, meta, damage);

	}

	public static void consumeStuff(EntityPlayer player, int[] ID, int[] meta, int mode) {
		HashMap<Integer, ItemStack> stackMap = new HashMap<Integer, ItemStack>();
		int cycleCounter = 0;
		int filledStacks = 0;
		
		for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
			if (player.inventory.mainInventory[slot] != null) {
				filledStacks += 1;
				if (player.inventory.mainInventory[slot].getItem() != Main.itemOblivionStone)
					stackMap.put(slot, player.inventory.mainInventory[slot]);
			}
		}
		
		if (stackMap.size() == 0)
			return;

		if (mode == 0) {
			
			for (int sID : ID) {
				for (int slot : stackMap.keySet()) {
					if (meta[cycleCounter] != -1) {
					if (stackMap.get(slot).getItem() == Item.getItemById(sID) & stackMap.get(slot).getItemDamage() == meta[cycleCounter])
						player.inventory.setInventorySlotContents(slot, null);
					} else {
					if (stackMap.get(slot).getItem() == Item.getItemById(sID)) 
						player.inventory.setInventorySlotContents(slot, null);
					}
					
				}
				cycleCounter++;
			}
		} else if (mode == 1) {
			
			for (int sID : ID) {
				HashMap<Integer, ItemStack> localStackMap = new HashMap<Integer, ItemStack>(stackMap);
				Multimap<Integer, Integer> stackSizeMultimap = ArrayListMultimap.create();
				
				for (int slot : stackMap.keySet()) {
					if (meta[cycleCounter] != -1) {
						if (stackMap.get(slot).getItem() != Item.getItemById(sID) || stackMap.get(slot).getItemDamage() != meta[cycleCounter])
							localStackMap.remove(slot);
					} else {
						if (stackMap.get(slot).getItem() != Item.getItemById(sID))
							localStackMap.remove(slot);
					}
				}
				
				for (int slot : localStackMap.keySet()) {
					stackSizeMultimap.put(localStackMap.get(slot).stackSize, slot);
				}
				
				while (localStackMap.size() > 1) {
					int smallestStackSize = Collections.min(stackSizeMultimap.keySet());
					Collection<Integer> smallestStacks = stackSizeMultimap.get(smallestStackSize);
					int slotWithSmallestStack = Collections.max(smallestStacks);
					
					player.inventory.setInventorySlotContents(slotWithSmallestStack, null);
					stackSizeMultimap.remove(smallestStackSize, slotWithSmallestStack);
					localStackMap.remove(slotWithSmallestStack);
				}
				cycleCounter++;
			}
			
		} else if (mode == 2) {
			if (filledStacks >= player.inventory.mainInventory.length) {
				
				for (int sID : ID) {
					HashMap<Integer, ItemStack> localStackMap = new HashMap<Integer, ItemStack>(stackMap);
					Multimap<Integer, Integer> stackSizeMultimap = ArrayListMultimap.create();
					
					for (int slot : stackMap.keySet()) {
						if (meta[cycleCounter] != -1) {
							if (stackMap.get(slot).getItem() != Item.getItemById(sID) || stackMap.get(slot).getItemDamage() != meta[cycleCounter])
								localStackMap.remove(slot);
						} else {
							if (stackMap.get(slot).getItem() != Item.getItemById(sID))
								localStackMap.remove(slot);
						}
					}
					
					for (int slot : localStackMap.keySet()) {
						stackSizeMultimap.put(localStackMap.get(slot).stackSize, slot);
					}
					
					if (localStackMap.size() > 0) {
						int smallestStackSize = Collections.min(stackSizeMultimap.keySet());
						Collection<Integer> smallestStacks = stackSizeMultimap.get(smallestStackSize);
						int slotWithSmallestStack = Collections.max(smallestStacks);
						
						player.inventory.setInventorySlotContents(slotWithSmallestStack, null);
						return;
					}
					
					cycleCounter++;
				}
				
			}
		}
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

		if (GuiScreen.isShiftKeyDown()) {
			list.add(StatCollector.translateToLocal("item.OblivionStone1.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone2.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone2_more.lore"));
			list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone3.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone4.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone5.lore"));
			list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone6.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone7.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone8.lore"));
			list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone9.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone10.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone11.lore"));
			list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone12.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone13.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone14.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStone15.lore"));
		} else if (GuiScreen.isCtrlKeyDown()) {
			list.add(StatCollector.translateToLocal("item.OblivionStoneCtrlList.lore"));
			if (stack.hasTagCompound()) {
				NBTTagCompound nbt = stack.getTagCompound();
				int[] arr = nbt.getIntArray("SupersolidID");
				int[] meta = nbt.getIntArray("SupersolidMetaID");
				int counter = 0;
				
				if (arr.length <= RelicsConfigHandler.oblivionStoneSoftCap) {
				for (int s : arr) {
					Item something = Item.getItemById(s);
					if (something != null) {
						ItemStack displayStack;
						if (meta[counter] != -1)
							displayStack = new ItemStack(something, 1, meta[counter]);
						else
							displayStack = new ItemStack(something, 1, 0);
						
						list.add(EnumChatFormatting.GOLD + " - "
								+ displayStack.getDisplayName());
					}
					counter++;
				}
				} else {
					for (int s = 0; s < RelicsConfigHandler.oblivionStoneSoftCap; s++) {
						int randomID = (int) (Math.random()*30.0D);
						Item something = Item.getItemById(arr[randomID]);
						
						if (something != null) {
							ItemStack displayStack;
							if (meta[randomID] != -1)
								displayStack = new ItemStack(something, 1, meta[randomID]);
							else
								displayStack = new ItemStack(something, 1, 0);
							
							list.add(EnumChatFormatting.GOLD + " - "
									+ displayStack.getDisplayName());
						}
					}
				}
			}
		} else {
			list.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore"));
			list.add(StatCollector.translateToLocal("item.OblivionStoneCtrlTooltip.lore"));
			list.add(StatCollector.translateToLocal("item.FREmpty.lore"));

			int mode = stack.getItemDamage();
			if (mode < 100) {
				list.add(StatCollector.translateToLocal("item.OblivionStoneMode.lore") + " "
						+ StatCollector.translateToLocal("item.OblivionMode" + mode + ".lore"));
			} else {
				list.add(StatCollector.translateToLocal("item.OblivionStoneMode.lore") + " "
						+ StatCollector.translateToLocal("item.OblivionStoneDeactivated.lore"));
			}
		}
		
		list.add(StatCollector.translateToLocal("item.FREmpty.lore"));
	}

	@Override
	public int getWarp(ItemStack arg0, EntityPlayer arg1) {
		return 2;
	}

}
