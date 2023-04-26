package electricity.billing.system;

import javax.swing.*;  //swing package
import java.awt.*;

public class Splash extends JFrame {
    Splash() {  //constructor
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Splash.jpg")); //static function
        Image imageOne = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);


        setSize(600, 400);
        setLocation(500, 200); //sets opening location
        setVisible(true);


        try {
            Thread.sleep(3000); //screen will hold for 3 seconds
            setVisible(false);
            new Login();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Splash s = new Splash();  //creating object of class Splash
    }

}
