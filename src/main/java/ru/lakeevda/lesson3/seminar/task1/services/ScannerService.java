package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Priority;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.view.View;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;


public class ScannerService {


    Scanner scanner;

    public ScannerService() {
        this.scanner = new Scanner(System.in);
    }

    public int intScanner(String message) {
        View.printConsole(message);
        String str = scanner.nextLine();
        return checkInt(str, message);
    }

    private int checkInt(String str, String message) {
        int res;
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            View.printConsole("Неккоректное значение. Повторите ввод");
            res = intScanner(message);
        }
        return res;
    }

    public String stringScanner(String message) {
        View.printConsole(message);
        return scanner.nextLine();
    }

    public LocalDate dateScanner(String message) {
        View.printConsole(message);
        LocalDate date;
        int day = intScanner("Введите день");
        int month = intScanner("Введите месяц");
        int year = intScanner("Введите год");
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            View.printConsole("Неккоректное значение. Повторите ввод");
            date = dateScanner(message);
        }
        return date;
    }

    public Skill skillScanner(String message) {
        return checkSkill(stringScanner(message), message);
    }

    private Skill checkSkill(String skill, String message) {
        try {
            return Skill.valueOf(skill.toUpperCase());
        } catch (IllegalArgumentException e) {
            View.printConsole("Неккоректное значение. Повторите ввод");
        }
        return skillScanner(message);
    }

    public Priority priorityScanner(String message) {
        return checkPriority(stringScanner(message), message);
    }

    private Priority checkPriority(String priority, String message) {
        try {
            return Priority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            View.printConsole("Неккоректное значение. Повторите ввод");
        }
        return priorityScanner(message);
    }

    public void close() {
        scanner.close();
    }

}
