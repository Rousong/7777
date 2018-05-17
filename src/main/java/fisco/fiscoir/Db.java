/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author k00299
 */
public class Db<T> {
    @PersistenceContext
    private EntityManager em;
    
    public void create(T obj){
        em.persist(obj);
    }
    public void update(T obj){
        em.merge(obj);
    }
}
