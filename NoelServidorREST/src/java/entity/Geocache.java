/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noel
 */
@Entity
@Table(name = "GEOCACHE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geocache.findAll", query = "SELECT g FROM Geocache g")
    , @NamedQuery(name = "Geocache.findById", query = "SELECT g FROM Geocache g WHERE g.id = :id")
    , @NamedQuery(name = "Geocache.findByLat", query = "SELECT g FROM Geocache g WHERE g.lat = :lat")
    , @NamedQuery(name = "Geocache.findByLon", query = "SELECT g FROM Geocache g WHERE g.lon = :lon")
    , @NamedQuery(name = "Geocache.findByUrl", query = "SELECT g FROM Geocache g WHERE g.url = :url")
    , @NamedQuery(name = "Geocache.findByHint", query = "SELECT g FROM Geocache g WHERE g.hint = :hint")})

public class Geocache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID", nullable =false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LAT")
    private Double lat;
    @Column(name = "LON")
    private Double lon;
    //@Size(max = 50)
    @Column(name = "URL",length=50)
    private String url;
    //@Size(max = 100)
    @Column(name = "HINT",length=100)
    private String hint;

    public Geocache() {
    }

    public Geocache(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
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
        if (!(object instanceof Geocache)) {
            return false;
        }
        Geocache other = (Geocache) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Geocache[ id=" + id + " ]";
    }
    
}
