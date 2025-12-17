package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatchModel {

    private final FreeModel freeModel = new FreeModel();

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

    public boolean deleteBatch(int itemCode, int batchNumber) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return true;
    }

    public boolean batchSaveTemp(BatchDTO batchDTO, FreeDTO freeDTO, DosageDTO dosageDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            // Batch save
            String query = "INSERT INTO batch (batch_id, batch_number, invoice_number, sell_price, cost_price, today_date, received_date, expired_date, qty, available_qty, status, company_name, item_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            boolean isBatchSave = CrudUtil.execute(
                    query,
                    batchDTO.getBatch_id(),
                    batchDTO.getBatch_number(),
                    batchDTO.getInvoice_number(),
                    batchDTO.getSell_price(),
                    batchDTO.getCost_price(),
                    batchDTO.getToday_Date(),
                    batchDTO.getReceived_date(),
                    batchDTO.getExpired_date(),
                    batchDTO.getQty(),
                    batchDTO.getAvailable_qty(),
                    batchDTO.getStatus(),
                    batchDTO.getCompany_name(),
                    batchDTO.getItem_code()
            );

            // Free Save
            if (isBatchSave) {
                boolean isFreeSave = freeModel.freeSaveTemp(freeDTO, dosageDTO);

            } else {
                throw new SQLException();
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

    public int getBatch(int itemCode) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM batch WHERE item_code = ?";
        return 0;
    }
}
