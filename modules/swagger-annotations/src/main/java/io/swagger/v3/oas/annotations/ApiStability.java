package io.swagger.v3.oas.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the stability level of an API.
 * This annotation can be used on classes and methods to clearly mark 
 * the stability status (e.g., EXPERIMENTAL, STABLE, DEPRECATED).
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiStability {

    /**
     * Defines the stability level of the annotated API.
     *
     * @return the stability level, defaults to STABLE
     */
    Level value() default Level.STABLE;

    /**
     * The defined stability levels.
     */
    enum Level {
        /**
         * Indicates that the API is experimental and subject to change.
         */
        EXPERIMENTAL,

        /**
         * Indicates that the API is stable and reliable for use.
         */
        STABLE,

        /**
         * Indicates that the API is deprecated and may be removed in the future.
         */
        DEPRECATED
    }
}
