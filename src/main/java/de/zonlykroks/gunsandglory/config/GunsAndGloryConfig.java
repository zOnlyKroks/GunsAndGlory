package de.zonlykroks.gunsandglory.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.zonlykroks.gunsandglory.common.BulletEntity;
import de.zonlykroks.gunsandglory.common.IBulletCaliber;
import de.zonlykroks.gunsandglory.common.IBulletType;
import de.zonlykroks.gunsandglory.util.RegistryManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GunsAndGloryConfig {

    private static final File typeFolder = new File(FabricLoader.getInstance().getConfigDir() + "/gunsandglory/type/");
    private static final File caliberFolder = new File(FabricLoader.getInstance().getConfigDir() + "/gunsandglory/caliber/");

    public GunsAndGloryConfig() throws FileNotFoundException {
        if(!typeFolder.exists())
            typeFolder.mkdirs();

        if(!caliberFolder.exists())
            caliberFolder.mkdirs();

        for(File typeFile : typeFolder.listFiles()) {
            JsonObject object = JsonParser.parseReader(new FileReader(typeFile)).getAsJsonObject();

            String mod_id = object.get("mod_id").getAsString();
            String name = object.get("name").getAsString();
            int fireInSeconds = object.get("fireInSeconds").getAsInt();
            float explosionStrength = object.get("explosionStrength").getAsFloat();

            IBulletType bulletType = new IBulletType() {
                @Override
                public Identifier id() {
                    return new Identifier(mod_id,name);
                }

                @Override
                public int fireInSeconds() {
                    return fireInSeconds;
                }

                @Override
                public float explosionStrength() {
                    return explosionStrength;
                }
            };

            RegistryManager.registerType(bulletType);
        }

        for(File caliberFile : caliberFolder.listFiles()) {
            JsonObject object = JsonParser.parseReader(new FileReader(caliberFile)).getAsJsonObject();

            String mod_id = object.get("mod_id").getAsString();
            String name = object.get("name").getAsString();
            int entityDamage = object.get("entityDamage").getAsInt();
            float verticalDrag = object.get("verticalDrag").getAsFloat();
            float velocity = object.get("velocity").getAsFloat();
            float divergence = object.get("divergence").getAsFloat();
            float roll = object.get("roll").getAsFloat();

            IBulletCaliber caliber = new IBulletCaliber() {
                @Override
                public Identifier id() {
                    return new Identifier(mod_id,name);
                }

                @Override
                public int applyEntityDamage(Entity hitEntity, BulletEntity bulletEntity, Vec3d initialPos, Vec3d endPos) {
                    return entityDamage;
                }

                @Override
                public float verticalDrag(BlockPos pos, Vec3d initialPos) {
                    return verticalDrag;
                }

                @Override
                public void tick(BulletEntity entity) {
                }

                @Override
                public void onEntityHit(BulletEntity bullet, EntityHitResult entityHitResult) {
                }

                @Override
                public void onBlockHit(BulletEntity bullet, BlockHitResult blockHitResult) {
                }

                @Override
                public float velocity() {
                    return velocity;
                }

                @Override
                public float divergence() {
                    return divergence;
                }

                @Override
                public float roll() {
                    return roll;
                }
            };

            RegistryManager.registerCaliber(caliber);
        }
    }

}
