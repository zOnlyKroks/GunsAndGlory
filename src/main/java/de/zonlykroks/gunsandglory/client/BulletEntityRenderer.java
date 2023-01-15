package de.zonlykroks.gunsandglory.client;


import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.BulletEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class BulletEntityRenderer extends EntityRenderer<BulletEntity> {

    protected BulletEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(BulletEntity entity) {
        return new Identifier(GunsAndGlory.MODID,"bullet");
    }
}
