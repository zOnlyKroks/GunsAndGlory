package de.zonlykroks.gunsandglory.common.impl.caliber;

import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.BulletEntity;
import de.zonlykroks.gunsandglory.common.IBulletCaliber;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Nato556 implements IBulletCaliber {
    @Override
    public Identifier id() {
        return new Identifier(GunsAndGlory.MODID,"nato_556");
    }

    @Override
    public int applyEntityDamage(Entity hitEntity, BulletEntity bulletEntity, Vec3d initialPos, Vec3d endPos) {
        return 10;
    }

    @Override
    public float verticalDrag(BlockPos pos, Vec3d initialPos) {
        return 0.05F;
    }

    @Override
    public void tick(BulletEntity entity) {
        //NO-OP
    }

    @Override
    public void onEntityHit(BulletEntity bullet, EntityHitResult entityHitResult) {
        //NO-OP
    }

    @Override
    public void onBlockHit(BulletEntity bullet, BlockHitResult blockHitResult) {
        bullet.world.setBlockState(blockHitResult.getBlockPos(), Blocks.RED_CONCRETE.getDefaultState());
    }

    @Override
    public float velocity() {
        return 2.2F;
    }

    @Override
    public float divergence() {
        return 0F;
    }

    @Override
    public float roll() {
        return 0F;
    }
}
