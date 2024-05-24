package com.SchoolManagementSystem.Views;

import javax.swing.table.DefaultTableModel;

import java.util.List;
import javax.swing.JOptionPane;

import com.SchoolManagementSystem.Dtos.RoomDataDto;
import com.SchoolManagementSystem.Helpers.CachingService;
import com.SchoolManagementSystem.Repositories.RoomRepositories;
import java.util.Map;

public class RoomDataView extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    
    // Room Repositories
    private final RoomRepositories roomRepositories =
        new RoomRepositories();
    
    // Room DTOs
    private RoomDataDto roomDataDto = 
        new RoomDataDto();
    
    public RoomDataView() {
        initComponents();
        TableHandler("default", null);
    }
    
    private void TableHandler(String mode, String search) {
        tableModel = new DefaultTableModel();
        RoomTable.setModel(tableModel);
        
        String[] columnNames = {
            "Kode Ruangan",
            "Nama Ruangan",
            "Lokasi Ruangan",
            "Luas Ruangan",
            "Kapasaitas Ruangan",
            "Harga Ruangan",
            "Catatan Ruangan"
        };
        
        tableModel.setColumnIdentifiers(columnNames);
        
        List<RoomDataDto> roomDataList;
        if (mode.equals("searching")) {
            roomDataList = roomRepositories
                .searchRoomsRepository(search);
        } else {
            roomDataList = roomRepositories
                .getRoomsRepository();
        }
        
        for (RoomDataDto roomData : roomDataList) {
            String roomCode       = roomData.getRoomCode();
            String roomName       = roomData.getRoomName();
            String roomLocation   = roomData.getRoomLocation();
            int    roomWide       = roomData.getRoomWide();
            int    roomCapacity   = roomData.getRoomCapacity();
            int    roomPrice      = roomData.getRoomPrice();
            String roomNote       = roomData.getRoomNote();
            
            tableModel.addRow(new Object[]{
                roomCode, roomName, roomLocation,
                roomWide, roomCapacity, roomPrice,roomNote
            });
        }
    }
    
    private void postAction(boolean condition, String message) {
        roomDataDto = new RoomDataDto();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        NameRoomLabel = new javax.swing.JLabel();
        NameRoomTextField = new javax.swing.JTextField();
        WideRoomLabel = new javax.swing.JLabel();
        WideRoomTextField = new javax.swing.JTextField();
        CapacityRoomTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PriceRoomTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LocationRoomTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        NoteRoomTextArea = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        RoomTable = new javax.swing.JTable();
        SearchTextField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Ruangan");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        NameRoomLabel.setBackground(new java.awt.Color(0, 0, 0));
        NameRoomLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameRoomLabel.setForeground(new java.awt.Color(255, 255, 255));
        NameRoomLabel.setText("Nama Ruangan");

        NameRoomTextField.setText("Masukan Nama Ruangan...");
        NameRoomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameRoomTextFieldActionPerformed(evt);
            }
        });

        WideRoomLabel.setBackground(new java.awt.Color(0, 0, 0));
        WideRoomLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        WideRoomLabel.setForeground(new java.awt.Color(255, 255, 255));
        WideRoomLabel.setText("Luas Ruangan");

        WideRoomTextField.setText("Masukan Luas Ruangan...");
        WideRoomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WideRoomTextFieldActionPerformed(evt);
            }
        });

        CapacityRoomTextField.setText("Masukan Kap Ruangan...");
        CapacityRoomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapacityRoomTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kapasitas Ruangan");

        PriceRoomTextField.setText("Masukan Harga Ruangan...");
        PriceRoomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceRoomTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga Ruangan");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lokasi Ruangan");

        LocationRoomTextArea.setColumns(20);
        LocationRoomTextArea.setRows(5);
        LocationRoomTextArea.setText("Masukan Lokasi Ruangan....");
        jScrollPane1.setViewportView(LocationRoomTextArea);

        NoteRoomTextArea.setColumns(20);
        NoteRoomTextArea.setRows(5);
        NoteRoomTextArea.setText("Masukan Catatan Ruangan....");
        jScrollPane2.setViewportView(NoteRoomTextArea);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Catatan Ruangan");

        RoomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Ruangan", "Nama Ruangan", "Lokasi Ruangan", "Luas Ruangan", "Kapasitas Ruangan", "Harga Ruangan", "Catatan Ruangan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        RoomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(RoomTable);

        SearchTextField.setText("Cari Ruangan...");
        SearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTextFieldActionPerformed(evt);
            }
        });

        SearchButton.setText("Cari");
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchButtonMouseClicked(evt);
            }
        });

        AddButton.setText("Tambah");
        AddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddButtonMouseClicked(evt);
            }
        });

        ResetButton.setText("Reset");
        ResetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetButtonMouseClicked(evt);
            }
        });

        DeleteButton.setText("Hapus");
        DeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteButtonMouseClicked(evt);
            }
        });

        EditButton.setText("Edit");
        EditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(180, 180, 180))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                .addComponent(NameRoomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(NameRoomTextField))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(WideRoomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(WideRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PriceRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CapacityRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(EditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(NameRoomLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NameRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PriceRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CapacityRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(WideRoomLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(WideRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditButton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(DeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameRoomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameRoomTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameRoomTextFieldActionPerformed

    private void WideRoomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WideRoomTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WideRoomTextFieldActionPerformed

    private void CapacityRoomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapacityRoomTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapacityRoomTextFieldActionPerformed

    private void PriceRoomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceRoomTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceRoomTextFieldActionPerformed

    private void SearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTextFieldActionPerformed

    private void RoomTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomTableMouseClicked
        int selectedRow = RoomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane
                .showMessageDialog(this, 
                "Pilih baris yang ingin diupdate.");
        }
        
        CachingService.put("codeRoom", 
            (String) tableModel.getValueAt(selectedRow, 0));
        NameRoomTextField.setText(
            (String) tableModel.getValueAt(selectedRow, 1));
        LocationRoomTextArea.setText(
            (String) tableModel.getValueAt(selectedRow, 2));
        WideRoomTextField.setText(Integer.toString(
            (int) tableModel.getValueAt(selectedRow, 3)));
        CapacityRoomTextField.setText(Integer.toString(
            (int) tableModel.getValueAt(selectedRow, 4)));
        PriceRoomTextField.setText(Integer.toString(
            (int) tableModel.getValueAt(selectedRow, 5)));
        NoteRoomTextArea.setText(
            (String) tableModel.getValueAt(selectedRow, 6));
    }//GEN-LAST:event_RoomTableMouseClicked

    private void AddButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddButtonMouseClicked
        roomDataDto.setRoomName(NameRoomTextField.getText());
        roomDataDto.setRoomLocation(LocationRoomTextArea.getText());
        roomDataDto.setRoomWide(Integer.parseInt(WideRoomTextField.getText()));
        roomDataDto.setRoomCapacity(Integer.parseInt(CapacityRoomTextField.getText()));
        roomDataDto.setRoomPrice(Integer.parseInt(PriceRoomTextField.getText()));
        roomDataDto.setRoomNote(NoteRoomTextArea.getText());
        
        Map<String, Object> createRoom = roomRepositories
            .createRoomRepository(roomDataDto);
        
        postAction(
            (boolean) createRoom.get("result"),
            (String) createRoom.get("message")
        );
    }//GEN-LAST:event_AddButtonMouseClicked

    private void ResetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetButtonMouseClicked
        NameRoomTextField.setText("Masukan Nama Ruangan...");
        LocationRoomTextArea.setText("Masukan Lokasi Ruangan....");
        WideRoomTextField.setText("Masukan Luas Ruangan...");
        CapacityRoomTextField.setText("Masukan Kap Ruangan...");
        PriceRoomTextField.setText("Masukan Harga Ruangan...");
        NoteRoomTextArea.setText("Masukan Catatan Ruangan....");
    }//GEN-LAST:event_ResetButtonMouseClicked

    private void DeleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteButtonMouseClicked
        boolean deleteRoom = roomRepositories
            .deleteRoomRepository((String) CachingService.get("codeRoom"));
        
        postAction(
            deleteRoom,
            "Data Berhasil Di Hapus !!!."
        );
    }//GEN-LAST:event_DeleteButtonMouseClicked

    private void EditButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditButtonMouseClicked
        roomDataDto.setRoomCode((String) CachingService.get("codeRoom"));
        roomDataDto.setRoomName(NameRoomTextField.getText());
        roomDataDto.setRoomLocation(LocationRoomTextArea.getText());
        roomDataDto.setRoomWide(Integer.parseInt(WideRoomTextField.getText()));
        roomDataDto.setRoomCapacity(Integer.parseInt(CapacityRoomTextField.getText()));
        roomDataDto.setRoomPrice(Integer.parseInt(PriceRoomTextField.getText()));
        roomDataDto.setRoomNote(NoteRoomTextArea.getText());
        
        Map<String, Object> updateRoom = roomRepositories
            .updateRoomRepository(roomDataDto);
        
        postAction(
            (boolean) updateRoom.get("result"),
            (String) updateRoom.get("message")
        );
    }//GEN-LAST:event_EditButtonMouseClicked

    private void SearchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchButtonMouseClicked
        TableHandler("searching", SearchTextField.getText());
    }//GEN-LAST:event_SearchButtonMouseClicked

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
            java.util.logging.Logger.getLogger(RoomDataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomDataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomDataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomDataView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomDataView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextField CapacityRoomTextField;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JTextArea LocationRoomTextArea;
    private javax.swing.JLabel NameRoomLabel;
    private javax.swing.JTextField NameRoomTextField;
    private javax.swing.JTextArea NoteRoomTextArea;
    private javax.swing.JTextField PriceRoomTextField;
    private javax.swing.JButton ResetButton;
    private javax.swing.JTable RoomTable;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JLabel WideRoomLabel;
    private javax.swing.JTextField WideRoomTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
