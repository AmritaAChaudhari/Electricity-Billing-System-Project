package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyEditorSupport;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {

    JLabel nameText, addressText;
    TextField unitText;
    Choice meterNumChoice, monthChoice;
    JButton submit, cancel;


    calculate_bill() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214, 195, 247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70, 10, 300, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(50, 80, 100, 20);
        panel.add(meternum);


        meterNumChoice = new Choice();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while (resultSet.next()) {
                meterNumChoice.add(resultSet.getString("meter_no"));
            }


        } catch (Exception E) {
            E.printStackTrace();
        }


        meterNumChoice.setBounds(180, 80, 100, 20);
        panel.add(meterNumChoice);


        JLabel name = new JLabel("Name");
        name.setBounds(50, 120, 100, 20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180, 120, 150, 20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);


        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no='" + meterNumChoice.getSelectedItem() + "'");
            while (resultSet.next()) {
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));

            }

        } catch (Exception E) {
            E.printStackTrace();
        }

        meterNumChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no='" + meterNumChoice.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));

                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });


        JLabel unitConsumed = new JLabel("Unit Consumed");
        unitConsumed.setBounds(50, 200, 100, 20);
        panel.add(unitConsumed);

        unitText = new TextField();
        unitText.setBounds(180, 200, 150, 20);
        panel.add(unitText);


        JLabel month = new JLabel("Month");
        month.setBounds(50, 240, 100, 20);
        panel.add(month);


        monthChoice = new Choice();
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");
        monthChoice.setBounds(180, 240, 150, 20);
        panel.add(monthChoice);


        submit = new JButton("Submit");
        submit.setBounds(80, 300, 100, 25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220, 300, 100, 25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel, "Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imgLabel = new JLabel(imageIcon1);
        add(imgLabel, "East");

        setSize(650, 400);
        setLocation(400, 200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == submit) {


            String smeterNo = meterNumChoice.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthChoice.getSelectedItem();


            int totalBill = 0;
            int units = Integer.parseInt(sunit);
            String query_tax = "select * from tax";

            try {
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_tax);
                while (resultSet.next()) {
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));


                }
            } catch (Exception E) {
                E.printStackTrace();
            }
            String query_total_bill = "insert into bill values('"+smeterNo+ "','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";
            try{

                database c=new database();
                c.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                setVisible(false);
            }

            catch (Exception E){
                E.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill();

    }
}
