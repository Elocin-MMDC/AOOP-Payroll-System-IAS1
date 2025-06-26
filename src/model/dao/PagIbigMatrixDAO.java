package model.dao;

import db.DBConnection;
import model.pojo.PagIbigMatrix;
import java.math.BigDecimal;
import java.sql.*;

public class PagIbigMatrixDAO {

    // Retrieve PagIbig row by employee's gross income
    public PagIbigMatrix getByGrossIncome(BigDecimal grossIncome) {
        String sql = """
            SELECT * FROM PagIbigMatrix
            WHERE minGrossIncome <= ? AND (maxGrossIncome IS NULL OR ? <= maxGrossIncome)
            LIMIT 1
        """;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, grossIncome);
            ps.setBigDecimal(2, grossIncome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PagIbigMatrix matrix = new PagIbigMatrix();
                    matrix.setPagIbigID(rs.getInt("pagIbigID"));
                    matrix.setMinGrossIncome(rs.getBigDecimal("minGrossIncome"));
                    matrix.setMaxGrossIncome(rs.getBigDecimal("maxGrossIncome"));
                    matrix.setEmployeeRate(rs.getBigDecimal("employeeRate"));
                    matrix.setEmployerRate(rs.getBigDecimal("employerRate"));
                    matrix.setMaxContribution(rs.getBigDecimal("maxContribution"));
                    return matrix;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}