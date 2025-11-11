package model;

public class Customer extends User{
    public Customer() {
        super();
    }

    public Customer(int id, String username, String password, String name, String email, String address) {
        super(id, username, password, name, email, address);
    }
}
