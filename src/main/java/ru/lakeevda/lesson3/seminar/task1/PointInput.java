package ru.lakeevda.lesson3.seminar.task1;

import ru.lakeevda.lesson3.seminar.task1.services.StartFinishService;
import ru.lakeevda.lesson3.seminar.task1.controller.GUI;

public class PointInput {

    public static void main(String[] args) {
        StartFinishService startFinishService = new StartFinishService();
        startFinishService.init();
        GUI gui = new GUI();
        gui.run();
        startFinishService.finish();
    }
}
