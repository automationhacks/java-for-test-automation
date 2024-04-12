package io.automationhacks.java16._01_pattern_matching_instance_of;

import org.testng.annotations.Test;

// Introduced in Java 12, added in Java 14
public class PatternMatchingInstanceOf {

    @Test
    public void testInstanceOf() {
        Object obj = "Hello";
        patternMatchAndAssert(obj);

        Object obj2 = 123;
        patternMatchAndAssert(obj2);
    }

    private static void patternMatchAndAssert(Object obj) {
        if (obj instanceof String str) {
            // The compiler will automatically inject the typecasted String s variable for us.
            System.out.println("String length: " + str.length());
        } else {
            System.out.println("Not a String");
        }

        // Pre java 12 and this feature we would have to do below:
        // if (obj instanceof String) {
        //    String str = (String) obj;
        //    System.out.println("String length: " + str.length());
        // }
    }
}
