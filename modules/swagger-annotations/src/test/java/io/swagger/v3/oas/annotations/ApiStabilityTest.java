package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.ApiStabilityLevel;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

public class ApiStabilityTest {

    @ApiStability(ApiStabilityLevel.STABLE)
    public static class StableClass {
        @ApiStability(ApiStabilityLevel.EXPERIMENTAL)
        public void experimentalMethod() {
        }

        public void stableMethod() {
        }
    }

    @ApiStability(ApiStabilityLevel.EXPERIMENTAL)
    public static class ExperimentalClass {
        @ApiStability(ApiStabilityLevel.DEPRECATED)
        public void deprecatedMethod() {
        }

        public void experimentalMethod() {
        }
    }

    @ApiStability(ApiStabilityLevel.DEPRECATED)
    public static class DeprecatedClass {
    }

    @ApiStability
    public static class DefaultLevelClass {
    }

    @Test
    public void testAnnotationOnClass() throws Exception {
        ApiStability annotation = StableClass.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), ApiStabilityLevel.STABLE);
    }

    @Test
    public void testAnnotationOnMethod() throws Exception {
        Method method = StableClass.class.getMethod("experimentalMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), ApiStabilityLevel.EXPERIMENTAL);
    }

    @Test
    public void testMethodInheritsClassLevelWhenNotAnnotated() throws Exception {
        Method method = StableClass.class.getMethod("stableMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNull(annotation);
    }

    @Test
    public void testExperimentalStabilityLevel() throws Exception {
        ApiStability annotation = ExperimentalClass.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), ApiStabilityLevel.EXPERIMENTAL);
    }

    @Test
    public void testDeprecatedStabilityLevel() throws Exception {
        Method method = ExperimentalClass.class.getMethod("deprecatedMethod");
        ApiStability annotation = method.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), ApiStabilityLevel.DEPRECATED);
    }

    @Test
    public void testDefaultStabilityLevel() throws Exception {
        ApiStability annotation = DefaultLevelClass.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation);
        assertEquals(annotation.value(), ApiStabilityLevel.STABLE);
    }

    @Test
    public void testAllStabilityLevelsExist() {
        for (ApiStabilityLevel level : ApiStabilityLevel.values()) {
            assertNotNull(level);
        }
        assertEquals(ApiStabilityLevel.values().length, 3);
        assertTrue(containsEnum(ApiStabilityLevel.values(), ApiStabilityLevel.EXPERIMENTAL));
        assertTrue(containsEnum(ApiStabilityLevel.values(), ApiStabilityLevel.STABLE));
        assertTrue(containsEnum(ApiStabilityLevel.values(), ApiStabilityLevel.DEPRECATED));
    }

    @Test
    public void testAnnotationRetentionPolicy() {
        assertEquals(ApiStability.class.getAnnotation(java.lang.annotation.Retention.class).value(),
                java.lang.annotation.RetentionPolicy.RUNTIME);
    }

    @Test
    public void testAnnotationTargetTypes() {
        java.lang.annotation.Target target = ApiStability.class.getAnnotation(java.lang.annotation.Target.class);
        java.lang.annotation.ElementType[] elementTypes = target.value();

        assertTrue(containsElementType(elementTypes, java.lang.annotation.ElementType.TYPE));
        assertTrue(containsElementType(elementTypes, java.lang.annotation.ElementType.METHOD));
        assertTrue(containsElementType(elementTypes, java.lang.annotation.ElementType.ANNOTATION_TYPE));
    }

    @Test
    public void testAnnotationIsInherited() {
        assertNotNull(ApiStability.class.getAnnotation(java.lang.annotation.Inherited.class));
    }

    private <T extends Enum<T>> boolean containsEnum(T[] values, T target) {
        for (T value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    private boolean containsElementType(java.lang.annotation.ElementType[] types, java.lang.annotation.ElementType target) {
        for (java.lang.annotation.ElementType type : types) {
            if (type == target) {
                return true;
            }
        }
        return false;
    }
}
