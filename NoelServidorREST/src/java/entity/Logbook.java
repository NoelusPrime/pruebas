/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noel
 */
@Entity
@Table(name = "LOGBOOK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logbook.findAll", query = "SELECT l FROM Logbook l")
    , @NamedQuery(name = "Logbook.findByEmail", query = "SELECT l FROM Logbook l WHERE l.logbookPK.email = :email")
    , @NamedQuery(name = "Logbook.findAllByEmailOrdered", query = "SELECT l FROM Logbook l WHERE l.logbookPK.email = :email ORDER BY l.stamp DESC")
    , @NamedQuery(name = "Logbook.findByGeocache", query = "SELECT l FROM Logbook l WHERE l.logbookPK.geocache = :geocache")
    , @NamedQuery(name = "Logbook.findByStamp", query = "SELECT l FROM Logbook l WHERE l.stamp = :stamp")})
public class Logbook implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LogbookPK logbookPK;
    @Column(name = "STAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stamp;

    public Logbook() {
    }

    public Logbook(LogbookPK logbookPK) {
        this.logbookPK = logbookPK;
    }

    public Logbook(String email, int geocache) {
        this.logbookPK = new LogbookPK(email, geocache);
    }

    public LogbookPK getLogbookPK() {
        return logbookPK;
    }

    public void setLogbookPK(LogbookPK logbookPK) {
        this.logbookPK = logbookPK;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logbookPK != null ? logbookPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logbook)) {
            return false;
        }
        Logbook other = (Logbook) object;
        if ((this.logbookPK == null && other.logbookPK != null) || (this.logbookPK != null && !this.logbookPK.equals(other.logbookPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Logbook[ logbookPK=" + logbookPK + " ]";
    }
    
}
