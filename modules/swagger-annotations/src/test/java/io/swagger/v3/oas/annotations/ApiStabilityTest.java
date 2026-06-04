package io.swagger.v3.oas.annotations;

import io.swagger.v3.oas.annotations.enums.ApiStabilityLevel;
import org.testng.annotations.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ApiStabilityTest {

    @ApiStability(ApiStabilityLevel.STABLE)
    private static class StableResource {

        @ApiStability(ApiStabilityLevel.DEPRECATED)
        public void deprecatedOperation() {
        }
    }

    @Test
    public void shouldExposeConfiguredStabilityLevelOnTypeAndMethod() throws Exception {
        ApiStability typeAnnotation = StableResource.class.getAnnotation(ApiStability.class);
        assertNotNull(typeAnnotation);
        assertEquals(typeAnnotation.value(), ApiStabilityLevel.STABLE);

        Method method = StableResource.class.getDeclaredMethod("deprecatedOperation");
        ApiStability methodAnnotation = method.getAnnotation(ApiStability.class);
        assertNotNull(methodAnnotation);
        assertEquals(methodAnnotation.value(), ApiStabilityLevel.DEPRECATED);
    }

    @Test
    public void shouldDeclareRuntimeRetentionAndTypeMethodTargets() {
        Retention retention = ApiStability.class.getAnnotation(Retention.class);
        assertNotNull(retention);
        assertEquals(retention.value(), RetentionPolicy.RUNTIME);

        Target target = ApiStability.class.getAnnotation(Target.class);
        assertNotNull(target);

        Set<ElementType> supportedTargets = new HashSet<>(Arrays.asList(target.value()));
        assertTrue(supportedTargets.contains(ElementType.TYPE));
        assertTrue(supportedTargets.contains(ElementType.METHOD));
        assertTrue(supportedTargets.contains(ElementType.ANNOTATION_TYPE));
    }
}
