package com.integral.forgottenrelics.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class RelicsTeleporter extends Teleporter {
	
	private final WorldServer worldServerInstance;

	private double XX;
	private double YY;
	private double ZZ;
	
	public RelicsTeleporter(WorldServer world, double x, double y, double z) {
		super(world);
		
		this.worldServerInstance = world;
		
		this.XX = x;
		this.YY = y;
		this.ZZ = z;
	}
	
	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float yaw) {
		//super.placeInPortal(entity, x, y, z, yaw);
		
		EntityLivingBase base = (EntityLivingBase) entity;
		entity.setLocationAndAngles(this.XX, this.YY, this.ZZ, entity.rotationYaw, 0.0F);
		
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float yaw) {
		//super.placeInExistingPortal(entity, x, y, z, yaw);
		
		EntityLivingBase base = (EntityLivingBase) entity;
		entity.setLocationAndAngles(this.XX, this.YY, this.ZZ, entity.rotationYaw, 0.0F);
		
		return true;
	}
	
	@Override
	public boolean makePortal(Entity entity) {
		//super.makePortal(entity);
		
		EntityLivingBase base = (EntityLivingBase) entity;
		entity.setLocationAndAngles(this.XX, this.YY, this.ZZ, entity.rotationYaw, 0.0F);
		
		return true;
	}
	
	@Override
	public void removeStalePortalLocations(long p_85189_1_){ 
		//super.removeStalePortalLocations(p_85189_1_);
	}
}
