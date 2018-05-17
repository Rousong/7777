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
 * 銘柄情報_基本のエンティティクラス
 * @author k00299
 */
@Entity
@Table(name = "m_brand_fund")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MBrandFund.findAll", query = "SELECT m FROM MBrandFund m"),
    @NamedQuery(name = "MBrandFund.findByBrandCd", query = "SELECT m FROM MBrandFund m WHERE m.brandCd = :brandCd"),
    @NamedQuery(name = "MBrandFund.findByStockCode", query = "SELECT m FROM MBrandFund m WHERE m.stockCode = :stockCode"),
    @NamedQuery(name = "MBrandFund.findByMainMarketCd", query = "SELECT m FROM MBrandFund m WHERE m.mainMarketCd = :mainMarketCd"),
    @NamedQuery(name = "MBrandFund.findByBrandMarketCd", query = "SELECT m FROM MBrandFund m WHERE m.brandMarketCd = :brandMarketCd"),
    @NamedQuery(name = "MBrandFund.findByMarketNm", query = "SELECT m FROM MBrandFund m WHERE m.marketNm = :marketNm"),
    @NamedQuery(name = "MBrandFund.findByBrandNm", query = "SELECT m FROM MBrandFund m WHERE m.brandNm = :brandNm"),
    @NamedQuery(name = "MBrandFund.findByBrandNmXebral", query = "SELECT m FROM MBrandFund m WHERE m.brandNmXebral = :brandNmXebral"),
    @NamedQuery(name = "MBrandFund.findByBrandSnm", query = "SELECT m FROM MBrandFund m WHERE m.brandSnm = :brandSnm"),
    @NamedQuery(name = "MBrandFund.findByBrandEnm", query = "SELECT m FROM MBrandFund m WHERE m.brandEnm = :brandEnm"),
    @NamedQuery(name = "MBrandFund.findByBrandNmSearch", query = "SELECT m FROM MBrandFund m WHERE m.brandNmSearch = :brandNmSearch"),
    @NamedQuery(name = "MBrandFund.findByTradeUnit", query = "SELECT m FROM MBrandFund m WHERE m.tradeUnit = :tradeUnit"),
    @NamedQuery(name = "MBrandFund.findBySettlementMm", query = "SELECT m FROM MBrandFund m WHERE m.settlementMm = :settlementMm"),
    @NamedQuery(name = "MBrandFund.findByBizCdTse33", query = "SELECT m FROM MBrandFund m WHERE m.bizCdTse33 = :bizCdTse33"),
    @NamedQuery(name = "MBrandFund.findByBiz", query = "SELECT m FROM MBrandFund m WHERE m.biz = :biz"),
    @NamedQuery(name = "MBrandFund.findByBizOverseaNum", query = "SELECT m FROM MBrandFund m WHERE m.bizOverseaNum = :bizOverseaNum"),
    @NamedQuery(name = "MBrandFund.findByFoundYymm", query = "SELECT m FROM MBrandFund m WHERE m.foundYymm = :foundYymm"),
    @NamedQuery(name = "MBrandFund.findByListedDate", query = "SELECT m FROM MBrandFund m WHERE m.listedDate = :listedDate"),
    @NamedQuery(name = "MBrandFund.findByDelistedDate", query = "SELECT m FROM MBrandFund m WHERE m.delistedDate = :delistedDate"),
    @NamedQuery(name = "MBrandFund.findByCommentEarnings", query = "SELECT m FROM MBrandFund m WHERE m.commentEarnings = :commentEarnings"),
    @NamedQuery(name = "MBrandFund.findByOfficeAddress", query = "SELECT m FROM MBrandFund m WHERE m.officeAddress = :officeAddress"),
    @NamedQuery(name = "MBrandFund.findByOfficeTel", query = "SELECT m FROM MBrandFund m WHERE m.officeTel = :officeTel"),
    @NamedQuery(name = "MBrandFund.findByHeadofficeTel", query = "SELECT m FROM MBrandFund m WHERE m.headofficeTel = :headofficeTel"),
    @NamedQuery(name = "MBrandFund.findByOfficeUrl", query = "SELECT m FROM MBrandFund m WHERE m.officeUrl = :officeUrl"),
    @NamedQuery(name = "MBrandFund.findByRepresentDirectorPostNm", query = "SELECT m FROM MBrandFund m WHERE m.representDirectorPostNm = :representDirectorPostNm"),
    @NamedQuery(name = "MBrandFund.findByRepresentDirectorNm", query = "SELECT m FROM MBrandFund m WHERE m.representDirectorNm = :representDirectorNm"),
    @NamedQuery(name = "MBrandFund.findByBranchInfo", query = "SELECT m FROM MBrandFund m WHERE m.branchInfo = :branchInfo"),
    @NamedQuery(name = "MBrandFund.findByEmployeeNumCnct", query = "SELECT m FROM MBrandFund m WHERE m.employeeNumCnct = :employeeNumCnct"),
    @NamedQuery(name = "MBrandFund.findByEmployeeNumAlon", query = "SELECT m FROM MBrandFund m WHERE m.employeeNumAlon = :employeeNumAlon"),
    @NamedQuery(name = "MBrandFund.findByAvgAge", query = "SELECT m FROM MBrandFund m WHERE m.avgAge = :avgAge"),
    @NamedQuery(name = "MBrandFund.findByAvgIncome", query = "SELECT m FROM MBrandFund m WHERE m.avgIncome = :avgIncome"),
    @NamedQuery(name = "MBrandFund.findByAvgWork", query = "SELECT m FROM MBrandFund m WHERE m.avgWork = :avgWork"),
    @NamedQuery(name = "MBrandFund.findByDealingStockComNm", query = "SELECT m FROM MBrandFund m WHERE m.dealingStockComNm = :dealingStockComNm"),
    @NamedQuery(name = "MBrandFund.findByDealingBankNm", query = "SELECT m FROM MBrandFund m WHERE m.dealingBankNm = :dealingBankNm"),
    @NamedQuery(name = "MBrandFund.findByAuditingFirm", query = "SELECT m FROM MBrandFund m WHERE m.auditingFirm = :auditingFirm"),
    @NamedQuery(name = "MBrandFund.findBySameTradeRank", query = "SELECT m FROM MBrandFund m WHERE m.sameTradeRank = :sameTradeRank"),
    @NamedQuery(name = "MBrandFund.findBySameTradeNum", query = "SELECT m FROM MBrandFund m WHERE m.sameTradeNum = :sameTradeNum"),
    @NamedQuery(name = "MBrandFund.findByStockholderUnit", query = "SELECT m FROM MBrandFund m WHERE m.stockholderUnit = :stockholderUnit"),
    @NamedQuery(name = "MBrandFund.findByStockOutstanding", query = "SELECT m FROM MBrandFund m WHERE m.stockOutstanding = :stockOutstanding"),
    @NamedQuery(name = "MBrandFund.findByStockholdOversea", query = "SELECT m FROM MBrandFund m WHERE m.stockholdOversea = :stockholdOversea"),
    @NamedQuery(name = "MBrandFund.findByStockholdInvestment", query = "SELECT m FROM MBrandFund m WHERE m.stockholdInvestment = :stockholdInvestment"),
    @NamedQuery(name = "MBrandFund.findByStockholdFloat", query = "SELECT m FROM MBrandFund m WHERE m.stockholdFloat = :stockholdFloat"),
    @NamedQuery(name = "MBrandFund.findByStockholdSpecific", query = "SELECT m FROM MBrandFund m WHERE m.stockholdSpecific = :stockholdSpecific"),
    @NamedQuery(name = "MBrandFund.findByPbrRecentYear", query = "SELECT m FROM MBrandFund m WHERE m.pbrRecentYear = :pbrRecentYear"),
    @NamedQuery(name = "MBrandFund.findByPbrRecentQuarter", query = "SELECT m FROM MBrandFund m WHERE m.pbrRecentQuarter = :pbrRecentQuarter"),
    @NamedQuery(name = "MBrandFund.findByPerRecentYear", query = "SELECT m FROM MBrandFund m WHERE m.perRecentYear = :perRecentYear"),
    @NamedQuery(name = "MBrandFund.findByPerRecentLtm", query = "SELECT m FROM MBrandFund m WHERE m.perRecentLtm = :perRecentLtm"),
    @NamedQuery(name = "MBrandFund.findByPerForecastYear", query = "SELECT m FROM MBrandFund m WHERE m.perForecastYear = :perForecastYear"),
    @NamedQuery(name = "MBrandFund.findByDpsInterestForecast", query = "SELECT m FROM MBrandFund m WHERE m.dpsInterestForecast = :dpsInterestForecast"),
    @NamedQuery(name = "MBrandFund.findByIncentivesInfo", query = "SELECT m FROM MBrandFund m WHERE m.incentivesInfo = :incentivesInfo"),
    @NamedQuery(name = "MBrandFund.findBySettlementTerm", query = "SELECT m FROM MBrandFund m WHERE m.settlementTerm = :settlementTerm"),
    @NamedQuery(name = "MBrandFund.findByDcKbn", query = "SELECT m FROM MBrandFund m WHERE m.dcKbn = :dcKbn"),
    @NamedQuery(name = "MBrandFund.findByNikkei225Flg", query = "SELECT m FROM MBrandFund m WHERE m.nikkei225Flg = :nikkei225Flg"),
    @NamedQuery(name = "MBrandFund.findByComLogoImgPath", query = "SELECT m FROM MBrandFund m WHERE m.comLogoImgPath = :comLogoImgPath"),
    @NamedQuery(name = "MBrandFund.findByDataUploadDatetime", query = "SELECT m FROM MBrandFund m WHERE m.dataUploadDatetime = :dataUploadDatetime"),
    @NamedQuery(name = "MBrandFund.findBySegment", query = "SELECT m FROM MBrandFund m WHERE m.segment = :segment"),
    @NamedQuery(name = "MBrandFund.findByLatestFqEndDate", query = "SELECT m FROM MBrandFund m WHERE m.latestFqEndDate = :latestFqEndDate"),
    @NamedQuery(name = "MBrandFund.findByLatestFyEndDate", query = "SELECT m FROM MBrandFund m WHERE m.latestFyEndDate = :latestFyEndDate"),
    @NamedQuery(name = "MBrandFund.findByLatestSettlementDate", query = "SELECT m FROM MBrandFund m WHERE m.latestSettlementDate = :latestSettlementDate"),
    @NamedQuery(name = "MBrandFund.findByNextSettlementDate", query = "SELECT m FROM MBrandFund m WHERE m.nextSettlementDate = :nextSettlementDate"),
    @NamedQuery(name = "MBrandFund.findByRegisterDatetime", query = "SELECT m FROM MBrandFund m WHERE m.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "MBrandFund.findByRenewalDatetime", query = "SELECT m FROM MBrandFund m WHERE m.renewalDatetime = :renewalDatetime")})
public class MBrandFund implements Serializable {

    private static final long serialVersionUID = 1L;
//<editor-fold defaultstate="collapsed" desc="プロパティ">
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "BRAND_CD")
    private String brandCd;
    @Size(max = 7)
    @Column(name = "STOCK_CODE")
    private String stockCode;
    @Column(name = "MAIN_MARKET_CD")
    private Character mainMarketCd;
    @Size(max = 7)
    @Column(name = "BRAND_MARKET_CD")
    private String brandMarketCd;
    @Size(max = 30)
    @Column(name = "MARKET_NM")
    private String marketNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "BRAND_NM")
    private String brandNm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "BRAND_NM_XEBRAL")
    private String brandNmXebral;
    @Size(max = 300)
    @Column(name = "BRAND_SNM")
    private String brandSnm;
    @Size(max = 300)
    @Column(name = "BRAND_ENM")
    private String brandEnm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "BRAND_NM_SEARCH")
    private String brandNmSearch;
    @Column(name = "TRADE_UNIT")
    private Short tradeUnit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SETTLEMENT_MM")
    private String settlementMm;
    @Size(max = 4)
    @Column(name = "BIZ_CD_TSE_33")
    private String bizCdTse33;
    @Size(max = 2000)
    @Column(name = "BIZ")
    private String biz;
    @Column(name = "BIZ_OVERSEA_NUM")
    private Short bizOverseaNum;
    @Size(max = 6)
    @Column(name = "FOUND_YYMM")
    private String foundYymm;
    @Size(max = 8)
    @Column(name = "LISTED_DATE")
    private String listedDate;
    @Size(max = 8)
    @Column(name = "DELISTED_DATE")
    private String delistedDate;
    @Size(max = 400)
    @Column(name = "COMMENT_EARNINGS")
    private String commentEarnings;
    @Size(max = 200)
    @Column(name = "OFFICE_ADDRESS")
    private String officeAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "OFFICE_TEL")
    private String officeTel;
    @Size(max = 20)
    @Column(name = "HEADOFFICE_TEL")
    private String headofficeTel;
    @Size(max = 80)
    @Column(name = "OFFICE_URL")
    private String officeUrl;
    @Size(max = 40)
    @Column(name = "REPRESENT_DIRECTOR_POST_NM")
    private String representDirectorPostNm;
    @Size(max = 40)
    @Column(name = "REPRESENT_DIRECTOR_NM")
    private String representDirectorNm;
    @Size(max = 2000)
    @Column(name = "BRANCH_INFO")
    private String branchInfo;
    @Column(name = "EMPLOYEE_NUM_CNCT")
    private Integer employeeNumCnct;
    @Column(name = "EMPLOYEE_NUM_ALON")
    private Integer employeeNumAlon;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AVG_AGE")
    private Float avgAge;
    @Column(name = "AVG_INCOME")
    private BigInteger avgIncome;
    @Column(name = "AVG_WORK")
    private Float avgWork;
    @Size(max = 500)
    @Column(name = "DEALING_STOCK_COM_NM")
    private String dealingStockComNm;
    @Size(max = 500)
    @Column(name = "DEALING_BANK_NM")
    private String dealingBankNm;
    @Size(max = 100)
    @Column(name = "AUDITING_FIRM")
    private String auditingFirm;
    @Column(name = "SAME_TRADE_RANK")
    private Short sameTradeRank;
    @Column(name = "SAME_TRADE_NUM")
    private Short sameTradeNum;
    @Column(name = "STOCKHOLDER_UNIT")
    private Float stockholderUnit;
    @Column(name = "STOCK_OUTSTANDING")
    private Double stockOutstanding;
    @Column(name = "STOCKHOLD_OVERSEA")
    private Float stockholdOversea;
    @Column(name = "STOCKHOLD_INVESTMENT")
    private Float stockholdInvestment;
    @Column(name = "STOCKHOLD_FLOAT")
    private Float stockholdFloat;
    @Column(name = "STOCKHOLD_SPECIFIC")
    private Float stockholdSpecific;
    @Column(name = "PBR_RECENT_YEAR")
    private Float pbrRecentYear;
    @Column(name = "PBR_RECENT_QUARTER")
    private Float pbrRecentQuarter;
    @Column(name = "PER_RECENT_YEAR")
    private Float perRecentYear;
    @Column(name = "PER_RECENT_LTM")
    private Float perRecentLtm;
    @Column(name = "PER_FORECAST_YEAR")
    private Float perForecastYear;
    @Column(name = "DPS_INTEREST_FORECAST")
    private Float dpsInterestForecast;
    @Size(max = 8000)
    @Column(name = "INCENTIVES_INFO")
    private String incentivesInfo;
    @Size(max = 6)
    @Column(name = "SETTLEMENT_TERM")
    private String settlementTerm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DC_KBN")
    private Character dcKbn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIKKEI_225_FLG")
    private Character nikkei225Flg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COM_LOGO_IMG_PATH")
    private String comLogoImgPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "DATA_UPLOAD_DATETIME")
    private String dataUploadDatetime;
    @Size(max = 200)
    @Column(name = "SEGMENT")
    private String segment;
    @Size(max = 8)
    @Column(name = "LATEST_FQ_END_DATE")
    private String latestFqEndDate;
    @Size(max = 8)
    @Column(name = "LATEST_FY_END_DATE")
    private String latestFyEndDate;
    @Size(max = 8)
    @Column(name = "LATEST_SETTLEMENT_DATE")
    private String latestSettlementDate;
    @Size(max = 8)
    @Column(name = "NEXT_SETTLEMENT_DATE")
    private String nextSettlementDate;
    @Column(name = "BRIEFING_MTG")
    private Boolean briefingMtg;
    @Size(max = 200)
    @Column(name = "BRIEFING_MTG_AUDIENCE")
    private String briefingMtgAudience;
    @Size(max = 200)
    @Column(name = "TITLE_AND_NAME_OF_REPRESENTATIVE")
    private String titleAndNameOfRepresentative;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTER_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDatetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENEWAL_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date renewalDatetime;
