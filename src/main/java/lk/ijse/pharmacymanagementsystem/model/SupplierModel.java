package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public ArrayList<SupplierDTO> getSuppliers() throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM supplier");
        ResultSet rs = ps.executeQuery();

        ArrayList<SupplierDTO> suppliers = new ArrayList<>();
        while (rs.next()) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    rs.getString("company_name"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getInt("company_contact"),
                    rs.getString("supplier_name"),
                    rs.getInt("supplier_contact")
            );
            suppliers.add(supplierDTO);
        }
        return suppliers;
    }
}
