public class Equipment {
    private String name;
    private int quantity;

    
    public Equipment(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   @Override
   public String toString() {
       return String.format("%-20s  (Qty: %d)", name, quantity);
   }
}


/*Equipment class stores name and quantity
also has getter and setter for quantity*/