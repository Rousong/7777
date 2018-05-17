/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author k00299
 */
@Stateless
public class TBrandPriceCurrentFacade extends AbstractFacade<TBrandPriceCurrent> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TBrandPriceCurrentFacade() {
        super(TBrandPriceCurrent.class);
    }
    //
    private String errmesg;

    public List<TBrandPriceCurrent> finds( String nativQuery ){
        /* @NOTEST */
        List<TBrandPriceCurrent> bpc = null;
        try{
            System.out.println("◆銘柄情報　株価現値　取得");
            //  DBから銘柄情報　株価現値を取得
            bpc = em.createNativeQuery(nativQuery,TBrandPriceCurrent.class)
                        .getResultList();
            System.out.println( "■■検索結果数::"+bpc.size()+"件");
        }catch(IllegalArgumentException iaEx){
            this.errmesg = "　◆ ＜銘柄情報　株価現値＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            this.errmesg = "　◆＜ 銘柄情報　株価現値＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return bpc;
    }
    public String toErrString()
    {
        return errmesg;
    }

}
