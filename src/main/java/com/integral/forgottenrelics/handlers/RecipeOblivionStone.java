package com.integral.forgottenrelics.handlers;

import java.util.ArrayList;
import java.util.List;

import com.integral.forgottenrelics.Main;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeOblivionStone implements IRecipe {
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
		ItemStack repairedStack = null;
		List<ItemStack> stackList = new ArrayList<ItemStack>();
		ItemStack voidStone = null;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
			ItemStack checkedItemStack = par1InventoryCrafting.getStackInSlot(i);

			if (checkedItemStack != null) {
				if (checkedItemStack.getItem() == Main.itemOblivionStone) {
					if (voidStone == null)
						voidStone = checkedItemStack;
					else
						return null;
				} else {
					stackList.add(checkedItemStack);
				}

			}

		}

		if (voidStone != null & stackList.size() == 1) {
			ItemStack savedStack = stackList.get(0).copy();

			NBTTagCompound nbt;

			if (voidStone.hasTagCompound())
				nbt = (NBTTagCompound) voidStone.getTagCompound().copy();
			else
				nbt = new NBTTagCompound();

			int[] arr = nbt.getIntArray("SupersolidID");
			int[] meta = nbt.getIntArray("SupersolidMetaID");
			int counter = 0;

			if (arr.length >= RelicsConfigHandler.oblivionStoneHardCap)
				return null;

			for (int s : arr) {
				int metaD = meta[counter];
				counter++;
				if (s == Item.itemRegistry.getIDForObject(savedStack.getItem()) & metaD != -1
						& metaD == savedStack.getItemDamage())
					return null;
				else if (s == Item.itemRegistry.getIDForObject(savedStack.getItem()) & metaD == -1)
					return null;
			}

			arr = SuperpositionHandler.addInt(arr, Item.itemRegistry.getIDForObject(savedStack.getItem()));

			if (!savedStack.isItemStackDamageable())
				meta = SuperpositionHandler.addInt(meta, savedStack.getItemDamage());
			else
				meta = SuperpositionHandler.addInt(meta, -1);

			nbt.setIntArray("SupersolidID", arr);
			nbt.setIntArray("SupersolidMetaID", meta);

			ItemStack returnedStack = voidStone.copy();
			returnedStack.setTagCompound(nbt);

			return returnedStack;
		} else if (voidStone != null & stackList.size() == 0) {
			ItemStack returnedStack = new ItemStack(Main.itemOblivionStone, 1, voidStone.getItemDamage());
			return returnedStack;
		} else
			return null;

	}

	public ItemStack getRecipeOutput() {
		return null;
	}

	public int getRecipeSize() {
		return 10;
	}

	public boolean matches(InventoryCrafting par1InventoryCrafting, World arg1) {

		ItemStack repairedStack = null;
		List<ItemStack> stackList = new ArrayList<ItemStack>();
		ItemStack voidStone = null;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
			ItemStack checkedItemStack = par1InventoryCrafting.getStackInSlot(i);

			if (checkedItemStack != null) {
				if (checkedItemStack.getItem() == Main.itemOblivionStone) {
					if (voidStone == null)
						voidStone = checkedItemStack;
					else
						return false;
				} else {
					stackList.add(checkedItemStack);
				}

			}

		}

		if (voidStone != null & stackList.size() == 1) {
			ItemStack savedStack = stackList.get(0).copy();

			NBTTagCompound nbt;

			if (voidStone.hasTagCompound())
				nbt = (NBTTagCompound) voidStone.getTagCompound().copy();
			else
				nbt = new NBTTagCompound();

			int[] arr = nbt.getIntArray("SupersolidID");
			int[] meta = nbt.getIntArray("SupersolidMetaID");
			int counter = 0;
			
			if (arr.length >= RelicsConfigHandler.oblivionStoneHardCap)
				return false;

			for (int s : arr) {
				int metaD = meta[counter];
				counter++;
				if (s == Item.itemRegistry.getIDForObject(savedStack.getItem()) & metaD != -1
						& metaD == savedStack.getItemDamage())
					return false;
				else if (s == Item.itemRegistry.getIDForObject(savedStack.getItem()) & metaD == -1)
					return false;
			}

			return true;
		} else if (voidStone != null & stackList.size() == 0)
			return true;
		else
			return false;

	}
}
