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
 *
 * @author k00299
 */
@Stateless
public class VLoginuserFacade extends AbstractFacade<VLoginuser> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    private String mesg;

    public VLoginuserFacade() {
        super(VLoginuser.class);
    }

    public List<VLoginuser> getList(String userId){
        List<VLoginuser> mmtag =null;
        try{
            // コードマスタから情報を取得
            Query q = this.em.createNamedQuery("VLoginuser.findByUserId", VLoginuser.class )
                                .setParameter("userId", userId);
            mmtag = q.getResultList();
            
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ タグID>「"+userId+"」 の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            mesg = "　◆タグID>「"+userId+"」 に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return mmtag;
    }
    
    public String toErrString()
    {
        return mesg;
    }
   
}
