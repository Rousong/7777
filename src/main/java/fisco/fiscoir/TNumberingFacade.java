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
 *
 * @author k00299
 */
@Stateless
public class TNumberingFacade extends AbstractFacade<TNumbering> {

    @PersistenceContext(unitName = "FIRScrmUnit")
    private EntityManager em;

    public static final String MEMOID = "MEMOID";
    private static final Integer MINNUMBER = 1;
    private static final Integer MAXNUMBER = 999999;
    
    private String  message;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TNumberingFacade() {
        super(TNumbering.class);
    }
    
    public Integer newNumber(String numberCd)
    {
        TNumbering tnb;
        Integer ret = 0;
        
        try{
            tnb = em.find(TNumbering.class, numberCd );
            if( tnb==null ){
                return 0;
            }else if( tnb.getNumber() < MAXNUMBER){
                tnb.setNumber(  tnb.getNumber()+1 );
            }else{
                //１週まわって、１～にする
                tnb.setNumber(MINNUMBER);
            }
            //  反映させる
            em.flush();
            //
            ret = tnb.getNumber();
        }catch(IllegalArgumentException | javax.persistence.TransactionRequiredException iaEx){
            message = iaEx.getMessage();
            ret = 0;
        }
        
        return ret;
    }
    public Integer createNumbering(){
        Integer ret = 0;
        
        TNumbering tnb = new TNumbering();
        tnb.setNumberCd(MEMOID);
        tnb.setNumber(MINNUMBER);

        try{
            em.persist(tnb);
            ret = tnb.getNumber();
        }catch(IllegalArgumentException | javax.persistence.TransactionRequiredException iaEx){
            message = iaEx.getMessage();
            ret = null;
        }
        return ret;
    }
    
    public String toErrmsg() {
        return "fisco.fiscoir.TNumberingFacade[ numberCd=" + message + " ]";
    }

}
