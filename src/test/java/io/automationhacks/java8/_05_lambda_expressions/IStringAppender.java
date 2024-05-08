package io.automationhacks.java8._05_lambda_expressions;

// Adding @FunctionalInterface prevents someone else to add another abstract method
// by mistake and ensures this interface can ONLY have one method
@FunctionalInterface
public interface IStringAppender {
    String append(String string);

    // We can have multiple default methods in a functional interface, however overusing defaults is
    // not a good architectural decision
    // We should use them for:
    // 1. You need to add methods to an existing interface without breaking existing
    // implementations.
    // 2. It’s a compromise to maintain backward compatibility.
    // 3. You’re upgrading interfaces without causing chaos.
    default void aDefaultMethod() {}

    default void anotherDefaultMethod() {}

    default void yetAnotherDefaultMethod() {}
}
