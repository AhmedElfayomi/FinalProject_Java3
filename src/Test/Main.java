package Test;

import View.ViewManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 /**
 *
 * @author Ahmed
 */

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
      ViewManager.openLoginPatientPage();
      /*
      احمد هشام الفيومي --- 120212266
      أيمن وليد شويدح   --- 120212346
      
      */
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }

 
}
