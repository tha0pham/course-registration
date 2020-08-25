/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "registration", catalog = "project_sms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByStuId", query = "SELECT r FROM Registration r WHERE r.registrationPK.stuId = :stuId"),
    @NamedQuery(name = "Registration.findByClaId", query = "SELECT r FROM Registration r WHERE r.registrationPK.claId = :claId"),
    @NamedQuery(name = "Registration.findByAverage", query = "SELECT r FROM Registration r WHERE r.average = :average"),
    @NamedQuery(name = "Registration.findByMidterm", query = "SELECT r FROM Registration r WHERE r.midterm = :midterm"),
    @NamedQuery(name = "Registration.findByFinalExam", query = "SELECT r FROM Registration r WHERE r.finalExam = :finalExam"),
    @NamedQuery(name = "Registration.findByAssignment", query = "SELECT r FROM Registration r WHERE r.assignment = :assignment")})
public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistrationPK registrationPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AVERAGE")
    private double average;
    @Column(name = "MIDTERM")
    private Integer midterm;
    @Column(name = "FINAL_EXAM")
    private Integer finalExam;
    @Column(name = "ASSIGNMENT")
    private Integer assignment;
    @JoinColumn(name = "STU_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "CLA_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clazz clazz;

    public Registration() {
    }

    public Registration(RegistrationPK registrationPK) {
        this.registrationPK = registrationPK;
    }

    public Registration(int stuId, int claId) {
        this.registrationPK = new RegistrationPK(stuId, claId);
    }

    public RegistrationPK getRegistrationPK() {
        return registrationPK;
    }

    public void setRegistrationPK(RegistrationPK registrationPK) {
        this.registrationPK = registrationPK;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Integer getMidterm() {
        return midterm;
    }

    public void setMidterm(Integer midterm) {
        this.midterm = midterm;
    }

    public Integer getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Integer finalExam) {
        this.finalExam = finalExam;
    }

    public Integer getAssignment() {
        return assignment;
    }

    public void setAssignment(Integer assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registrationPK != null ? registrationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.registrationPK == null && other.registrationPK != null) || (this.registrationPK != null && !this.registrationPK.equals(other.registrationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Registration[ registrationPK=" + registrationPK + " ]";
    }
    
}
