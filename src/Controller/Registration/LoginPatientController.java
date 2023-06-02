/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Registration;

import Model.Users;
import Model.globalLoginUser;
import View.LoginPatient;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class LoginPatientController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button LoginAdminBtn;
    @FXML
    private Button loginPBtn;
    @FXML
    private Button BtnRegister;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginToPatientDash(ActionEvent event) throws IOException {
         String username = usernameTF.getText();
        String password = passwordTF.getText();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FBPU");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Users.findByUserAndPasswordPatient").setParameter("username", username).setParameter("password", password);
        Users user = (Users) query.getSingleResult();
        if(user != null){
            globalLoginUser.userLogedIn = user ;
            ViewManager.openPatientPage();
            ViewManager.closeLoginPatientPage();
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
        }  else {
            System.out.println("Invalid username or password");
        }
    }

    @FXML
    private void RegisterPatient(ActionEvent event) throws IOException {
            ViewManager.openRegisterPatientPage();
            ViewManager.closeLoginPatientPage();
    }

    @FXML
    private void LoginAdminPage(ActionEvent event) throws IOException {
            ViewManager.closeLoginPatientPage();
            ViewManager.openLoginAdminPage();
        
    }
    
}
