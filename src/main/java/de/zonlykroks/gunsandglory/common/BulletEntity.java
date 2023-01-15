package de.zonlykroks.gunsandglory.common;

import de.zonlykroks.gunsandglory.init.EntityInit;
import de.zonlykroks.gunsandglory.util.RegistryUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BulletEntity extends PersistentProjectileEntity implements IAnimatable{

    private IBulletType type;
    private IBulletCaliber caliber;

    private LivingEntity owner;

    private Vec3d initialPos = new Vec3d(0, 0, 0);

    protected int ticksInAir;

    public BulletEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType,world);
    }

    protected BulletEntity(Identifier caliber, Identifier bulletType, World world, LivingEntity owner) {
        super(EntityInit.BULLET,world);
        this.type = RegistryUtils.BULLET_TYPE_REGISTRY.get(caliber);
        this.caliber = RegistryUtils.BULLET_CALIBER_REGISTRY.get(bulletType);
        this.owner = owner;
        this.setOwner(owner);
        this.initialPos = new Vec3d(owner.getX(),owner.getY(),owner.getZ());
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

        /*if (this.world.isClient) {
            double d2 = this.getX() + (this.random.nextDouble()) * (double) this.getWidth() * 0.5D;
            double f2 = this.getZ() + (this.random.nextDouble()) * (double) this.getWidth() * 0.5D;
            this.world.addParticle(ParticleTypes.SMOKE, true, d2, this.getY(), f2, 0, 0, 0);
        }*/
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

    public void initEntity(LivingEntity user) {
        this.setPos(user.getX(),user.getEyeY(),user.getZ());
        this.setVelocity(user,user.getPitch(),user.getHeadYaw(),caliber.roll(), caliber.velocity(),caliber.divergence());
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
    protected ItemStack asItemStack() {
        return new ItemStack(Blocks.RED_CONCRETE);
    }

    @Override
    public void initDataTracker() {
        super.initDataTracker();
    }

    public double distSquared(double x1, double y1, double x2, double y2) {
        return (y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
    }

    @Override
    public AnimationFactory getFactory() {
        return GeckoLibUtil.createFactory(this);
    }
}
