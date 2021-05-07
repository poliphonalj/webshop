package webshop.DTOs;

public class SumOfItemsForADeliveryDAyDTO {
String name;
long quantity;

    public SumOfItemsForADeliveryDAyDTO(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
