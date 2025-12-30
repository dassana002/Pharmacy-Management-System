package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchModel {

    private final FreeModel freeModel = new FreeModel();

    public boolean saveItem(BatchDTO batchDTO, FreeDTO freeDTO, int billId) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            boolean isBatchSave = batchSave(batchDTO, billId);
            if (!isBatchSave) {
                conn.rollback();
                return false;
            }

            boolean isFreeSave = freeModel.freeSave(freeDTO, batchDTO.getBatch_id());
            if (!isFreeSave) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public int getBatchesCount(int itemCode) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM batch WHERE item_code = ?");
        ps.setInt(1, itemCode);
        ResultSet rs = ps.executeQuery();

        int count = 0;
        while (rs.next()) {
            count++;
        }
        return count;
    }

    public boolean batchSave(BatchDTO batchDTO, int billId) throws SQLException {

        String query = "INSERT INTO batch (batch_id, batch_number, sell_price, cost_price, expired_date, qty, available_qty, item_code, bill_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return CrudUtil.execute(
                query,
                batchDTO.getBatch_id(),
                batchDTO.getBatch_number(),
                batchDTO.getSell_price(),
                batchDTO.getCost_price(),
                batchDTO.getExpired_date(),
                batchDTO.getQty(),
                batchDTO.getAvailable_qty(),
                batchDTO.getItem_code(),
                billId
        );
    }


    public ArrayList<BatchDTO> getBatchesByBillId(int billId) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM batch WHERE bill_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, billId);
        ResultSet rs = ps.executeQuery();

        ArrayList<BatchDTO> batchDTOs = new ArrayList<>();
        while (rs.next()) {
            BatchDTO batchDTO = new BatchDTO(
                   rs.getInt("batch_id"),
                   rs.getInt("batch_number"),
                   rs.getDouble("sell_price"),
                   rs.getDouble("cost_price"),
                   rs.getString("expired_date"),
                   rs.getInt("qty"),
                   rs.getInt("available_qty"),
                   rs.getInt("item_code"),
                    rs.getInt("bill_id")
            );
            batchDTOs.add(batchDTO);
        }
        return batchDTOs;
    }

    public ArrayList<Integer> getBatchIDsByItemCode(int itemCode) throws SQLException {
        String query = "SELECT batch_id FROM batch WHERE item_code = ?";
        ResultSet rs = CrudUtil.execute(query, itemCode);

        ArrayList<Integer> batchIDs = new ArrayList<>();
        while (rs.next()) {
            batchIDs.add(rs.getInt("batch_id"));
        }
        return batchIDs;
    }

    public BatchDTO getBatchByItemCodeAndBillId(int itemCode, int billId) throws SQLException {
        String query = "SELECT * FROM batch WHERE item_code = ? AND bill_id = ?";
        ResultSet rs = CrudUtil.execute(query, itemCode, billId);

        BatchDTO batchDTO = null;
        if(rs.next()) {
            batchDTO = new BatchDTO(
                    rs.getInt("batch_id"),
                    rs.getInt("batch_number"),
                    rs.getDouble("sell_price"),
                    rs.getDouble("cost_price"),
                    rs.getString("expired_date"),
                    rs.getInt("qty"),
                    rs.getInt("available_qty"),
                    rs.getInt("item_code"),
                    rs.getInt("bill_id")
            );
        }
        return batchDTO;
    }

    public boolean updateBatchAndFree(BatchDTO newbatchDTO, FreeDTO newFreeDto) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);

            String query = "UPDATE batch SET batch_number = ?, sell_price = ?, cost_price = ?, expired_date = ?, qty = ?, available_qty = ?, item_code = ?, bill_id = ? WHERE batch_id = ?";
            boolean isBatchUpdate = CrudUtil.execute(
                    query,
                    newbatchDTO.getBatch_number(),
                    newbatchDTO.getSell_price(),
                    newbatchDTO.getCost_price(),
                    newbatchDTO.getExpired_date(),
                    newbatchDTO.getQty(),
                    newbatchDTO.getAvailable_qty(),
                    newbatchDTO.getItem_code(),
                    newbatchDTO.getBill_id(),
                    newbatchDTO.getBatch_id()
            );

            if (!isBatchUpdate) {
                conn.rollback();
                return false;
            }

            boolean isUpdateFree = freeModel.updateFree(newFreeDto);
            if (!isUpdateFree) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public boolean deleteBatch(int batchId, int billId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String query = "DELETE FROM batch WHERE bill_id = ? AND batch_id = ?";

            boolean isFreeDelete = freeModel.deleteFree(batchId);
            if (!isFreeDelete) {
                connection.rollback();
                return false;
            }

            boolean isDelete = CrudUtil.execute(query, billId, batchId);
            if (!isDelete) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            throw e;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    public ArrayList<BatchDTO> getOutOfStocks() throws SQLException {

        String query = """
        SELECT 
            b.batch_id,
            b.batch_number,
            b.item_code,
            b.bill_id,
            (b.available_qty + IFNULL(f.ava_qty, 0)) AS total_available_qty
        FROM batch b
        LEFT JOIN free f ON b.batch_id = f.batch_id
        GROUP BY 
            b.batch_id,
            b.batch_number,
            b.item_code,
            b.bill_id,
            b.available_qty,
            f.ava_qty
        HAVING total_available_qty < 10
    """;

        ResultSet rs = CrudUtil.executeQuery(query);
        ArrayList<BatchDTO> outOfStocks = new ArrayList<>();

        while (rs.next()) {
            BatchDTO batchDTO = new BatchDTO(
                    rs.getInt("batch_id"),
                    rs.getInt("batch_number"),
                    0,
                    rs.getInt("item_code"),
                    rs.getInt("bill_id")
            );

            batchDTO.setAvailable_qty(rs.getInt("total_available_qty"));

            outOfStocks.add(batchDTO);
        }
        return outOfStocks;
    }

}
