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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Noel
 */
@Embeddable
public class LogbookPK implements Serializable {

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "EMAIL",nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "GEOCACHE",nullable = false)
    private int geocache;

    public LogbookPK() {
    }

    public LogbookPK(String email, int geocache) {
        this.email = email;
        this.geocache = geocache;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGeocache() {
        return geocache;
    }

    public void setGeocache(int geocache) {
        this.geocache = geocache;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        hash += (int) geocache;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogbookPK)) {
            return false;
        }
        LogbookPK other = (LogbookPK) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        if (this.geocache != other.geocache) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LogbookPK[ email=" + email + ", geocache=" + geocache + " ]";
    }
    
}
