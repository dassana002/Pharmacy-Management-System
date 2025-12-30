package lk.ijse.pharmacymanagementsystem.dto.item;

public class OutOfStockTM {
    private int itemCode;
    private String description;
    private int batchNumber;
    private int availableQty;
    private String supplier;

    public OutOfStockTM(int itemCode, String description, int batchNumber, int availableQty, String supplier) {
        this.itemCode = itemCode;
        this.description = description;
        this.batchNumber = batchNumber;
        this.availableQty = availableQty;
        this.supplier = supplier;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "OutOfStockTM{" +
                "itemCode=" + itemCode +
                ", description='" + description + '\'' +
                ", batchNumber=" + batchNumber +
                ", availableQty=" + availableQty +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
