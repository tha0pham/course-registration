/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "classes", catalog = "project_sms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clazz.findAll", query = "SELECT c FROM Clazz c"),
    @NamedQuery(name = "Clazz.findById", query = "SELECT c FROM Clazz c WHERE c.id = :id"),
    @NamedQuery(name = "Clazz.findByName", query = "SELECT c FROM Clazz c WHERE c.name = :name"),
    @NamedQuery(name = "Clazz.findByWeek", query = "SELECT c FROM Clazz c WHERE c.week = :week"),
    @NamedQuery(name = "Clazz.findByCapacity", query = "SELECT c FROM Clazz c WHERE c.capacity = :capacity"),
    @NamedQuery(name = "Clazz.findByMaxAbsent", query = "SELECT c FROM Clazz c WHERE c.maxAbsent = :maxAbsent"),
    @NamedQuery(name = "Clazz.findByMidterm", query = "SELECT c FROM Clazz c WHERE c.midterm = :midterm"),
    @NamedQuery(name = "Clazz.findByFinalExam", query = "SELECT c FROM Clazz c WHERE c.finalExam = :finalExam"),
    @NamedQuery(name = "Clazz.findByAssignment", query = "SELECT c FROM Clazz c WHERE c.assignment = :assignment")})
public class Clazz implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "WEEK")
    private Integer week;
    @Column(name = "CAPACITY")
    private Integer capacity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MAX_ABSENT")
    private double maxAbsent;
    @Column(name = "MIDTERM")
    private Integer midterm;
    @Column(name = "FINAL_EXAM")
    private Integer finalExam;
    @Column(name = "ASSIGNMENT")
    private Integer assignment;
    @JoinColumn(name = "YEAR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Year year;
    @JoinColumn(name = "SEMESTER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Semester semester;
    @JoinColumn(name = "COURSE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "INS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Instructor insId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clazz")
    private List<Registration> registrationList;

    public Clazz() {
    }

    public Clazz(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public double getMaxAbsent() {
        return maxAbsent;
    }

    public void setMaxAbsent(double maxAbsent) {
        this.maxAbsent = maxAbsent;
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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInsId() {
        return insId;
    }

    public void setInsId(Instructor insId) {
        this.insId = insId;
    }

    @XmlTransient
    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registrationList) {
        this.registrationList = registrationList;
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
        if (!(object instanceof Clazz)) {
            return false;
        }
        Clazz other = (Clazz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Clazz[ id=" + id + " ]";
    }
    
}
