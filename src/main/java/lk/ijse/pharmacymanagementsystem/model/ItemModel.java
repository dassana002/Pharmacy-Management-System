package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        return itemDTO;
    }

    public boolean save(ItemDTO itemDTO) throws SQLException {
        String query = "INSERT INTO item VALUES (?,?)";
        return CrudUtil.execute(query,itemDTO.getItem_code(), itemDTO.getDescription());
    }

    public boolean saveAll(ItemDTO itemDTO) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);

            boolean isItemSave = save(itemDTO);
            if (!isItemSave) {
                con.rollback();
                return false;
            }

            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw e;

        } finally {
            con.setAutoCommit(true);
        }
    }
}
