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
//import javax.ejb.Stateless;
//import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
//import javax.faces.view.ViewScoped;
import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

/**
 *
 * @author k00299
 */
@Named
//@Dependent
//@ViewScoped
@SessionScoped
//@WithLog
public class SerchCompItems  implements Serializable{
    
    private static final long serialVersionUID = 1L;

    ArrayList<selectboxItem> industryCode;       //  業種
    ArrayList<selectboxItem> closedMonth;
    ArrayList<selectboxItem> market;
    ArrayList<selectboxItem> listed;
    ArrayList<selectboxItem> marketCap;         // 総資産
    
    /* ------------------------ */
    /* DBアクセスクラス  */
    /* ------------------------ */
      @EJB
    MCodeFacade mcf;
    /*
    @PersistenceContext(unitName="FIRScrmUnit")
    private EntityManager em;
    */
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    public void setIndustryCode(ArrayList<selectboxItem> industryCode) {
        this.industryCode = industryCode;
    }
    
    public void setClosedMonth(ArrayList<selectboxItem> closedMonth) {
        this.closedMonth = closedMonth;
    }
    
    public void setMarket(ArrayList<selectboxItem> market) {
        this.market = market;
    }
    
    public void setListed(ArrayList<selectboxItem> listed) {
        this.listed = listed;
    }
    
    public ArrayList<selectboxItem> getIndustryCode() {
        return industryCode;
    }
    
    public ArrayList<selectboxItem> getClosedMonth() {
        return closedMonth;
    }
    
    public ArrayList<selectboxItem> getMarket() {
        return market;
    }
    
    public ArrayList<selectboxItem> getListed() {
        return listed;
    }
    
    public ArrayList<selectboxItem> getMarketCap() {
        return marketCap;
    }
    
    public void setMarketCap(ArrayList<selectboxItem> marketCap) {
        this.marketCap = marketCap;
    }
//</editor-fold>
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public SerchCompItems() {
        System.out.println("◆SerchCompItems:SerchCompItems　初期化");
//        initial_selitems();
   }
    

    
    public void initial_selitems()
    {
        System.out.println("◆SerchCompItems:initial_selitems()"+this);

        this.industryCode   = new ArrayList<>();
        this.closedMonth   = new ArrayList<>();
        this.market           = new ArrayList<>();
        this.listed              = new ArrayList<>();
        this.marketCap     = new ArrayList<>();
        
        System.out.println("◆◆企業検索条件パラメータ取得　試験用にデータセット処理");
        
        initial_industryCode();
        initial_settlementMonth();
        initial_market();
        initial_listed();
        initial_marketCap();
    }
    
    private List<MCode> initial_listItems( String mcode, ArrayList<selectboxItem> mlist){
        System.out.println("◆◆◆リストボックスパラメータ設定");
        
        /* @NOTEST */
        List<MCode> mcodes = mcf.getList(mcode);
        //  データ取得有無を確認
        if( mcodes==null){
            System.out.println("◆◆◆失敗『"+mcf.toString()+"』");
            //データなし。
            return null;
        }
        return mcodes;
        /*
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[mcodes.size()];

        for( Integer i=0; i<mcodes.size(); i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(((MCode)mcodes.get(i)).getContents1());
            sbi_list[i].setValue(((MCode)mcodes.get(i)).getMCodePK().getCode());

            mlist.add(sbi_list[i]);
        }
        return true;
        */
    }
    
    private void initial_industryCode()
    {
        System.out.println("◆◆◆業種パラメータ設定");
 
        
        /* @NOTEST */
        List<MCode> mcodes =  initial_listItems( MCodeFacade.GROUPID_BIZTSE33CD, this.industryCode );
        if( null==mcodes ){
           return;
        }
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[mcodes.size()];

        for( Integer i=0; i<mcodes.size(); i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(((MCode)mcodes.get(i)).getContents1());
            sbi_list[i].setValue(((MCode)mcodes.get(i)).getMCodePK().getCode());

            this.industryCode.add(sbi_list[i]);
        }
        /*
        /* @TEST 
        String[] indLabel = new String[]{" 水産・農林業","鉱業","建設業","食料品","繊維製品","パルプ・紙","化学","医薬品","石油・石炭製品","ゴム製品","ガラス・土石製品","鉄鋼","非鉄金属","金属製品","機械","電気機器","輸送用機器","精密機器","その他製品","電気・ガス業","陸運業","海運業","空運業","倉庫・輸送関連業","情報・通信業","卸売業","小売業","銀行業","証券、商品先物取引","保険業","その他金融業","不動産業","サービス業"};
        Integer indval = 1001;

        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];
       
        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.industryCode.add(sbi_list[i]);
        }
        */
    }      
            
