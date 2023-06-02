package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class AdminDashboard extends Stage {

    // All Scenes that AdminDashboard page have
    private Scene usersManagmentScene;
    private Scene AddNewUserScene;

    private Scene AppointmentsManagmentScene;
    private Scene AddNewAppointmentScene;

    private Scene BookedAppointmentsManagmentScene;
//    private Scene AddNewBookedAppointmentsScene;

    //private Scene operationsScene;
    public AdminDashboard() throws IOException {

        //load UserManagment FXML File in UsersManagment Scene
        FXMLLoader usersMLoader = new FXMLLoader(getClass().getResource("AdminFXML/UsersManagment.fxml"));
        Parent usersMRoot = usersMLoader.load();
        usersManagmentScene = new Scene(usersMRoot);

//        load CreateUser FXML File in CreateUser Scene
        FXMLLoader AddNewUserLoader = new FXMLLoader(getClass().getResource("AdminFXML/AddNewUser.fxml"));
        Parent AddNewUserRoot = AddNewUserLoader.load();
        AddNewUserScene = new Scene(AddNewUserRoot);

        FXMLLoader AppointmentsManagmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/AppointmentsManagment.fxml"));
        Parent AppointmentsManagmentRoot = AppointmentsManagmentLoader.load();
        AppointmentsManagmentScene = new Scene(AppointmentsManagmentRoot);

        FXMLLoader AddNewAppointmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/AddNewAppointment.fxml"));
        Parent AddNewAppointmentRoot = AddNewAppointmentLoader.load();
        AddNewAppointmentScene = new Scene(AddNewAppointmentRoot);

        FXMLLoader BookedAppointmentsManagmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/booked_appointmentsMangment.fxml"));
        Parent BookedAppointmentsManagmentRoot = BookedAppointmentsManagmentLoader.load();
        BookedAppointmentsManagmentScene = new Scene(BookedAppointmentsManagmentRoot);

        // Set Main Scene in Admin Dasboard ( UsersManagment Scene )
        this.setScene(usersManagmentScene);
        this.setTitle("Admin Dashboard");

    }

    public void changeSceneToAddNewUser() {
        this.setScene(AddNewUserScene);
    }

    public void changeSceneToUsersManagment() {
        this.setScene(usersManagmentScene);
    }

    public void changeSceneToAppointmentsManagment() {
        this.setScene(AppointmentsManagmentScene);
    }

    public void changeSceneToAddNewAppointment() {
        this.setScene(AddNewAppointmentScene);
    }

    public void changeSceneToBookedAppointmentsManagmentScene() {
        this.setScene(BookedAppointmentsManagmentScene);
    }

}
