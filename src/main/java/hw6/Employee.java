package hw6;

/**
 * Класс, представляющий сотрудника.
 */
public class Employee {

    private static int nextEmployeeId = 1;
    private final int employeeId;
    private final String phoneNumber;
    private final String name;
    private final int experience;

    public Employee(String phoneNumber, String name, int experience) {
        this.employeeId = nextEmployeeId++;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "{" +
                "Id=" + employeeId +
                ", phone='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}