package com.argonathsystems.adapter.api.hytale;

import com.argonathsystems.adapter.api.ArgonathPluginContext;
import com.argonathsystems.framework.accessorapi.*;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hytale-specific implementation of ArgonathPluginContext.
 * 
 * <p>This bridges the Hytale plugin system to the platform-agnostic
 * ArgonathPluginContext interface by obtaining accessors from the
 * AccessorRegistry.</p>
 * 
 * @since 1.0.0
 */
public class HytaleArgonathPluginContext implements ArgonathPluginContext {
    
    private final JavaPlugin hytalePlugin;
    private final Logger logger;
    private final AccessorProvider provider;
    
    /**
     * Create a new Hytale plugin context.
     * 
     * @param hytalePlugin The Hytale JavaPlugin instance
     * @param pluginName The name of the plugin (for logging)
     */
    public HytaleArgonathPluginContext(JavaPlugin hytalePlugin, String pluginName) {
        this.hytalePlugin = hytalePlugin;
        this.logger = LoggerFactory.getLogger(pluginName);
        this.provider = AccessorRegistry.getProvider();
    }
    
    @Override
    public Logger getLogger() {
        return logger;
    }
    
    @Override
    public PlayerAccessor getPlayerAccessor() {
        return provider.getPlayerAccessor();
    }
    
    @Override
    public EventAccessor getEventAccessor() {
        return provider.getEventAccessor();
    }
    
    @Override
    public SchedulerAccessor getSchedulerAccessor() {
        return provider.getSchedulerAccessor();
    }
    
    @Override
    public CommandAccessor getCommandAccessor() {
        return provider.getCommandAccessor();
    }
    
    @Override
    public ConfigAccessor getConfigAccessor() {
        return provider.getConfigAccessor();
    }
    
    @Override
    public StorageAccessor getStorageAccessor() {
        return provider.getStorageAccessor();
    }
    
    @Override
    public EntityAccessor getEntityAccessor() {
        return provider.getEntityAccessor();
    }
    
    /**
     * Get the underlying Hytale plugin.
     * For internal use only.
     * 
     * @return The Hytale JavaPlugin instance
     */
    public JavaPlugin getHytalePlugin() {
        return hytalePlugin;
    }
}
