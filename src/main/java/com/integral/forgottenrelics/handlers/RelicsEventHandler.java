package com.integral.forgottenrelics.handlers;

import java.util.List;

import org.lwjgl.input.Mouse;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.items.ItemFateTome;
import com.integral.forgottenrelics.packets.ForgottenResearchMessage;
import com.integral.forgottenrelics.packets.GuardianVanishMessage;
import com.integral.forgottenrelics.packets.PacketVoidMessage;
import com.integral.forgottenrelics.packets.PortalTraceMessage;

import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.research.ResearchManager;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;

public class RelicsEventHandler {

	@SubscribeEvent
	public void onAchievement(AchievementEvent event) {
		Achievement theAchievement = event.achievement;
		EntityPlayer player = event.entityPlayer;
		Achievement kingKey = ModAchievements.relicKingKey;
		
		
		if (theAchievement.equals(kingKey) & !event.entityPlayer.worldObj.isRemote)
		if (!((EntityPlayerMP) player).func_147099_x().hasAchievementUnlocked(kingKey)) {
			boolean clueUnlocked = ResearchManager.isResearchComplete(player.getDisplayName(), "@Apotheosis");
			boolean researchCompleted = ResearchManager.isResearchComplete(player.getDisplayName(), "Apotheosis");
			
			if (!clueUnlocked & !researchCompleted) {
				Main.packetInstance.sendTo(new ForgottenResearchMessage("@Apotheosis"), (EntityPlayerMP) player);
	            Thaumcraft.proxy.getResearchManager().completeResearch(player, "@Apotheosis");
			}
		}
		
	}
	
	@SubscribeEvent
	public void livingTick(LivingEvent.LivingUpdateEvent event) {
		
		if (event.entity instanceof EntityPlayer & !event.entity.worldObj.isRemote) {
			EntityPlayer player = (EntityPlayer) event.entity;
			
			/*
			 * Handler used to prevent players from abusing the Outer Lands.
			 * Basically, it ensures that if player somehow gets out of
			 * the maze, they would be killed almost instantly.
			 */
			
			if (RelicsConfigHandler.outerLandsAntiAbuseEnabled)
			if (player.ticksExisted % RelicsConfigHandler.outerLandsCheckrate == 0)
			if (player.dimension == Config.dimensionOuterId) {
				double d0 = player.posX;
		        double d1 = player.posY + 1.62D - (double) player.yOffset;
		        double d2 = player.posZ;
				
				Vec3 position1 = Vec3.createVectorHelper(d0, d1, d2);
				Vec3 position2 = Vec3.createVectorHelper(d0, d1, d2);
				Vec3 up = Vec3.createVectorHelper(d0, d1+24.0D, d2);
				Vec3 down = Vec3.createVectorHelper(d0, d1-24.0D, d2);
				
				MovingObjectPosition pos1 = player.worldObj.rayTraceBlocks(position1, up);
				MovingObjectPosition pos2 = player.worldObj.rayTraceBlocks(position2, down);
				
				if (pos1 == null || pos2 == null) {
					player.attackEntityFrom(DamageSource.outOfWorld, RelicsConfigHandler.outerLandsAntiAbuseDamage);
				}
				
			}
			
			/*
			 * Handler for randomly triggering execution of
			 * Justice Handler from time to time.
			 */
			
			if (player.ticksExisted % RelicsConfigHandler.researchInspectionFrequency == 0)
			if (Math.random() <= RelicsConfigHandler.knowledgeChance) {
				SuperpositionHandler.bringTheJustice(player);
			}
		
			/*
			 * Handler for decrementing player's casting cooldown on each tick.
			 */
			
			if (Main.castingCooldowns.containsKey(player)) {
				int cooldown = Main.castingCooldowns.get(player);
				if (cooldown > 0) {
					cooldown--;
					Main.castingCooldowns.put(player, cooldown);
					return;
				} else {
					return;
				}
				
			} else {
				Main.castingCooldowns.put(player, 0);
			}
			
		}
		
		/*
		 * Handler for preventing players from abusing Gaia Guardian (through making water arenas).
		 * Because c'mon, bro, it's 2019, who kills bosses using bugs these times?..
		 */
		
		if (RelicsConfigHandler.guardianAntiAbuseRadius > 0)
		if (event.entity instanceof EntityDoppleganger & !event.entity.worldObj.isRemote & event.entity.ticksExisted > 100) {
			EntityDoppleganger theGuardian = (EntityDoppleganger) event.entity;
			Vector3 pos = Vector3.fromEntityCenter(theGuardian);
			double range = RelicsConfigHandler.guardianAntiAbuseRadius;
			AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(theGuardian.posX-range, theGuardian.posY-range, theGuardian.posZ-range, theGuardian.posX+range, theGuardian.posY+range, theGuardian.posZ+range);
			
			if (theGuardian.worldObj.isAnyLiquid(boundingBox)) {
				
				Main.packetInstance.sendToAllAround(new PacketVoidMessage(pos.x, pos.y, pos.z, true), new TargetPoint(theGuardian.dimension, theGuardian.posX, theGuardian.posY, theGuardian.posZ, 64.0D));
				if (RelicsConfigHandler.guardianNotificationRadius != 0) {
					if (RelicsConfigHandler.guardianNotificationRadius > 0)
						Main.packetInstance.sendToAllAround(new GuardianVanishMessage(), new TargetPoint(theGuardian.dimension, theGuardian.posX, theGuardian.posY, theGuardian.posZ, RelicsConfigHandler.guardianNotificationRadius));
					else
						Main.packetInstance.sendToAll(new GuardianVanishMessage());
				}
				SuperpositionHandler.imposeBurst(theGuardian.worldObj, theGuardian.dimension, pos.x, pos.y, pos.z, 2.0F);
				theGuardian.playSound("thaumcraft:craftfail", 4.0F, (float) (0.9F + (Math.random()*0.1F)));
				
				if (RelicsConfigHandler.memesEnabled)
				theGuardian.playSound("forgottenrelics:sound.meme112", 4.0F, 1.0F);
				
				theGuardian.setDead();
			}
		}
		
	}
	
