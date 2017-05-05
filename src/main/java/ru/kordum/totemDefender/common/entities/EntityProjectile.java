package ru.kordum.totemDefender.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectile extends EntityThrowable {
    private TileEntityTotem owner;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public EntityProjectile(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityProjectile(World world) {
        super(world);
    }

    public EntityProjectile(World world, EntityLivingBase entityLiving) {
        super(world, entityLiving);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    protected void onImpact(RayTraceResult traceResult) {
        if (!worldObj.isRemote) {
            if (traceResult.entityHit != null && traceResult.entityHit instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) traceResult.entityHit;
                owner.attack(entity);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                worldObj.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        }
        setDead();
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    protected float getGravityVelocity() {
        return 0;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public void setOwner(TileEntityTotem owner) {
        this.owner = owner;
    }
}