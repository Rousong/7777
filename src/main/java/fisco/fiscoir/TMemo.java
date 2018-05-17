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
@Table(name = "t_memo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMemo.findAll", query = "SELECT t FROM TMemo t"),
    @NamedQuery(name = "TMemo.findByMemoId", query = "SELECT t FROM TMemo t WHERE t.memoId = :memoId"),
    @NamedQuery(name = "TMemo.findByUserId", query = "SELECT t FROM TMemo t WHERE t.userId = :userId"),
    @NamedQuery(name = "TMemo.findByBrandCd", query = "SELECT t FROM TMemo t WHERE t.brandCd = :brandCd"),
    @NamedQuery(name = "TMemo.findByPostNm", query = "SELECT t FROM TMemo t WHERE t.postNm = :postNm"),
    @NamedQuery(name = "TMemo.findByPersonNm", query = "SELECT t FROM TMemo t WHERE t.personNm = :personNm"),
    @NamedQuery(name = "TMemo.findByDateFrom", query = "SELECT t FROM TMemo t WHERE t.dateFrom = :dateFrom"),
    @NamedQuery(name = "TMemo.findByDateTo", query = "SELECT t FROM TMemo t WHERE t.dateTo = :dateTo"),
    @NamedQuery(name = "TMemo.findByTimeFrom", query = "SELECT t FROM TMemo t WHERE t.timeFrom = :timeFrom"),
    @NamedQuery(name = "TMemo.findByTimeTo", query = "SELECT t FROM TMemo t WHERE t.timeTo = :timeTo"),
    @NamedQuery(name = "TMemo.findByTag1", query = "SELECT t FROM TMemo t WHERE t.tag1 = :tag1"),
    @NamedQuery(name = "TMemo.findByTag2", query = "SELECT t FROM TMemo t WHERE t.tag2 = :tag2"),
    @NamedQuery(name = "TMemo.findByTag3", query = "SELECT t FROM TMemo t WHERE t.tag3 = :tag3"),
    @NamedQuery(name = "TMemo.findByTitle", query = "SELECT t FROM TMemo t WHERE t.title = :title"),
    @NamedQuery(name = "TMemo.findByMemo", query = "SELECT t FROM TMemo t WHERE t.memo = :memo"),
    @NamedQuery(name = "TMemo.findByRegisterDatetime", query = "SELECT t FROM TMemo t WHERE t.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "TMemo.findByRegisterUser", query = "SELECT t FROM TMemo t WHERE t.registerUser = :registerUser"),
    @NamedQuery(name = "TMemo.findByRenewalDatetime", query = "SELECT t FROM TMemo t WHERE t.renewalDatetime = :renewalDatetime"),
    @NamedQuery(name = "TMemo.findByRenewaluser", query = "SELECT t FROM TMemo t WHERE t.renewaluser = :renewaluser")})
public class TMemo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "MEMO_ID")
    private String memoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USER_ID")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "BRAND_CD")
    private String brandCd;
    @Size(max = 64)
    @Column(name = "POST_NM")
    private String postNm;
    @Size(max = 128)
    @Column(name = "PERSON_NM")
    private String personNm;
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;
    @Size(max = 5)
    @Column(name = "TIME_FROM")
    private String timeFrom;
    @Size(max = 5)
    @Column(name = "TIME_TO")
    private String timeTo;
    @Size(max = 10)
    @Column(name = "TAG1")
    private String tag1;
    @Size(max = 10)
    @Column(name = "TAG2")
    private String tag2;
    @Size(max = 10)
    @Column(name = "TAG3")
    private String tag3;
    @Size(max = 512)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 1024)
    @Column(name = "MEMO")
    private String memo;
    @Column(name = "REGISTER_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDatetime;
    @Size(max = 256)
    @Column(name = "REGISTER_USER")
    private String registerUser;
    @Column(name = "RENEWAL_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renewalDatetime;
    @Size(max = 256)
    @Column(name = "RENEWAL_USER")
    private String renewaluser;

    public TMemo() {
    }

    public TMemo(String memoId) {
        this.memoId = memoId;
    }

    public TMemo(String memoId, String userId, String brandCd) {
        this.memoId = memoId;
        this.userId = userId;
        this.brandCd = brandCd;
    }

    public String getMemoId() {
        return memoId;
    }

    public void setMemoId(String memoId) {
        this.memoId = memoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBrandCd() {
        return brandCd;
    }

    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }

    public String getPostNm() {
        return postNm;
    }

    public void setPostNm(String postNm) {
        this.postNm = postNm;
    }

    public String getPersonNm() {
        return personNm;
    }

    public void setPersonNm(String personNm) {
        this.personNm = personNm;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getRenewaluser() {
        return renewaluser;
    }

    public void setRenewaluser(String renewaluser) {
        this.renewaluser = renewaluser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memoId != null ? memoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMemo)) {
            return false;
        }
        TMemo other = (TMemo) object;
        if ((this.memoId == null && other.memoId != null) || (this.memoId != null && !this.memoId.equals(other.memoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.TMemo[ memoId=" + memoId + " ]";
    }
    
}
