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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * コードマスタのエンティティクラス
 * @author k00299
 */
@Entity
@Table(name = "m_code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MCode.findAll", query = "SELECT m FROM MCode m"),
    @NamedQuery(name = "MCode.findByGroupId", query = "SELECT m FROM MCode m WHERE m.mCodePK.groupId = :groupId order by m.seq"),
    @NamedQuery(name = "MCode.findByGroupIdCode", query = "SELECT m FROM MCode m WHERE m.mCodePK.groupId = :groupId and m.mCodePK.code = :code order by m.seq"),
    @NamedQuery(name = "MCode.findByCode", query = "SELECT m FROM MCode m WHERE m.mCodePK.code = :code order by m.seq"),
    @NamedQuery(name = "MCode.findByContents1", query = "SELECT m FROM MCode m WHERE m.contents1 = :contents1"),
    @NamedQuery(name = "MCode.findByContents2", query = "SELECT m FROM MCode m WHERE m.contents2 = :contents2"),
    @NamedQuery(name = "MCode.findBySeq", query = "SELECT m FROM MCode m WHERE m.seq = :seq"),
    @NamedQuery(name = "MCode.findByNotes", query = "SELECT m FROM MCode m WHERE m.notes = :notes"),
    @NamedQuery(name = "MCode.findByRegisterDatetime", query = "SELECT m FROM MCode m WHERE m.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "MCode.findByRenewalDatetime", query = "SELECT m FROM MCode m WHERE m.renewalDatetime = :renewalDatetime")})
public class MCode implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MCodePK mCodePK;
    @Size(max = 50)
    @Column(name = "CONTENTS_1")
    private String contents1;
    @Size(max = 50)
    @Column(name = "CONTENTS_2")
    private String contents2;
    @Column(name = "SEQ")
    private Short seq;
    @Size(max = 50)
    @Column(name = "NOTES")
    private String notes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTER_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDatetime;
    @Column(name = "RENEWAL_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renewalDatetime;

    public MCode() {
    }

    public MCode(MCodePK mCodePK) {
        this.mCodePK = mCodePK;
    }

    public MCode(MCodePK mCodePK, Date registerDatetime) {
        this.mCodePK = mCodePK;
        this.registerDatetime = registerDatetime;
    }

    public MCode(String groupId, String code) {
        this.mCodePK = new MCodePK(groupId, code);
    }

    public MCodePK getMCodePK() {
        return mCodePK;
    }

    public void setMCodePK(MCodePK mCodePK) {
        this.mCodePK = mCodePK;
    }

    public String getContents1() {
        return contents1;
    }

    public void setContents1(String contents1) {
        this.contents1 = contents1;
    }

    public String getContents2() {
        return contents2;
    }

    public void setContents2(String contents2) {
        this.contents2 = contents2;
    }

    public Short getSeq() {
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Date registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public Date getRenewalDatetime() {
        return renewalDatetime;
    }

    public void setRenewalDatetime(Date renewalDatetime) {
        this.renewalDatetime = renewalDatetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mCodePK != null ? mCodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MCode)) {
            return false;
        }
        MCode other = (MCode) object;
        if ((this.mCodePK == null && other.mCodePK != null) || (this.mCodePK != null && !this.mCodePK.equals(other.mCodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.MCode[ mCodePK=" + mCodePK + " ]";
    }
    
}
