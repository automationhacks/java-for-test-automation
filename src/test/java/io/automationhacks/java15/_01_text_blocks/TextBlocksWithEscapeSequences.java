package io.automationhacks.java15._01_text_blocks;

import static com.google.common.truth.Truth.assertWithMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TextBlocksWithEscapeSequences {
    private final Logger logger = LoggerFactory.getLogger(TextBlocksWithEscapeSequences.class);

    /**
     * \: to indicate the end of the line, so that a new line character is not introduced \s: to
     * indicate a single space
     */
    @Test
    public void testEscapeSequencesOnTextBlocks() {
        var sentence = "A quick brown fox jumps over the lazy dog; the dog barks!";
        // \ improves the readability of the sentence for a human eye but does not add a new line
        // after dog;.
        var textBlock =
                """
               A quick brown fox jumps over the lazy dog;\
                the dog barks!
               """;
        logger.info("Sentence: %s".formatted(sentence));
        logger.info("Text block: %s".formatted(textBlock));
        assertWithMessage("Text block did not contain expected")
                .that(textBlock.contains(sentence))
                .isTrue();
    }
}
