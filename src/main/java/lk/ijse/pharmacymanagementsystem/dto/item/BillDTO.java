package lk.ijse.pharmacymanagementsystem.dto.item;

import lk.ijse.pharmacymanagementsystem.model.Status;

import java.util.ArrayList;

public class BillDTO {
    private int bill_id;
    private String invoice_number;
    private Status status;
    private ArrayList<BatchDTO> BatchDtoList;

    public BillDTO() {
    }

    public BillDTO(int bill_id, String invoice_number, Status status, ArrayList<BatchDTO> batchDtoList) {
        this.bill_id = bill_id;
        this.invoice_number = invoice_number;
        this.status = status;
        BatchDtoList = batchDtoList;
    }

    public BillDTO(int bill_id, String invoice_number, Status status) {
        this.bill_id = bill_id;
        this.invoice_number = invoice_number;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                ", status=" + status +
                ", BatchDtoList=" + BatchDtoList +
                '}';
    }
}