	@SubscribeEvent
	public void miningStuff(PlayerEvent.BreakSpeed event) {
		
		/*
		 * Handler for calculating mining speed boost from wearing Mining Charms.
		 */
		
		float miningBoost = 1.0F;
		
		if (SuperpositionHandler.hasBauble(event.entityPlayer, Main.itemAdvancedMiningCharm)) {
			miningBoost = miningBoost + RelicsConfigHandler.advancedMiningCharmBoost;
		}
		
		if (SuperpositionHandler.hasBauble(event.entityPlayer, Main.itemMiningCharm)) {
			miningBoost += RelicsConfigHandler.miningCharmBoost;
		}
		
		event.newSpeed *= miningBoost;
		
		
	}
	
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onEntityAttacked(LivingAttackEvent event) {
		
		/*
		 * Handler for redirecting damage dealt BY player who possesses Chaos Core.
		 */
		if (event.source.getEntity() instanceof EntityPlayer & !event.isCanceled()) {
			EntityPlayer attackerPlayer = (EntityPlayer) event.source.getEntity();
			
			if (attackerPlayer.inventory.hasItem(Main.itemChaosCore) & Math.random() < 0.45) {
				List<Entity> entityList = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, AxisAlignedBB.getBoundingBox(event.entity.posX-16, event.entity.posY-16, event.entity.posZ-16, event.entity.posX+16, event.entity.posY+16, event.entity.posZ+16));
	 			
	 			if (!(entityList == null) & entityList.size() > 0) {
	 			Entity randomEntity = entityList.get((int) (Math.random() * (entityList.size() - 1)));
	 				
	 			float redirectedAmount = event.ammount * ((float) (Math.random() * 2));
	 			
	 			if (Math.random() < 0.15) {
	 				attackerPlayer.attackEntityFrom(event.source, redirectedAmount);
	 			} else {
	 				randomEntity.attackEntityFrom(event.source, redirectedAmount);
	 			}
	 			
	 			event.setCanceled(true);
	 			}
			}
			
		}
		
