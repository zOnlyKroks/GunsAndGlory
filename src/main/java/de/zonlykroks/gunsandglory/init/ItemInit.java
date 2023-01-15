package de.zonlykroks.gunsandglory.init;

import de.zonlykroks.gunsandglory.GunsAndGlory;
import de.zonlykroks.gunsandglory.common.AbstractGunItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {

    public static final Item TEST_GUN = Registry.register(Registry.ITEM,
            new Identifier(GunsAndGlory.MODID,"test_gun"),
            new AbstractGunItem(new FabricItemSettings().group(ItemGroup.FOOD)));

}
