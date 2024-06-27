package com.SchoolManagementSystem.Views;

import javax.swing.table.DefaultTableModel;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.SchoolManagementSystem.Views.DashboardView;

import com.SchoolManagementSystem.Dtos.SubjectScheduleDto;
import com.SchoolManagementSystem.Helpers.CachingService;

import com.SchoolManagementSystem.Repositories.SuperRepositories;
import com.SchoolManagementSystem.Repositories.SubjectScheduleRepositories;

import com.SchoolManagementSystem.Reports.SubjectScheduleReport;

public class SubjectScheduleView extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    
    // Super Repositories
    private final SuperRepositories superRepositories = new SuperRepositories();
    
    // Subject Schedule Repositories
    private final SubjectScheduleRepositories subjectScheduleRepositories =
        new SubjectScheduleRepositories();
    
    // Subject Schedule Report
    private final SubjectScheduleReport subjectScheduleReport 
        = new SubjectScheduleReport();
    
    // Subject Schedule DTOs
    private SubjectScheduleDto subjectScheduleDto = new SubjectScheduleDto();
    
    // Subject Schedule IDs
    private ArrayList<String> subjectScheduleIdLists = new ArrayList<>();
    
    private List<String> classNames = superRepositories
        .getClassNameRepository();
    private List<String> subjectNames = superRepositories
        .getSubjectNameRepository();
    private List<String> teacherNames = superRepositories
        .getTeacherNameRepository();
    
    public SubjectScheduleView() {
        initComponents();
        this.setTitle("Jadwal Mata Pelajaran");
        TableHandler("default", null);
        
        ClassComboBoxHandler();
        SubjectComboBoxHandler();
        TeacherComboBoxHandler();
    }
    
    private void ClassComboBoxHandler() {     
        classNames = superRepositories.getClassNameRepository();
        for (String className: classNames) {
            if (SubjectScheduleClassComboBox.getItemCount() >= classNames.size()) {
                SubjectScheduleClassComboBox.removeAllItems();
            }
                
            SubjectScheduleClassComboBox.addItem(className);
        }
    }
    
    private void SubjectComboBoxHandler() {
        subjectNames = superRepositories.getSubjectNameRepository();
        for (String subjectName: subjectNames) {
            if (SubjectScheduleComboBox.getItemCount() >= subjectNames.size()) {
                SubjectScheduleComboBox.removeAllItems();
            }
                
            SubjectScheduleComboBox.addItem(subjectName);
        }
    }
    
    private void TeacherComboBoxHandler() {
        teacherNames = superRepositories.getTeacherNameRepository();
        for (String teacherName: teacherNames) {
            if (SubjectScheduleTeacherComboBox.getItemCount() >= teacherNames.size()) {
                SubjectScheduleTeacherComboBox.removeAllItems();
            }
                
            SubjectScheduleTeacherComboBox.addItem(teacherName);
        }
    }
    
    private void TableHandler(String mode, String search) {
        tableModel = new DefaultTableModel();
        EmployeeTable.setModel(tableModel);
        
        String[] columnNames = {
            "Nomor",
            "Kelas",
            "MaPel",
            "Pengajar",
            "Hari",
            "Mulai",
            "Selesai"
        };
        
        tableModel.setColumnIdentifiers(columnNames);
        
        List<SubjectScheduleDto> subjectScheduleDataList;
        if (mode.equals("searching")) {
            subjectScheduleDataList = subjectScheduleRepositories
                .searchSubjectSchedulesRepository(search);
        } else {
            subjectScheduleDataList = subjectScheduleRepositories
                .getSubjectSchedulesRepository();
        }
        
        int index = 1;
        subjectScheduleIdLists = new ArrayList<>();
        for (SubjectScheduleDto subjectScheduleData : subjectScheduleDataList) {
            String scheduleId        = subjectScheduleData.getScheduleId();
            String className         = subjectScheduleData.getClassName();
            String subjectName       = subjectScheduleData.getSubjectName();
            String teacherName       = subjectScheduleData.getEmployeeName();
            String scheduleDay       = subjectScheduleData.getScheduleDay();
            String scheduleTimeStart = subjectScheduleData.getScheduleTimeStart();
            String scheduleTimeEnd   = subjectScheduleData.getScheduleTimeEnd();
            
            tableModel.addRow(new Object[]{
                index, className, subjectName, teacherName,
                scheduleDay, scheduleTimeStart, scheduleTimeEnd
            });
            
            subjectScheduleIdLists.add(scheduleId);
            index ++;
        }
    }
    
    private void postAction(boolean condition, String message) {
        subjectScheduleDto = new SubjectScheduleDto();
        CachingService.clear();
        TableHandler("default", null);
        
        if (condition) {
            JOptionPane.showMessageDialog(
                this, 
                message,
                "Berhasil...",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                this, 
                message,
                "Gagal...",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NameRoomLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();
        SearchTextField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BackButton = new javax.swing.JLabel();
        NameRoomLabel2 = new javax.swing.JLabel();
        NameRoomLabel3 = new javax.swing.JLabel();
        SubjectScheduleTimeStartTextField = new javax.swing.JTextField();
        NameRoomLabel6 = new javax.swing.JLabel();
        SubjectScheduleComboBox = new javax.swing.JComboBox<>();
        SubjectScheduleTimeEndTextField = new javax.swing.JTextField();
        NameRoomLabel7 = new javax.swing.JLabel();
        SubjectScheduleTeacherComboBox = new javax.swing.JComboBox<>();
        NameRoomLabel4 = new javax.swing.JLabel();
        SubjectScheduleDayComboBox = new javax.swing.JComboBox<>();
        SubjectScheduleClassComboBox = new javax.swing.JComboBox<>();
        PrintButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        NameRoomLabel.setBackground(new java.awt.Color(255, 255, 255));
        NameRoomLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel.setForeground(new java.awt.Color(86, 85, 191));
        NameRoomLabel.setText("Kelas");

        EmployeeTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nomor", "Kelas", "MaPel", "Pengajar", "Hari", "Mulai", "Selesai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        EmployeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmployeeTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(EmployeeTable);

        SearchTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SearchTextField.setForeground(new java.awt.Color(86, 85, 191));
        SearchTextField.setText("  Cari Jadwal Mata Pelajaran ...");
        SearchTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(86, 85, 191), 2, true));
        SearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTextFieldActionPerformed(evt);
            }
        });

        SearchButton.setBackground(new java.awt.Color(86, 85, 191));
        SearchButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SearchButton.setForeground(new java.awt.Color(255, 255, 255));
        SearchButton.setText("Cari");
        SearchButton.setBorder(null);
        SearchButton.setBorderPainted(false);
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchButtonMouseClicked(evt);
            }
        });

        AddButton.setBackground(new java.awt.Color(86, 85, 191));
        AddButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddButton.setForeground(new java.awt.Color(255, 255, 255));
        AddButton.setText("Tambah");
        AddButton.setBorder(null);
        AddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddButtonMouseClicked(evt);
            }
        });

        ResetButton.setBackground(new java.awt.Color(86, 85, 191));
        ResetButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ResetButton.setForeground(new java.awt.Color(255, 255, 255));
        ResetButton.setText("Reset");
        ResetButton.setBorder(null);
        ResetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetButtonMouseClicked(evt);
            }
        });

        DeleteButton.setBackground(new java.awt.Color(86, 85, 191));
        DeleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        DeleteButton.setText("Hapus");
        DeleteButton.setBorder(null);
        DeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteButtonMouseClicked(evt);
            }
        });

        EditButton.setBackground(new java.awt.Color(86, 85, 191));
        EditButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EditButton.setForeground(new java.awt.Color(255, 255, 255));
        EditButton.setText("Edit");
        EditButton.setBorder(null);
        EditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditButtonMouseClicked(evt);
            }
        });
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(86, 85, 191));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jadwal Mata Pelajaran");

        BackButton.setBackground(new java.awt.Color(255, 255, 255));
        BackButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BackButton.setForeground(new java.awt.Color(255, 255, 255));
        BackButton.setText("  Kembali  ");
        BackButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        BackButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BackButton)
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        NameRoomLabel2.setBackground(new java.awt.Color(255, 255, 255));
        NameRoomLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel2.setForeground(new java.awt.Color(86, 85, 191));
        NameRoomLabel2.setText("Hari");

        NameRoomLabel3.setBackground(new java.awt.Color(255, 255, 255));
        NameRoomLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel3.setForeground(new java.awt.Color(86, 85, 191));
        NameRoomLabel3.setText("Mata Pelajaran");

        SubjectScheduleTimeStartTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SubjectScheduleTimeStartTextField.setForeground(new java.awt.Color(86, 85, 191));
        SubjectScheduleTimeStartTextField.setText("  Masukan Jam Mulai ...");
        SubjectScheduleTimeStartTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(86, 85, 191), 2, true));
        SubjectScheduleTimeStartTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SubjectScheduleTimeStartTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectScheduleTimeStartTextFieldActionPerformed(evt);
            }
        });

        NameRoomLabel6.setBackground(new java.awt.Color(255, 255, 255));
        NameRoomLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel6.setForeground(new java.awt.Color(86, 85, 191));
        NameRoomLabel6.setText("Jam Mulai (mis: 09:00:00)");

        SubjectScheduleComboBox.setBorder(null);
        SubjectScheduleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectScheduleComboBoxActionPerformed(evt);
            }
        });

        SubjectScheduleTimeEndTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SubjectScheduleTimeEndTextField.setForeground(new java.awt.Color(86, 85, 191));
        SubjectScheduleTimeEndTextField.setText("  Masukan Jam Selesai ...");
        SubjectScheduleTimeEndTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(86, 85, 191), 2, true));
        SubjectScheduleTimeEndTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SubjectScheduleTimeEndTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectScheduleTimeEndTextFieldActionPerformed(evt);
            }
        });

        NameRoomLabel7.setBackground(new java.awt.Color(255, 255, 255));
        NameRoomLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel7.setForeground(new java.awt.Color(86, 85, 191));
        NameRoomLabel7.setText("Jam Selesai (mis: 12:00:00)");

        SubjectScheduleTeacherComboBox.setBorder(null);

        NameRoomLabel4.setBackground(new java.awt.Color(255, 255, 255));
        NameRoomLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel4.setForeground(new java.awt.Color(86, 85, 191));
        NameRoomLabel4.setText("Pengajar");

        SubjectScheduleDayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu" }));
        SubjectScheduleDayComboBox.setBorder(null);
        SubjectScheduleDayComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectScheduleDayComboBoxActionPerformed(evt);
            }
        });

        SubjectScheduleClassComboBox.setBorder(null);
        SubjectScheduleClassComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectScheduleClassComboBoxActionPerformed(evt);
            }
        });

        PrintButton.setBackground(new java.awt.Color(86, 85, 191));
        PrintButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PrintButton.setForeground(new java.awt.Color(255, 255, 255));
        PrintButton.setText("Cetak");
        PrintButton.setBorder(null);
        PrintButton.setBorderPainted(false);
        PrintButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameRoomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameRoomLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubjectScheduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameRoomLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubjectScheduleTeacherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubjectScheduleClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SubjectScheduleTimeEndTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NameRoomLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NameRoomLabel6)
                                    .addComponent(SubjectScheduleDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 21, Short.MAX_VALUE))
                            .addComponent(SubjectScheduleTimeStartTextField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(NameRoomLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NameRoomLabel)
                            .addComponent(NameRoomLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SubjectScheduleDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SubjectScheduleClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(NameRoomLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectScheduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(NameRoomLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectScheduleTimeStartTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NameRoomLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectScheduleTimeEndTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NameRoomLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubjectScheduleTeacherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(221, 221, 221)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(EditButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTextFieldActionPerformed

    private void EmployeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmployeeTableMouseClicked
        int selectedRow = EmployeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane
                .showMessageDialog(this, 
                "Pilih baris yang ingin diupdate.");
        }
        
        CachingService.put("scheduleId", subjectScheduleIdLists.get(
                (int) tableModel.getValueAt(selectedRow, 0) - 1
            )
        );
        
        SubjectScheduleClassComboBox.setSelectedItem(
            (Object) tableModel.getValueAt(selectedRow, 1));
        SubjectScheduleComboBox.setSelectedItem(
            (Object) tableModel.getValueAt(selectedRow, 2));
        SubjectScheduleTeacherComboBox.setSelectedItem(
            (Object) tableModel.getValueAt(selectedRow, 3));
        SubjectScheduleDayComboBox.setSelectedItem(
            (Object) tableModel.getValueAt(selectedRow, 4));
        SubjectScheduleTimeStartTextField.setText(
            (String) tableModel.getValueAt(selectedRow, 5));
        SubjectScheduleTimeEndTextField.setText(
            (String) tableModel.getValueAt(selectedRow, 6));
    }//GEN-LAST:event_EmployeeTableMouseClicked

    private void AddButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddButtonMouseClicked
        subjectScheduleDto.setClassName(SubjectScheduleClassComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setSubjectName(SubjectScheduleComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setEmployeeName(SubjectScheduleTeacherComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setScheduleDay(SubjectScheduleDayComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setScheduleTimeStart(
            SubjectScheduleTimeStartTextField.getText());
        subjectScheduleDto.setScheduleTimeEnd(
            SubjectScheduleTimeEndTextField.getText());
        
        Map<String, Object> createSubjectSchedule = subjectScheduleRepositories
            .createSubjectScheduleRepository(subjectScheduleDto);
        
        postAction(
            (boolean) createSubjectSchedule.get("result"),
            (String) createSubjectSchedule.get("message")
        );
    }//GEN-LAST:event_AddButtonMouseClicked

    private void ResetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetButtonMouseClicked
        SubjectScheduleClassComboBox.setSelectedItem(classNames.get(0));
        SubjectScheduleComboBox.setSelectedItem(subjectNames.get(0));
        SubjectScheduleTeacherComboBox.setSelectedItem(teacherNames.get(0));
        
        SubjectScheduleDayComboBox.setSelectedItem((Object) "Senin");
        SubjectScheduleTimeStartTextField.setText("  Masukan Jam Mulai ...");
        SubjectScheduleTimeEndTextField.setText("  Masukan Jam Selesai ...");
        
        SearchTextField.setText("  Cari Jadwal Mata Pelajaran ...");
    }//GEN-LAST:event_ResetButtonMouseClicked

    private void DeleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteButtonMouseClicked
        boolean deleteSubjectSchedule = subjectScheduleRepositories
            .deleteSubjectScheduleRepository((String) CachingService.get("scheduleId"));
        
        postAction(
            deleteSubjectSchedule,
            "Data Berhasil Di Hapus !!!."
        );
    }//GEN-LAST:event_DeleteButtonMouseClicked

    private void EditButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditButtonMouseClicked
        subjectScheduleDto.setScheduleId((String) CachingService.get("scheduleId"));
        
        subjectScheduleDto.setClassName(SubjectScheduleClassComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setSubjectName(SubjectScheduleComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setEmployeeName(SubjectScheduleTeacherComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setScheduleDay(SubjectScheduleDayComboBox
            .getSelectedItem().toString());
        subjectScheduleDto.setScheduleTimeStart(
            SubjectScheduleTimeStartTextField.getText());
        subjectScheduleDto.setScheduleTimeEnd(
            SubjectScheduleTimeEndTextField.getText());
        
        Map<String, Object> updateSubjectSchedule = subjectScheduleRepositories
            .updateSubjectScheduleRepository(subjectScheduleDto);
        
        postAction(
            (boolean) updateSubjectSchedule.get("result"),
            (String) updateSubjectSchedule.get("message")
        );
    }//GEN-LAST:event_EditButtonMouseClicked

    private void SearchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMouseClicked
        TableHandler("searching", SearchTextField.getText());
    }//GEN-LAST:event_SearchButtonMouseClicked

    private void BackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackButtonMouseClicked
        DashboardView dashboardView = new DashboardView();
            
        dashboardView.setVisible(true);
        dashboardView.setResizable(false);
        dashboardView.pack();
        dashboardView.setLocationRelativeTo(null);
        dashboardView.setDefaultCloseOperation(dashboardView.EXIT_ON_CLOSE);

        this.setVisible(false);
    }//GEN-LAST:event_BackButtonMouseClicked

    private void SubjectScheduleTimeStartTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectScheduleTimeStartTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubjectScheduleTimeStartTextFieldActionPerformed

    private void SubjectScheduleTimeEndTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectScheduleTimeEndTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubjectScheduleTimeEndTextFieldActionPerformed

    private void SubjectScheduleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectScheduleComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubjectScheduleComboBoxActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditButtonActionPerformed

    private void SubjectScheduleDayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectScheduleDayComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubjectScheduleDayComboBoxActionPerformed

    private void SubjectScheduleClassComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectScheduleClassComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubjectScheduleClassComboBoxActionPerformed

    private void PrintButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintButtonMouseClicked
        subjectScheduleReport.getScheduleBySearch(SearchTextField.getText());
    }//GEN-LAST:event_PrintButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SubjectScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectScheduleView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubjectScheduleView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JLabel BackButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JLabel NameRoomLabel;
    private javax.swing.JLabel NameRoomLabel2;
    private javax.swing.JLabel NameRoomLabel3;
    private javax.swing.JLabel NameRoomLabel4;
    private javax.swing.JLabel NameRoomLabel6;
    private javax.swing.JLabel NameRoomLabel7;
    private javax.swing.JButton PrintButton;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JComboBox<String> SubjectScheduleClassComboBox;
    private javax.swing.JComboBox<String> SubjectScheduleComboBox;
    private javax.swing.JComboBox<String> SubjectScheduleDayComboBox;
    private javax.swing.JComboBox<String> SubjectScheduleTeacherComboBox;
    private javax.swing.JTextField SubjectScheduleTimeEndTextField;
    private javax.swing.JTextField SubjectScheduleTimeStartTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
