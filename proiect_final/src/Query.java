import java.sql.*;
import java.util.ArrayList;

public class Query {
    String checkin,checkout;
    int pers;
    String nume;
    String telefon;
    public void set_checkin(String c){checkin=c;};
    public void set_checkout(String c){checkout=c;};
    public void set_pers(int c){pers=c;};
    public String get_checkin(){return checkin;};
    public String get_checkout(){return checkout;};
    public int get_pers(){return pers;};
    Connection c;
    Client p;
    public void timeFrameCheck(ArrayList<String> aux1,ArrayList<Integer> aux2,ArrayList<Integer> aux3) {
        String data = null;
        data = String.format("select * from tester.Room natural join tester.RType where idRoom not in (select idRoom from tester.Reservation where checkIn <= %s or checkOut >= %s) and Capacity >= %s", checkout, checkin, pers);
        System.out.println(data);
/*        ArrayList<String> aux1 = new ArrayList<String>();
        ArrayList<Integer> aux2 = new ArrayList<Integer>();
        ArrayList<Integer> aux3 = new ArrayList<Integer>();*/
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(data);
            while (rs.next()) {
                aux1.add(rs.getString("idRoom"));
                aux2.add(rs.getInt("StandardPrice"));
                aux3.add(rs.getInt("Capacity"));
            }
            //graphics gui=new graphics();
            //gui.menu5(aux1, aux2, aux3,this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void clientquery(ArrayList<Reservation> aux1,ArrayList<Client> aux2,String nume, String telefon, String idClient) {
        String data = null;
        String param1="nume";
        if(nume.isEmpty())
            nume="%";
        else
        if(nume.contains(" "))
            param1="concat(concat(nume,\" \"),prenume)";
        if(telefon.isEmpty())
            telefon="%";
        if(idClient.isEmpty())
            idClient="%";
        data = String.format("select * from tester.Reservation natural join tester.client where %s like '%s' and telefon like '%s' and idPers like '%s'",param1, nume, telefon,idClient);
        System.out.println(data);
        Statement stmt = null;
        try {
            Reservation r;
            Client p;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(data);
            while (rs.next()) {
                r=new Reservation(rs.getString("idRoom"),rs.getInt("Costs"),rs.getString("CheckIn"),rs.getString("CheckOut"),rs.getInt("IdPers"),rs.getInt("IdRes"));
                aux1.add(r);
                p=new Client(rs.getString("nume"),rs.getString("prenume"),rs.getString("telefon"),rs.getInt("IdPers"));
                aux2.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletereservations(ArrayList<Reservation> aux1) {
        String data=null;
        PreparedStatement stmt = null;
        for(int i=0;i<aux1.size();i++) {
            data = String.format("delete from tester.reservations where idRes;", aux1.get(i).get_idRes());
            System.out.println(data);
            try {
                stmt = c.prepareStatement(data);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void occupationbymonth(int x,ArrayList<Integer> aux1)
    {//here to be added different month lengths
        String data;
        int ct;
        for(int i=0;i<30;i++)
        {
            ct=0;
            data = String.format("select * from tester.Reservation where CheckIn<='2023-%d-%d' and CheckOut >='2023-%d-%s';",x,i,x,i);
            System.out.println(data);
            Statement stmt = null;
            try {
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(data);
                while (rs.next()) {
                    ct++;
                }
                aux1.add(ct);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Query() {
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
