package io.automationhacks.java14._01_switch_expressions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Set;

public class SwitchExpressions {
    private final Logger logger = LoggerFactory.getLogger(SwitchExpressions.class);

    @Test
    public void testSwitchExpressionsForHttpCodes() {
        Set<Integer> httpCodes = Set.of(200, 201, 202, 400, 404, 500, 503);

        for (int code : httpCodes) {
            String message =
                    switch (code) {
                        case 200, 201, 202 -> "Success";
                        case 400, 404 -> "Client Error";
                        case 500, 503 -> "Server Error";
                        default -> "Unknown";
                    };

            logger.info("Code: %d Message: %s%n".formatted(code, message));
        }
    }
}
