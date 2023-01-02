import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.SqlDateModel;
import org.jdatepicker.UtilCalendarModel;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class graphics {
   // private static final long serialVersionUID = 1L;
    //a subclass for every menu
    int variable=0,y=500,x=700;
    JFrame f=new JFrame();
    JButton buton1;
    JButton buton2;
    JButton buton3;
    JButton buton4;
    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JDialog dialog;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JComponent panel;
    JRadioButton radio1;
    JRadioButton radio2;
    ButtonGroup G1;
    void clear() {
        panel.removeAll();
        panel.revalidate();
        f.repaint(0,0,0,y,x);
        f.setLayout(null);
        f.setVisible(true);
    }
    void update() {
        f.repaint(0,0,0,y,x);
        f.repaint(0,x/2,y/2,y,x);
        f.setLayout(null);
        f.setVisible(true);
    }
    private DefaultCategoryDataset createDataset( ) {
        Query q=new Query();
        ArrayList<Integer> aux=new ArrayList<>();
        q.occupationbymonth(1,aux);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(int i=0;i<30;i++)
        dataset.addValue( aux.get(i) , "rooms" , String.valueOf(i) );
        return dataset;
    }
    void menu8()
    {
        clear();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "ceva",
                "rooms","days",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setBounds( 10,10,600,400 );
        panel.add(chartPanel);
        update();
    }
    void menu9()
    {
        clear();
        buton1=new JButton("Grad ocupare luna curenta");
        buton1.setBounds(x / 2-50, y / 2+10, 100, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu8();
                    }
                }
        );
        panel.add(buton1);
        update();
    }
    void menu7()
    {
        clear();
        JLabel[] labele=new JLabel[3];
        JTextField[] texte=new JTextField[3];
        label1=new JLabel("Filter by");
        label1.setBounds(20,40,150,30);
        radio1=new JRadioButton("Persoana");
        radio1.setBounds(180,40,150,30);
        radio1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(labele[0]!=null)
                        for(int i=0;i<3;i++)
                        {
                            panel.remove(labele[i]);
                            panel.remove(texte[i]);
                        }
                        labele[0] = new JLabel("Nume");
                        labele[0].setBounds(20, 80, 150, 30);
                        texte[0]=new JTextField("");
                        texte[0].setBounds(20,120,150,20);
                        labele[1] = new JLabel("Telefon");
                        labele[1].setBounds(180, 80, 150, 30);
                        texte[1]=new JTextField("");
                        texte[1].setBounds(180,120,150,20);
                        labele[2] = new JLabel("IdClient");
                        labele[2].setBounds(340, 80, 150, 30);
                        texte[2]=new JTextField("");
                        texte[2].setBounds(340,120,150,20);
                        for(int i=0;i<3;i++)
                        {
                            panel.add(labele[i]);
                            panel.add(texte[i]);
                        }
                        update();
                    }
                }
        );
        radio2=new JRadioButton("Rezervare");
        radio2.setBounds(340,40,150,30);
        radio2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(labele[0]!=null)
                            for(int i=0;i<3;i++)
                        {
                            panel.remove(labele[i]);
                            panel.remove(texte[i]);
                        }
                        labele[0] = new JLabel("Data");
                        labele[0].setBounds(20, 80, 150, 30);
                        texte[0]=new JTextField("");
                        texte[0].setBounds(20,120,150,20);
                        labele[1] = new JLabel("Camera");
                        labele[1].setBounds(180, 80, 150, 30);
                        texte[1]=new JTextField("");
                        texte[1].setBounds(180,120,150,20);
                        labele[2] = new JLabel("IdRezervare");
                        labele[2].setBounds(340, 80, 150, 30);
                        texte[2]=new JTextField("");
                        texte[2].setBounds(340,120,150,20);
                        for(int i=0;i<3;i++)
                        {
                            panel.add(labele[i]);
                            panel.add(texte[i]);
                        }
                        update();
                    }
                }
        );
        ArrayList<Reservation> aux1=new ArrayList();
        ArrayList<Client> aux2=new ArrayList();
        ArrayList<JButton> butoane=new ArrayList<>();
        Query q=new Query();
        buton2=new JButton("Delete");
        buton2.setBounds(3*x/4,80,100,30);
        buton2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<Reservation> removable=new ArrayList();
                        for(int i=0;i<aux1.size();i++) {
                        if(butoane.get(i).getBackground()==Color.PINK)
                            removable.add(aux1.get(i));
                        }
                        q.deletereservations(removable);
                    }
                }
        );
        buton1=new JButton("Cautare->");
        buton1.setBounds(3*x/4,40,100,30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(radio1.isSelected()) {
                            q.clientquery(aux1, aux2, texte[0].getText(), texte[1].getText(),texte[2].getText());
                            for(int i=0;i<aux1.size();i++)
                            {
                                buton4=new JButton(String.format("%s to %s, room:%d, costs:%d",aux1.get(i).get_in(),aux1.get(i).get_out(),aux1.get(i).get_idRoom(),aux1.get(i).get_costs()));
                                buton4.setBounds(20,i*30+160,300,30);
                                buton4.setBackground(Color.GRAY);
                                int finalI = i;
                                butoane.add(buton4);
                                butoane.get(i).addActionListener(
                                        new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                            if(butoane.get(finalI).getBackground()==Color.GRAY)
                                                butoane.get(finalI).setBackground(Color.PINK);
                                            else
                                                butoane.get(finalI).setBackground(Color.GRAY);
                                            }
                                        }
                                );
                                buton4.addMouseListener(new MouseListener() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                    }
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                    }
                                    @Override
                                    public void mouseReleased(MouseEvent e) {
                                    }
                                    @Override
                                    public void mouseEntered(MouseEvent e) {
                                        buton3=new JButton(String.format("Client:%s %s, telefon:%s, id:%d",aux2.get(finalI).get_nume(),aux2.get(finalI).get_prenume(),aux2.get(finalI).get_telefon(),aux2.get(finalI).get_idClient()));
                                        buton3.setBounds(330,finalI*30+160,350,30);
                                        panel.add(buton3);
                                        update();
                                    }
                                    @Override
                                    public void mouseExited(MouseEvent e) {
                                        panel.remove(buton3);
                                        update();
                                    }
                                });
                                panel.add(butoane.get(i));
                            }
                            update();
                        }
                    }
                }
        );
        G1=new ButtonGroup();
        G1.add(radio1);
        G1.add(radio2);
        panel.add(label1);
        panel.add(radio1);
        panel.add(radio2);
        panel.add(buton1);
        update();
    }
    void menu6(Reservation r)
    {
        clear();
        label1=new JLabel(String.format("Rezervarea cu nr:%d inregistrata cu success",r.get_idRes()));
        label1.setBounds(x/2-50,y/2,300,20);
        panel.add(label1);
        update();
    }
    void menu5(ArrayList<String> aux1,ArrayList<Integer> aux2,ArrayList<Integer> aux3,Query q)
    {
        clear();
        if(aux1.size()<1) {
            label1 = new JLabel("nu mai exista disponibilitate pentru aceasta perioada");
            label1.setBounds(x / 2-75, y / 2-20, 150, 30);
            buton1=new JButton("Inapoi");
            buton1.setBounds(x / 2-50, y / 2+10, 100, 30);
            buton1.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            menu2();
                        }
                    }
            );
            panel.add(label1);
            panel.add(buton1);
            update();
        }
        else
        {
        JButton[] scris=new JButton[aux1.size()];
        for(int i=0;i<aux1.size();i++)
        {
            scris[i]=new JButton();
            scris[i].setText(String.format("Pachet cu pret pe noapte:%d",aux2.get(i)));
            scris[i].setBounds(20,i*y/20+30,300,20);
            int finalI = i;
            scris[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    buton1 = new JButton(String.format("Room number:%d",aux1.get(finalI)));
                    buton1.setBounds(320, finalI * y / 20 + 30, 300, 20);
                    panel.add(buton1);
                    update();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    panel.remove(buton1);
                    update();
                }
            });
            scris[i].addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Random ceva=new Random();
                            Client p=new Client(null,null,null,0);
                            Reservation d=new Reservation(aux1.get(finalI),aux2.get(finalI),q.get_checkin(),q.get_checkout(),0, (int) (ceva.nextLong() & 0xffffffffL));
                            menu3(p,d);
                        }
                    }
            );
            panel.add(scris[i]);
        }
        update();
        }
    }
    void menu4()
    {
        clear();
        label1=new JLabel("Numar Persoane");
        label1.setBounds(x/4,2*y/8-30,200,40);
        SqlDateModel ceva1 = new SqlDateModel();
        JDatePicker datePicker1 = new JDatePicker(ceva1);
        datePicker1.setBounds(x/4,3*y/8,200,20);
        SqlDateModel ceva2 = new SqlDateModel();
        JDatePicker datePicker2 = new JDatePicker(ceva2);
        datePicker2.setBounds(x/4,4*y/8,200,20);
        label2=new JLabel("Check-In");
        label2.setBounds(x/4,3*y/8-30,200,40);
        label3=new JLabel("Check-Out");
        label3.setBounds(x/4,4*y/8-30,200,40);
        text2=new JTextField("");
        text2.setBounds(x/4,2*y/8,200,20);
        buton1=new JButton("Gata");
        buton1.setBounds(6*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Query q=new Query();
                        q.set_checkin(String.valueOf(datePicker1.getModel().getValue()));
                        q.set_checkout(String.valueOf(datePicker2.getModel().getValue()));
                        q.set_pers(Integer.parseInt(text2.getText()));
                        //f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        //f.setVisible(false); //you can't see me!
                        //f.dispose();
                        ArrayList<String> aux1 = new ArrayList<String>();
                        ArrayList<Integer> aux2 = new ArrayList<Integer>();
                        ArrayList<Integer> aux3 = new ArrayList<Integer>();
                        q.timeFrameCheck(aux1,aux2,aux3);
                        menu5(aux1,aux2,aux3,q);
                    }
                }
        );
        panel.add(label1);
        panel.add(datePicker1);
        panel.add(datePicker2);
        panel.add(label2);
        panel.add(label3);
        panel.add(text2);
        panel.add(buton1);
        update();
    }
    void menu3(Client p,Reservation d)
    {
        clear();
        label1=new JLabel("Nume Client");
        label1.setBounds(x/4,2*y/8-30,200,40);
        label2=new JLabel("Prenume Client");
        label2.setBounds(x/4,3*y/8-30,200,40);
        label3=new JLabel("Telefon Client");
        label3.setBounds(x/4,4*y/8-30,200,40);
        text1=new JTextField("");
        text1.setBounds(x/4,2*y/8,200,20);
        text2=new JTextField("");
        text2.setBounds(x/4,3*y/8,200,20);
        text3=new JTextField("");
        text3.setBounds(x/4,4*y/8,200,20);
        buton1=new JButton("Gata");
        buton1.setBounds(6*x / 8, y / 2, x / 8, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        p.set_nume(text1.getText());
                        p.set_prenume(text2.getText());
                        p.set_telefon(text3.getText());
                        p.set_idPers(p.generate_idClient());
                        Entry c=new Entry();
                        if(p.get_idClient()==0) {
                            c.insertClient(p);
                        }
                        d.set_idPers(p.get_idClient());
                        c.insertReservation(d);
                        menu6(d);
                    }
                }
        );
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(text1);
        panel.add(text2);
        panel.add(text3);
        panel.add(buton1);
        update();
    }
    void menu2()
    {
        clear();
        label1=new JLabel("Alegeti o optiune");
        label1.setBounds(x/4,y/4+40,100,40);
        buton1 = new JButton();
        buton1.setText("Adaugare");
        buton1.setBounds(2*x / 8, y / 2, 150, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu4();
                    }
                }
        );
        buton2 = new JButton();
        buton2.setText("Interogare");
        buton2.setBounds(4*x / 8, y / 2, 150, 30);
        buton2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu7();
                    }
                }
        );
        f.add(buton1);
        f.add(buton2);
        f.add(label1);
        update();
    }
    public void menu1()
    {
        label1=new JLabel("WELCOME TO HOTEL ORGANOSER");
        label1.setFont(new Font("Serif", Font.PLAIN, 28));
        label1.setBounds(x/8,y/4+40,500,40);
        //option1
        buton1 = new JButton();
        buton1.setText("Rezervari");
        buton1.setBounds(2*x / 8, y / 2, 150, 30);
        buton1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buton3=new JButton("Future reservations");
                        buton4=new JButton("Todays reservations");
                        buton3.setBounds(2*x / 8-90, y / 2+40, 150, 30);
                        buton4.setBounds(2*x / 8+90, y / 2+40, 150, 30);
                        buton3.addActionListener(
                                new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        menu2();
                                    }
                                }
                        );
                        panel.add(buton3);
                        panel.add(buton4);
                        update();
                    }
                }
        );
        buton2 = new JButton();
        buton2.setText("Statistici");
        buton2.setBounds(4*x / 8, y / 2, 150, 30);
        buton2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        menu9();
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
        panel=(JComponent)f.getContentPane();
    }
}
