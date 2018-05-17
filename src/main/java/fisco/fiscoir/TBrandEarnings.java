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
 *
 * @author k00299
 */
@Entity
@Table(name = "t_brand_earnings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBrandEarnings.findAll", query = "SELECT t FROM TBrandEarnings t"),
    @NamedQuery(name = "TBrandEarnings.findByBrandCd", query = "SELECT t FROM TBrandEarnings t WHERE t.tBrandEarningsPK.brandCd = :brandCd"),
    @NamedQuery(name = "TBrandEarnings.findByBrandMarketCd", query = "SELECT t FROM TBrandEarnings t WHERE t.brandMarketCd = :brandMarketCd"),
    @NamedQuery(name = "TBrandEarnings.findBySettlementTerm", query = "SELECT t FROM TBrandEarnings t WHERE t.tBrandEarningsPK.settlementTerm = :settlementTerm"),
    @NamedQuery(name = "TBrandEarnings.findBySettlementCnctAlonKbn", query = "SELECT t FROM TBrandEarnings t WHERE t.tBrandEarningsPK.settlementCnctAlonKbn = :settlementCnctAlonKbn"),
    @NamedQuery(name = "TBrandEarnings.findByResImportQuarter", query = "SELECT t FROM TBrandEarnings t WHERE t.resImportQuarter = :resImportQuarter"),
    @NamedQuery(name = "TBrandEarnings.findByResImportQuarterSettlement", query = "SELECT t FROM TBrandEarnings t WHERE t.resImportQuarterSettlement = :resImportQuarterSettlement"),
    @NamedQuery(name = "TBrandEarnings.findBySales", query = "SELECT t FROM TBrandEarnings t WHERE t.sales = :sales"),
    @NamedQuery(name = "TBrandEarnings.findByProfitGross", query = "SELECT t FROM TBrandEarnings t WHERE t.profitGross = :profitGross"),
    @NamedQuery(name = "TBrandEarnings.findByProfitSales", query = "SELECT t FROM TBrandEarnings t WHERE t.profitSales = :profitSales"),
    @NamedQuery(name = "TBrandEarnings.findByProfitOrdinary", query = "SELECT t FROM TBrandEarnings t WHERE t.profitOrdinary = :profitOrdinary"),
    @NamedQuery(name = "TBrandEarnings.findByProfitCurrent", query = "SELECT t FROM TBrandEarnings t WHERE t.profitCurrent = :profitCurrent"),
    @NamedQuery(name = "TBrandEarnings.findByEps", query = "SELECT t FROM TBrandEarnings t WHERE t.eps = :eps"),
    @NamedQuery(name = "TBrandEarnings.findByDpsYear", query = "SELECT t FROM TBrandEarnings t WHERE t.dpsYear = :dpsYear"),
    @NamedQuery(name = "TBrandEarnings.findBySales1q", query = "SELECT t FROM TBrandEarnings t WHERE t.sales1q = :sales1q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitGross1q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitGross1q = :profitGross1q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitSales1q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitSales1q = :profitSales1q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitOrdinary1q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitOrdinary1q = :profitOrdinary1q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitCurrent1q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitCurrent1q = :profitCurrent1q"),
    @NamedQuery(name = "TBrandEarnings.findByEps1q", query = "SELECT t FROM TBrandEarnings t WHERE t.eps1q = :eps1q"),
    @NamedQuery(name = "TBrandEarnings.findByDps1q", query = "SELECT t FROM TBrandEarnings t WHERE t.dps1q = :dps1q"),
    @NamedQuery(name = "TBrandEarnings.findBySales2q", query = "SELECT t FROM TBrandEarnings t WHERE t.sales2q = :sales2q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitGross2q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitGross2q = :profitGross2q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitSales2q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitSales2q = :profitSales2q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitOrdinary2q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitOrdinary2q = :profitOrdinary2q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitCurrent2q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitCurrent2q = :profitCurrent2q"),
    @NamedQuery(name = "TBrandEarnings.findByEps2q", query = "SELECT t FROM TBrandEarnings t WHERE t.eps2q = :eps2q"),
    @NamedQuery(name = "TBrandEarnings.findByDps2q", query = "SELECT t FROM TBrandEarnings t WHERE t.dps2q = :dps2q"),
    @NamedQuery(name = "TBrandEarnings.findBySales3q", query = "SELECT t FROM TBrandEarnings t WHERE t.sales3q = :sales3q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitGross3q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitGross3q = :profitGross3q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitSales3q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitSales3q = :profitSales3q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitOrdinary3q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitOrdinary3q = :profitOrdinary3q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitCurrent3q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitCurrent3q = :profitCurrent3q"),
    @NamedQuery(name = "TBrandEarnings.findByEps3q", query = "SELECT t FROM TBrandEarnings t WHERE t.eps3q = :eps3q"),
    @NamedQuery(name = "TBrandEarnings.findByDps3q", query = "SELECT t FROM TBrandEarnings t WHERE t.dps3q = :dps3q"),
    @NamedQuery(name = "TBrandEarnings.findBySales4q", query = "SELECT t FROM TBrandEarnings t WHERE t.sales4q = :sales4q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitGross4q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitGross4q = :profitGross4q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitSales4q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitSales4q = :profitSales4q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitOrdinary4q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitOrdinary4q = :profitOrdinary4q"),
    @NamedQuery(name = "TBrandEarnings.findByProfitCurrent4q", query = "SELECT t FROM TBrandEarnings t WHERE t.profitCurrent4q = :profitCurrent4q"),
    @NamedQuery(name = "TBrandEarnings.findByEps4q", query = "SELECT t FROM TBrandEarnings t WHERE t.eps4q = :eps4q"),
    @NamedQuery(name = "TBrandEarnings.findByDps4q", query = "SELECT t FROM TBrandEarnings t WHERE t.dps4q = :dps4q"),
    @NamedQuery(name = "TBrandEarnings.findByMarketCapitalization", query = "SELECT t FROM TBrandEarnings t WHERE t.marketCapitalization = :marketCapitalization"),
    @NamedQuery(name = "TBrandEarnings.findByAssets", query = "SELECT t FROM TBrandEarnings t WHERE t.assets = :assets"),
    @NamedQuery(name = "TBrandEarnings.findByCapitalStock", query = "SELECT t FROM TBrandEarnings t WHERE t.capitalStock = :capitalStock"),
    @NamedQuery(name = "TBrandEarnings.findByCapitalOwnedRate", query = "SELECT t FROM TBrandEarnings t WHERE t.capitalOwnedRate = :capitalOwnedRate"),
    @NamedQuery(name = "TBrandEarnings.findByShareholdersEquity", query = "SELECT t FROM TBrandEarnings t WHERE t.shareholdersEquity = :shareholdersEquity"),
    @NamedQuery(name = "TBrandEarnings.findByCapital", query = "SELECT t FROM TBrandEarnings t WHERE t.capital = :capital"),
    @NamedQuery(name = "TBrandEarnings.findByRetainedEarnings", query = "SELECT t FROM TBrandEarnings t WHERE t.retainedEarnings = :retainedEarnings"),
    @NamedQuery(name = "TBrandEarnings.findByInterestBearingDept", query = "SELECT t FROM TBrandEarnings t WHERE t.interestBearingDept = :interestBearingDept"),
    @NamedQuery(name = "TBrandEarnings.findByPer", query = "SELECT t FROM TBrandEarnings t WHERE t.per = :per"),
    @NamedQuery(name = "TBrandEarnings.findByPbr", query = "SELECT t FROM TBrandEarnings t WHERE t.pbr = :pbr"),
    @NamedQuery(name = "TBrandEarnings.findByBps", query = "SELECT t FROM TBrandEarnings t WHERE t.bps = :bps"),
    @NamedQuery(name = "TBrandEarnings.findByRoe", query = "SELECT t FROM TBrandEarnings t WHERE t.roe = :roe"),
    @NamedQuery(name = "TBrandEarnings.findByRoa", query = "SELECT t FROM TBrandEarnings t WHERE t.roa = :roa"),
    @NamedQuery(name = "TBrandEarnings.findByAdjustEps", query = "SELECT t FROM TBrandEarnings t WHERE t.adjustEps = :adjustEps"),
    @NamedQuery(name = "TBrandEarnings.findByCapitalInvestment", query = "SELECT t FROM TBrandEarnings t WHERE t.capitalInvestment = :capitalInvestment"),
    @NamedQuery(name = "TBrandEarnings.findByDepreciation", query = "SELECT t FROM TBrandEarnings t WHERE t.depreciation = :depreciation"),
    @NamedQuery(name = "TBrandEarnings.findByResearchDev", query = "SELECT t FROM TBrandEarnings t WHERE t.researchDev = :researchDev"),
    @NamedQuery(name = "TBrandEarnings.findByRoe12q", query = "SELECT t FROM TBrandEarnings t WHERE t.roe12q = :roe12q"),
    @NamedQuery(name = "TBrandEarnings.findByRoa12q", query = "SELECT t FROM TBrandEarnings t WHERE t.roa12q = :roa12q"),
    @NamedQuery(name = "TBrandEarnings.findByAdjustEps12q", query = "SELECT t FROM TBrandEarnings t WHERE t.adjustEps12q = :adjustEps12q"),
    @NamedQuery(name = "TBrandEarnings.findByCapitalInvestment12q", query = "SELECT t FROM TBrandEarnings t WHERE t.capitalInvestment12q = :capitalInvestment12q"),
    @NamedQuery(name = "TBrandEarnings.findByDepreciation12q", query = "SELECT t FROM TBrandEarnings t WHERE t.depreciation12q = :depreciation12q"),
    @NamedQuery(name = "TBrandEarnings.findByResearchDev12q", query = "SELECT t FROM TBrandEarnings t WHERE t.researchDev12q = :researchDev12q"),
    @NamedQuery(name = "TBrandEarnings.findByRoe34q", query = "SELECT t FROM TBrandEarnings t WHERE t.roe34q = :roe34q"),
    @NamedQuery(name = "TBrandEarnings.findByRoa34q", query = "SELECT t FROM TBrandEarnings t WHERE t.roa34q = :roa34q"),
    @NamedQuery(name = "TBrandEarnings.findByAdjustEps34q", query = "SELECT t FROM TBrandEarnings t WHERE t.adjustEps34q = :adjustEps34q"),
    @NamedQuery(name = "TBrandEarnings.findByCapitalInvestment34q", query = "SELECT t FROM TBrandEarnings t WHERE t.capitalInvestment34q = :capitalInvestment34q"),
    @NamedQuery(name = "TBrandEarnings.findByDepreciation34q", query = "SELECT t FROM TBrandEarnings t WHERE t.depreciation34q = :depreciation34q"),
    @NamedQuery(name = "TBrandEarnings.findByResearchDev34q", query = "SELECT t FROM TBrandEarnings t WHERE t.researchDev34q = :researchDev34q"),
    @NamedQuery(name = "TBrandEarnings.findByCfSales", query = "SELECT t FROM TBrandEarnings t WHERE t.cfSales = :cfSales"),
    @NamedQuery(name = "TBrandEarnings.findByCfInvestment", query = "SELECT t FROM TBrandEarnings t WHERE t.cfInvestment = :cfInvestment"),
    @NamedQuery(name = "TBrandEarnings.findByCfFinancial", query = "SELECT t FROM TBrandEarnings t WHERE t.cfFinancial = :cfFinancial"),
    @NamedQuery(name = "TBrandEarnings.findByCashBalance", query = "SELECT t FROM TBrandEarnings t WHERE t.cashBalance = :cashBalance"),
    @NamedQuery(name = "TBrandEarnings.findByStockOutstanding", query = "SELECT t FROM TBrandEarnings t WHERE t.stockOutstanding = :stockOutstanding"),
    @NamedQuery(name = "TBrandEarnings.findByCfFree", query = "SELECT t FROM TBrandEarnings t WHERE t.cfFree = :cfFree"),
    @NamedQuery(name = "TBrandEarnings.findByNetCash", query = "SELECT t FROM TBrandEarnings t WHERE t.netCash = :netCash"),
    @NamedQuery(name = "TBrandEarnings.findByNetCashRate", query = "SELECT t FROM TBrandEarnings t WHERE t.netCashRate = :netCashRate"),
    @NamedQuery(name = "TBrandEarnings.findByEbitda", query = "SELECT t FROM TBrandEarnings t WHERE t.ebitda = :ebitda"),
    @NamedQuery(name = "TBrandEarnings.findByStockholdOversea", query = "SELECT t FROM TBrandEarnings t WHERE t.stockholdOversea = :stockholdOversea"),
    @NamedQuery(name = "TBrandEarnings.findByStockholdInvestment", query = "SELECT t FROM TBrandEarnings t WHERE t.stockholdInvestment = :stockholdInvestment"),
    @NamedQuery(name = "TBrandEarnings.findByStockholdFloat", query = "SELECT t FROM TBrandEarnings t WHERE t.stockholdFloat = :stockholdFloat"),
    @NamedQuery(name = "TBrandEarnings.findByStockholdBank", query = "SELECT t FROM TBrandEarnings t WHERE t.stockholdBank = :stockholdBank"),
    @NamedQuery(name = "TBrandEarnings.findByResFinImportFlg", query = "SELECT t FROM TBrandEarnings t WHERE t.resFinImportFlg = :resFinImportFlg"),
    @NamedQuery(name = "TBrandEarnings.findByEpsPostAdjust", query = "SELECT t FROM TBrandEarnings t WHERE t.epsPostAdjust = :epsPostAdjust"),
    @NamedQuery(name = "TBrandEarnings.findByBpsPostAdjust", query = "SELECT t FROM TBrandEarnings t WHERE t.bpsPostAdjust = :bpsPostAdjust"),
    @NamedQuery(name = "TBrandEarnings.findByRegisterDatetime", query = "SELECT t FROM TBrandEarnings t WHERE t.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "TBrandEarnings.findByRenewalDatetime", query = "SELECT t FROM TBrandEarnings t WHERE t.renewalDatetime = :renewalDatetime")})
public class TBrandEarnings implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TBrandEarningsPK tBrandEarningsPK;
    @Size(max = 7)
    @Column(name = "BRAND_MARKET_CD")
    private String brandMarketCd;
    @Column(name = "RES_IMPORT_QUARTER")
    private Character resImportQuarter;
    @Size(max = 6)
    @Column(name = "RES_IMPORT_QUARTER_SETTLEMENT")
    private String resImportQuarterSettlement;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALES")
    private Double sales;
    @Column(name = "PROFIT_GROSS")
    private Double profitGross;
    @Column(name = "PROFIT_SALES")
    private Double profitSales;
    @Column(name = "PROFIT_ORDINARY")
    private Double profitOrdinary;
    @Column(name = "PROFIT_CURRENT")
    private Double profitCurrent;
    @Column(name = "EPS")
    private Double eps;
    @Column(name = "DPS_YEAR")
    private Float dpsYear;
    @Column(name = "SALES_1Q")
    private Double sales1q;
    @Column(name = "PROFIT_GROSS_1Q")
    private Double profitGross1q;
    @Column(name = "PROFIT_SALES_1Q")
    private Double profitSales1q;
    @Column(name = "PROFIT_ORDINARY_1Q")
    private Double profitOrdinary1q;
    @Column(name = "PROFIT_CURRENT_1Q")
    private Double profitCurrent1q;
    @Column(name = "EPS_1Q")
    private Double eps1q;
    @Column(name = "DPS_1Q")
    private Float dps1q;
    @Column(name = "SALES_2Q")
    private Double sales2q;
    @Column(name = "PROFIT_GROSS_2Q")
    private Double profitGross2q;
    @Column(name = "PROFIT_SALES_2Q")
    private Double profitSales2q;
    @Column(name = "PROFIT_ORDINARY_2Q")
    private Double profitOrdinary2q;
    @Column(name = "PROFIT_CURRENT_2Q")
    private Double profitCurrent2q;
    @Column(name = "EPS_2Q")
    private Double eps2q;
    @Column(name = "DPS_2Q")
    private Float dps2q;
    @Column(name = "SALES_3Q")
    private Double sales3q;
    @Column(name = "PROFIT_GROSS_3Q")
    private Double profitGross3q;
    @Column(name = "PROFIT_SALES_3Q")
    private Double profitSales3q;
    @Column(name = "PROFIT_ORDINARY_3Q")
    private Double profitOrdinary3q;
    @Column(name = "PROFIT_CURRENT_3Q")
    private Double profitCurrent3q;
    @Column(name = "EPS_3Q")
    private Double eps3q;
    @Column(name = "DPS_3Q")
    private Float dps3q;
    @Column(name = "SALES_4Q")
    private Double sales4q;
    @Column(name = "PROFIT_GROSS_4Q")
    private Double profitGross4q;
    @Column(name = "PROFIT_SALES_4Q")
    private Double profitSales4q;
    @Column(name = "PROFIT_ORDINARY_4Q")
    private Double profitOrdinary4q;
    @Column(name = "PROFIT_CURRENT_4Q")
    private Double profitCurrent4q;
    @Column(name = "EPS_4Q")
    private Double eps4q;
    @Column(name = "DPS_4Q")
    private Float dps4q;
    @Column(name = "MARKET_CAPITALIZATION")
    private Double marketCapitalization;
    @Column(name = "ASSETS")
    private Double assets;
    @Column(name = "CAPITAL_STOCK")
    private Double capitalStock;
    @Column(name = "CAPITAL_OWNED_RATE")
    private Float capitalOwnedRate;
    @Column(name = "SHAREHOLDERS_EQUITY")
    private Double shareholdersEquity;
    @Column(name = "CAPITAL")
    private Double capital;
    @Column(name = "RETAINED_EARNINGS")
    private Double retainedEarnings;
    @Column(name = "INTEREST_BEARING_DEPT")
    private Double interestBearingDept;
    @Column(name = "PER")
    private Float per;
    @Column(name = "PBR")
    private Float pbr;
    @Column(name = "BPS")
    private Float bps;
    @Column(name = "ROE")
    private Float roe;
    @Column(name = "ROA")
    private Float roa;
    @Column(name = "ADJUST_EPS")
    private Float adjustEps;
    @Column(name = "CAPITAL_INVESTMENT")
    private Double capitalInvestment;
    @Column(name = "DEPRECIATION")
    private Double depreciation;
    @Column(name = "RESEARCH_DEV")
    private Double researchDev;
    @Column(name = "ROE_1_2Q")
    private Float roe12q;
    @Column(name = "ROA_1_2Q")
    private Float roa12q;
    @Column(name = "ADJUST_EPS_1_2Q")
    private Float adjustEps12q;
    @Column(name = "CAPITAL_INVESTMENT_1_2Q")
    private Double capitalInvestment12q;
    @Column(name = "DEPRECIATION_1_2Q")
    private Double depreciation12q;
    @Column(name = "RESEARCH_DEV_1_2Q")
    private Double researchDev12q;
    @Column(name = "ROE_3_4Q")
    private Float roe34q;
    @Column(name = "ROA_3_4Q")
    private Float roa34q;
    @Column(name = "ADJUST_EPS_3_4Q")
    private Float adjustEps34q;
    @Column(name = "CAPITAL_INVESTMENT_3_4Q")
    private Double capitalInvestment34q;
    @Column(name = "DEPRECIATION_3_4Q")
    private Double depreciation34q;
    @Column(name = "RESEARCH_DEV_3_4Q")
    private Double researchDev34q;
    @Column(name = "CF_SALES")
    private Double cfSales;
    @Column(name = "CF_INVESTMENT")
    private Double cfInvestment;
    @Column(name = "CF_FINANCIAL")
    private Double cfFinancial;
    @Column(name = "CASH_BALANCE")
    private Double cashBalance;
    @Column(name = "STOCK_OUTSTANDING")
    private Double stockOutstanding;
    @Column(name = "CF_FREE")
    private Double cfFree;
    @Column(name = "NET_CASH")
    private Double netCash;
    @Column(name = "NET_CASH_RATE")
    private Double netCashRate;
    @Column(name = "EBITDA")
    private Double ebitda;
    @Column(name = "STOCKHOLD_OVERSEA")
    private Float stockholdOversea;
    @Column(name = "STOCKHOLD_INVESTMENT")
    private Double stockholdInvestment;
    @Column(name = "STOCKHOLD_FLOAT")
    private Double stockholdFloat;
    @Column(name = "STOCKHOLD_BANK")
    private Double stockholdBank;
    @Column(name = "RES_FIN_IMPORT_FLG")
    private Character resFinImportFlg;
    @Column(name = "EPS_POST_ADJUST")
    private Double epsPostAdjust;
    @Column(name = "BPS_POST_ADJUST")
    private Float bpsPostAdjust;
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

    public TBrandEarnings() {
    }

    public TBrandEarnings(TBrandEarningsPK tBrandEarningsPK) {
        this.tBrandEarningsPK = tBrandEarningsPK;
    }

    public TBrandEarnings(TBrandEarningsPK tBrandEarningsPK, Date registerDatetime, Date renewalDatetime) {
        this.tBrandEarningsPK = tBrandEarningsPK;
        this.registerDatetime = registerDatetime;
        this.renewalDatetime = renewalDatetime;
    }

    public TBrandEarnings(String brandCd, String settlementTerm, Character settlementCnctAlonKbn) {
        this.tBrandEarningsPK = new TBrandEarningsPK(brandCd, settlementTerm, settlementCnctAlonKbn);
    }

    public TBrandEarningsPK getTBrandEarningsPK() {
        return tBrandEarningsPK;
    }

    public void setTBrandEarningsPK(TBrandEarningsPK tBrandEarningsPK) {
        this.tBrandEarningsPK = tBrandEarningsPK;
    }

    public String getBrandMarketCd() {
        return brandMarketCd;
    }

    public void setBrandMarketCd(String brandMarketCd) {
        this.brandMarketCd = brandMarketCd;
    }

    public Character getResImportQuarter() {
        return resImportQuarter;
    }

    public void setResImportQuarter(Character resImportQuarter) {
        this.resImportQuarter = resImportQuarter;
    }

    public String getResImportQuarterSettlement() {
        return resImportQuarterSettlement;
    }

    public void setResImportQuarterSettlement(String resImportQuarterSettlement) {
        this.resImportQuarterSettlement = resImportQuarterSettlement;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getProfitGross() {
        return profitGross;
    }

    public void setProfitGross(Double profitGross) {
        this.profitGross = profitGross;
    }

    public Double getProfitSales() {
        return profitSales;
    }

    public void setProfitSales(Double profitSales) {
        this.profitSales = profitSales;
    }

    public Double getProfitOrdinary() {
        return profitOrdinary;
    }

    public void setProfitOrdinary(Double profitOrdinary) {
        this.profitOrdinary = profitOrdinary;
    }

    public Double getProfitCurrent() {
        return profitCurrent;
    }

    public void setProfitCurrent(Double profitCurrent) {
        this.profitCurrent = profitCurrent;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Float getDpsYear() {
        return dpsYear;
    }

    public void setDpsYear(Float dpsYear) {
        this.dpsYear = dpsYear;
    }

    public Double getSales1q() {
        return sales1q;
    }

    public void setSales1q(Double sales1q) {
        this.sales1q = sales1q;
    }

    public Double getProfitGross1q() {
        return profitGross1q;
    }

    public void setProfitGross1q(Double profitGross1q) {
        this.profitGross1q = profitGross1q;
    }

    public Double getProfitSales1q() {
        return profitSales1q;
    }

    public void setProfitSales1q(Double profitSales1q) {
        this.profitSales1q = profitSales1q;
    }

    public Double getProfitOrdinary1q() {
        return profitOrdinary1q;
    }

    public void setProfitOrdinary1q(Double profitOrdinary1q) {
        this.profitOrdinary1q = profitOrdinary1q;
    }

    public Double getProfitCurrent1q() {
        return profitCurrent1q;
    }

    public void setProfitCurrent1q(Double profitCurrent1q) {
        this.profitCurrent1q = profitCurrent1q;
    }

    public Double getEps1q() {
        return eps1q;
    }

    public void setEps1q(Double eps1q) {
        this.eps1q = eps1q;
    }

    public Float getDps1q() {
        return dps1q;
    }

    public void setDps1q(Float dps1q) {
        this.dps1q = dps1q;
    }

    public Double getSales2q() {
        return sales2q;
    }

    public void setSales2q(Double sales2q) {
        this.sales2q = sales2q;
    }

    public Double getProfitGross2q() {
        return profitGross2q;
    }

    public void setProfitGross2q(Double profitGross2q) {
        this.profitGross2q = profitGross2q;
    }

    public Double getProfitSales2q() {
        return profitSales2q;
    }

    public void setProfitSales2q(Double profitSales2q) {
        this.profitSales2q = profitSales2q;
    }

    public Double getProfitOrdinary2q() {
        return profitOrdinary2q;
    }

    public void setProfitOrdinary2q(Double profitOrdinary2q) {
        this.profitOrdinary2q = profitOrdinary2q;
    }

    public Double getProfitCurrent2q() {
        return profitCurrent2q;
    }

    public void setProfitCurrent2q(Double profitCurrent2q) {
        this.profitCurrent2q = profitCurrent2q;
    }

    public Double getEps2q() {
        return eps2q;
    }

    public void setEps2q(Double eps2q) {
        this.eps2q = eps2q;
    }

    public Float getDps2q() {
        return dps2q;
    }

    public void setDps2q(Float dps2q) {
        this.dps2q = dps2q;
    }

    public Double getSales3q() {
        return sales3q;
    }

    public void setSales3q(Double sales3q) {
        this.sales3q = sales3q;
    }

    public Double getProfitGross3q() {
        return profitGross3q;
    }

    public void setProfitGross3q(Double profitGross3q) {
        this.profitGross3q = profitGross3q;
    }

    public Double getProfitSales3q() {
        return profitSales3q;
    }

    public void setProfitSales3q(Double profitSales3q) {
        this.profitSales3q = profitSales3q;
    }

    public Double getProfitOrdinary3q() {
        return profitOrdinary3q;
    }

    public void setProfitOrdinary3q(Double profitOrdinary3q) {
        this.profitOrdinary3q = profitOrdinary3q;
    }

    public Double getProfitCurrent3q() {
        return profitCurrent3q;
    }

    public void setProfitCurrent3q(Double profitCurrent3q) {
        this.profitCurrent3q = profitCurrent3q;
    }

    public Double getEps3q() {
        return eps3q;
    }

    public void setEps3q(Double eps3q) {
        this.eps3q = eps3q;
    }

    public Float getDps3q() {
        return dps3q;
    }

    public void setDps3q(Float dps3q) {
        this.dps3q = dps3q;
    }

    public Double getSales4q() {
        return sales4q;
    }

    public void setSales4q(Double sales4q) {
        this.sales4q = sales4q;
    }

    public Double getProfitGross4q() {
        return profitGross4q;
    }

    public void setProfitGross4q(Double profitGross4q) {
        this.profitGross4q = profitGross4q;
    }

    public Double getProfitSales4q() {
        return profitSales4q;
    }

    public void setProfitSales4q(Double profitSales4q) {
        this.profitSales4q = profitSales4q;
    }

    public Double getProfitOrdinary4q() {
        return profitOrdinary4q;
    }

    public void setProfitOrdinary4q(Double profitOrdinary4q) {
        this.profitOrdinary4q = profitOrdinary4q;
    }

    public Double getProfitCurrent4q() {
        return profitCurrent4q;
    }

    public void setProfitCurrent4q(Double profitCurrent4q) {
        this.profitCurrent4q = profitCurrent4q;
    }

    public Double getEps4q() {
        return eps4q;
    }

    public void setEps4q(Double eps4q) {
        this.eps4q = eps4q;
    }

    public Float getDps4q() {
        return dps4q;
    }

    public void setDps4q(Float dps4q) {
        this.dps4q = dps4q;
    }

    public Double getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(Double marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Double getAssets() {
        return assets;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }

    public Double getCapitalStock() {
        return capitalStock;
    }

    public void setCapitalStock(Double capitalStock) {
        this.capitalStock = capitalStock;
    }

    public Float getCapitalOwnedRate() {
        return capitalOwnedRate;
    }

    public void setCapitalOwnedRate(Float capitalOwnedRate) {
        this.capitalOwnedRate = capitalOwnedRate;
    }

    public Double getShareholdersEquity() {
        return shareholdersEquity;
    }

    public void setShareholdersEquity(Double shareholdersEquity) {
        this.shareholdersEquity = shareholdersEquity;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getRetainedEarnings() {
        return retainedEarnings;
    }

    public void setRetainedEarnings(Double retainedEarnings) {
        this.retainedEarnings = retainedEarnings;
    }

    public Double getInterestBearingDept() {
        return interestBearingDept;
    }

    public void setInterestBearingDept(Double interestBearingDept) {
        this.interestBearingDept = interestBearingDept;
    }

    public Float getPer() {
        return per;
    }

    public void setPer(Float per) {
        this.per = per;
    }

    public Float getPbr() {
        return pbr;
    }

    public void setPbr(Float pbr) {
        this.pbr = pbr;
    }

    public Float getBps() {
        return bps;
    }

    public void setBps(Float bps) {
        this.bps = bps;
    }

    public Float getRoe() {
        return roe;
    }

    public void setRoe(Float roe) {
        this.roe = roe;
    }

    public Float getRoa() {
        return roa;
    }

    public void setRoa(Float roa) {
        this.roa = roa;
    }

    public Float getAdjustEps() {
        return adjustEps;
    }

    public void setAdjustEps(Float adjustEps) {
        this.adjustEps = adjustEps;
    }

    public Double getCapitalInvestment() {
        return capitalInvestment;
    }

    public void setCapitalInvestment(Double capitalInvestment) {
        this.capitalInvestment = capitalInvestment;
    }

    public Double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Double depreciation) {
        this.depreciation = depreciation;
    }

    public Double getResearchDev() {
        return researchDev;
    }

    public void setResearchDev(Double researchDev) {
        this.researchDev = researchDev;
    }

    public Float getRoe12q() {
        return roe12q;
    }

    public void setRoe12q(Float roe12q) {
        this.roe12q = roe12q;
    }

    public Float getRoa12q() {
        return roa12q;
    }

    public void setRoa12q(Float roa12q) {
        this.roa12q = roa12q;
    }

    public Float getAdjustEps12q() {
        return adjustEps12q;
    }

    public void setAdjustEps12q(Float adjustEps12q) {
        this.adjustEps12q = adjustEps12q;
    }

    public Double getCapitalInvestment12q() {
        return capitalInvestment12q;
    }

    public void setCapitalInvestment12q(Double capitalInvestment12q) {
        this.capitalInvestment12q = capitalInvestment12q;
    }

    public Double getDepreciation12q() {
        return depreciation12q;
    }

    public void setDepreciation12q(Double depreciation12q) {
        this.depreciation12q = depreciation12q;
    }

    public Double getResearchDev12q() {
        return researchDev12q;
    }

    public void setResearchDev12q(Double researchDev12q) {
        this.researchDev12q = researchDev12q;
    }

    public Float getRoe34q() {
        return roe34q;
    }

    public void setRoe34q(Float roe34q) {
        this.roe34q = roe34q;
    }

    public Float getRoa34q() {
        return roa34q;
    }

    public void setRoa34q(Float roa34q) {
        this.roa34q = roa34q;
    }

    public Float getAdjustEps34q() {
        return adjustEps34q;
    }

    public void setAdjustEps34q(Float adjustEps34q) {
        this.adjustEps34q = adjustEps34q;
    }

    public Double getCapitalInvestment34q() {
        return capitalInvestment34q;
    }

    public void setCapitalInvestment34q(Double capitalInvestment34q) {
        this.capitalInvestment34q = capitalInvestment34q;
    }

    public Double getDepreciation34q() {
        return depreciation34q;
    }

    public void setDepreciation34q(Double depreciation34q) {
        this.depreciation34q = depreciation34q;
    }

    public Double getResearchDev34q() {
        return researchDev34q;
    }

    public void setResearchDev34q(Double researchDev34q) {
        this.researchDev34q = researchDev34q;
    }

    public Double getCfSales() {
        return cfSales;
    }

    public void setCfSales(Double cfSales) {
        this.cfSales = cfSales;
    }

    public Double getCfInvestment() {
        return cfInvestment;
    }

    public void setCfInvestment(Double cfInvestment) {
        this.cfInvestment = cfInvestment;
    }

    public Double getCfFinancial() {
        return cfFinancial;
    }

    public void setCfFinancial(Double cfFinancial) {
        this.cfFinancial = cfFinancial;
    }

    public Double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(Double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public Double getStockOutstanding() {
        return stockOutstanding;
    }

    public void setStockOutstanding(Double stockOutstanding) {
        this.stockOutstanding = stockOutstanding;
    }

    public Double getCfFree() {
        return cfFree;
    }

    public void setCfFree(Double cfFree) {
        this.cfFree = cfFree;
    }

    public Double getNetCash() {
        return netCash;
    }

    public void setNetCash(Double netCash) {
        this.netCash = netCash;
    }

    public Double getNetCashRate() {
        return netCashRate;
    }

    public void setNetCashRate(Double netCashRate) {
        this.netCashRate = netCashRate;
    }

    public Double getEbitda() {
        return ebitda;
    }

    public void setEbitda(Double ebitda) {
        this.ebitda = ebitda;
    }

    public Float getStockholdOversea() {
        return stockholdOversea;
    }

    public void setStockholdOversea(Float stockholdOversea) {
        this.stockholdOversea = stockholdOversea;
    }

    public Double getStockholdInvestment() {
        return stockholdInvestment;
    }

    public void setStockholdInvestment(Double stockholdInvestment) {
        this.stockholdInvestment = stockholdInvestment;
    }

    public Double getStockholdFloat() {
        return stockholdFloat;
    }

    public void setStockholdFloat(Double stockholdFloat) {
        this.stockholdFloat = stockholdFloat;
    }

    public Double getStockholdBank() {
        return stockholdBank;
    }

    public void setStockholdBank(Double stockholdBank) {
        this.stockholdBank = stockholdBank;
    }

    public Character getResFinImportFlg() {
        return resFinImportFlg;
    }

    public void setResFinImportFlg(Character resFinImportFlg) {
        this.resFinImportFlg = resFinImportFlg;
    }

    public Double getEpsPostAdjust() {
        return epsPostAdjust;
    }

    public void setEpsPostAdjust(Double epsPostAdjust) {
        this.epsPostAdjust = epsPostAdjust;
    }

    public Float getBpsPostAdjust() {
        return bpsPostAdjust;
    }

    public void setBpsPostAdjust(Float bpsPostAdjust) {
        this.bpsPostAdjust = bpsPostAdjust;
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
        hash += (tBrandEarningsPK != null ? tBrandEarningsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBrandEarnings)) {
            return false;
        }
        TBrandEarnings other = (TBrandEarnings) object;
        if ((this.tBrandEarningsPK == null && other.tBrandEarningsPK != null) || (this.tBrandEarningsPK != null && !this.tBrandEarningsPK.equals(other.tBrandEarningsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.TBrandEarnings[ tBrandEarningsPK=" + tBrandEarningsPK + " ]";
    }
    
}
