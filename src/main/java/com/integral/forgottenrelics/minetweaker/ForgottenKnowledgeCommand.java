package com.integral.forgottenrelics.minetweaker;

import com.integral.forgottenrelics.Main;

import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.api.player.IPlayer;
import minetweaker.api.server.ICommandFunction;

public class ForgottenKnowledgeCommand implements ICommandFunction {
    public void execute(final String[] arguments, final IPlayer player) {
    	
    	for (String key : Main.forgottenKnowledge.keySet()) {
    		MineTweakerAPI.logCommand(key + ": " + Main.forgottenKnowledge.get(key));
    	}
    	
        if (player != null) {
            player.sendChat(MineTweakerImplementationAPI.platform.getMessage("List generated; see minetweaker.log in your minecraft dir"));
        }
    }
    
}
