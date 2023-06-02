package Controller.Admin;

import Model.Users;
import Model.UsersJpaController;
import Model.exceptions.IllegalOrphanException;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class UsersManagmentController implements Initializable {

    @FXML
    private TableView<Users> UsersTableView;
    @FXML
    private TableColumn<Users, Integer> idCol;
    @FXML
    private TableColumn<Users, String> usernameCol;
    @FXML
    private TableColumn<Users, String> firstnameCol;
    @FXML
    private TableColumn<Users, String> lastnameCol;
    @FXML
    private TableColumn<Users, Integer> ageCol;
    @FXML
    private TableColumn<Users, String> emailCol;
    @FXML
    private TableColumn<Users, String> phoneCol;
    @FXML
    private TableColumn<Users, String> genderCol;
    @FXML
    private TableColumn<Users, String> roleCol;
    @FXML
    private TextField searchTF;
    @FXML
    private Button AddUserBtn;
    @FXML
    private Button UpdateBtn;
    @FXML
    private Button DeleteBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button showAllBtn;
    private EntityManagerFactory emf;
    @FXML
    private Button AppointmentsBtn;
    @FXML
    private Button UsersBtn;
    @FXML
    private Button bookedBtn;

    public static Users selectedUsertoUpdate;
    public static Stage updateStage;
    @FXML
    private Button logoutBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        ageCol.setCellValueFactory(new PropertyValueFactory("age"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        roleCol.setCellValueFactory(new PropertyValueFactory("role"));
        this.emf = Persistence.createEntityManagerFactory("FBPU");
    }

    @FXML
    private void AddNewUser(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAddNewUser();
    }

    @FXML
    private void searchFirstName(ActionEvent event) {
        String search = searchTF.getText();
        EntityManager em = emf.createEntityManager();
        List<Users> listUsers = em.createNamedQuery("Users.findByFirstname").setParameter("firstname", search).getResultList();
        UsersTableView.getItems().setAll(listUsers);
        em.close();
    }

    @FXML
    private void showAllUser(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Users> listUsers = em.createNamedQuery("Users.findAll").getResultList();
        UsersTableView.getItems().setAll(listUsers);
        em.close();
    }

    @FXML
    private void UpdateUser(ActionEvent event) throws NonexistentEntityException, Exception {
        Users selected = UsersTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedUsertoUpdate = selected;
            FXMLLoader UpdateUserLoader = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateUser.fxml"));
            Parent UpdateUserRoot = UpdateUserLoader.load();
            Scene UpdateUserScene = new Scene(UpdateUserRoot);
            updateStage = new Stage();
            updateStage.setScene(UpdateUserScene);
            updateStage.setTitle("Update user " + selectedUsertoUpdate.getUsername());
            updateStage.show();

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }

    }

    @FXML
    private void DeleteUser(ActionEvent event) {
        if (UsersTableView.getSelectionModel().getSelectedItem() != null) {
            Users selectedUser = UsersTableView.getSelectionModel().getSelectedItem();
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Integer id = selectedUser.getId();
                        EntityManager em = emf.createEntityManager();
                        UsersJpaController usersController = new UsersJpaController(emf);
                        usersController.destroy(id);

                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("User deleted");
                    deletedSuccessAlert.setContentText("User deleted");
                    deletedSuccessAlert.show();
                }
            });
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void showPageAppointments(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAppointmentsManagment();
    }

    @FXML
    private void showPageUsers(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showPagebooked(ActionEvent event) {
        ViewManager.adminPage.changeSceneToBookedAppointmentsManagmentScene();

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closeAdminPage();
        ViewManager.openLoginPatientPage();
    }

}
