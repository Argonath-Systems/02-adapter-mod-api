package com.argonathsystems.adapter.hytalemodapi;

import com.argonathsystems.adapter.api.ArgonathPlugin;
import com.argonathsystems.adapter.api.ArgonathPluginContext;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import java.util.logging.Level;

/**
 * Base wrapper class that bridges Hytale's JavaPlugin to ArgonathPlugin.
 * 
 * <p>This class extends Hytale's {@link JavaPlugin} and wraps an instance of
 * {@link ArgonathPlugin}, providing the bridge between Hytale's plugin system
 * and our platform-agnostic mod architecture.</p>
 * 
 * <h2>Usage:</h2>
 * <p>Create a thin wrapper class for each mod:</p>
 * <pre>{@code
 * public class QuestTrackerWrapper extends HytalePluginWrapper<QuestTrackerMod> {
 *     public QuestTrackerWrapper(JavaPluginInit init) {
 *         super(init, QuestTrackerMod::new);
 *     }
 * }
 * }</pre>
 * 
 * <h2>Lifecycle:</h2>
 * <ol>
 *   <li>Hytale loads the wrapper (calls constructor with JavaPluginInit)</li>
 *   <li>Wrapper creates ArgonathPluginContext from Hytale services</li>
 *   <li>Wrapper instantiates the ArgonathPlugin via factory</li>
 *   <li>Wrapper calls {@code plugin.initialize(context)}</li>
 *   <li>ArgonathPlugin.onEnable() is invoked</li>
 * </ol>
 * 
 * @param <T> The type of ArgonathPlugin being wrapped
 * @since 1.0.0
 */
public abstract class HytalePluginWrapper<T extends ArgonathPlugin> extends JavaPlugin {
    
    private final T wrappedPlugin;
    private final HytaleArgonathPluginContext context;
    
    /**
     * Create a new wrapper for the given ArgonathPlugin.
     * 
     * @param init Hytale plugin initialization context
     * @param pluginFactory Factory to create the ArgonathPlugin instance
     */
    public HytalePluginWrapper(JavaPluginInit init, PluginFactory<T> pluginFactory) {
        super(init);
        
        try {
            // Create the context bridging Hytale to Argonath
            this.context = new HytaleArgonathPluginContext(this, getPluginName());
            
            // Create the wrapped plugin instance
            this.wrappedPlugin = pluginFactory.create();
            
            // Initialize the plugin with our context
            // This will call onEnable() on the wrapped plugin
            this.wrappedPlugin.initialize(context);
            
            getLogger().at(Level.INFO).log("✓ " + getPluginName() + " initialized successfully");
            
        } catch (Exception e) {
            getLogger().at(Level.SEVERE).withCause(e).log(
                "✗ Failed to initialize " + getPluginName()
            );
            throw new RuntimeException("Plugin initialization failed: " + getPluginName(), e);
        }
    }
    
    /**
     * Get the name of this plugin for logging purposes.
     * Override to provide a custom name.
     * 
     * @return Plugin name
     */
    protected String getPluginName() {
        return wrappedPlugin != null ? 
            wrappedPlugin.getClass().getSimpleName() : 
            getClass().getSimpleName();
    }
    
    /**
     * Called when the plugin is enabled by Hytale.
     * The wrapped plugin is already initialized in the constructor.
     */
    public void onEnable() {
        // Plugin already initialized in constructor
        getLogger().at(Level.INFO).log(getPluginName() + " enabled");
    }
    
    /**
     * Called when the plugin is disabled by Hytale.
     * Delegates to the wrapped plugin's onDisable().
     */
    public void onDisable() {
        if (wrappedPlugin != null) {
            try {
                wrappedPlugin.onDisable();
                getLogger().at(Level.INFO).log(getPluginName() + " disabled");
            } catch (Exception e) {
                getLogger().at(Level.WARNING).withCause(e).log(
                    "Error during " + getPluginName() + " shutdown"
                );
            }
        }
    }
    
    /**
     * Get the wrapped ArgonathPlugin instance.
     * 
     * @return The wrapped plugin
     */
    public T getWrappedPlugin() {
        return wrappedPlugin;
    }
    
    /**
     * Get the plugin context.
     * 
     * @return The Hytale-based plugin context
     */
    public ArgonathPluginContext getPluginContext() {
        return context;
    }
    
    /**
     * Functional interface for creating ArgonathPlugin instances.
     * 
     * @param <T> The plugin type
     */
    @FunctionalInterface
    public interface PluginFactory<T extends ArgonathPlugin> {
        /**
         * Create a new plugin instance.
         * 
         * @return New plugin instance
         */
        T create();
    }
}
