package ru.lakeevda.lesson3.seminar.task1.services;

import ru.lakeevda.lesson3.seminar.task1.model.*;
import ru.lakeevda.lesson3.seminar.task1.repository.DepartmentRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {


    private void fileWriter(List<String> dats, String fileName, boolean append) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            for (String s : dats) {
                fileWriter.write(s + "\n");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWriterEmployee(List<Employee> employees) {
        String fileName = "employees.txt";
        StringBuilder employee = new StringBuilder();
        List<String> employeeStringBuilder = new ArrayList<>();
        for (Employee empl : employees) {
            employee.append(" ").append(empl.getLastName()).append(";")
                    .append(empl.getFirstName()).append(";")
                    .append(empl.getBirthDate()).append(";")
                    .append(empl.getSalary()).append(";")
                    .append(empl.getSkill()).append(";")
                    .append(empl.getId()).append(";")
                    .append(empl.getDepartment().getSkill());
            employeeStringBuilder.add(employee.toString());
            employee.delete(0, employee.length());

        }
        fileWriter(employeeStringBuilder, fileName, false);
    }

    public void fileWriterAssigmentAndTask(List<Assigment> assigments, List<Task> freeTask) {
        String fileName = "tasks.txt";
        String fileName1 = "taskComplete.txt";
        List<Task> tasks = new ArrayList<>();
        List<Task> tasksComplete = new ArrayList<>();
        for (Assigment ass : assigments) {
            if (ass.getTask().getStatus() != Status.COMPLETE)
                tasks.add(ass.getTask());
            else tasksComplete.add(ass.getTask());
        }
        fileWriterTask(tasks, fileName, false);
        fileWriterTask(freeTask, fileName, true);
        fileWriterTask(tasksComplete, fileName1, true);
    }

    private void fileWriterTask(List<Task> tasks, String fileName, boolean append) {
        StringBuilder task = new StringBuilder();
        List<String> taskStringList = new ArrayList<>();
        for (Task tas : tasks) {
            task.append(" ").append(tas.getName()).append(";")
                    .append(tas.getCreateDate()).append(";")
                    .append(tas.getFactStartDate()).append(";")
                    .append(tas.getSkill()).append(";")
                    .append(tas.getPriority()).append(";")
                    .append(tas.getLength()).append(";")
                    .append(tas.getId()).append(";")
                    .append(tas.getIdEmployee()).append(";")
                    .append(tas.getStatus()).append(";")
                    .append(tas.getFactEndDate());
            taskStringList.add(task.toString());
            task.delete(0, task.length());
        }
        fileWriter(taskStringList, fileName, append);
    }

    private List<String> fileReader(String fileName) {

        List<String> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.read() != -1) {
                result.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Employee> fileReaderEmployee() {
        Department department;
        List<Employee> employees = new ArrayList<>();
        List<String> tempEmployee = fileReader("employees.txt");
        String[] temp;
        for (String str : tempEmployee) {
            temp = str.split(";");
            String lastname = temp[0];
            String firstName = temp[1];
            String[] tempLocalDate = temp[2].split("-");
            LocalDate birthDay = LocalDate.of(Integer.parseInt(tempLocalDate[0]), Integer.parseInt(tempLocalDate[1])
                    , Integer.parseInt(tempLocalDate[2]));
            double salary = Double.parseDouble(temp[3]);
            Skill skill = Skill.valueOf(temp[4]);
            int id = Integer.parseInt(temp[5]);
            Skill skillDepartment = Skill.valueOf(temp[6]);
            if (isCreateDepartment(skillDepartment)) {
                department = DepartmentRepository.getDepartmentBySkill(skillDepartment);
            } else {
                department = new Department(skillDepartment);
                DepartmentRepository.addDepartment(department);
            }
            Employee employee = new Employee(lastname, firstName, birthDay, salary, skill, id, department);
            employees.add(employee);
        }
        return employees;
    }

    private boolean isCreateDepartment(Skill skill) {
        for (Department department : DepartmentRepository.getDepartmentList()) {
            if (department.getSkill() == skill)
                return true;
        }
        return false;
    }

    public List<Task> fileReaderTask() {
        String fileName = "tasks.txt";
        List<String> stringTask = fileReader(fileName);
        return parseTask(stringTask);
    }

    public List<Task> fileReaderTaskComplete() {
        String fileName = "taskComplete.txt";
        List<String> stringTask = fileReader(fileName);
        return parseTask(stringTask);
    }

    private List<Task> parseTask(List<String> stringTask) {

        List<Task> tasks = new ArrayList<>();
        String[] temp;
        for (String str : stringTask) {
            temp = str.split(";");
            String name = temp[0];
            String[] tempLocalDate = temp[1].split("-");
            LocalDate createDate = LocalDate.of(Integer.parseInt(tempLocalDate[0]), Integer.parseInt(tempLocalDate[1])
                    , Integer.parseInt(tempLocalDate[2]));
            tempLocalDate = temp[2].split("-");
            LocalDate factStartDate = LocalDate.of(Integer.parseInt(tempLocalDate[0]), Integer.parseInt(tempLocalDate[1])
                    , Integer.parseInt(tempLocalDate[2]));
            Skill skill = Skill.valueOf(temp[3]);
            Priority priority = Priority.valueOf(temp[4]);
            int length = Integer.parseInt(temp[5]);
            int id = Integer.parseInt(temp[6]);
            int idEmployee = Integer.parseInt(temp[7]);
            Status status = Status.valueOf(temp[8]);
            tempLocalDate = temp[9].split("-");
            LocalDate factEndDate = LocalDate.of(Integer.parseInt(tempLocalDate[0]), Integer.parseInt(tempLocalDate[1])
                    , Integer.parseInt(tempLocalDate[2]));
            Task task = new Task(name, factStartDate, createDate, skill, priority, length, id,
                    idEmployee, status, factEndDate);
            tasks.add(task);
        }
        return tasks;
    }
}
