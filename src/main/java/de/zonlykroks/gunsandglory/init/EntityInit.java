package de.zonlykroks.gunsandglory.init;

import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.BulletEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit {

    public static final EntityType<BulletEntity> BULLET = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(GunsAndGlory.MODID, "bullet"),
           FabricEntityTypeBuilder.create(SpawnGroup.MISC,BulletEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.2F)).trackRangeBlocks(4).trackedUpdateRate(20).build());

}
