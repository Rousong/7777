/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "v_brand_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VBrandInfo.findAll", query = "SELECT v FROM VBrandInfo v"),
    @NamedQuery(name = "VBrandInfo.findByBrandCd", query = "SELECT v FROM VBrandInfo v WHERE v.brandCd = :brandCd"),
    @NamedQuery(name = "VBrandInfo.findByStockCode", query = "SELECT v FROM VBrandInfo v WHERE v.stockCode = :stockCode"),
    @NamedQuery(name = "VBrandInfo.findByMarketNm", query = "SELECT v FROM VBrandInfo v WHERE v.marketNm = :marketNm"),
    @NamedQuery(name = "VBrandInfo.findBySettlementMm", query = "SELECT v FROM VBrandInfo v WHERE v.settlementMm = :settlementMm"),
    @NamedQuery(name = "VBrandInfo.findByBizCdTse33", query = "SELECT v FROM VBrandInfo v WHERE v.bizCdTse33 = :bizCdTse33"),
    @NamedQuery(name = "VBrandInfo.findByBiz", query = "SELECT v FROM VBrandInfo v WHERE v.biz = :biz"),
    @NamedQuery(name = "VBrandInfo.findByFoundYymm", query = "SELECT v FROM VBrandInfo v WHERE v.foundYymm = :foundYymm"),
    @NamedQuery(name = "VBrandInfo.findByListedDate", query = "SELECT v FROM VBrandInfo v WHERE v.listedDate = :listedDate"),
    @NamedQuery(name = "VBrandInfo.findByDelistedDate", query = "SELECT v FROM VBrandInfo v WHERE v.delistedDate = :delistedDate"),
    @NamedQuery(name = "VBrandInfo.findByOfficeAddress", query = "SELECT v FROM VBrandInfo v WHERE v.officeAddress = :officeAddress"),
    @NamedQuery(name = "VBrandInfo.findByOfficeTel", query = "SELECT v FROM VBrandInfo v WHERE v.officeTel = :officeTel"),
    @NamedQuery(name = "VBrandInfo.findByOfficeUrl", query = "SELECT v FROM VBrandInfo v WHERE v.officeUrl = :officeUrl"),
    @NamedQuery(name = "VBrandInfo.findByRepresentDirectorPostNm", query = "SELECT v FROM VBrandInfo v WHERE v.representDirectorPostNm = :representDirectorPostNm"),
    @NamedQuery(name = "VBrandInfo.findByRepresentDirectorNm", query = "SELECT v FROM VBrandInfo v WHERE v.representDirectorNm = :representDirectorNm"),
    @NamedQuery(name = "VBrandInfo.findByRenewalDatetime", query = "SELECT v FROM VBrandInfo v WHERE v.renewalDatetime = :renewalDatetime"),
    @NamedQuery(name = "VBrandInfo.findByMarketCapitalization", query = "SELECT v FROM VBrandInfo v WHERE v.marketCapitalization = :marketCapitalization"),
    @NamedQuery(name = "VBrandInfo.findByLastUpdate", query = "SELECT v FROM VBrandInfo v WHERE v.lastUpdate = :lastUpdate")})
public class VBrandInfo implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "BRAND_NM")
    @Id
    private String brandNm;
    @Size(max = 300)
    @Column(name = "BRAND_SNM")
    private String brandSnm;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "BRAND_CD")
    private String brandCd;
    @Size(max = 7)
    @Column(name = "STOCK_CODE")
    private String stockCode;
    @Size(max = 30)
    @Column(name = "MARKET_NM")
    private String marketNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SETTLEMENT_MM")
    private String settlementMm;
    @Size(max = 4)
    @Column(name = "BIZ_CD_TSE_33")
    private String bizCdTse33;
    @Size(max = 50)
    @Column(name = "CONTENTS_1")
    private String contents1;
    @Size(max = 2000)
    @Column(name = "BIZ")
    private String biz;
    @Size(max = 6)
    @Column(name = "FOUND_YYMM")
    private String foundYymm;
    @Size(max = 8)
    @Column(name = "LISTED_DATE")
    private String listedDate;
    @Size(max = 8)
    @Column(name = "DELISTED_DATE")
    private String delistedDate;
    @Size(max = 200)
    @Column(name = "OFFICE_ADDRESS")
    private String officeAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "OFFICE_TEL")
    private String officeTel;
    @Size(max = 80)
    @Column(name = "OFFICE_URL")
    private String officeUrl;
    @Size(max = 40)
    @Column(name = "REPRESENT_DIRECTOR_POST_NM")
    private String representDirectorPostNm;
    @Size(max = 40)
    @Column(name = "REPRESENT_DIRECTOR_NM")
    private String representDirectorNm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENEWAL_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renewalDatetime;
    @Column(name = "MARKET_CAPITALIZATION")
    private BigInteger marketCapitalization;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public VBrandInfo() {
    }

    public String getBrandCd() {
        return brandCd;
    }

    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getMarketNm() {
        return marketNm;
    }

    public void setMarketNm(String marketNm) {
        this.marketNm = marketNm;
    }

    public String getSettlementMm() {
        return settlementMm;
    }

    public void setSettlementMm(String settlementMm) {
        this.settlementMm = settlementMm;
    }

    public String getBizCdTse33() {
        return bizCdTse33;
    }

    public void setBizCdTse33(String bizCdTse33) {
        this.bizCdTse33 = bizCdTse33;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getFoundYymm() {
        return foundYymm;
    }

    public void setFoundYymm(String foundYymm) {
        this.foundYymm = foundYymm;
    }

    public String getListedDate() {
        return listedDate;
    }

    public void setListedDate(String listedDate) {
        this.listedDate = listedDate;
    }

    public String getDelistedDate() {
        return delistedDate;
    }

    public void setDelistedDate(String delistedDate) {
        this.delistedDate = delistedDate;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getOfficeUrl() {
        return officeUrl;
    }

    public void setOfficeUrl(String officeUrl) {
        this.officeUrl = officeUrl;
    }

    public String getRepresentDirectorPostNm() {
        return representDirectorPostNm;
    }

    public void setRepresentDirectorPostNm(String representDirectorPostNm) {
        this.representDirectorPostNm = representDirectorPostNm;
    }

    public String getRepresentDirectorNm() {
        return representDirectorNm;
    }

    public void setRepresentDirectorNm(String representDirectorNm) {
        this.representDirectorNm = representDirectorNm;
    }

    public Date getRenewalDatetime() {
        return renewalDatetime;
    }

    public void setRenewalDatetime(Date renewalDatetime) {
        this.renewalDatetime = renewalDatetime;
    }

    public BigInteger getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(BigInteger marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getContents1() {
        return contents1;
    }

    public void setContents1(String contents1) {
        this.contents1 = contents1;
    }

    public String getBrandNm() {
        return brandNm;
    }

    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    public String getBrandSnm() {
        return brandSnm;
    }

    public void setBrandSnm(String brandSnm) {
        this.brandSnm = brandSnm;
    }
    
}
