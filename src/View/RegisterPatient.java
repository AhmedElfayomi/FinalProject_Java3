
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class RegisterPatient extends Stage {

    private Scene RigesterPatientScene;

    public RegisterPatient() throws IOException {

        //load UserManagment FXML File in UsersManagment Scene
        FXMLLoader RigesterPatientLoader = new FXMLLoader(getClass().getResource("Registration/RegisterPatient.fxml"));
        Parent RigesterPatientRoot = RigesterPatientLoader.load();
        RigesterPatientScene = new Scene(RigesterPatientRoot);

        this.setScene(RigesterPatientScene);
        this.setTitle("Register Patient");

    }

}