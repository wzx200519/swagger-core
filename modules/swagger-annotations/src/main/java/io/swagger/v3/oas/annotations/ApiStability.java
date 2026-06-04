package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.StabilityLevel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Marks a given API (class or method) with its stability level.
 */
@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiStability {
    StabilityLevel value() default StabilityLevel.STABLE;
}
