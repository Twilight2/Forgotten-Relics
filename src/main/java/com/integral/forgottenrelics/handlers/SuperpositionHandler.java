package com.integral.forgottenrelics.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.packets.ArcLightningMessage;
import com.integral.forgottenrelics.packets.BurstMessage;
import com.integral.forgottenrelics.packets.ForgottenResearchMessage;
import com.integral.forgottenrelics.packets.ICanSwingMySwordMessage;
import com.integral.forgottenrelics.packets.LightningMessage;
import com.integral.forgottenrelics.packets.NotificationMessage;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import vazkii.botania.common.block.subtile.functional.SubTileHeiseiDream;
import vazkii.botania.common.entity.EntityDoppleganger;

public class SuperpositionHandler {
	
	public static JusticeBringerHandler justiceHandler = null;
	
	/**
	 * Executes the Justice Handler, ensuring
	 * that no other threads of this handler
	 * exist at the moment.
	 */
	
	public static void bringTheJustice(EntityPlayer player) {
		
		if (player.worldObj.isRemote)
			return;
		
		try {
			
		if (!justiceHandler.isAlive()) {
			justiceHandler = new JusticeBringerHandler(player);
			justiceHandler.start();
		}
		
		} catch (NullPointerException ex) {
			justiceHandler = new JusticeBringerHandler(player);
			justiceHandler.start();
		}

	}
	
	/**
	 * Adds specified research to the list of forgotten knowledge,
	 * alongside with all items required to unlock it.
	 * The research must already be .setHidden() or .setLost()
	 * in order for this to take any effect.
	 * 
	 * Once player have all specified items scanned with thaumometer,
	 * the research could be chosen to be made available for
	 * researching by them with next execution of Justice Handler.
	 * 
	 * @param researchKey String key of the research.
	 * @param stacks All items to be set up as triggers for research.
	 */
	
	public static void setupResearchTriggers(String researchKey, ItemStack... stacks) {
		List<ItemStack> stackList = new ArrayList<ItemStack>(Arrays.asList(stacks));
		Main.forgottenKnowledge.put(researchKey, stackList);
	}
	
	/**
	 * Sends custom notification to a player,
	 * @param player
	 * @param type
	 */
	
	public static void sendNotification(EntityPlayer player, int type) {
		if (!player.worldObj.isRemote)
		Main.packetInstance.sendTo(new NotificationMessage(type), (EntityPlayerMP) player);
	}
	
	/**
	 * Basically, does the same thing as Heisei Dream.
	 */
	
