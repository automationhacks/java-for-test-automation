package io.automationhacks.java8._04_streams;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    private List<String> names = Arrays.asList("Rob", "James", "Steve", "James");

    @Test
    public void createStreamTest() {
        // Different ways of creating streams
        // Stream<T> is added in base Collection and as such would be applicable for any collection
        String[] arr = new String[] {"a", "b", "c", "d"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> stream2 = Stream.of("a", "b", "c", "d");
        assertThat(stream.toList()).containsAnyIn(stream2.toList());
    }

    @Test
    public void createParallelStreamTest() {
        // We can run functions in parallel on a collection
        var updated = names.parallelStream().map(elem -> elem.toUpperCase()).toList();
        assertThat(String.join(" ", updated)).isEqualTo("ROB JAMES STEVE JAMES");
    }

    @Test
    public void chainingOfMethodCallsInStreams() {
        // Streams don't mutate the sources
        var count = names.stream().distinct().count();
        assertWithMessage("Unique names does not match").that(count).isEqualTo(3);
    }

    @Test
    public void iterationUsingStreams() {
        boolean isNamePresent = names.stream().anyMatch(elem -> elem.contains("Steve"));
        assertWithMessage("Could not find name in names").that(isNamePresent).isTrue();
    }

    @Test
    public void filteringUsingStreams() {
        var noOfJames = names.stream().filter(elem -> elem.contains("James")).count();
        assertWithMessage("How many James are present in names").that(noOfJames).isEqualTo(2);
    }
}
