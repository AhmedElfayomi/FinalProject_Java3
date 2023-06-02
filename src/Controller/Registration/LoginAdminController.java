package Controller.Registration;

import Model.Users;
import Model.globalLoginUser;
import View.LoginAdmin;
import View.RegisterPatient;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
public class LoginAdminController implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button CancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void logoutHomePage(ActionEvent event) {
        try {
            ViewManager.openPatientPage();
            ViewManager.closeLoginAdminPage();
        } catch (IOException ex) {
            Logger.getLogger(LoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginToAdminDash(ActionEvent event) throws IOException {
       String username = usernameTF.getText();
        String password = passwordTF.getText();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FBPU");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createNamedQuery("Users.findByUserAndPasswordAdmin").setParameter("username", username).setParameter("password", password);

        Users user = (Users) query.getSingleResult();
        if(user != null){
            globalLoginUser.userLogedIn = user ;
            ViewManager.openAdminPage();
            ViewManager.closeLoginAdminPage();
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
        }  else {
            System.out.println("Invalid username or password");
        }
    /*    if (list.size() > 0) {
            ViewManager.openAdminPage();
            ViewManager.closeLoginAdminPage();
            for (Users user : list) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
            }
        } else {
            System.out.println("Invalid username or password");
        }
//        list.forEach(e ->  username.equals(e.getUsername()+ " ----- " + password.equals(e.getPassword())));

       */
//      ViewManager.openAdminPage();
//      ViewManager.closeLoginAdminPage();
    } 

    @FXML
    private void CancelLoginAdmin(ActionEvent event) throws IOException {
        ViewManager.openLoginPatientPage();
        ViewManager.closeLoginAdminPage();
    }

}
