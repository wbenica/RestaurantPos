package sample;

import data.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String FORM_LABEL = "form_label";

    public static void main ( String[] args ) {

        Database.createNewDatabase ( );
        launch(args);
    }

    @Override
    public void start ( Stage primaryStage ) throws Exception {

        Label labelFirstName = new Label ( "First Name" );
        labelFirstName.getStyleClass ( ).add ( FORM_LABEL );
        TextField textFirstName = new TextField ( );
        Label     labelLastName = new Label ( "Last Name" );
        labelLastName.getStyleClass ( ).add ( FORM_LABEL );
        TextField textLastName  = new TextField ( );
        Label     labelPosition = new Label ( "Position" );
        labelPosition.getStyleClass ( ).add ( FORM_LABEL );
        ChoiceBox menuPosition = new ChoiceBox ( );
        menuPosition.setItems ( Database.getPositions ( ) );
        Button buttAddEmployee = new Button ( "Add Employee" );

        GridPane gp = new GridPane ( );
        gp.add ( labelFirstName, 0, 0 );
        gp.add ( textFirstName, 1, 0 );
        gp.add ( labelLastName, 0, 1 );
        gp.add ( textLastName, 1, 1 );
        gp.add ( labelPosition, 0, 2 );
        gp.add ( menuPosition, 1, 2 );
        gp.add ( buttAddEmployee, 0, 3, 2, 1 );

        Scene scene = new Scene ( gp, 300, 300 );
        primaryStage.setTitle ( "Add Employee" );
        primaryStage.setScene ( scene );
        primaryStage.show ();
    }
}
