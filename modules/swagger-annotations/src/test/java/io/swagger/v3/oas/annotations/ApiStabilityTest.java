package io.swagger.v3.oas.annotations;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ApiStabilityTest {

    @ApiStability(ApiStability.Level.EXPERIMENTAL)
    public static class AnnotatedClass {
        
        @ApiStability(ApiStability.Level.STABLE)
        public void stableMethod() {
        }
        
        @ApiStability(ApiStability.Level.DEPRECATED)
        public void deprecatedMethod() {
        }
        
        @ApiStability
        public void defaultMethod() {
        }
    }

    @Test
    public void testClassAnnotation() {
        ApiStability annotation = AnnotatedClass.class.getAnnotation(ApiStability.class);
        assertNotNull(annotation, "Annotation should be present on class");
        assertEquals(annotation.value(), ApiStability.Level.EXPERIMENTAL);
    }

    @Test
    public void testMethodAnnotationStable() throws NoSuchMethodException {
        ApiStability annotation = AnnotatedClass.class.getMethod("stableMethod").getAnnotation(ApiStability.class);
        assertNotNull(annotation, "Annotation should be present on method");
        assertEquals(annotation.value(), ApiStability.Level.STABLE);
    }

    @Test
    public void testMethodAnnotationDeprecated() throws NoSuchMethodException {
        ApiStability annotation = AnnotatedClass.class.getMethod("deprecatedMethod").getAnnotation(ApiStability.class);
        assertNotNull(annotation, "Annotation should be present on method");
        assertEquals(annotation.value(), ApiStability.Level.DEPRECATED);
    }
    
    @Test
    public void testMethodAnnotationDefault() throws NoSuchMethodException {
        ApiStability annotation = AnnotatedClass.class.getMethod("defaultMethod").getAnnotation(ApiStability.class);
        assertNotNull(annotation, "Annotation should be present on method");
        assertEquals(annotation.value(), ApiStability.Level.STABLE); // Testing default value
    }
}