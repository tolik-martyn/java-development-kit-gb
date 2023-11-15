package hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий справочник сотрудников.
 */
public class EmployeeDirectory {
    private final List<Employee> employees;

    /**
     * Конструктор класса.
     *
     * @param employees Список сотрудников
     */
    public EmployeeDirectory(List<Employee> employees) {
        this.employees = (employees != null) ? employees : new ArrayList<>();
    }

    /**
     * Метод для поиска сотрудников по стажу.
     *
     * @param experience Стаж, по которому осуществляется поиск
     * @return Список сотрудников с указанным опытом работы
     */
    public List<Employee> findEmployeesByExperience(int experience) {
        return employees.stream()
                .filter(employee -> employee != null && employee.getExperience() == experience)
                .collect(Collectors.toList());
    }

    public List<Employee> findEmployeesByExperienceMore(int experience) {
        return employees.stream()
                .filter(employee -> employee != null && employee.getExperience() > experience)
                .collect(Collectors.toList());
    }

    public List<Employee> findEmployeesByExperienceLess(int experience) {
        return employees.stream()
                .filter(employee -> employee != null && employee.getExperience() < experience)
                .collect(Collectors.toList());
    }

    /**
     * Метод получения списка номеров телефонов по имени сотрудника.
     *
     * @param name Имя сотрудника
     * @return Список номеров телефонов сотрудников с указанным именем
     */
    public List<String> getPhoneNumbersByName(String name) {
        return employees.stream()
                .filter(employee -> employee != null && employee.getName().equals(name))
                .map(Employee::getPhoneNumber)
                .collect(Collectors.toList());
    }

    /**
     * Метод поиска сотрудника по идентификатору.
     *
     * @param employeeId Идентификатор сотрудника
     * @return Сотрудник с указанным идентификатором или null, если сотрудник не найден
     */
    public Employee findEmployeeById(int employeeId) {
        return employees.stream()
                .filter(employee -> employee != null && employee.getEmployeeId() == employeeId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод добавления сотрудника в справочник.
     *
     * @param employee Сотрудник, которого нужно добавить в справочник
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Метод удаления сотрудника.
     *
     * @param employee Сотрудник, которого нужно удалить из справочника
     * @return true, если сотрудник удален; false, если сотрудника не существует или не удалось удалить
     */
    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }

    /**
     * Метод удаления сотрудника по идентификатору.
     *
     * @param employeeId Идентификатор сотрудника, которого нужно удалить из справочника
     */
    public boolean removeEmployeeById(int employeeId) {
        return employees.removeIf(employee -> employee != null && employee.getEmployeeId() == employeeId);
    }
}