package lk.ijse.pharmacymanagementsystem.Dto;

public class AddItemTM {
    private int number;
    private String description;
    private double unit_cost;
    private double sell_price;
    private int qty;
    private double subtotal;

    public AddItemTM() {
    }

    public AddItemTM(int number, String description, double unit_cost, double sell_price, int qty, double subtotal) {
        this.number = number;
        this.description = description;
        this.unit_cost = unit_cost;
        this.sell_price = sell_price;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(double unit_cost) {
        this.unit_cost = unit_cost;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "AddItemTM{" +
                "number=" + number +
                ", description='" + description + '\'' +
                ", unit_cost=" + unit_cost +
                ", sell_price=" + sell_price +
                ", qty=" + qty +
                ", subtotal=" + subtotal +
                '}';
    }
}
