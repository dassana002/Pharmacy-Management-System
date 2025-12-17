package lk.ijse.pharmacymanagementsystem.dto.item;

public class FreeDTO {
    private int free_id;
    private int batch_id;
    private int qty;
    private int ava_qty;

    public FreeDTO() {
    }

    public FreeDTO(int free_id, int batch_id, int qty, int ava_qty) {
        this.free_id = free_id;
        this.batch_id = batch_id;
        this.qty = qty;
        this.ava_qty = ava_qty;
    }

    public FreeDTO(int batch_id, int qty, int ava_qty) {
        this.batch_id = batch_id;
        this.qty = qty;
        this.ava_qty = ava_qty;
    }

    public int getFree_id() {
        return free_id;
    }

    public void setFree_id(int free_id) {
        this.free_id = free_id;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
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
