package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FreeModel {

    private final DosageModel  dosageModel = new  DosageModel();

    public boolean freeSaveTemp(FreeDTO freeDTO, DosageDTO dosageDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            // Free Save
            String query = "INSERT INTO free (free_id, batch_id, qty, ava_qty) VALUES (?, ?, ?, ?)";
            boolean isFreeSave = CrudUtil.execute(
                    query,
                    freeDTO.getFree_id(),
                    freeDTO.getBatch_id(),
                    freeDTO.getQty(),
                    freeDTO.getAva_qty()
                    );

            // Dosage Save
            if (isFreeSave) {
                boolean isDosageSave = dosageModel.dosageSaveTemp(dosageDTO);

            }else {
                throw new SQLException();
            }

        }catch (Exception e){
            throw new SQLException();
        }

        return true;
    }

    public int getFreeQtyById(int batchId) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT qty FROM free WHERE batch_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1,batchId);

        ResultSet resultSet = preparedStatement.executeQuery();

        int qty = 0;
        if (resultSet.next()) {
            qty = resultSet.getInt("qty");
        }

        return qty;
    }
}
