package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.*;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillModel {

    private final BatchModel batchModel = new BatchModel();
    private final FreeModel freeModel = new FreeModel();
    private final ItemDosageModel itemDosageModel = new ItemDosageModel();

    public boolean saveBill(BatchDTO batchDTO, FreeDTO freeDTO, ItemDosageDTO itemDosageDTO, BillDTO billDTO) throws SQLException {

        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            // Bill
            String billQuery = "INSERT INTO bill (bill_id, invoice_number, status, company_name, today_date, received_date) VALUES (?, ?, ?, ?,?,?)";
            CrudUtil.execute(
                    billQuery,
                    billDTO.getBill_id(),
                    billDTO.getInvoice_number(),
                    billDTO.getStatus().name(),
                    billDTO.getCompany_name(),
                    billDTO.getToday_date(),
                    billDTO.getReceived_date()
            );

            // Batch
            batchModel.batchSaveTemp(batchDTO, billDTO.getBill_id());

            // Free
            freeModel.freeSaveTemp(freeDTO);

            // Dosage
            itemDosageModel.save(itemDosageDTO);

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
        String query = "SELECT bill_id FROM bill WHERE invoice_number = ?";
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
