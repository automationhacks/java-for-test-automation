package io.automationhacks.java8._03_optional;

import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

import java.util.Optional;

class User {
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Address address;
}

class Address {
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    private String street;
}

public class OptionalTest {
    @Test
    public void optionalTest() {
        Optional<String> optional = Optional.empty();
        assertWithMessage("How did an empty optional become present 🤔?")
                .that(optional.isPresent())
                .isFalse();

        String name = "automation hacks";
        Optional<String> optionalName = Optional.of(name);
        assertWithMessage("How did a present optional become empty 🤔?")
                .that(optional.isPresent())
                .isTrue();
    }

    @Test
    public void nestedOptionalWithNullCheckTest() {
        Address address = new Address();
        address.setStreet("123 Main");

        User user = new User();
        user.setAddress(address);

        Address nullAddress = new Address();
        nullAddress.setStreet(null);

        User userWithNullStreet = new User();
        userWithNullStreet.setAddress(nullAddress);

        // Before Java 8 with if else checks
        String street = getStreetString(user);
        assertWithMessage("Street is unexpected").that(street).isEqualTo("123 Main");

        String nullStreet = getStreetString(userWithNullStreet);
        assertWithMessage("Street returned for null").that(nullStreet).isEqualTo("not specified");

        // After Java 8
        Optional<User> optionalUser = Optional.ofNullable(user);
        String result = getNullSafeStreet(optionalUser);
        assertWithMessage("Street is unexpected").that(result).isEqualTo("123 Main");

        Optional<User> anotherOptionalUser = Optional.ofNullable(userWithNullStreet);
        result = getNullSafeStreet(anotherOptionalUser);
        assertWithMessage("Street is unexpected").that(result).isEqualTo("not specified");
    }

    // How we would implement a null safe method prior to Optional
    private String getStreetString(User user) {
        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                String street = address.getStreet();
                if (street != null) {
                    return street;
                }
            }
        }

        return "not specified";
    }

    // After Java 8, and using optional this code can be made more concise
    private static String getNullSafeStreet(Optional<User> optionalUser) {
        return optionalUser.map(User::getAddress).map(Address::getStreet).orElse("not specified");
    }
}
