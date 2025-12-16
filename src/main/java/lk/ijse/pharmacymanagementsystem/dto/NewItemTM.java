package lk.ijse.pharmacymanagementsystem.dto;

public class NewItemTM {
    private int item_code;
    private String description;
    private String dosage;

    public NewItemTM() {
    }

    public NewItemTM(int item_code, String description, String dosage) {
        this.item_code = item_code;
        this.description = description;
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItem_code() {
        return item_code;
    }

    public void setItem_code(int item_code) {
        this.item_code = item_code;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "NewItemTM{" +
                "item_code=" + item_code +
                ", description='" + description + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}
