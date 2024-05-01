package io.automationhacks.java8._04_streams;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

// https://www.baeldung.com/java-8-streams-introduction
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

  @Test
  public void flatMappingUsingStreams() {
    record Person(String name, List<String> moods) {}
    var people =
        List.of(
            new Person("John", List.of("Angry", "Happy", "Sad")),
            new Person("Jane", List.of("Adventurous", "Calm")));

    // We can use flatMap to convert a nested list into a flat list
    var flatMoods = people.stream().flatMap(elem -> elem.moods.stream());
    String moods = String.join(", ", flatMoods.toList());
    logger.info(moods);
    assertWithMessage("Moods don't match")
        .that(moods)
        .isEqualTo("Angry, Happy, Sad, Adventurous, Calm");
  }

  @Test
  public void matchingUsingStreams() {
    var empty = List.of("");
    assertWithMessage("").that(empty.stream().anyMatch(String::isEmpty)).isTrue();

    var names = List.of("Tango", "Charlie");

    assertWithMessage("Names do not contain 'a'")
        .that(names.stream().allMatch(elem -> elem.contains("a")))
        .isTrue();
    assertWithMessage("Names contain 'x'")
        .that(names.stream().noneMatch(elem -> elem.contains("x")))
        .isTrue();
  }

  @Test
  public void reduceUsingStreams() {
    var randomNumbers = new ArrayList<Integer>();
    RandomGeneratorFactory.of("Random").create().ints(10).forEach(randomNumbers::add);

    var start = 10;

    // Reduce takes a start value and then calls an accumulator method that would be applied on the
    // previous value
    // Here we use Integer::sum method reference but this can be expanded as below as well
    // var total = numbers.reduce(start, (a, b) -> a + b);
    var total = randomNumbers.stream().reduce(start, Integer::sum);
    logger.info("Sum total: %s".formatted(total));

    assertWithMessage("Total is not greater than 10").that(total).isNotNull();
  }
}
