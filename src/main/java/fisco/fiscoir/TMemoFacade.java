/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author k00299
 */
@Stateless
public class TMemoFacade extends AbstractFacade<TMemo> {

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
        public final static String NATIV_QUERY_LASTUP = "select tm.MEMO_ID,"
                + " tm.BRAND_CD,"
                + " tm.RENEWAL_DATETIME"
                + " from t_memo tm"
                + " order by tm.RENEWAL_DATETIME DESC";
//                + " group by tm.BRAND_CD";

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    private String mesg;

    public TMemoFacade() {
        super(TMemo.class);
    }
        @Override
    public void create(TMemo obj){
        boolean ret = false;
        try{
            em.persist(obj);
            ret = true;
        }catch(EntityExistsException etEx){
            mesg = "　◆ ＜メモ＞の更新失敗【EntityExistsException::"+etEx.toString()+"】";            
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ ＜メモ＞の更新失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(TransactionRequiredException trEx){
            mesg = "　◆＜ メモ＞有効なトランザクションなし 【TransactionRequiredException::"+trEx.toString()+"】";
        }
        //return ret;
    }
    public boolean update(TMemo obj){
        boolean ret = false;
        try{
            em.merge(obj);
            ret = true;
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ ＜メモ＞の更新失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(TransactionRequiredException trEx){
            mesg = "　◆＜ メモ＞有効なトランザクションなし 【TransactionRequiredException::"+trEx.toString()+"】";
        }
        return ret;
    }
    
    public TMemo find(String memoID){
        TMemo memo;
        memo=null;
        try{
            memo = em.find(TMemo.class, memoID);
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ ＜メモ＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            mesg = "　◆＜ メモ＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return memo;
    }
    
    public boolean deleate(String memoID){
        boolean ret = true;
        try{
            TMemo memo = this.find(memoID);
            this.remove(memo);
        }catch( IllegalArgumentException iaEx){
            mesg = "　◆ ＜メモ＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
            ret = false;
        }
        return ret;
    }

    public List<TMemo> finds(String query){
        List<TMemo> memos;
        memos=null;
        try{
            memos = em.createNativeQuery(query,TMemo.class)
                        .getResultList();
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ ＜メモ＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            mesg = "　◆＜ メモ＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return memos;
    }

    public List<TMemo> finds_lastUpdate(){
        List<TMemo> memos;
        memos=null;
        try{
            memos = em.createNativeQuery(NATIV_QUERY_LASTUP,TMemo.class)
                        .getResultList();
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ ＜メモ＞の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            mesg = "　◆＜ メモ＞条件に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return memos;
    }
    
    public String toErrString()
    {
        return mesg;
    }

}
