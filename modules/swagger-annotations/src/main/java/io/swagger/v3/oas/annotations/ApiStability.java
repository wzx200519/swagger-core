package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.Stability;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Marks a given resource class or method with a stability level, indicating the
 * maturity and expected compatibility guarantees of the API element.
 **/
@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiStability {
    Stability value() default Stability.STABLE;
}
