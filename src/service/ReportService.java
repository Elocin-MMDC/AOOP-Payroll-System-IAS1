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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.MonthlyPayrollSummaryReportViewDAO;
import model.pojo.MonthlyPayrollSummaryReportView;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
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

            // Fill from the DAO so government IDs are decrypted, instead of letting the
            // template read the encrypted columns directly with its own SQL
            JRDataSource rows = new JRMapCollectionDataSource(toReportRows(
                    new MonthlyPayrollSummaryReportViewDAO().getWithinDateRange(payStartDate, payEndDate)));
            JasperPrint jp = buildReport("/report/MonthlyPayrollSummary.jrxml", params, rows);
            view(jp, String.format("Payroll Summary – %s to %s", payStartDate, payEndDate));

        } catch (JRException | IOException ex) {
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

    // Builds and fills a report from a JRXML template using pre-loaded rows
    private JasperPrint buildReport(String jrxmlPath, Map<String, Object> params, JRDataSource rows)
            throws JRException, IOException {

        try (InputStream in = getClass().getResourceAsStream(jrxmlPath)) {
            if (in == null) {
                throw new IOException("JRXML not found: " + jrxmlPath);
            }

            JasperReport rpt = JasperCompileManager.compileReport(in);
            return JasperFillManager.fillReport(rpt, params, rows);
        }
    }

    // Converts decrypted report records into rows keyed by the JRXML field names and types
    private List<Map<String, ?>> toReportRows(List<MonthlyPayrollSummaryReportView> records) {
        List<Map<String, ?>> rows = new ArrayList<>();
        for (MonthlyPayrollSummaryReportView r : records) {
            Map<String, Object> row = new HashMap<>();
            row.put("payStartDate", Date.valueOf(r.getPayStartDate()));
            row.put("payEndDate", Date.valueOf(r.getPayEndDate()));
            row.put("employeeID", r.getEmployeeID());
            row.put("employeeName", r.getEmployeeName());
            row.put("position", r.getPosition());
            row.put("department", r.getDepartment());
            row.put("grossIncome", r.getGrossIncome());
            row.put("sssNumber", r.getSssNumber());
            row.put("sssContribution", r.getSssContribution());
            row.put("philHealthNumber", r.getPhilHealthNumber());
            row.put("philHealthContribution", r.getPhilHealthContribution());
            row.put("pagIbigNumber", r.getPagIbigNumber());
            row.put("pagIbigContribution", r.getPagIbigContribution());
            row.put("tin", r.getTin());
            row.put("withholdingTax", r.getWithholdingTax());
            row.put("netIncome", r.getNetIncome());
            rows.add(row);
        }
        return rows;
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
