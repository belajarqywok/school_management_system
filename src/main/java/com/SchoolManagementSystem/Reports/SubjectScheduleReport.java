package com.SchoolManagementSystem.Reports;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.Styles;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.jasper.builder.export.Exporters;

import java.sql.Connection;
import java.sql.SQLException;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;


/**
 *  Subject Schedule Report
 */
public class SubjectScheduleReport extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "subject_schedules";
  
  /**
   * Subject Schedule Report By Search
   *
   * @param key String
   * @return void
   *
   */
  public void getScheduleBySearch(String key) {
    try {
        Connection connection = getConnection();
        String queryString = String.format(
          "SELECT              " +
            "sc.schedule_id,   " +
            "c.class_name,     " +
            "s.subject_name,   " +
            "e.employee_name,  " +
            "sc.day_of_week,   " +
            "sc.time_start,    " +
            "sc.time_end       " +
          "FROM %s sc          " +
            "JOIN class     c ON sc.class_id    = c.class_id    " +
            "JOIN subjects  s ON sc.subject_id  = s.subject_id  " +
            "JOIN employees e ON sc.employee_id = e.employee_id " +
          "WHERE (                               " +
            "c.class_name      LIKE '%%%s%%' OR  " +
            "s.subject_name    LIKE '%%%s%%' OR  " +
            "e.employee_name   LIKE '%%%s%%' OR  " +
            "sc.day_of_week    LIKE '%%%s%%' OR  " +
            "sc.time_start     LIKE '%%%s%%' OR  " +
            "sc.time_end       LIKE '%%%s%%' AND " +
            "e.employee_type = 'Guru'            " +
          ")", this.TABLENAME,
          key, key, key,
          key, key, key
        );
        
        DynamicReports.report()
          .setTemplate(DynamicReports.template().setColumnTitleStyle(Styles.style().setBold(true)))
              .columns(
                  Columns.column("Kelas", "class_name", DynamicReports.type.stringType()),
                  Columns.column("MaPel", "subject_name", DynamicReports.type.stringType()),
                  Columns.column("Guru", "employee_name", DynamicReports.type.stringType()),
                  Columns.column("Hari", "day_of_week", DynamicReports.type.stringType()),
                  Columns.column("Jam Mulai", "time_start", DynamicReports.type.timeHourToSecondType()),
                  Columns.column("Jam Selesai", "time_end", DynamicReports.type.timeHourToSecondType())
              )
              .title(
                  Components.text("Jadwal Pelajaran")
                      .setStyle(Styles.style().setBold(true).setFontSize(18))
              )
              .pageFooter(Components.pageXofY())
              .setDataSource(queryString, connection)
              .toPdf(Exporters.pdfExporter("JadwalPelajaran.pdf"))
              .show();

    } catch (DRException exception) {
        exception.printStackTrace();
    }
  }
}
