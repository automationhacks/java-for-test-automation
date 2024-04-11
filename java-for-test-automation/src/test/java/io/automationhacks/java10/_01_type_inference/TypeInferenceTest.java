package io.automationhacks.java10._01_type_inference;

import static com.google.common.truth.Truth.assertWithMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

// Introduced in Java 10
// Source: https://www.baeldung.com/java-10-local-variable-type-inference
public class TypeInferenceTest {
    private final Logger logger = LoggerFactory.getLogger(TypeInferenceTest.class);

    // Does not work for member variables
    // Below is a compilation error
    // var lastName = "Hacks";

    @Test
    public void testTypeInference() {
        // Only available for local variables
        var name = "AutomationHacks";
        logger.info("Name: %s".formatted(name));

        assertWithMessage("Name does not match").that(name).isEqualTo("AutomationHacks");
        assertWithMessage("Name is not of type String").that(name).isInstanceOf(String.class);
    }
}
