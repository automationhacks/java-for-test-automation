package io.automationhacks.preview._01_pattern_matching_for_switch;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.logging.Logger;

// Base class Employee
sealed class Employee permits Leader, Engineer {
    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Subclass Leader
final class Leader extends Employee {
    public Leader(String name) {
        super(name);
    }
}

// Subclass Engineer
non-sealed class Engineer extends Employee {
    public Engineer(String name) {
        super(name);
    }
}

public class PatternMatchingForSwitchTest {
    private static final Logger logger =
            Logger.getLogger(PatternMatchingForSwitchTest.class.getName());

    @Test
    public void testPatternMatchingForSwitch() {
        var leaderOrEngineers = new Employee[] {new Leader("John"), new Engineer("Doe")};

        for (Employee employee : leaderOrEngineers) {
            String roleDescription =
                    switch (employee) {
                            /*
                             Here we are using the pattern matching for switch expression
                             We are matching the type of the employee object and then extracting
                             the name
                             of the employee object
                             Previously we would have been required to use instanceof and then
                             cast
                            */
                        case Leader leader -> "Leader: %s".formatted(leader.getName());
                        case Engineer engineer -> "Engineer: %s".formatted(engineer.getName());
                        default ->
                                throw new IllegalStateException(
                                        "Unexpected value: %s".formatted(employee));
                    };

            logger.info(roleDescription);
            assertWithMessage("Role description does not match")
                    .that(roleDescription)
                    .contains(employee.getName());
        }
    }
}
