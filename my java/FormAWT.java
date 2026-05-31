import java.awt.*;

public class FormAWT {
    public static void main(String[] args) {
        Frame f = new Frame("Form");

        Label l = new Label("Name:");
        TextField t = new TextField();
        Checkbox c = new Checkbox("Male");
        Choice ch = new Choice();
        Button b = new Button("Submit");

        ch.add("BCA"); ch.add("MCA");

        l.setBounds(50,50,80,30);
        t.setBounds(140,50,100,30);
        c.setBounds(50,100,80,30);
        ch.setBounds(50,150,100,30);
        b.setBounds(50,200,80,30);

        f.add(l); f.add(t); f.add(c); f.add(ch); f.add(b);

        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
    }
}