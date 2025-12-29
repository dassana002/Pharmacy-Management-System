package lk.ijse.pharmacymanagementsystem.dto.item;

public class AddItemTM {
    private int itemId;
    private String description;
    private double unitCost;
    private double sellPrice;
    private int qty;
    private double subTotal;

    public AddItemTM() {
    }

    public AddItemTM(int itemId, String description, double unitCost, double sellPrice, int qty, double subTotal) {
        this.itemId = itemId;
        this.description = description;
        this.unitCost = unitCost;
        this.sellPrice = sellPrice;
        this.qty = qty;
        this.subTotal = subTotal;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "AddItemTM{" +
                "itemId=" + itemId +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                ", sellPrice=" + sellPrice +
                ", qty=" + qty +
                ", subTotal=" + subTotal +
                '}';
    }
}
