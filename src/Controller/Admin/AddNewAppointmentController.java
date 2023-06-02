package Controller.Admin;

import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.UsersJpaController;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class AddNewAppointmentController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private DatePicker dateTF;
    @FXML
    private ComboBox<String> Day;
    @FXML
    private TextField TimeTF;
    @FXML
    private Button AddAppointmentBtn;
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
        Day.setItems(FXCollections.observableArrayList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

    }

    @FXML
    private void cancelToMangment(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAppointmentsManagment();
    }

    @FXML
    private void AddNewAppointment(ActionEvent event) {
        String day = Day.getValue();
        String Time = TimeTF.getText();
        Date date = Date.valueOf(dateTF.getValue());
        String Status = "free";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FBPU");
        AppointmentsJpaController AppointmentController = new AppointmentsJpaController(emf);
        Appointments Appointment = new Appointments(1, date, day, Time, Status);
        AppointmentController.create(Appointment);
        // Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment inserted");
        alert.setContentText("Appointment inserted Successfully");
        alert.showAndWait();
        //clear After insert    
        TimeTF.clear();
        dateTF.setValue(null);
        Day.setValue("");
        ViewManager.adminPage.changeSceneToAppointmentsManagment();
    }

    @FXML
    private void showPageUsers(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showPageAppointments(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAppointmentsManagment();
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
