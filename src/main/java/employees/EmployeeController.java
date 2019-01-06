package employees;

import data.Database;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Main;

import java.sql.Timestamp;
import java.time.chrono.Chronology;

// TODO: Check dates for correctness before attempting to insert in db
public class EmployeeController {

    public static GridPane getSceneAddEmployee ( ) {

        Label title = new Label ( "Add Employee" );
        title.getStyleClass ( ).add ( Main.TITLE );

        Label labelFirstName = new Label ( "First Name" );
        labelFirstName.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textFirstName = new TextField ( );

        Label labelLastName = new Label ( "Last Name" );
        labelLastName.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textLastName = new TextField ( );

        Label labelPosition = new Label ( "Position" );
        labelPosition.getStyleClass ( ).add ( Main.FORM_LABEL );
        ChoiceBox<String> menuPosition = new ChoiceBox<> ( );
        menuPosition.setItems ( Database.getPositions ( ) );
        menuPosition.setPrefWidth ( 200 );

        Label labelHireDate = new Label ( "Hire Date" );
        labelHireDate.getStyleClass ( ).add ( Main.FORM_LABEL );
        DatePicker pickerHireDate = new DatePicker ( );

        Label labelTerminationDate = new Label ( "Termination Date" );
        labelTerminationDate.getStyleClass ( ).add ( Main.FORM_LABEL );
        DatePicker pickerTerminationDate = new DatePicker ( );

        Label labelIsSalaried = new Label ( "Salaried" );
        labelIsSalaried.getStyleClass ( ).add ( Main.FORM_LABEL );
        CheckBox checkIsSalaried = new CheckBox ( );

        Label labelPayRate = new Label ( "Pay Rate" );
        labelPayRate.getStyleClass ( ).add ( Main.FORM_LABEL );
        TextField textPayRate     = new TextField ( );
        Button    buttAddEmployee = new Button ( "Add Employee" );

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
        gp.add ( pickerHireDate, 1, 4 );
        gp.add ( labelTerminationDate, 0, 5 );
        gp.add ( pickerTerminationDate, 1, 5 );
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
                    Chronology hdate = pickerHireDate.getChronology ( );
                    String hire = Timestamp.valueOf (
                            pickerHireDate.getValue ( ).atStartOfDay ( ) )
                            .toString ( );
                    String termination = null;
                    if ( pickerTerminationDate.getValue ( ) != null ) {
                        termination = Timestamp.valueOf (
                                pickerTerminationDate.getValue ( )
                                        .atStartOfDay ( ) ).toString ( );
                    }

                    Integer salaried =
                            checkIsSalaried.isSelected ( ) ? 0 : 1;
                    Double pay =
                            Double.parseDouble ( textPayRate.getText ( ) );
                    Database.addEmployee ( first, last, pos, hire,
                            termination, salaried, pay );
                    textFirstName.clear ( );
                    textLastName.clear ( );
                    menuPosition.setValue ( null );
                    pickerHireDate.setValue ( null );
                    pickerTerminationDate.setValue ( null );
                    checkIsSalaried.setSelected ( false );
                    textPayRate.clear ( );
                } );
        return gp;
    }

    public static VBox getSceneSearchEmployee ( ) {

        VBox boop = new VBox ( );

        Label labelCurrEmplOnly =
                new Label ( "Current employees" );
        ToggleButton toggleCurrEmplOnlyYes = new ToggleButton ( "Yes" );
        ToggleButton toggleCurrEmplOnlyNo  = new ToggleButton ( "No" );
        ToggleGroup  tgCurrEmpl            = new ToggleGroup ( );
        tgCurrEmpl.getToggles ( ).addAll ( toggleCurrEmplOnlyNo,
                toggleCurrEmplOnlyYes );

        Label title = new Label ( "Search Employee" );
        title.getStyleClass ( ).add ( Main.TITLE );

        TextField searchBar =
                new TextField ( );
        searchBar.setPromptText ( "Enter name, employee id, or position" );
        searchBar.setFocusTraversable ( false );
        TableView<Employee> resultsTable = new TableView<> ( );

//        searchBar.setOnMouseClicked (
//                event -> searchBar.selectAll ( )
//        );
//
        searchBar.setOnKeyReleased (
                event -> {
                    String val = searchBar.getText ( );
                    resultsTable.setItems ( Database.getEmployees ( val ) );
                }
        );

        TableColumn<Employee, Integer> colId = new TableColumn<> (
                "ID" );
        colId.setCellValueFactory ( new PropertyValueFactory<> ( "id" ) );
        colId.setPrefWidth ( 50 );

        TableColumn<Employee, String> colFirstName =
                new TableColumn<> ( "First " +
                        "Name" );
        colFirstName.setCellValueFactory (
                new PropertyValueFactory<> ( "firstName" ) );
        colFirstName.setPrefWidth ( 150 );

        TableColumn<Employee, String> colLastName =
                new TableColumn<> ( "Last Name" );
        colLastName.setCellValueFactory (
                new PropertyValueFactory<> ( "lastName" ) );
        colLastName.setPrefWidth ( 150 );

        TableColumn<Employee, String> colPosition =
                new TableColumn<> ( "Position" );
        colPosition.setCellValueFactory (
                new PropertyValueFactory<> ( "position" ) );
        colPosition.setPrefWidth ( 100 );

        TableColumn<Employee, String> colHireDate =
                new TableColumn<> ( "Hire Date" );
        colHireDate.setCellValueFactory (
                new PropertyValueFactory<> ( "hireDate" ) );
        colHireDate.setPrefWidth ( 100 );

        resultsTable.getColumns ( )
                .addAll ( colId, colFirstName, colLastName, colPosition,
                        colHireDate );

        HBox currEmplOnly = new HBox ( );
        currEmplOnly.getChildren ( )
                .addAll ( new Label ( "Current employees " +
                                "only" ), toggleCurrEmplOnlyNo,
                        toggleCurrEmplOnlyYes );
        boop.getChildren ( ).addAll ( title, searchBar, currEmplOnly,
                resultsTable );

        return boop;
    }
}
