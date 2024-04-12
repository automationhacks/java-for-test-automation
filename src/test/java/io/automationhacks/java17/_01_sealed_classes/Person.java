package io.automationhacks.java17._01_sealed_classes;

// Sealed classes provide fine grained control over inheritance.
// Sealed classes can be abstract or non-abstract.
// Sealed classes can have subclasses that are sealed, non-sealed or final.
// Introduce new keywords: sealed, non-sealed, permits
public sealed class Person permits Employee, Manager {}
