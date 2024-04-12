package io.automationhacks.java14._03_records;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

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
}
