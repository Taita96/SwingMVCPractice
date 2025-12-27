package util;

import model.entity.enums.City;
import model.entity.enums.Country;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Utilities {

    public static void setBorderBtn(JButton btn,Color color) {
        Color normalBg = btn.getBackground();
        Color hoverBg  = new Color(230, 230, 230);

        LineBorder lineBorder = new LineBorder(color, 2, true);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 40, 5, 40);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        btn.setBorder(compoundBorder);

        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverBg);

                if(btn.getForeground().equals(Color.white)){
                    btn.setForeground(normalBg);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(normalBg);

                if(btn.getForeground().equals(normalBg)){
                    btn.setForeground(Color.white);
                }
            }
        });
    }

    public static void setBorderTxtField(JTextField txt,Color color){
        LineBorder lineBorder = new LineBorder(color, 1, true);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 40);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        txt.setBorder(compoundBorder);
    }

    public static void manageBtn(JButton btn,boolean behavior){
        btn.setEnabled(behavior);
        btn.setVisible(behavior);
    }

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,}$";
        return regex.matches(regex);
    }

    public static void showErrorAlert(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningAlert(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Aviso",JOptionPane.WARNING_MESSAGE);
    }

    public static void showInfoAlert(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Información",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayCard(CardLayout card,JPanel panel,String nameCard) {
        card.show(panel,nameCard);
    }

    public static int confirmMessage(String message,String title) {
        return JOptionPane.showConfirmDialog(null,message
                ,title,JOptionPane.YES_NO_OPTION);
    }

    public static City[] getCitiesByCountry(Country country) {

        ArrayList<City> result = new ArrayList<>();

        for (City city : City.values()) {
            if (city.getCountry() == country) {
                result.add(city);
            }
        }

        return result.toArray(new City[0]);
    }

}
