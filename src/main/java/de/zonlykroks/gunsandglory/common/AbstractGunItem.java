package de.zonlykroks.gunsandglory.common;

import de.zonlykroks.gunsandglory.init.EntityInit;
import de.zonlykroks.gunsandglory.util.RegistryManager;
import net.minecraft.entity.Entity;
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
        BulletEntity entity = new BulletEntity(EntityInit.BULLET,world);
        entity.initEntity(RegistryManager.NATO556.id(),RegistryManager.NORMAL.id(),user);
        world.spawnEntity(entity);
    }
}
