package lk.ijse.pharmacymanagementsystem.dto.order;

import lk.ijse.pharmacymanagementsystem.model.Status;

public class OrderDTO {
    private int order_id;
    private String date;
    private Status status;
    private String customer_NIC;
    private int employee_id;
    private int payment_id;

    public OrderDTO() {
    }

    public OrderDTO(int order_id, String date, Status status, String customer_NIC, int employee_id, int payment_id) {
        this.order_id = order_id;
        this.date = date;
        this.status = status;
        this.customer_NIC = customer_NIC;
        this.employee_id = employee_id;
        this.payment_id = payment_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCustomer_NIC() {
        return customer_NIC;
    }

    public void setCustomer_NIC(String customer_NIC) {
        this.customer_NIC = customer_NIC;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "order_id=" + order_id +
                ", date='" + date + '\'' +
                ", status=" + status +
                ", customer_NIC='" + customer_NIC + '\'' +
                ", employee_id=" + employee_id +
                ", payment_id=" + payment_id +
                '}';
    }
}
