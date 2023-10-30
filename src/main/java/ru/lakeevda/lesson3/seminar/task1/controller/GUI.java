package ru.lakeevda.lesson3.seminar.task1.controller;

import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.*;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.util.List;


public class GUI {

    private final ScannerService scannerService;
    private final EmployeeService employeeService;
    private final DepartmentHRService departmentHRService;
    private final TaskPlanner taskPlanner;
    private final FileService fileService;


    public GUI() {
        this.scannerService = new ScannerService();
        this.employeeService = new EmployeeService();
        this.departmentHRService = new DepartmentHRService();
        this.taskPlanner = new TaskPlanner(new SelectionEmployee(departmentHRService, employeeService));
        this.fileService = new FileService();
    }

    public void run() {
        boolean isExit = true;
        while (isExit) {
            String command = scannerService.stringScanner("Введите команду или воспользуйтесь командой HELP ").toUpperCase();
            switch (command) {
                case ("EXIT") -> {
                    isExit = false;
                    scannerService.close();
                }
                case ("HELP") -> View.help();
                case ("GET A") -> AssigmentRepository.getAssigmentList().forEach(System.out::println);
                case ("GET FT") -> TaskPlanner.getFreeTask().forEach(System.out::println);
                case ("GET E") -> EmployeeRepository.getEmployees().forEach(System.out::println);
                case ("GET AE") -> employeeService.getAssigmentsByIdEmployee().forEach(System.out::println);
                case ("GET ADE") -> {
                    List<Employee> employees = departmentHRService.getEmployeesByDepartment();
                    if (employees == null)
                        View.printConsole("No employee");
                    else employees.forEach(System.out::println);
                }
                case ("CREATE E") -> departmentHRService.createEmployee();
                case ("CREATE T") -> taskPlanner.createTask();
                case ("GET CT") -> fileService.fileReaderTaskComplete().forEach(System.out::println);
                case ("TASK M") -> ManagerService.assigmentTaskManual();
                case ("EMP S") -> employeeGUI();
                default -> {
                    View.printConsole("Команда не найдена");
                    View.help();
                }
            }
        }
    }

    public void employeeGUI() {
        boolean isExit = true;
        int idEmployee = scannerService.intScanner("Введите id пользователя");
        Employee employee = EmployeeRepository.getEmployeeById(idEmployee);
        if (employee.getSkill() == Skill.NoSKILL) {
            View.printConsole("No employee");
            return;
        }
        while (isExit) {
            String command = scannerService.stringScanner("Введите команду или воспользуйтесь командой HELP ").toUpperCase();
            switch (command) {
                case ("EXIT") -> isExit = false;
                case ("HELP") -> View.helpEmployee();
                case ("EMP ST") -> employeeService.startTaskByEmployee(employee);
                case ("EMP FT") -> employeeService.finishTaskByEmployee(employee);
                case ("EMP HT") -> employeeService.onHoldCurrentTask(employee);
                case ("EMP LT") -> employeeService.getAssigmentsByEmployee(employee).forEach(System.out::println);
                default -> {
                    View.printConsole("Команда не найдена");
                    View.helpEmployee();
                }
            }
        }
    }
}
