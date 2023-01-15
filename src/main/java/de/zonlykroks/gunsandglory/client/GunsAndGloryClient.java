package de.zonlykroks.gunsandglory.client;

import de.zonlykroks.gunsandglory.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class GunsAndGloryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityInit.BULLET, BulletEntityRenderer::new);
    }
}
