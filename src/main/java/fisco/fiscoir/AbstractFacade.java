/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *　エンティティ操作の基底クラス
 * @author k00299
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
/**
 * コンストラクタ
 * @param entityClass 
 */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
/**
 * インターフェース
 * @return 
 */
    protected abstract EntityManager getEntityManager();

    /**
     * エンティティ作成メソット
     * @param entity 
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    /**
     * エンティティ編集メソッド
     * @param entity 
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    /**
     * エンティティ削除メソット
     * @param entity 
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    /**
     * エンティティ検索メソット（キー検索）
     * @param id    検索キー
     * @return　検索結果
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * エンティティ検索メソッド（全検索）
     * @return 検索結果リスト
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    /**
     * エンティティ検索（範囲）
     * @param range　検索結果リスト
     * @return 
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * エンティティのレコード数カウント
     * @return エンティティのレコード数
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
