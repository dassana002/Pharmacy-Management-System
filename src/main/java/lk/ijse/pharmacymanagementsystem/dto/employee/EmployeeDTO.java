package lk.ijse.pharmacymanagementsystem.dto.employee;

public class EmployeeDTO {
    private int employeeId;
    private String userName;
    private String name;
    private String password;
    private String role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int employeeId, String userName, String name, String password, String role) {
        this.employeeId = employeeId;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
