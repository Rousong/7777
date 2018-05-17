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
 * コードマスタのエンティティクラスのセッションBean 
 * @author k00299
 */
@Stateless
public class MCodeFacade extends AbstractFacade<MCode> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    private String mesg;

    public MCodeFacade() {
        super(MCode.class);
    }
    public static final String GROUPID_BIZTSE33CD= "001";
    public static final String GROUPID_SETTLEMM = "021";
    public static final String GROUPID_MARKET = "006";

    
    public List<MCode> getList(String gId){
        List<MCode> mcodes;
        mcodes = null;
        try{
            // コードマスタから情報を取得
            Query q = this.em.createNamedQuery("MCode.findByGroupId", MCode.class )
                                .setParameter("groupId", gId);
            mcodes = q.getResultList();
        }catch(IllegalArgumentException iaEx){
            mesg = "　◆ グループＩＤ>「"+gId+"」 の取得失敗【IllegalArgumentException::"+iaEx.toString()+"】";
        }catch(NoResultException  nrEx){
            mesg = "　◆グループＩＤ>「"+gId+"」 に該当するデータなし 【NoResultException::"+nrEx.toString()+"】";
        }
        return mcodes;
    }
    public String toErrString()
    {
        return mesg;
    }
}
