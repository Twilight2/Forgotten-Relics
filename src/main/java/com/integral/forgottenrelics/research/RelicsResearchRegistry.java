package com.integral.forgottenrelics.research;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class RelicsResearchRegistry {
	
	public static HashMap<String, InfusionRecipe> recipes = new HashMap<String, InfusionRecipe>();
	
	public static ItemStack superpositionRing = new ItemStack(Main.itemSuperpositionRing, 1, 0);
	public static ItemStack weatherStone = new ItemStack(Main.itemWeatherStone, 1, 0);
	public static ItemStack miningCharm = new ItemStack(Main.itemMiningCharm, 1, 0);
	public static ItemStack advancedMiningCharm = new ItemStack(Main.itemAdvancedMiningCharm, 1, 0);
	public static ItemStack ancientAegis = new ItemStack(Main.itemAncientAegis, 1, 0);
	public static ItemStack apotheosis = new ItemStack(Main.itemApotheosis, 1, 0);
	public static ItemStack nebulousCore  = new ItemStack(Main.itemArcanum, 1, 0);
	public static ItemStack chaosCore = new ItemStack(Main.itemChaosCore, 1, 0);
	public static ItemStack chaosTome = new ItemStack(Main.itemChaosTome, 1, 0);
	public static ItemStack crimsonSpell = new ItemStack(Main.itemCrimsonSpell, 1, 0);
	public static ItemStack darkSunRing = new ItemStack(Main.itemDarkSunRing, 1, 0);
	public static ItemStack deificAmulet = new ItemStack(Main.itemDeificAmulet, 1, 0);
	public static ItemStack dimensionalMirror = new ItemStack(Main.itemDimensionalMirror, 1, 0);
	public static ItemStack eldritchSpell = new ItemStack(Main.itemEldritchSpell, 1, 0);
	public static ItemStack falseJustice = new ItemStack(Main.itemFalseJustice, 1, 0);
	public static ItemStack fateTome = new ItemStack(Main.itemFateTome, 1, 0);
	public static ItemStack lunarFlares = new ItemStack(Main.itemLunarFlares, 1, 0);
	public static ItemStack nuclearFury = new ItemStack(Main.itemMissileTome, 1, 0);
	public static ItemStack obeliskDrainer = new ItemStack(Main.itemObeliskDrainer, 1, 0);
	public static ItemStack theParadox = new ItemStack(Main.itemParadox, 1, 0);
	public static ItemStack shinyStone = new ItemStack(Main.itemShinyStone, 1, 0);
	public static ItemStack soulTome = new ItemStack(Main.itemSoulTome, 1, 0);
	public static ItemStack telekinesisTome = new ItemStack(Main.itemTelekinesisTome, 1, 0);
	public static ItemStack discordTome = new ItemStack(Main.itemTeleportationTome, 1, 0);
	public static ItemStack XPTome = new ItemStack(Main.itemXPTome, 1, 0);
	public static ItemStack oblivionAmulet = new ItemStack(Main.itemOblivionAmulet, 1, 0);
	public static ItemStack terrorCrown = new ItemStack(Main.itemTerrorCrown, 1, 0);
	public static ItemStack thunderpeal = new ItemStack(Main.itemThunderpeal, 1, 0);
	public static ItemStack overthrower = new ItemStack(Main.itemOverthrower, 1, 0);
	public static ItemStack discordRing = new ItemStack(Main.itemDiscordRing, 1, 0);
	public static ItemStack voidGrimoire = new ItemStack(Main.itemVoidGrimoire, 1, 0);
	public static ItemStack oblivionStone = new ItemStack(Main.itemOblivionStone, 1, 0);
	
	public static ItemStack enderEye = new ItemStack(Items.ender_eye, 1, 0);
	public static ItemStack salisMundus = new ItemStack(ConfigItems.itemResource, 1, 14);
	public static ItemStack voidSeed = new ItemStack(ConfigItems.itemResource, 1, 17);
	public static ItemStack enderAir = new ItemStack(ModItems.manaResource, 1, 15);
	public static ItemStack primalCharm = new ItemStack(ConfigItems.itemResource, 1, 15);
	public static ItemStack blankRing = new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1);
	public static ItemStack ghastTear = new ItemStack(Items.ghast_tear, 1, 0);
	public static ItemStack airRune = new ItemStack(ModItems.rune, 1, 3);
	public static ItemStack airShard = new ItemStack(ConfigItems.itemShard, 1, 0);
	public static ItemStack fireShard = new ItemStack(ConfigItems.itemShard, 1, 1);
	public static ItemStack waterShard = new ItemStack(ConfigItems.itemShard, 1, 2);
	public static ItemStack earthShard = new ItemStack(ConfigItems.itemShard, 1, 3);
	public static ItemStack orderShard = new ItemStack(ConfigItems.itemShard, 1, 4);
	public static ItemStack entropyShard = new ItemStack(ConfigItems.itemShard, 1, 5);
	public static ItemStack balancedShard = new ItemStack(ConfigItems.itemShard, 1, 6);
	
	public static ItemStack revealingGoggles = new ItemStack(ConfigItems.itemGoggles, 1, 0);
	public static ItemStack knowledgeFragment = new ItemStack(ConfigItems.itemResource, 1, 9);
	public static ItemStack inkwell = new ItemStack(ConfigItems.itemInkwell, 1, 0);
	public static ItemStack arcaneStone = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6);
	public static ItemStack gaiaSpirit = new ItemStack(ModItems.manaResource, 1, 5);
	public static ItemStack terrasteelIngot = new ItemStack(ModItems.manaResource, 1, 4);
	public static ItemStack nitor = new ItemStack(ConfigItems.itemResource, 1, 1);
	public static ItemStack elementalPickaxe = new ItemStack(ConfigItems.itemPickElemental, 1, 0);
	public static ItemStack speedPotionII = new ItemStack(Items.potionitem, 1, 8226);
	public static ItemStack reachRing = new ItemStack(ModItems.reachRing, 1, 0);
	public static ItemStack dragonStone = new ItemStack(ModItems.manaResource, 1, 9);
	public static ItemStack portableHole = new ItemStack(ConfigItems.itemFocusPortableHole, 1, 0);
	public static ItemStack handMirror = new ItemStack(ConfigItems.itemHandMirror, 1, 0);
	public static ItemStack glowstone = new ItemStack(Items.glowstone_dust, 1, 0);
	public static ItemStack etherealEssence = new ItemStack(ConfigItems.itemWispEssence, 1, 0);
	public static ItemStack elementiumIngot = new ItemStack(ModItems.manaResource, 1, 7);
	public static ItemStack voidPickaxe = new ItemStack(ConfigItems.itemPickVoid, 1, 0);
	public static ItemStack kineticRunicGirdle = new ItemStack(ConfigItems.itemGirdleRunic, 1, 1);
	public static ItemStack tectonicGirdle = new ItemStack(ModItems.knockbackBelt, 1, 0);
	public static ItemStack healPotionII = new ItemStack(Items.potionitem, 1, 8229);
	public static ItemStack pixieDust = new ItemStack(ModItems.manaResource, 1, 8);
	public static ItemStack enchantedFabric = new ItemStack(ConfigItems.itemResource, 1, 7);
	public static ItemStack goldIngot = new ItemStack(Items.gold_ingot, 1, 0);
	public static ItemStack blazePowder = new ItemStack(Items.blaze_powder, 1, 0);
	public static ItemStack blazeRod = new ItemStack(Items.blaze_rod, 1, 0);
	public static ItemStack lavaBucket = new ItemStack(Items.lava_bucket, 1, 0);
	public static ItemStack runicRingCharged = new ItemStack(ConfigItems.itemRingRunic, 1, 2);
	public static ItemStack cinderPearl = new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3);
	public static ItemStack superLavaPendant = new ItemStack(ModItems.superLavaPendant, 1, 0);
	public static ItemStack runicAmuletAdv = new ItemStack(ConfigItems.itemAmuletRunic, 1, 1);
	public static ItemStack lavaPendant = new ItemStack(ModItems.lavaPendant, 1, 0);
	public static ItemStack voidIngot = new ItemStack(ConfigItems.itemResource, 1, 16);
	public static ItemStack alumentum = new ItemStack(ConfigItems.itemResource, 1, 0);
	public static ItemStack voidSword = new ItemStack(ConfigItems.itemSwordVoid, 1, 0);
	public static ItemStack primordialPearl = new ItemStack(ConfigItems.itemEldritchObject, 1, 3);
	public static ItemStack superGoldenApple = new ItemStack(Items.golden_apple, 1, 1);
	public static ItemStack bloodPendant = new ItemStack(ModItems.bloodPendant, 1, 0);
	public static ItemStack netherStar = new ItemStack(Items.nether_star, 1, 0);
	public static ItemStack eldritchEye = new ItemStack(ConfigItems.itemEldritchObject, 1, 0);
	public static ItemStack writableBook = new ItemStack(Items.writable_book, 1, 0);
	public static ItemStack amber = new ItemStack(ConfigItems.itemResource, 1, 6);
	public static ItemStack greatVisAmulet = new ItemStack(ConfigItems.itemAmuletVis, 0, 1);
	public static ItemStack thaumiumIngot = new ItemStack(ConfigItems.itemResource, 1, 2);
	public static ItemStack enderPearl = new ItemStack(Items.ender_pearl, 1, 0);
	public static ItemStack shockFocus = new ItemStack(ConfigItems.itemFocusShock, 1, 0);
	public static ItemStack gravityRod = new ItemStack(ModItems.gravityRod, 1, 0);
	public static ItemStack pechFocus = new ItemStack(ConfigItems.itemFocusPech, 1, 0);
	public static ItemStack jarredBrain = new ItemStack(ConfigBlocks.blockJar, 1, 1);
	public static ItemStack goldLaurel = new ItemStack(ModItems.goldLaurel);
	public static ItemStack fireball = new ItemStack(Items.fire_charge, 1, 0);
	public static ItemStack bottledTaint = new ItemStack(ConfigItems.itemBottleTaint, 1, 0);
	public static ItemStack primalFocus = new ItemStack(ConfigItems.itemFocusPrimal, 1, 0);
	public static ItemStack redstone = new ItemStack(Items.redstone, 1, 0);
	public static ItemStack crimsonRites = new ItemStack(ConfigItems.itemEldritchObject, 1, 1);
	public static ItemStack starSword = new ItemStack(ModItems.starSword, 1, 0);
	public static ItemStack missileRod = new ItemStack(ModItems.missileRod, 1, 0);
	public static ItemStack kingKey = new ItemStack(ModItems.kingKey, 1, 0);
	public static ItemStack emerald = new ItemStack(Items.emerald, 1, 0);
	public static ItemStack wrathRune = new ItemStack(ModItems.rune, 1, 13);
	public static ItemStack prideRune = new ItemStack(ModItems.rune, 1, 15);
	public static ItemStack manaPearl = new ItemStack(ModItems.manaResource, 1, 1);
	public static ItemStack netherBrick = new ItemStack(Items.netherbrick, 1, 0);
	public static ItemStack netherWart = new ItemStack(Items.nether_wart, 1, 0);
	public static ItemStack sugar = new ItemStack(Items.sugar, 1, 0);
	public static ItemStack fermentedSpiderEye = new ItemStack(Items.fermented_spider_eye, 1, 0);
	public static ItemStack quartz = new ItemStack(Items.quartz, 1, 0);
	public static ItemStack hellFocus = new ItemStack(ConfigItems.itemFocusHellbat, 1, 0);
	public static ItemStack eldritchTablet = new ItemStack(ConfigItems.itemEldritchObject, 1, 2);
	public static ItemStack bedrock = new ItemStack(Blocks.bedrock, 1, 0);
	public static ItemStack sinisterStone = new ItemStack(ConfigItems.itemCompassStone, 1, 0);
	
	public static void integrateResearch() {
		
		ResearchCategories.registerCategory("ForgottenRelics", new ResourceLocation("forgottenrelics:textures/items/Ghastly_Skull.png"), new ResourceLocation("thaumcraft:textures/gui/gui_researchback.png"));
		
		new ForgottenRelicsResearchItem("GenericTheory", "ForgottenRelics", new AspectList().add(Aspect.MIND, 4).add(Aspect.TOOL, 4).add(Aspect.MAGIC, 4).add(Aspect.CRAFT, 4), 0, 0, 1, eldritchTablet).setSpecial().setRound().setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5") })
		.setParentsHidden("INFUSION", "THAUMIUM")
		.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("SuperpositionRing", "ForgottenRelics", 
			new AspectList().add(Aspect.TRAVEL, 4).add(Aspect.DARKNESS, 3).add(Aspect.ELDRITCH, 6).add(Aspect.EXCHANGE, 4),
			3, 1, 2,
			new ItemStack(Main.itemSuperpositionRing))
			.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ISuperpositionRing")), new ResearchPage("2") })
			.setParents("GenericTheory")
			.setParentsHidden("ELDRITCHMINOR")
			.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("WeatherStone", "ForgottenRelics", 
				new AspectList().add(Aspect.WEATHER, 8).add(Aspect.AIR, 4).add(Aspect.WATER, 4).add(Aspect.EXCHANGE, 3),
				-3, -1, 1,
				new ItemStack(Main.itemWeatherStone))
				.setConcealed()
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IWeatherStone")), new ResearchPage("2") })
				.setParents("GenericTheory")
				.setParentsHidden("DeificAmulet")
				.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("MiningCharm", "ForgottenRelics", 
				new AspectList().add(Aspect.MINE, 5).add(Aspect.TOOL, 4).add(Aspect.MOTION, 4).add(Aspect.MAN, 3),
				3, -1, 1,
				new ItemStack(Main.itemMiningCharm))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IMiningCharm")) })
				.setParents("GenericTheory")
				.setParentsHidden("ELEMENTALPICK")
				.setConcealed()
				.setRound()
				.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("AdvancedMiningCharm", "ForgottenRelics", 
				new AspectList().add(Aspect.MINE, 8).add(Aspect.TOOL, 6).add(Aspect.MOTION, 4).add(Aspect.AURA, 3).add(Aspect.MAGIC, 4),
				5, -3, 2,
				new ItemStack(Main.itemAdvancedMiningCharm))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IAdvancedMiningCharm")) })
				.setParents("MiningCharm")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("AdvancedMiningCharm", gaiaSpirit, miningCharm);
		
		
		new ForgottenRelicsResearchItem("DimensionalMirror", "ForgottenRelics", 
				new AspectList().add(Aspect.TRAVEL, 8).add(Aspect.DARKNESS, 6).add(Aspect.ELDRITCH, 6).add(Aspect.MAGIC, 5),
				1, -3, 2,
				new ItemStack(Main.itemDimensionalMirror))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IDimensionalMirror")), new ResearchPage("2") })
				.setParents("GenericTheory")
				.setParentsHidden("MIRRORHAND", "FOCUSPORTABLEHOLE", "DeificAmulet")
				.setConcealed()
				.setSpecial()
				.registerResearchItem();
		
		ThaumcraftApi.addWarpToResearch("DimensionalMirror", 1);
		
		
		new ForgottenRelicsResearchItem("AncientAegis", "ForgottenRelics", 
				new AspectList().add(Aspect.ARMOR, 8).add(Aspect.EXCHANGE, 6).add(Aspect.METAL, 6).add(Aspect.MAGIC, 4),
				5, 3, 1,
				new ItemStack(Main.itemAncientAegis))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IAncientAegis")), new ResearchPage("2") })
				.setParents("SuperpositionRing")
				.setParentsHidden("RUNICKINETIC", "ENCHFABRIC")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("AncientAegis", dragonStone);
		
		
		new ForgottenRelicsResearchItem("XPTome", "ForgottenRelics", 
				new AspectList().add(Aspect.MIND, 10).add(Aspect.VOID, 8).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 8),
				-5, -3, 2,
				new ItemStack(Main.itemXPTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IXPTome")), new ResearchPage("2") })
				.setParents("WeatherStone")
				.setParentsHidden("JARBRAIN", "NITOR")
				.setConcealed()
				.registerResearchItem();
		
		ThaumcraftApi.addWarpToResearch("XPTome", 2);
		
		
		new ForgottenRelicsResearchItem("SpellbookTheory", "ForgottenRelics", 
				new AspectList().add(Aspect.MIND, 6).add(Aspect.MAGIC, 4).add(Aspect.CRAFT, 4).add(Aspect.ORDER, 3),
				-1, 2, 1,
				new ItemStack(Items.writable_book))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4") })
				.setParents("GenericTheory")
				.setParentsHidden("CRIMSON")
				.setConcealed()
				.setRound()
				.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("DiscordTome", "ForgottenRelics", 
				new AspectList().add(Aspect.MAGIC, 8).add(Aspect.TRAVEL, 6).add(Aspect.DARKNESS, 4).add(Aspect.MIND, 4).add(Aspect.ELDRITCH, 3),
				-2, 5, 2,
				new ItemStack(Main.itemTeleportationTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IDiscordTome")), new ResearchPage("2") })
				.setParents("SpellbookTheory")
				.setParentsHidden("FOCUSPORTABLEHOLE")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("DiscordTome", elementiumIngot);
		ThaumcraftApi.addWarpToResearch("DiscordTome", 3);
		
		
		new ForgottenRelicsResearchItem("FateTome", "ForgottenRelics", 
				new AspectList().add(Aspect.HEAL, 8).add(Aspect.LIFE, 8).add(Aspect.EXCHANGE, 5).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				-4, 2, 3,
				new ItemStack(Main.itemFateTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IFateTome")), new ResearchPage("2") })
				.setParents("SpellbookTheory")
				.setParentsHidden("ELDRITCHMAJOR")
				.setConcealed()
				.setSpecial()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("FateTome", goldLaurel, gaiaSpirit, dragonStone);
		ThaumcraftApi.addWarpToResearch("FateTome", 8);
		
		
		new ForgottenRelicsResearchItem("Thunderpeal", "ForgottenRelics", 
				new AspectList().add(Aspect.ENERGY, 5).add(Aspect.AIR, 4).add(Aspect.MAGIC, 4).add(Aspect.MIND, 3),
				0, 4, 2,
				new ItemStack(Main.itemThunderpeal))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IThunderpeal")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("SpellbookTheory")
				.setParentsHidden("DiscordTome", "FOCUSSHOCK", "FOCALMANIPULATION")
				.setConcealed()
				.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("TelekinesisTome", "ForgottenRelics", 
				new AspectList().add(Aspect.MOTION, 8).add(Aspect.FLIGHT, 6).add(Aspect.TRAP, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				-4, 7, 2,
				new ItemStack(Main.itemTelekinesisTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ITelekinesisTome")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("DiscordTome")
				.setParentsHidden("ELDRITCHMINOR", "Thunderpeal")
				.setHidden()
				.registerResearchItem();

		SuperpositionHandler.setupResearchTriggers("TelekinesisTome", gaiaSpirit, gravityRod, voidSeed);
		
		
		new ForgottenRelicsResearchItem("ObeliskDrainer", "ForgottenRelics", 
				new AspectList().add(Aspect.ELDRITCH, 10).add(Aspect.VOID, 8).add(Aspect.DARKNESS, 8).add(Aspect.EXCHANGE, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				0, 7, 3,
				new ItemStack(Main.itemObeliskDrainer))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IObeliskDrainer")), new ResearchPage("2") })
				.setParents("DiscordTome")
				.setParentsHidden("ELDRITCHMAJOR", "VOIDMETAL", "ENCHFABRIC", "NITOR", "OCULUS")
				.setConcealed()
				.registerResearchItem();

		ThaumcraftApi.addWarpToResearch("ObeliskDrainer", 4);
		
		
		new ForgottenRelicsResearchItem("EldritchSpell", "ForgottenRelics", 
				new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.WEAPON, 8).add(Aspect.ELDRITCH, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				2, 6, 2,
				new ItemStack(Main.itemEldritchSpell))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IEldritchSpell")), new ResearchPage("2") })
				.setParents("ObeliskDrainer")
				.setConcealed()
				.setHidden()
				.setSpecial()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("EldritchSpell", eldritchEye, gaiaSpirit);
		ThaumcraftApi.addWarpToResearch("EldritchSpell", 6);
		
		
		new ForgottenRelicsResearchItem("CrimsonSpell", "ForgottenRelics", 
				new AspectList().add(Aspect.FIRE, 8).add(Aspect.DARKNESS, 8).add(Aspect.ENTROPY, 6).add(Aspect.ELDRITCH, 4).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				3, 8, 3,
				new ItemStack(Main.itemCrimsonSpell))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ICrimsonSpell")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("EldritchSpell")
				.setHidden()
				.setConcealed()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("CrimsonSpell", crimsonRites);
		ThaumcraftApi.addWarpToResearch("CrimsonSpell", 5);
		
		
		new ForgottenRelicsResearchItem("ChaosTome", "ForgottenRelics", 
				new AspectList().add(Aspect.AIR, 8).add(Aspect.WATER, 8).add(Aspect.FIRE, 8).add(Aspect.EARTH, 8).add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				2, 3, 2,
				new ItemStack(Main.itemChaosTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IChaosTome")), new ResearchPage("2") })
				.setParents("EldritchSpell")
				.setParentsHidden("ROD_primal_staff")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("ChaosTome", gaiaSpirit, primalFocus);
		ThaumcraftApi.addWarpToResearch("ChaosTome", 7);
		
		
		new ForgottenRelicsResearchItem("NuclearFury", "ForgottenRelics", 
				new AspectList().add(Aspect.MAGIC, 8).add(Aspect.ENERGY, 8).add(Aspect.LIGHT, 6).add(Aspect.FIRE, 5).add(Aspect.AURA, 5).add(Aspect.MIND, 4),
				4, 5, 3,
				new ItemStack(Main.itemMissileTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("INuclearFury")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("ChaosTome")
				.setParentsHidden("PRIMPEARL")
				.setConcealed()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("NuclearFury", missileRod, terrasteelIngot, alumentum);
		ThaumcraftApi.addWarpToResearch("NuclearFury", 4);
		
		
		new ForgottenRelicsResearchItem("SoulTome", "ForgottenRelics", 
				new AspectList().add(Aspect.SOUL, 8).add(Aspect.TRAP, 8).add(Aspect.DEATH, 6).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				-1, 9, 3,
				new ItemStack(Main.itemSoulTome))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ISoulTome")), new ResearchPage("2") })
				.setParents("ObeliskDrainer")
				.setParentsHidden("JARBRAIN")
				.setHidden()
				.setConcealed()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("SoulTome", pechFocus, gaiaSpirit, jarredBrain, enderEye, alumentum);
		ThaumcraftApi.addWarpToResearch("SoulTome", 3);
		
		
		new ForgottenRelicsResearchItem("LunarFlares", "ForgottenRelics", 
				new AspectList().add(Aspect.LIGHT, 8).add(Aspect.SENSES, 8).add(Aspect.AIR, 6).add(Aspect.ENERGY, 8).add(Aspect.MIND, 4).add(Aspect.MAGIC, 4),
				-5, 9, 3,
				new ItemStack(Main.itemLunarFlares))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ILunarFlares")), new ResearchPage("2") })
				.setParents("TelekinesisTome")
				.setParentsHidden("EldritchSpell", "PRIMPEARL")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("LunarFlares", starSword, terrasteelIngot, enderAir);
		
		
		new ForgottenRelicsResearchItem("OblivionStone", "ForgottenRelics", 
				new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.VOID, 4).add(Aspect.EXCHANGE, 4),
				7, -2, 1,
				new ItemStack(Main.itemOblivionStone))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IOblivionStone")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("SuperpositionRing")
				.setParentsHidden("VOIDMETAL", "SINSTONE")
				.setSecondary()
				.setSpecial()
				.setConcealed()
				.registerResearchItem();

		ThaumcraftApi.addWarpToResearch("OblivionStone", 3);
		
		
		new ForgottenRelicsResearchItem("ChaosCore", "ForgottenRelics", 
				new AspectList().add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8).add(Aspect.EXCHANGE, 8).add(Aspect.ENERGY, 8).add(Aspect.VOID, 8),
				8, -4, 2,
				new ItemStack(Main.itemChaosCore))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IChaosCore")), new ResearchPage("2") })
				.setParents("OblivionStone")
				.setParentsHidden("ALUMENTUM", "AncientAegis")
				.setConcealed()
				.setRound()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("ChaosCore", pixieDust, dragonStone, voidIngot);
		ThaumcraftApi.addWarpToResearch("ChaosCore", 2);
		
		
		new ForgottenRelicsResearchItem("TheParadox", "ForgottenRelics", 
				new AspectList().add(Aspect.ENTROPY, 8).add(Aspect.EXCHANGE, 8).add(Aspect.ORDER, 8).add(Aspect.WEAPON, 6),
				6, 0, 1,
				new ItemStack(Main.itemParadox))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ITheParadox")), new ResearchPage("2") })
				.setParents("SuperpositionRing")
				.setParentsHidden("ChaosCore", "PRIMPEARL")
				.setConcealed()
				.setSecondary()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("TheParadox", chaosCore);
		ThaumcraftApi.addWarpToResearch("TheParadox", 8);
		
		
		new ForgottenRelicsResearchItem("DarkSunRing", "ForgottenRelics", 
				new AspectList().add(Aspect.FIRE, 7).add(Aspect.DARKNESS, 6).add(Aspect.ENERGY, 5).add(Aspect.ARMOR, 4).add(Aspect.EXCHANGE, 3).add(Aspect.MAGIC, 2).add(Aspect.LIGHT, 1),
				7, 2, 3,
				new ItemStack(Main.itemDarkSunRing))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IDarkSunRing")), new ResearchPage("2") })
				.setParents("SuperpositionRing")
				.setParentsHidden("ELDRITCHMINOR", "NITOR", "RUNICCHARGED")
				.setConcealed()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("DarkSunRing", superLavaPendant, blazeRod, gaiaSpirit);
		ThaumcraftApi.addWarpToResearch("DarkSunRing", 3);
		
		
		new ForgottenRelicsResearchItem("DeificAmulet", "ForgottenRelics", 
				new AspectList().add(Aspect.MAN, 6).add(Aspect.HEAL, 4).add(Aspect.LIGHT, 4).add(Aspect.EXCHANGE, 4).add(Aspect.MAGIC, 4),
				3, -4, 3,
				new ItemStack(Main.itemDeificAmulet))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IDeificAmulet")), new ResearchPage("2") })
				.setParents("MiningCharm")
				.setParentsHidden("RUNICEMERGENCY")
				.setHidden()
				.setConcealed()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("DeificAmulet", gaiaSpirit, pixieDust);
		
		
		new ForgottenRelicsResearchItem("ShinyStone", "ForgottenRelics", 
				new AspectList().add(Aspect.HEAL, 8).add(Aspect.EXCHANGE, 6).add(Aspect.CRYSTAL, 4).add(Aspect.MAGIC, 4),
				6, -5, 2,
				new ItemStack(Main.itemShinyStone))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IShinyStone")), new ResearchPage("2") })
				.setParents("DeificAmulet")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("ShinyStone", superGoldenApple, dragonStone, gaiaSpirit);
		
		
		new ForgottenRelicsResearchItem("TerrorCrown", "ForgottenRelics", 
				new AspectList().add(Aspect.ELDRITCH, 8).add(Aspect.WEAPON, 6).add(Aspect.ENTROPY, 6).add(Aspect.ARMOR, 4).add(Aspect.SENSES, 2),
				-6, -5, 3,
				new ItemStack(Main.itemTerrorCrown))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("ITerrorCrown")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("XPTome")
				.setParentsHidden("ELDRITCHMAJOR", "OCULUS")
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("TerrorCrown", netherStar, eldritchEye);
		ThaumcraftApi.addWarpToResearch("TerrorCrown", 4);
		
		
		new ForgottenRelicsResearchItem("OblivionAmulet", "ForgottenRelics", 
				new AspectList().add(Aspect.DARKNESS, 12).add(Aspect.DEATH, 10).add(Aspect.ARMOR, 8).add(Aspect.EXCHANGE, 8).add(Aspect.VOID, 8).add(Aspect.SOUL, 4),
				-3, -4, 3,
				new ItemStack(Main.itemOblivionAmulet))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IOblivionAmulet")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("XPTome")
				.setParentsHidden("OblivionStone", "TerrorCrown", "PRIMPEARL")
				.setConcealed()
				.setSpecial()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("OblivionAmulet", netherStar, eldritchEye, voidIngot);
		ThaumcraftApi.addWarpToResearch("OblivionAmulet", 8);
		
		
		new ForgottenRelicsResearchItem("NebulousCore", "ForgottenRelics", 
				new AspectList().add(Aspect.AURA, 10).add(Aspect.MAGIC, 8).add(Aspect.VOID, 6).add(Aspect.ENERGY, 6).add(Aspect.EXCHANGE, 4),
				0, -5, 3,
				new ItemStack(Main.itemArcanum))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("INebulousCore")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("DeificAmulet")
				.setParentsHidden("PRIMPEARL", "VISAMULET", "SuperpositionRing", "THAUMIUM")
				.setSpecial()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("NebulousCore", superpositionRing, thaumiumIngot);
		
		
		if (RelicsConfigHandler.falseJusticeEnabled) {
		new ForgottenRelicsResearchItem("FalseJustice", "ForgottenRelics", 
				new AspectList().add(Aspect.LIGHT, 8).add(Aspect.DARKNESS, 8).add(Aspect.EXCHANGE, 8).add(Aspect.TOOL, 6).add(Aspect.MAN, 4),
				-7, 0, 3,
				new ItemStack(Main.itemFalseJustice))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IFalseJustice")), new ResearchPage("2") })
				.setParentsHidden("Apotheosis")
				.setRound()
				.setSpecial()
				.registerResearchItem();
		
		ThaumcraftApi.addWarpToResearch("FalseJustice", 4);
		}
		
		
		new ForgottenRelicsResearchItem("Overthrower", "ForgottenRelics", 
				new AspectList().add(Aspect.FIRE, 10).add(Aspect.TRAVEL, 8).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 6).add(Aspect.MIND, 4).add(Aspect.SOUL, 4),
				-5, 5, 2,
				new ItemStack(Main.itemOverthrower))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IOverthrower")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("DiscordTome")
				.setParentsHidden("FOCUSHELLBAT")
				.setSecondary()
				.setSpecial()
				.setConcealed()
				.setHidden()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("Overthrower", hellFocus, netherWart);
		ThaumcraftApi.addWarpToResearch("Overthrower", 3);
		
		if (RelicsConfigHandler.voidGrimoireEnabled) {
		new ForgottenRelicsResearchItem("VoidGrimoire", "ForgottenRelics", 
				new AspectList().add(Aspect.VOID, 10).add(Aspect.DARKNESS, 8).add(Aspect.ELDRITCH, 8).add(Aspect.TRAVEL, 6).add(Aspect.MIND, 4).add(Aspect.ENTROPY, 4),
				-7, 7, 3,
				new ItemStack(Main.itemVoidGrimoire))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IVoidGrimoire")), new ResearchPage("2"), new ResearchPage("3") })
				.setParents("Overthrower")
				.setParentsHidden("OCULUS", "TerrorCrown", "ChaosCore")
				.setLost()
				.setConcealed()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("VoidGrimoire", eldritchTablet, eldritchEye, voidSeed, bedrock, sinisterStone);
		ThaumcraftApi.addWarpToResearch("VoidGrimoire", 4);
		}
		
		new ForgottenRelicsResearchItem("PechFocus", "ForgottenRelics", 
				new AspectList().add(Aspect.EXCHANGE, 8).add(Aspect.ENTROPY, 6).add(Aspect.MAGIC, 4).add(Aspect.SENSES, 4),
				-1, -2, 2,
				new ItemStack(ConfigItems.itemFocusPech))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IPechFocus")), new ResearchPage("2") })
				.setParents("WeatherStone")
				.setParentsHidden("FOCUSFIRE", "FOCUSFROST")
				.setHidden()
				.setSecondary()
				.registerResearchItem();
		
		SuperpositionHandler.setupResearchTriggers("PechFocus", netherWart, ghastTear, fermentedSpiderEye, sugar, blazePowder);
		
		
		new ForgottenRelicsResearchItem("DiscordRing", "ForgottenRelics", 
				new AspectList().add(Aspect.TOOL, 8).add(Aspect.TRAVEL, 8).add(Aspect.MAGIC, 8),
				3, -6, 2,
				new ItemStack(Main.itemDiscordRing))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IDiscordRing")), new ResearchPage("2") })
				.setParents("DeificAmulet")
				.setParentsHidden("DiscordTome")
				.setSecondary()
				.setConcealed()
				.registerResearchItem();
		
		
		new ForgottenRelicsResearchItem("Apotheosis", "ForgottenRelics", 
				new AspectList().add(Aspect.MAGIC, 16).add(Aspect.WEAPON, 12).add(Aspect.ENERGY, 12).add(Aspect.LIGHT, 12).add(Aspect.MIND, 8),
				-2, 7, 2,
				new ItemStack(Main.itemApotheosis))
				.setPages(new ResearchPage[]{ new ResearchPage("1"), new ResearchPage(recipes.get("IApotheosis")), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4") })
				.setParents("DiscordTome")
				.setParentsHidden("LunarFlares", "SoulTome", "NuclearFury", "CrimsonSpell", "FateTome", "NebulousCore", "OblivionAmulet", "TheParadox", "Overthrower", "DarkSunRing", "ShinyStone", "DimensionalMirror")
				.setSpecial()
				.setRound()
				.setLost()
				.registerResearchItem();
		
		// It's unlocking is actually triggered in RelicsEventHandler
		ThaumcraftApi.addWarpToResearch("Apotheosis", 10);
		
		
		ThaumcraftApi.addWarpToItem(chaosTome, 4);
		ThaumcraftApi.addWarpToItem(fateTome, 6);
		ThaumcraftApi.addWarpToItem(theParadox, 8);
		ThaumcraftApi.addWarpToItem(oblivionAmulet, 4);
		ThaumcraftApi.addWarpToItem(falseJustice, 10);
		ThaumcraftApi.addWarpToItem(obeliskDrainer, 3);
		ThaumcraftApi.addWarpToItem(overthrower, 1);
		ThaumcraftApi.addWarpToItem(terrorCrown, 3);
		
		//ThaumcraftApi.addWarpToResearch("JARBRAIN", 3);
        //ThaumcraftApi.addWarpToItem(new ItemStack(ConfigBlocks.blockJar, 1, 1), 1);
		
		//System.out.println("Research list: " + ResearchCategories.getResearchList("ForgottenRelics").research);
	}
	
	public static void integrateInfusion() {
		
		RelicsResearchRegistry.recipes.put("ISuperpositionRing", ThaumcraftApi.addInfusionCraftingRecipe("SuperpositionRing", 
			superpositionRing, 4, new AspectList()
			.add(Aspect.ELDRITCH, 32).add(Aspect.EXCHANGE, 30).add(Aspect.TRAVEL, 24).add(Aspect.DARKNESS, 20).add(Aspect.ARMOR, 8),
			blankRing,
			new ItemStack[]{ enderEye, enderAir, voidSeed, salisMundus, emerald, salisMundus, voidSeed, enderAir }));
		
		RelicsResearchRegistry.recipes.put("IWeatherStone", ThaumcraftApi.addInfusionCraftingRecipe("WeatherStone", 
				weatherStone, 4, new AspectList()
				.add(Aspect.WEATHER, 16).add(Aspect.AIR, 24).add(Aspect.WATER, 10).add(Aspect.EXCHANGE, 12).add(Aspect.ENERGY, 20),
				arcaneStone,
				new ItemStack[]{ gaiaSpirit, ghastTear, airRune, knowledgeFragment, nitor, knowledgeFragment, airRune, ghastTear }));
		
		RelicsResearchRegistry.recipes.put("IMiningCharm", ThaumcraftApi.addInfusionCraftingRecipe("MiningCharm", 
				miningCharm, 1, new AspectList()
				.add(Aspect.MINE, 24).add(Aspect.TOOL, 20).add(Aspect.MOTION, 16).add(Aspect.METAL, 12).add(Aspect.MAGIC, 16),
				reachRing,
				new ItemStack[]{ elementalPickaxe, earthShard, salisMundus, goldIngot, speedPotionII, earthShard, salisMundus, goldIngot }));
		
		RelicsResearchRegistry.recipes.put("IDimensionalMirror", ThaumcraftApi.addInfusionCraftingRecipe("DimensionalMirror", 
				dimensionalMirror, 6, new AspectList()
				.add(Aspect.TRAVEL, 40).add(Aspect.DARKNESS, 20).add(Aspect.MAGIC, 24).add(Aspect.VOID, 16).add(Aspect.ELDRITCH, 10),
				handMirror,
				new ItemStack[]{ gaiaSpirit, glowstone, dragonStone, enderAir, portableHole, enderAir, dragonStone, glowstone }));
		
		RelicsResearchRegistry.recipes.put("IAdvancedMiningCharm", ThaumcraftApi.addInfusionCraftingRecipe("AdvancedMiningCharm", 
				advancedMiningCharm, 8, new AspectList()
				.add(Aspect.MINE, 64).add(Aspect.AURA, 40).add(Aspect.CRYSTAL, 48).add(Aspect.EXCHANGE, 40).add(Aspect.MOTION, 32).add(Aspect.MAGIC, 36).add(Aspect.TOOL, 50),
				miningCharm,
				new ItemStack[]{ primalCharm, etherealEssence, elementiumIngot, dragonStone, gaiaSpirit, etherealEssence, voidPickaxe, etherealEssence, gaiaSpirit, dragonStone, elementiumIngot, etherealEssence }));
		
		RelicsResearchRegistry.recipes.put("IAncientAegis", ThaumcraftApi.addInfusionCraftingRecipe("AncientAegis", 
				ancientAegis, 5, new AspectList()
				.add(Aspect.ARMOR, 45).add(Aspect.EXCHANGE, 30).add(Aspect.HEAL, 24).add(Aspect.MAGIC, 30).add(Aspect.METAL, 20),
				tectonicGirdle,
				new ItemStack[]{ dragonStone, healPotionII, goldIngot, enchantedFabric, kineticRunicGirdle, enchantedFabric, goldIngot, healPotionII }));
		
		RelicsResearchRegistry.recipes.put("IDarkSunRing", ThaumcraftApi.addInfusionCraftingRecipe("DarkSunRing", 
				darkSunRing, 8, new AspectList()
				.add(Aspect.FIRE, 60).add(Aspect.ARMOR, 48).add(Aspect.EXCHANGE, 36).add(Aspect.DARKNESS, 40).add(Aspect.MAGIC, 25),
				runicRingCharged,
				new ItemStack[]{ superLavaPendant, elementiumIngot, blazeRod, cinderPearl, voidSeed, nitor, voidSeed, cinderPearl, blazeRod, elementiumIngot }));
		
		RelicsResearchRegistry.recipes.put("IDeificAmulet", ThaumcraftApi.addInfusionCraftingRecipe("DeificAmulet", 
				deificAmulet, 4, new AspectList()
				.add(Aspect.MAN, 30).add(Aspect.LIGHT, 42).add(Aspect.AURA, 16).add(Aspect.MAGIC, 25).add(Aspect.HEAL, 8).add(Aspect.EXCHANGE, 20),
				runicAmuletAdv,
				new ItemStack[]{ lavaPendant, gaiaSpirit, pixieDust, gaiaSpirit, primalCharm, gaiaSpirit, pixieDust, gaiaSpirit }));
		
		RelicsResearchRegistry.recipes.put("IChaosCore", ThaumcraftApi.addInfusionCraftingRecipe("ChaosCore", 
				chaosCore, 10, new AspectList()
				.add(Aspect.ENTROPY, 25).add(Aspect.ORDER, 25).add(Aspect.EXCHANGE, 16).add(Aspect.MAGIC, 32),
				primalCharm,
				new ItemStack[]{ dragonStone, pixieDust, voidIngot, pixieDust, alumentum, pixieDust, voidIngot, pixieDust }));
	
		RelicsResearchRegistry.recipes.put("ITheParadox", ThaumcraftApi.addInfusionCraftingRecipe("TheParadox", 
				theParadox, 32, new AspectList()
				.add(Aspect.AIR, 64).add(Aspect.FIRE, 64).add(Aspect.WATER, 64).add(Aspect.EARTH, 64).add(Aspect.ORDER, 64).add(Aspect.ENTROPY, 64).add(Aspect.VOID, 48).add(Aspect.WEAPON, 32).add(Aspect.MAGIC, 24).add(Aspect.EXCHANGE, 36),
				voidSword,
				new ItemStack[]{ chaosCore, airShard, fireShard, waterShard, primordialPearl, earthShard, orderShard, entropyShard }));
		
		RelicsResearchRegistry.recipes.put("IShinyStone", ThaumcraftApi.addInfusionCraftingRecipe("ShinyStone", 
				shinyStone, 8, new AspectList()
				.add(Aspect.HEAL, 40).add(Aspect.LIFE, 32).add(Aspect.TRAP, 24).add(Aspect.EXCHANGE, 24).add(Aspect.MAGIC, 24).add(Aspect.CRYSTAL, 16),
				dragonStone,
				new ItemStack[]{ primalCharm, goldIngot, gaiaSpirit, nitor, superGoldenApple, nitor, gaiaSpirit, goldIngot }));
		
		RelicsResearchRegistry.recipes.put("IOblivionAmulet", ThaumcraftApi.addInfusionCraftingRecipe("OblivionAmulet", 
				oblivionAmulet, 16, new AspectList()
				.add(Aspect.DEATH, 64).add(Aspect.EXCHANGE, 50).add(Aspect.VOID, 72).add(Aspect.DARKNESS, 100).add(Aspect.ELDRITCH, 24).add(Aspect.MAGIC, 32).add(Aspect.FIRE, 16),
				bloodPendant,
				new ItemStack[]{ netherStar, blazePowder, voidIngot, blazePowder, eldritchEye, blazePowder, voidIngot, blazePowder }));
		
		RelicsResearchRegistry.recipes.put("IXPTome", ThaumcraftApi.addInfusionCraftingRecipe("XPTome", 
				XPTome, 4, new AspectList()
				.add(Aspect.SOUL, 32).add(Aspect.MIND, 40).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 24),
				writableBook,
				new ItemStack[]{ jarredBrain, salisMundus, amber, salisMundus, nitor, salisMundus, amber, salisMundus }));
		
		RelicsResearchRegistry.recipes.put("INebulousCore", ThaumcraftApi.addInfusionCraftingRecipe("NebulousCore", 
				nebulousCore, 12, new AspectList()
				.add(Aspect.AURA, 80).add(Aspect.MAGIC, 128).add(Aspect.VOID, 32).add(Aspect.ELDRITCH, 50).add(Aspect.DARKNESS, 40).add(Aspect.TRAVEL, 24).add(Aspect.EXCHANGE, 48),
				primordialPearl,
				new ItemStack[]{ greatVisAmulet, etherealEssence, gaiaSpirit, thaumiumIngot, enderAir, etherealEssence, superpositionRing, etherealEssence, enderAir, thaumiumIngot, gaiaSpirit, etherealEssence }));
		
		RelicsResearchRegistry.recipes.put("IDiscordTome", ThaumcraftApi.addInfusionCraftingRecipe("DiscordTome", 
				discordTome, 5, new AspectList()
				.add(Aspect.TRAVEL, 50).add(Aspect.DARKNESS, 30).add(Aspect.VOID, 30).add(Aspect.MAGIC, 45).add(Aspect.MIND, 24),
				writableBook,
				new ItemStack[]{ primalCharm, elementiumIngot, enderPearl, enderAir, salisMundus, portableHole, salisMundus, enderAir, enderPearl, elementiumIngot }));
		
		RelicsResearchRegistry.recipes.put("IObeliskDrainer", ThaumcraftApi.addInfusionCraftingRecipe("ObeliskDrainer", 
				obeliskDrainer, 8, new AspectList()
				.add(Aspect.ELDRITCH, 72).add(Aspect.VOID, 64).add(Aspect.DARKNESS, 64).add(Aspect.ENERGY, 40).add(Aspect.MAGIC, 52).add(Aspect.AURA, 25).add(Aspect.EXCHANGE, 32),
				writableBook,
				new ItemStack[]{ nitor, knowledgeFragment, voidIngot, primalCharm, enchantedFabric, salisMundus, eldritchEye, salisMundus, enchantedFabric, primalCharm, voidIngot, knowledgeFragment }));
		
		RelicsResearchRegistry.recipes.put("ITelekinesisTome", ThaumcraftApi.addInfusionCraftingRecipe("TelekinesisTome", 
				telekinesisTome, 8, new AspectList()
				.add(Aspect.MOTION, 32).add(Aspect.TRAP, 48).add(Aspect.MAGIC, 64).add(Aspect.DARKNESS, 24).add(Aspect.AIR, 25).add(Aspect.ENERGY, 40).add(Aspect.TOOL, 16),
				writableBook,
				new ItemStack[]{ primalCharm, voidSeed, shockFocus, gaiaSpirit, salisMundus, gravityRod, salisMundus, gaiaSpirit, shockFocus, voidSeed }));
		
		RelicsResearchRegistry.recipes.put("ISoulTome", ThaumcraftApi.addInfusionCraftingRecipe("SoulTome", 
				soulTome, 10, new AspectList()
				.add(Aspect.SOUL, 80).add(Aspect.VOID, 60).add(Aspect.MAGIC, 45).add(Aspect.TRAP, 60).add(Aspect.DEATH, 32).add(Aspect.DARKNESS, 48).add(Aspect.EXCHANGE, 52),
				writableBook,
				new ItemStack[]{ pechFocus, alumentum, gaiaSpirit, enderEye, gaiaSpirit, salisMundus, jarredBrain, salisMundus, gaiaSpirit, enderEye, gaiaSpirit, alumentum }));
		
		RelicsResearchRegistry.recipes.put("IFateTome", ThaumcraftApi.addInfusionCraftingRecipe("FateTome", 
				fateTome, 20, new AspectList()
				.add(Aspect.HEAL, 40).add(Aspect.LIFE, 64).add(Aspect.EXCHANGE, 32).add(Aspect.MAN, 24).add(Aspect.MIND, 16).add(Aspect.SOUL, 36).add(Aspect.ORDER, 20),
				writableBook,
				new ItemStack[]{ goldLaurel, dragonStone, gaiaSpirit, nitor, gaiaSpirit, salisMundus, primalCharm, salisMundus, gaiaSpirit, nitor, gaiaSpirit, dragonStone }));
		
		RelicsResearchRegistry.recipes.put("IEldritchSpell", ThaumcraftApi.addInfusionCraftingRecipe("EldritchSpell", 
				eldritchSpell, 8, new AspectList()
				.add(Aspect.ELDRITCH, 72).add(Aspect.DARKNESS, 64).add(Aspect.DEATH, 40).add(Aspect.MAGIC, 36).add(Aspect.MIND, 24).add(Aspect.WEAPON, 12).add(Aspect.TAINT, 24),
				writableBook,
				new ItemStack[]{ primalCharm, voidIngot, fireball, eldritchEye, bottledTaint, salisMundus, gaiaSpirit, salisMundus, bottledTaint, eldritchEye, fireball, voidIngot }));
		
		RelicsResearchRegistry.recipes.put("IChaosTome", ThaumcraftApi.addInfusionCraftingRecipe("ChaosTome", 
				chaosTome, 12, new AspectList()
				.add(Aspect.AIR, 100).add(Aspect.WATER, 100).add(Aspect.EARTH, 100).add(Aspect.FIRE, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100).add(Aspect.MAGIC, 256).add(Aspect.EXCHANGE, 72).add(Aspect.ELDRITCH, 36).add(Aspect.DARKNESS, 40),
				writableBook,
				new ItemStack[]{ primalFocus, salisMundus, elementiumIngot, primalFocus, gaiaSpirit, salisMundus, primalFocus, salisMundus, gaiaSpirit, primalFocus, elementiumIngot, salisMundus }));
		
		RelicsResearchRegistry.recipes.put("INuclearFury", ThaumcraftApi.addInfusionCraftingRecipe("NuclearFury", 
				nuclearFury, 10, new AspectList()
				.add(Aspect.LIGHT, 72).add(Aspect.FIRE, 80).add(Aspect.ENTROPY, 36).add(Aspect.MAGIC, 64).add(Aspect.AURA, 16).add(Aspect.ENERGY, 48).add(Aspect.SENSES, 32).add(Aspect.VOID, 40),
				chaosTome,
				new ItemStack[]{ missileRod, alumentum, terrasteelIngot, primalCharm, gaiaSpirit, etherealEssence, primordialPearl, etherealEssence, gaiaSpirit, primalCharm, terrasteelIngot, alumentum }));
		
		RelicsResearchRegistry.recipes.put("ICrimsonSpell", ThaumcraftApi.addInfusionCraftingRecipe("CrimsonSpell", 
				crimsonSpell, 8, new AspectList()
				.add(Aspect.FIRE, 72).add(Aspect.ENTROPY, 64).add(Aspect.MAGIC, 54).add(Aspect.EXCHANGE, 24).add(Aspect.SENSES, 32).add(Aspect.MIND, 24).add(Aspect.ENERGY, 48),
				writableBook,
				new ItemStack[]{ crimsonRites, blazePowder, enchantedFabric, primalCharm, redstone, salisMundus, eldritchEye, salisMundus, redstone, primalCharm, enchantedFabric, blazePowder }));
		
		RelicsResearchRegistry.recipes.put("ILunarFlares", ThaumcraftApi.addInfusionCraftingRecipe("LunarFlares", 
				lunarFlares, 12, new AspectList()
				.add(Aspect.AIR, 64).add(Aspect.ENERGY, 96).add(Aspect.MAGIC, 120).add(Aspect.MIND, 32).add(Aspect.ORDER, 40).add(Aspect.MOTION, 16).add(Aspect.WEAPON, 24).add(Aspect.LIGHT, 48),
				writableBook,
				new ItemStack[]{ primalCharm, enderAir, terrasteelIngot, starSword, gaiaSpirit, salisMundus, primordialPearl, salisMundus, gaiaSpirit, starSword, terrasteelIngot, enderAir }));
		
		RelicsResearchRegistry.recipes.put("IApotheosis", ThaumcraftApi.addInfusionCraftingRecipe("Apotheosis", 
				apotheosis, 48, new AspectList()
				.add(Aspect.WEAPON, 128).add(Aspect.TOOL, 72).add(Aspect.EXCHANGE, 54).add(Aspect.MAGIC, 80).add(Aspect.LIGHT, 64).add(Aspect.METAL, 32).add(Aspect.MIND, 48).add(Aspect.ORDER, 40),
				writableBook,
				new ItemStack[]{ kingKey, knowledgeFragment, goldIngot, primalCharm, gaiaSpirit, salisMundus, primordialPearl, salisMundus, gaiaSpirit, primalCharm, goldIngot, knowledgeFragment }));
		
		RelicsResearchRegistry.recipes.put("IFalseJustice", ThaumcraftApi.addInfusionCraftingRecipe("FalseJustice", 
				falseJustice, 24, new AspectList()
				.add(Aspect.LIGHT, 120).add(Aspect.GREED, 24).add(Aspect.WEAPON, 40).add(Aspect.TOOL, 36).add(Aspect.MAN, 52).add(Aspect.MAGIC, 36).add(Aspect.EXCHANGE, 72).add(Aspect.SOUL, 48).add(Aspect.DARKNESS, 45),
				writableBook,
				new ItemStack[]{ nitor, goldIngot, netherStar, knowledgeFragment, primordialPearl, knowledgeFragment, netherStar, goldIngot }));
		
		RelicsResearchRegistry.recipes.put("ITerrorCrown", ThaumcraftApi.addInfusionCraftingRecipe("TerrorCrown", 
				terrorCrown, 8, new AspectList()
				.add(Aspect.ELDRITCH, 50).add(Aspect.WEAPON, 32).add(Aspect.SENSES, 24).add(Aspect.DEATH, 42).add(Aspect.SOUL, 16).add(Aspect.ENTROPY, 20).add(Aspect.MAGIC, 36).add(Aspect.ARMOR, 16),
				revealingGoggles,
				new ItemStack[]{ netherStar, prideRune, goldIngot, gaiaSpirit, enderEye, gaiaSpirit, goldIngot, wrathRune }));
		
		RelicsResearchRegistry.recipes.put("IThunderpeal", ThaumcraftApi.addInfusionCraftingRecipe("Thunderpeal", 
				thunderpeal, 4, new AspectList()
				.add(Aspect.ENERGY, 32).add(Aspect.MAGIC, 24).add(Aspect.AIR, 24).add(Aspect.MIND, 10).add(Aspect.FIRE, 16),
				writableBook,
				new ItemStack[]{ primalCharm, pixieDust, manaPearl, salisMundus, shockFocus, salisMundus, manaPearl, pixieDust }));
		
		RelicsResearchRegistry.recipes.put("IOverthrower", ThaumcraftApi.addInfusionCraftingRecipe("Overthrower", 
				overthrower, 8, new AspectList()
				.add(Aspect.FIRE, 70).add(Aspect.TRAVEL, 40).add(Aspect.MAGIC, 36).add(Aspect.MIND, 24).add(Aspect.DARKNESS, 48),
				writableBook,
				new ItemStack[]{ primalCharm, blazePowder, netherBrick, salisMundus, netherWart, enderEye, netherWart, salisMundus, netherBrick, blazePowder }));
		
		RelicsResearchRegistry.recipes.put("IPechFocus", ThaumcraftApi.addInfusionCraftingRecipe("PechFocus", 
				pechFocus, 4, new AspectList()
				.add(Aspect.EXCHANGE, 25).add(Aspect.POISON, 20).add(Aspect.MAGIC, 16).add(Aspect.ENTROPY, 12).add(Aspect.SENSES, 8),
				primalCharm,
				new ItemStack[]{ emerald, blazePowder, quartz, ghastTear, sugar, quartz, redstone, netherWart, quartz, fermentedSpiderEye }));
		
		RelicsResearchRegistry.recipes.put("IDiscordRing", ThaumcraftApi.addInfusionCraftingRecipe("DiscordRing", 
				discordRing, 2, new AspectList()
				.add(Aspect.TOOL, 16).add(Aspect.TRAVEL, 16).add(Aspect.MAGIC, 12).add(Aspect.EXCHANGE, 8).add(Aspect.VOID, 8),
				blankRing,
				new ItemStack[]{ dragonStone, salisMundus, elementiumIngot, salisMundus, gaiaSpirit, salisMundus, elementiumIngot, salisMundus }));
		
		RelicsResearchRegistry.recipes.put("IVoidGrimoire", ThaumcraftApi.addInfusionCraftingRecipe("VoidGrimoire", 
				voidGrimoire, 10, new AspectList()
				.add(Aspect.VOID, 100).add(Aspect.DARKNESS, 52).add(Aspect.TRAVEL, 24).add(Aspect.MAGIC, 40).add(Aspect.MIND, 32).add(Aspect.ELDRITCH, 40).add(Aspect.EXCHANGE, 16).add(Aspect.ENTROPY, 20),
				overthrower,
				new ItemStack[]{ gaiaSpirit, voidSeed, knowledgeFragment, voidSeed, eldritchEye, voidSeed, knowledgeFragment, voidSeed }));
		
		RelicsResearchRegistry.recipes.put("IOblivionStone", ThaumcraftApi.addInfusionCraftingRecipe("OblivionStone", 
				oblivionStone, 4, new AspectList()
				.add(Aspect.VOID, 16).add(Aspect.DARKNESS, 16).add(Aspect.ENTROPY, 12).add(Aspect.EXCHANGE, 8).add(Aspect.MAGIC, 8),
				sinisterStone,
				new ItemStack[]{ enderEye, voidSeed, voidIngot, voidSeed, nitor, voidSeed, voidIngot, voidSeed }));
	}

}
