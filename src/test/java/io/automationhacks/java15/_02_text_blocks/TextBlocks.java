package io.automationhacks.java15._02_text_blocks;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

public class TextBlocks {

    @Test
    public void testTextBlocks() {
        String html =
                """
                    {
                        "name": "John",
                        "age": 30
                    }
                    """;
        assertWithMessage("Unexpected HTML")
                .that(html)
                .isEqualTo("{\n    \"name\": \"John\",\n    \"age\": 30\n}\n");
    }

    @Test
    public void testStringMethodsOnTextBlocks() {
        String html =
                """
                    {
                        "name": "John",
                        "age": 30
                    }
                    """;

        assertWithMessage("Text block did not contain expected").that(html.contains("John")).isTrue();
        assertWithMessage("Text block value is at 0th index").that(html.indexOf("30")).isGreaterThan(0);
    }
}
