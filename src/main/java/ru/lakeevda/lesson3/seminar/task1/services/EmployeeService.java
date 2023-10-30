package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {

    public List<Assigment> getAssigmentsByEmployee(Employee employee) {
        return AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    }

    public List<Assigment> getAssigmentsByIdEmployee() {
        ScannerService scannerService = new ScannerService();
        Employee employee = EmployeeRepository.getEmployeeById(scannerService.intScanner("Введите id сотрудника"));
        if (employee.getSkill() == Skill.NoSKILL)
            System.out.println("Неккоректное значение");
        return getAssigmentsByEmployee(employee);
    }

    /**
     * Полностью поменял логику старта задач. При нажатии сотрудником кнопки "Взять задачу"
     * сотруднику автоматически в работу выдается задача с наивысшим приоритетом из спмска назначенных
     * ему planTask - ом
     */

    public void startTaskByEmployee(Employee employee) {
        if (!checkingEmployeeHasCompletedTasks(employee).isEmpty()) {
            View.printConsole("Необходимо завершить/отложить текущее задание");
            return;
        }
        List<Assigment> assigmentsSort = getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getStatus() != Status.COMPLETE)
                .sorted(Comparator.comparingInt(x -> x.getTask().getPriority().getPriority()))
                .toList();
        if (assigmentsSort.isEmpty()) {
            View.printConsole("Спмсок назначенных задач пуст");
            return;
        }
        employee.setWorking(true);
        Assigment assigment = assigmentsSort.get(0);
        assigment.getTask().setFactStartDate(LocalDate.now());
        assigment.getTask().setStatus(Status.IN_PROGRESS);

    }

    public List<Assigment> checkingEmployeeHasCompletedTasks(Employee employee) {
        return getAssigmentsByEmployee(employee).stream()
                .filter(x -> x.getTask().getStatus() == Status.IN_PROGRESS)
                .toList();
    }

    /**
     * Автоматически завершается задача находящаяся в работе
     */
    public void finishTaskByEmployee(Employee employee) {
        List<Assigment> assigmentsSort = checkingEmployeeHasCompletedTasks(employee);
        if (assigmentsSort.isEmpty()) {
            View.printConsole("Нет выполняемых задач");
            return;
        }
        Assigment assigment = assigmentsSort.get(0);
        employee.setWorking(false);
        assigment.getTask().setFactEndDate(LocalDate.now());
        assigment.getTask().setStatus(Status.COMPLETE);
    }


    public void onHoldCurrentTask(Employee employee) {
        for (Assigment progressTask : checkingEmployeeHasCompletedTasks(employee))
            progressTask.getTask().setStatus(Status.OnHOLD);
    }
}
