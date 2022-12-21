import java.sql.*;
import java.lang.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        //System.out.println("1.Adaugare inregistrare /n 2.Modificare inregistrare /n 3.Stergere inregistrare");
        //int x;
        //x = Integer.parseInt(args[0]);
        graphics interfata=new graphics();
        interfata.menu1();
        /*switch (x)
        {
            case 1:
        {
            String[] n=new String[4];
            for(int i=0;i<4;i++)
                n[i]=args[i+1];
            Client persoana=new Client(n[0],n[1],n[2],Integer.parseInt(n[3]));
            try {
                // Class.forName("org.postgresql.Driver");
                //Class.forName("com.mysql.jdbc.Driver");
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:3306/test", "root", "vladut28");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tester?characterEncoding=utf8", "root", "vladut28");
            if(con!=(null))
            {
                persoana.insert(con);
            }
            break;
        }
            case 2:
            {
//                String[] n=new String[4];
//                for(int i=0;i<4;i++)
//                    n[i]=args[i+1];
//                Client persoana=new Client(n[0],n[1],n[2],Integer.parseInt(n[3]));
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                }
                catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tester?characterEncoding=utf8", "root", "vladut28");
                Client persoana=new Client(con,5);
                persoana.set_prenume("Haiti");
                persoana.set_nume("Percival");
                if(con!=(null))
                {
                    persoana.modify(con);
                }
                break;
            }
            case 3:
            {
                int y=Integer.parseInt(args[1]);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                }
                catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tester?characterEncoding=utf8", "root", "vladut28");
                Client persoana=new Client(con,y);
                if(con!=(null))
                {
                    persoana.delete(con);
                }
                break;
            }
         }*/
    }
}
//TO DO
//swap menus-> first check period and number of persons
//then offer options
//select housing option then insert customer data
//insert customer data in DB
//insert reservation in DB
//and done the hardest part