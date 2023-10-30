package ru.lakeevda.lesson3.seminar.task1.model;


public class Assigment {
    private final Employee employee;
    private final Task task;


    private final int id;
    private static int count;

    public Assigment(Employee employee, Task task) {
        this.employee = employee;
        this.task = task;
        this.id = count++;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Task getTask() {
        return task;
    }


    @Override
    public String toString() {
        return "Assigment{" +
                "id = " + id +
                ", employee=" + employee + "\n" +
                ", task=" + task + "\n" +
                '}';
    }
}
