package com.integral.forgottenrelics.minetweaker;

import minetweaker.MineTweakerImplementationAPI;
import minetweaker.MineTweakerImplementationAPI.ReloadEvent;
import minetweaker.util.IEventHandler;

public class ReloadEventHandler implements IEventHandler<MineTweakerImplementationAPI.ReloadEvent> {
	
	@Override
	public void handle(ReloadEvent event) {
		MineTweakerIntegrator.registerCommands();
		//System.out.println("Oh gods we've done that!");
	}

}
