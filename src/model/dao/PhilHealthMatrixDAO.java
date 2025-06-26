package model.dao;

import db.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import model.pojo.PhilHealthMatrix;

public class PhilHealthMatrixDAO {

    // Retrieve PhilHealth row by employee's gross income
    public PhilHealthMatrix getByGrossIncome(BigDecimal grossIncome) {
        String sql = "SELECT * FROM PhilHealthMatrix WHERE ? >= minGrossIncome AND (? <= maxGrossIncome OR maxGrossIncome IS NULL) ORDER BY minGrossIncome DESC LIMIT 1";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, grossIncome);
            ps.setBigDecimal(2, grossIncome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PhilHealthMatrix matrix = new PhilHealthMatrix();
                    matrix.setPhilhealthID(rs.getInt("philhealthID"));
                    matrix.setMinGrossIncome(rs.getBigDecimal("minGrossIncome"));
                    matrix.setMaxGrossIncome(rs.getBigDecimal("maxGrossIncome"));
                    matrix.setPremiumRate(rs.getBigDecimal("premiumRate"));
                    matrix.setContributionAmount(rs.getBigDecimal("contributionAmount"));
                    return matrix;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}