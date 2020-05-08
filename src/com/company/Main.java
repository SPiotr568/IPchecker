package com.company;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class Main extends JFrame implements ActionListener{
    private int windowWidth;
    private int widowHeight;
    private int locationWidth;
    private int locationHeight;
    private JLabel labelMyHostname;
    private JLabel labelMyAddress;
    private JTextField hostnameTextField;
    private JLabel labelAnswer;
    private JButton buttonFindeIP;

    Main(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculateScreensSize();
        labelMyHostname=new JLabel();
        labelMyHostname.setBounds(windowWidth/2 - 250/2,windowWidth/4 - 90, 250,20);
        labelMyAddress=new JLabel();
        labelMyAddress.setBounds(windowWidth/2 - 250/2,windowWidth/4 - 60, 250,20);
        hostnameTextField=new JTextField();
        hostnameTextField.setBounds(windowWidth/4, widowHeight/4,windowWidth/2,20);
        labelAnswer=new JLabel();
        labelAnswer.setBounds(windowWidth/2 - 250/2,windowWidth/4 +100, 250,20);
        buttonFindeIP=new JButton("Find IP");
        buttonFindeIP.setBounds(windowWidth/2 - 95/2,widowHeight/4 + 20,95,30);
        buttonFindeIP.addActionListener(this);
        add(labelMyHostname);
        labelMyHostname.setText(findeMyHostname());
        add(labelMyAddress);
        labelMyAddress.setText(findeMyAddress());
        add(buttonFindeIP);
        add(hostnameTextField);
        add(labelAnswer);
        setSize(windowWidth,widowHeight);
        setLocation(locationWidth,locationHeight);
        setLayout(null);
        setVisible(true);
    }

    public void calculateScreensSize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        windowWidth = 400;
        widowHeight = 300;
        locationWidth = screenSize.width/2 - windowWidth/2;
        locationHeight = screenSize.height/2 - widowHeight/2;
    }

    public String findeMyHostname() {
        try {
            InetAddress me = InetAddress.getLocalHost();
            return "My hostname is " + me.getHostName();
        } catch (Exception ex){
            System.out.println(ex);
            labelAnswer.setText("Error");
        }
        return "";
    }

    public String findeMyAddress() {
        try {
            InetAddress me = InetAddress.getLocalHost();
            return "My local IP address is " + me.getHostAddress();
        } catch (Exception ex){
            System.out.println(ex);
            labelAnswer.setText("Error");
        }
        return "";
    }

    public static String getAddress() {
        byte[] address;
        try {
            InetAddress me = InetAddress.getLocalHost();
            address = me.getAddress();
            for (int i = 0; i < address.length; i++) {
                System.out.print(address[i] + " ");
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
        return "";
    }

    public void actionPerformed(ActionEvent e) {
        try{
            String host=hostnameTextField.getText();
            String ip=java.net.InetAddress.getByName(host).getHostAddress();
            if(host.equals("")){
                host="'loopback'";
            }
            labelAnswer.setText("IP of " + host + " is: " + ip);
        } catch (Exception ex){
            System.out.println(ex);
            labelAnswer.setText("Error");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}