		if (event.entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.entity;
			
			/*
			 * Handler for redirecting damage dealt TO player who possesses Chaos Core.
			 */
			
			if (!event.isCanceled() & player.inventory.hasItem(Main.itemChaosCore) & Math.random() < 0.42D) {
				
				List<Entity> entityList = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, AxisAlignedBB.getBoundingBox(event.entity.posX-16, event.entity.posY-16, event.entity.posZ-16, event.entity.posX+16, event.entity.posY+16, event.entity.posZ+16));
	 			
	 			if (!(entityList == null) & entityList.size() > 0) {
	 			Entity randomEntity = entityList.get((int) (Math.random() * (entityList.size() - 1)));
	 			
	 			//if (event.entity.hurtResistantTime == 0) {
	 				
	 			float redirectedAmount = event.ammount * ((float) (Math.random() * 2));
	 			
	 			randomEntity.attackEntityFrom(event.source, redirectedAmount);
	 			
	 			event.setCanceled(true);
	 			}
				
			}
			
			/*
			 * Handler for randomly teleport player who has Nebulous Core equipped,
			 * instead of taking damage from attack.
			 */
			
			if (!event.isCanceled() & SuperpositionHandler.hasBauble(player, Main.itemArcanum) & Math.random() < RelicsConfigHandler.nebulousCoreDodgeChance & !SuperpositionHandler.isDamageTypeAbsolute(event.source)) {
					
					for (int counter = 0; counter <= 32; counter ++) {
						if (SuperpositionHandler.validTeleportRandomly(event.entity, event.entity.worldObj, 16)) {
							event.entity.hurtResistantTime = 20;
							event.setCanceled(true);
							break;
						}
					}
			}
			
			/*
			 * Handler for converting fire damage into healing for Ring of The Seven Suns.
			 */
			
