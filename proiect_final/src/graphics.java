import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;

public class graphics {
   // private static final long serialVersionUID = 1L;
    //a subclass for every menu
    int variable=0,y=500,x=700;
    JFrame f=new JFrame();
    JButton buton1;
    JButton buton2;
    JButton buton3;
    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JDialog dialog;
    JLabel label1;
    JComponent panel=(JComponent)f.getContentPane();
    //2nd menu
    void clear() {
        panel.removeAll();
        panel.revalidate();
        f.repaint(0,0,0,y,x);
        f.setLayout(null);
        f.setVisible(true);
    }
    void update() {
        f.repaint(0,0,0,y,x);
        f.setLayout(null);
        f.setVisible(true);
    }
    void menu6()
    {
        clear();
        label1=new JLabel("Rezervare inregistrata");
        update();
    }
    public void menu5(ArrayList<String> aux1,ArrayList<Integer> aux2,ArrayList<Integer> aux3,Query q)
    {
        clear();
        JLabel[] scris=new JLabel[aux1.size()];
        for(int i=0;i<aux1.size();i++)
        {
            scris[i]=new JLabel();
            scris[i].setText(String.format("%d. Camera:%s, Price:%d, Capacity:%d",i,aux1.get(i),aux2.get(i),aux3.get(i)));
            scris[i].setBounds(x/4,i*y/20+30,200,20);
            panel.add(scris[i]);
        }
        buton1=new JButton("Gata");
        buton1.setBounds(6*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Random ceva=new Random();
                        Client p=new Client(null,null,null,ceva.nextInt());
                        Reservation d=new Reservation(aux1.get(Integer.parseInt(text1.getText())),aux2.get(Integer.parseInt(text1.getText())),q.get_checkin(),q.get_checkout(),p.get_idClient(),ceva.nextInt());
                        menu3(p,d);
                    }
                }
        );
        text1=new JTextField("Optiunea aleasa");
        text1.setBounds(x / 8, 16 * y / 20, x / 8, 30);
        panel.add(text1);
        panel.add(buton1);
        update();
    }
    void menu4()
    {
        clear();
        label1=new JLabel("Perioada");
        text2=new JTextField("Numar persoane");
        text2.setBounds(x/4,2*y/8,200,20);
        text3=new JTextField("Check-in");
        text3.setBounds(x/4,3*y/8,200,20);
        text4=new JTextField("Check-out");
        text4.setBounds(x/4,4*y/8,200,20);
        buton1=new JButton("Gata");
        buton1.setBounds(6*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Query q=new Query();
                        q.set_checkin(text3.getText());
                        q.set_checkout(text4.getText());
                        q.set_pers(Integer.parseInt(text2.getText()));
                        q.timeFrameCheck();
                    }
                }
        );
        panel.add(label1);
        panel.add(text2);
        panel.add(text3);
        panel.add(text4);
        panel.add(buton1);
        update();
    }
    void menu3(Client p,Reservation d)
    {
        clear();
        label1=new JLabel("Date client");
        text1=new JTextField("Nume client");
        text1.setBounds(x/4,y/8,200,20);
        text2=new JTextField("Prenume client");
        text2.setBounds(x/4,2*y/8,200,20);
        text3=new JTextField("Telefon client");
        text3.setBounds(x/4,3*y/8,200,20);
        buton1=new JButton("Gata");
        buton1.setBounds(6*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        p.set_nume(text1.getText());
                        p.set_prenume(text2.getText());
                        p.set_telefon(text3.getText());
                        Entry c=new Entry();
                        c.insertClient(p);
                        c.insertReservation(d);
                        menu6();
                    }
                }
        );
        panel.add(label1);
        panel.add(text1);
        panel.add(text2);
        panel.add(text3);
        panel.add(buton1);
        update();
    }
    void menu2(){
        clear();
        buton1 = new JButton();
        buton1.setText("Adaugare");
        buton1.setBounds(2*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu4();
                    }
                }
        );
        buton2 = new JButton();
        buton2.setText("Interogare");
        buton2.setBounds(4*x / 8, y / 2, x / 8, 30);
        buton2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu2();
                    }
                }
        );
        f.add(buton1);
        f.add(buton2);
        f.add(label1);
        update();
    }
    //1st menu
    public void menu1()
    {
        label1=new JLabel("Alegeti o optiune");
        label1.setBounds(x/4,y/4,100,40);
        //option1
        buton1 = new JButton();
        buton1.setText("Rezervari");
        buton1.setBounds(2*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu2();
                    }
                }
        );
        buton2 = new JButton();
        buton2.setText("Statistici");
        buton2.setBounds(4*x / 8, y / 2, x / 8, 30);
        buton2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu2();
                    }
                }
        );
        panel.add(buton1);
        panel.add(buton2);
        panel.add(label1);
        update();
    }
    public graphics()
    {
        //frame
        f.setTitle("Hotel Organiser");
        f.setLayout(new FlowLayout(FlowLayout.CENTER));
        f.setSize(x, y);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}
