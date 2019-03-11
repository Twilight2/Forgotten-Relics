package com.integral.forgottenrelics.minetweaker;

import cpw.mods.fml.common.Optional;
import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;

public class MineTweakerIntegrator {
	
	@Optional.Method(modid = "MineTweaker3")
	public static void init() {
		
		MineTweakerImplementationAPI.onReloadEvent(new ReloadEventHandler());
		MineTweakerAPI.registerClass(ResearchSuperset.class);
		MineTweakerAPI.registerClass(JusticeHandlerInteraction.class);
		
	}
	
	@Optional.Method(modid = "MineTweaker3")
	public static void registerCommands() {
		
    	if (MineTweakerAPI.server != null) {
    		MineTweakerAPI.server.addMineTweakerCommand("forgottenKnowledge", new String[] { "/minetweaker forgottenKnowledge", "    Outputs a list of all forgotten knowledge reasearches, alongside with arrays of ItemStacks bound to them in string representation" }, new ForgottenKnowledgeCommand());
    	}
    	
    }

}
