/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class PatientDashboard extends Stage {

    private Scene patientManagmentScene;
    private Scene bookedwaitingScene;
    private Scene bookedfinishedScene;


    public PatientDashboard() throws IOException {

        FXMLLoader patientMLoader = new FXMLLoader(getClass().getResource("PatientFXML/PatientManagment.fxml"));
        Parent patientMRoot = patientMLoader.load();
        patientManagmentScene = new Scene(patientMRoot);

        FXMLLoader bookedwaitingLoader = new FXMLLoader(getClass().getResource("PatientFXML/bookedwaiting.fxml"));
        Parent bookedwaitingRoot = bookedwaitingLoader.load();
        bookedwaitingScene = new Scene(bookedwaitingRoot);

    FXMLLoader bookedfinishedLoader = new FXMLLoader(getClass().getResource("PatientFXML/bookedFinish.fxml"));
        Parent bookedfinishedRoot = bookedfinishedLoader.load();
        bookedfinishedScene = new Scene(bookedfinishedRoot);


        this.setScene(patientManagmentScene);
        this.setTitle("Patient Dashboard");

    }

    public void changeSceneToPatientManagment() {
        this.setScene(patientManagmentScene);
    }
    
    public void changeSceneTobookedwaiting() {
        this.setScene(bookedwaitingScene);
    }
 
    public void changeSceneTobookedFinished() {
        this.setScene(bookedfinishedScene);
    }


}
