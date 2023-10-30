package ru.lakeevda.lesson3.seminar.task1.view;

import ru.lakeevda.lesson3.seminar.task1.model.Employee;
import ru.lakeevda.lesson3.seminar.task1.model.Priority;

import java.util.List;

public class View {

    public static void printConsole(String message) {
        System.out.println(message);
    }

    /**
     * Информирование работника о поступлении нового задания.
     */
    public static void informingEmployee(Employee employee, Priority priority) {
        View.printConsole("Для " + employee + " назначена новая задача приоритета " + priority);
    }

    public static <T> void printConsoleList(List<T> list) {
        list.forEach(System.out::println);
    }

    public static void help() {
        printConsole("HELP - помощь");
        printConsole("EXIT - выход из приложения");
        printConsole("GET A - список назначенных задач");
        printConsole("GET FT - список неназначенных задач");
        printConsole("GET E -  список сотрудников");
        printConsole("GET AE - список назначенных задач по сотруднику");
        printConsole("GET ADE - список сотрудников департамента");
        printConsole("GET CT - список завершенных задач");
        printConsole("CREATE E - новый сотрудник");
        printConsole("CREATE T - новая задача");
        printConsole("TASK M - назначение задач вручную из списка неназначенных задач");
        printConsole("EMP S - вход в подмену сотрудников");
    }

    public static void helpEmployee() {
        printConsole("HELP - помощь");
        printConsole("EXIT - выход из приложения");
        printConsole("EMP ST - взять задачу");
        printConsole("EMP FT - завершить задачу");
        printConsole("EMP HT - отложить задачу");
        printConsole("EMP LT - список задач");

    }
}
