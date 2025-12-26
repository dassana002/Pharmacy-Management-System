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

    public void freeSaveTemp(FreeDTO freeDTO) throws SQLException {
        String query = "INSERT INTO free (free_id, batch_id, qty, ava_qty) VALUES (?, ?, ?, ?)";
        CrudUtil.execute(
                query,
                freeDTO.getFree_id(),
                freeDTO.getBatch_id(),
                freeDTO.getQty(),
                freeDTO.getAva_qty()
        );
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
