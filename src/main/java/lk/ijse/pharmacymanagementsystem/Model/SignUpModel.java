package lk.ijse.pharmacymanagementsystem.Model;

import lk.ijse.pharmacymanagementsystem.Dto.EmployeeDTO;
import lk.ijse.pharmacymanagementsystem.Utility.CrudUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class SignUpModel {
    public boolean save(EmployeeDTO employeeDTO) throws SQLException {

        /// Password convert to BCrypt hashing
        String hashedPassword = BCrypt.hashpw(employeeDTO.getPassword(), BCrypt.gensalt());

        String query = "INSERT INTO employee(username, name, password, role)VALUES(?,?,?,?)";
        return CrudUtil.execute(query, employeeDTO.getUserName(), employeeDTO.getName(), hashedPassword, employeeDTO.getRole());
    }
}
