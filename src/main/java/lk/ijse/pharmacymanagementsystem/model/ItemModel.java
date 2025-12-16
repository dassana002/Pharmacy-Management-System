package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public boolean save(ArrayList<ItemDTO> items) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO item(item_code, description, dosage)VALUES(?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);
        for (ItemDTO item : items) {
            ps.setString(1, Integer.toString(item.getItem_code()));
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getDosage());
        }

        int[] result = ps.executeBatch();
        return result.length == items.size();
    }
}
