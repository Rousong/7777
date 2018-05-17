/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * ユーザパスワード情報のエンティティクラスのセッションBeans
 * @author k00299
 */
@Stateless
public class MUserPasswordFacade extends AbstractFacade<MUserPassword> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //
    private String mesg;

    public MUserPasswordFacade() {
        super(MUserPassword.class);
    }
  
    public String toErrString()
    {
        return mesg;
    }

    
}