	public static void cryHavoc(World world, EntityPlayer player, int RANGE) {
		
		/*
		List<IMob> mobs = world.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - RANGE, player.posY - RANGE, player.posZ - RANGE, player.posX + RANGE + 1, player.posY + RANGE + 1, player.posZ + RANGE + 1));
		if(mobs.size() > 1)
			for(IMob mob : mobs) {
				if(mob instanceof EntityLiving) {
					EntityLiving recast = (EntityLiving) mob;
					EntityLivingBase newTarget = (EntityLivingBase) mob;
					
					if (recast.getAttackTarget() != null & recast.getAttackTarget() != player)
						break;
					
					while (newTarget == recast || newTarget == player)
					newTarget = (EntityLiving) mobs.get((int) (Math.random() * mobs.size()));
					
					recast.setAttackTarget(newTarget);
				}
		}
		*/
		
		List<IMob> mobs = world.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - RANGE, player.posY - RANGE, player.posZ - RANGE, player.posX + RANGE + 1, player.posY + RANGE + 1, player.posZ + RANGE + 1));
		if(mobs.size() > 1)
			for(IMob mob : mobs) {
				if(mob instanceof EntityLiving) {
					EntityLiving entity1 = (EntityLiving) mob;
					if(SubTileHeiseiDream.brainwashEntity(entity1, mobs)) {
						break;
					}
				}
		}
		
	}
	
	public static void imposeArcLightning(World world, int dimension, double x, double y, double z, double destX, double destY, double destZ, float r, float g, float b, float h) {
		if (!world.isRemote)
		Main.packetInstance.sendToAllAround(new ArcLightningMessage(x, y, z, destX, destY, destZ, r, g, b, h), new TargetPoint(dimension, x, y, z, 128.0D));
	}
	
	public static void imposeLightning(World world, int dimension, double x, double y, double z, double destX, double destY, double destZ, int duration, float curve, int speed, int type, float width) {
		if (!world.isRemote) 
		Main.packetInstance.sendToAllAround(new LightningMessage(x, y, z, destX, destY, destZ, duration, curve, speed, type, width), new TargetPoint(dimension, x, y, z, 128.0D));
	}
	
	public static void imposeBurst(World world, int dimension, double x, double y, double z, float size) {
		if (!world.isRemote)
		Main.packetInstance.sendToAllAround(new BurstMessage(x, y, z, size), new TargetPoint(dimension, x, y, z, 128.0D));
	}
	
	/**
	 * Checks if given position is valid for teleportation of regular entity,
	 * given that it has solid block under and at least two blocks space over
	 * it to avoid teleporting into the walls.
	 * @return True if position is valid, false otherwise.
	 */
	
	public static boolean validatePosition(World world, int x, int y, int z) {
		
		if (!world.isAirBlock(x, y-1, z) & world.getBlock(x, y-1, z).isCollidable() & world.isAirBlock(x, y, z) & world.isAirBlock(x, y+1, z)) {
			return true;
		} else {
		return false;
		}
	}
	
	/**
	 * Checks if given player has active casting cooldown.
	 * @return True if yes, false if no.
	 */
	
	public static boolean isOnCoodown(EntityPlayer player) {
		if (player.worldObj.isRemote)
			return false;
		
		int cooldown;
		
		try {
			cooldown = Main.castingCooldowns.get(player);
		}
		catch (NullPointerException ex) {
			Main.castingCooldowns.put(player, 0);
			cooldown = 0;
		}
		
		if (cooldown != 0)
			return true;
		else
			return false;
		
	}
	
	/**
	 * Sets the player casting cooldown to given number and, optionally, swings the item player is holding.
	 */
		
	public static void setCasted(EntityPlayer player, int cooldown, boolean swing) {
		if(!player.worldObj.isRemote) {
		Main.castingCooldowns.put(player, cooldown);
		
		if (swing) { 
		player.swingItem();
		Main.packetInstance.sendTo(new ICanSwingMySwordMessage(true), (EntityPlayerMP) player);
		}
		
		}
		
	}
	
	/**
	 * Registers aspect tag on given item, for all it's existing meta-ID
	 * variants, within range specified by startCount and metaLimit.
	 */
	
	public static void setItemAspectsForMetaRange(ItemStack stack, AspectList list, int metaLimit, int startCount) {
		
		for (int counter = startCount; counter <= metaLimit; counter++) {
			stack.setItemDamage(counter);
			ThaumcraftApi.registerObjectTag(stack, list);
		}
		
	}
	
	/**
	 * Checks if given Damage Source is instance of absolute damage.
	 * Absolute damage types include True Damage, Soul Drain Damage,
	 * damage dealt by Amulet of Oblivion or Tome of Broken Fates,
	 * and vanilla ones like Void Damage.
	 * 
	 * This is used by some features to prevent them from decreasing
	 * those damage types or negating them completely.
	 */
	
	public static boolean isDamageTypeAbsolute(DamageSource source) {
		if (source == DamageSource.outOfWorld || source == DamageSource.starve || source instanceof DamageRegistryHandler.DamageSourceFate || source instanceof DamageRegistryHandler.DamageSourceOblivion || source instanceof DamageRegistryHandler.DamageSourceSoulDrain || source instanceof DamageRegistryHandler.DamageSourceTrueDamage || source instanceof DamageRegistryHandler.DamageSourceTrueDamageUndef)
		return true;
		else
		return false;
	}
	
	public static boolean isEntityBlacklistedFromTelekinesis(EntityLivingBase entity) {
		if (entity instanceof EntityThaumcraftBoss || entity instanceof EntityDoppleganger)
		return true;
		else
		return false;
	}
	
	public static String getBaubleTooltip(BaubleType type) {
		String str = "";
		
		switch (type) {
		
		case AMULET: str = StatCollector.translateToLocal("item.FRAmulet.lore");
			break;
			
		case BELT: str = StatCollector.translateToLocal("item.FRBelt.lore");
			break;
			
		case RING: str = StatCollector.translateToLocal("item.FRRing.lore");
			break;
			
		default: str = "";
			break;
		
		}
		
		return str;
	}
	
	/**
	 * Performs ray trace for blocks in the direction of player's look, within given range.
	 * @return First block in the line of sight, null if none found.
	 */
	
	public static MovingObjectPosition getPointedBlock(EntityPlayer player, World world, float range) {
		
		double d0 = player.posX;
        double d1 = player.posY + 1.62D - (double) player.yOffset;
        double d2 = player.posZ;
		
		Vec3 position = Vec3.createVectorHelper(d0, d1, d2);
		Vec3 look = player.getLook(1.0F);
		Vec3 finalvec = position.addVector(look.xCoord * range, look.yCoord * range, look.zCoord * range);
		
		MovingObjectPosition mop = world.rayTraceBlocks(position, finalvec);
		
		return mop;
	}
	
	/**
	 * Searches for all players that have specified bauble equipped.
	 * @return List of players that have specified... you should have understood by now, didn't you?
	 */
	
	public static List <EntityPlayer> getBaubleOwnersList(World world, Item baubleItem) {
		
		List<EntityPlayer> returnList = new LinkedList();
		
		if (!world.isRemote) {
		
		List<EntityPlayer> playersList = new ArrayList(MinecraftServer.getServer().getConfigurationManager().playerEntityList);
		
		for (int counter = playersList.size() - 1; counter >= 0; counter --) {
			
			if (SuperpositionHandler.hasBauble(playersList.get(counter), baubleItem)) {
				returnList.add(playersList.get(counter));
			}
			
		}
		
		}
		
		return returnList;
	}
	
	/**
	 * Searches for players within given radius from given entity
	 * that have specified item within their bauble inventory, and 
	 * constructs a list of them.
	 * @return Random player from constructed list, null if none were found.
	 */
	
	public static EntityPlayer findPlayerWithBauble(World world, int radius, Item baubleItem, EntityLivingBase entity) {
		
		List<EntityPlayer> returnList = new LinkedList();
		
		if (!world.isRemote) {
		List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(entity.posX-radius, entity.posY-radius, entity.posZ-radius, entity.posX+radius, entity.posY+radius, entity.posZ+radius));
		
		if (playerList.contains(entity))
			playerList.remove(entity);
		
		for (int counter = playerList.size() - 1; counter >= 0; counter --) {
			
			if (SuperpositionHandler.hasBauble(playerList.get(counter), baubleItem)) {
				returnList.add(playerList.get(counter));
			}
			
		}
		
		if (returnList.size() > 0) {
			return returnList.get((int) ((returnList.size() - 1) * Math.random()));
		} else {
			return null;
		}
		
		
		}
		
		return null;
	}
	
	/**
	 * Checks if given player has specified item equipped as bauble.
	 * @return True if item is equipped, false otherwise.
	 */
	
	public static boolean hasBauble(EntityPlayer player, Item theBauble) {
		
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		List<Item> baubleList = new ArrayList();
		if (baubles.getStackInSlot(0) != null)
		baubleList.add(baubles.getStackInSlot(0).getItem());
		if (baubles.getStackInSlot(1) != null)
		baubleList.add(baubles.getStackInSlot(1).getItem());
		if (baubles.getStackInSlot(2) != null)
		baubleList.add(baubles.getStackInSlot(2).getItem());
		if (baubles.getStackInSlot(3) != null)
		baubleList.add(baubles.getStackInSlot(3).getItem());
		
		if (baubleList.contains(theBauble)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Attempts to teleport entity at given coordinates, or nearest valid location on Y axis.
	 * @return True if successfull, false otherwise.
	 */
	
	public static boolean validTeleport(Entity entity, double x_init, double y_init, double z_init, World world) {
		
		int x = (int) x_init;
		int y = (int) y_init;
		int z = (int) z_init;
		
		Block block = world.getBlock(x, y-1, z);
		
		if (block != Blocks.air & block.isCollidable()) {
			
			for (int counter = 0; counter <= 32; counter++) {
				
				if (!world.isAirBlock(x, y+counter-1, z) & world.getBlock(x, y+counter-1, z).isCollidable() & world.isAirBlock(x, y+counter, z) & world.isAirBlock(x, y+counter+1, z)) {
					
					SuperpositionHandler.imposeBurst(entity.worldObj, entity.dimension, entity.posX, entity.posY+1, entity.posZ, 1.25f);
					
					entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "mob.endermen.portal", 1.0F, 1.0F);
					((EntityLivingBase) entity).setPositionAndUpdate(x+0.5, y+counter, z+0.5);
					entity.worldObj.playSoundEffect(x, y+counter, z, "mob.endermen.portal", 1.0F, 1.0F);
					
					return true;
				}
				
			}
			
		} else {
			
			for (int counter = 0; counter <= 32; counter++) {
				
				if (!world.isAirBlock(x, y-counter-1, z) & world.getBlock(x, y-counter-1, z).isCollidable() & world.isAirBlock(x, y-counter, z) & world.isAirBlock(x, y-counter+1, z)) {
					
					SuperpositionHandler.imposeBurst(entity.worldObj, entity.dimension, entity.posX, entity.posY+1, entity.posZ, 1.25f);
					
					entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "mob.endermen.portal", 1.0F, 1.0F);
					((EntityLivingBase) entity).setPositionAndUpdate(x+0.5, y-counter, z+0.5);
					entity.worldObj.playSoundEffect(x, y-counter, z, "mob.endermen.portal", 1.0F, 1.0F);
					
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	
	/**
	  * Attempts to find valid location within given radius and teleport entity there.
	  * @return True if teleportation were successfull, false otherwise.
	  */
	 public static boolean validTeleportRandomly(Entity entity, World world, int radius) {
		 int d = radius*2;
		 
	     double x = entity.posX + ((Math.random()-0.5D)*d);
	     double y = entity.posY + ((Math.random()-0.5D)*d);
	     double z = entity.posZ + ((Math.random()-0.5D)*d);
	     return SuperpositionHandler.validTeleport(entity, x, y, z, world);
	 }
	
	/**
	 * @param list - ItemStack array. All elements must be instances of ItemWandCasting.
	 * @return Random wand from given ItemStack list that is not fully charged with given Aspect. Null if none found.
	 */
	
	public static ItemStack getRandomValidWand(List<ItemStack> list, Aspect aspect) {
		
		List <ItemStack> validWand = new LinkedList();
		
		ItemStack randomWand = null;
		
		if (list.size() > 0) {
			
			for (int counter = 0; counter < list.size(); counter++) {
				ItemStack sheduledWand = list.get(counter);
				if (((ItemWandCasting)sheduledWand.getItem()).getVis(sheduledWand, aspect) < ((ItemWandCasting)sheduledWand.getItem()).getMaxVis(sheduledWand)) {
					validWand.add(sheduledWand);
                }
			}
			
			if (validWand.size() > 0) {
			randomWand = validWand.get((int)(Math.random() * (((validWand.size()-1)) + 1)));
			}
			return randomWand;
		}
		
		
		
		return randomWand;
	}
	
	/**
	 * @return An array containing all instances of ItemWandCasting within player inventory.
	 */
	
	public static List wandSearch(EntityPlayer player) {
		
		List<ItemStack> itemStackList = new LinkedList<ItemStack>();
		
		 for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
	            if (player.inventory.mainInventory[slot] == null)
	                continue;
	            if (player.inventory.mainInventory[slot].getItem() instanceof ItemWandCasting) {
	            	itemStackList.add(player.inventory.mainInventory[slot]);
	            }
	        }
		
		
		
		return itemStackList;
	}
	
	/**
	 * @return An array containing all ItemStacks of given Item within player inventory.
	 */
	
	public static List itemSearch(EntityPlayer player, Item researchItem) {
		
		List<ItemStack> itemStackList = new LinkedList<ItemStack>();
		
		 for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
	            if (player.inventory.mainInventory[slot] == null)
	                continue;
	            if (player.inventory.mainInventory[slot].getItem() == researchItem) {
	   
	            	itemStackList.add(player.inventory.mainInventory[slot]);
	                
	            }
	        }
		
		
		
		return itemStackList;
	}
	
	/**
	 * Because I'm fucking tired of this shit.
	 */
	
	public static boolean sidedVisConsumption(EntityPlayer player, World world, AspectList list) {
		
		if (!world.isRemote) {
			if (WandManager.consumeVisFromInventory(player, list))
			return true;
			else
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Yeah. The description of Tome of Broken Fates was quite literal.
	 */
	
	public static void insanelyDisastrousConsequences(EntityPlayer player) {
		while (player.inventory.hasItem(Main.itemFateTome)) {
			player.inventory.consumeInventoryItem(Main.itemFateTome);
		}
		
			List<EntityLivingBase> entityList = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX-64, player.posY-64, player.posZ-64, player.posX+64, player.posY+64, player.posZ+64));
			
			if (!(entityList == null) & entityList.size() > 0) {
			
			for (int counter = entityList.size(); counter > 0; counter--) {
				
				entityList.get(counter - 1).attackEntityFrom(new DamageRegistryHandler.DamageSourceFate(), 40000.0F);
				player.worldObj.newExplosion(player, entityList.get(counter - 1).posX, entityList.get(counter - 1).posY, entityList.get(counter - 1).posZ, 16F, true, true);
			}
		
			}
			
			player.worldObj.newExplosion(player, player.posX, player.posY, player.posZ, 100F, true, true);
		
	}
	
	/**
	 * Searches for ItemStacks of given Item within player's inventory.
	 * @param searchItem - Item to be searched.
	 * @return First available ItemStack of given Item, null if none found.
	 */
	
	public static ItemStack findFirst(EntityPlayer player, Item searchItem) {
        for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
            if (player.inventory.mainInventory[slot] == null)
                continue;
            if (player.inventory.mainInventory[slot].getItem() == searchItem) {
                return player.inventory.mainInventory[slot];
            }
        }
        
        return null;
    }
	
	/**
	 * Converts ItemStack of consumableItem within player's inventory to ItemStack of a single leftoverItem.
	 * 
	 * @param player
	 * @param consumableItem Item to convert
	 * @param leftoverItem Item to convert to
	 */
	
	public static void convertStuff(EntityPlayer player, Item consumableItem, Item leftoverItem) {
        for (int slot = 0; slot < player.inventory.mainInventory.length; slot++) {
            if (player.inventory.mainInventory[slot] == null)
                continue;
            if (player.inventory.mainInventory[slot].getItem() == consumableItem) {
                player.inventory.mainInventory[slot] = new ItemStack(leftoverItem);
                return;
            }
        }
    }
	
	/**
	 * Basically, expands given int array to write given Integer into there.
	 */
	
	public static int[] addInt(int[] series, int newInt) {
		int[] newSeries = new int[series.length + 1];

		for (int i = 0; i < series.length; i++) {
			newSeries[i] = series[i];
		}

		newSeries[newSeries.length - 1] = newInt;
		return newSeries;

	}

}
