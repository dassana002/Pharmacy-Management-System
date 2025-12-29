package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.*;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillModel {

    private final BatchModel batchModel = new BatchModel();
    private final FreeModel freeModel = new FreeModel();

    public boolean saveBill(BatchDTO batchDTO, FreeDTO freeDTO, BillDTO billDTO) throws SQLException {

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

            // Batch save
            boolean isBatchSave = batchModel.batchSave(batchDTO, billDTO.getBill_id());
            if (!isBatchSave) {
                conn.rollback();
                return false;
            }

            // Free save
            boolean isFreeSave = freeModel.freeSaveTemp(freeDTO);
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

    public BillDTO getBillById(int billId) throws SQLException {
        String query = "SELECT * FROM bill WHERE bill_id = ?";
        ResultSet rs = CrudUtil.execute(query, billId);

        BillDTO billDTO = null;
        ArrayList<BatchDTO> batchDTOS = batchModel.getBatchesByBillId(billId);

        if (rs.next()) {
            billDTO = new BillDTO(
                    rs.getInt("bill_id"),
                    rs.getString("invoice_number"),
                    Status.valueOf(rs.getString("status")),
                    rs.getString("company_name"),
                    rs.getString("today_date"),
                    rs.getString("received_date"),
                    batchDTOS
            );
        }
        return billDTO;
    }

    public String searchStatusById(int billId) throws SQLException {
        String query = "SELECT status FROM bill WHERE bill_id = ?";
        ResultSet rs =  CrudUtil.execute(query, billId);

        String status = null;
        if (rs.next()) {
             status = rs.getString("status");
        }
        return status;
    }
}
