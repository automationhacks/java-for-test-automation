package io.automationhacks.java08._02_method_references;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.List;

class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    static boolean isRealUser(User user) {
        return !user.name.isEmpty();
    }

    public boolean isLegalName(User user) {
        return user.name.length() > 1;
    }
}

public class MethodReferencesTest {
    // The list has some user who name are empty
    List<User> users =
            List.of(
                    new User("John"),
                    new User("Doe"),
                    new User("Jane"),
                    new User(""),
                    new User("Alice"),
                    new User(""));

    @Test
    public void testStaticMethodReferences() {
        // Using lambda expression
        var isRealUser = users.stream().anyMatch(u -> User.isRealUser(u));
        // Using method references for static methods
        var isRealUserMethodRef = users.stream().anyMatch(User::isRealUser);
        assertWithMessage("Is real user does not match")
                .that(isRealUserMethodRef)
                .isEqualTo(isRealUser);
    }

    @Test
    public void testInstanceMethodForObjectOfAGivenType() {
        User user = new User("");
        // Instance method reference - containingInstance::methodName
        var isLegalName = users.stream().anyMatch(user::isLegalName);
        assertWithMessage("Not a legal name").that(isLegalName).isTrue();
    }
}
