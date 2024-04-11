package io.automationhacks.java11._04_local_variable_in_lambda;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

public class LocalVariableInLambda {
    @Test
    public void testLocalVariableSyntaxInLambda() {
        var languages = Arrays.asList("Java", "Python", "Ruby", "JavaScript");
        String languageInCaps =
                languages.stream()
                        .map((@Nonnull var language) -> language.toUpperCase())
                        .collect(Collectors.joining(", "));

        assertWithMessage("Local variable inference in lambda did not work")
                .that(languageInCaps)
                .isEqualTo("JAVA, PYTHON, RUBY, JAVASCRIPT");
    }
}
