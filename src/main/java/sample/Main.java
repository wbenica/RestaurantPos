package sample;

import data.Database;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String FORM_LABEL = "form_label";
    public static final String TITLE      = "title";

    public static void main ( String[] args ) {

        Database.createNewDatabase ( );
        launch ( args );
    }

    @Override
    public void start ( Stage primaryStage ) {

        primaryStage.setTitle ( "Expo" );
        primaryStage.setFullScreen ( true );
        primaryStage.setScene ( MainController.getMain ( ) );
        primaryStage.show ( );
    }
}
