package de.zonlykroks.gunsandglory.util;

import com.mojang.serialization.Lifecycle;
import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.BulletEntity;
import de.zonlykroks.gunsandglory.common.IBulletCaliber;
import de.zonlykroks.gunsandglory.common.IBulletType;
import de.zonlykroks.gunsandglory.common.impl.caliber.Nato556;
import de.zonlykroks.gunsandglory.common.impl.type.NormalType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.mixin.registry.sync.RegistryAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class RegistryManager {
    public static final SimpleRegistry<IBulletCaliber> BULLET_CALIBER_SIMPLE_REGISTRY = FabricRegistryBuilder.createSimple(IBulletCaliber.class,new Identifier(GunsAndGlory.MODID,"bullet_caliber")).buildAndRegister();
    public static final RegistryKey<IBulletCaliber> BULLET_CALIBER_REGISTRY_KEY = ((RegistryAccessor) BULLET_CALIBER_SIMPLE_REGISTRY).getRegistryKey();

    ///////////////////////////
    public static final SimpleRegistry<IBulletType> BULLET_TYPE_SIMPLE_REGISTRY = FabricRegistryBuilder.createSimple(IBulletType.class,new Identifier(GunsAndGlory.MODID,"bullet_type")).buildAndRegister();
    public static final RegistryKey<IBulletType> BULLET_TYPE_REGISTRY_KEY = ((RegistryAccessor) BULLET_TYPE_SIMPLE_REGISTRY).getRegistryKey();

    public static final IBulletCaliber NATO556 = registerCaliber(new Nato556()).value();

    public static final IBulletType NORMAL = registerType(new NormalType()).value();

    public static RegistryEntry<IBulletCaliber> registerCaliber(IBulletCaliber caliber) {
        return BULLET_CALIBER_SIMPLE_REGISTRY.add(BULLET_CALIBER_REGISTRY_KEY,caliber,Lifecycle.stable());
    }

    public static RegistryEntry<IBulletType> registerType(IBulletType type) {
        return BULLET_TYPE_SIMPLE_REGISTRY.add(BULLET_TYPE_REGISTRY_KEY,type,Lifecycle.stable());
    }
}
