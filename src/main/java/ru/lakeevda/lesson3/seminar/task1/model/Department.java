package ru.lakeevda.lesson3.seminar.task1.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final Skill skill;
    public List<Employee> departmentEmployee;
    private Employee manager;

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getDepartmentEmployee() {
        return departmentEmployee;
    }


    public void addEmployee(Employee employee) {
        if (departmentEmployee == null) departmentEmployee = new ArrayList<>();
        departmentEmployee.add(employee);
    }

    @Override
    public String toString() {
        return "Department " + "{" +
                "skill='" + skill + '\'' +
                '}';
    }

}
