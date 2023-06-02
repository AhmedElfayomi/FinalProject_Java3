/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class LoginPatient extends Stage {

    private Scene LoginPatientScene;

    public LoginPatient() throws IOException {

        //load UserManagment FXML File in UsersManagment Scene
        FXMLLoader LoginPatientLoader = new FXMLLoader(getClass().getResource("Registration/LoginPatient.fxml"));
        Parent LoginPatientRoot = LoginPatientLoader.load();
        LoginPatientScene = new Scene(LoginPatientRoot);

        this.setScene(LoginPatientScene);
        this.setTitle("Login Patient");

    }

}