import java.sql.*;
import java.util.ArrayList;

public class Query {
    String checkin,checkout;
    int pers;
    public void set_checkin(String c){checkin=c;};
    public void set_checkout(String c){checkout=c;};
    public void set_pers(int c){pers=c;};
    public String get_checkin(){return checkin;};
    public String get_checkout(){return checkout;};
    public int get_pers(){return pers;};
    Connection c;
    Client p;
    public void timeFrameCheck() {
        String data = null;
        data = String.format("select * from tester.Room natural join tester.RType where idRoom not in (select idRoom from tester.Reservation where checkIn <= %s or checkOut >= %s) and Capacity >= %s", checkout, checkin, pers);
        System.out.println(data);
        ArrayList<String> aux1 = new ArrayList<String>();
        ArrayList<Integer> aux2 = new ArrayList<Integer>();
        ArrayList<Integer> aux3 = new ArrayList<Integer>();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(data);
            while (rs.next()) {
                aux1.add(rs.getString("idRoom"));
                aux2.add(rs.getInt("StandardPrice"));
                aux3.add(rs.getInt("Capacity"));
            }
            graphics gui=new graphics();
            gui.menu5(aux1, aux2, aux3,this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Query()
    {
        try {
            // Class.forName("org.postgresql.Driver");
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            //Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:3306/test", "root", "vladut28");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tester?characterEncoding=utf8", "root", "vladut28");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
