package ui;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

// Main method to instantiate WardrobeSelectorApp
public class Main {
    public static void main(String[] args) {
        JWindow window = new JWindow();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
        window.getContentPane().add(new JLabel("", 
                new ImageIcon("src/res/wardrobe_splash_screen.png"), SwingConstants.CENTER));
        window.setBounds((int)size.getWidth() / 2 - 256, (int)size.getHeight() / 2 - 256, 512, 512);
        window.setVisible(true);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();

        new WardrobeSelectorApp();
    }
}