/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author k00299
 */
@Embeddable
public class TBrandEarningsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "BRAND_CD")
    private String brandCd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "SETTLEMENT_TERM")
    private String settlementTerm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETTLEMENT_CNCT_ALON_KBN")
    private Character settlementCnctAlonKbn;

    public TBrandEarningsPK() {
    }

    public TBrandEarningsPK(String brandCd, String settlementTerm, Character settlementCnctAlonKbn) {
        this.brandCd = brandCd;
        this.settlementTerm = settlementTerm;
        this.settlementCnctAlonKbn = settlementCnctAlonKbn;
    }

    public String getBrandCd() {
        return brandCd;
    }

    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }

    public String getSettlementTerm() {
        return settlementTerm;
    }

    public void setSettlementTerm(String settlementTerm) {
        this.settlementTerm = settlementTerm;
    }

    public Character getSettlementCnctAlonKbn() {
        return settlementCnctAlonKbn;
    }

    public void setSettlementCnctAlonKbn(Character settlementCnctAlonKbn) {
        this.settlementCnctAlonKbn = settlementCnctAlonKbn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandCd != null ? brandCd.hashCode() : 0);
        hash += (settlementTerm != null ? settlementTerm.hashCode() : 0);
        hash += (settlementCnctAlonKbn != null ? settlementCnctAlonKbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBrandEarningsPK)) {
            return false;
        }
        TBrandEarningsPK other = (TBrandEarningsPK) object;
        if ((this.brandCd == null && other.brandCd != null) || (this.brandCd != null && !this.brandCd.equals(other.brandCd))) {
            return false;
        }
        if ((this.settlementTerm == null && other.settlementTerm != null) || (this.settlementTerm != null && !this.settlementTerm.equals(other.settlementTerm))) {
            return false;
        }
        if ((this.settlementCnctAlonKbn == null && other.settlementCnctAlonKbn != null) || (this.settlementCnctAlonKbn != null && !this.settlementCnctAlonKbn.equals(other.settlementCnctAlonKbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fisco.fiscoir.TBrandEarningsPK[ brandCd=" + brandCd + ", settlementTerm=" + settlementTerm + ", settlementCnctAlonKbn=" + settlementCnctAlonKbn + " ]";
    }
    
}
