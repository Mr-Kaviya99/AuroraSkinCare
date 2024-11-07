package model;
/**
 * @author K2460782
 */
public class Patient {
    private String patientNIC;
    private String patientName;
    private String patientEmail;
    private String patientTelephone;

    public String getPatientNIC() {
        return patientNIC;
    }

    public void setPatientNIC(String patientNIC) {
        this.patientNIC = patientNIC;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientTelephone() {
        return patientTelephone;
    }

    public void setPatientTelephone(String patientTelephone) {
        this.patientTelephone = patientTelephone;
    }

    public Patient() {
    }

    public Patient(String NIC, String name, String email, String telephone) {
        this.patientNIC = NIC;
        this.patientName = name;
        this.patientEmail = email;
        this.patientTelephone = telephone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientNIC='" + patientNIC + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", patientTelephone='" + patientTelephone + '\'' +
                '}';
    }
}
