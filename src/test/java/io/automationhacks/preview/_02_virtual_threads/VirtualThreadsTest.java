package io.automationhacks.preview._02_virtual_threads;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

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
