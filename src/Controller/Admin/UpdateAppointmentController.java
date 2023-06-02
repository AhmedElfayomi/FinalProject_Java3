/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Appointments;
import View.ViewManager;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class UpdateAppointmentController implements Initializable {

    private Appointments oldAppointment;

    @FXML
    private Button cancelBtn;
    @FXML
    private Button UpdateAppointmentBtn;
    @FXML
    private DatePicker dateTF;
    @FXML
    private ComboBox<String> Day;
    @FXML
    private TextField TimeTF;
    private EntityManagerFactory emf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Day.setItems(FXCollections.observableArrayList("Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"));

        this.oldAppointment = AppointmentsManagmentController.selectedAppointmentToUpdate;

        //set text field's data the same as updatedUser data
        Day.setValue(oldAppointment.getAppointmentDay());
        TimeTF.setText(oldAppointment.getAppointmentTime());
        this.emf = Persistence.createEntityManagerFactory("FBPU");

    }

    @FXML
    private void cancelToMangment(ActionEvent event) {
        AppointmentsManagmentController.updateStage.close();
    }

    @FXML
    private void UpdateAppointment(ActionEvent event) {
        String day = Day.getValue();
        String Time = TimeTF.getText();
        Date date = Date.valueOf(dateTF.getValue());
        String Status = "free";

        Appointments newAppointment = new Appointments(1, date, day, Time, Status);
        newAppointment.setId(oldAppointment.getId());

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(newAppointment);
        em.getTransaction().commit();
        em.close();
        emf.close();
        AppointmentsManagmentController.updateStage.close();
    }

}
