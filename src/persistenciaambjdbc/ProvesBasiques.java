/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaambjdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ale
 */
public class ProvesBasiques {

    private static String[] contrasenyes = {
        "trc", "pts", "ioc"
    };
    private static String usuari = "ioc";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        provaDeConnexio();
        crearTaula();
        insereixTasques();
        modificarTasques();
        consultarTasques();
    }

    public static void provaDeConnexio() {
        Connection con = null;
        Driver driver = null;
        String url = "jdbc:mysql://localhost:3306/ioc_proves";
        String usuari = "ioc";
        String pass = "ioc";
        System.out.println("provaDeConnexio()");
        try {
            //Carreguem el controlador en memòria
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ( " + ex.getMessage() + ")");
            //Si no tenim el controlador, no podem fer res més. Sortim.
            return;
        }
        try {
            //Obtenim una connexió desde DriverManager
            con = DriverManager.getConnection(url, usuari, pass);
            System.out.println("Connexió realitzada utilitzant DriverManager");
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        try {
            //Obtenim el Driver del controlador des de DriverManager
            driver = DriverManager.getDriver(url);
            //Configuram l'usuari i la contrassenya
            Properties properties = new Properties();
            properties.setProperty("user", usuari);
            properties.setProperty("password", pass);
            //Obtenim una connexió des de la instància de Driver
            con = driver.connect(url, properties);
            System.out.println("Connexió realitzada usant Driver");
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }

    public static void crearTaula() {
        Connection con = null;
        Statement statement = null;
        String sentenciaSQL = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ioc_proves";
            con = DriverManager.getConnection(url, "ioc", "ioc");

            statement = con.createStatement();

            sentenciaSQL = "DROP TABLE IF EXISTS tasques";
            statement.executeUpdate(sentenciaSQL);

            sentenciaSQL = "CREATE TABLE tasques(\n"
                    + "id INTEGER NOT NULL, \n"
                    + "descripcio VARCHAR(300) NOT NULL, \n"
                    + "data_inici DATE, \n"
                    + "data_final DATE NOT NULL, \n"
                    + "finalitzada BOOLEAN DEFAULT false, \n"
                    + "CONSTRAINT pk_clauPrimaria PRIMARY KEY (id)"
                    + ")";

            statement.executeUpdate(sentenciaSQL);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + ")");
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {/*Llàstima!*/
            }
        }
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void insereixTasques() {
        Connection con = null;
        Statement statement = null;
        String sentenciaSQL = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ioc_proves";
            con = DriverManager.getConnection(url, "ioc", "ioc");

            statement = con.createStatement();

            sentenciaSQL = "INSERT INTO TASQUES(id, descripcio, data_inici, data_final) VALUES (1, "
                    + "'Comprar pomes', '2012-02-15', '2012-02-16')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (2, "
                    + "'Estudiar examen', '2012-05-2', '2012-05-18')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (3, "
                    + "'Compar bitllet Sidney', '2012-02-15', '2012-05-20')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (4, "
                    + "'Anar a Australia', '2012-06-01', '2012-08-25')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (5, "
                    + "'Revisió cotxe', '2012-04-8', '2012-04-16')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (6, "
                    + "'Manifestació 1r maig', '2012-05-1', '2012-05-1')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (7, "
                    + "'Pràctica JDBC', '2012-04-15', '2012-05-16')";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "INSERT INTO TASQUES (id, descripcio, data_inici, data_final) VALUES (8, "
                    + "'FCT', '2012-03-1', '2012-05-24')";
            statement.executeUpdate(sentenciaSQL);

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + ")");
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
        }
    }

    public static void modificarTasques() {
        Connection con = null;
        Statement statement = null;
        String sentenciaSQL = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ioc_proves";
            con = DriverManager.getConnection(url, "ioc", "ioc");

            statement = con.createStatement();

            sentenciaSQL = "UPDATE TASQUES SET FINALITZADA=true "
                    + "WHERE ID=1 OR ID=3 OR id=5";
            statement.executeUpdate(sentenciaSQL);
            sentenciaSQL = "UPDATE TASQUES SET data_final='2012-06-5' "
                    + "where id=7";
            statement.executeUpdate(sentenciaSQL);
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + ")");
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
        }
    }

    public static void consultarTasques() {
        Connection con = null;
        Statement statement = null;
        String sentenciaSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ioc_proves";
            con = DriverManager.getConnection(url, "ioc", "ioc");

            statement = con.createStatement();

            sentenciaSQL = "SELECT id, descripcio, data_inici,"
                    + " data_final, finalitzada"
                    + " FROM tasques WHERE finalitzada";
            rs = statement.executeQuery(sentenciaSQL);

//mostrar capçalera per la pantalla
            System.out.print(" id");
            System.out.print(" descripcio ");
            System.out.print(" data_inici ");
            System.out.print(" data_final ");
            System.out.println("finalitzada");
            System.out.print("----------");
            System.out.print("--------------------------");
            System.out.print("------------");
            System.out.print("------------");
            System.out.print("-----------");
            System.out.println();
            //mostrar files de dades
            while (rs.next()) {
                System.out.printf("%10d", rs.getInt(1));
                String desc = rs.getString(2);
                //fem que la mida de desc sigui sempre 25 caràcters
                //així no es desquadra la taula.
                System.out.println(desc);
                if (desc.length() <= 25) {
                    System.out.printf(" %s%s", desc,
                            "                         ".substring(0, 25 - desc.length()));
                } else {
                    System.out.printf(" %25s", rs.getString(2).substring(0, 25));
                }
                //usem Dateformat per formatar les dates
                //en el nostre cas DD-MM-AAAA
                DateFormat df = DateFormat.getDateInstance();
                System.out.print(" " + df.format(rs.getDate(3)) + " ");
                System.out.print(" " + df.format(rs.getDate(4)) + " ");
                System.out.println(" " + rs.getBoolean(5));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + ")");
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {/*llàstima!*/
            }
        }
    }

    public static void tractamentErrorConnexio() {
        boolean connectat = false;
        Connection con = null;
        System.out.println("tractamentErrorConnexio()");

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ioc_proves";

            for (int i = 0; !connectat && i < contrasenyes.length; i++) {
                try {
                    con = DriverManager.getConnection(url, usuari,
                            contrasenyes[i]);
                    connectat = true;
                } catch (SQLException ex) {
                    if (!ex.getSQLState().equals("28000")) {
                        //NO és un error d'autenticació
                        throw ex;
                    }
                }
            }
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("08001")) {
                System.out.println("S'ha detectat un problema de"
                        + " connexió. Reviseu els cables de xarxa"
                        + " i assegureu-vos que l'SGBD està"
                        + " operatiu.\n Si continua sense"
                        + " connectar, aviseu el servei tècnic");

            } else {
                System.out.println("S'ha produït un error inesperat."
                        + " Truqueu al servei tècnic indicant el següent codi "
                        + "d'error SQL:" + ex.getSQLState());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + "). Truqueu al servei tècnic");
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProvesBasiques.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void executa(String[] sentenciesSql) throws SQLException {
        Connection con = null;
        boolean autocommit = true;
        Statement stm = null;
        try {
            autocommit = con.getAutoCommit();
            con.setAutoCommit(false);
            stm = con.createStatement();

            for (String sent : sentenciesSql) {
                stm.executeUpdate(sent);
            }
            con.commit();
            con.setAutoCommit(autocommit);
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            try {
                if (stm != null && !stm.isClosed()) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProvesBasiques.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }
}
