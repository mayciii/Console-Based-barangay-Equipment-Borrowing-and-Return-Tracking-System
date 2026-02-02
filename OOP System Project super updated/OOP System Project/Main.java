import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Admin admin = new Admin("admin", "123");
        User user = new User("user", "123");

        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("======================================================================");
            System.out.println("      Barangay Equipment Borrowing and Return Tracking System");
            System.out.println("======================================================================");
            System.out.println();
            System.out.println("Select an option: ");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.println();
            System.out.print("Choose: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    loginMenu(sc, admin, user);
                    break;

                case 2:
                    clearScreen();
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    clearScreen();
                    System.out.println("Invalid choice!");
                    System.out.println();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {}
            }
        }

        sc.close();
    }

    static void loginMenu(Scanner sc, Admin admin, User user) {
        clearScreen();

        System.out.println("======================================================================");
        System.out.println("                             LOGIN MENU");
        System.out.println("======================================================================");
        System.out.println();
        System.out.println("Select account type: ");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println();
        System.out.print("Choose: ");
        int accountType = 0;
        try {
            accountType = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            accountType = -1;
        }
        System.out.println();

        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.println();

        switch (accountType) {
            case 1:
                clearScreen();
                if (admin.login(username, password)) {
                    System.out.println("Admin login successful!");
                    System.out.println();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {}
                    adminMenu(sc, admin);
                } else {
                    System.out.println("Invalid admin credentials!");
                    System.out.println();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {}
                }
                break;

            case 2:
                clearScreen();
                if (user.login(username, password)) {
                    System.out.println("User login successful!");
                    System.out.println();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {}
                    userMenu(sc, user, admin);
                } else {
                    System.out.println("Invalid user credentials!");
                    System.out.println();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {}
                }
                break;

            default:
                clearScreen();
                System.out.println("Invalid account type!");
                System.out.println();
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {}
        }
    }

    static void adminMenu(Scanner sc, Admin admin) {
        int option;
        do {
            clearScreen();
            System.out.println("======================================================================");
            System.out.println("                            ADMIN MENU");
            System.out.println("======================================================================");
            System.out.println();
            System.out.println("1. Add Equipment");
            System.out.println("2. View Equipment");
            System.out.println("3. Update Equipment");
            System.out.println("4. Delete Equipment");
            System.out.println("5. View Transaction Logs");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                option = -1;
            }
            clearScreen();

            switch (option) {
                case 1:
                    System.out.println("======================================================================");
                    System.out.println("                           ADD EQUIPMENT");
                    System.out.println("======================================================================");
                    System.out.println();
                    System.out.print("Enter equipment name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = 0;
                    try {
                        qty = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        qty = 0;
                    }
                    System.out.println();
                    admin.addEquipment(name, qty);
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 2:
                    admin.viewEquipment();
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("======================================================================");
                    System.out.println("                         UPDATE EQUIPMENT");
                    System.out.println("======================================================================");
                    System.out.println();
                    System.out.print("Enter equipment name to update: ");
                    String upname = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQty = 0;
                    try {
                        newQty = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        newQty = 0;
                    }
                    System.out.println();
                    admin.updateEquipment(upname, newQty);
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("======================================================================");
                    System.out.println("                          DELETE EQUIPMENT");
                    System.out.println("======================================================================");
                    System.out.println();
                    System.out.print("Enter equipment name to delete: ");
                    String delname = sc.nextLine();
                    System.out.println();
                    admin.deleteEquipment(delname);
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 5:
                    admin.viewLogs();
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 6:
                    clearScreen();
                    System.out.println("Logging out...");
                    System.out.println();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {}
                    break;
                default:
                    System.out.println("Invalid choice!");
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
            }
        } while (option != 6);
    }

    static void userMenu(Scanner sc, User user, Admin admin) {
        int option;
        do {
            clearScreen();
            System.out.println("======================================================================");
            System.out.println("                             USER MENU");
            System.out.println("======================================================================");
            System.out.println();
            System.out.println("1. View Available Equipment");
            System.out.println("2. Borrow Equipment");
            System.out.println("3. Return Equipment");
            System.out.println("4. Logout");
            System.out.println();
            System.out.print("Choose: ");
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                option = -1;
            }
            clearScreen();

            switch (option) {
                case 1:
                    user.viewAvailableEquipment(admin.getEquipmentList());
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 2:
                    user.viewAvailableEquipment(admin.getEquipmentList());
                    System.out.println();
                    System.out.print("Enter equipment name to borrow: ");
                    String bname = sc.nextLine();
                    System.out.println();
                    user.borrowItem(sc, admin.getEquipmentList(), bname, admin);
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("======================================================================");
                    System.out.println("                          RETURN EQUIPMENT");
                    System.out.println("======================================================================");
                    System.out.println();
                    System.out.print("Enter equipment name to return: ");
                    String rname = sc.nextLine();
                    System.out.println();
                    user.returnItem(admin.getEquipmentList(), rname, admin);
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;
                case 4:
                    clearScreen();
                    System.out.println("Logging out...");
                    System.out.println();
                    try {
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {}
                    break;
                default:
                    System.out.println("Invalid choice!");
                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
            }
        } while (option != 4);
    }

    // Helper method to clear screen
    static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }


}


