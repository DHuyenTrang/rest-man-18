package model;

public class Employee extends User {

    private String role;

    public Employee() {
        super();
    }

    public Employee(int id, String username, String password, String name, String email, String address, String role) {
        super(id, username, password, name, email, address);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}