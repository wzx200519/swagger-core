package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.ApiStabilityLevel;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * The annotation may be used to mark an API's stability level, indicating the maturity
 * and expected longevity of the annotated resource class, bean type, or resource method.
 *
 * <p>Possible values:</p>
 * <ul>
 * <li>{@link ApiStabilityLevel#EXPERIMENTAL EXPERIMENTAL} - The API is experimental and may change or be removed without notice.</li>
 * <li>{@link ApiStabilityLevel#STABLE STABLE} - The API is stable and safe for production use.</li>
 * <li>{@link ApiStabilityLevel#DEPRECATED DEPRECATED} - The API is deprecated and may be removed in a future version.</li>
 * </ul>
 *
 * <p>When applied on a method, the method-level annotation overrides the class-level annotation
 * for that specific method.</p>
 *
 * @see ApiStabilityLevel
 **/
@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiStability {

    /**
     * The stability level of the annotated API.
     *
     * @return the stability level
     **/
    ApiStabilityLevel value() default ApiStabilityLevel.STABLE;
}