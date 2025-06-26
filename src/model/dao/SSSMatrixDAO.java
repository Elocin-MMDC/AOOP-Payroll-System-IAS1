package model.dao;

import db.DBConnection;
import java.math.BigDecimal;
import model.pojo.SSSMatrix;
import java.sql.*;

public class SSSMatrixDAO {

    // Retrieve SSS row by employee's gross income
    public SSSMatrix getByGrossIncome(BigDecimal grossIncome) {
        String sql = "SELECT * FROM SSSMatrix WHERE ? >= minGrossIncome AND (? <= maxGrossIncome OR maxGrossIncome IS NULL) ORDER BY minGrossIncome DESC LIMIT 1";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setBigDecimal(1, grossIncome);
            ps.setBigDecimal(2, grossIncome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SSSMatrix sss = new SSSMatrix();
                    sss.setSssID(rs.getInt("sssID"));
                    sss.setMinGrossIncome(rs.getBigDecimal("minGrossIncome"));
                    sss.setMaxGrossIncome(rs.getBigDecimal("maxGrossIncome"));
                    sss.setContributionAmount(rs.getBigDecimal("contributionAmount"));
                    return sss;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
