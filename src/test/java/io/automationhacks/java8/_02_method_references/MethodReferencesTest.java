package io.automationhacks.java8._02_method_references;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.List;

record User(String name) {}

public class MethodReferencesTest {
    List<String> users = List.of("John", "Doe", "Jane", "", "Alice", "", "Bob", "Charlie");

    @Test
    public void testStaticMethodReferences() {

        // Using lambda expression
        boolean emptyUsersFound = users.stream().anyMatch(user -> user.isEmpty());
        // Using static method reference
        // Syntax: ClassName::staticMethodName
        boolean emptyUsersFoundUsingStaticMethodReference =
                users.stream().anyMatch(String::isEmpty);

        assertWithMessage("Empty users not found")
                .that(emptyUsersFoundUsingStaticMethodReference)
                .isEqualTo(emptyUsersFound);
    }

    @Test
    public void testInstanceMethodForObjectOfAGivenType() {
        var count = users.stream().filter(String::isEmpty).count();
        assertWithMessage("Empty users count does not match").that(count).isEqualTo(2);
    }

    @Test
    public void testMethodReferenceToConstructor() {
        var newUsers = users.stream().map(User::new).toList();
        assertWithMessage("Users do not have same size").that(newUsers).hasSize(users.size());
    }
}
