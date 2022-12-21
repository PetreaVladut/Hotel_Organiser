import java.sql.*;

public class Client {
    int idClient;
    String nume, prenume, telefon;
    public void insert(Connection con) {
/*        String data;
        data = "'"+nume + "', '" + prenume + "', '" + telefon + "', " + String.valueOf(idClient);
        data = "INSERT INTO tester.client VALUES (" + data + ");";
        System.out.println(data);
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(data);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    public void delete(Connection con) {
        /*String data;
        data = "DELETE from tester.client WHERE IdPers = ?";
        System.out.println(data);
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(data);
            stmt.setString(1,Integer.toString(idClient));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    public void set_nume(String n)
    {
        nume=n;
    }
    public void set_prenume(String n) { prenume=n; }
    public void set_telefon(String n) { telefon=n; }
    public String get_nume() { return nume; }
    public String get_prenume() { return prenume; }
    public String get_telefon() { return telefon; }
    public int get_idClient() { return idClient; }
    public void modify(Connection con) {
/*        String data;
        data = "UPDATE tester.client SET nume = ?, prenume = ?, telefon = ? WHERE  IdPers = ?";
        System.out.println(data);
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(data);
            stmt.setString(1,nume);
            stmt.setString(2,prenume);
            stmt.setString(3,telefon);
            stmt.setString(4,Integer.toString(idClient));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    public String DatatoString() {
        String data;
        data= nume+", "+prenume+", "+telefon+", "+String.valueOf(idClient);
        data="insert into tester.client values ("+data+");";
        return data;
    }
    public void generate_idClient()
    {

    }
    public Client(Connection con,int i) {
/*        idClient =i;
        String data;
        data = "select * from tester.client where IdPers="+ idClient +";";
        System.out.println(data);
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(data);
            rs.next();
            nume=rs.getString("nume");
            prenume=rs.getString("prenume");
            telefon=rs.getString("telefon");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    public Client(String s1,String s2,String s3,int i) {
        nume=s1;
        prenume=s2;
        telefon=s3; // can be added prefix reading
        idClient =i;
    }
}
