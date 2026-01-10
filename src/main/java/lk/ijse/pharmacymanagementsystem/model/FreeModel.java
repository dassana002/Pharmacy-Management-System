package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FreeModel {

    public boolean freeSave(FreeDTO freeDTO, String batchId) throws SQLException {
        String query = "INSERT INTO free (free_id, batch_id, qty, ava_qty) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(
                query,
                freeDTO.getFree_id(),
                batchId,
                freeDTO.getQty(),
                freeDTO.getAva_qty()
        );
    }

    public int getFreeQtyById(String batchId) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT qty FROM free WHERE batch_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,batchId);

        ResultSet resultSet = preparedStatement.executeQuery();

        int qty = 0;
        if (resultSet.next()) {
            qty = resultSet.getInt("qty");
        }

        return qty;
    }

    public FreeDTO getFreeById(String batchId) throws SQLException {
        String query = "SELECT * FROM free WHERE batch_id=?";
        ResultSet rs = CrudUtil.execute(query, batchId);
        FreeDTO freeDTO = null;
        if (rs.next()) {
            freeDTO = new FreeDTO(
                    rs.getString("free_id"),
                    rs.getString("batch_id"),
                    rs.getInt("qty"),
                    rs.getInt("ava_qty")
            );
        }
        return freeDTO;
    }

    public boolean updateFree(FreeDTO newFreeDto) throws SQLException {
        String query = "UPDATE free SET qty = ?, ava_qty = ? WHERE batch_id = ?";
        return CrudUtil.execute(
                query,
                newFreeDto.getQty(),
                newFreeDto.getAva_qty(),
                newFreeDto.getBatch_id()
        );
    }

    public boolean deleteFree(String batchId) throws SQLException {
        String query = "DELETE FROM free WHERE batch_id=?";
        return CrudUtil.execute(query,batchId);
    }
}
