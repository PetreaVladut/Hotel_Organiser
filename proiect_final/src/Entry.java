import java.sql.*;
public class Entry {
    Connection c;
    public void insertClient(Client p){
        String data;
        data = "'"+p.get_nume() + "', '" + p.get_prenume() + "', '" + p.get_telefon() + "', " + String.valueOf(p.get_idClient());
        data = "INSERT INTO tester.client VALUES (" + data + ");";
        PreparedStatement stmt = null;
        try {
            stmt = c.prepareStatement(data);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteClient(int i){
        String data;
        data = "DELETE from tester.client WHERE idPers = ?";
        System.out.println(data);
        PreparedStatement stmt = null;
        try {
            stmt = c.prepareStatement(data);
            stmt.setString(1,Integer.toString(i));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void modifyClient(Client p){
        String data;
        data = "UPDATE tester.client SET nume = ?, prenume = ?, telefon = ? WHERE  idPers = ?";
        System.out.println(data);
        PreparedStatement stmt = null;
        try {
            stmt = c.prepareStatement(data);
            stmt.setString(1,p.get_nume());
            stmt.setString(2,p.get_prenume());
            stmt.setString(3,p.get_telefon());
            stmt.setString(4,Integer.toString(p.get_idClient()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int existClient(Client p){
        String data=null;
        data = String.format("select * from tester.client where nume='%s' and prenume='%s' and telefon='%s'",p.get_nume(),p.get_prenume(),p.get_telefon());
        System.out.println(data);
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(data);
            if(rs.next())
            return rs.getInt("idPers");
            else return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertReservation(Reservation r){
        String data=null;
        data = String.format("INSERT INTO tester.reservation VALUES (%d,%d,'%s','%s',%d,%d);",r.get_idRes(),r.get_costs(),r.get_in(), r.get_out(),r.get_idPers(),r.get_idRoom());
        PreparedStatement stmt = null;
        try {
            stmt = c.prepareStatement(data);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteReservation(){}
    public void modifyReservation(){}
    public void createReservation(){}
    public Entry(Connection con) {
        if(con!=(null))
        {
            c=con;
        }
    }
    public Entry(){
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
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tester?characterEncoding=utf8", "root", "vladut28");
        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
    }
}
