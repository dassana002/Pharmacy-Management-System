package lk.ijse.pharmacymanagementsystem.dto.item;

public class DosageDTO {
    private int dosage_id;
    private String size;

    public DosageDTO() {
    }

    public DosageDTO(int dosage_id, String size) {
        this.dosage_id = dosage_id;
        this.size = size;
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

    @Override
    public String toString() {
        return "DosageDTO{" +
                "dosage_id=" + dosage_id +
                ", size='" + size + '\'' +
                '}';
    }
}
