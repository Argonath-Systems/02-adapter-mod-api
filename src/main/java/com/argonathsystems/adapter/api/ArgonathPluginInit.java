package com.argonathsystems.adapter.api;

import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

/**
 * Wrapper for Hytale's JavaPluginInit to provide Argonath-specific context.
 */
public class ArgonathPluginInit {
    
    private final JavaPluginInit handle;

    public ArgonathPluginInit(JavaPluginInit handle) {
        this.handle = handle;
    }

    public JavaPluginInit getHandle() {
        return handle;
    }
    
    // Future expansion: Add Argonath specific initialization methods here
}
