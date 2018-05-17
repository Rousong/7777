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
 * ユーザパスワード情報のエンティティクラス
 * @author k00299
 */
@Entity
@Table(name = "m_user_password")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MUserPassword.findAll", query = "SELECT m FROM MUserPassword m"),
    @NamedQuery(name = "MUserPassword.findByInternalId", query = "SELECT m FROM MUserPassword m WHERE m.internalId = :internalId"),
    @NamedQuery(name = "MUserPassword.findByUserId", query = "SELECT m FROM MUserPassword m WHERE m.userId = :userId"),
    @NamedQuery(name = "MUserPassword.findByPassword", query = "SELECT m FROM MUserPassword m WHERE m.password = :password"),
    @NamedQuery(name = "MUserPassword.findByRegisterDatetime", query = "SELECT m FROM MUserPassword m WHERE m.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "MUserPassword.findByRegisterUser", query = "SELECT m FROM MUserPassword m WHERE m.registerUser = :registerUser"),
    @NamedQuery(name = "MUserPassword.findByRenewalDatetime", query = "SELECT m FROM MUserPassword m WHERE m.renewalDatetime = :renewalDatetime"),
    @NamedQuery(name = "MUserPassword.findByRenewalUser", query = "SELECT m FROM MUserPassword m WHERE m.renewalUser = :renewalUser")})
public class MUserPassword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "INTERNAL_ID")
    private String internalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "USER_ID")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTER_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDatetime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "REGISTER_USER")
    private String registerUser;
    @Column(name = "RENEWAL_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renewalDatetime;
    @Size(max = 250)
    @Column(name = "RENEWAL_USER")
    private String renewalUser;

    public MUserPassword() {
    }

    public MUserPassword(String internalId) {
        this.internalId = internalId;
    }

    public MUserPassword(String internalId, String userId, String password, Date registerDatetime, String registerUser) {
        this.internalId = internalId;
        this.userId = userId;
        this.password = password;
        this.registerDatetime = registerDatetime;
        this.registerUser = registerUser;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Date registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public String getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(String registerUser) {
        this.registerUser = registerUser;
    }

    public Date getRenewalDatetime() {
        return renewalDatetime;
    }

    public void setRenewalDatetime(Date renewalDatetime) {
        this.renewalDatetime = renewalDatetime;
    }

    public String getRenewalUser() {
        return renewalUser;
    }

    public void setRenewalUser(String renewalUser) {
        this.renewalUser = renewalUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (internalId != null ? internalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MUserPassword)) {
            return false;
        }
        MUserPassword other = (MUserPassword) object;
        if ((this.internalId == null && other.internalId != null) || (this.internalId != null && !this.internalId.equals(other.internalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.MUserPassword[ internalId=" + internalId + " ]";
    }
    
}
