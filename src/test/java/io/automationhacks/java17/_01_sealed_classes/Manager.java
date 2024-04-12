package io.automationhacks.java17._01_sealed_classes;

public final class Manager extends Person {
    private final int supervisorId;
    private final String department;

    public Manager(int supervisorId, String department) {
        this.supervisorId = supervisorId;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public int getSupervisorId() {
        return supervisorId;
    }
}