//</editor-fold>
    /**
     * コンストラクタ
     */
    public MBrandFund() {
    }
    /**
     * コンストラクタ
     * @param brandCd 企業コード
     */
    public MBrandFund(String brandCd) {
        this.brandCd = brandCd;
    }
    /**
     * コンストラクタ
     * @param brandCd
     * @param brandNm
     * @param brandNmXebral
     * @param brandNmSearch
     * @param settlementMm
     * @param officeTel
     * @param dcKbn
     * @param nikkei225Flg
     * @param comLogoImgPath
     * @param dataUploadDatetime
     * @param registerDatetime
     * @param renewalDatetime 
     */
    public MBrandFund(String brandCd, String brandNm, String brandNmXebral, String brandNmSearch, String settlementMm, String officeTel, Character dcKbn, Character nikkei225Flg, String comLogoImgPath, String dataUploadDatetime, Date registerDatetime, Date renewalDatetime) {
        this.brandCd = brandCd;
        this.brandNm = brandNm;
        this.brandNmXebral = brandNmXebral;
        this.brandNmSearch = brandNmSearch;
        this.settlementMm = settlementMm;
        this.officeTel = officeTel;
        this.dcKbn = dcKbn;
        this.nikkei225Flg = nikkei225Flg;
        this.comLogoImgPath = comLogoImgPath;
        this.dataUploadDatetime = dataUploadDatetime;
        this.registerDatetime = registerDatetime;
        this.renewalDatetime = renewalDatetime;
    }

