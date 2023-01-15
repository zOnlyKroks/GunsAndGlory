package de.zonlykroks.gunsandglory.common.impl.type;

import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.IBulletType;
import net.minecraft.util.Identifier;

public class NormalType implements IBulletType {
    @Override
    public Identifier id() {
        return new Identifier(GunsAndGlory.MODID,"normal");
    }

    @Override
    public int fireInSeconds() {
        return 2;
    }

    @Override
    public float explosionStrength() {
        return 0.5F;
    }
}
