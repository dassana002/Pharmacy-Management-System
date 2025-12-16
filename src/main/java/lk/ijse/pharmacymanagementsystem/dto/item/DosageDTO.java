package lk.ijse.pharmacymanagementsystem.dto.item;

public class DosageDTO {
    private int dosage_id;
    private String size;
    private int itemCode;

    public DosageDTO() {
    }

    public DosageDTO(int dosage_id, String size, int itemCode) {
        this.dosage_id = dosage_id;
        this.size = size;
        this.itemCode = itemCode;
    }

    public int getDosage_id() {
        return dosage_id;
    }

    public void setDosage_id(int dosage_id) {
        this.dosage_id = dosage_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "DosageDTO{" +
                "dosage_id=" + dosage_id +
                ", size='" + size + '\'' +
                ", itemCode=" + itemCode +
                '}';
    }
}
