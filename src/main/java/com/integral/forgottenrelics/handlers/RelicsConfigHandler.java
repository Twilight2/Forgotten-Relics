package com.integral.forgottenrelics.handlers;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class RelicsConfigHandler {
	
	public static float damageApotheosisDirect;
	public static float damageApotheosisImpact;
	public static float damageLunarFlareDirect;
	public static float damageLunarFlareImpact;
	public static float paradoxDamageCap;
	public static float telekinesisTomeDamageMIN;
	public static float telekinesisTomeDamageMAX;
	public static float nuclearFuryDamageMIN;
	public static float nuclearFuryDamageMAX;
	public static float crimsonSpellDamageMIN;
	public static float crimsonSpellDamageMAX;
	public static float weatherStoneVisMult;
	public static float chaosTomeDamageCap;
	public static float eldritchSpellDamage;
	public static float eldritchSpellDamageEx;
	
	public static float discordTomeVisMult;
	public static float telekinesisTomeVisMult;
	public static float chaosTomeVisMult;
	public static float eldritchSpellVisMult;
	public static float crimsonSpellVisMult;
	public static float soulTomeVisMult;
	public static float nuclearFuryVisMult;
	public static float lunarFlaresVisMult;
	public static float apotheosisVisMult;
	public static float fateTomeVisMult;
	public static float obeliskDrainerVisMult;
	public static float oblivionAmuletVisMult;
	public static float deificAmuletVisMult;
	public static float dormantArcanumVisMult;
	
	public static float arcanumGenRate;
	public static float soulTomeDivisor;
	
	public static boolean falseJusticeEnabled;
	
	// Brand new options! Hate implementing this stuff.
	
	public static int shinyStoneCheckrate;
	
	public static boolean deificAmuletInvincibility;
	public static boolean deificAmuletEffectImmunity;
	
	public static float darkSunRingDamageCap;
	public static float darkSunRingDeflectChance;
	public static boolean darkSunRingHealLimit;
	
	public static boolean interdimensionalMirror;
	
	public static float ancientAegisDamageReduction;
	public static float nebulousCoreDodgeChance;
	
	public static float miningCharmBoost;
	public static float miningCharmReach;
	
	public static float advancedMiningCharmBoost;
	public static float advancedMiningCharmReach;
	
	public static float damageThunderpealDirect;
	public static float damageThunderpealBolt;
	public static float thunderpealVisMult;
	
	public static float overthrowerVisMult;
	public static float voidGrimoireVisMult;
	
	public static int runicCost;
	public static int runicRechargeDelay;
	public static int runicRechargeSpeed;
	
	public static int notificationDelay;
	
	public static int fateTomeCooldownMIN;
	public static int fateTomeCooldownMAX;
	
	public static boolean telekinesisOnPlayers;
	public static float revelationModifier;
	
	public static int researchInspectionFrequency;
	public static double knowledgeChance;
	
	public static int outerLandsCheckrate;
	public static float outerLandsAntiAbuseDamage;
	public static boolean outerLandsAntiAbuseEnabled;
	
	public static boolean voidGrimoireEnabled;
	public static boolean updateNotificationsEnabled;
	public static boolean memesEnabled;
	
	public static int oblivionStoneSoftCap;
	public static int oblivionStoneHardCap;
	
	public void configDisposition(FMLPreInitializationEvent event) {
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	    config.load();
	    
	    this.oblivionStoneHardCap = config.getInt("oblivionStoneHardCap", "Generic Config", 64, 0, 2048, 
	    		"How much items you can add into list of single Keystone of The Oblivion before you would be unable add nothing more. This limit exists to prevent players from occasional or intentional abusing, since multiple keystones with huge lists (like tens of thousands of items) may cause significant performance impact.");
	    
	    this.oblivionStoneSoftCap = config.getInt("oblivionStoneSoftCap", "Generic Config", 28, 0, 2048, 
	    		"Controls the amount of items that can be added into list of Keystone of The Oblivion, before displayble list in Ctrl tooltip stops expanding and becomes unreadable. You may want to increase or decrease it, depending on your screen resolution.");
	    
	    this.updateNotificationsEnabled = config.getBoolean("memesEnabled", "Generic Config", false, 
	    		"Enables super secret memes. You are not prepared!");
	    
	    this.updateNotificationsEnabled = config.getBoolean("updateNotificationsEnabled", "Generic Config", true, 
	    		"Whether or not update notifications should be enabled. I have no idea why someone may not want to behold greatness of new versions, but alright, it's all up to you.");
	    
	    this.voidGrimoireEnabled = config.getBoolean("voidGrimoireEnabled", "Generic Config", true, 
	    		"Whethere or not Grimoire of The Abyss should be enabled. Note that it will only remove respective research, so it would be impossible to create this relic legally - it won't remove existing copies from world or prevent it's spawning from Creative Mode.");
	    
	    this.outerLandsCheckrate = config.getInt("outerLandsCheckrate", "Generic Config", 20, 1, 1024000, 
	    		"Checkrate for Outer Lands anti-abuse system, if it's enabled. Measured in ticks. Setting this value to 20 means that it would check each player once in 20 ticks, or once per second.");
	    
	    this.outerLandsAntiAbuseDamage = config.getFloat("outerLandsAntiAbuseDamage", "Generic Config", 40000.0F, 0.0F, 512000.0F, 
	    	"How much damage is dealt to player in Outer Lands if anti-abuse system is enabled and finds them out of maze. You may want to decrease this if you are getting there accidentally and want to do something about this before you are obliterated.");
	    
	    this.outerLandsAntiAbuseEnabled = config.getBoolean("outerLandsAntiAbuseEnabled", "Generic Config", true, 
	    		"Whether or not anti-abuse system for Outer Lands should be enabled. Disable if you like cheating or don't want it for some other reason.");
	    
	    this.revelationModifier = config.getFloat("revelationModifier", "Generic Config", 1.0F, 0.001F, 32.0F, 
	    		"Multiplier for probability of revealing forgotten knowledge. This multiplies both inspection frequency and individual chance for each check, so increasing it more than few times over is highly unrecommended.");
	    
	    this.telekinesisOnPlayers = config.getBoolean("telekinesisOnPlayers", "Generic Config", true, 
	    		"In a perfect world, this option would disable Tome of Predestiny's ability to affect players... BUT IT'S A WRONG WORLD BRO, AHAHAHAHAHAHAHAHAAHAHAHAHAHAH");
	    
	    this.fateTomeCooldownMIN = config.getInt("fateTomeCooldownMIN", "Generic Config", 30, 0, 32768, 
	    		"Minimal possible cooldown (in seconds) for triggering Tome of Broken Fates' death prevention effect.");
	    
	    this.fateTomeCooldownMAX = config.getInt("fateTomeCooldownMAX", "Generic Config", 90, 0, 32768, 
	    		"Maximal possible cooldown (in seconds) for triggering Tome of Broken Fates' death prevention effect. Setting this to 0 will disable cooldown entirely.");
	    
	    this.notificationDelay = config.getInt("notificationDelay", "Thaumcraft Overrides", 2000, 0, 32768, 
	    		"Determines how fast notifications scroll downwards. Overrides respective option in default Thaumcraft config.");
	    
	    this.runicRechargeSpeed = config.getInt("runicRechargeSpeed", "Thaumcraft Overrides", 750, 0, 32768, 
	    		"How many milliseconds pass between Runic Shield recharge ticks. Setting this value lower than 50 is not recommended. Overrides respective option in default Thaumcraft config.");
	    
	    this.runicRechargeDelay = config.getInt("runicRechargeDelay", "Thaumcraft Overrides", 40, 0, 32768, 
	    		"How many game ticks pass after Runic Shield has been reduced to zero before it can start recharging again. Overrides respective option in default Thaumcraft config.");
	    
	    this.runicCost = config.getInt("runicCost", "Thaumcraft Overrides", 10, 0, 32768, 
	    		"How much Aer and Terra centi-vis (0.01 vis) it costs to reacharge a single unit of Runic Shield. Overrides respective option in default Thaumcraft config.");
	    
	    this.advancedMiningCharmReach = config.getFloat("advancedMiningCharmReach", "Generic Config", 4.0F, 0.0F, 32.0F, 
	    		"Block reach increase for Ethereal Mining Charm.");
	    
	    this.miningCharmReach = config.getFloat("miningCharmReach", "Generic Config", 2.0F, 0.0F, 32.0F, 
	    		"Block reach increase for Mining Charm.");
	    
	    this.advancedMiningCharmBoost = config.getFloat("advancedMiningCharmBoost", "Generic Config", 3.0F, 0.0F, 32000.0F, 
	    		"Mining speed boost for Ethereal Mining Charm. 3.0 means that it is boosted by 300%.");
	    
	    this.miningCharmBoost = config.getFloat("miningCharmBoost", "Generic Config", 1.0F, 0.0F, 32000.0F, 
	    		"Mining speed boost for Mining Charm. 1.0 means that it is boosted by 100%.");
	    
	    this.nebulousCoreDodgeChance = config.getFloat("nebulousCoreDodgeChance", "Generic Config", 0.4F, 0.0F, 1.0F, 
	    		"Chance to dodge attack by teleporting player from it for Nebulous Core. 1.0 equals 100% chance, 0.0 - 0%.");
	    
	    this.ancientAegisDamageReduction = config.getFloat("ancientAegisDamageReduction", "Generic Config", 0.25F, 0.0F, 1.0F, 
	    		"Damage Reduction for Ancient Aegis. 1.0 equals 100% reduction, 0.0 - 0%.");
	    
	    this.deificAmuletEffectImmunity = config.getBoolean("deificAmuletEffectImmunity", "Generic Config", true, 
	    		"Whether or not Deific Amulet should provide immunity to status effects. Note, that it includes buffs as well as debuffs.");
	    
	    this.deificAmuletInvincibility = config.getBoolean("deificAmuletInvincibility", "Generic Config", true, 
	    		"Whether or not Deific Amulet should provide prolonged invincibility frames.");
	    
	    this.darkSunRingDeflectChance = config.getFloat("darkSunRingDeflectChance", "Generic Config", 0.2F, 0.0F, 1.0F, 
	    		"Chance to deflect enemy's attack back to it, while wearing Ring of The Seven Suns. 1.0 equals 100% chance, 0.0 - 0%.");
	    
	    this.darkSunRingDamageCap = config.getFloat("darkSunRingDamageCap", "Generic Config", 100.0F, 0.0F, 32768.0F, 
	    		"Damage cap for Ring of The Seven Suns. Any attacks that exceed this amount of damage will be completely negated while wearing it.");
	    
	    this.darkSunRingHealLimit = config.getBoolean("darkSunRingHealLimit", "Generic Config", false,
	    		"Enables the cooldown on Ring of The Seven Sun's healing effect, so standing in fire or lava wouldn't replenish your health insanely fast. WARNING: This config option is experimental, only touch it if you really want this.");
	    
	    this.interdimensionalMirror = config.getBoolean("interdimensionalMirror", "Generic Config", true,
	    		"Whether or not Dimensional Mirror should be capable of teleporting player across dimensions. If this is set to false, player must reside in the dimension of saved location in order to teleport to it.");
	    
	    this.shinyStoneCheckrate = config.getInt("shinyStoneCheckrate", "Generic Config", 4, 1, 2048,
	    		"Checkrate for Shiny Stone effects. The greater it is, the less frequently health regen would happen, and the more time acceleration would take. WARNING: This config option is experimental, only touch it if you really want this.");
	    
	    this.obeliskDrainerVisMult = config.getFloat("obeliskDrainerVisGen", "Generic Config", 1.0F, 0F, 32000.0F,
	    		"Vis production multiplier for Devourer of The Void.");
	    
	    this.arcanumGenRate = config.getFloat("arcanumGenRate", "Generic Config", 1.0F, 0F, 32000.0F,
	    		"Multiplier for Vis generation rate of Nebulous Core.");
	    
	    this.soulTomeDivisor = config.getFloat("soulTomeDivisor", "Generic Config", 10.0F, 0F, Float.POSITIVE_INFINITY,
	    		"Divisor, used during damage calculations by Edict of a Thousand Damned Souls. Setting this value to 10 basically means that most of the time it will drain 1/10 of entity's max health per attack.");
	    
	    this.falseJusticeEnabled = config.getBoolean("falseJusticeEnabled", "Generic Config", true,
	    		"Whether or not False Justice should be enabled. Note that it will only remove respective research, so it would be impossible to create this relic legally - it won't remove existing copies from world or prevent it's spawning from Creative Mode.");
		
	    this.damageThunderpealDirect = config.getFloat("damageThunderpealDirect", "Damage Values", 24.0F, 0F, 32000.0F,
	    		"How much damage inflicts direct hit of Thunderpeal's electrical orbs.");
	    
	    this.damageThunderpealBolt = config.getFloat("damageThunderpealBolt", "Damage Values", 16.0F, 0F, 32000.0F,
	    		"How much damage deal lightning bolts of Thunderpeal's electrical orbs.");
	    
	    this.damageApotheosisDirect = config.getFloat("damageApotheosisDirect", "Damage Values", 100.0F, 0F, 32000.0F,
	    		"How much damage inflicts direct hit of Babylon Weapons, summoned by Apotheosis.");
	    
	    this.damageApotheosisImpact = config.getFloat("damageApotheosisImpact", "Damage Values", 75.0F, 0F, 32000.0F,
	    		"How much damage receive entities within impact zone of Babylon Weapons, summoned by Apotheosis.");
	    
	    this.damageLunarFlareDirect = config.getFloat("damageLunarFlareDirect", "Damage Values", 72.0F, 0F, 32000.0F,
	    		"How much damage inflicts direct hit of Lunar Flare.");
	    
	    this.damageLunarFlareImpact = config.getFloat("damageLunarFlareImpact", "Damage Values", 40.0F, 0F, 32000.0F,
	    		"How much damage receive entities within impact zone of Lunar Flare.");
	    
	    this.paradoxDamageCap = config.getFloat("paradoxDamageCap", "Damage Values", 200.0F, 0F, 32000.0F,
	    		"Maximum damage The Paradox can deal. Damage dealt varies between 1 and this value for each hit.");
	    
	    this.telekinesisTomeDamageMIN = config.getFloat("telekinesisTomeDamageMIN", "Damage Values", 16.0F, 0F, 32000.0F,
	    		"Minimal damage that can be dealt by lightning attack of Tome of Predestiny.");
	    
	    this.telekinesisTomeDamageMAX = config.getFloat("telekinesisTomeDamageMAX", "Damage Values", 40.0F, 0F, 32000.0F,
	    		"Maximal damage that can be dealt by lightning attack of Tome of Predestiny.");
	    
	    this.nuclearFuryDamageMIN = config.getFloat("nuclearFuryDamageMIN", "Damage Values", 24.0F, 0F, 32000.0F,
	    		"Minimal damage that can be dealt by charges of Nuclear Fury.");
	    
	    this.nuclearFuryDamageMAX = config.getFloat("nuclearFuryDamageMAX", "Damage Values", 32.0F, 0F, 32000.0F,
	    		"Maximal damage that can be dealt by charges of Nuclear Fury.");
	    
	    this.crimsonSpellDamageMIN = config.getFloat("crimsonSpellDamageMIN", "Damage Values", 42.0F, 0F, 32000.0F,
	    		"Minimal damage that can be dealt by projectiles of Crimson Spell.");
	    
	    this.crimsonSpellDamageMAX = config.getFloat("crimsonSpellDamageMAX", "Damage Values", 100.0F, 0F, 32000.0F,
	    		"Maximal damage that can be dealt by projectiles of Crimson Spell.");
	    
	    this.chaosTomeDamageCap = config.getFloat("chaosTomeDamageCap", "Damage Values", 100.0F, 0F, 32000.0F,
	    		"Maximum damage that projectile of Tome of Primal Chaos can deal. Damage dealt varies between 1 and this value for each hit.");
	    
	    this.eldritchSpellDamage = config.getFloat("eldritchSpellDamage", "Damage Values", 32.5F, 0F, 32000.0F,
	    		"Default damage dealt by projectiles of Eldritch Spell.");
	    
	    this.eldritchSpellDamageEx = config.getFloat("eldritchSpellDamageEx", "Damage Values", 100.0F, 0F, 32000.0F,
	    		"Damage dealt by projectiles of Eldritch Spell, while it is used in Outer Lands.");
	    
	    this.apotheosisVisMult = config.getFloat("apotheosisVisMult", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Apotheosis.");
	    
	    this.chaosTomeVisMult = config.getFloat("chaosTomeVisMult", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Tome of Primal Chaos.");
	    
	    this.crimsonSpellVisMult = config.getFloat("crimsonSpellVisMult", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Crimson Spell.");
	    
	    this.deificAmuletVisMult = config.getFloat("deificAmuletVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Deific Amulet.");
	    
	    this.discordTomeVisMult = config.getFloat("discordTomeVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Tome of Discord.");
	    
	    this.dormantArcanumVisMult = config.getFloat("dormantArcanumVisMult", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Dormant Nebulous Core (applies in the moment of transormation; final amount of Vis required for re-awakening depends on this.)");

	    this.eldritchSpellVisMult = config.getFloat("eldritchSpellVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Eldritch Spell.");
	    
	    this.fateTomeVisMult = config.getFloat("fateTomeVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Tome of Broken Fates.");
	    
	    this.lunarFlaresVisMult = config.getFloat("lunarFlaresVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Tome of Lunar Flares.");
	    
	    this.nuclearFuryVisMult = config.getFloat("nuclearFuryVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Nuclear Fury.");
	    
	    this.oblivionAmuletVisMult = config.getFloat("oblivionAmuletVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Amulet of The Oblivion.");
	    
	    this.soulTomeVisMult = config.getFloat("soulTomeVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Edict of a Thousand Damned Souls.");
	    
	    this.telekinesisTomeVisMult = config.getFloat("telekinesisTomeVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Tome of Predestiny.");
	    
	    this.weatherStoneVisMult = config.getFloat("weatherStoneVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Runic Stone.");
	    
	    this.thunderpealVisMult = config.getFloat("thunderpealVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Thunderpeal.");
	    
	    this.overthrowerVisMult = config.getFloat("overthrowerVisCost", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Edict of Eternal Banishment.");
	    
	    this.voidGrimoireVisMult = config.getFloat("voidGrimoireVisMult", "Vis Costs", 1.0F, 0F, 1024.0F,
	    		"Vis cost multiplier for Grimoire of The Abyss.");
	    
	    config.save();
	    
	    
	    this.researchInspectionFrequency = (int) (600/this.revelationModifier);
	    this.knowledgeChance = (double) (0.1D*this.revelationModifier);
	}
}
