/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Users;
import Model.UsersJpaController;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class UpdateUserController implements Initializable {

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
    private Button UpdateUserBtn;
    private EntityManagerFactory emf;

    private Users oldUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.oldUser = Controller.Admin.UsersManagmentController.selectedUsertoUpdate;
        usernameTF.setText(oldUser.getUsername());
        passwordTF.setText(oldUser.getPassword());
        firstnameTF.setText(oldUser.getFirstname());
        lastnameTF.setText(oldUser.getLastname());
        String age = String.valueOf(oldUser.getAge());
        ageTF.setText(age);
        emailTF.setText(oldUser.getEmail());
        phoneTF.setText(oldUser.getPhone());
        emailTF.setText(oldUser.getEmail());

        this.emf = Persistence.createEntityManagerFactory("FBPU");

    }

    @FXML
    private void cancelToMangment(ActionEvent event) {
        UsersManagmentController.updateStage.close();
    }

    @FXML
    private void UpdateUser(ActionEvent event) throws NonexistentEntityException, Exception {

        String firstname = firstnameTF.getText();
        String lastname = lastnameTF.getText();
        String username = usernameTF.getText();
        int age = Integer.parseInt(ageTF.getText());
        String phone = phoneTF.getText();
        String email = emailTF.getText();
        String password = passwordTF.getText();
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton) RoleGroup.getSelectedToggle()).getText();

        Users newUser = new Users(Integer.MAX_VALUE, username, password, firstname, lastname, age, email, phone, gender, role);
        newUser.setId(oldUser.getId());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(newUser);
        em.getTransaction().commit();
        em.close();
        emf.close();
        UsersManagmentController.updateStage.close();

    }
}
