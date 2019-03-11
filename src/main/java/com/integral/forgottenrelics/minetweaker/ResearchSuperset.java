package com.integral.forgottenrelics.minetweaker;

import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;

@ZenClass("mods.forgottenrelics.Research")
public class ResearchSuperset {
    
	@ZenMethod
    public static void setHidden(String researchKey, boolean def) {
    	MineTweakerAPI.apply(new ResearchSuperset.Executor(researchKey, def, 0));
    }
    
	@ZenMethod
    public static void setLost(String researchKey, boolean def) {
    	MineTweakerAPI.apply(new ResearchSuperset.Executor(researchKey, def, 1));
    }
    
	@ZenMethod
    public static void obliterateDefaultTriggers(String researchKey) {
    	MineTweakerAPI.apply(new ResearchSuperset.Executor(researchKey, false, 2));
    }

    private static class Executor implements IUndoableAction {
        String researchKey;
        boolean def;
        int commandIndex;
        
        boolean wasHidden = false;
        boolean wasLost = false;
        
        public Executor(String researchKey, boolean def, int index) {
            this.researchKey = researchKey;
            this.def = def;
        	this.commandIndex = index;
        }
        
        public void apply() {
        	ResearchItem research = ResearchCategories.getResearch(this.researchKey);
        	
        	if (research == null) {
        		MineTweakerAPI.logError("The research " + researchKey + " is unlikely to exist.");
        		return;
        	}
        	
        	
        	if (this.commandIndex == 0) {
        		
        		if (this.def)
        			research.setHidden();
        		else {
        			if (research.isHidden())
        				this.wasHidden = true;
        			
        			SuperpositionHandler.setResearchUnhidden(research);
        		}
        	} else if (this.commandIndex == 1) {
        		
        		if (this.def)
        			research.setLost();
        		else {
        			if (research.isLost())
        				this.wasLost = true;
        			
        			SuperpositionHandler.setResearchUnlost(research);
        		}
        	} else if (this.commandIndex == 2) {
        		research.setItemTriggers((ItemStack[])null);
        		research.setAspectTriggers((Aspect[])null);
        		research.setEntityTriggers((String[])null);
        	}
        	
        }
        
        public boolean canUndo() {
        	ResearchItem research = ResearchCategories.getResearch(this.researchKey);
        	
        	if (research == null)
        		return true;
        	
        	if (this.commandIndex == 0) {
        		if (this.def)
        			return research.isHidden();
        		else
        			return true;
        	} else if (this.commandIndex == 1) {
        		
        		if (this.def)
        			return research.isLost();
        		else
        			return true;
        	
        	} else if (this.commandIndex == 2) {
        		return false;
        	}
        	
        	return false;
        }
        
        public String describe() {
        	ResearchItem research = ResearchCategories.getResearch(this.researchKey);
        	String desc = null;
        	
        	if (research == null)
        		return desc;
        	
        	if (this.commandIndex == 0) {
        		if (this.def)
        			desc = "Research " + research.key + " is set as Hidden.";
        		else
        			desc = "Research " + research.key + " is set as not Hidden.";
        	} else if (this.commandIndex == 1) {
        		if (this.def)
        			desc = "Research " + research.key + " is set as Lost.";
        		else
        			desc = "Research " + research.key + " is set as not Lost.";
        	} else if (this.commandIndex == 2) {
        		desc = "Research " + research.key + " had it's default triggers obliterated.";
        	}
        	
        	return desc;
        }
        
        public void undo() {
        	ResearchItem research = ResearchCategories.getResearch(this.researchKey);
        	
        	if (research == null)
        		return;
        	
        	if (this.commandIndex == 0) {
        		if (this.def)
        			SuperpositionHandler.setResearchUnhidden(research);
        		else {
        			if (this.wasHidden)
        			research.setHidden();
        		}
        	} else if (this.commandIndex == 1) {
        		if (this.def)
        			SuperpositionHandler.setResearchUnlost(research);
        		else {
        			if (this.wasLost)
        			research.setLost();
        		}
        	}
        }
        
        public String describeUndo() {
        	ResearchItem research = ResearchCategories.getResearch(this.researchKey);
            String desc = null;
            
            if (research == null)
        		return desc;
        	
        	if (this.commandIndex == 0) {
        		if (this.def)
        			desc = "Research " + research.key + " is no longer Hidden.";
        		else
        			if (this.wasHidden)
        			desc = "Research " + research.key + " is Hidden once more.";
        	} else if (this.commandIndex == 1) {
        		if (this.def)
        			desc = "Research " + research.key + " is no longer Lost.";
        		else
        			if (this.wasLost)
        			desc = "Research " + research.key + " is Lost once more.";
        	}
        	
        	return desc;
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }

}
