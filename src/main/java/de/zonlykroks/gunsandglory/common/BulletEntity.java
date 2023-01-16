package de.zonlykroks.gunsandglory.common;

import de.zonlykroks.gunsandglory.init.EntityInit;
import de.zonlykroks.gunsandglory.util.RegistryManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BulletEntity extends ProjectileEntity implements IAnimatable{

    private IBulletType type;
    private IBulletCaliber caliber;

    private Vec3d initialPos = new Vec3d(0, 0, 0);

    protected int ticksInAir;

    public BulletEntity(EntityType<BulletEntity> entityType, World world) {
        super(entityType,world);
    }

    @Override
    public void initDataTracker() {
    }

    @Override
    public void tick() {
        super.tick();

        ++this.ticksInAir;

        if (this.ticksInAir >= 400) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }

        Vec3d current = this.getVelocity();
        this.setVelocity(current.x, current.y - caliber.verticalDrag(this.getBlockPos(),initialPos), current.z);

        this.caliber.tick(this);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        entity.damage(DamageSource.thrownProjectile(entity,this.getOwner()),caliber.applyEntityDamage(entity,this,initialPos,getPos()));

        entity.setOnFireFor(type.fireInSeconds());
        this.world.createExplosion(this,getX(),getY(),getZ(),type.explosionStrength(), Explosion.DestructionType.DESTROY);

        entity.timeUntilRegen = 0;

        caliber.onEntityHit(this,entityHitResult);

        if (!this.world.isClient) {
            this.remove(Entity.RemovalReason.DISCARDED);
        }
        /*double dist = world.isClient ? 0 : Math.sqrt(distSquared(initialPos.x, initialPos.z, getX(), getZ()));
        double modifiedDamage = this.damage * Math.pow(1 - this.damageDroppoff, dist);*/
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        this.world.createExplosion(this,getX(),getY(),getZ(),type.explosionStrength(), Explosion.DestructionType.DESTROY);

        this.caliber.onBlockHit(this,blockHitResult);

        if(!this.world.isClient)
            this.remove(RemovalReason.DISCARDED);
    }

    public void initEntity(Identifier caliber, Identifier bulletType,LivingEntity user) {
        this.caliber = RegistryManager.BULLET_CALIBER_SIMPLE_REGISTRY.get(caliber);
        this.type = RegistryManager.BULLET_TYPE_SIMPLE_REGISTRY.get(bulletType);

        this.setOwner(user);
        this.initialPos = new Vec3d(user.getX(),user.getY(),user.getZ());

        this.setPos(user.getX(),user.getEyeY(),user.getZ());
        this.setVelocity(user,user.getPitch(),user.getHeadYaw(),this.caliber.roll(), this.caliber.velocity(),this.caliber.divergence());
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double distance) {
        return true;
    }

    public void setVelocity(double x,double y, double z,float speed,float divergence) {
        super.setVelocity(x,y,z,speed,divergence);
        this.ticksInAir = 0;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return GeckoLibUtil.createFactory(this);
    }
}
