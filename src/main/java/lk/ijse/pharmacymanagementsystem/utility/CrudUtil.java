package lk.ijse.pharmacymanagementsystem.utility;

import lk.ijse.pharmacymanagementsystem.dbConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql, Object... obj) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement ptsm = conn.prepareStatement(sql);

        for(int i = 0; i < obj.length; i++){
            ptsm.setObject(i+1, obj[i]);
        }

        if(sql.startsWith("select") || sql.startsWith("SELECT")) {
            ResultSet rs = ptsm.executeQuery();
            return (T)rs;
        }else {
            int result = ptsm.executeUpdate();
            boolean rs = result>0;
            return (T)(Boolean)rs;
        }
    }

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }

        return ps.executeQuery();
    }

}
