package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.BillDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillModel {

    private final BatchModel batchModel = new BatchModel();

    public boolean saveBill(BatchDTO batchDTO, FreeDTO freeDTO, DosageDTO dosageDTO, BillDTO billDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            // Bill save
            String query = "INSERT INTO bill (bill_id, invoice_number) VALUES (?, ?)";
            boolean isBillSave = CrudUtil.execute(
                    query,
                    billDTO.getBill_id(),
                    billDTO.getInvoice_number()
            );

            // Batch save
            if (isBillSave) {
                boolean isBatchSave = batchModel.batchSaveTemp(batchDTO, freeDTO, dosageDTO, billDTO.getBill_id());

            } else {
                throw new  SQLException();
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

    public int getBillIdByInvoice(String invoiceNumber) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT bill_id FROM batch WHERE invoice_number = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, invoiceNumber);
        ResultSet rs = ps.executeQuery();

        int id = 0;
        if (rs.next()) {
             id = rs.getInt("bill_id");
             return id;
        }
        return id;
    }
}
