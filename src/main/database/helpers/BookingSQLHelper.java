package main.database.helpers;

public class BookingSQLHelper extends SQLHelper {

    @Override
    public String generate(Object o, String direction) {
        switch (direction) {
            case "UPDATE":
                System.out.println("Xd");
            default:
                break;
        }

        return "";
    }

}
