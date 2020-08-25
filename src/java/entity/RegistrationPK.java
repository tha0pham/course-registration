/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrator
 */
@Embeddable
public class RegistrationPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "STU_ID")
    private int stuId;
    @Basic(optional = false)
    @Column(name = "CLA_ID")
    private int claId;

    public RegistrationPK() {
    }

    public RegistrationPK(int stuId, int claId) {
        this.stuId = stuId;
        this.claId = claId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getClaId() {
        return claId;
    }

    public void setClaId(int claId) {
        this.claId = claId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) stuId;
        hash += (int) claId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationPK)) {
            return false;
        }
        RegistrationPK other = (RegistrationPK) object;
        if (this.stuId != other.stuId) {
            return false;
        }
        if (this.claId != other.claId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RegistrationPK[ stuId=" + stuId + ", claId=" + claId + " ]";
    }
    
}
