package io.automationhacks.java8._05_lambda_expressions;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.function.Function;

@FunctionalInterface
interface IStringAppender {
    String method(String string);
}

public class LambdaExpressionsTest {

    // appendStr is a higher order function since it can accept a lambda as an argument and invoke
    // the
    // method
    // specified in the functional expression in this case
    public String appendStr(String target, IStringAppender lambda) {
        return lambda.method(target);
    }

    // We can use Function<String, String> for functional interface provided by default in Java
    // To apply a lambda expression on a given argument, for example, see the definition of this
    // below
    // @FunctionalInterface
    // public interface Function<T, R> {
    //
    //    /**
    //     * Applies this function to the given argument.
    //     *
    //     * @param t the function argument
    //     * @return the function result
    //     */
    //    R apply(T t); }
    public String appendStr(String target, Function<String, String> fn) {
        return fn.apply(target);
    }

    @Test
    public void testLambdaExpressions() {
        // stringAppender is a function that accepts one argument and returns another string after
        // concatenation with a
        // hardcoded string
        IStringAppender stringAppender = param -> param + " from lambda function";

        // We can pass this lambda expression as an argument
        String result = appendStr("Hello", stringAppender);

        assertWithMessage("Result of append does not match")
                .that(result)
                .isEqualTo("Hello from lambda function");
    }

    @Test
    public void alternativeTestForLambdaExpression() {
        Function<String, String> fn = param -> param + " from lambda";
        String result = appendStr("Hello", fn);

        assertWithMessage("Result of append does not match")
                .that(result)
                .isEqualTo("Hello from lambda");
    }
}
