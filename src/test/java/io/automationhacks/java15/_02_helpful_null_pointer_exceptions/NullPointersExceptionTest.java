package io.automationhacks.java15._02_helpful_null_pointer_exceptions;

import org.testng.annotations.Test;

import java.util.logging.Logger;

public class NullPointersExceptionTest {
    private static final Logger logger =
            Logger.getLogger(NullPointersExceptionTest.class.getName());

    @Test(
            expectedExceptions = NullPointerException.class,
            expectedExceptionsMessageRegExp =
                    "Cannot load from object array because \"names\" is null")
    public void testNullPointerExceptions() {
        String[] names = null;
        // Previously, the exception message would only tell the location of the exception.
        // Exception in thread "main" java.lang.NullPointerException
        // at com.baeldung.MyClass.main(MyClass.java:27)
        logger.info("First name: %s".formatted(names[0]));
    }
}
