package main.entity;

public class Staff {
    private final int id;
    private final String firstName;
    private final String lastName;

    public Staff(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
