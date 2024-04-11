package io.automationhacks.java12._02_pattern_matching_instance_of;

import org.testng.annotations.Test;

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
