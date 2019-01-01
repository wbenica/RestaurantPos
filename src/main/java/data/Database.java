package data;

import employees.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {

    private static final String DATA_URL = "jdbc:sqlite:./data.db";

    private Database ( ) {

        throw new IllegalStateException ( "Utility class" );
    }

    public static void createNewDatabase ( ) {

        try ( Connection conn = DriverManager.getConnection ( DATA_URL ) ) {
            if ( conn != null ) {
                createTables ( );
                addPositions ( );
            }
        }
        catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
        }
    }

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
            System.out.println ( e.getMessage ( ) );
        }

        return result;
    }

    public static ObservableList<Employee> getEmployees ( String value ) {

        ObservableList<Employee> result = FXCollections.observableArrayList ( );
        String employees = "SELECT empl_id, first_name, last_name\n"
                + " FROM employees\n"
                + " WHERE (empl_id LIKE \"%" + value + "%\")"
                + " OR (first_name LIKE \"%" + value + "%\")"
                + " OR (last_name LIKE \"%" + value + "%\");";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( );
              ResultSet rs = stmt.executeQuery ( employees ) ) {
            while ( rs.next ( ) ) {
                result.add ( new Employee (
                        Integer.parseInt ( rs.getString ( "empl_id" ) ),
                        rs.getString ( "first_name" ),
                        rs.getString ( "last_name" ) ) );
            }
        }
        catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
        }

        return result;
    }

    // TODO: need to verify input
    public static void addEmployee ( String fName,
                                     String lName,
                                     String pos
    ) {

        String cmd = "INSERT INTO employees(first_name, last_name, position)\n"
                + " VALUES ( \"" + fName + "\", \"" + lName + "\", \"" + pos +
                "\");";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( ) ) {
            stmt.execute ( cmd );
        }
        catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
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
            System.out.println ( e.getMessage ( ) );
        }

    }

    private static void createTables ( ) {

        String items = "CREATE TABLE IF NOT EXISTS items (\n"
                + " sku integer PRIMARY KEY,\n"
                + " name text NOT NULL, \n"
                + " buttonName text NOT NULL,\n"
                + " price real NOT NULL"
                + ");";

        String employees = "CREATE TABLE IF NOT EXISTS employees (\n"
                + " empl_id integer PRIMARY KEY,\n"
                + " first_name text NOT NULL, \n"
                + " last_name text NOT NULL,\n"
                + " position text NOT NULL"
                + ");";

        String tickets = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + " check_no integer PRIMARY KEY,\n"
                + " server integer NOT NULL, \n"
                + " date integer NOT NULL,\n"
                + " open_time integer NOT NULL,\n"
                + " close_time integer NOT NULL,\n"
                + " FOREIGN KEY (server) REFERENCES employees(id)"
                + ");";

        String ticketItems = "CREATE TABLE IF NOT EXISTS ticket_items (\n"
                + " check_no integer,\n"
                + " sku integer,\n"
                + " FOREIGN KEY(check_no) REFERENCES tickets(check_no),\n"
                + " FOREIGN KEY(sku) REFERENCES items(sku)"
                + ");";

        String positions = "CREATE TABLE IF NOT EXISTS positions (\n"
                + " title string UNIQUE "
                + ");";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement ( ) ) {
            stmt.execute ( items );
            stmt.execute ( employees );
            stmt.execute ( tickets );
            stmt.execute ( ticketItems );
            stmt.execute ( positions );
        }
        catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
        }
    }
}
