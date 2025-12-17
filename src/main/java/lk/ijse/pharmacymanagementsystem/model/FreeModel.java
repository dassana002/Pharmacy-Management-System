package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
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
}
