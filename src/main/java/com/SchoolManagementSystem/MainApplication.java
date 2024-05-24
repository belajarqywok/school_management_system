/**
 *
 * @author al-fariqy raihan azhwar
 * @npm 202143501514
 * 
 */

package com.SchoolManagementSystem;

import javax.swing.SwingUtilities;
import com.SchoolManagementSystem.Views.RoomDataView;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RoomDataView roomDataView = new RoomDataView();
            
            roomDataView.setVisible(true);
            roomDataView.setResizable(false);
            roomDataView.pack();
            roomDataView.setLocationRelativeTo(null);
            roomDataView.setDefaultCloseOperation(roomDataView.EXIT_ON_CLOSE);
            
        });
    }
}
