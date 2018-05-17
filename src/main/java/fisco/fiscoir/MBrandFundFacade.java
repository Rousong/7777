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
 * 銘柄情報_基本のエンティティクラスのセッションBean
 * @author k00299
 */
@Stateless
public class MBrandFundFacade extends AbstractFacade<MBrandFund> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    private String errmesg;

    public MBrandFundFacade() {
        super(MBrandFund.class);
    }
    /**
     * 検索結果リストの取得
     * @param nativQuery　検索用のクエリ
     * @return 検索結果のリスト
     */
    public List<MBrandFund> finds( String nativQuery){
        /* @NOTEST */
        List<MBrandFund> bfnds = null;
        try{
            System.out.println("◆銘柄情報 基本の取得開始");
            //  DBから企業情報を取得
            bfnds = em.createNativeQuery(nativQuery,MBrandFund.class)
                        .getResultList();
            System.out.println( "◆検索結果数::"+bfnds.size()+"件");
        }catch(IllegalArgumentException iaEx){
            errmesg = "　◆ ＜銘柄情報 基本＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            errmesg = "　◆＜銘柄情報 基本＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return bfnds;
    }
    /**
     * 検索
     * @param brandCd　検索対象の企業コード
     * @return 検索結果の銘柄情報
     */
    public MBrandFund find(String brandCd){
        /* @NOTEST */
        MBrandFund bfnd;
        bfnd= null;
        try{
            System.out.println("◆銘柄情報 基本の取得開始");
            //  DBから企業情報を取得
            bfnd = em.find(MBrandFund.class,brandCd);
        }catch(IllegalArgumentException iaEx){
            errmesg = "　◆ ＜銘柄情報 基本＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            errmesg = "　◆＜ 銘柄情報 基本＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return bfnd;
    }
    /**
     * エラーメッセージ取得
     * @return 
     */
    public String toErrString()
    {
        return errmesg;
    }

}
