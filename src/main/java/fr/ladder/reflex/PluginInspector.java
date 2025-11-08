package fr.ladder.reflex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Defines a contract for inspecting plugin resources.
 * <p>
 * This interface provides methods to discover resources, classes, and fields
 * based on patterns and annotations within a plugin's context, which is
 * typically a JAR file or a classpath.
 * It implements {@link AutoCloseable} to ensure that any underlying resources
 * (like file streams) are properly released.
 *
 * @author Snowtyy
 */
public interface PluginInspector extends AutoCloseable {

    /**
     * Finds resources whose names match the given pattern.
     *
     * @param pattern The regular expression {@link Pattern} to match against resource names.
     * @return A {@link Stream} of resource names (as {@link String}s) that match the pattern.
     */
    Stream<String> getResources(Pattern pattern);

    /**
     * Finds all classes that are annotated with the specified annotation.
     *
     * @param annotation The {@link Annotation} class to search for.
     * @return A {@link Stream} of {@link Class} objects that have the specified annotation.
     */
    Stream<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation);

    /**
     * Finds all fields that are annotated with the specified annotation.
     *
     * @param annotation The {@link Annotation} class to search for on fields.
     * @return A {@link Stream} of {@link Field} objects that have the specified annotation.
     */
    Stream<Field> getFieldsWithAnnotation(Class<? extends Annotation> annotation);

    @Override
    void close();
}
