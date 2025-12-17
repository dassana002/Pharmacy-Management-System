package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ItemModel {

    private final DosageModel dosageModel = new DosageModel();

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

    public boolean saveAll(ItemDTO itemDTO, String dosage) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            // Item Save
            String query = "INSERT INTO item (item_code, description) VALUES (?, ?)";
            boolean isItemSave = CrudUtil.execute(query, itemDTO.getItem_code(), itemDTO.getDescription());

            // Dosage Save
            if (isItemSave) {
                int dosage_id = UUID.randomUUID().hashCode();
                DosageDTO dosageDTO = new DosageDTO(
                        dosage_id,
                        dosage,
                        itemDTO.getItem_code()
                );
                boolean isDosageSave = dosageModel.dosageSaveTemp(dosageDTO);

            }else {
                throw new SQLException();
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
}
