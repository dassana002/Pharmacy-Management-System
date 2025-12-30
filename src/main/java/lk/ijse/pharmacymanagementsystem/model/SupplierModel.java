package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.item.SupplierDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;

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

    public SupplierDTO getSupplierByCompany(String companyName) throws SQLException {
        String query = "SELECT * FROM supplier WHERE company_name = ?";
        ResultSet rs = CrudUtil.execute(query,companyName);
        SupplierDTO supplierDTO = null;
        if (rs.next()) {
            supplierDTO = new SupplierDTO(
                    rs.getString("company_name"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getInt("company_contact"),
                    rs.getString("supplier_name"),
                    rs.getInt("supplier_contact")
            );
        }
        return supplierDTO;
    }

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        String query = "INSERT INTO supplier (company_name,address,email,company_contact,supplier_name,supplier_contact) VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(query,supplierDTO.getCompany_name(), supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getCompany_contact(), supplierDTO.getSupplier_name(),supplierDTO.getSupplier_contact());
    }

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        String query = "UPDATE supplier SET address = ?,email = ?,company_contact = ?,supplier_name = ?,supplier_contact = ? WHERE company_name = ?";
        return CrudUtil.execute(query,supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getCompany_contact(), supplierDTO.getSupplier_name(),supplierDTO.getSupplier_contact(), supplierDTO.getCompany_name());
    }
}
