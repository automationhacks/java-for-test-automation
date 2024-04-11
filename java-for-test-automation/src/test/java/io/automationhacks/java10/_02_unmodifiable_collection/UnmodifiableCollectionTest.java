package io.automationhacks.java10._02_unmodifiable_collection;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable Configuration Data:
 * When you want to provide configuration data to different parts of your application but prevent them from modifying it.
 * For example, storing application settings or constants in an unmodifiable map.
 *
 * Thread Safety:
 * In multi-threaded environments, unmodifiable collections can be shared among threads without worrying about concurrent modifications.
 * They act as read-only snapshots of the original data.
 *
 * API Design:
 * When designing APIs, returning unmodifiable collections ensures that clients cannot accidentally modify the internal state.
 * For instance, a method returning a list of supported options should return an unmodifiable list.
 *
 * Caching and Memoization:
 * To cache computed results or memoize expensive function calls.
 * An unmodifiable map can store previously computed results for specific inputs.
 *
 * Testing and Mocking:
 * In unit testing, you can use unmodifiable collections to create test data that remains constant during test execution.
 * Mocking dependencies with unmodifiable collections ensures they are not accidentally modified.
 */
public class UnmodifiableCollectionTest {

    @Test
    public void
            whenModifyUnmodifiableCollectionAfterCopy_ThenUnsupportedOperationExceptionIsThrown() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add((int) (Math.random() * 100));
        }

        List<Integer> unmodifiableNumbers = List.copyOf(numbers);
        assertWithMessage("Unmodifiable collection is not same as original collection")
                .that(unmodifiableNumbers)
                .containsExactlyElementsIn(numbers);

        try {
            unmodifiableNumbers.add(100);
        } catch (UnsupportedOperationException e) {
            assertWithMessage("Unmodifiable collection should not allow modification")
                    .that(e)
                    .isInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void whenModifyToUnmodifiableList_thenThrowsException() {
        List<Integer> someIntList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            someIntList.add((int) (Math.random() * 100));
        }

        // toList() returns an unmodifiable list
        // this is equivalent to .collect(Collectors.toUnmodifiableList());
        var evenList = someIntList.stream().filter(i -> i % 2 == 0).toList();
        evenList.add(4);
    }
}
