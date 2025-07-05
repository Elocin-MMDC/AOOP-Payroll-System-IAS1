package service;

import db.DBConnection;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import util.AccessControlUtil;

public class ReportService {

    // Displays the payslip report for the specified payslip ID
    public void generatePayslip(int payslipID) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("payslipID", payslipID);

            JasperPrint jp = buildReport("/report/Payslip.jrxml", params);
            view(jp, "Payslip – ID " + payslipID);

        } catch (JRException | SQLException | IOException ex) {
            System.err.println("[ERROR] Failed to generate payslip report:");
            System.err.println("Reason: " + ex.getMessage());

        } catch (Exception ex) {
            System.err.println("[ERROR] Unexpected error:");
            ex.printStackTrace();
        }
    }

    // Displays the payroll summary report for the given date range
    public void generateMonthlyPayrollSummary(LocalDate payStartDate, LocalDate payEndDate) {
        AccessControlUtil.requireRole("Finance Admin");
        
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("payStartDate", Date.valueOf(payStartDate));
            params.put("payEndDate", Date.valueOf(payEndDate));

            JasperPrint jp = buildReport("/report/MonthlyPayrollSummary.jrxml", params);
            view(jp, String.format("Payroll Summary – %s to %s", payStartDate, payEndDate));
            
        } catch (JRException | SQLException | IOException ex) {
            System.err.println("[ERROR] Failed to generate payroll summary:");
            System.err.println("Reason: " + ex.getMessage());
            
        } catch (Exception ex) {
            System.err.println("[ERROR] Unexpected error:");
            ex.printStackTrace();
        }
    }
    
    // Builds and fills a report from a JRXML template using DB connection and parameters
    private JasperPrint buildReport(String jrxmlPath, Map<String, Object> params)
            throws JRException, SQLException, IOException {

        try (Connection conn = DBConnection.getConnection();
             InputStream in = getClass().getResourceAsStream(jrxmlPath)) {

            if (in == null) {
                throw new IOException("JRXML not found: " + jrxmlPath);
            }

            JasperReport rpt = JasperCompileManager.compileReport(in);
            return JasperFillManager.fillReport(rpt, params, conn);
        }
    }

    // Opens the JasperViewer for a filled report
    private void view(JasperPrint jp, String title) throws JRException {
        if (jp != null && jp.getPages() != null && !jp.getPages().isEmpty()) {
            if (GraphicsEnvironment.isHeadless()) {
                System.out.println("   (Headless mode – skipping JasperViewer)");
                return;
            }
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle(title);
            viewer.setVisible(true);
        } else {
            System.out.println("No pages to display in: " + title);
        }
    }
    
}
