package de.zonlykroks.gunsandglory.common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AbstractGunItem extends Item{

    public AbstractGunItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        this.shoot(user,world);

        return TypedActionResult.pass(stack);
    }

    public void shoot(LivingEntity user, World world) {
        BulletEntity entity = new BulletEntity(IBulletCaliber.NATO556.id(), IBulletType.NORMAL.id(),world,user);
        entity.initEntity(user);
        world.spawnEntity(entity);
    }
}
