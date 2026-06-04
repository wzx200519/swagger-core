package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.StabilityLevel;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ApiStabilityTest {

    @Test(description = "Test ApiStability annotation on class")
    public void testClassAnnotation() {
        ApiStability annotation = TestClass.class.getAnnotation(ApiStability.class);
        assertEquals(annotation.value(), StabilityLevel.EXPERIMENTAL);
    }

    @Test(description = "Test ApiStability annotation on method")
    public void testMethodAnnotation() throws NoSuchMethodException {
        ApiStability annotation = TestClass.class.getMethod("deprecatedMethod").getAnnotation(ApiStability.class);
        assertEquals(annotation.value(), StabilityLevel.DEPRECATED);
    }

    @Test(description = "Test ApiStability annotation with default value")
    public void testDefaultValue() throws NoSuchMethodException {
        ApiStability annotation = TestClass.class.getMethod("stableMethod").getAnnotation(ApiStability.class);
        assertEquals(annotation.value(), StabilityLevel.STABLE);
    }

    @ApiStability(StabilityLevel.EXPERIMENTAL)
    static class TestClass {

        @ApiStability(StabilityLevel.DEPRECATED)
        public void deprecatedMethod() {
        }

        @ApiStability
        public void stableMethod() {
        }
    }
}
