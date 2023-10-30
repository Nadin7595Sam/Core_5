package ru.lakeevda.lesson3.seminar.task1.model;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private final int id;
    private final String name;
    private final LocalDate createDate;
    private LocalDate factStartDate = LocalDate.of(1000, 1, 1);
    private LocalDate factEndDate = LocalDate.of(1000, 1, 1);
    private Priority priority;
    private Skill skill;
    private final int length;
    private static int count;
    private int idEmployee = -1;
    private Status status;

    public static void initCount(int maxIdTask) {
        count = maxIdTask;
    }

    public Task(String name, Skill skill, int length) {
        this.name = name;
        this.createDate = LocalDate.now();
        this.skill = skill;
        this.priority = Priority.P2;
        this.length = length;
        this.id = ++count;
        this.status = Status.NEW;
    }

    public Task(String name, LocalDate factStartDate, LocalDate createDate, Skill skill, Priority priority,
                int length, int id, int idEmployee, Status status, LocalDate factEndDate) {
        this.name = name;
        this.createDate = createDate;
        this.factStartDate = factStartDate;
        this.skill = skill;
        this.priority = priority;
        this.length = length;
        this.id = id;
        this.status = status;
        this.idEmployee = idEmployee;
        this.factEndDate = factEndDate;


    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getFactStartDate() {
        return factStartDate;
    }

    public void setFactStartDate(LocalDate factStartDate) {
        this.factStartDate = factStartDate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLength() {
        return length;
    }

    public int getId() {
        return id;
    }

    public void setFactEndDate(LocalDate factEndDate) {
        this.factEndDate = factEndDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id = " + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", factStartDate=" + factStartDate +
                ", factEndDate=" + factEndDate +
                ", priority=" + priority +
                ", skill=" + skill +
                ", status=" + status +
                ", idEmployee=" + idEmployee +
                '}';
    }

    public LocalDate getFactEndDate() {
        return factEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
