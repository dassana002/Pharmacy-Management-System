package lk.ijse.pharmacymanagementsystem.model;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;
import lk.ijse.pharmacymanagementsystem.dto.employee.EmployeeDTO;
import lk.ijse.pharmacymanagementsystem.utility.CrudUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {

    public boolean save(EmployeeDTO employeeDTO) throws SQLException {
        // Password convert to BCrypt hashing
        String hashedPassword = BCrypt.hashpw(employeeDTO.getPassword(), BCrypt.gensalt());
        String query = "INSERT INTO employee (user_name, name, password, role)VALUES (?,?,?,?)";
        return CrudUtil.execute(query,employeeDTO.getUserName(), employeeDTO.getName(), hashedPassword, employeeDTO.getRole());
    }

    public boolean checkValidation(String userName, String password) throws SQLException {

        if (userName != null || password != null) {

            Connection con = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM employee WHERE user_name = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            EmployeeDTO employeeDTO = null;

            if (rs.next()) {
                employeeDTO = new EmployeeDTO(
                        rs.getInt("employee_id"),
                        rs.getString("user_name"),
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
