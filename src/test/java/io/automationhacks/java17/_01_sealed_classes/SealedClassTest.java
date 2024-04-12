package io.automationhacks.java17._01_sealed_classes;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

public class SealedClassTest {
    /**
     * Sealed classes allow modeling Domain Possibilities with a clean inheritance hierarchy.
     *
     * <p><b>Benefits of Sealed Classes for testing:</b>
     * <li>Clarity: Sealed classes make it clear which subclasses are valid. Test data is less
     *     error-prone.
     * <li>Security: Prevents accidental misuse by disallowing unknown subclasses.
     * <li>Readable Tests: E2E tests become more self-explanatory due to the restricted inheritance.
     */
    @Test
    public void testSealedClasses() {
        var john = new Employee(1, "Engineering");
        var jane = new Manager(1, "Engineering");

        assertWithMessage("Employee is not an instance of Person")
                .that(john instanceof Person)
                .isEqualTo(true);
        assertWithMessage("Employee Id does not match").that(getId(john)).isEqualTo(1);

        assertWithMessage("Manager is not an instance of Person")
                .that(jane instanceof Person)
                .isEqualTo(true);
        assertWithMessage("Manager id is does not match").that(getId(jane)).isEqualTo(1);
    }

    private static int getId(Person person) {
        int id;
        if (person instanceof Employee) {
            id = ((Employee) person).getId();
        } else if (person instanceof Manager) {
            id = ((Manager) person).getSupervisorId();
        } else {
            id = 0;
        }
        return id;
    }
}
