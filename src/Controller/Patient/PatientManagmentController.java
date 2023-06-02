package Controller.Patient;

import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.BookedAppointments;
import Model.BookedAppointmentsJpaController;
import Model.Users;
import Model.globalLoginUser;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PatientManagmentController implements Initializable {

    @FXML
    private TableColumn<Appointments, Integer> idCol;
    @FXML
    private TableColumn<Appointments, Date> DateCol;
    @FXML
    private TableColumn<Appointments, String> DayCol;
    @FXML
    private TableColumn<Appointments, String> TimeCol;
    @FXML
    private TableColumn<Appointments, String> statusCol;
    @FXML
    private Button bookBtn;
    @FXML
    private Button showBtn;
    private EntityManagerFactory emf;
    @FXML
    private TableView<Appointments> TapleViewAppointments;
    @FXML
    private Button BtnAppointmentFree;
    @FXML
    private Button BtnBookingW;
    @FXML
    private Button BtnBookingF;
    @FXML
    private Label labelUsername;
    @FXML
    private Button logouBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelUsername.setText(globalLoginUser.userLogedIn.getUsername());
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        DateCol.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        DayCol.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        TimeCol.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        this.emf = Persistence.createEntityManagerFactory("FBPU");
    }

    @FXML
    private void BookAppointment(ActionEvent event) {
        if (TapleViewAppointments.getSelectionModel().getSelectedItem() != null) {
            Appointments selectedUser = TapleViewAppointments.getSelectionModel().getSelectedItem();
            Integer id = selectedUser.getId();
            EntityManager em = emf.createEntityManager();
            Appointments newUser = new Appointments(id, selectedUser.getAppointmentDate(), selectedUser.getAppointmentDay(), selectedUser.getAppointmentTime(), "booked");
            newUser.setId(selectedUser.getId());

            BookedAppointments BA = new BookedAppointments();
            BA.setDoctorComment("waiting");
            BA.setStatus("waiting"); //
            BA.setAppointmentId(newUser);//
            Users userId = globalLoginUser.userLogedIn;
            BA.setUserId(userId);//

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(BA);
            transaction.commit();

            em.getTransaction().begin();
            em.merge(newUser);
            em.getTransaction().commit();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booked inserted");
            alert.setContentText("Booked Successfully");
            alert.showAndWait();
/// Alert here
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Appointment");
            warnAlert.setContentText("Please select an Appointment from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void showAppointment(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Appointments> listUsers = em.createNamedQuery("Appointments.findByStatus").setParameter("status", "free").getResultList();
        TapleViewAppointments.getItems().setAll(listUsers);
        em.close();
    }

    @FXML
    private void showAppointmentFree(ActionEvent event) {
        ViewManager.PatientPage.changeSceneToPatientManagment();

    }

    @FXML
    private void showBookingW(ActionEvent event) {
        ViewManager.PatientPage.changeSceneTobookedwaiting();
    }

    @FXML
    private void showBookingF(ActionEvent event) {
        ViewManager.PatientPage.changeSceneTobookedFinished();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closePatientPage();
        ViewManager.openLoginPatientPage();
    }

}
