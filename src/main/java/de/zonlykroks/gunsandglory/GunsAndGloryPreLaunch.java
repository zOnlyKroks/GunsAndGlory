package de.zonlykroks.gunsandglory;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class GunsAndGloryPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        MixinExtrasBootstrap.init();
    }
}
