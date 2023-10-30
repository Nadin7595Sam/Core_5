package ru.lakeevda.lesson3.seminar.task1.model;

public enum Priority {

    P1(1),
    P2(2),

    P3(3);

    private final int priority;

    Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
