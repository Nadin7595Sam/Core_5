package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;

import java.util.List;


public class SelectionEmployee {


    DepartmentHRService departmentHRService;
    EmployeeService employeeService;

    public SelectionEmployee(DepartmentHRService departmentHRService, EmployeeService employeeService) {
        this.departmentHRService = departmentHRService;
        this.employeeService = employeeService;
    }


    public Employee selectionEmployee(Skill skill, Priority priority) {
        // При большом количестве заданий необходимо считать общее время заданий и раздавать задания с учетом общей занятости работника
        // Так же при малом количестве заданий все задания будут сыпаться на первого работника. Что тоже не комильфо
        List<Employee> sortEmployees = EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() == skill)
                .toList();

        for (Employee employee : sortEmployees) {
            if (!employee.isWorking())
                return employee;
        }
        for (Employee employee : sortEmployees) {
            if (priority == Priority.P1) {
                if (checkEmployeeTaskPriorityP1(employee))
                    return employee;
            } else if (checkingTotalTaskCompletionTime(employee))
                return employee;
        }
        ManagerService.informingManager("ВНИМАНИЕ МЕНЕДЖЕРОВ! "
                + " нет свободных работников для выполнения задания");
        return new Employee(Skill.NoSKILL);

    }

    public boolean checkingTotalTaskCompletionTime(Employee employee) {
        //    Здесь должна быть логика отслеживающая время до конца рабочего дня
        int maximumTotalTaskCompletionTime = 61;

        int totalTaskCompletionTime = employeeService.getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getStatus() != Status.COMPLETE)
                .mapToInt(x -> x.getTask().getLength())
                .sum();
        return totalTaskCompletionTime < maximumTotalTaskCompletionTime;
    }

    public boolean checkEmployeeTaskPriorityP1(Employee employee) {
        List<Assigment> assigmentFilterPriority = employeeService.getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getPriority() == Priority.P1)
                .toList();
        return assigmentFilterPriority.isEmpty();

    }
}
