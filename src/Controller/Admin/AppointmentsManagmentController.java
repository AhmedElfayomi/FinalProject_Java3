package Controller.Admin;

import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.exceptions.IllegalOrphanException;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
public class AppointmentsManagmentController implements Initializable {

    public static Appointments selectedAppointmentToUpdate;
    public static Stage updateStage;

    @FXML
    private Button AppointmentsBtn;
    @FXML
    private Button UsersBtn;
    @FXML
    private Button bookedBtn;
    @FXML
    private TableColumn<Appointments, Integer> idCol;
    @FXML
    private TableColumn<Appointments, Date> dateCol;
    @FXML
    private TableColumn<Appointments, String> dayCol;
    @FXML
    private TableColumn<Appointments, String> timeCol;
    @FXML
    private TableColumn<Appointments, String> statusCol;
    @FXML
    private Button AddAppointmentBtn;
    @FXML
    private Button UpdateAppointmentBtn;
    @FXML
    private Button DeleteAppointmentBtn;
    private EntityManagerFactory emf;
    @FXML
    private Button showBtn;
    @FXML
    private TableView<Appointments> AppointmentTableView;
    @FXML
    private Button logoutBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        dayCol.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        timeCol.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        this.emf = Persistence.createEntityManagerFactory("FBPU");

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
    private void AddNewAppointment(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAddNewAppointment();
    }

    @FXML
    private void UpdateAppointment(ActionEvent event) throws IOException {
        Appointments selected = AppointmentTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selectedAppointmentToUpdate = selected;
            FXMLLoader UpdateUserLoader = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAppointment.fxml"));
            Parent UpdateUserRoot = UpdateUserLoader.load();
            Scene UpdateUserScene = new Scene(UpdateUserRoot);
            //store loaded fxml in our global stage updateStage and show it
            updateStage = new Stage();
            updateStage.setScene(UpdateUserScene);
            updateStage.setTitle("Update Appointment ");
            updateStage.show();

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Appointment");
            warnAlert.setContentText("Please select an Appointment from the table view");
            warnAlert.show();
        }

    }

    @FXML
    private void DeleteAppointment(ActionEvent event) {
        if (AppointmentTableView.getSelectionModel().getSelectedItem() != null) {
            Appointments selectedUser = AppointmentTableView.getSelectionModel().getSelectedItem();
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our User model
                        Integer id = selectedUser.getId();
                        EntityManager em = emf.createEntityManager();
                        AppointmentsJpaController usersController = new AppointmentsJpaController(emf);
                        usersController.destroy(id);

                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Appointment deleted");
                    deletedSuccessAlert.setContentText("Appointment deleted");
                    deletedSuccessAlert.show();
                }
            });
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Appointment");
            warnAlert.setContentText("Please select an Appointment from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void showAppointments(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Appointments> listUsers = em.createNamedQuery("Appointments.findByStatus").setParameter("status", "free").getResultList();
        AppointmentTableView.getItems().setAll(listUsers);
        em.close();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closeAdminPage();
        ViewManager.openLoginPatientPage();
    }

}
