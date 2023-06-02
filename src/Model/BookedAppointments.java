/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "booked_appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookedAppointments.findAll", query = "SELECT b FROM BookedAppointments b"),
    @NamedQuery(name = "BookedAppointments.findById", query = "SELECT b FROM BookedAppointments b WHERE b.id = :id"),
    @NamedQuery(name = "BookedAppointments.findByStatus", query = "SELECT b FROM BookedAppointments b WHERE b.status = :status"),
    @NamedQuery(name = "BookedAppointments.findByDoctorComment", query = "SELECT b FROM BookedAppointments b WHERE b.doctorComment = :doctorComment"),
    @NamedQuery(name = "BookedAppointments.findByfirstname", query = "SELECT b FROM BookedAppointments b JOIN FETCH b.userId JOIN FETCH b.appointmentId  WHERE b.userId.firstname = :firstname"),
    @NamedQuery(name = "BookedAppointments.findByAll2", query = "SELECT b FROM BookedAppointments b JOIN FETCH b.userId JOIN FETCH b.appointmentId")})

public class BookedAppointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Column(name = "doctor_comment")
    private String doctorComment;
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Appointments appointmentId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
 @Transient
    private String firstname;

    public String getFirstname() {
        return userId != null ? userId.getFirstname() : null;
    }
    @Transient
    private String lastname;

    public String getLastname() {
        return userId != null ? userId.getLastname() : null;
    }
    @Transient
    private String username;

    public String getUsername() {
        return userId != null ? userId.getUsername() : null;
    }

    @Transient
    private String appointmentDay;

    public String getAppointmentDay() {
        return appointmentId != null ? appointmentId.getAppointmentDay() : null;
    }

    @Transient
    private String appointmentDate;

    public Date getAppointmentDate() {
        return appointmentId != null ? appointmentId.getAppointmentDate() : null;
    }
    @Transient
    private String appointmentTime;

    public String getAppointmentTime() {
        return appointmentId != null ? appointmentId.getAppointmentTime() : null;
    }
    public BookedAppointments() {
    }

    public BookedAppointments(Integer id) {
        this.id = id;
    }

    public BookedAppointments(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookedAppointments)) {
            return false;
        }
        BookedAppointments other = (BookedAppointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.BookedAppointments[ id=" + id + " ]";
    }
    
}
