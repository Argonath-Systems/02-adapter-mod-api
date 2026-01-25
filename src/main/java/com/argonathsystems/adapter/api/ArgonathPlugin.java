package com.argonathsystems.adapter.api;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public abstract class ArgonathPlugin extends JavaPlugin {
    
    private final ArgonathPluginInitconfig argonathInit;

    public ArgonathPlugin(JavaPluginInit init) {
        super(init);
        this.argonathInit = new ArgonathPluginInitconfig(init);
    }
    
    public ArgonathPluginInitconfig getArgonathInit() {
        return argonathInit;
    }
}
