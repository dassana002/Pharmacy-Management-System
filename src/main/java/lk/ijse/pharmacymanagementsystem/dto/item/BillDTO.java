package lk.ijse.pharmacymanagementsystem.dto.item;

import java.util.ArrayList;

public class BillDTO {
    private int bill_id;
    private String invoice_number;
    private ArrayList<BatchDTO> BatchDtoList;

    public BillDTO() {
    }

    public BillDTO(int bill_id, String invoice_number, ArrayList<BatchDTO> batchDtoList) {
        this.bill_id = bill_id;
        this.invoice_number = invoice_number;
        BatchDtoList = batchDtoList;
    }

    public BillDTO(String invoice_number, ArrayList<BatchDTO> batchDtoList) {
        this.invoice_number = invoice_number;
        BatchDtoList = batchDtoList;
    }

    public BillDTO(String invoice_number, int bill_id) {
        this.invoice_number = invoice_number;
        this.bill_id = bill_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public ArrayList<BatchDTO> getBatchDtoList() {
        return BatchDtoList;
    }

    public void setBatchDtoList(ArrayList<BatchDTO> batchDtoList) {
        BatchDtoList = batchDtoList;
    }

    @Override
    public String toString() {
        return "BillDTO{" +
                "bill_id=" + bill_id +
                ", invoice_number='" + invoice_number + '\'' +
                ", BatchDtoList=" + BatchDtoList +
                '}';
    }
}
