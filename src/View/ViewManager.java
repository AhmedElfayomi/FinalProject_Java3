/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;

/**
 *
 * @author Ahmed
 */
public class ViewManager {
    public static RegisterPatient RegisterPatientPage;
    public static LoginPatient LoginPatientPage;
    public static LoginAdmin LoginAdminPage;
    public static AdminDashboard adminPage;
    public static PatientDashboard PatientPage;



    private ViewManager() {
    }


   
    public static void openRegisterPatientPage() throws IOException {
        if (RegisterPatientPage == null) {
            RegisterPatientPage = new RegisterPatient();
            RegisterPatientPage.show();
        } else {
            RegisterPatientPage.show();
        }

    }

    public static void closeRegisterPatientPage() {
        if (RegisterPatientPage != null) {
            RegisterPatientPage.close();
        }
    }
    public static void openLoginPatientPage() throws IOException {
        if (LoginPatientPage == null) {
            LoginPatientPage = new LoginPatient();
            LoginPatientPage.show();
        } else {
            LoginPatientPage.show();
        }

    }

    public static void closeLoginPatientPage() {
        if (LoginPatientPage != null) {
            LoginPatientPage.close();
        }
    }

    public static void openLoginAdminPage() throws IOException {
        if (LoginAdminPage == null) {
            LoginAdminPage = new LoginAdmin();
            LoginAdminPage.show();
        } else {
            LoginAdminPage.show();
        }

    }

    public static void closeLoginAdminPage() {
        if (LoginAdminPage != null) {
            LoginAdminPage.close();
        }
    }

  public static void openAdminPage() throws IOException {
        if (adminPage == null) {
            adminPage = new AdminDashboard();
            adminPage.show();
        } else {
            adminPage.show();
        }

    }

    public static void closeAdminPage() {
        if (adminPage != null) {
            adminPage.close();
        }
    }
 
    public static void openPatientPage() throws IOException {
        if (PatientPage == null) {
            PatientPage = new PatientDashboard();
            PatientPage.show();
        } else {
            PatientPage.show();
        }

    }

    public static void closePatientPage() {
        if (PatientPage != null) {
            PatientPage.close();  
        }
    }
    
       
        
    
    

}
