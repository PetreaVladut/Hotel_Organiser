import java.sql.*;

public class Reservation{
    int idRes,costs,idRoom,idPers;
    String in,out;
    public String get_in(){return in;}
    public String get_out(){return out;}
    public int get_idRes(){return idRes;}
    public int get_costs(){return costs;}
    public int get_idRoom(){return idRoom;}
    public int get_idPers(){return idPers;}
    public Reservation(Connection con,int i) {
/*        idRes=i;
        String data=null;
        //data = "select * from tester.Reservation where idRes="+idRes+";";
        System.out.println(data);
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(data);
            rs.next();
*//*            nume=rs.getString("nume");
            prenume=rs.getString("prenume");
            telefon=rs.getString("telefon");*//*
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    public Reservation(String camera,int costuri,String in,String out,int d,int i)
    {
        //generate idRes(alta bataie de cap)
        idRes=i;
        //generate or find out personal id o bataie de cap si mai mare sa ma fut
        idPers=d;
        this.in=in;
        this.out=out;
        //idRoom= Integer.parseInt(camera);
        costs= costuri;
    }
}
