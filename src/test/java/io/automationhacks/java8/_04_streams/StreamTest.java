package io.automationhacks.java8._04_streams;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class StreamTest {
  private final Logger logger = LoggerFactory.getLogger(StreamTest.class);
  private final List<String> names = Arrays.asList("Rob", "James", "Steve", "James");

  @Test
  public void createStreamTest() {
    // Different ways of creating streams
    // Stream<T> is added in base Collection and as such would be applicable for any collection
    String[] arr = new String[] {"a", "b", "c", "d"};
    // Use stream method in collection
    Stream<String> stream = Arrays.stream(arr);
    // Directly create a stream
    Stream<String> stream2 = Stream.of("a", "b", "c", "d");
    assertThat(stream.toList()).containsAnyIn(stream2.toList());
  }

  @Test
  public void createParallelStreamTest() {
    // We can run functions in parallel on a collection to do operations faster
    // Here we also directly use method references
    var updated = names.parallelStream().map(String::toUpperCase).toList();
    assertThat(String.join(" ", updated)).isEqualTo("ROB JAMES STEVE JAMES");
  }

  @Test
  public void chainingOfMethodCallsInStreams() {
    // Streams don't mutate the source and we can chain methods
    // Here we 1st get only distinct elements and then find their counts
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

  @Test
  public void mappingUsingStreams() {
    var filePathStrings =
        List.of("foo/bar", "bar/foo", "foobar/foo", "foobar/bar", "foobarfoo/foo");

    // We can use map() to call a method on each elem in the stream
    var filePathsWithUserDir =
        filePathStrings.stream()
            .map(elem -> "%s/%s".formatted("~/Users/hypotheticalUser", elem))
            .toList();
    logger.info(String.join(",", filePathsWithUserDir));
  }


}
