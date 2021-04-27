package webshop.Model.Barion;

public class Item {

    private String Name;
    private int Quantity;
    private String Unit;
    private int UnitPrice;
    private int ItemTotal;

    public Item() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getItemTotal() {
        return ItemTotal;
    }

    public void setItemTotal(int itemTotal) {
        ItemTotal = itemTotal;
    }
}
