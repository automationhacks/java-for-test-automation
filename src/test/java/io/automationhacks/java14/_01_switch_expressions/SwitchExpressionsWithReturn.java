package io.automationhacks.java14._01_switch_expressions;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

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

    // In case switch has to execute a block of code we can use yield keyword to return the value
    // from
    // the block
    public String switchExpressionWithReturn(int code) {
        return switch (code) {
            case 200, 201, 202 -> "Success";
            case 400, 404 -> "Client Error";
            case 500, 503 -> {
                String errorType = "Server Error";
                yield errorType;
            }
            default -> "Unknown";
        };
    }
}
