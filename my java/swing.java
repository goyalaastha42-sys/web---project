import javax.swing.*;
import java.awt.event.*;

public class swing {
    public static void main(String[] args) {

        JFrame f = new JFrame("Calc");
        JTextField t = new JTextField();
        t.setBounds(20,20,200,30);

        JButton b1 = new JButton("+");
        JButton b2 = new JButton("-");

        b1.setBounds(20,60,50,30);
        b2.setBounds(80,60,50,30);

        f.add(t);
        f.add(b1);
        f.add(b2);

        f.setSize(250,150);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        b1.addActionListener(e -> {
            String[] s = t.getText().split("\\+");
            t.setText("" + (Integer.parseInt(s[0]) + Integer.parseInt(s[1])));
        });

        b2.addActionListener(e -> {
            String[] s = t.getText().split("-");
            t.setText("" + (Integer.parseInt(s[0]) - Integer.parseInt(s[1])));
        });
    }
}