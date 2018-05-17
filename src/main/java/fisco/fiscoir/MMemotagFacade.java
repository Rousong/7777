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
import javax.persistence.Query;

/**
 *  メモタグのエンティティクラスのセッションBean
 * @author k00299
 */
@Stateless
public class MMemotagFacade extends AbstractFacade<MMemotag> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    private String mesg;
    //
    public final String MEMOTAGID_1= "100";
    public final String MEMOTAGID_2 = "200";
    public final String MEMOTAGID_3 = "300";


    public MMemotagFacade() {
        super(MMemotag.class);
    }

    public List<MMemotag> getList(String tId){
        List<MMemotag> mmtag =null;
        try{
            // コードマスタから情報を取得
            Query q = this.em.createNamedQuery("MMemotag.findByTagId", MMemotag.class )
                                .setParameter("tagId", tId);
            mmtag = q.getResultList();
            
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ タグID>「"+tId+"」 の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            mesg = "　◆タグID>「"+tId+"」 に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return mmtag;
    }

    public String toErrString()
    {
        return mesg;
    }

}
