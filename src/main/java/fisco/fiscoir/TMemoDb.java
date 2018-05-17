/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author k00299
 */
@Stateless
public class TMemoDb extends Db{
    
//<editor-fold defaultstate="collapsed" desc="NATIVE_QUERY_BASE の定義">
    public final static String NATIVE_QUERY_BASE = "select tm.MEMO_ID,"
            + "tm.USER_ID, "
            + "tm.BRAND_CD,"
            + "tm.POST_NM,"
            + "tm.PERSON_NM,"
            + "tm.DATE_FROM,"
            + "tm.DATE_TO,"
            + "tm.TIME_FROM,"
            + "tm.TIME_TO,"
            + "tm.TAG1,"
            + "tm.TAG2,"
            + "tm.TAG3,"
            + "tm.TITLE,"
            + "tm.MEMO,"
            + "tm.REGISTER_DATETIME,"
            + "tm.REGISTER_USER,"
            + "tm.RENEWAL_DATETIME,"
            + "tm.RENEWAL_USER"
            + " from t_memo tm";
//</editor-fold>
    
    @PersistenceContext
    private EntityManager em;
    
    public void create(TMemo obj){
        em.persist(obj);
    }
    public void update(TMemo obj){
        em.merge(obj);
    }
    public TMemo find(String memoID){
        return em.find(TMemo.class, memoID);
    }
    public List<TMemo> finds(String query){
        List<TMemo> memos;
        memos = em.createNativeQuery(query,TMemo.class)
                    .getResultList();
        return memos;
    }
}
