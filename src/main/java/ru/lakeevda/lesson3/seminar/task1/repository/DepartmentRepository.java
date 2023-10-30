package ru.lakeevda.lesson3.seminar.task1.repository;

import ru.lakeevda.lesson3.seminar.task1.model.Department;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {
    static private final List<Department> departmentList = new ArrayList<>();

    static public List<Department> getDepartmentList() {
        return departmentList;
    }

    static public void addDepartment(Department department) {
        departmentList.add(department);
    }

    public static Department getDepartmentBySkill(Skill skill) {
        for (Department department : departmentList) {
            if (department.getSkill() == skill)
                return department;
        }
        return new Department(Skill.NoSKILL);
    }
}
