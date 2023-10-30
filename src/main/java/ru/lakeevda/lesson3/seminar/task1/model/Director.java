package ru.lakeevda.lesson3.seminar.task1.model;

import java.time.LocalDate;

public class Director extends Employee {

    String patronymic;

    public Director(String lastName, String firstName, String patronymic, LocalDate birthDate, double salary, Skill skill) {
        super(lastName, firstName, birthDate, salary, skill);
        this.patronymic = patronymic;
    }
}
