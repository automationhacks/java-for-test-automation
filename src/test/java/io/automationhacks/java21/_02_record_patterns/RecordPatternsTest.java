package io.automationhacks.java21._02_record_patterns;

// https://www.baeldung.com/java-19-record-patterns

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

record GPSPoint(double latitude, double longitude) {}

record Location(String name, GPSPoint gpsPoint) {}

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
        if (location instanceof Location(String name, GPSPoint gpsPoint)) {
            assertWithMessage("Name does not match").that(name).isEqualTo("Home");
            assertWithMessage("Latitude does not match").that(gpsPoint.latitude()).isEqualTo(12.9716);
            assertWithMessage("Longitude does not match").that(gpsPoint.longitude()).isEqualTo(77.5946);
        }
    }
}
