package lk.ijse.pharmacymanagementsystem.dto.order;

public class OrderDetailDTO {
    private int orderDetail_id;
    private int order_id;
    private int batch_id;
    private int qty;
    private double price;
    private double subTotal;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetail_id, int order_id, int batch_id, int qty, double price, double subTotal) {
        this.orderDetail_id = orderDetail_id;
        this.order_id = order_id;
        this.batch_id = batch_id;
        this.qty = qty;
        this.price = price;
        this.subTotal = subTotal;
    }

    public int getOrderDetail_id() {
        return orderDetail_id;
    }

    public void setOrderDetail_id(int orderDetail_id) {
        this.orderDetail_id = orderDetail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderDetail_id=" + orderDetail_id +
                ", order_id=" + order_id +
                ", batch_id=" + batch_id +
                ", qty=" + qty +
                ", price=" + price +
                ", subTotal=" + subTotal +
                '}';
    }
}
