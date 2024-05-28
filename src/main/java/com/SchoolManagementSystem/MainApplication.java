/**
 *
 * @author Anitaaa
 * @npm 20224350xxx..
 * 
 */

package com.SchoolManagementSystem;

import javax.swing.SwingUtilities;
import com.SchoolManagementSystem.Views.AuthView;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthView authView = new AuthView();
            
            authView.setVisible(true);
            authView.setResizable(false);
            authView.pack();
            authView.setLocationRelativeTo(null);
            authView.setDefaultCloseOperation(authView.EXIT_ON_CLOSE);
        });
    }
}
