package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocChoice, meterTypeChoice, phaseCodeChoice, billTypeChoice;
    JButton submit;
    String meternumber;

    meterInfo(String meternumber) {
        this.meternumber = meternumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);


        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50, 80, 100, 20);
        panel.add(meterNumber);

        JLabel meterNumberText = new JLabel(meternumber);
        meterNumberText.setBounds(180, 80, 150, 20);
        panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Location");
        meterLoc.setBounds(50, 120, 100, 20);
        panel.add(meterLoc);

        meterLocChoice = new Choice();
        meterLocChoice.add("Outside");
        meterLocChoice.add("Inside");
        meterLocChoice.setBounds(180, 120, 150, 20);
        panel.add(meterLocChoice);

        JLabel metertype = new JLabel("Meter Type");
        metertype.setBounds(50, 160, 100, 20);
        panel.add(metertype);

        meterTypeChoice = new Choice();
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        meterTypeChoice.setBounds(180, 160, 150, 20);
        panel.add(meterTypeChoice);


        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50, 200, 100, 20);
        panel.add(phaseCode);


        phaseCodeChoice = new Choice();
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        phaseCodeChoice.setBounds(180, 200, 150, 20);
        panel.add(phaseCodeChoice);


        JLabel billType = new JLabel("Bill Type");
        billType.setBounds(50, 240, 100, 20);
        panel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        billTypeChoice.setBounds(180, 240, 150, 20);
        panel.add(billTypeChoice);


        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50, 280, 150, 20);
        panel.add(day);


        JLabel note = new JLabel("Note:-");
        note.setBounds(50, 320, 100, 20);
        panel.add(note);


        JLabel note1 = new JLabel("By default bill is calculated for 30 days only");
        note1.setBounds(50, 340, 300, 20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220, 390, 100, 25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);


        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, "East");


        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
if(e.getSource()==submit){
    String smeterNum=meternumber;
    String smeterLoc=meterLocChoice.getSelectedItem();
    String smetertyp=meterTypeChoice.getSelectedItem();
    String sphaseCode=phaseCodeChoice.getSelectedItem();
    String sbillTyp=billTypeChoice.getSelectedItem();
    String sday="30";

    String query_meterInfo="insert into meter_info values('"+smeterNum+"','"+smeterLoc+"','"+smetertyp+"','"+sphaseCode+"','"+sbillTyp+"','"+sday+"')";
    try{
database c=new database();
c.statement.executeUpdate(query_meterInfo);
JOptionPane.showMessageDialog(null,"Meter information submitted successfully");
setVisible(false);

    }
    catch (Exception E){
        E.printStackTrace();
    }
}else{
    setVisible(false);
}
    }

    public static void main(String[] args) {
        new meterInfo("");

    }
}
