package com.argonathsystems.adapter.api.hytale;

import com.argonathsystems.adapter.api.ArgonathPluginContext;
import com.argonathsystems.framework.accessorapi.*;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
    private final String pluginName;
    private final Path dataFolder;

    /**
     * Create a new Hytale plugin context.
     * 
     * @param hytalePlugin The Hytale JavaPlugin instance
     * @param pluginName The name of the plugin (for logging)
     */
    public HytaleArgonathPluginContext(JavaPlugin hytalePlugin, String pluginName) {
        this.hytalePlugin = hytalePlugin;
        this.pluginName = pluginName;
        this.logger = LoggerFactory.getLogger(pluginName);
        this.provider = AccessorRegistry.getProvider();
        
        // Data folder is plugins/<pluginName>/ relative to server directory
        this.dataFolder = Path.of("plugins", pluginName);
        
        // Ensure data folder exists
        try {
            Files.createDirectories(this.dataFolder);
        } catch (IOException e) {
            logger.warn("Failed to create data folder: {}", dataFolder, e);
        }
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
    
    @Override
    public Path getDataFolder() {
        return dataFolder;
    }

    @Override
    public InputStream getResource(String path) {
        // Load resource from the classpath (inside the JAR)
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    @Override
    public void saveResource(String resourcePath, String destinationPath) throws IOException {
        try (InputStream is = getResource(resourcePath)) {
            if (is == null) {
                throw new IOException("Resource not found in JAR: " + resourcePath);
            }
            
            Path destFile = dataFolder.resolve(destinationPath);
            
            // Create parent directories if needed
            Path parent = destFile.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            
            // Copy resource to destination
            Files.copy(is, destFile, StandardCopyOption.REPLACE_EXISTING);
            logger.debug("Saved resource {} to {}", resourcePath, destFile);
        }
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
