package com.argonathsystems.adapter.api;

import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

/**
 * Wrapper for Hytale's JavaPluginInit to provide Argonath-specific context.
 */
public class ArgonathPluginInitconfig {
    
    private final JavaPluginInit handle;

    public ArgonathPluginInitconfig(JavaPluginInit init) {
        // Since we can't easily copy fields from the handle without knowing them/having getters, 
        // and we can't call a copy constructor on super (JavaPluginInit),
        // we rely on the fact that we are compiling against a stub or SDK 
        // and might be just passing this instance through to something that doesn't check fields immediately
        // or using it just as a carrier.
        // Ideally, we would clone the init object.
        this.handle = init;
    }

    public JavaPluginInit getHandle() {
        return handle;
    }
}
