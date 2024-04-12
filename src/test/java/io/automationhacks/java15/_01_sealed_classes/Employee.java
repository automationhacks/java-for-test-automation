package io.automationhacks.java15._01_sealed_classes;

public non-sealed class Employee extends Person {
    private final int id;
    private final String department;

    public Employee(int id, String department) {
        this.id = id;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }
}
