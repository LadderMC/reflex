package fr.ladder.reflex.base;

import fr.ladder.reflex.PluginInspector;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.Resource;
import io.github.classgraph.ScanResult;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Snowtyy
 */
public class ClassGraphAdapter implements PluginInspector {

    private final ScanResult _result;

    private ClassGraphAdapter(ClassLoader classLoader) {
        _result = new ClassGraph()
                .addClassLoader(classLoader)
                .enableAllInfo()
                .scan();
    }

    public ClassGraphAdapter(Plugin plugin) {
        this(plugin.getClass().getClassLoader());
    }

    @Override
    public Stream<String> getResources(Pattern pattern) {
        return _result.getResourcesMatchingPattern(pattern).stream()
                .map(Resource::getPath);
    }

    @Override
    public Stream<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation) {
        return _result.getClassesWithAnnotation(annotation).stream()
                .map(ClassInfo::loadClass);
    }

    @Override
    public Stream<Field> getFieldsWithAnnotation(Class<? extends Annotation> annotation) {
        return _result.getClassesWithFieldAnnotation(annotation).stream()
                .map(ClassInfo::loadClass)
                .flatMap(c -> Arrays.stream(c.getDeclaredFields()))
                .filter(f -> f.isAnnotationPresent(annotation));
    }

    @Override
    public void close() {
        _result.close();
    }
}
