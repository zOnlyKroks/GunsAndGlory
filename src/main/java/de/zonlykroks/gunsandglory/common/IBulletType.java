package de.zonlykroks.gunsandglory.common;

import de.zonlykroks.gunsandglory.common.impl.type.NormalType;
import net.minecraft.util.Identifier;

public interface IBulletType {

    IBulletType NORMAL = new NormalType();

    Identifier id();
    int fireInSeconds();
    float explosionStrength();
}
