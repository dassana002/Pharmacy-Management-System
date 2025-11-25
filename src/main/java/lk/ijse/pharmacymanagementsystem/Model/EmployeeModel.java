package lk.ijse.pharmacymanagementsystem.Model;

import lk.ijse.pharmacymanagementsystem.DBconnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.Dto.EmployeeDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {

    public boolean save(EmployeeDTO employeeDTO) throws SQLException {

        // Password convert to BCrypt hashing
        String hashedPassword = BCrypt.hashpw(employeeDTO.getPassword(), BCrypt.gensalt());

        Connection con = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO employee (username, name, password, role)VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, employeeDTO.getUserName());
        ps.setString(2, employeeDTO.getName());
        ps.setString(3, hashedPassword);
        ps.setString(4, employeeDTO.getRole());

        int result = ps.executeUpdate();

        return result > 0;
    }

    public boolean checkValidation(String userName, String password) throws SQLException {

        if (userName != null || password != null) {

            Connection con = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM employee WHERE username = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            EmployeeDTO employeeDTO = null;

            if (rs.next()) {
                employeeDTO = new EmployeeDTO(
                        rs.getInt("employee_id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                return BCrypt.checkpw(password, rs.getString("password"));
            }
        }
        return false;
    }
}
