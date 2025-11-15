package fr.ladder.reflex;

import fr.ladder.reflex.base.ClassGraphAdapter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A factory utility class for creating {@link PluginInspector} instances.
 * <p>
 * This class provides a simple entry point for obtaining an inspector
 * for a given Bukkit plugin.
 *
 * @author Snowtyy
 */
public final class Reflex {

    private Reflex() {
        // Prevents instantiation of this utility class.
    }

    /**
     * Creates a new {@link PluginInspector} for the specified Bukkit plugin.
     *
     * @param plugin The {@link Plugin} to inspect.
     * @return A new {@link PluginInspector} instance for analyzing the plugin's resources.
     */
    public static PluginInspector getInspector(Plugin plugin) {
        return new ClassGraphAdapter(plugin);
    }

}
