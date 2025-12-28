package lk.ijse.pharmacymanagementsystem.model;

public class BatchDosageDTO {
    private int batch_id;
    private int dosage_id;

    public BatchDosageDTO(int batch_id, int dosage_id) {
        this.batch_id = batch_id;
        this.dosage_id = dosage_id;
    }

    public BatchDosageDTO() {
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getDosage_id() {
        return dosage_id;
    }

    public void setDosage_id(int dosage_id) {
        this.dosage_id = dosage_id;
    }

    @Override
    public String toString() {
        return "BatchDosageDTO{" +
                "batch_id=" + batch_id +
                ", dosage_id=" + dosage_id +
                '}';
    }
}
