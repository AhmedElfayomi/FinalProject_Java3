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
public class LoginAdmin extends Stage {

    private Scene LoginAdminScene;

    public LoginAdmin() throws IOException {

        //load UserManagment FXML File in UsersManagment Scene
        FXMLLoader LoginAdminLoader = new FXMLLoader(getClass().getResource("Registration/LoginAdmin.fxml"));
        Parent LoginAdminRoot = LoginAdminLoader.load();
        LoginAdminScene = new Scene(LoginAdminRoot);

        this.setScene(LoginAdminScene);
        this.setTitle("Login Admin");

    }

}