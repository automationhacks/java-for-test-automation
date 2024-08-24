package io.automationhacks.java08._05_lambda_expressions;

import static com.google.common.truth.Truth.assertWithMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.function.Function;

public class LambdaExpressionsTest {
    private final Logger logger = LoggerFactory.getLogger(LambdaExpressionsTest.class);
    private String value = "Outer scope";

    // appendStr is a higher order function since it can accept a lambda as an argument and invoke
    // the
    // method
    // specified in the functional expression in this case
    public String appendStr(String target, IStringAppender lambda) {
        return lambda.append(target);
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

        // Tip: Don't treat lambda expression as a substitute for anonymous inner classes, since:

        // When we use an inner class, it creates a new scope. We can hide local variables from the
        // enclosing scope by instantiating new local variables with the same names. We can also use
        // the keyword this inside our inner class as a reference to its instance.
        //
        // Lambda expressions, however, work with enclosing scope. We can’t hide variables from the
        // enclosing scope inside the lambda’s body. In this case, the keyword this is a reference
        // to an enclosing instance.
        IStringAppender stringAppender = param -> param + " from lambda function";

        // Above could also have been expressed using an anonymous inner class while making the code
        // verbose, lambda expressions are much more concise and easy to read

        //        IStringAppender stringAppender = new IStringAppender() {
        //            @Override
        //            public String method(String param) {
        //                return param + " from lambda function";
        //            }
        //        };

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

    @Test
    public void testlambdaExpressionScopeVsAnonymousInnerClassScope() {
        var iStringAppender =
                new IStringAppender() {
                    private final String value = "Anonymous inner class scope";

                    @Override
                    public String append(String string) {
                        return "%s --> %s".formatted(string, this.value);
                    }
                };
        var result = iStringAppender.append("Hola");
        logger.info("Result: %s".formatted(result));
    }
}
