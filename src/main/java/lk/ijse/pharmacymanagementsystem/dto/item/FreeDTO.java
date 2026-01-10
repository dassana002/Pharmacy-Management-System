package lk.ijse.pharmacymanagementsystem.dto.item;

public class FreeDTO {
    private String free_id;
    private String batch_id;
    private int qty;
    private int ava_qty;

    public FreeDTO() {
    }

    public FreeDTO(String free_id, String batch_id, int qty, int ava_qty) {
        this.free_id = free_id;
        this.batch_id = batch_id;
        this.qty = qty;
        this.ava_qty = ava_qty;
    }

    public FreeDTO(String batch_id, int qty, int ava_qty) {
        this.batch_id = batch_id;
        this.qty = qty;
        this.ava_qty = ava_qty;
    }

    public String getFree_id() {
        return free_id;
    }

    public void setFree_id(String free_id) {
        this.free_id = free_id;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getAva_qty() {
        return ava_qty;
    }

    public void setAva_qty(int ava_qty) {
        this.ava_qty = ava_qty;
    }

    @Override
    public String toString() {
        return "FreeDTO{" +
                "free_id=" + free_id +
                ", batch_id=" + batch_id +
                ", qty=" + qty +
                ", ava_qty=" + ava_qty +
                '}';
    }
}
