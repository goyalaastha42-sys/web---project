import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calc extends JFrame implements ActionListener {
    JTextField t = new JTextField();
    double a, b;
    String op;

    Calc() {
        setSize(250, 300);
        setLayout(new BorderLayout());

        add(t, BorderLayout.NORTH);

        JPanel p = new JPanel(new GridLayout(5, 4));
        String btns[] = {"7","8","9","/","4","5","6","*","1","2","3","-","0","%","=","+","C"};

        for (String s : btns) {
            JButton b = new JButton(s);
            b.addActionListener(this);
            p.add(b);
        }

        add(p);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        try {
            if (s.matches("[0-9]"))
                t.setText(t.getText() + s);

            else if (s.matches("[+\\-*/%]")) {
                a = Double.parseDouble(t.getText());
                op = s;
                t.setText("");
            }

            else if (s.equals("=")) {
                b = Double.parseDouble(t.getText());

                if (op.equals("+")) t.setText("" + (a + b));
                else if (op.equals("-")) t.setText("" + (a - b));
                else if (op.equals("*")) t.setText("" + (a * b));
                else if (op.equals("/")) {
                    if (b == 0) throw new ArithmeticException();
                    t.setText("" + (a / b));
                }
                else if (op.equals("%")) t.setText("" + (a % b));
            }

            else if (s.equals("C")) t.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error");
            t.setText("");
        }
    }

    public static void main(String[] args) {
        new Calc();
    }
}*/
    
