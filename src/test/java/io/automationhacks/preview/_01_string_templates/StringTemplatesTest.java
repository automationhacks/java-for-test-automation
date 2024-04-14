package io.automationhacks.preview._01_string_templates;

import org.testng.annotations.Test;

import static java.lang.StringTemplate.STR;

public class StringTemplatesTest {
    @Test
    public void testStringTemplates() {
        String name = "Java 21";
        String version = "21";
        String message = STR."""
                Welcome to \{name}
                This is version \{version}
                """.formatted(name, version);
        System.out.println(message);
    }
}
