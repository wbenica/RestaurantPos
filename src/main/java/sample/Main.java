package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void createNewDatabase ( String fileName ) {

        String url = "jdbc:sqlite:" + Paths.get ( "" ).toAbsolutePath ()
                .toString () + fileName;

        try ( Connection conn = DriverManager.getConnection ( url ) ) {
            if ( conn != null ) {
                DatabaseMetaData meta = conn.getMetaData ();
                System.out.println ( "The driver name is " + meta.getDriverName () );
                System.out.println ( "A new database has been created." );
            }
        } catch ( SQLException e ) {
            System.out.println ( e.getMessage () );
        }

    }

    public static void main(String[] args) {
        //launch(args);

        createNewDatabase ( "hello.db" );
    }
}
