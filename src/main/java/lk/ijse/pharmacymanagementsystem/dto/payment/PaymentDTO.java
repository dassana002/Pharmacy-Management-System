package lk.ijse.pharmacymanagementsystem.dto.payment;

import lk.ijse.pharmacymanagementsystem.model.Payment;

public class PaymentDTO {
    private int payment_id;
    private String payment_date;
    private String payment_type;
    private Payment payment;
    private double amount;

    public PaymentDTO() {
    }

    public PaymentDTO(int payment_id, String payment_date, String payment_type, Payment payment, double amount) {
        this.payment_id = payment_id;
        this.payment_date = payment_date;
        this.payment_type = payment_type;
        this.payment = payment;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "payment_id=" + payment_id +
                ", payment_date='" + payment_date + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", payment=" + payment +
                ", amount=" + amount +
                '}';
    }
}
