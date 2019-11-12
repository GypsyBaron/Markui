package ta2;

import java.sql.*;
import java.util.ArrayList;

// java.sql*
public class TA2 {
    static public void main(String[] args) {
        try {

//            Darbuotojas naujas = new Darbuotojas();
//            naujas.setVardas("Jokubasasass");
//            naujas.setPavarde("Rinkeviciusiusius");
//            boolean[] sav = new boolean[7];
//            sav[0] = true;
//            sav[1] = false;
//            sav[2] = true;
//            sav[3] = true;
//            sav[4] = false;
//            sav[5] = false;
//            sav[6] = true;
//            naujas.setDarboDienos(sav);
//
//            TA2 mano = new TA2();
//            mano.connect();
//            //mano.addEmployee(naujas);
////            ArrayList<Darbuotojas> d = mano.getAllEmployees();
////            for (Darbuotojas darb : d) {
////                System.out.println(darb);
////            }
//            Darbuotojas d = mano.getEmployeeById(3);
//            System.out.println(d);
//            mano.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Connection connection = null;

    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ta2", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void addEmployee(Darbuotojas d) throws Exception {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO darb (id, vard, pavard, pirmadienis, antradienis, treciadienis, ketvirtadienis, penktadienis, sestadienis, sekmadienis)" +
                " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        ps.setString(1, d.getVardas());
        ps.setString(2, d.getPavarde());
        ps.setBoolean(3, d.getDarboDienos()[0]);
        ps.setBoolean(4, d.getDarboDienos()[1]);
        ps.setBoolean(5, d.getDarboDienos()[2]);
        ps.setBoolean(6, d.getDarboDienos()[3]);
        ps.setBoolean(7, d.getDarboDienos()[4]);
        ps.setBoolean(8, d.getDarboDienos()[5]);
        ps.setBoolean(9, d.getDarboDienos()[6]);
        ps.executeUpdate();
        ps.close();
    }

    public ArrayList<Darbuotojas> getAllEmployees() throws Exception{
        ArrayList<Darbuotojas> allEmployee = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rez = statement.executeQuery("SELECT * FROM darb");

        while (rez.next()) {
            int id = rez.getInt("id");
            String vardas = rez.getString(2);
            String pavarde = rez.getString(3);
            boolean[] savaite = new boolean[7];
            for (int i = 0; i < 7; i++) {
                savaite[i] = rez.getBoolean(i + 4);
            }

            Darbuotojas d = new Darbuotojas();
            d.setId(id);
            d.setVardas(vardas);
            d.setPavarde(pavarde);
            d.setDarboDienos(savaite);
            allEmployee.add(d);
        }

        rez.close();
        statement.close();
        return allEmployee;
    }

    public Darbuotojas getEmployeeById(int id) throws Exception{
        Darbuotojas d = null;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM darb WHERE id = ?");
        statement.setInt(1, id);
        ResultSet rez = statement.executeQuery();

        while (rez.next()) {
            //int id = rez.getInt("id");
            String vardas = rez.getString(2);
            String pavarde = rez.getString(3);
            boolean[] savaite = new boolean[7];
            for (int i = 0; i < 7; i++) {
                savaite[i] = rez.getBoolean(i + 4);
            }
            d = new Darbuotojas();
            d.setId(id);
            d.setVardas(vardas);
            d.setPavarde(pavarde);
            d.setDarboDienos(savaite);
            break;
        }

        rez.close();
        statement.close();
        return d;
    }
}
