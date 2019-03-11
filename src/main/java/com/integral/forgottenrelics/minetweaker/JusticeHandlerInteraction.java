package com.integral.forgottenrelics.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.integral.forgottenrelics.Main;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.research.ResearchCategories;

@ZenClass("mods.forgottenrelics.JusticeHandler")
public class JusticeHandlerInteraction {
    
	@ZenMethod
    public static void addTrigger(String researchKey, IItemStack stack) {
		MineTweakerAPI.apply(new JusticeHandlerInteraction.Executor(researchKey, stack));
    }
	
	@ZenMethod
	public static void obliterateJusticeTriggers(String researchKey) {
		MineTweakerAPI.apply(new JusticeHandlerInteraction.Executor(researchKey));
	}
	
	public static ItemStack toStack(final IItemStack iStack) {
        if (iStack == null) {
            return null;
        }
        
        final Object internal = iStack.getInternal();
        if (!(internal instanceof ItemStack)) {
        	MineTweakerAPI.logError("Invalid ItemStack: " + iStack);
        	return null;
        }
        
        ItemStack theStack = (ItemStack) internal;
        //if (theStack.getItemDamage() == 32767)
        //	theStack.setItemDamage(0);
        
        theStack.stackSize = 1;
        
        return theStack;
    }

    private static class Executor implements IUndoableAction {
    	String researchKey;
    	IItemStack iStack;
    	boolean obliteration;
    	
    	List<ItemStack> obliteratedStacks;
    	ItemStack operableStack;
    	
        public Executor(String researchKey, IItemStack stack) {
        	this.researchKey = researchKey;
        	this.iStack = stack;
        	this.obliteration = false;
        }
        
        public Executor(String researchKey) {
        	this.researchKey = researchKey;
        	this.iStack = null;
        	this.obliteration = true;
        }
        
        public void apply() {
        	
        	if (this.researchKey == "Apotheosis") {
        		MineTweakerAPI.logError("You can't redefine triggers for Apotheosis research. Don't even try.");
        		return;
        	}
        	
        	if (ResearchCategories.getResearch(this.researchKey) == null) {
        		MineTweakerAPI.logError("The research " + this.researchKey + " is unlikely to exist.");
        		return;
        	}
        	
        	if (this.obliteration) {
        		if (Main.forgottenKnowledge.containsKey(this.researchKey))
        			this.obliteratedStacks = Main.forgottenKnowledge.get(this.researchKey);
        			Main.forgottenKnowledge.remove(this.researchKey);
        	} else {
        		ItemStack stack = JusticeHandlerInteraction.toStack(this.iStack);
        		List<ItemStack> triggerList = new ArrayList<ItemStack>();
        		if (Main.forgottenKnowledge.get(this.researchKey) != null)
        			triggerList.addAll(Main.forgottenKnowledge.get(this.researchKey));
        		
        		if (stack != null) {
        			triggerList.add(stack);
        			this.operableStack = stack;
        			Main.forgottenKnowledge.put(this.researchKey, triggerList);
        		}
        	}
        	
        }
        
        public boolean canUndo() {
			return true;
        }
        
        public String describe() {
        	if (this.obliteration)
        		return "Obliterating Justice triggers of " + this.researchKey + " research";
        	else
        		return "Adding " + this.iStack + " to Justice triggers of " + this.researchKey;
        }
        
        public void undo() {
        	
        	if (this.researchKey == "Apotheosis") {
        		return;
        	}
        	
        	if (ResearchCategories.getResearch(this.researchKey) == null)
        		return;
        	
        	if (this.obliteration) {
        		if (this.obliteratedStacks != null)
        			Main.forgottenKnowledge.put(this.researchKey, this.obliteratedStacks);
 
        	} else {
        		
        		List<ItemStack> triggerList = new ArrayList<ItemStack>();
        		
        		if (Main.forgottenKnowledge.get(this.researchKey) != null)
        			triggerList.addAll(Main.forgottenKnowledge.get(this.researchKey));
        		
        		if (triggerList != null) {
        			triggerList.remove(this.operableStack);
        			if (triggerList.size() > 0)
        				Main.forgottenKnowledge.put(this.researchKey, triggerList);
        			else
        				Main.forgottenKnowledge.remove(this.researchKey);
        		}
        	}
        	
        }
        
        public String describeUndo() {
        	if (this.obliteration)
        		return "Reversing obliteration of " + this.researchKey + " research";
        	else
        		return "Removing " + this.iStack + " from Justice triggers of " + this.researchKey;
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }

}
