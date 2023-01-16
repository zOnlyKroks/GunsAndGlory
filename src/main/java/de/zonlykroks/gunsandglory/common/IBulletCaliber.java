package de.zonlykroks.gunsandglory.common;

import de.zonlykroks.gunsandglory.common.impl.caliber.Nato556;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public interface IBulletCaliber {

    Identifier id();
    int applyEntityDamage(Entity hitEntity,BulletEntity bulletEntity, Vec3d initialPos, Vec3d endPos);
    float verticalDrag(BlockPos pos,Vec3d initialPos);
    void tick(BulletEntity entity);

    //Runs after damage, before removal
    //Used for running logic OTHER THAN DAMAGE
    void onEntityHit(BulletEntity bullet, EntityHitResult entityHitResult);

    //Called right before removal
    //Used for logic once a block is hit
    void onBlockHit(BulletEntity bullet, BlockHitResult blockHitResult);

    float velocity();

    //Bullet spread
    float divergence();

    float roll();
}
