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
 * ユーザ情報のエンティティクラス
 * @author k00299
 */
@Entity
@Table(name = "m_user_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MUserInfo.findAll", query = "SELECT m FROM MUserInfo m"),
    @NamedQuery(name = "MUserInfo.findByInternalId", query = "SELECT m FROM MUserInfo m WHERE m.internalId = :internalId"),
    @NamedQuery(name = "MUserInfo.findByUserId", query = "SELECT m FROM MUserInfo m WHERE m.userId = :userId"),
    @NamedQuery(name = "MUserInfo.findByLname", query = "SELECT m FROM MUserInfo m WHERE m.lname = :lname"),
    @NamedQuery(name = "MUserInfo.findByFname", query = "SELECT m FROM MUserInfo m WHERE m.fname = :fname"),
    @NamedQuery(name = "MUserInfo.findByMname", query = "SELECT m FROM MUserInfo m WHERE m.mname = :mname"),
    @NamedQuery(name = "MUserInfo.findByLnameKana", query = "SELECT m FROM MUserInfo m WHERE m.lnameKana = :lnameKana"),
    @NamedQuery(name = "MUserInfo.findByFnameKana", query = "SELECT m FROM MUserInfo m WHERE m.fnameKana = :fnameKana"),
    @NamedQuery(name = "MUserInfo.findByMnameKana", query = "SELECT m FROM MUserInfo m WHERE m.mnameKana = :mnameKana"),
    @NamedQuery(name = "MUserInfo.findByMailaddrReg", query = "SELECT m FROM MUserInfo m WHERE m.mailaddrReg = :mailaddrReg"),
    @NamedQuery(name = "MUserInfo.findByMailaddrNotice", query = "SELECT m FROM MUserInfo m WHERE m.mailaddrNotice = :mailaddrNotice"),
    @NamedQuery(name = "MUserInfo.findByBrandCd", query = "SELECT m FROM MUserInfo m WHERE m.brandCd = :brandCd"),
    @NamedQuery(name = "MUserInfo.findByStatus", query = "SELECT m FROM MUserInfo m WHERE m.status = :status"),
    @NamedQuery(name = "MUserInfo.findByRegisterDatetime", query = "SELECT m FROM MUserInfo m WHERE m.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "MUserInfo.findByRegisterUser", query = "SELECT m FROM MUserInfo m WHERE m.registerUser = :registerUser"),
    @NamedQuery(name = "MUserInfo.findByRenewalDatetime", query = "SELECT m FROM MUserInfo m WHERE m.renewalDatetime = :renewalDatetime"),
    @NamedQuery(name = "MUserInfo.findByRenewalUser", query = "SELECT m FROM MUserInfo m WHERE m.renewalUser = :renewalUser")})
public class MUserInfo implements Serializable {

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
    @Size(min = 1, max = 45)
    @Column(name = "LNAME")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FNAME")
    private String fname;
    @Size(max = 45)
    @Column(name = "MNAME")
    private String mname;
    @Size(max = 45)
    @Column(name = "LNAME_KANA")
    private String lnameKana;
    @Size(max = 45)
    @Column(name = "FNAME_KANA")
    private String fnameKana;
    @Size(max = 45)
    @Column(name = "MNAME_KANA")
    private String mnameKana;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "MAILADDR_REG")
    private String mailaddrReg;
    @Size(max = 250)
    @Column(name = "MAILADDR_NOTICE")
    private String mailaddrNotice;
    @Size(max = 7)
    @Column(name = "BRAND_CD")
    private String brandCd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
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

    public MUserInfo() {
    }

    public MUserInfo(String internalId) {
        this.internalId = internalId;
    }

    public MUserInfo(String internalId, String userId, String lname, String fname, String mailaddrReg, int status, Date registerDatetime, String registerUser) {
        this.internalId = internalId;
        this.userId = userId;
        this.lname = lname;
        this.fname = fname;
        this.mailaddrReg = mailaddrReg;
        this.status = status;
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

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLnameKana() {
        return lnameKana;
    }

    public void setLnameKana(String lnameKana) {
        this.lnameKana = lnameKana;
    }

    public String getFnameKana() {
        return fnameKana;
    }

    public void setFnameKana(String fnameKana) {
        this.fnameKana = fnameKana;
    }

    public String getMnameKana() {
        return mnameKana;
    }

    public void setMnameKana(String mnameKana) {
        this.mnameKana = mnameKana;
    }

    public String getMailaddrReg() {
        return mailaddrReg;
    }

    public void setMailaddrReg(String mailaddrReg) {
        this.mailaddrReg = mailaddrReg;
    }

    public String getMailaddrNotice() {
        return mailaddrNotice;
    }

    public void setMailaddrNotice(String mailaddrNotice) {
        this.mailaddrNotice = mailaddrNotice;
    }

    public String getBrandCd() {
        return brandCd;
    }

    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof MUserInfo)) {
            return false;
        }
        MUserInfo other = (MUserInfo) object;
        if ((this.internalId == null && other.internalId != null) || (this.internalId != null && !this.internalId.equals(other.internalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.MUserInfo[ internalId=" + internalId + " ]";
    }
    
}
