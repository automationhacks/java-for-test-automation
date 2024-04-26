package io.automationhacks.java8._01_interface_default_static_methods;

public interface Vehicle {
    // static cannot be overridden in the implementing class
    // it is only available through and inside an interface
    static String producer() {
        return "Toyota";
    }

    default String getOverview() {
        return "SUV from %s".formatted(producer());
    }
}
