package com.argonathsystems.adapter.api.hytale;

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
 *   <li>Hytale calls {@link #setup()} — wrapper creates context and initializes the ArgonathPlugin</li>
 *   <li>ArgonathPlugin.onEnable() is invoked (ArgonathPlugin's own lifecycle, not Hytale's)</li>
 *   <li>Hytale calls {@link #shutdown()} — wrapper delegates to ArgonathPlugin.onDisable()</li>
 * </ol>
 * 
 * <p><b>IMPORTANT:</b> Hytale has NO {@code onEnable()}/{@code onDisable()} methods on JavaPlugin.
 * The real lifecycle uses {@code setup()}/{@code start()}/{@code shutdown()}.
 * We use {@code setup()} for initialization because EventRegistry is available at that point.</p>
 * 
 * @param <T> The type of ArgonathPlugin being wrapped
 * @since 1.0.0
 */
public abstract class HytalePluginWrapper<T extends ArgonathPlugin> extends JavaPlugin {
    
    private final PluginFactory<T> pluginFactory;
    private T wrappedPlugin;
    private HytaleArgonathPluginContext context;
    
    /**
     * Create a new wrapper for the given ArgonathPlugin.
     * 
     * <p>The constructor only stores the factory. Actual plugin initialization
     * is deferred to {@link #setup()} when the EventRegistry and other
     * plugin services are available.</p>
     * 
     * @param init Hytale plugin initialization context
     * @param pluginFactory Factory to create the ArgonathPlugin instance
     */
    public HytalePluginWrapper(JavaPluginInit init, PluginFactory<T> pluginFactory) {
        super(init);
        this.pluginFactory = pluginFactory;
        getLogger().at(Level.INFO).log(getClass().getSimpleName() + " constructed - awaiting setup() for initialization");
    }
    
    /**
     * Called by Hytale PluginManager during SETUP phase.
     * 
     * <p>At this point the plugin's EventRegistry and other services are available.
     * We create the ArgonathPluginContext and initialize the wrapped plugin here.</p>
     */
    @Override
    protected void setup() {
        try {
            // Create the wrapped plugin instance
            this.wrappedPlugin = pluginFactory.create();
            
            // Create the context bridging Hytale to Argonath
            // Pass the plugin class so resources are loaded from the plugin's JAR
            this.context = new HytaleArgonathPluginContext(
                this, 
                getPluginName(), 
                this.wrappedPlugin.getClass()
            );
            
            // Initialize the plugin with our context
            // This will call onEnable() on the wrapped ArgonathPlugin
            // (ArgonathPlugin.onEnable() is our own lifecycle, separate from Hytale's)
            this.wrappedPlugin.initialize(context);
            
            getLogger().at(Level.INFO).log("✓ " + getPluginName() + " initialized successfully in setup()");
            
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
     * Called by Hytale PluginManager during SHUTDOWN phase.
     * Delegates to the wrapped plugin's onDisable().
     */
    @Override
    protected void shutdown() {
        if (wrappedPlugin != null) {
            try {
                wrappedPlugin.onDisable();
                getLogger().at(Level.INFO).log(getPluginName() + " shutdown complete");
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
     * @return The wrapped plugin, or null if setup() has not been called yet
     */
    public T getWrappedPlugin() {
        return wrappedPlugin;
    }
    
    /**
     * Get the plugin context.
     * 
     * @return The Hytale-based plugin context, or null if setup() has not been called yet
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
