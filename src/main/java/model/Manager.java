package model;

public class Manager extends Employee {

    public Manager() {
        super();
        setRole("Manager");
    }

    public Manager(int id, String username, String password, String name, String email, String address) {
        super(id, username, password, name, email, address, "Manager");
    }
}