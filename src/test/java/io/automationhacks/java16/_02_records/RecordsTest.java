package io.automationhacks.java16._02_records;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

/**
 * Records generate constructor, getter, toString, equals and hashCode automatically. They help to
 * reduce boilerplate code in POJO
 */
public class RecordsTest {
    @Test
    public void testRecords() {
        UserCredentials userCredentials = new UserCredentials("admin", "admin");
        UserCredentials userCredentials2 = new UserCredentials("admin", "admin");

        // Records are immutable and equals and hashcode are automatically generated
        assertWithMessage("Records are not equal")
                .that(userCredentials)
                .isEqualTo(userCredentials2);
        assertWithMessage("Hashcode did not match")
                .that(userCredentials.hashCode())
                .isEqualTo(userCredentials2.hashCode());

        // Getters are automatically generated for records
        assertWithMessage("User name was not correct")
                .that(userCredentials.userName())
                .isEqualTo("admin");
        assertWithMessage("Password did not match")
                .that(userCredentials.password())
                .isEqualTo(userCredentials2.password());
    }

    @Test
    public void recordsAsInnerClasses() {
        // With the release of Java 16, we can now define records as class members of inner classes
        class OuterClass {
            class InnerClass {
                final UserCredentials userCredentials = new UserCredentials("admin", "admin");
            }
        }

        // Below shows an example wherein a record can be accessed from an inner class
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        UserCredentials userCredentials = innerClass.userCredentials;

        assertWithMessage("User name was not correct")
                .that(userCredentials.userName())
                .isEqualTo("admin");
    }
}
