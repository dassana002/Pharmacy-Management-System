package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public ItemDTO getItem(int itemCode) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM item WHERE item_code = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, itemCode);
        ResultSet rs = ps.executeQuery();

        ItemDTO itemDTO = null;
        if (rs.next()) {
            return new ItemDTO(
                    rs.getInt("item_code"),
                    rs.getString("description")
            );
        }
        return null;
    }

    /**
     *
     * Issue--------------------------------------------
     *
     * */
    public boolean saveAll(ArrayList<ItemDTO> items) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO item(item_code, description)VALUES(?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);
        for (ItemDTO item : items) {
            ps.setString(1, Integer.toString(item.getItem_code()));
            ps.setString(2, item.getDescription());
        }

        int[] result = ps.executeBatch();
        return result.length == items.size();
    }
}
