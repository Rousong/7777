/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k00299
 */
@Entity
@Table(name = "t_numbering")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TNumbering.findAll", query = "SELECT t FROM TNumbering t"),
    @NamedQuery(name = "TNumbering.findByNumberCd", query = "SELECT t FROM TNumbering t WHERE t.numberCd = :numberCd"),
    @NamedQuery(name = "TNumbering.findByNumber", query = "SELECT t FROM TNumbering t WHERE t.number = :number"),
    @NamedQuery(name = "TNumbering.findByRenewalDatetime", query = "SELECT t FROM TNumbering t WHERE t.renewalDatetime = :renewalDatetime"),
    @NamedQuery(name = "TNumbering.findByRenewaluser", query = "SELECT t FROM TNumbering t WHERE t.renewaluser = :renewaluser")})
public class TNumbering implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NUMBER_CD")
    private String numberCd;
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "RENEWAL_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renewalDatetime;
    @Size(max = 256)
    @Column(name = "RENEWALUSER")
    private String renewaluser;

    public TNumbering() {
    }

    public TNumbering(String numberCd) {
        this.numberCd = numberCd;
    }

    public String getNumberCd() {
        return numberCd;
    }

    public void setNumberCd(String numberCd) {
        this.numberCd = numberCd;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getRenewalDatetime() {
        return renewalDatetime;
    }

    public void setRenewalDatetime(Date renewalDatetime) {
        this.renewalDatetime = renewalDatetime;
    }

    public String getRenewaluser() {
        return renewaluser;
    }

    public void setRenewaluser(String renewaluser) {
        this.renewaluser = renewaluser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numberCd != null ? numberCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TNumbering)) {
            return false;
        }
        TNumbering other = (TNumbering) object;
        if ((this.numberCd == null && other.numberCd != null) || (this.numberCd != null && !this.numberCd.equals(other.numberCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.TNumbering[ numberCd=" + numberCd + " ]";
    }
    
}
