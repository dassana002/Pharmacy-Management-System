package lk.ijse.pharmacymanagementsystem.dto.item;

public class ItemDTO {
    private int item_code;
    private String description;

    public ItemDTO() {
    }

    public ItemDTO(int item_code, String description) {
        this.item_code = item_code;
        this.description = description;
    }

    public int getItem_code() {
        return item_code;
    }

    public void setItem_code(int item_code) {
        this.item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "item_code=" + item_code +
                ", description='" + description + '\'' +
                '}';
    }
}
