/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Patient;

import Model.Appointments;
import Model.BookedAppointments;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class BookedwaitingController implements Initializable {

    @FXML
    private Button BtnAppointmentFree;
    @FXML
    private Button BtnBookingW;
    @FXML
    private Button BtnBookingF;
    @FXML
    private TableView<BookedAppointments> TableViewBookedW;
    @FXML
    private TableColumn<BookedAppointments, Integer> idCol;
    @FXML
    private TableColumn<BookedAppointments, Date> dateCol;
    @FXML
    private TableColumn<BookedAppointments, String> dayCol;
    @FXML
    private TableColumn<BookedAppointments, String> timeCol;
    @FXML
    private TableColumn<BookedAppointments, String> statusCol;
    @FXML
    private Button showBtn;
    private EntityManagerFactory emf;
    @FXML
    private Button logouBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        dayCol.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        timeCol.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        this.emf = Persistence.createEntityManagerFactory("FBPU");
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
    private void showBookedW(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        Users UserID = globalLoginUser.userLogedIn;

        String jpql = "SELECT b FROM BookedAppointments b "
                + "JOIN FETCH b.userId "
                + "JOIN FETCH b.appointmentId "
                + "WHERE b.userId.id = :USERid And b.status = :status";

        List<BookedAppointments> listUsers = em.createQuery(jpql).setParameter("status", "waiting").setParameter("USERid", UserID.getId()).getResultList();
        TableViewBookedW.getItems().setAll(listUsers);
        em.close();

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closePatientPage();
        ViewManager.openLoginPatientPage();
    }

}
