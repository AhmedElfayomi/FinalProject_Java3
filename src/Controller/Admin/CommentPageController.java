/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.BookedAppointments;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class CommentPageController implements Initializable {

    @FXML
    private Button FinishBtn;
    @FXML
    private TextArea commentTA;
    private BookedAppointments BAOld;
    private EntityManagerFactory emf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.emf = Persistence.createEntityManagerFactory("FBPU");

    }

    @FXML
    private void FinishAppointment(ActionEvent event) {
        String Comment = commentTA.getText();
        BAOld = Booked_appointmentsMangmentController.selectedAppointmentToFinish;

        BookedAppointments newBA = new BookedAppointments(1, BAOld.getStatus());
        newBA.setId(BAOld.getId());
        newBA.setAppointmentId(BAOld.getAppointmentId());
        newBA.setUserId(BAOld.getUserId());
        newBA.setDoctorComment(Comment);
        newBA.setStatus("finished");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(newBA);
        em.getTransaction().commit();
        em.close();
        emf.close();
        Booked_appointmentsMangmentController.CommentStage.close();
    }

}
