package lk.ijse.pharmacymanagementsystem.dto.item;

public class NewItemTM {
    private int itemCode;
    private String description;

    public NewItemTM() {
    }

    public NewItemTM(int itemCode, String description) {
        this.itemCode = itemCode;
        this.description = description;
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

    @Override
    public String toString() {
        return "NewItemTM{" +
                "item_code=" + itemCode +
                ", description='" + description + '\'' +
                '}';
    }
}
