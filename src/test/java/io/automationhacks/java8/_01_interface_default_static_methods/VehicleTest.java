package io.automationhacks.java8._01_interface_default_static_methods;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

class TataNexon implements Vehicle {
    @Override
    public String getOverview() {
        return "Compact SUV Made in India by Tata Motors";
    }
}

class ToyotaEtios implements Vehicle {}

public class VehicleTest {
    @Test
    public void testInterfaceStaticInVehicle() {
        // static methods can be called directly on the interface
        String producer = Vehicle.producer();
        assertWithMessage("Invalid vehicle manufacturer").that(producer).isEqualTo("Toyota");
    }

    @Test
    public void testInterfaceDefaultInVehicle() {
        var toyotaEtios = new ToyotaEtios();
        String overview = toyotaEtios.getOverview();
        assertWithMessage("Invalid vehicle overview").that(overview).isEqualTo("SUV from Toyota");
    }

    @Test
    public void testInterfaceDefaultOverrideInVehicle() {
        var tataNexon = new TataNexon();
        String overview = tataNexon.getOverview();
        assertWithMessage("Invalid vehicle overview")
                .that(overview)
                .isEqualTo("Compact SUV Made in India by Tata Motors");
    }
}
