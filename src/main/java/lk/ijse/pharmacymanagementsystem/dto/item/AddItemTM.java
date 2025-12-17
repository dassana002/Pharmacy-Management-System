package lk.ijse.pharmacymanagementsystem.dto.item;

public class AddItemTM {
    private int id;
    private String description;
    private double unitCost;
    private double sellPrice;
    private int qty;
    private double subTotal;

    public AddItemTM() {
    }

    public AddItemTM(String description, double unitCost, double sellPrice, int qty, double subTotal) {
        this.description = description;
        this.unitCost = unitCost;
        this.sellPrice = sellPrice;
        this.qty = qty;
        this.subTotal = subTotal;
    }

    public AddItemTM(int number, String description, double unitCost, double sellPrice, int qty, double subTotal) {
        this.id = number;
        this.description = description;
        this.unitCost = unitCost;
        this.sellPrice = sellPrice;
        this.qty = qty;
        this.subTotal = subTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int number) {
        this.id = number;
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
                "number=" + id +
                ", description='" + description + '\'' +
                ", unitCost=" + unitCost +
                ", sellPrice=" + sellPrice +
                ", qty=" + qty +
                ", subTotal=" + subTotal +
                '}';
    }
}
