package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.AssigmentRepository;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.util.ArrayList;
import java.util.List;


public class TaskPlanner {

    private final SelectionEmployee selectionEmployee;
    public static List<Task> freeTask = new ArrayList<>();


    public TaskPlanner(SelectionEmployee selectionEmployee) {
        this.selectionEmployee = selectionEmployee;
    }

    public void planTask(Task task) {
        Employee employee = selectionEmployee.selectionEmployee(task.getSkill(), task.getPriority());
        if (employee.getSkill() == Skill.NoSKILL) {
            freeTask.add(task);
            return;
        }
        Assigment assigment = new Assigment(employee, task);
        AssigmentRepository.addAssigment(assigment);
        task.setIdEmployee(employee.getId());
        View.informingEmployee(employee, task.getPriority());
    }

    public static List<Task> getFreeTask() {
        return freeTask;
    }

    public void createTask (){
        ScannerService scannerService = new ScannerService();
        String name = scannerService.stringScanner("Наименование задачи");
        Skill skill = scannerService.skillScanner("Skill");
        int length = scannerService.intScanner("Планируемое время выполнения");
        Priority priority = scannerService.priorityScanner("Приоритет");
        Task task = new Task(name,skill,length);
        task.setPriority(priority);
        planTask(task);
    }
    public static void removeFreeTask (Task task){
        freeTask.remove(task);
    }

}
