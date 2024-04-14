# Java for Test automation

## Why?

Hey, folks! 👋🏼

- Many teams still use Java 8 or Java 11 for testing, but Java has come a long way and evolved since then. The
  latest LTS version of Java 21 has a bunch of cool features from other JVM languages and modern stuff too.
- Upgrading to the latest version of Java can give testers a lot of new tools to work with. They can write more robust,
  easy-to-maintain, and expressive automated tests.
- In this repo, we'll go over the major language features in Java version 8 and later, and how we can use them to boost
  our test automation.

Excited? Let’s go. ⚡

## [Java 9](https://www.baeldung.com/new-java-9)

- **JShell:** REPL for Java, allows for quick testing of code snippets

```shell
jshell> "hey this is something.new".substring(5, 10)
$1 ==> "his i"
```

- **Private methods in Interface**: Useful when we have a default method that is used by multiple default methods in the
  same interface.

```java
public interface PrivateMethodsInInterface {
    Logger logger = LoggerFactory.getLogger(PrivateMethodsInInterface.class);

    private static String staticPrivate() {
        return "static private";
    }

    private String instancePrivate() {
        return "instance private";
    }

    default void check() {
        String result = staticPrivate();
        logger.info("Result: %s".formatted(result));

        // We initialize an anonymous class to access the instance private method
        PrivateMethodsInInterface pvt = new PrivateMethodsInInterface() { // Anonymous class
        };
        result = pvt.instancePrivate();
        logger.info("Result: %s".formatted(result));
    }
}

```

## [Java 10](https://www.baeldung.com/java-10-overview)

- **Local Type inference**: Use `var` to have the compiler infer the type of the variable

```text
var name = "AutomationHacks";
logger.info("Name: %s".formatted(name));
```

- **Unmodifiable collections**: Use `copyOf` and `.toList` to create immutable collections

```java
List<Integer> unmodifiableNumbers = List.copyOf(numbers);
var evenList = someIntList.stream().filter(i -> i % 2 == 0).toList();
```

## [Java 11](https://www.baeldung.com/java-11-new-features)

- **HTTP client**: A new HTTP client that supports HTTP/2 and WebSockets

```java

@Test
public void testHttpClient() {

    try (var client =
                 HttpClient.newBuilder()
                         .version(HttpClient.Version.HTTP_2)
                         .connectTimeout(Duration.ofSeconds(20))
                         .build()) {
        var request =
                HttpRequest.newBuilder()
                        .uri(URI.create("https://postman-echo.com/get"))
                        .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("Response: %s".formatted(response.body()));

        assertWithMessage("Echo API did not return a response").that(response.body()).isNotNull();
    } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
    }
}
```

- **String methods**: New methods added to the String class like `isBlank`, `strip`, `repeat`, `lines`,`stripLeading`,
  `stripTrailing`

```java

@Test
public void testStringMethods() {
    String str = " AutomationHacks ";
    assertWithMessage("Length does not match").that(str.length()).isEqualTo(17);
    assertWithMessage("Is not blank").that(str.isBlank()).isFalse();
    assertWithMessage("Strip does not match").that(str.strip()).isEqualTo("AutomationHacks");
    assertWithMessage("Strip Leading does not match")
            .that(str.stripLeading())
            .isEqualTo("AutomationHacks ");
    assertWithMessage("Strip Trailing does not match")
            .that(str.stripTrailing())
            .isEqualTo(" AutomationHacks");
    assertWithMessage("Repeat does not match")
            .that(str.repeat(2))
            .isEqualTo(" AutomationHacks  AutomationHacks ");
    assertWithMessage("Lines does not match").that(str.lines().count()).isEqualTo(1);
}
```

- **File methods**: New methods added to the Files class like `readString`, `writeString`, `deleteIfExists`

```java

@Test
public void testFileMethods() throws IOException {
    var tempDir = Files.createTempDirectory("demo");
    Path filePath =
            Files.writeString(Files.createTempFile(tempDir, "sample", ".txt"), "Sample text");
    String fileContent = Files.readString(filePath);
    assertWithMessage("File content does not match").that(fileContent).isEqualTo("Sample text");

    Files.deleteIfExists(filePath);
}
```

- **Local variable syntax for lambda parameters**: Use `var` for lambda parameters

```java
var languages = Arrays.asList("Java", "Python", "Ruby", "JavaScript");
String languageInCaps =
        languages.stream()
                .map((@Nonnull var language) -> language.toUpperCase())
                .collect(Collectors.joining(", "));
```

## Java 12 - 13

- No new language features

## [Java 14](https://www.baeldung.com/java-14-new-features)

- **Switch expressions with return**: Switch expressions can now be used as an expression and can return a value

```java
public class SwitchExpressions {
    private final Logger logger = LoggerFactory.getLogger(SwitchExpressions.class);

    @Test
    public void testSwitchExpressionsForHttpCodes() {
        Set<Integer> httpCodes = Set.of(200, 201, 202, 400, 404, 500, 503);

        for (int code : httpCodes) {
            String message =
                    switch (code) {
                        case 200, 201, 202 -> "Success";
                        case 400, 404 -> "Client Error";
                        case 500, 503 -> "Server Error";
                        default -> "Unknown";
                    };

            logger.info("Code: %d Message: %s%n".formatted(code, message));
        }
    }
}

```

