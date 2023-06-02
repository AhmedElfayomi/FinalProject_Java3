package Controller.Admin;

import static Controller.Admin.AppointmentsManagmentController.selectedAppointmentToUpdate;
import static Controller.Admin.AppointmentsManagmentController.updateStage;
import Model.Appointments;
import Model.BookedAppointments;
import Model.Users;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class Booked_appointmentsMangmentController implements Initializable {

    public static BookedAppointments selectedAppointmentToFinish;
    public static Stage CommentStage;

    @FXML
    private Button FinishBtn;
    @FXML
    private Button showBtn;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchBtn;
    @FXML
    private TableColumn<BookedAppointments, Integer> idCol;
    @FXML
    private TableColumn<BookedAppointments, Integer> Appointment_idCol;
    @FXML
    private TableColumn<BookedAppointments, Integer> user_idCol;
    @FXML
    private TableColumn<BookedAppointments, String> statusCol;
    @FXML
    private TableColumn<BookedAppointments, String> doctor_commentCol;
    @FXML
    private TableColumn<BookedAppointments, String> firstnameCol;
    @FXML
    private TableColumn<BookedAppointments, String> UsernameCol;
    @FXML
    private TableColumn<BookedAppointments, String> lastnameCol;

    @FXML
    private TableView<BookedAppointments> BookedTableView;
    private EntityManagerFactory emf;
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

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        Appointment_idCol.setCellValueFactory(new PropertyValueFactory("appointmentId"));
        user_idCol.setCellValueFactory(new PropertyValueFactory("userId"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        doctor_commentCol.setCellValueFactory(new PropertyValueFactory("doctorComment"));
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
    private void FinishBooked(ActionEvent event) throws IOException {
        BookedAppointments selected = BookedTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            selectedAppointmentToFinish = selected;
            if (!selected.getStatus().equals("finished")) {
                FXMLLoader CommentLoader = new FXMLLoader(getClass().getResource("/View/AdminFXML/CommentPage.fxml"));
                Parent CommentRoot = CommentLoader.load();
                Scene CommentScene = new Scene(CommentRoot);
                CommentStage = new Stage();
                CommentStage.setScene(CommentScene);
                CommentStage.setTitle("Comment Appointment ");
                CommentStage.show();
            } else {
                Alert warnAlert = new Alert(Alert.AlertType.WARNING);
                warnAlert.setTitle("Finished Appointment");
                warnAlert.setContentText("This Appointment is Finished");
                warnAlert.show();

            }
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Appointment");
            warnAlert.setContentText("Please select an Appointment from the table view");
            warnAlert.show();
        }

    }

    @FXML
    private void showBooked(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<BookedAppointments> list = em.createNamedQuery("BookedAppointments.findByAll2").getResultList();
        BookedTableView.getItems().setAll(list);
        em.close();
    }

    @FXML
    private void searchFirstName(ActionEvent event) {
        String search = searchTF.getText();
        EntityManager em = emf.createEntityManager();
        try {
            List<BookedAppointments> list = em.createNamedQuery("BookedAppointments.findByfirstname").setParameter("firstname", search).getResultList();
            BookedTableView.getItems().setAll(list);
        } finally {
            em.close();
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closeAdminPage();
        ViewManager.openLoginPatientPage();
    }

}
