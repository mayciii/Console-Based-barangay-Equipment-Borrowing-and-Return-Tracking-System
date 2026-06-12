import java.util.ArrayList;
import java.util.Scanner;

public abstract class Borrower extends Account {
    public Borrower(String username, String password) {
        super(username, password);
    }

    
    public abstract void borrowItem(Scanner sc, ArrayList<Equipment> list, String name, Admin admin);
    public abstract void returnItem(ArrayList<Equipment> list, String name, Admin admin);
}

