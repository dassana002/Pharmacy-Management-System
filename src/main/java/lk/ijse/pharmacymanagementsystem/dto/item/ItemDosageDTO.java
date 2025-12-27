package lk.ijse.pharmacymanagementsystem.dto.item;

public class ItemDosageDTO {
    private int itemDosage_id;
    private int itemCode;
    private int dosageId;

    public ItemDosageDTO() {
    }

    public ItemDosageDTO(int itemDosage_id, int itemCode, int dosageId) {
        this.itemDosage_id = itemDosage_id;
        this.itemCode = itemCode;
        this.dosageId = dosageId;
    }

    public ItemDosageDTO(int itemCode, int dosageId) {
        this.itemCode = itemCode;
        this.dosageId = dosageId;
    }

    public int getItemDosage_id() {
        return itemDosage_id;
    }

    public void setItemDosage_id(int itemDosage_id) {
        this.itemDosage_id = itemDosage_id;
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
                "itemDosage_id=" + itemDosage_id +
                ", itemCode=" + itemCode +
                ", dosageId=" + dosageId +
                '}';
    }
}
