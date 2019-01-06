package data;

import employees.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: set version number of database, update if needed
public class Database {

    final static Logger log = Logger.getLogger ( Database.class.getName ( ) );

    private static final String DATA_URL = "jdbc:sqlite:./data.db";
    private static final String ITEMS    =
            "CREATE TABLE IF NOT EXISTS items (\n"
                    + " sku integer PRIMARY KEY,\n"
                    + " name text NOT NULL, \n"
                    + " buttonName text NOT NULL,\n"
                    + " price real NOT NULL"
                    + ");";

    private static final String EMPLOYEES =
            "CREATE TABLE IF NOT EXISTS employees (\n"
                    + " first_name text NOT NULL, \n"
                    + " last_name text NOT NULL,\n"
                    + " position integer NOT NULL,\n"
                    + " hire_date text NOT NULL,\n"
                    + " termination_date text CHECK ((termination_date >"
                    + " hire_date) OR NULL),\n"
                    + " is_salaried integer NOT NULL CHECK (is_salaried IN"
                    + " (0, 1)),\n"
                    + " pay_rate real NOT NULL,\n"
                    + " FOREIGN KEY (position) REFERENCES positions(ROWID)"
                    + ");";

    private static final String TICKETS =
            "CREATE TABLE IF NOT EXISTS tickets (\n"
                    + " check_no integer PRIMARY KEY,\n"
                    + " server integer NOT NULL, \n"
                    + " date integer NOT NULL,\n"
                    + " open_time integer NOT NULL,\n"
                    + " close_time integer NOT NULL,\n"
                    + " FOREIGN KEY (server) REFERENCES employees(ROWID)"
                    + ");";

    private static final String TICKET_ITEMS =
            "CREATE TABLE IF NOT EXISTS ticket_items (\n"
                    + " check_no integer,\n"
                    + " sku integer,\n"
                    + " FOREIGN KEY(check_no) REFERENCES tickets(check_no),\n"
                    + " FOREIGN KEY(sku) REFERENCES items(sku)"
                    + ");";

    private static final String POSITIONS =
            "CREATE TABLE IF NOT EXISTS positions (\n"
                    + " title string UNIQUE "
                    + ");";

    public static ObservableList<String> getPositions ( ) {

        ObservableList<String> result =
                FXCollections.observableArrayList ( );
        String positions = "SELECT * FROM positions";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( );
              ResultSet rs = stmt.executeQuery ( positions ) ) {
            while ( rs.next ( ) ) {
                result.add ( rs.getString ( "title" ) );
            }
        }
        catch ( SQLException e ) {
            log.log ( Level.WARNING, "getPositions", e );
        }

        return result;
    }

    public static void createNewDatabase ( ) {

        try ( Connection conn = DriverManager.getConnection ( DATA_URL ) ) {
            if ( conn != null ) {
                createTables ( );
                addPositions ( );
            }
        }
        catch ( SQLException e ) {
            log.log ( Level.SEVERE, "createNewDatabase", e );
        }
    }

    public static Integer getPositionId ( String value ) {

        String cmd = "SELECT ROWID"
                + " FROM positions"
                + " WHERE title = \"" + value + "\"";

        Integer result = null;

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( );
              ResultSet rs = stmt.executeQuery ( cmd ) ) {
            if ( rs.next ( ) ) {
                result = rs.getInt ( 1 );
            }
        }
        catch ( SQLException e ) {
            log.log ( Level.WARNING, "getPositionId", e );
        }

        return result;
    }

    public static ObservableList<Employee> getEmployees ( String value ) {

        ObservableList<Employee> result = FXCollections.observableArrayList ( );
        String employees = "SELECT employees.ROWID, first_name, last_name, " +
                "title, hire_date, termination_date, is_salaried, pay_rate\n"
                + " FROM employees"
                +
                " INNER JOIN positions ON employees.position = positions.ROWID"
                + " WHERE (employees.ROWID LIKE \"%" + value + "%\")"
                + " OR (first_name LIKE \"%" + value + "%\")"
                + " OR (last_name LIKE \"%" + value + "%\")"
                + " OR (title LIKE \"%" + value + "%\");";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( );
              ResultSet rs = stmt.executeQuery ( employees ) ) {
            while ( rs.next ( ) ) {
                result.add ( new Employee (
                        rs.getInt ( "ROWID" ),
                        rs.getString ( "first_name" ),
                        rs.getString ( "last_name" ),
                        rs.getString ( "title" ),
                        rs.getDate ( "hire_date" ),
                        rs.getDate ( "termination_date" ),
                        rs.getBoolean ( "is_salaried" ),
                        rs.getDouble ( "pay_rate" ) ) );
            }
        }
        catch ( SQLException e ) {
            log.log ( Level.WARNING, "getEmployees", e );
        }

        return result;
    }

    // TODO: need to verify input
    public static void addEmployee ( String fName,
                                     String lName,
                                     Integer pos,
                                     String hireDate,
                                     String terminationDate,
                                     Integer isSalaried,
                                     Double payRate ) {

        String term = ( terminationDate == null ? null : "\"" +
                terminationDate + "\"" );

        String cmd = "INSERT INTO employees\n"
                + " VALUES ( \"" + fName + "\", \""
                + lName + "\", "
                + pos + ", \""
                + hireDate + "\", "
                + term
                + ", " + isSalaried + ", " + payRate + " );";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( ) ) {
            stmt.execute ( cmd );
        }
        catch ( SQLException e ) {
            log.log ( Level.WARNING, "addEmployee", e );
        }
    }

    private static void addPositions ( ) {

        String positions = "INSERT OR IGNORE INTO positions\n"
                + " VALUES ( \"Manager\"), (\"Server\"), (\"Busser\" );";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( ) ) {
            stmt.execute ( positions );
        }
        catch ( SQLException e ) {
            log.log ( Level.WARNING, "addPositions", e );
        }

    }

    private static void createTables ( ) {

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( ) ) {
            stmt.execute ( ITEMS );
            stmt.execute ( EMPLOYEES );
            stmt.execute ( TICKETS );
            stmt.execute ( TICKET_ITEMS );
            stmt.execute ( POSITIONS );
        }
        catch ( SQLException e ) {
            log.log ( Level.SEVERE, "createTables", e );
        }
    }

    private Database ( ) {

        throw new IllegalStateException ( "Utility class" );
    }
}
