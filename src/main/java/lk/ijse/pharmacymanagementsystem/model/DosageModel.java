package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DosageModel {

    public ArrayList<String> getDosageIdsBySize(ArrayList<Integer> dosageIds) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT size FROM dosage WHERE dosage_id = ?");

        ArrayList<String> dosages = new ArrayList<>();

        for (int dosageId : dosageIds) {
            ps.setInt(1, dosageId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dosages.add(rs.getString("size"));
            }
        }
        return dosages;
    }

    public boolean save(DosageDTO dosageDTO) throws SQLException {
        String query = "INSERT INTO dosage VALUES (?,?)";
        return CrudUtil.execute(query,dosageDTO.getDosage_id(), dosageDTO.getSize());
    }

    public int findSizeById(String dosage) throws SQLException {
        String query = "SELECT dosage_id FROM dosage WHERE size = ?";
        ResultSet rs = CrudUtil.execute(query, dosage);

        if (rs.next()) {
            return rs.getInt("dosage_id");
        } else {
            throw new SQLException("Dosage not found for size: " + dosage);
        }
    }

}
