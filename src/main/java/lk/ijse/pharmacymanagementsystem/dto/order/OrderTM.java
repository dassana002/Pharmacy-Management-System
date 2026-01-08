package lk.ijse.pharmacymanagementsystem.dto.order;

public class OrderTM {
    private int id;
    private String description;
    private double sellPrice;
    private int qty;
    private double subTotal;

    public OrderTM() {
    }

    public OrderTM(int id, String description, double sellPrice, int qty, double subTotal) {
        this.id = id;
        this.description = description;
        this.sellPrice = sellPrice;
        this.qty = qty;
        this.subTotal = subTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "OrderTM{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sellPrice=" + sellPrice +
                ", qty=" + qty +
                ", subTotal=" + subTotal +
                '}';
    }
}
