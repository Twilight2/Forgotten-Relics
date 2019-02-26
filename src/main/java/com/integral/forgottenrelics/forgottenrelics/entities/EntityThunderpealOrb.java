package com.integral.forgottenrelics.entities;

import java.util.ArrayList;

import com.integral.forgottenrelics.handlers.DamageRegistryHandler;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.EntityUtils;
import vazkii.botania.common.core.helper.Vector3;

public class EntityThunderpealOrb extends EntityThrowable
{
    public int area;
    public float damage;
    
    public EntityThunderpealOrb(final World par1World) {
        super(par1World);
        this.area = 4;
        this.damage = RelicsConfigHandler.damageThunderpealBolt;
    }
    
    public EntityThunderpealOrb(final World par1World, final EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.area = 4;
        this.damage = RelicsConfigHandler.damageThunderpealBolt;
    }
    
    public void shootLightning(final World world, Entity entity, final double xx, final double yy, final double zz, boolean main) {
        Vector3 initPos = Vector3.fromEntity(entity);
        Vector3 finalPos = new Vector3(xx, yy, zz);
        
		Vector3 diffVec = finalPos.copy().sub(initPos);
		Vector3 motionVec = initPos.add(diffVec.copy().multiply((1/this.getDistance(finalPos.x, finalPos.y, finalPos.z))*0.5));
		 
		float curve = 0.5F;
		curve *= (1/entity.getDistance(finalPos.x, finalPos.y, finalPos.z))*24.0F;
		
		float width;
		if (main)
			width = 0.075F;
		else
			width = 0.04F;

   		SuperpositionHandler.imposeLightning(entity.worldObj, entity.dimension, motionVec.x, motionVec.y, motionVec.z, finalPos.x, finalPos.y, finalPos.z, 20, curve, (int) (entity.getDistance(finalPos.x, finalPos.y, finalPos.z)*1.6F), 0, width);
    }
    
    protected float getGravityVelocity() {
        return 0.05f;
    }
    
    protected void onImpact(final MovingObjectPosition mop) {
    	Entity hitEntity = null;
    	if (mop.entityHit != null) {
    		hitEntity = mop.entityHit;
    		hitEntity.attackEntityFrom(new DamageRegistryHandler.DamageSourceTLightning(this.getThrower()), RelicsConfigHandler.damageThunderpealDirect);
    		hitEntity.hurtResistantTime = 0;
    	}
    	
        if (!this.worldObj.isRemote) {
            ArrayList<Entity> list = (ArrayList<Entity>)EntityUtils.getEntitiesInRange(this.worldObj, this.posX, this.posY, this.posZ, (Entity)this, (Class)EntityLivingBase.class, (double)this.area);
            
            if (list.contains(this.getThrower()))
            	list.remove(this.getThrower());
            
            if (list.contains(hitEntity))
            	list.remove(hitEntity);
            
            for (Entity e : list) {
                if (true) {
                	Vector3 targetVec = Vector3.fromEntityCenter(e);
                	this.shootLightning(this.worldObj, this, targetVec.x, targetVec.y, targetVec.z, true);
                	e.hurtResistantTime = 0;
                    e.attackEntityFrom(new DamageRegistryHandler.DamageSourceTLightning(this.getThrower()), this.damage);
                    
                    ArrayList<Entity> exlist = (ArrayList<Entity>)EntityUtils.getEntitiesInRange(e.worldObj, e.posX, e.posY, e.posZ, e, (Class)EntityLivingBase.class, 4.0D);
                    
                    if (exlist.contains(this.getThrower()))
                    	exlist.remove(this.getThrower());
                    
                    if (exlist.contains(e))
                    	exlist.remove(e);
                    
                    while (exlist.size() > 3)
                    	exlist.remove((int)(Math.random() * exlist.size()));
                    
                    for (Entity ex : exlist) {
                    	Vector3 targetVecX = Vector3.fromEntityCenter(ex);
                    	this.shootLightning(this.worldObj, e, targetVecX.x, targetVecX.y, targetVecX.z, false);
                    	ex.hurtResistantTime = 0;
                    	ex.attackEntityFrom(new DamageRegistryHandler.DamageSourceTLightning(this.getThrower()), this.damage/2.0F);
                    }
                    
                }
            }
            
            SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 2.5F);
            SuperpositionHandler.imposeBurst(this.worldObj, this.dimension, this.posX, this.posY, this.posZ, 2.5F);
            
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "thaumcraft:shock", 2.0f, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f);
        }
        
        this.setDead();
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted > 500) {
            this.setDead();
        }
    }
    
    public float getShadowSize() {
        return 0.1f;
    }
    
    @Override
    public boolean attackEntityFrom(final DamageSource p_70097_1_, final float p_70097_2_) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        this.setBeenAttacked();
        if (p_70097_1_.getEntity() != null) {
            final Vec3 vec3 = p_70097_1_.getEntity().getLookVec();
            if (vec3 != null) {
                this.motionX = vec3.xCoord;
                this.motionY = vec3.yCoord;
                this.motionZ = vec3.zCoord;
                this.motionX *= 0.9;
                this.motionY *= 0.9;
                this.motionZ *= 0.9;
                this.worldObj.playSoundAtEntity((Entity)this, "thaumcraft:zap", 1.0f, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f);
            }
            return true;
        }
        return false;
    }
}
