package employees;

import data.Database;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Main;

public class EmployeeController {

    public static GridPane getSceneAddEmployee ( ) {

        Label title = new Label ( "Add Employee" );
        title.getStyleClass ( ).add ( Main.TITLE );
        Label labelFirstName = new Label ( "First Name" );
        labelFirstName.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textFirstName = new TextField ( );
        Label     labelLastName = new Label ( "Last Name" );
        labelLastName.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textLastName  = new TextField ( );
        Label     labelPosition = new Label ( "Position" );
        labelPosition.getStyleClass ( ).add ( Main.FORM_LABEL );
        ChoiceBox menuPosition = new ChoiceBox ( );
        menuPosition.setItems ( Database.getPositions ( ) );
        menuPosition.setPrefWidth ( 200 );
        Button buttAddEmployee = new Button ( "Add Employee" );

        GridPane gp = new GridPane ( );
        gp.setVgap ( 5 );
        gp.setHgap ( 5 );
        gp.setPadding ( new Insets ( 20 ) );
        gp.add ( title, 0, 0, 2, 1 );
        gp.add ( labelFirstName, 0, 1 );
        gp.add ( textFirstName, 1, 1 );
        gp.add ( labelLastName, 0, 2 );
        gp.add ( textLastName, 1, 2 );
        gp.add ( labelPosition, 0, 3 );
        gp.add ( menuPosition, 1, 3 );
        gp.add ( buttAddEmployee, 0, 4, 2, 1 );
        buttAddEmployee.setOnMouseReleased (
                event -> {

                    String first = textFirstName.getText ( );
                    String last  = textLastName.getText ( );
                    String pos   = menuPosition.getValue ( ).toString ( );
                    Database.addEmployee ( first, last, pos );
                    textFirstName.clear ( );
                    textLastName.clear ( );
                    menuPosition.setValue ( null );
                } );
        return gp;
    }

    public static VBox getSceneSearchEmployee ( ) {

        VBox      boop      = new VBox ( );
        TextField searchBar = new TextField ( "Enter name or employee id" );
        boop.getChildren ( ).addAll ( searchBar );

        return boop;
    }
}
