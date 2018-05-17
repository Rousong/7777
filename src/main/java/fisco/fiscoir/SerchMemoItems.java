/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
//import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

/**
 *
 * @author k00299
 */
@Named
//@Dependent
@SessionScoped
//@WithLog
public class SerchMemoItems implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    ArrayList<selectboxItem> memoTag1;       //  タグ
    ArrayList<selectboxItem> memoTag2;       //  種別中
    ArrayList<selectboxItem> memoTag3;       //  種別小

    /* 未使用　*/
    ArrayList<selectboxItem> memoStatus;                 //  ステータス

    /*   @NOTEST */
    /* ------------------------ */
    /*      */
    /* ------------------------ */
    @EJB
    private MMemotagFacade mmtf;
    
    /* ------------------------ */
    /*  エンティティマネージャ    */
    /* ------------------------ */
    @PersistenceContext(unitName="FIRScrmUnit")
    private EntityManager em;
    
    /*  Getter/Setter   */
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    public ArrayList<selectboxItem> getMemoTag1() {
        return memoTag1;
    }
    
    public void setMemoTag1(ArrayList<selectboxItem> memoTag1) {
        this.memoTag1 = memoTag1;
    }
    
    public ArrayList<selectboxItem> getMemoTag2() {
        return memoTag2;
    }
    
    public void setMemoTag2(ArrayList<selectboxItem> memoTag2) {
        this.memoTag2 = memoTag2;
    }
    
    public ArrayList<selectboxItem> getMemoTag3() {
        return memoTag3;
    }
    
    public void setMemoTag3(ArrayList<selectboxItem> memoTag3) {
        this.memoTag3 = memoTag3;
    }
    
    public ArrayList<selectboxItem> getMemoStatus() {
        return memoStatus;
    }
    
    public void setMemoStatus(ArrayList<selectboxItem> memoStatus) {
        this.memoStatus = memoStatus;
    }
