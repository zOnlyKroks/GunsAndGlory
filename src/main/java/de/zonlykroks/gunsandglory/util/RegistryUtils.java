package de.zonlykroks.gunsandglory.util;

import com.mojang.serialization.Lifecycle;
import de.zonlykroks.gunsandglory.common.IBulletCaliber;
import de.zonlykroks.gunsandglory.common.IBulletType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class RegistryUtils {

    public static final RegistryKey<Registry<IBulletCaliber>> BULLET_CALIBER_REGISTRY_KEY = createRegistryKey("bullet_caliber");
    public static final Registry<IBulletCaliber> BULLET_CALIBER_REGISTRY = new SimpleRegistry<>(BULLET_CALIBER_REGISTRY_KEY, Lifecycle.stable(),null);

    public static final RegistryKey<Registry<IBulletType>> BULLET_TYPE_REGISTRY_KEY = createRegistryKey("bullet_type");
    public static final Registry<IBulletType> BULLET_TYPE_REGISTRY = new SimpleRegistry<>(BULLET_TYPE_REGISTRY_KEY, Lifecycle.stable(),null);

    private static <T> RegistryKey<Registry<T>> createRegistryKey(String registryId) {
        return RegistryKey.ofRegistry(new Identifier(registryId));
    }

}
