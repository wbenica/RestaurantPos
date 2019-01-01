package employees;

import data.Database;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        ChoiceBox<String> menuPosition = new ChoiceBox<> ( );
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
                    String pos   = menuPosition.getValue ( );
                    Database.addEmployee ( first, last, pos );
                    textFirstName.clear ( );
                    textLastName.clear ( );
                    menuPosition.setValue ( null );
                } );
        return gp;
    }

    public static VBox getSceneSearchEmployee ( ) {

        VBox  boop  = new VBox ( );
        Label title = new Label ( "Search Employee" );
        title.getStyleClass ( ).add ( Main.TITLE );
        TextField           searchBar    = new TextField ( "Enter name or employee id" );
        TableView<Employee> resultsTable = new TableView<> ( );
        TableColumn<Employee, Integer> idCol = new TableColumn<> (
                "Emp ID" );
        idCol.setCellValueFactory (
                new PropertyValueFactory<> (
                        "id" )
        );
        TableColumn<Employee, String> firstCol = new TableColumn<> ( "First " +
                "Name" );
        firstCol.setCellValueFactory (
                new PropertyValueFactory<> (
                        "firstName" )
        );
        TableColumn<Employee, String> lastCol = new TableColumn<> ( "Last " +
                "Name" );
        lastCol.setCellValueFactory (
                new PropertyValueFactory<> (
                        "lastName" )
        );
        resultsTable.getColumns ( ).addAll ( idCol, firstCol, lastCol );
        searchBar.setOnKeyReleased (
                event -> {
                    String val = searchBar.getText ( );
                    resultsTable.setItems ( Database.getEmployees ( val ) );
                }
        );
        boop.getChildren ( ).addAll ( title, searchBar, resultsTable );

        return boop;
    }
}
