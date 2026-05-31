import java.awt.*;
import java.awt.event.*;

class Calculator extends Frame implements ActionListener {

    TextField t1, t2, t3;
    Button b1, b2, b3, b4;

    Calculator() {

        Label l1 = new Label("First Number");
        Label l2 = new Label("Second Number");
        Label l3 = new Label("Result");

        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();

        b1 = new Button("+");
        b2 = new Button("-");
        b3 = new Button("*");
        b4 = new Button("/");

        setLayout(new FlowLayout());

        add(l1);
        add(t1);

        add(l2);
        add(t2);

        add(l3);
        add(t3);

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        setSize(300,300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int a = Integer.parseInt(t1.getText());
        int b = Integer.parseInt(t2.getText());

        int result = 0;

        if(e.getSource() == b1)
            result = a + b;

        if(e.getSource() == b2)
            result = a - b;

        if(e.getSource() == b3)
            result = a * b;

        if(e.getSource() == b4)
            result = a / b;

        t3.setText(String.valueOf(result));
    }

    public static void main(String args[]) {
        new Calculator();
    }
}