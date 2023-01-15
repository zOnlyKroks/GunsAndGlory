package de.zonlykroks.gunsandglory.init;

import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.BulletEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit {

    public static final EntityType<BulletEntity> BULLET = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(GunsAndGlory.MODID, "bullet"),
            EntityType.Builder.create(BulletEntity::new, SpawnGroup.MISC).setDimensions(0.5F, 0.2F).maxTrackingRange(4).trackingTickInterval(20).build("guns-and-glory:bullet"));

}
