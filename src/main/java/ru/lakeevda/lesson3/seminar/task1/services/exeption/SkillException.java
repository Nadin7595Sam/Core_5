package ru.lakeevda.lesson3.seminar.task1.services.exeption;

public class SkillException extends RuntimeException{

    public SkillException (){
        super("Неверная квалификация сотрудника");
    }
}
