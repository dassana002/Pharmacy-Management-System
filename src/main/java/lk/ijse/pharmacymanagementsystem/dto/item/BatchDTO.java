package lk.ijse.pharmacymanagementsystem.dto.item;


public class BatchDTO {
    private int batch_id;
    private int batch_number;
    private double sell_price;
    private double cost_price;
    private String expired_date;
    private int qty;
    private int available_qty;
    private int item_code;
    private int bill_id;

    public BatchDTO() {
    }

    public BatchDTO(int batch_id, int batch_number, int available_qty, int item_code, int bill_id) {
        this.batch_id = batch_id;
        this.batch_number = batch_number;
        this.available_qty = available_qty;
        this.item_code = item_code;
        this.bill_id = bill_id;
    }

    public BatchDTO(int batch_id, int batch_number, double sell_price, double cost_price, String expired_date, int qty, int available_qty, int item_code, int bill_id) {
        this.batch_id = batch_id;
        this.batch_number = batch_number;
        this.sell_price = sell_price;
        this.cost_price = cost_price;
        this.expired_date = expired_date;
        this.qty = qty;
        this.available_qty = available_qty;
        this.item_code = item_code;
        this.bill_id = bill_id;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(int batch_number) {
        this.batch_number = batch_number;
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

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAvailable_qty() {
        return available_qty;
    }

    public void setAvailable_qty(int available_qty) {
        this.available_qty = available_qty;
    }

    public int getItem_code() {
        return item_code;
    }

    public void setItem_code(int item_code) {
        this.item_code = item_code;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
                "batch_id=" + batch_id +
                ", batch_number=" + batch_number +
                ", sell_price=" + sell_price +
                ", cost_price=" + cost_price +
                ", expired_date=" + expired_date +
                ", qty=" + qty +
                ", available_qty=" + available_qty +
                ", item_code=" + item_code +
                ", bill_id=" + bill_id +
                '}';
    }
}
