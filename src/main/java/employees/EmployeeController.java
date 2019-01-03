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
        Label labelHireDate = new Label ( "Hire Date" );
        labelHireDate.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textHireDate         = new TextField ( );
        Label     labelTerminationDate = new Label ( "Termination Date" );
        labelTerminationDate.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textTerminationDate = new TextField ( );
        Label     labelIsSalaried     = new Label ( "Salaried" );
        labelIsSalaried.getStyleClass ( ).add ( Main.FORM_LABEL );
        CheckBox checkIsSalaried = new CheckBox ( );
        Label    labelPayRate    = new Label ( "Pay Rate" );
        labelPayRate.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textPayRate  = new TextField ( );
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
        gp.add ( labelHireDate, 0, 4 );
        gp.add ( textHireDate, 1, 4 );
        gp.add ( labelTerminationDate, 0, 5 );
        gp.add ( textTerminationDate, 1, 5 );
        gp.add ( labelIsSalaried, 0, 6 );
        gp.add ( checkIsSalaried, 1, 6 );
        gp.add ( labelPayRate, 0, 7 );
        gp.add ( textPayRate, 1, 7 );
        gp.add ( buttAddEmployee, 0, 8, 2, 1 );
        buttAddEmployee.setOnMouseReleased (
                event -> {

                    String first = textFirstName.getText ( );
                    String last  = textLastName.getText ( );
                    Integer pos = Database.getPositionId (
                            menuPosition.getValue ( ) );
                    Database.addEmployee ( first, last, pos );
                    textFirstName.clear ( );
                    textLastName.clear ( );
                    menuPosition.setValue ( null );
                } );
        return gp;
    }

    public static VBox getSceneSearchEmployee ( ) {

        VBox boop = new VBox ( );

        Label title = new Label ( "Search Employee" );
        title.getStyleClass ( ).add ( Main.TITLE );

        TextField searchBar =
                new TextField ( "Enter name or employee id" );
        TableView<Employee> resultsTable = new TableView<> ( );

        searchBar.setOnMouseClicked (
                event -> searchBar.selectAll ( )
        );

        searchBar.setOnKeyReleased (
                event -> {
                    String val = searchBar.getText ( );
                    resultsTable.setItems ( Database.getEmployees ( val ) );
                }
        );

        TableColumn<Employee, Integer> idCol = new TableColumn<> (
                "Emp ID" );
        idCol.setCellValueFactory ( new PropertyValueFactory<> ( "id" ) );

        TableColumn<Employee, String> firstCol = new TableColumn<> ( "First " +
                "Name" );
        firstCol.setCellValueFactory (
                new PropertyValueFactory<> ( "firstName" ) );

        TableColumn<Employee, String> lastCol =
                new TableColumn<> ( "Last Name" );
        lastCol.setCellValueFactory (
                new PropertyValueFactory<> ( "lastName" ) );

        TableColumn<Employee, String> positionCol =
                new TableColumn<> ( "Position" );
        positionCol.setCellValueFactory (
                new PropertyValueFactory<> ( "position" ) );

        resultsTable.getColumns ( )
                .addAll ( idCol, firstCol, lastCol, positionCol );

        boop.getChildren ( ).addAll ( title, searchBar, resultsTable );

        return boop;
    }
}
