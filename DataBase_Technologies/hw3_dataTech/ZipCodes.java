package javaPracticeWorld;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.sql.*;


public class ZipCodes {



    public static void main(String[] args) {

        Connection c = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@acadoradbprd01.dpu.depaul.edu:1521:ACADPRD0", "sweber23", "cdm1917410");
        }

        catch (SQLException ex) {System.out.println(ex.getMessage());}
        catch (ClassNotFoundException ex) {System.out.println(ex.getMessage());}

        String path = "/Users/sethweber/Downloads/ChIzipcode.csv";
        BufferedReader BR = null;
        String line = "";
        String coma = ",";

        try {
            BR = new BufferedReader(new FileReader(path));
            while((line = BR.readLine()) != null) {
                String[] zip = line.split(coma);
                doInsert("zipcode", zip, c);
            }
            doSelect(c);
        }
        catch (IOException e) {e.printStackTrace();}
        finally {
            if (BR != null) {
                try {
                    BR.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void doInsert(String table, String[] values, Connection c)
    {

        try
        {
            Statement st = c.createStatement();
            String upd = "INSERT INTO " + table + " VALUES (";
            for (int i = 0; i < values.length; i++)
            {
                upd = upd + values[i].replace('"', '\'');
                if (i < values.length-1)
                    upd += ", ";
                else
                    upd += ")";

            }
            System.out.println(upd);
            st.executeUpdate(upd);
        }
        catch (SQLException ex)
        {
            System.err.println("Insert failure " + ex.getMessage());
        }
    }

    private static void doSelect(Connection c)
    {
        System.out.println("\ndoSelect:\n");
        String query = "SELECT r.name, z.zip, z.latitude, z.longitude FROM Restaurant_Locations r, zipcode z WHERE r.zipcode = z.zip";

        try
        {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String s = rs.getString("name");
                String n = rs.getString("zip");
                String lt = rs.getString("latitude");
                String lg = rs.getString("longitude");
                System.out.println(s + ", " + n + ", " + lt + ", " +lg);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Select failure " + ex.getMessage());
        }
    }
}