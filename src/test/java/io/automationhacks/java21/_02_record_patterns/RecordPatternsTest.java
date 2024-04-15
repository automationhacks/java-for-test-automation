package io.automationhacks.java21._02_record_patterns;

// https://www.baeldung.com/java-19-record-patterns

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

record GPSPoint(double latitude, double longitude) {}

record Location(String name, GPSPoint gpsPoint) {}

record Wrapper<T>(T type, String description) {}

public class RecordPatternsTest {
    @Test
    public void testRecordPatternDestruction() {
        var location = new Location("Home", new GPSPoint(12.9716, 77.5946));

        // Destructuring of record, here we are extracting location directly into a variable loc
        if (location instanceof Location loc) {
            assertWithMessage("").that(loc.name()).isEqualTo("Home");
        }
    }

    @Test
    public void testNestedRecordPatternDestruction() {
        var location = new Location("Home", new GPSPoint(12.9716, 77.5946));

        // Destructuring of record, here we are extracting location directly into a variable loc
        if (location instanceof Location(String name, GPSPoint(var latitude, var longitude))) {
            assertWithMessage("Name does not match").that(name).isEqualTo("Home");
            assertWithMessage("Latitude does not match").that(latitude).isEqualTo(12.9716);
            assertWithMessage("Longitude does not match").that(longitude).isEqualTo(77.5946);
        }
    }

    @Test
    public void testGenericRecordPatternDestruction() {
        var location = new Location("Home", new GPSPoint(12.9716, 77.5946));
        var wrapper = new Wrapper<>(location, "Description");

        // If we have a generic record, we can destructure it as well
        // This is a nested record pattern matching
        // We are matching the type of the wrapper object and then extracting the name, latitude, longitude and description
        if (wrapper instanceof Wrapper<Location>(Location(var name, GPSPoint(var latitude, var longitude)), String description)) {
            assertWithMessage("Name does not match").that(name).isEqualTo("Home");
            assertWithMessage("Latitude does not match").that(latitude).isEqualTo(12.9716);
            assertWithMessage("Longitude does not match").that(longitude).isEqualTo(77.5946);
            assertWithMessage("Description does not match").that(description).isEqualTo("Description");
        }
    }
}
