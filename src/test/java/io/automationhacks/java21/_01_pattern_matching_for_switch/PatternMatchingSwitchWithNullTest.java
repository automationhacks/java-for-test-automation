package io.automationhacks.java21._01_pattern_matching_for_switch;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

public class PatternMatchingSwitchWithNullTest {
    @Test
    public void patternMatchingWithNull() {
        assertWithMessage("Confirmation not provided")
                .that(getConfirmation("Yes"))
                .isEqualTo("Confirmed");
        assertWithMessage("Confirmation provided")
                .that(getConfirmation("No"))
                .isEqualTo("Not Confirmed");
        assertWithMessage("Null input provided")
                .that(getConfirmation(null))
                .isEqualTo("Input is null");
        assertWithMessage("Invalid input provided")
                .that(getConfirmation("Invalid"))
                .isEqualTo("Invalid input");
    }

    private static String getConfirmation(String input) {
        var output = "";
        switch (input) {
                // Switch expression can now match a null as well
            case null -> output = "Input is null";
                // We can now use pattern matching in switch expressions
                // Here we are matching the type of the input and then checking the value
                // And build complex checks inside a case as well.
            case String s -> {
                if ("Yes".equalsIgnoreCase(s)) {
                    output = "Confirmed";
                } else if ("No".equalsIgnoreCase(s)) {
                    output = "Not Confirmed";
                } else {
                    output = "Invalid input";
                }
            }
        }
        return output;
    }

    @Test
    public void patternMatchingWithNullAndWhen() {
        assertWithMessage("Confirmation not provided")
                .that(getConfirmationV2("Yes"))
                .isEqualTo("Confirmed");
        assertWithMessage("Confirmation provided")
                .that(getConfirmationV2("No"))
                .isEqualTo("Not Confirmed");
        assertWithMessage("Null input provided")
                .that(getConfirmationV2(null))
                .isEqualTo("Input is null");
        assertWithMessage("Invalid input provided")
                .that(getConfirmationV2("Invalid"))
                .isEqualTo("Invalid input");
    }

    /**
     * This method uses the new switch expression with pattern matching and when
     * @param input input to be checked
     * @return output based on the input
     */
    private static String getConfirmationV2(String input) {
        var output = "";
        switch (input) {
            // Switch expression can now match a null as well
            case null -> output = "Input is null";
            // We can now use pattern matching in switch expressions
            // Here we are matching the type of the input and then checking the value
            // And build complex checks inside a case as well.
            case String s when s.equalsIgnoreCase("Yes") -> output = "Confirmed";
            case String s when s.equalsIgnoreCase("No") -> output = "Not Confirmed";
            case String ignored -> output = "Invalid input";
            }
        return output;
    }
}