## [Java 15](https://www.baeldung.com/java-15-new)

- **Text blocks**: Multiline strings can be created using text blocks

```java

@Test
public void testTextBlocks() {
    String html =
            """
                    {
                        "name": "John",
                        "age": 30
                    }
                    """;
    assertWithMessage("Unexpected HTML")
            .that(html)
            .isEqualTo("{\n    \"name\": \"John\",\n    \"age\": 30\n}\n");
}
```

- **Helpful Null pointer exception log provide more details**: The NullPointerException message now includes the name of
  the variable that was null

## [Java 16](https://www.baeldung.com/java-16-new-features)

- **Records**: Records are immutable data transfer objects (DTO) with all args constructor and getters

```java
// Records are immutable data transfer objects (DTO) with all args constructor and getters
// Records do have some restrictions. they are always final, they cannot be
// declared abstract, and they can’t use native methods.
public record UserCredentials(String userName, String password) {
}
```

- **Pattern matching for instanceof**: Pattern matching for instanceof allows for a more concise way to check the type
  of an
  object

```java
public void testInstanceOfPatternMatching() {
    Object obj = "AutomationHacks";
    if (obj instanceof String str) {
        logger.info("Length: %d".formatted(str.length()));
    }
}
```

## [Java 17](https://www.baeldung.com/java-17-new-features)

- **Sealed classes**: Sealed classes allow for restricting the classes that can extend a class

```java
// Sealed classes provide fine grained control over inheritance.
// Sealed classes can be abstract or non-abstract.
// Sealed classes can have subclasses that are sealed, non-sealed or final.
// Introduce new keywords: sealed, non-sealed, permits
public sealed class Person permits Employee, Manager {
}
```

- **Pseudo random number generator factory**: A new factory class to create pseudo random number generators

```java
public class PseudoRandomGeneratorTest {
    private final Logger logger = Logger.getLogger(PseudoRandomGeneratorTest.class.getName());

    @Test
    public void testPseudoRandomGenerator() {
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/RandomGeneratorFactory.html

        var randomDoubles = new ArrayList<>();
        RandomGeneratorFactory.of("Random").create().doubles(100).forEach(randomDoubles::add);

        String collected =
                randomDoubles.stream().map(String::valueOf).collect(Collectors.joining(", "));
        logger.info("Random Doubles: %s".formatted(collected));

        assertWithMessage("Random doubles did not contain expected")
                .that(randomDoubles.size())
                .isEqualTo(100);
    }
}

```

## Java 18 - 20

- No new language features

## [Java 21](https://www.baeldung.com/java-lts-21-new-features)

- **Pattern matching for switch**: Pattern matching for switch allows for more concise switch expressions
    - Support for null
    - Support for adding conditional checks using `when` keyword

```java

@Test
public void testPatternMatchingForSwitch() {
    var leaderOrEngineers = new Employee[]{new Leader("John"), new Engineer("Doe")};

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
                    default -> throw new IllegalStateException(
                            "Unexpected value: %s".formatted(employee));
                };

        logger.info(roleDescription);
        assertWithMessage("Role description does not match")
                .that(roleDescription)
                .contains(employee.getName());
    }
}
```

- **Record patterns**: Record patterns allow for destructuring of records in switch expressions

```java

@Test
public void testRecordPatternDestruction() {
    var location = new Location("Home", new GPSPoint(12.9716, 77.5946));

    // Destructuring of record, here we are extracting location directly into a variable loc
    if (location instanceof Location loc) {
        assertWithMessage("").that(loc.name()).isEqualTo("Home");
    }
}
```

- **Virtual threads**: Virtual threads are lightweight threads that are managed by the JVM. They are not bound to any OS
  thread and are scheduled by the JVM.

```java
public class VirtualThreadsTest {
    @Test
    public void testVirtualThreads() {
        // Virtual threads are lightweight threads that are managed by the JVM.
        // They are not bound to any OS thread and are scheduled by the JVM.

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.rangeClosed(1, 5_00)
                    .forEach(
                            i ->
                                    executor.submit(
                                            () -> {
                                                System.out.println(
                                                        "Task: %d running on virtual thread: %s"
                                                                .formatted(
                                                                        i, Thread.currentThread()));
                                                try {
                                                    Thread.sleep(Duration.ofSeconds(1));
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }));
        }
    }
}

```

## Preview

- String templates

## References

I read the following articles to understand the new features in Java versions, primarily from baeldung.com, please feel
free to check them out for more awesome Java content

- [Java 9](https://www.baeldung.com/new-java-9)
- [Java 10](https://www.baeldung.com/java-10-overview)
- [Java 11](https://www.baeldung.com/java-11-new-features)
- [Java 12](https://www.baeldung.com/java-12-new-features)
- [Java 13](https://www.baeldung.com/java-13-new-features)
- [Java 14](https://www.baeldung.com/java-14-new-features)
- [Java 15](https://www.baeldung.com/java-15-new)
- [Java 16](https://www.baeldung.com/java-16-new-features)
- [Java 17](https://www.baeldung.com/java-17-new-features)
- [Java 20](https://www.baeldung.com/java-20-new-features)
- [Java 21](https://www.baeldung.com/java-lts-21-new-features)