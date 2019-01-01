package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static employees.EmployeeController.getSceneAddEmployee;
import static employees.EmployeeController.getSceneSearchEmployee;

public class MainController {

    public static Scene getMain ( ) {

        VBox mainWindow = new VBox ( );

        MenuBar  menuBar       = new MenuBar ( );
        Menu     menuEmployees = new Menu ( "Employees" );
        MenuItem addEmployee   = new MenuItem ( "Add employee" );
        addEmployee.setOnAction (
                event -> {
                    setMain ( getSceneAddEmployee ( ), mainWindow );
                } );
        MenuItem findEmployee = new MenuItem ( "Search" );
        findEmployee.setOnAction (
                event -> { setMain ( getSceneSearchEmployee ( ), mainWindow );}
        );
        menuEmployees.getItems ( ).addAll ( addEmployee, findEmployee );
        menuBar.getMenus ( ).addAll ( menuEmployees );
        mainWindow.getChildren ( ).add ( menuBar );

        mainWindow.getChildren ( ).add ( new Label ( "Welcome" ) );

        Scene scene = new Scene ( mainWindow );
        scene.getStylesheets ( ).add ( "styles.css" );

        return scene;
    }

    private static void setMain ( Pane scene,
                                  Pane mainWindow ) {

        mainWindow.getChildren ( ).remove ( 1 );
        mainWindow.getChildren ( ).add ( scene );
    }
}
