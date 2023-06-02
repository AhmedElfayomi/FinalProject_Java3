/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Users;
import Model.UsersJpaController;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AddNewUserController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup RoleGroup;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button AddUserBtn;
    @FXML
    private Button UsersBtn;
    @FXML
    private Button AppointmentsBtn;
    @FXML
    private Button bookedBtn;
    @FXML
    private Button logoutBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelToMangment(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void AddNewUser(ActionEvent event) throws IOException {
        String firstname = firstnameTF.getText();
        String lastname = lastnameTF.getText();
        String username = usernameTF.getText();
        int age = Integer.parseInt(ageTF.getText());
        String phone = phoneTF.getText();
        String email = emailTF.getText();
        String password = passwordTF.getText();
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) RoleGroup.getSelectedToggle()).getText();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FBPU");
        UsersJpaController usersController = new UsersJpaController(emf);
        Users user = new Users(1, username, password, firstname, lastname, age, email, phone, gender, role);
        usersController.create(user);
        ////////////////////////
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted Successfully");
        alert.showAndWait();
    }

    @FXML
    private void showPageUsers(ActionEvent event) {
    }

    @FXML
    private void showPageAppointments(ActionEvent event) {
    }

    @FXML
    private void showPagebooked(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closeAdminPage();
        ViewManager.openLoginPatientPage();
    }

}
