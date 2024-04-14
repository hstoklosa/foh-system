package main.entity;

public class Staff {
    private final String name;
    private final String surname;

    public Staff(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
