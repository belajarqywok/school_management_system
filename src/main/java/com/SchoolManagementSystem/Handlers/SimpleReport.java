/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SchoolManagementSystem.Handlers;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.Styles;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.jasper.builder.export.Exporters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleReport {
    public static void main(String[] args) {
        try {
            
            Connection connection = getConnection();

            DynamicReports.report()
                .setTemplate(DynamicReports.template().setColumnTitleStyle(Styles.style().setBold(true)))
                .columns(
                    Columns.column("Hari", "day_of_week", DynamicReports.type.stringType()),
                    Columns.column("Waktu Mulai", "time_start", DynamicReports.type.timeHourToSecondType()),
                    Columns.column("Waktu Selesai", "time_end", DynamicReports.type.timeHourToSecondType()),
                    Columns.column("ID Kelas", "class_id", DynamicReports.type.stringType()),
                    Columns.column("ID Mata Pelajaran", "subject_id", DynamicReports.type.stringType()),
                    Columns.column("ID Guru", "employee_id", DynamicReports.type.stringType())
                )
                .title(
                    Components.text("Jadwal Pelajaran")
                        .setStyle(Styles.style().setBold(true).setFontSize(18))
                )
                .pageFooter(Components.pageXofY())
                .setDataSource("SELECT day_of_week, time_start, time_end, class_id, subject_id, employee_id FROM subject_schedules", connection)
                .toPdf(Exporters.pdfExporter("JadwalPelajaran.pdf"))
                .show();

            connection.close();
        } catch (DRException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/school_management_system_db";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


