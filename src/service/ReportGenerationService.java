/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import db.DBConnection;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author Eldan Eunice Sinsuan
 */
public class ReportGenerationService {
        public void generatePayslipReport(int payslipID) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
             
            // Load the JRXML file
            InputStream jrxmlStream = getClass().getResourceAsStream("/reports/EmployeePayslip.jrxml");
            if (jrxmlStream == null) {
                throw new FileNotFoundException("Could not find EmployeePayslip.jrxml in /reports/");
            }

            // Compile the report from .jrxml
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // Parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("payslipID", payslipID);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            if (jasperPrint.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No data found for Payslip ID: " + payslipID);
            } else {
                JasperViewer.viewReport(jasperPrint, false);
                
                String outputFile = "Payslip_" + payslipID + ".pdf";
                JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
                System.out.println("PDF exported: " + outputFile);
            }
        }
    }
        
        public void generatePayrollSummaryReport(int payMonth, int payYear) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {

            InputStream jrxmlStream = getClass().getResourceAsStream("/reports/PayrollSummaryReport.jrxml");
            if (jrxmlStream == null) {
                throw new FileNotFoundException("Could not find PayrollSummaryReport.jrxml in /reports/");
            }

            // Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("payMonth", payMonth);
            parameters.put("payYear", payYear);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            if (jasperPrint.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No data found for the selected pay period.");
            } else {
                JasperViewer.viewReport(jasperPrint, false);

                String outputFile = "PayrollSummary_" + payYear + "_" + payMonth + ".pdf";
                JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
                System.out.println("PDF exported: " + outputFile);
            }
        }
    }
}
