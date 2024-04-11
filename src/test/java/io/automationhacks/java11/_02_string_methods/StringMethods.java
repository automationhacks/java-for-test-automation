package io.automationhacks.java11._02_string_methods;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

public class StringMethods {
    @Test
    public void testStringMethods() {
        String str = " AutomationHacks ";
        assertWithMessage("Length does not match").that(str.length()).isEqualTo(17);
        assertWithMessage("Is not blank").that(str.isBlank()).isFalse();
        assertWithMessage("Strip does not match").that(str.strip()).isEqualTo("AutomationHacks");
        assertWithMessage("Strip Leading does not match")
                .that(str.stripLeading())
                .isEqualTo("AutomationHacks ");
        assertWithMessage("Strip Trailing does not match")
                .that(str.stripTrailing())
                .isEqualTo(" AutomationHacks");
        assertWithMessage("Repeat does not match")
                .that(str.repeat(2))
                .isEqualTo(" AutomationHacks  AutomationHacks ");
        assertWithMessage("Lines does not match").that(str.lines().count()).isEqualTo(1);
    }
}
