package com.integral.forgottenrelics.handlers;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class RelicsChunkLoadCallback implements ForgeChunkManager.OrderedLoadingCallback
{
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) 
	{
		
		for(Ticket ticket: tickets) {
			
		}
		
	}

	@Override
	public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount)  {
		List<Ticket> validTickets = Lists.newArrayList();
		for(Ticket ticket: tickets) {
			validTickets.add(ticket);
		}
		return validTickets;
	}
	
}