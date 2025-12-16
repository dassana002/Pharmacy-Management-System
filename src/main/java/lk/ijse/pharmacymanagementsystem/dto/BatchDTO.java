package lk.ijse.pharmacymanagementsystem.dto;

import lk.ijse.pharmacymanagementsystem.model.Status;
import java.util.Date;

public class BatchDTO {
    private String batch_id;
    private String batch_number;
    private String invoice_number;
    private double sell_price;
    private double cost_price;
    private Date expired_date;
    private Date received_date;
    private int qty;
    private Status status;
    private String company_name;
    private String itemCode;

    public BatchDTO() {
    }

    public BatchDTO(String batch_id, String batch_number, ItemDTO item, String invoice_number, double cost_price, double sell_price, Date expired_date, Date received_date, int qty, Status status, String company_name, String itemCode) {
        this.batch_id = batch_id;
        this.batch_number = batch_number;
        this.invoice_number = invoice_number;
        this.cost_price = cost_price;
        this.sell_price = sell_price;
        this.expired_date = expired_date;
        this.received_date = received_date;
        this.qty = qty;
        this.status = status;
        this.company_name = company_name;
        this.itemCode = itemCode;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
                "batch_id='" + batch_id + '\'' +
                ", batch_number='" + batch_number + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", sell_price=" + sell_price +
                ", cost_price=" + cost_price +
                ", expired_date=" + expired_date +
                ", received_date=" + received_date +
                ", qty=" + qty +
                ", status=" + status +
                ", company_name=" + company_name +
                ", itemCode=" + itemCode +
                '}';
    }
}
