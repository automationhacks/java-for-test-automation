package io.automationhacks.java17._02_pseudo_random_generator;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

public class PseudoRandomGeneratorTest {
    private final Logger logger = Logger.getLogger(PseudoRandomGeneratorTest.class.getName());

    @Test
    public void testPseudoRandomGenerator() {
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/RandomGeneratorFactory.html

        var randomDoubles = new ArrayList<>();
        RandomGeneratorFactory.of("Random")
                .create()
                .doubles(100)
                .forEach(randomDoubles::add);

        String collected =
                randomDoubles.stream().map(String::valueOf).collect(Collectors.joining(", "));
        logger.info("Random Doubles: %s".formatted(collected));

        assertWithMessage("Random doubles did not contain expected")
                .that(randomDoubles.size())
                .isEqualTo(100);
    }
}
