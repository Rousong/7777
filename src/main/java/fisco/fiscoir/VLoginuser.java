/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

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
 * @author k00299
 */
@Entity
@Table(name = "v_loginuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VLoginuser.findAll", query = "SELECT v FROM VLoginuser v"),
    @NamedQuery(name = "VLoginuser.findByInternalId", query = "SELECT v FROM VLoginuser v WHERE v.internalId = :internalId"),
    @NamedQuery(name = "VLoginuser.findByUserId", query = "SELECT v FROM VLoginuser v WHERE v.userId = :userId"),
    @NamedQuery(name = "VLoginuser.findByLname", query = "SELECT v FROM VLoginuser v WHERE v.lname = :lname"),
    @NamedQuery(name = "VLoginuser.findByFname", query = "SELECT v FROM VLoginuser v WHERE v.fname = :fname"),
    @NamedQuery(name = "VLoginuser.findByMname", query = "SELECT v FROM VLoginuser v WHERE v.mname = :mname"),
    @NamedQuery(name = "VLoginuser.findByLnameKana", query = "SELECT v FROM VLoginuser v WHERE v.lnameKana = :lnameKana"),
    @NamedQuery(name = "VLoginuser.findByFnameKana", query = "SELECT v FROM VLoginuser v WHERE v.fnameKana = :fnameKana"),
    @NamedQuery(name = "VLoginuser.findByMnameKana", query = "SELECT v FROM VLoginuser v WHERE v.mnameKana = :mnameKana"),
    @NamedQuery(name = "VLoginuser.findByMailaddrReg", query = "SELECT v FROM VLoginuser v WHERE v.mailaddrReg = :mailaddrReg"),
    @NamedQuery(name = "VLoginuser.findByMailaddrNotice", query = "SELECT v FROM VLoginuser v WHERE v.mailaddrNotice = :mailaddrNotice"),
    @NamedQuery(name = "VLoginuser.findByBrandCd", query = "SELECT v FROM VLoginuser v WHERE v.brandCd = :brandCd"),
    @NamedQuery(name = "VLoginuser.findByPassword", query = "SELECT v FROM VLoginuser v WHERE v.password = :password")})
public class VLoginuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "INTERNAL_ID")
    @Id
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
    @Size(max = 32)
    @Column(name = "PASSWORD")
    private String password;

    public VLoginuser() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
