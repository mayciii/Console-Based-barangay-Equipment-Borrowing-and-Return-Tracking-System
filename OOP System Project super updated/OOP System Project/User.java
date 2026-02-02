import java.util.ArrayList;
import java.util.Scanner;

public class User extends Borrower {
    private ArrayList<Equipment> borrowedItems = new ArrayList<>();

    public User(String username, String password) {
        super(username, password);
    }

    public void viewAvailableEquipment(ArrayList<Equipment> list) {
        System.out.println("======================================================================");
        System.out.println("                        AVAILABLE EQUIPMENT");
        System.out.println("======================================================================");
        System.out.println();
        boolean hasAvailable = false;
        for (Equipment eq : list) {
            if (eq.getQuantity() > 0) {
                System.out.println(String.format("%-20s  (Qty: %d)", eq.getName(), eq.getQuantity()));
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("No equipment available at the moment.");
        }
    }

    
    @Override
    public void borrowItem(Scanner sc, ArrayList<Equipment> list, String name, Admin admin) {
        for (Equipment eq : list) {
            if (eq.getName().equalsIgnoreCase(name) && eq.getQuantity() > 0) {

                System.out.print("Enter your full name: ");
                String borrowerName = sc.nextLine();
                System.out.print("Enter your contact number: ");
                String borrowerContact = sc.nextLine();

                eq.setQuantity(eq.getQuantity() - 1);
                borrowedItems.add(new Equipment(eq.getName(), 1)); // add a copy with qty 1 to borrowed list

                admin.addLog(this.getUsername(), eq.getName(), "BORROW",
                             borrowerName, borrowerContact);

                System.out.println();
                System.out.println("You borrowed: " + eq.getName());
                return;
            }
        }
        System.out.println();
        System.out.println("Equipment not available!");
    }

    @Override
    public void returnItem(ArrayList<Equipment> list, String name, Admin admin) {
        Equipment returnItem = null;
        for (Equipment eq : borrowedItems) {
            if (eq.getName().equalsIgnoreCase(name)) {
                returnItem = eq;
                break;
            }
        }
        if (returnItem != null) {
            for (Equipment eq : list) {
                if (eq.getName().equalsIgnoreCase(name)) {
                    eq.setQuantity(eq.getQuantity() + 1);
                    borrowedItems.remove(returnItem);
                    // Log the transaction. Borrower info not collected here, so use "-" placeholders.
                    admin.addLog(this.getUsername(), eq.getName(), "RETURN", "-", "-");
                    System.out.println();
                    System.out.println("Returned successfully!");
                    return;
                }
            }
        } else {
            System.out.println();
            System.out.println("You didn't borrow this item.");
        }
    }
}
