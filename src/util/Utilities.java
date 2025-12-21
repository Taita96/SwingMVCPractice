package util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Utilities {

    public static void setBorderBtn(JButton btn,Color color,boolean contentAreaFilled) {
        LineBorder lineBorder = new LineBorder(color, 2, true);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 40, 5, 40);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        btn.setBorder(compoundBorder);


        //btn.setFocusPainted(true);
        btn.setContentAreaFilled(contentAreaFilled);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void setBorderTxtField(JTextField txt,Color color){
        LineBorder lineBorder = new LineBorder(color, 1, true);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 40);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        txt.setBorder(compoundBorder);
    }

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,}$";
        return regex.matches(regex);
    }

//    public static String chooseLogin(String login){
//
//        if()
//
//    }

    public static void showErrorAlert(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningAlert(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Aviso",JOptionPane.WARNING_MESSAGE);
    }

    public static void showInfoAlert(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Información",JOptionPane.INFORMATION_MESSAGE);
    }
}
