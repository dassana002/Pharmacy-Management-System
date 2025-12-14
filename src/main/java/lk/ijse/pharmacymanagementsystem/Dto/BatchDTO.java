package lk.ijse.pharmacymanagementsystem.Dto;

import java.util.Date;

public class BatchDTO {
    private String batch_id;
    private String batch_number;
    private ItemDTO item;
    private String invoice_number;
    private double sell_price;
    private double cost_price;
    private Date expired_date;
    private Date received_date;
    private int qty;
}
