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
 *　メモタグのエンティティクラス
 * @author k00299
 */
@Entity
@Table(name = "m_memotag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MMemotag.findAll", query = "SELECT m FROM MMemotag m"),
    @NamedQuery(name = "MMemotag.findByTagId", query = "SELECT m FROM MMemotag m WHERE m.mMemotagPK.tagId = :tagId"),
    @NamedQuery(name = "MMemotag.findByCode", query = "SELECT m FROM MMemotag m WHERE m.mMemotagPK.code = :code"),
    @NamedQuery(name = "MMemotag.findByContents1", query = "SELECT m FROM MMemotag m WHERE m.contents1 = :contents1"),
    @NamedQuery(name = "MMemotag.findByContents2", query = "SELECT m FROM MMemotag m WHERE m.contents2 = :contents2"),
    @NamedQuery(name = "MMemotag.findBySeq", query = "SELECT m FROM MMemotag m WHERE m.seq = :seq"),
    @NamedQuery(name = "MMemotag.findByNotes", query = "SELECT m FROM MMemotag m WHERE m.notes = :notes"),
    @NamedQuery(name = "MMemotag.findByRegisterDatetime", query = "SELECT m FROM MMemotag m WHERE m.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "MMemotag.findByRenewalDatetime", query = "SELECT m FROM MMemotag m WHERE m.renewalDatetime = :renewalDatetime")})
public class MMemotag implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MMemotagPK mMemotagPK;
    @Size(max = 50)
    @Column(name = "CONTENTS_1")
    private String contents1;
    @Size(max = 50)
    @Column(name = "CONTENTS_2")
    private String contents2;
    @Column(name = "SEQ")
    private Short seq;
    @Size(max = 30)
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

    public MMemotag() {
    }

    public MMemotag(MMemotagPK mMemotagPK) {
        this.mMemotagPK = mMemotagPK;
    }

    public MMemotag(MMemotagPK mMemotagPK, Date registerDatetime) {
        this.mMemotagPK = mMemotagPK;
        this.registerDatetime = registerDatetime;
    }

    public MMemotag(String tagId, String code) {
        this.mMemotagPK = new MMemotagPK(tagId, code);
    }

    public MMemotagPK getMMemotagPK() {
        return mMemotagPK;
    }

    public void setMMemotagPK(MMemotagPK mMemotagPK) {
        this.mMemotagPK = mMemotagPK;
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
        hash += (mMemotagPK != null ? mMemotagPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MMemotag)) {
            return false;
        }
        MMemotag other = (MMemotag) object;
        if ((this.mMemotagPK == null && other.mMemotagPK != null) || (this.mMemotagPK != null && !this.mMemotagPK.equals(other.mMemotagPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.MMemotag[ mMemotagPK=" + mMemotagPK + " ]";
    }
    
}
