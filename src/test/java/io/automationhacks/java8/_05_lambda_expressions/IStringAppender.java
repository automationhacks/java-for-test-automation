package io.automationhacks.java8._05_lambda_expressions;

// Adding @FunctionalInterface prevents someone else to add another abstract method
// by mistake and ensures this interface can ONLY have one method
@FunctionalInterface
public interface IStringAppender {
    String method(String string);
}
