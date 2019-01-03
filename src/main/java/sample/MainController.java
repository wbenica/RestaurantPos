package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import skus.SkusController;

import static employees.EmployeeController.getSceneAddEmployee;
import static employees.EmployeeController.getSceneSearchEmployee;

public class MainController {

    public static Scene getMain ( ) {

        VBox mainWindow = new VBox ( );
        mainWindow.getStyleClass ( ).add ( "main" );
        mainWindow.getChildren ( ).add ( getMenuBar ( mainWindow ) );
        Label start = new Label ( "Welcome" );
        start.getStyleClass ( ).add ( "start" );
        mainWindow.getChildren ( ).add ( start );

        Scene scene = new Scene ( mainWindow );
        scene.getStylesheets ( ).addAll ( "controls.css", "menu.css" );

        return scene;
    }

    private static MenuBar getMenuBar ( Pane main ) {

        MenuBar menuBar = new MenuBar ( );

        Menu menuEmployees = new Menu ( "Employees" );

        MenuItem addEmployee = new MenuItem ( "Add employee" );
        addEmployee.setOnAction (
                event -> setMain ( getSceneAddEmployee ( ), main ) );

        MenuItem findEmployee = new MenuItem ( "Search" );
        findEmployee.setOnAction (
                event -> setMain ( getSceneSearchEmployee ( ), main )
        );

        menuEmployees.getItems ( ).addAll ( addEmployee, findEmployee );

        Menu menuSkus = new Menu ( "SKUs" );

        MenuItem editSkus = new MenuItem ( "Edit SKUs" );
        editSkus.setOnAction (
                event -> setMain ( SkusController.getSceneEditSkus ( ), main )
        );

        menuSkus.getItems ( ).addAll ( editSkus );
        menuBar.getMenus ( ).addAll ( menuEmployees, menuSkus );
        return menuBar;
    }

    private static void setMain ( Pane scene,
                                  Pane mainWindow ) {

        mainWindow.getChildren ( ).remove ( 1 );
        scene.setPadding ( new Insets ( 0, 24, 24, 24 ) );
        mainWindow.getChildren ( ).add ( scene );
    }
}
