package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.Stability;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

public class ApiStabilityTest {

    @ApiStability(Stability.EXPERIMENTAL)
    private static class ExperimentalApi {}

    @ApiStability(Stability.STABLE)
    private static class StableApi {}

    @ApiStability(Stability.DEPRECATED)
    private static class DeprecatedApi {}

    @ApiStability
    private static class DefaultApi {}

    private static class AnnotatedMethods {
        @ApiStability(Stability.EXPERIMENTAL)
        public void experimentalMethod() {}

        @ApiStability(Stability.STABLE)
        public void stableMethod() {}

        @ApiStability(Stability.DEPRECATED)
        public void deprecatedMethod() {}

        @ApiStability
        public void defaultMethod() {}
    }

    @Test
    public void testAnnotationOnClassWithExperimental() throws NoSuchMethodException {
        ApiStability annotation = ExperimentalApi.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.EXPERIMENTAL);
    }

    @Test
    public void testAnnotationOnClassWithStable() {
        ApiStability annotation = StableApi.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.STABLE);
    }

    @Test
    public void testAnnotationOnClassWithDeprecated() {
        ApiStability annotation = DeprecatedApi.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.DEPRECATED);
    }

    @Test
    public void testAnnotationDefaultValue() {
        ApiStability annotation = DefaultApi.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.STABLE);
    }

    @Test
    public void testAnnotationOnMethodExperimental() throws NoSuchMethodException {
        Method method = AnnotatedMethods.class.getMethod("experimentalMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.EXPERIMENTAL);
    }

    @Test
    public void testAnnotationOnMethodStable() throws NoSuchMethodException {
        Method method = AnnotatedMethods.class.getMethod("stableMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.STABLE);
    }

    @Test
    public void testAnnotationOnMethodDeprecated() throws NoSuchMethodException {
        Method method = AnnotatedMethods.class.getMethod("deprecatedMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.DEPRECATED);
    }

    @Test
    public void testAnnotationOnMethodDefaultValue() throws NoSuchMethodException {
        Method method = AnnotatedMethods.class.getMethod("defaultMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), Stability.STABLE);
    }

    @Test
    public void testStabilityEnumValues() {
        Stability[] values = Stability.values();
        assertEquals(values.length, 3);
        assertEquals(Stability.EXPERIMENTAL, Stability.valueOf("EXPERIMENTAL"));
        assertEquals(Stability.STABLE, Stability.valueOf("STABLE"));
        assertEquals(Stability.DEPRECATED, Stability.valueOf("DEPRECATED"));
    }

    @Test
    public void testAnnotationTargetTypeAndMethod() throws NoSuchMethodException {
        assertNotNull(ApiStability.class.getAnnotation(java.lang.annotation.Target.class));
        assertNotNull(DefaultApi.class.getAnnotation(ApiStability.class));
        assertNotNull(AnnotatedMethods.class.getMethod("stableMethod").getAnnotation(ApiStability.class));
    }

    @Test
    public void testAnnotationRetentionRuntime() {
        assertNotNull(ApiStability.class.getAnnotation(java.lang.annotation.Retention.class));
        assertEquals(
            ApiStability.class.getAnnotation(java.lang.annotation.Retention.class).value(),
            java.lang.annotation.RetentionPolicy.RUNTIME
        );
    }
}
