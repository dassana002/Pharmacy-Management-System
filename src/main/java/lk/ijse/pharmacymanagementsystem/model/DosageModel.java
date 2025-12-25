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

    public ArrayList<String> getDosageById(int itemCode) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT size FROM dosage WHERE itemCode = ?");
        ps.setInt(1, itemCode);
        ResultSet rs = ps.executeQuery();

        ArrayList<String> dosages = new ArrayList<>();
        while (rs.next()) {
            dosages.add(rs.getString("size"));
        }
        return dosages;
    }

    public boolean dosageSaveTemp(DosageDTO dosageDTO) throws SQLException {
        String query = "INSERT INTO dosage VALUES(?,?,?)";
        return CrudUtil.execute(query, dosageDTO.getDosage_id(), dosageDTO.getSize(), dosageDTO.getItemCode());
    }
}
