package io.automationhacks.java13._01_switch_expressions_jep_354;


import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class SwitchExpressionsWithReturn {
    @Test
    public void testSwitchExpressionsForHttpCodes() {
        assertWithMessage("Unexpected error message")
                .that(switchExpressionWithReturn(200))
                .isEqualTo("Success");
        assertWithMessage("Unexpected error message")
                .that(switchExpressionWithReturn(400))
                .isEqualTo("Client Error");
    }

    // In Java 13, this return was possible with yield keyword which was
    // later removed and we can directly return from switch expression.
    public String switchExpressionWithReturn(int code) {
        return switch (code) {
            case 200, 201, 202 -> "Success";
            case 400, 404 -> "Client Error";
            case 500, 503 -> "Server Error";
            default -> "Unknown";
        };
    }
}
