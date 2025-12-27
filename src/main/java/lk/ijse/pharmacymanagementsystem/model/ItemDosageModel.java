package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDosageDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDosageModel {

    private final DosageModel dosageModel = new DosageModel();

    public ArrayList<Integer> getItemDosagesByItemCode(int itemCode) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String query = "SELECT dosage_id FROM itemdosage WHERE item_code = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, itemCode);
        ResultSet rs = ps.executeQuery();

        ArrayList<Integer> dosageIds = new ArrayList<>();
        while (rs.next()) {
            dosageIds.add(rs.getInt("dosage_id"));
        }
        return dosageIds;
    }

    public boolean newDosageSave(DosageDTO dosageDTO, ItemDosageDTO itemDosageDTO) throws SQLException {

        Connection con = DBConnection.getInstance().getConnection();

        try {
            con.setAutoCommit(false);

            // Save dosage
            boolean isDosageSaved = dosageModel.save(dosageDTO);

            if (!isDosageSaved) {
                con.rollback();
                return false;
            }

            // save item-dosage
            boolean isItemDosageSaved = save(itemDosageDTO);

            if (!isItemDosageSaved) {
                con.rollback();
                return false;
            }

            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw new RuntimeException(e);

        } finally {
            con.setAutoCommit(true);
        }
    }

    public boolean save(ItemDosageDTO itemDosageDTO) throws SQLException {
        String query = "INSERT INTO itemdosage (item_code, dosage_id) VALUES (?,?)";
        return CrudUtil.execute(
                query,
                itemDosageDTO.getItemCode(),
                itemDosageDTO.getDosageId()
        );
    }
}