    private void initial_settlementMonth()
    {
        System.out.println("◆◆◆決算月パラメータ設定");

        /* @NOTEST */
       List<MCode> mcodes = initial_listItems( MCodeFacade.GROUPID_SETTLEMM, this.closedMonth );
        if( null==mcodes ){
           return;
        }
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[mcodes.size()];

        for( Integer i=0; i<mcodes.size(); i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(((MCode)mcodes.get(i)).getContents1());
            sbi_list[i].setValue(((MCode)mcodes.get(i)).getMCodePK().getCode());

            this.closedMonth.add(sbi_list[i]);
        }
        /* @TEST
        String[] indLabel = new String[]{"１月","２月","３月","４月","５月","６月","７月","８月","９月","１０月","１１月","１２月"};
        Integer indval = 2001;

        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];
       
        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.closedMonth.add(sbi_list[i]);
        }
        */
    }    
    
    private void initial_market()
    {
        System.out.println("◆◆◆上場市場パラメータ設定");

        /* @NOTEST */
        List<MCode> mcodes = initial_listItems( MCodeFacade.GROUPID_MARKET, this.market );
        if( null==mcodes ){
           return;
        }
        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[mcodes.size()];

        for( Integer i=0; i<mcodes.size(); i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(((MCode)mcodes.get(i)).getContents2());
            sbi_list[i].setValue(((MCode)mcodes.get(i)).getContents2());

            this.market.add(sbi_list[i]);
        }
        /*
        try{
            // コードマスタから情報を取得
            List<MCode> mcodes;
            Query q = this.em.createNamedQuery("MCode.findByGroupId", MCode.class )
                                .setParameter("groupId", GROUPID_MARKET);
            mcodes = q.getResultList();
            
            selectboxItem sbi_list[];
            sbi_list = new selectboxItem[mcodes.size()];

            for( Integer i=0; i<mcodes.size(); i++){
                sbi_list[i] = new selectboxItem();
                sbi_list[i].setLabel(((MCode)mcodes.get(i)).getContents2());
                sbi_list[i].setValue(((MCode)mcodes.get(i)).getContents2());

                this.market.add(sbi_list[i]);
            }
        }catch(IllegalArgumentException iaEx){
            System.err.printf("　◆ 取得失敗【"+iaEx.toString()+"】");
        }catch(NoResultException  nrEx){
            System.err.printf("　◆グループＩＤ「006」に該当するデータなし【"+nrEx.toString()+"】");
        }
        */
        /* @TEST
        String[] indLabel = new String[]{"東証１部","東証２部","マザーズ","JQグロース","JQスタンダード"};
        Integer indval = 3001;

        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];
       
        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval++));
            
            this.market.add(sbi_list[i]);
        }
        */
    }      

    public static final Integer LISTED_NON = 1;
    public static final Integer LISTED_OK = 2;
    public static final Integer LISTED_NG = 3;
    
    private void initial_listed()
    {
        String[] indLabel = new String[]{"不問","上場","未上場"};
        Integer[] indval = new Integer[]{LISTED_NON,LISTED_OK,LISTED_NG};

        System.out.println("◆◆◆上場状態パラメータ設定");

        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];
       
        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval[i]));
            
            this.listed.add(sbi_list[i]);
        }
    }      
    private void initial_marketCap()
    {
        String[] indLabel = new String[]{"10","50","100", "500", "1,000", "2,000", "5,000", "10,000"};
        Integer[] indval = new Integer[]{10,50,100, 500, 1000, 2000, 5000, 10000};

        System.out.println("◆◆◆時価総額パラメータ設定");

        selectboxItem sbi_list[];
        sbi_list = new selectboxItem[indLabel.length];
       
        for( Integer i=0; i<indLabel.length; i++){
            sbi_list[i] = new selectboxItem();
            sbi_list[i].setLabel(indLabel[i]);
            sbi_list[i].setValue(Integer.toString(indval[i]));
            
            this.marketCap.add(sbi_list[i]);
        }
    }      
     
}
