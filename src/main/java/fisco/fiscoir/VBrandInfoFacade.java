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
public class VBrandInfoFacade extends AbstractFacade<VBrandInfo> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    private String errmesg;
    
    public VBrandInfoFacade() {
        super(VBrandInfo.class);
    }
    public List<VBrandInfo> finds(String nativQuery)
    {
        List<VBrandInfo> vfnds = null;
        try{
            System.out.println("◆銘柄情報Viewの取得開始");
            //  DBから企業情報を取得
            vfnds = em.createNativeQuery(nativQuery,VBrandInfo.class)
                        .getResultList();
            System.out.println( "◆検索結果数::"+vfnds.size()+"件");
        }catch(IllegalArgumentException iaEx){
            errmesg = "　◆ ＜銘柄情報View＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            errmesg = "　◆＜銘柄情報View＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return vfnds;
    }
    public String toErrString()
    {
        return errmesg;
    }

}
