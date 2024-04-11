package io.automationhacks.java11._03_file_methods;

import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertWithMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMethods {
    @Test
    public void testFileMethods() throws IOException {
        var tempDir = Files.createTempDirectory("demo");
        Path filePath =
                Files.writeString(Files.createTempFile(tempDir, "sample", ".txt"), "Sample text");
        String fileContent = Files.readString(filePath);
        assertWithMessage("File content does not match").that(fileContent).isEqualTo("Sample text");

        Files.deleteIfExists(filePath);
    }
}
