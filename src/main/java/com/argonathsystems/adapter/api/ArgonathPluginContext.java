package com.argonathsystems.adapter.api;

import com.argonathsystems.framework.accessorapi.*;
import org.slf4j.Logger;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * Platform-agnostic plugin context providing access to all framework services.
 * 
 * <p>This interface abstracts away the underlying Hytale SDK implementation,
 * allowing mods to use framework accessors without direct platform dependencies.</p>
 * 
 * <h2>Available Services:</h2>
 * <ul>
 *   <li>{@link PlayerAccessor} - Player management and messaging</li>
 *   <li>{@link EventAccessor} - Event registration and handling</li>
 *   <li>{@link SchedulerAccessor} - Task scheduling</li>
 *   <li>{@link CommandAccessor} - Command registration</li>
 *   <li>{@link ConfigAccessor} - Configuration management</li>
 *   <li>{@link StorageAccessor} - Data persistence</li>
 *   <li>{@link TextStylingAccessor} - Styled text formatting</li>
 * </ul>
 * 
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * public void onEnable() {
 *     PlayerAccessor playerAccessor = getContext().getPlayerAccessor();
 *     playerAccessor.withPlayer(uuid, ctx -> {
 *         ctx.sendMessage("Hello!");
 *     });
 * }
 * }</pre>
 * 
 * @since MIGRATION-001
 */
public interface ArgonathPluginContext {
    
    /**
     * Get the logger for this plugin.
     * 
     * @return SLF4J logger instance
     */
    Logger getLogger();
    
    /**
     * Get the player accessor for player management operations.
     * 
     * @return Player accessor instance
     */
    PlayerAccessor getPlayerAccessor();
    
    /**
     * Get the event accessor for event handling.
     * 
     * @return Event accessor instance
     */
    EventAccessor getEventAccessor();
    
    /**
     * Get the scheduler accessor for task scheduling.
     * 
     * @return Scheduler accessor instance
     */
    SchedulerAccessor getSchedulerAccessor();
    
    /**
     * Get the command accessor for command registration.
     * 
     * @return Command accessor instance
     */
    CommandAccessor getCommandAccessor();
    
    /**
     * Get the config accessor for configuration management.
     * 
     * @return Config accessor instance
     */
    ConfigAccessor getConfigAccessor();
    
    /**
     * Get the storage accessor for data persistence.
     * 
     * @return Storage accessor instance
     */
    StorageAccessor getStorageAccessor();
    
    /**
     * Get the entity accessor for entity management operations.
     * 
     * @return Entity accessor instance
     */
    EntityAccessor getEntityAccessor();
    
    // =========================================================================
    // Data Folder and Resource Management
    // =========================================================================

    /**
     * Get the data folder for this plugin.
     * 
     * <p>This folder is where the plugin should store configuration files,
     * data files, and any other persistent resources. The folder is created
     * if it doesn't exist.</p>
     * 
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * Path configFile = getContext().getDataFolder().resolve("config.yml");
     * Path mapsDir = getContext().getDataFolder().resolve("maps");
     * }</pre>
     * 
     * @return Path to the plugin's data folder
     */
    Path getDataFolder();

    /**
     * Get a resource bundled with the plugin JAR.
     * 
     * <p>Loads resources from the classpath (inside the JAR file). Returns
     * null if the resource is not found.</p>
     * 
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * try (InputStream is = getContext().getResource("default-config.yml")) {
     *     if (is != null) {
     *         // Process the resource
     *     }
     * }
     * }</pre>
     * 
     * @param path Path to the resource relative to resources root (e.g., "maps/default.png")
     * @return InputStream for the resource, or null if not found
     */
    InputStream getResource(String path);

    /**
     * Save a bundled resource to the data folder.
     * 
     * <p>Extracts a resource from the plugin JAR to the plugin's data folder.
     * This is useful for extracting default configuration files or assets
     * on first run.</p>
     * 
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * // Extract default config if it doesn't exist
     * Path configPath = getContext().getDataFolder().resolve("config.yml");
     * if (!Files.exists(configPath)) {
     *     getContext().saveResource("config.yml", "config.yml");
     * }
     * }</pre>
     * 
     * @param resourcePath Path to the resource in the JAR
     * @param destinationPath Path relative to data folder where file will be saved
     * @throws java.io.IOException if the resource cannot be saved
     */
    void saveResource(String resourcePath, String destinationPath) throws java.io.IOException;

    
    // Add additional accessors as needed for migration
    // WorldAccessor, NPCAccessor, QuestAccessor, etc.
}
