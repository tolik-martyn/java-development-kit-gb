package hw4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Список сотрудников
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee("89990000001", "Иван", 1));
        employeesList.add(new Employee("89990000002", "Василий", 2));
        employeesList.add(new Employee("89990000003", "Ирина", 3));
        employeesList.add(new Employee("89990000004", "Иван", 4));
        employeesList.add(null);

        // Справочник сотрудников
        EmployeeDirectory employeeDirectory = new EmployeeDirectory(employeesList);

        // Поиск сотрудников по стажу сотрудника
        System.out.println("Сотрудники со стажем работы 3 года: " + employeeDirectory.findEmployeesByExperience(3));

        // Поиск сотрудников по стажу более 3 лет.
        System.out.println("Сотрудники со стажем работы более 3 лет: " + employeeDirectory.findEmployeesByExperienceMore(3));

        // Поиск сотрудников по стажу менее 3 лет.
        System.out.println("Сотрудники со стажем работы менее 3 лет: " + employeeDirectory.findEmployeesByExperienceLess(3));

        // Поиск телефонов по имени сотрудника
        System.out.println("Номера телефонов сотрудников с именем 'Иван': " + employeeDirectory.getPhoneNumbersByName("Иван"));

        // Поиск сотрудника по ID
        int employeeIdToFind = 2;
        Employee foundEmployee = employeeDirectory.findEmployeeById(employeeIdToFind);
        System.out.println("Сотрудник с ID " + employeeIdToFind + ": " + foundEmployee);

        // Добавление нового сотрудника
        Employee newEmployee = new Employee("89990000005", "Андрей", 5);
        employeeDirectory.addEmployee(newEmployee);
        System.out.println("Добавлен сотрудник: " + newEmployee);

        List<Employee> employeesLisToRemove = new ArrayList<>();
        employeesLisToRemove.add(foundEmployee);
        employeesLisToRemove.add(null);
        employeesLisToRemove.add(null);

        // Удаление сотрудника
        for (Employee employee : employeesLisToRemove) {
            if (employeeDirectory.removeEmployee(employee)) {
                System.out.println("Удален сотрудник: " + employee);
            } else {
                System.out.println("Сотрудник " + employee + " не найден или его невозможно удалить");
            }
        }

        // Удаление сотрудника по идентификатору
        int[] employeeIdsToRemove = new int[]{3, 7};

        for (int id : employeeIdsToRemove) {
            if (employeeDirectory.removeEmployeeById(id)) {
                System.out.println("Удален сотрудник с ID: " + id);
            } else {
                System.out.println("Сотрудник с ID " + id + " не найден или его невозможно удалить");
            }
        }
    }
}