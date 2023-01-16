package de.zonlykroks.gunsandglory;

import de.zonlykroks.gunsandglory.common.IBulletCaliber;
import de.zonlykroks.gunsandglory.common.IBulletType;
import de.zonlykroks.gunsandglory.config.GunsAndGloryConfig;
import de.zonlykroks.gunsandglory.init.EntityInit;
import de.zonlykroks.gunsandglory.init.ItemInit;
import de.zonlykroks.gunsandglory.util.RegistryManager;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

public class GunsAndGlory implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("guns-and-glory");

	public static final String MODID = "guns-and-glory";

	@Override
	public void onInitialize()  {
		try {
			new GunsAndGloryConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		new RegistryManager();

		new ItemInit();
		new EntityInit();
	}
}