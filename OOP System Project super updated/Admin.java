import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin extends Account {
    private ArrayList<Equipment> equipmentList;
    private ArrayList<TransactionLog> logs;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Admin(String username, String password) {
        super(username, password);
        equipmentList = new ArrayList<>();
        logs = new ArrayList<>();
    }


    public void addEquipment(String name, int qty) {
        for (Equipment eq : equipmentList) {
            if (eq.getName().equalsIgnoreCase(name)) {
                eq.setQuantity(eq.getQuantity() + qty);
                System.out.println("Equipment quantity updated successfully!");
                return;
            }
        }
        equipmentList.add(new Equipment(name, qty));
        System.out.println("Equipment added successfully!");
    }

    
    public void addEquipment(String name) {
        addEquipment(name, 1);
    }


    public void viewEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment available.");
        } else {
            System.out.println("======================================================================");
            System.out.println("                      AVAILABLE EQUIPMENT");
            System.out.println("======================================================================");
            System.out.println();
            for (Equipment eq : equipmentList) {
                System.out.println(String.format("%-20s  (Qty: %d)", eq.getName(), eq.getQuantity()));
            }
        }
    }

    
    public void updateEquipment(String name, int newQty) {
        for (Equipment eq : equipmentList) {
            if (eq.getName().equalsIgnoreCase(name)) {
                eq.setQuantity(newQty);
                System.out.println("Equipment updated successfully!");
                return;
            }
        }
        System.out.println("Equipment not found!");
    }

    
    public void deleteEquipment(String name) {
        Equipment found = null;
        for (Equipment eq : equipmentList) {
            if (eq.getName().equalsIgnoreCase(name)) {
                found = eq;
                break;
            }
        }
        if (found != null) {
            equipmentList.remove(found);
            System.out.println("Equipment deleted successfully!");
        } else {
            System.out.println("Equipment not found!");
        }
    }

    public ArrayList<Equipment> getEquipmentList() {
        return equipmentList;
    }

    
    public void addLog(String username, String equipmentName, String action,
                       String borrowerName, String borrowerContact) {
        logs.add(new TransactionLog(username, equipmentName, action, borrowerName, borrowerContact, new Date()));
    }

    public void viewLogs() {
        if (logs.isEmpty()) {
            System.out.println("No transaction logs available.");
        } else {
            System.out.println("======================================================================");
            System.out.println("                      TRANSACTION LOGS");
            System.out.println("======================================================================");
            System.out.println();
            for (TransactionLog log : logs) {
                String logEntry = String.format(
                    "%s - %s: %s [%s] | Borrower: %s | Contact: %s",
                    log.getTimestamp(),
                    log.getAction().toUpperCase(),
                    log.getEquipmentName(),
                    log.getUsername(),
                    log.getBorrowerName(),
                    log.getBorrowerContact()
                );
                System.out.println(logEntry);
            }
        }
    }

    public ArrayList<TransactionLog> getLogs() {
        return logs;
    }



    
    public static class TransactionLog {
        private String username;
        private String equipmentName;
        private String action; 
        private Date timestamp;
        private String borrowerName;
        private String borrowerContact;

        public TransactionLog(String username, String equipmentName, String action,
                              String borrowerName, String borrowerContact, Date timestamp) {
            this.username = username;
            this.equipmentName = equipmentName;
            this.action = action;
            this.borrowerName = borrowerName;
            this.borrowerContact = borrowerContact;
            this.timestamp = timestamp;
        }

        public String getUsername() { return username; }
        public String getEquipmentName() { return equipmentName; }
        public String getAction() { return action; }
        public String getBorrowerName() { return borrowerName; }
        public String getBorrowerContact() { return borrowerContact; }
        public String getTimestamp() {
            return dateFormat.format(timestamp);
        }
    }
}