//</editor-fold>

    public SerchMemoItems() {
            System.out.println("◆SerchMemoItems:SerchMemoItems() コンストラクタ");
//            initial_selitems();
    }
    

    public void initial_selitems(){

        System.out.println("◆SerchMemoItems:initial_selitems() データ初期化");

        //  タグを取得
        this.memoTag1 = new ArrayList<>();
        this.memoTag2 = new ArrayList<>();
        this.memoTag3 = new ArrayList<>();
        initial_memokind1();
        initial_memokind2();
        initial_memokind3();
        
        /* ステータスはしばらく未使用
        this.memoStatus = new ArrayList<>();
        initial_memostatus();
        */
    }
    
    private boolean initial_memotag(String mtagId, ArrayList<selectboxItem> mtaglist )
    {
        System.out.println("◆◆メモタグ項目取得パラメータ設定");
        
        /* @NOTEST */
        List<MMemotag> mmtag = mmtf.getList(mtagId);
        //  データ取得有無を確認
        if( mmtag==null){
            System.out.println("◆◆◆失敗『"+mmtf.toString()+"』");
            //データなし。
            return false;
        }
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[mmtag.size()];

        for( Integer i=0; i<mmtag.size(); i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(((MMemotag)mmtag.get(i)).getContents1());
            sbi_list[i].setValue(((MMemotag)mmtag.get(i)).getMMemotagPK().getCode());

            mtaglist.add(sbi_list[i]);
        }
        return true;
    }
    
    private void initial_memokind1()
    {
        System.out.println("◆◆SerchMemoItems:initial_memokind1() 項目初期化");
        /* @NOTEST */
        initial_memotag(mmtf.MEMOTAGID_1, this.memoTag1);
        /*
        try{
            // コードマスタから情報を取得
            List<MMemotag> mmtag;
            Query q = this.em.createNamedQuery("MMemotag.findByTagId", MMemotag.class )
                                .setParameter("tagId", MEMOTAGID_1);
            mmtag = q.getResultList();
            
            selectboxItem sbi_list[];
            sbi_list = new selectboxItem[mmtag.size()];

            for( Integer i=0; i<mmtag.size(); i++){
                sbi_list[i] = new selectboxItem();
                sbi_list[i].setLabel(((MMemotag)mmtag.get(i)).getContents1());
                sbi_list[i].setValue(((MMemotag)mmtag.get(i)).getMMemotagPK().getCode());

                this.memoTag1.add(sbi_list[i]);
            }
        }catch(IllegalArgumentException iaEx){
            System.err.printf("　◆ 取得失敗【"+iaEx.toString()+"】");
        }catch(NoResultException  nrEx){
            System.err.printf("　◆メモタグ１「"+MEMOTAGID_1+"」に該当するデータなし【"+nrEx.toString()+"】");
        }
        */
        /* @TEST
        String[] indLabel = new String[]{" 商談","営業","打合せ","季節の挨拶","紹介","来客","訪問"};
        Integer indval = 1001;

        System.out.println("◆◆◆項目大パラメータ設定");
        
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];

        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.memoTag1.add(sbi_list[i]);
        }
        */
    }
    /**/
    private void initial_memokind2()
    {
        System.out.println("◆◆SerchMemoItems:initial_memokind2() 項目初期化");
        /* @NOTEST */
        initial_memotag(mmtf.MEMOTAGID_2, this.memoTag2);
        /*
        try{
            // コードマスタから情報を取得
            List<MMemotag> mmtag;
            Query q = this.em.createNamedQuery("MMemotag.findByTagId", MMemotag.class )
                                .setParameter("tagId", MEMOTAGID_2);
            mmtag = q.getResultList();
            
            selectboxItem sbi_list[];
            sbi_list = new selectboxItem[mmtag.size()];

            for( Integer i=0; i<mmtag.size(); i++){
                sbi_list[i] = new selectboxItem();
                sbi_list[i].setLabel(((MMemotag)mmtag.get(i)).getContents1());
                sbi_list[i].setValue(((MMemotag)mmtag.get(i)).getMMemotagPK().getCode());

                this.memoTag2.add(sbi_list[i]);
            }
        }catch(IllegalArgumentException iaEx){
            System.err.printf("　◆ 取得失敗【"+iaEx.toString()+"】");
        }catch(NoResultException  nrEx){
            System.err.printf("　◆メモタグ2「"+MEMOTAGID_2+"」に該当するデータなし【"+nrEx.toString()+"】");
        }
        */
        /* @TEST
        String[] indLabel = new String[]{" 内容・結果","受注","失注","説明","提案","宿題","見積り依頼","ヒアリング","アフターフォロー","キックオフ","レビュー"};
        Integer indval = 2001;

        System.out.println("◆◆◆項目中パラメータ設定");
        
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];

        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.memoTag2.add(sbi_list[i]);
        }
        */
    }
    private void initial_memokind3()
    {
        System.out.println("◆◆SerchMemoItems:initial_memokind3() 項目初期化");
        /* @NOTEST */
        initial_memotag(mmtf.MEMOTAGID_3, this.memoTag3);
        /*
        try{
            // コードマスタから情報を取得
            List<MMemotag> mmtag;
            Query q = this.em.createNamedQuery("MMemotag.findByTagId", MMemotag.class )
                                .setParameter("tagId", MEMOTAGID_3);
            mmtag = q.getResultList();
            
            selectboxItem sbi_list[];
            sbi_list = new selectboxItem[mmtag.size()];

            for( Integer i=0; i<mmtag.size(); i++){
                sbi_list[i] = new selectboxItem();
                sbi_list[i].setLabel(((MMemotag)mmtag.get(i)).getContents1());
                sbi_list[i].setValue(((MMemotag)mmtag.get(i)).getMMemotagPK().getCode());

                this.memoTag3.add(sbi_list[i]);
            }
        }catch(IllegalArgumentException iaEx){
            System.err.printf("　◆ 取得失敗【"+iaEx.toString()+"】");
        }catch(NoResultException  nrEx){
            System.err.printf("　◆メモタグ3「"+MEMOTAGID_3+"」に該当するデータなし【"+nrEx.toString()+"】");
        }
        */
        /* @TEST
        String[] indLabel = new String[]{"緊急","クレーム","日報","予定","中止","延期","期限あり"};
        Integer indval = 1001;

        System.out.println("◆◆◆項目小パラメータ設定");
        
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];

        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.memoTag3.add(sbi_list[i]);
        }
        */
    }
    /*現在未使用*/
    private void initial_memostatus()
    {
        System.out.println("◆◆SerchMemoItems:initial_memostatus() 項目初期化");
        String[] indLabel = new String[]{"予定","確定","実施中","中断","実施済","取消","未実施","延期","その他"};
        Integer indval = 1001;

        System.out.println("◆◆◆メモステータスパラメータ設定");
        
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];

        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.memoStatus.add(sbi_list[i]);
        }
    }
}
