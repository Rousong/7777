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
@Table(name = "t_brand_price_current")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBrandPriceCurrent.findAll", query = "SELECT t FROM TBrandPriceCurrent t"),
    @NamedQuery(name = "TBrandPriceCurrent.findByBrandCd", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.brandCd = :brandCd"),
    @NamedQuery(name = "TBrandPriceCurrent.findByBrandMarketCd", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.brandMarketCd = :brandMarketCd"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceDatetime", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceDatetime = :priceDatetime"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceCurrent", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceCurrent = :priceCurrent"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceOpen", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceOpen = :priceOpen"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceHigh", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceHigh = :priceHigh"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceLow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceLow = :priceLow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByVwap", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.vwap = :vwap"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceLimitFrom", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceLimitFrom = :priceLimitFrom"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceLimitTo", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceLimitTo = :priceLimitTo"),
    @NamedQuery(name = "TBrandPriceCurrent.findByDifferPreDay", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.differPreDay = :differPreDay"),
    @NamedQuery(name = "TBrandPriceCurrent.findByRisefallRate", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.risefallRate = :risefallRate"),
    @NamedQuery(name = "TBrandPriceCurrent.findByTurnover", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.turnover = :turnover"),
    @NamedQuery(name = "TBrandPriceCurrent.findBySalesValue", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.salesValue = :salesValue"),
    @NamedQuery(name = "TBrandPriceCurrent.findByMinimumPurchase", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.minimumPurchase = :minimumPurchase"),
    @NamedQuery(name = "TBrandPriceCurrent.findByMarketCapitalization", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.marketCapitalization = :marketCapitalization"),
    @NamedQuery(name = "TBrandPriceCurrent.findByDpsForecast", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.dpsForecast = :dpsForecast"),
    @NamedQuery(name = "TBrandPriceCurrent.findByDpsInterestNow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.dpsInterestNow = :dpsInterestNow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceYearlyHigh", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceYearlyHigh = :priceYearlyHigh"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPriceYearlyLow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.priceYearlyLow = :priceYearlyLow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByStopHighFlg", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.stopHighFlg = :stopHighFlg"),
    @NamedQuery(name = "TBrandPriceCurrent.findByStopLowFlg", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.stopLowFlg = :stopLowFlg"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPrice9daysHigh", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.price9daysHigh = :price9daysHigh"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPrice9daysLow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.price9daysLow = :price9daysLow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPrice26daysHigh", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.price26daysHigh = :price26daysHigh"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPrice26daysLow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.price26daysLow = :price26daysLow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPrice52daysHigh", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.price52daysHigh = :price52daysHigh"),
    @NamedQuery(name = "TBrandPriceCurrent.findByPrice52daysLow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.price52daysLow = :price52daysLow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByIchmokuKumoPriceHigh", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.ichmokuKumoPriceHigh = :ichmokuKumoPriceHigh"),
    @NamedQuery(name = "TBrandPriceCurrent.findByIchmokuKumoPriceLow", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.ichmokuKumoPriceLow = :ichmokuKumoPriceLow"),
    @NamedQuery(name = "TBrandPriceCurrent.findByIchmokuKumonukeKbn", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.ichmokuKumonukeKbn = :ichmokuKumonukeKbn"),
    @NamedQuery(name = "TBrandPriceCurrent.findByRegisterDatetime", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.registerDatetime = :registerDatetime"),
    @NamedQuery(name = "TBrandPriceCurrent.findByRenewalDatetime", query = "SELECT t FROM TBrandPriceCurrent t WHERE t.renewalDatetime = :renewalDatetime")})
public class TBrandPriceCurrent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "BRAND_CD")
    private String brandCd;
    @Size(max = 7)
    @Column(name = "BRAND_MARKET_CD")
    private String brandMarketCd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "PRICE_DATETIME")
    private String priceDatetime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE_CURRENT")
    private Float priceCurrent;
    @Column(name = "PRICE_OPEN")
    private Float priceOpen;
    @Column(name = "PRICE_HIGH")
    private Float priceHigh;
    @Column(name = "PRICE_LOW")
    private Float priceLow;
    @Column(name = "VWAP")
    private BigInteger vwap;
    @Column(name = "PRICE_LIMIT_FROM")
    private Float priceLimitFrom;
    @Column(name = "PRICE_LIMIT_TO")
    private Float priceLimitTo;
    @Column(name = "DIFFER_PRE_DAY")
    private Float differPreDay;
    @Column(name = "RISEFALL_RATE")
    private Float risefallRate;
    @Column(name = "TURNOVER")
    private Integer turnover;
    @Column(name = "SALES_VALUE")
    private BigInteger salesValue;
    @Column(name = "MINIMUM_PURCHASE")
    private Integer minimumPurchase;
    @Column(name = "MARKET_CAPITALIZATION")
    private BigInteger marketCapitalization;
    @Column(name = "DPS_FORECAST")
    private Double dpsForecast;
    @Column(name = "DPS_INTEREST_NOW")
    private Double dpsInterestNow;
    @Column(name = "PRICE_YEARLY_HIGH")
    private Float priceYearlyHigh;
    @Column(name = "PRICE_YEARLY_LOW")
    private Float priceYearlyLow;
    @Column(name = "STOP_HIGH_FLG")
    private Character stopHighFlg;
    @Column(name = "STOP_LOW_FLG")
    private Character stopLowFlg;
    @Column(name = "PRICE_9DAYS_HIGH")
    private Float price9daysHigh;
    @Column(name = "PRICE_9DAYS_LOW")
    private Float price9daysLow;
    @Column(name = "PRICE_26DAYS_HIGH")
    private Float price26daysHigh;
    @Column(name = "PRICE_26DAYS_LOW")
    private Float price26daysLow;
    @Column(name = "PRICE_52DAYS_HIGH")
    private Float price52daysHigh;
    @Column(name = "PRICE_52DAYS_LOW")
    private Float price52daysLow;
    @Column(name = "ICHMOKU_KUMO_PRICE_HIGH")
    private Float ichmokuKumoPriceHigh;
    @Column(name = "ICHMOKU_KUMO_PRICE_LOW")
    private Float ichmokuKumoPriceLow;
    @Column(name = "ICHMOKU_KUMONUKE_KBN")
    private Character ichmokuKumonukeKbn;
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

    public TBrandPriceCurrent() {
    }

    public TBrandPriceCurrent(String brandCd) {
        this.brandCd = brandCd;
    }

    public TBrandPriceCurrent(String brandCd, String priceDatetime, Date registerDatetime, Date renewalDatetime) {
        this.brandCd = brandCd;
        this.priceDatetime = priceDatetime;
        this.registerDatetime = registerDatetime;
        this.renewalDatetime = renewalDatetime;
    }

    public String getBrandCd() {
        return brandCd;
    }

    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }

    public String getBrandMarketCd() {
        return brandMarketCd;
    }

    public void setBrandMarketCd(String brandMarketCd) {
        this.brandMarketCd = brandMarketCd;
    }

    public String getPriceDatetime() {
        return priceDatetime;
    }

    public void setPriceDatetime(String priceDatetime) {
        this.priceDatetime = priceDatetime;
    }

    public Float getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(Float priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public Float getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(Float priceOpen) {
        this.priceOpen = priceOpen;
    }

    public Float getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(Float priceHigh) {
        this.priceHigh = priceHigh;
    }

    public Float getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Float priceLow) {
        this.priceLow = priceLow;
    }

    public BigInteger getVwap() {
        return vwap;
    }

    public void setVwap(BigInteger vwap) {
        this.vwap = vwap;
    }

    public Float getPriceLimitFrom() {
        return priceLimitFrom;
    }

    public void setPriceLimitFrom(Float priceLimitFrom) {
        this.priceLimitFrom = priceLimitFrom;
    }

    public Float getPriceLimitTo() {
        return priceLimitTo;
    }

    public void setPriceLimitTo(Float priceLimitTo) {
        this.priceLimitTo = priceLimitTo;
    }

    public Float getDifferPreDay() {
        return differPreDay;
    }

    public void setDifferPreDay(Float differPreDay) {
        this.differPreDay = differPreDay;
    }

    public Float getRisefallRate() {
        return risefallRate;
    }

    public void setRisefallRate(Float risefallRate) {
        this.risefallRate = risefallRate;
    }

    public Integer getTurnover() {
        return turnover;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public BigInteger getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(BigInteger salesValue) {
        this.salesValue = salesValue;
    }

    public Integer getMinimumPurchase() {
        return minimumPurchase;
    }

    public void setMinimumPurchase(Integer minimumPurchase) {
        this.minimumPurchase = minimumPurchase;
    }

    public BigInteger getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(BigInteger marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Double getDpsForecast() {
        return dpsForecast;
    }

    public void setDpsForecast(Double dpsForecast) {
        this.dpsForecast = dpsForecast;
    }

    public Double getDpsInterestNow() {
        return dpsInterestNow;
    }

    public void setDpsInterestNow(Double dpsInterestNow) {
        this.dpsInterestNow = dpsInterestNow;
    }

    public Float getPriceYearlyHigh() {
        return priceYearlyHigh;
    }

    public void setPriceYearlyHigh(Float priceYearlyHigh) {
        this.priceYearlyHigh = priceYearlyHigh;
    }

    public Float getPriceYearlyLow() {
        return priceYearlyLow;
    }

    public void setPriceYearlyLow(Float priceYearlyLow) {
        this.priceYearlyLow = priceYearlyLow;
    }

    public Character getStopHighFlg() {
        return stopHighFlg;
    }

    public void setStopHighFlg(Character stopHighFlg) {
        this.stopHighFlg = stopHighFlg;
    }

    public Character getStopLowFlg() {
        return stopLowFlg;
    }

    public void setStopLowFlg(Character stopLowFlg) {
        this.stopLowFlg = stopLowFlg;
    }

    public Float getPrice9daysHigh() {
        return price9daysHigh;
    }

    public void setPrice9daysHigh(Float price9daysHigh) {
        this.price9daysHigh = price9daysHigh;
    }

    public Float getPrice9daysLow() {
        return price9daysLow;
    }

    public void setPrice9daysLow(Float price9daysLow) {
        this.price9daysLow = price9daysLow;
    }

    public Float getPrice26daysHigh() {
        return price26daysHigh;
    }

    public void setPrice26daysHigh(Float price26daysHigh) {
        this.price26daysHigh = price26daysHigh;
    }

    public Float getPrice26daysLow() {
        return price26daysLow;
    }

    public void setPrice26daysLow(Float price26daysLow) {
        this.price26daysLow = price26daysLow;
    }

    public Float getPrice52daysHigh() {
        return price52daysHigh;
    }

    public void setPrice52daysHigh(Float price52daysHigh) {
        this.price52daysHigh = price52daysHigh;
    }

    public Float getPrice52daysLow() {
        return price52daysLow;
    }

    public void setPrice52daysLow(Float price52daysLow) {
        this.price52daysLow = price52daysLow;
    }

    public Float getIchmokuKumoPriceHigh() {
        return ichmokuKumoPriceHigh;
    }

    public void setIchmokuKumoPriceHigh(Float ichmokuKumoPriceHigh) {
        this.ichmokuKumoPriceHigh = ichmokuKumoPriceHigh;
    }

    public Float getIchmokuKumoPriceLow() {
        return ichmokuKumoPriceLow;
    }

    public void setIchmokuKumoPriceLow(Float ichmokuKumoPriceLow) {
        this.ichmokuKumoPriceLow = ichmokuKumoPriceLow;
    }

    public Character getIchmokuKumonukeKbn() {
        return ichmokuKumonukeKbn;
    }

    public void setIchmokuKumonukeKbn(Character ichmokuKumonukeKbn) {
        this.ichmokuKumonukeKbn = ichmokuKumonukeKbn;
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
        hash += (brandCd != null ? brandCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBrandPriceCurrent)) {
            return false;
        }
        TBrandPriceCurrent other = (TBrandPriceCurrent) object;
        if ((this.brandCd == null && other.brandCd != null) || (this.brandCd != null && !this.brandCd.equals(other.brandCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.TBrandPriceCurrent[ brandCd=" + brandCd + " ]";
    }
    
}
