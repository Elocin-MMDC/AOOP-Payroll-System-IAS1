package model.dao;

import db.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import model.pojo.WithholdingTaxMatrix;

public class WithholdingTaxMatrixDAO {

    // Retrieve tax bracket by employee's taxable income
    public WithholdingTaxMatrix getByTaxableIncome(BigDecimal taxableIncome) {
        String sql = """
                SELECT *
                FROM WithholdingTaxMatrix
                WHERE ? >= minTaxableIncome
                  AND (? <= maxTaxableIncome OR maxTaxableIncome IS NULL)
                ORDER BY minTaxableIncome DESC
                LIMIT 1
                """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, taxableIncome);
            ps.setBigDecimal(2, taxableIncome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    WithholdingTaxMatrix matrix = new WithholdingTaxMatrix();
                    matrix.setWithholdingTaxID(rs.getInt("withholdingTaxID"));
                    matrix.setMinTaxableIncome(rs.getBigDecimal("minTaxableIncome"));
                    matrix.setMaxTaxableIncome(rs.getBigDecimal("maxTaxableIncome"));
                    matrix.setBaseTax(rs.getBigDecimal("baseTax"));
                    matrix.setExcessRate(rs.getBigDecimal("excessRate"));
                    return matrix;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}