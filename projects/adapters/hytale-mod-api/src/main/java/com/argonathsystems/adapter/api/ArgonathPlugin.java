package com.argonathsystems.adapter.api;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public abstract class ArgonathPlugin extends JavaPlugin {
    
    private final ArgonathPluginInitconfig argonathInit;

    public ArgonathPlugin(ArgonathPluginInitconfig init) {
        super(init);
        this.argonathInit = init;
    }
    
    public ArgonathPluginInitconfig getArgonathInit() {
        return argonathInit;
    }
}
