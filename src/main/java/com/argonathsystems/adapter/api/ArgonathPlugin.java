package com.argonathsystems.adapter.api;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public abstract class ArgonathPlugin extends JavaPlugin {
    
    private final ArgonathPluginInitConfig argonathInit;

    public ArgonathPlugin(JavaPluginInit init) {
        super(init);
        this.argonathInit = new ArgonathPluginInitConfig(init);
    }
    
    public ArgonathPluginInitConfig getArgonathInit() {
        return argonathInit;
    }
}
