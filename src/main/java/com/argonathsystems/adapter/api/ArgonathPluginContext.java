package com.argonathsystems.adapter.api;

import com.argonathsystems.framework.accessor.accessorapi.*;
import org.slf4j.Logger;

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
     * Get the text styling accessor for formatted text.
     * 
     * @return Text styling accessor instance
     */
    TextStylingAccessor getTextStylingAccessor();
    
    // Add additional accessors as needed for migration
    // WorldAccessor, NPCAccessor, QuestAccessor, etc.
}
