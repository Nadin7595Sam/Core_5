package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.Director;
import ru.lakeevda.lesson3.seminar.task1.model.Skill;
import ru.lakeevda.lesson3.seminar.task1.repository.EmployeeRepository;
import ru.lakeevda.lesson3.seminar.task1.services.exeption.CheckingAccessRights;
import ru.lakeevda.lesson3.seminar.task1.view.View;


public class DirectorService {


    private DirectorService(Director director) {
    }

    public static DirectorService factoryDirectorService(Director director) {
        try {
            if (Skill.DIRECTOR == director.getSkill()) {
                return new DirectorService(director);
            } else throw new CheckingAccessRights();
        } catch (CheckingAccessRights e) {
            View.printConsole(e.getMessage());
        }
        return null;
    }

    public void raisingSalaries(double percentageIncrease) {
        EmployeeRepository.getEmployees().stream()
                .filter(x -> x.getSkill() != Skill.DIRECTOR)
                .forEach(x -> x.setSalary(x.getSalary() * (1 + percentageIncrease)));
    }

}
