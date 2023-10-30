package ru.lakeevda.lesson3.seminar.task1.services.exeption;

public class CheckingAccessRights extends RuntimeException{
    public CheckingAccessRights() {
        super("У сотрудника недостаточно прав доступа");
    }
}
