package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.util.List;

public class StartFinishService {


    FileService fileService;

    public StartFinishService() {
        this.fileService = new FileService();
    }

    public void init() {

        int maxIdEmployee = -1;
        int maxIdTask = -2;

        EmployeeRepository.setEmployees(fileService.fileReaderEmployee());

        for (Employee employee : EmployeeRepository.getEmployees()) {
            if (employee.getId() > maxIdEmployee)
                maxIdEmployee = employee.getId();
            Department department = employee.getDepartment();
            department.addEmployee(employee);
        }
        List<Task> tasks = fileService.fileReaderTask();
        for (Task task : tasks) {
            int idEmployee = task.getIdEmployee();
            int idTask = task.getId();
            if (idTask > maxIdTask)
                maxIdTask = idTask;
            if (idEmployee == -1) {
                TaskPlanner.freeTask.add(task);
            } else {
                Employee employee = EmployeeRepository.getEmployeeById(idEmployee);
                Assigment assigment = new Assigment(employee, task);
                AssigmentRepository.addAssigment(assigment);
            }
            Task.initCount(maxIdTask);
            Employee.initCount(maxIdEmployee);

        }
    }

    public void finish() {
        fileService.fileWriterAssigmentAndTask(AssigmentRepository.getAssigmentList(), TaskPlanner.getFreeTask());
        fileService.fileWriterEmployee(EmployeeRepository.getEmployees());
        View.printConsole("EXIT");

    }
}

