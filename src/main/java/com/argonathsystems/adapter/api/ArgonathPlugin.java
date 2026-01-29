package com.argonathsystems.adapter.api;

import org.slf4j.Logger;

/**
 * Base class for all Argonath mods.
 * Provides platform-agnostic API for mod initialization and lifecycle.
 * 
 * <p>This abstraction allows mods to be independent of the underlying
 * Hytale SDK implementation, making them portable and testable.</p>
 * 
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * public class MyMod extends ArgonathPlugin {
 *     @Override
 *     public void onEnable() {
 *         getLogger().info("MyMod enabled!");
 *         
 *         // Access services through context
 *         PlayerAccessor playerAccessor = getContext().getPlayerAccessor();
 *         EventAccessor eventAccessor = getContext().getEventAccessor();
 *     }
 * }
 * }</pre>
 * 
 * @since MIGRATION-001
 */
public abstract class ArgonathPlugin {
    private ArgonathPluginContext context;
    private Logger logger;
    
    /**
     * Initialize this plugin with the given context.
     * Called by the platform adapter during plugin loading.
     * 
     * @param context Platform-specific plugin context
     */
    public final void initialize(ArgonathPluginContext context) {
        this.context = context;
        this.logger = context.getLogger();
        onEnable();
    }
    
    /**
     * Called when the plugin is enabled.
     * Override this method to initialize your mod.
     */
    public abstract void onEnable();
    
    /**
     * Called when the plugin is disabled.
     * Override this method to cleanup resources.
     * Default implementation does nothing.
     */
    public void onDisable() {
        // Optional override
    }
    
    /**
     * Get the plugin context providing access to all framework services.
     * 
     * @return Plugin context
     */
    protected ArgonathPluginContext getContext() {
        return context;
    }
    
    /**
     * Get the logger for this plugin.
     * 
     * @return SLF4J logger instance
     */
    protected Logger getLogger() {
        return logger;
    }
}
