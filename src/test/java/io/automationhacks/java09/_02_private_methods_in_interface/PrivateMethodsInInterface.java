package io.automationhacks.java09._02_private_methods_in_interface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Introduced in Java 9
public interface PrivateMethodsInInterface {
    Logger logger = LoggerFactory.getLogger(PrivateMethodsInInterface.class);

    private static String staticPrivate() {
        return "static private";
    }

    private String instancePrivate() {
        return "instance private";
    }

    default void check() {
        String result = staticPrivate();
        logger.info("Result: %s".formatted(result));

        // We initialize an anonymous class to access the instance private method
        PrivateMethodsInInterface pvt = new PrivateMethodsInInterface() { // Anonymous class
                };
        result = pvt.instancePrivate();
        logger.info("Result: %s".formatted(result));
    }
}
