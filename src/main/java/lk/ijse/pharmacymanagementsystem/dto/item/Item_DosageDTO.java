package lk.ijse.pharmacymanagementsystem.dto.item;

public class Item_DosageDTO {
    private int itemCode;
    private int dosageId;

    public Item_DosageDTO() {
    }

    public Item_DosageDTO(int itemCode, int dosageId) {
        this.itemCode = itemCode;
        this.dosageId = dosageId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getDosageId() {
        return dosageId;
    }

    public void setDosageId(int dosageId) {
        this.dosageId = dosageId;
    }

    @Override
    public String toString() {
        return "Item_DosageDTO{" +
                "itemCode=" + itemCode +
                ", dosageId=" + dosageId +
                '}';
    }
}
