package lk.ijse.pharmacymanagementsystem.dto.item;

public class BatchDTO {
    private int batch_id;
    private int batch_number;
    private double sell_price;
    private double cost_price;
    private String today_Date;
    private String expired_date;
    private String received_date;
    private int qty;
    private int available_qty;
    private String company_name;
    private int item_code;
    private int bill_id;

    public BatchDTO() {
    }

    public BatchDTO(int batch_id, int batch_number, double sell_price, double cost_price, String today_Date, String expired_date, String received_date, int qty, int available_qty, String company_name, int item_code, int bill_id) {
        this.batch_id = batch_id;
        this.batch_number = batch_number;
        this.sell_price = sell_price;
        this.cost_price = cost_price;
        this.today_Date = today_Date;
        this.expired_date = expired_date;
        this.received_date = received_date;
        this.qty = qty;
        this.available_qty = available_qty;
        this.company_name = company_name;
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

    public String getToday_Date() {
        return today_Date;
    }

    public void setToday_Date(String today_Date) {
        this.today_Date = today_Date;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getReceived_date() {
        return received_date;
    }

    public void setReceived_date(String received_date) {
        this.received_date = received_date;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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
                ", today_Date='" + today_Date + '\'' +
                ", expired_date='" + expired_date + '\'' +
                ", received_date='" + received_date + '\'' +
                ", qty=" + qty +
                ", available_qty=" + available_qty +
                ", company_name='" + company_name + '\'' +
                ", item_code=" + item_code +
                ", bill_id=" + bill_id +
                '}';
    }
}
