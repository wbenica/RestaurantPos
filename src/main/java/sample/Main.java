package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    private static final String DATA_URL = "jdbc:sqlite:./data.db";

    public static void createNewDatabase ( ) {

        try ( Connection conn = DriverManager.getConnection ( DATA_URL ) ) {
            if ( conn != null ) {
                DatabaseMetaData meta = conn.getMetaData ( );
                System.out.println (
                        "The driver name is " + meta.getDriverName ( ) );
                System.out.println ( "A new database has been created." );
            }
        }
        catch ( SQLException e ) {
            System.out.println ( e.getMessage ( ) );
        }
    }

    public static void main ( String[] args ) {
        //launch(args);

        createNewDatabase ( );
        createTables ( );
    }

    private static void createTables () {

        String items = "CREATE TABLE IF NOT EXISTS items (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL, \n"
                + " buttonName text NOT NULL,\n"
                + " price real NOT NULL"
                + ");";

        String employees = "CREATE TABLE IF NOT EXISTS employees (\n"
                + " id integer PRIMARY KEY,\n"
                + " first_name text NOT NULL, \n"
                + " last_name text NOT NULL,\n"
                + " position text NOT NULL"
                + ");";

        String tickets = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + " id integer PRIMARY KEY,\n"
                + " server integer NOT NULL, \n"
                + " date integer NOT NULL,\n"
                + " open_time integer NOT NULL,\n"
                + " close_time integer NOT NULL,\n"
                + " FOREIGN KEY (server) REFERENCES employees(id)"
                + ");";

        String ticketItems = "CREATE TABLE IF NOT EXISTS ticket_items (\n"
               + " ticket_id integer,\n"
                + " item_id integer,\n"
                + " FOREIGN KEY(ticket_id) REFERENCES tickets(id),\n"
                + " FOREIGN KEY(item_id) REFERENCES items(id)"
                + ");";

        try ( Connection conn = DriverManager.getConnection ( DATA_URL );
              Statement stmt = conn.createStatement () ) {
            stmt.execute ( items );
            stmt.execute ( employees );
            stmt.execute ( tickets );
            stmt.execute ( ticketItems );
        } catch ( SQLException e ) {
            System.out.println ( e.getMessage () );
        }
    }

    @Override
    public void start ( Stage primaryStage ) throws Exception {

        Parent root =
                FXMLLoader.load ( getClass ( ).getResource ( "sample.fxml" ) );
        primaryStage.setTitle ( "Hello World" );
        primaryStage.setScene ( new Scene ( root, 300, 275 ) );
        primaryStage.show ( );
    }
}
