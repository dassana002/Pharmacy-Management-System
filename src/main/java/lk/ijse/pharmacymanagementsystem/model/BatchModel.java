package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchModel {

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

    public BatchDTO getBatch(int itemCode) throws SQLException {
        String query = "SELECT * FROM batch WHERE item_code = ?";
        ResultSet rs = CrudUtil.execute(query, itemCode);

        BatchDTO batchDTO = null;
        if (rs.next()) {
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

    public ArrayList<Integer> getBatchIDsByItemCode(int itemCode) throws SQLException {
        String query = "SELECT batch_id FROM batch WHERE item_code = ?";
        ResultSet rs = CrudUtil.execute(query, itemCode);

        ArrayList<Integer> batchIDs = new ArrayList<>();
        while (rs.next()) {
            batchIDs.add(rs.getInt("batch_id"));
        }
        return batchIDs;
    }
}
