/*import javax.swing.*;
import java.awt.event.*;
public class MenuExample implements ActionListener{
    JFrame f;
    JMenuBar mb;
    JMenu file, edit, help;
    JMenuItem cut, copy, paste, selectAll;
    JTextArea ta;
    MenuExample(){
        f=new JFrame();
        cut=new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        mb =new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        mb.add(file);mb.add(edit);mb.add(help);
        ta = new JTextArea();
        ta.setBounds(5,5,360,320);
        f.add(mb);f.add(ta);
        f.setJMenuBar(mb);
        f.setLayout(null);
        f.setSize(400,400);
        f.setVisible(true);}
        public void actionPerformed(ActionEvent e){
            if( e.getSource()==cut)
            ta.cut();
           if(e.getSource()==paste)
            ta.paste();
           if(e.getSource()==selectAll)
            ta.selectAll();}
          public static void main(String[]args){
            new MenuExample();
          }
        
*/
/* 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TrafficLightSimulator extends JFrame implements ItemListener {

    JLabel lbl1, lbl2;
    JPanel nPanel, cPanel;
    CheckboxGroup cbg;
    Checkbox rbn1, rbn2, rbn3;

    public TrafficLightSimulator() {

        setTitle("Traffic Light Simulator");
        setSize(600, 400);
        setLayout(new GridLayout(2, 1));

        nPanel = new JPanel(new FlowLayout());
        cPanel = new JPanel(new FlowLayout());

        // Font
        Font font = new Font("Verdana", Font.BOLD, 20);

        // Top Label
        lbl1 = new JLabel("Select a Light");
        lbl1.setFont(font);
        nPanel.add(lbl1);
        add(nPanel);

        // Control Panel
        lbl2 = new JLabel("Select Lights");
        lbl2.setFont(font);
        cPanel.add(lbl2);

        cbg = new CheckboxGroup();

        rbn1 = new Checkbox("Red Light", cbg, false);
        rbn2 = new Checkbox("Yellow Light", cbg, false);
        rbn3 = new Checkbox("Green Light", cbg, false);

        rbn1.addItemListener(this);
        rbn2.addItemListener(this);
        rbn3.addItemListener(this);

        cPanel.add(rbn1);
        cPanel.add(rbn2);
        cPanel.add(rbn3);

        add(cPanel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) {
        Checkbox chk = cbg.getSelectedCheckbox();
        String str = chk.getLabel();

        lbl1.setText(str + " is ON");

        if (str.equals("Red Light")) {
            nPanel.setBackground(Color.RED);
        } else if (str.equals("Yellow Light")) {
            nPanel.setBackground(Color.YELLOW);
        } else if (str.equals("Green Light")) {
            nPanel.setBackground(Color.GREEN);
        }
    }

    public static void main(String[] args) {
        new TrafficLightSimulator();
    }
}*/