//<editor-fold defaultstate="collapsed" desc="setter/getter">
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
    
    public Character getMainMarketCd() {
        return mainMarketCd;
    }
    
    public void setMainMarketCd(Character mainMarketCd) {
        this.mainMarketCd = mainMarketCd;
    }
    
    public String getBrandMarketCd() {
        return brandMarketCd;
    }
    
    public void setBrandMarketCd(String brandMarketCd) {
        this.brandMarketCd = brandMarketCd;
    }
    
    public String getMarketNm() {
        return marketNm;
    }
    
    public void setMarketNm(String marketNm) {
        this.marketNm = marketNm;
    }
    
    public String getBrandNm() {
        return brandNm;
    }
    
    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }
    
    public String getBrandNmXebral() {
        return brandNmXebral;
    }
    
    public void setBrandNmXebral(String brandNmXebral) {
        this.brandNmXebral = brandNmXebral;
    }
    
    public String getBrandSnm() {
        return brandSnm;
    }
    
    public void setBrandSnm(String brandSnm) {
        this.brandSnm = brandSnm;
    }
    
    public String getBrandEnm() {
        return brandEnm;
    }
    
    public void setBrandEnm(String brandEnm) {
        this.brandEnm = brandEnm;
    }
    
    public String getBrandNmSearch() {
        return brandNmSearch;
    }
    
    public void setBrandNmSearch(String brandNmSearch) {
        this.brandNmSearch = brandNmSearch;
    }
    
    public Short getTradeUnit() {
        return tradeUnit;
    }
    
    public void setTradeUnit(Short tradeUnit) {
        this.tradeUnit = tradeUnit;
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
    
    public Short getBizOverseaNum() {
        return bizOverseaNum;
    }
    
    public void setBizOverseaNum(Short bizOverseaNum) {
        this.bizOverseaNum = bizOverseaNum;
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
    
    public String getCommentEarnings() {
        return commentEarnings;
    }
    
    public void setCommentEarnings(String commentEarnings) {
        this.commentEarnings = commentEarnings;
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
    
    public String getHeadofficeTel() {
        return headofficeTel;
    }
    
    public void setHeadofficeTel(String headofficeTel) {
        this.headofficeTel = headofficeTel;
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
    
    public String getBranchInfo() {
        return branchInfo;
    }
    
    public void setBranchInfo(String branchInfo) {
        this.branchInfo = branchInfo;
    }
    
    public Integer getEmployeeNumCnct() {
        return employeeNumCnct;
    }
    
    public void setEmployeeNumCnct(Integer employeeNumCnct) {
        this.employeeNumCnct = employeeNumCnct;
    }
    
    public Integer getEmployeeNumAlon() {
        return employeeNumAlon;
    }
    
    public void setEmployeeNumAlon(Integer employeeNumAlon) {
        this.employeeNumAlon = employeeNumAlon;
    }
    
    public Float getAvgAge() {
        return avgAge;
    }
    
    public void setAvgAge(Float avgAge) {
        this.avgAge = avgAge;
    }
    
    public BigInteger getAvgIncome() {
        return avgIncome;
    }
    
    public void setAvgIncome(BigInteger avgIncome) {
        this.avgIncome = avgIncome;
    }
    
    public Float getAvgWork() {
        return avgWork;
    }
    
    public void setAvgWork(Float avgWork) {
        this.avgWork = avgWork;
    }
    
    public String getDealingStockComNm() {
        return dealingStockComNm;
    }
    
    public void setDealingStockComNm(String dealingStockComNm) {
        this.dealingStockComNm = dealingStockComNm;
    }
    
    public String getDealingBankNm() {
        return dealingBankNm;
    }
    
    public void setDealingBankNm(String dealingBankNm) {
        this.dealingBankNm = dealingBankNm;
    }
    
    public String getAuditingFirm() {
        return auditingFirm;
    }
    
    public void setAuditingFirm(String auditingFirm) {
        this.auditingFirm = auditingFirm;
    }
    
    public Short getSameTradeRank() {
        return sameTradeRank;
    }
    
    public void setSameTradeRank(Short sameTradeRank) {
        this.sameTradeRank = sameTradeRank;
    }
    
    public Short getSameTradeNum() {
        return sameTradeNum;
    }
    
    public void setSameTradeNum(Short sameTradeNum) {
        this.sameTradeNum = sameTradeNum;
    }
    
    public Float getStockholderUnit() {
        return stockholderUnit;
    }
    
    public void setStockholderUnit(Float stockholderUnit) {
        this.stockholderUnit = stockholderUnit;
    }
    
    public Double getStockOutstanding() {
        return stockOutstanding;
    }
    
    public void setStockOutstanding(Double stockOutstanding) {
        this.stockOutstanding = stockOutstanding;
    }
    
    public Float getStockholdOversea() {
        return stockholdOversea;
    }
    
    public void setStockholdOversea(Float stockholdOversea) {
        this.stockholdOversea = stockholdOversea;
    }
    
    public Float getStockholdInvestment() {
        return stockholdInvestment;
    }
    
    public void setStockholdInvestment(Float stockholdInvestment) {
        this.stockholdInvestment = stockholdInvestment;
    }
    
    public Float getStockholdFloat() {
        return stockholdFloat;
    }
    
    public void setStockholdFloat(Float stockholdFloat) {
        this.stockholdFloat = stockholdFloat;
    }
    
    public Float getStockholdSpecific() {
        return stockholdSpecific;
    }
    
    public void setStockholdSpecific(Float stockholdSpecific) {
        this.stockholdSpecific = stockholdSpecific;
    }
    
    public Float getPbrRecentYear() {
        return pbrRecentYear;
    }
    
    public void setPbrRecentYear(Float pbrRecentYear) {
        this.pbrRecentYear = pbrRecentYear;
    }
    
    public Float getPbrRecentQuarter() {
        return pbrRecentQuarter;
    }
    
    public void setPbrRecentQuarter(Float pbrRecentQuarter) {
        this.pbrRecentQuarter = pbrRecentQuarter;
    }
    
    public Float getPerRecentYear() {
        return perRecentYear;
    }
    
    public void setPerRecentYear(Float perRecentYear) {
        this.perRecentYear = perRecentYear;
    }
    
    public Float getPerRecentLtm() {
        return perRecentLtm;
    }
    
    public void setPerRecentLtm(Float perRecentLtm) {
        this.perRecentLtm = perRecentLtm;
    }
    
    public Float getPerForecastYear() {
        return perForecastYear;
    }
    
    public void setPerForecastYear(Float perForecastYear) {
        this.perForecastYear = perForecastYear;
    }
    
    public Float getDpsInterestForecast() {
        return dpsInterestForecast;
    }
    
    public void setDpsInterestForecast(Float dpsInterestForecast) {
        this.dpsInterestForecast = dpsInterestForecast;
    }
    
    public String getIncentivesInfo() {
        return incentivesInfo;
    }
    
    public void setIncentivesInfo(String incentivesInfo) {
        this.incentivesInfo = incentivesInfo;
    }
    
    public String getSettlementTerm() {
        return settlementTerm;
    }
    
    public void setSettlementTerm(String settlementTerm) {
        this.settlementTerm = settlementTerm;
    }
    
    public Character getDcKbn() {
        return dcKbn;
    }
    
    public void setDcKbn(Character dcKbn) {
        this.dcKbn = dcKbn;
    }
    
    public Character getNikkei225Flg() {
        return nikkei225Flg;
    }
    
    public void setNikkei225Flg(Character nikkei225Flg) {
        this.nikkei225Flg = nikkei225Flg;
    }
    
    public String getComLogoImgPath() {
        return comLogoImgPath;
    }
    
    public void setComLogoImgPath(String comLogoImgPath) {
        this.comLogoImgPath = comLogoImgPath;
    }
    
    public String getDataUploadDatetime() {
        return dataUploadDatetime;
    }
    
    public void setDataUploadDatetime(String dataUploadDatetime) {
        this.dataUploadDatetime = dataUploadDatetime;
    }
    
    public String getSegment() {
        return segment;
    }
    
    public void setSegment(String segment) {
        this.segment = segment;
    }
    
    public String getLatestFqEndDate() {
        return latestFqEndDate;
    }
    
    public void setLatestFqEndDate(String latestFqEndDate) {
        this.latestFqEndDate = latestFqEndDate;
    }
    
    public String getLatestFyEndDate() {
        return latestFyEndDate;
    }
    
    public void setLatestFyEndDate(String latestFyEndDate) {
        this.latestFyEndDate = latestFyEndDate;
    }
    
    public String getLatestSettlementDate() {
        return latestSettlementDate;
    }
    
    public void setLatestSettlementDate(String latestSettlementDate) {
        this.latestSettlementDate = latestSettlementDate;
    }
    
    public String getNextSettlementDate() {
        return nextSettlementDate;
    }
    
    public void setNextSettlementDate(String nextSettlementDate) {
        this.nextSettlementDate = nextSettlementDate;
    }
    public Boolean getBriefingMtg() {
        return briefingMtg;
    }

    public void setBriefingMtg(Boolean briefingMtg) {
        this.briefingMtg = briefingMtg;
    }

    public String getBriefingMtgAudience() {
        return briefingMtgAudience;
    }

    public void setBriefingMtgAudience(String briefingMtgAudience) {
        this.briefingMtgAudience = briefingMtgAudience;
    }

    public String getTitleAndNameOfRepresentative() {
        return titleAndNameOfRepresentative;
    }

    public void setTitleAndNameOfRepresentative(String titleAndNameOfRepresentative) {
        this.titleAndNameOfRepresentative = titleAndNameOfRepresentative;
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
//</editor-fold>
    /**
     * ハッシュ値作成
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandCd != null ? brandCd.hashCode() : 0);
        return hash;
    }
    /**
     * オブジェクトが一致するか検証
     * @param object 検査対象オブジェクト
     * @return true:一致　false:不一致
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MBrandFund)) {
            return false;
        }
        MBrandFund other = (MBrandFund) object;
        if ((this.brandCd == null && other.brandCd != null) || (this.brandCd != null && !this.brandCd.equals(other.brandCd))) {
            return false;
        }
        return true;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "fisco.fiscoir.MBrandFund[ brandCd=" + brandCd + " ]";
    }    
}
