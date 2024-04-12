package io.automationhacks.java14._03_records;

// Records are immutable data transfer objects (DTO) with all args constructor and getters
// Records do have some restrictions. they are always final, they cannot be
// declared abstract, and they can’t use native methods.
public record UserCredentials(String userName, String password) {}