			if (!event.isCanceled() & SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing) & Main.darkRingDamageNegations.contains(event.source.damageType)) {
				
				if (RelicsConfigHandler.darkSunRingHealLimit) {
					if (event.entity.hurtResistantTime == 0) {
						player.heal(event.ammount);
						event.entity.hurtResistantTime = 20;
					}
				} else {
					player.heal(event.ammount);
				}
				
				event.setCanceled(true);
			
			}
			
			/*
			 * Handler for deflecting incoming attack to it's source for Ring of The Seven Suns.
			 */
			
			else if (!event.isCanceled() & SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing) & event.source.getEntity() != null & Math.random() <= RelicsConfigHandler.darkSunRingDeflectChance) {
				
				if (player.hurtResistantTime == 0) {
				player.hurtResistantTime = 20;
				event.source.getEntity().attackEntityFrom(event.source, event.ammount);
				event.setCanceled(true);
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event) {
		
		/*
		 * Handler for converting damage dealt TO owners of False Justice.
		 */
		
		if (event.entity instanceof EntityPlayer & !event.isCanceled()) {
			EntityPlayer player = (EntityPlayer) event.entity;
			
		if (player.inventory.hasItem(Main.itemFalseJustice) & !SuperpositionHandler.isDamageTypeAbsolute(event.source)) {
			event.setCanceled(true);
			if (event.source.getEntity() == null) {
			player.attackEntityFrom(new DamageRegistryHandler.DamageSourceTrueDamageUndef(), event.ammount*2);
			}
			else {
			player.attackEntityFrom(new DamageRegistryHandler.DamageSourceTrueDamage(event.source.getEntity()), event.ammount*2);
			}
		}
		
		}
		
		/*
		 * Hanlder for converting damage dealt BY owners of False Justice.
		 */
		
		if (event.source.getEntity() instanceof EntityPlayer & !event.isCanceled()) {
			EntityPlayer attackerPlayer = (EntityPlayer) event.source.getEntity();
			
			if (attackerPlayer.inventory.hasItem(Main.itemFalseJustice) & !SuperpositionHandler.isDamageTypeAbsolute(event.source)) {
				event.setCanceled(true);
				event.entity.attackEntityFrom(new DamageRegistryHandler.DamageSourceTrueDamage(event.source.getEntity()), event.ammount*2);
			}
		}
		
		
		
		if (event.entity instanceof EntityPlayer & !event.isCanceled()) {
			EntityPlayer player = (EntityPlayer) event.entity;
			
			/*
			 * Handler for multiplication of damage dealt to owners of Chaos Core by value in range
			 * between 0.0 and 2.0.
			 */
			
			if (!event.isCanceled() & player.inventory.hasItem(Main.itemChaosCore)) {
				event.ammount = event.ammount * ((float) (Math.random() * 2));
			}
			
			/*
			 * Handler for blocking attacks that exceed damage cap for Ring of The Seven Suns.
			 */
			
			if (!event.isCanceled() & event.ammount > 100.0F & !SuperpositionHandler.isDamageTypeAbsolute(event.source) & SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing)) {
				SuperpositionHandler.sendNotification(player, 2);
				event.setCanceled(true);
			}
			
			/*
			 * Handler for increasing strenght of regular attacks on wearers of Ring of The Seven Suns.
			 */
			
			if (SuperpositionHandler.hasBauble(player, Main.itemDarkSunRing) & !event.isCanceled() & Math.random() <= 0.25 & !SuperpositionHandler.isDamageTypeAbsolute(event.source)) {
				event.ammount = event.ammount + (event.ammount * ((float) Math.random()));
			}
			
			/*
			 * Handler for redirecting damage received by player to owner of Ancient Aegis
			 * nearby, if one is present.
			 */
			
			if(!event.entity.worldObj.isRemote & !SuperpositionHandler.hasBauble(player, Main.itemAncientAegis) & !event.isCanceled()) {
			EntityPlayer aegisOwner = SuperpositionHandler.findPlayerWithBauble(event.entity.worldObj, 32, Main.itemAncientAegis, player);
			
			if (aegisOwner != null) {
				aegisOwner.attackEntityFrom(event.source, event.ammount * 0.4F);
				event.ammount *= 0.6F;
			}
			}
			
			/*
			 * Handler for Ancient Aegis damage reduction.
			 */
			
			if (SuperpositionHandler.hasBauble(player, Main.itemAncientAegis) & !event.isCanceled() & !SuperpositionHandler.isDamageTypeAbsolute(event.source)) {
				event.ammount *= 1.0F - RelicsConfigHandler.ancientAegisDamageReduction;
			}
			
			/*
			 * Handler for splitting damage dealt to wearer of Superposition Ring
			 * among all other wearers, if any exist.
			 */
			
			if (!(event.source instanceof DamageRegistryHandler.DamageSourceSuperposition) & !(event.source instanceof DamageRegistryHandler.DamageSourceSuperpositionDefined))
			if (SuperpositionHandler.hasBauble(player, Main.itemSuperpositionRing) & !event.isCanceled()) {
				
				DamageSource altSource;
				
				if (event.source.getEntity() != null)
					altSource = new DamageRegistryHandler.DamageSourceSuperpositionDefined(event.source.getEntity());
				else 
					altSource = new DamageRegistryHandler.DamageSourceSuperposition();
					
				if (event.source.isUnblockable())
					altSource.setDamageBypassesArmor();
				if (event.source.isDamageAbsolute())
					altSource.setDamageIsAbsolute();
				
				altSource.damageType = event.source.getDamageType();
				
				List superpositioned = SuperpositionHandler.getBaubleOwnersList(player.worldObj, Main.itemSuperpositionRing);
				if (superpositioned.contains(player))
					superpositioned.remove(player);
				
				if (superpositioned.size() > 0) {
				double percent = 0.12 + (Math.random()*0.62);
				float splitAmount = (float) (event.ammount * percent);
				
				for (int counter = superpositioned.size() - 1; counter >= 0; counter--) {
					EntityPlayer cPlayer = (EntityPlayer) superpositioned.get(counter);
					cPlayer.attackEntityFrom(altSource, splitAmount/superpositioned.size());
				}
				
				event.ammount -= splitAmount;
				}
			}			
			
			/*
			 * Handler for damage absorption by Oblivion Amulet.
			 */
			
			if (!event.isCanceled() & SuperpositionHandler.hasBauble(player, Main.itemOblivionAmulet) & !SuperpositionHandler.isDamageTypeAbsolute(event.source)) {
				if (WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.FIRE, (int)(event.ammount*8*RelicsConfigHandler.oblivionAmuletVisMult)).add(Aspect.ENTROPY, (int)(event.ammount*8*RelicsConfigHandler.oblivionAmuletVisMult)))) {
				 
				 ItemStack oblivionAmulet = PlayerHandler.getPlayerBaubles(player).getStackInSlot(0);
					
				 ItemNBTHelper.setFloat(oblivionAmulet, "IDamageStored", ItemNBTHelper.getFloat(oblivionAmulet, "IDamageStored", 0) + event.ammount);
				 
				 event.setCanceled(true);
				 
			}
			}
			
			
		}
		
		
	}
	
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		
		
		 // Handlers to prevent double kill glitch that appears when handling False Justice's effects. 
		 
		
		if (event.entity instanceof EntityPlayer & !(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamage) & !(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamageUndef)) {
			EntityPlayer playerAttacked = (EntityPlayer) event.entity;
			if (playerAttacked.inventory.hasItem(Main.itemFalseJustice)) {
				event.setCanceled(true);
			}
		}
		else if (event.source.getEntity() instanceof EntityPlayer  & !(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamage) & !(event.source instanceof DamageRegistryHandler.DamageSourceTrueDamageUndef)) {
			EntityPlayer playerAttacker = (EntityPlayer) event.source.getEntity();
			if (playerAttacker.inventory.hasItem(Main.itemFalseJustice)) {
				event.setCanceled(true);
			}
		}
		
	}
	
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(LivingDeathEvent event) {
		
		// You're certainly dead at this point. Very certainly. Very dead.
		
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			if(player.inventory.hasItem(Main.itemOmegaCore)) {
				event.setCanceled(true);
				player.setHealth(1);
			}
			
			/*
			 * Handler for Tome of Broken Fates death prevention.
			 */

			else if (!player.worldObj.isRemote) {
				
				ItemStack fateTomeStack = (SuperpositionHandler.findFirst(player, Main.itemFateTome));
				
				if (fateTomeStack != null) {
					
					if (fateTomeStack.hasTagCompound()) {
					    
					    if (ItemNBTHelper.verifyExistance(fateTomeStack, "IFateCooldown"))
					    if (ItemNBTHelper.getInt(fateTomeStack, "IFateCooldown", 0) == 0)
					    if (WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.AIR, ItemFateTome.AerCost).add(Aspect.EARTH, ItemFateTome.TerraCost).add(Aspect.FIRE, ItemFateTome.IgnisCost).add(Aspect.WATER, ItemFateTome.AquaCost).add(Aspect.ORDER, ItemFateTome.OrdoCost).add(Aspect.ENTROPY, ItemFateTome.PerditioCost))) {
					    	
					    	int minCooldown = (RelicsConfigHandler.fateTomeCooldownMIN)*20;
					    	int bonusCooldown = (RelicsConfigHandler.fateTomeCooldownMAX*20)-minCooldown;
					    	
					    	if (RelicsConfigHandler.fateTomeCooldownMAX != 0)
					    	ItemNBTHelper.setInt(fateTomeStack, "IFateCooldown", (int) ((minCooldown) + (Math.random() * bonusCooldown)));    	
							
					    	event.setCanceled(true);
							
					    	player.setHealth(player.getMaxHealth());
					    	
					    	if (Math.random() <= 0.75) {
					    		player.addPotionEffect(new PotionEffect(11, 200, 2, false));
					    		player.addPotionEffect(new PotionEffect(10, 500, 1, false));
					    		player.addPotionEffect(new PotionEffect(12, 1000, 0, false));
					    	} else {
					    		player.addPotionEffect(new PotionEffect(18, 600, 2, false));
						    	player.addPotionEffect(new PotionEffect(15, 200, 0, false));
						    	player.addPotionEffect(new PotionEffect(20, 300, 1, false));
					    	}
					    	SuperpositionHandler.imposeBurst(player.worldObj, player.dimension, player.posX, player.posY+1, player.posZ, 1.5f);
					    	player.worldObj.playSoundEffect((double) player.posX + 0.5D, (double) player.posY + 1.5D, (double) player.posZ + 0.5D, "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
					    	
					    }
					    
				}
					
				}
				
			}
		}
	}
	
	
	
}
