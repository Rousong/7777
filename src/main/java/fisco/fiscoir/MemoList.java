/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.sql.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
//import org.apache.commons.lang.time.DateFormatUtils;

/**
 *
 * @author k00299
 */
@Named
@ViewScoped
//@SessionScoped
//@WithLog
public class MemoList implements Serializable{

    private static final long serialVersionUID = 1L;

    //  t_memo　にあるデータ
    private String brandCd;                     //  企業コード　※企業検索のみ
    private String dateFrom;                //  訪問日From
    private String dateTo;                      // 訪問日To
    private String writeUserId1;            //  記入者１    ※
    private String writeUserId2;            //  記入者２
    private String memoTag1;                //  タグ１
    private String memoTag2;                //  タグ１
    private String memoTag3;                //  タグ１
    private String title;                           //  タイトル　※メモ検索のみ
    
    //  m_brand_fund　に存在するデータ
    private String stockCd;                     //  証券コード
    private String marketNm;                //上場市場名　※メモ検索のみ
    
    
    private String errMsg;
    
     private SerchMemoItems selMemoItems; 
    
    private ArrayList<Memo>  memos;
    private List<TMemo>  tmemos;
    
    @EJB
    TMemoDb tmdb;
    
    /**
     * DBメモ情報テーブルアクセス
     */
    @EJB
    TMemoFacade tmf;
    
    /**
     * DB 基本きぎょウ情報テーブルアクセス
     */
    @EJB
    private MBrandFundFacade mBrandFundFacade;

//<editor-fold defaultstate="collapsed" desc="setter/getter">
    public ArrayList<Memo> getMemos() {
        return memos;
    }
    
    public void setMemos(ArrayList<Memo> memoList) {
        this.memos = memoList;
    }
    
    public String getBrandCd() {
        return brandCd;
    }
    
    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }
    
    public String getStockCd() {
        return stockCd;
    }
    
    public void setStockCd(String stockCd) {
        this.stockCd = stockCd;
    }
    
    public String getDateFrom() {
        return dateFrom;
    }
    
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    
    public String getDateTo() {
        return dateTo;
    }
    
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    
    public String getWriteUserId1() {
        return writeUserId1;
    }
    
    public void setWriteUserId1(String writeUserId1) {
        this.writeUserId1 = writeUserId1;
    }
    
    public String getWriteUserId2() {
        return writeUserId2;
    }
    
    public void setWriteUserId2(String writeUserId2) {
        this.writeUserId2 = writeUserId2;
    }

    public String getMemoTag1() {
        return memoTag1;
    }

    public void setMemoTag1(String memoTag1) {
        this.memoTag1 = memoTag1;
    }

    public String getMemoTag2() {
        return memoTag2;
    }

    public void setMemoTag2(String memoTag2) {
        this.memoTag2 = memoTag2;
    }

    public String getMemoTag3() {
        return memoTag3;
    }

    public void setMemoTag3(String memoTag3) {
        this.memoTag3 = memoTag3;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setSelMemoItems(SerchMemoItems selMemoItems) {
        this.selMemoItems = selMemoItems;
    }

    public String getMarketNm() {
        return marketNm;
    }

    public void setMarketNm(String marketNm) {
        this.marketNm = marketNm;
    }

//</editor-fold>

    public MemoList() {
        System.out.println("◆MemoList:MemoList()　メモリスト　コンストラクタ");
    }

    public boolean IsMemoList()
    {
        System.out.println("◆MemoList:IsMemoList()　List 有無の確認");
        System.out.println("◆◆セッション情報3【"+memos+"】");
 
        boolean ret = true;
        if(memos==null)
        {
            System.out.println("◆◆リスト【無し】");
            ret = false;
        }else{
            System.out.println("◆◆リスト【有り】");
        }
        return ret;
    }
    
    public Memo getMemo(String memoid)
    {
        if(false==IsMemoList()){
            return null;
        }
        
        Memo memo = null;
        for( int i=0; i<memos.size(); i++)
        {
            memo = memos.get(i);
            if( memoid.equals(memo.getMemoId()) == true){
                break;
            }else{
                memo = null;
            }
        }
        return memo;
    }
    
    public String connectString(String queryw)
    {
        if( null == queryw)
            return " where";
        else
            return queryw+" and";
    }
    
//    private static final String serchTime = "09:00:00";
    private static final String SSERCHTIMEVALFR = "00:00:00";
    private static final String SSERCHTIMEVALTO = "23:59:59";
    /**
     * メモ検索（企業検索から）処理
     * @return 
     */
    public boolean SerchMemoList()
    {
        boolean ret = true;
        
        System.out.println("◆MemoList:SerchMemoList()　メモの検索開始");

        /* @NOTEST */
        //String query = TMemoDb.NATIVE_QUERY_BASE;
        String query = TMemoFacade.NATIVE_QUERY_BASE;
        String queryW=null;

        // 細かい検索条件をついか
        // 企業コード
        if( this.getBrandCd()!=null && this.getBrandCd().length()>0){
            queryW = connectString(queryW);
            queryW += " tm.BRAND_CD =  '"+this.getBrandCd()+"'";
        }
        //  証券コード <-企業検索では入ってこない想定
        //  証券コード
        if( this.getStockCd()!=null && this.getStockCd().length()>0){
            queryW = connectString(queryW);
            queryW += " tm.BRAND_CD =  "
                          + "( select bf.brand_cd from m_brand_fund bf "
                          + "where bf.stock_code = '"+this.getStockCd()+"')";
        }

        // 日付
        Date sqlDaFrom,sqlDaTo;
        String dFrom = this.getDateFrom();
        String dTo = this.getDateTo();
        if( dFrom!=null && dFrom.length()>0){
            // 入力の日付文字列を　yyyy-mm-dd の形に変換
            dFrom = dFrom.replace('/', '-');
            sqlDaFrom = Date.valueOf(dFrom);
            if( dTo!=null && dTo.length()>0 ){
                dTo = dTo.replace('/', '-');
                sqlDaTo = Date.valueOf(dTo);
                //　From　To 　で検索
                if( sqlDaFrom.after(sqlDaTo) ){
                    // From が to の後ろならNG
                    this.errMsg = "検索条件「日付:From」は「日付:To」より以前の日付を指定してください。";
                    System.out.println("検索条件日付："+this.errMsg);
                    return false;
                }else if( sqlDaFrom.equals(sqlDaTo)){
                    //  From　To が一致
                    System.out.println("検索条件日付：From To が同じ");
                    // SQL作成
                    queryW = connectString(queryW);
                    queryW += " (tm.DATE_FROM = '"+dFrom+" "+SSERCHTIMEVALFR+"' or tm.DATE_TO = '"+dTo+" "+SSERCHTIMEVALTO+"')";
                }else{
                    // SQL作成
                    queryW = connectString(queryW);
                    queryW += " (tm.DATE_FROM between '"+dFrom+" "+SSERCHTIMEVALFR+"' AND '"+dTo+" "+SSERCHTIMEVALTO+"' or tm.DATE_TO between '"+dFrom+" "+SSERCHTIMEVALFR+"' AND '"+dTo+" "+SSERCHTIMEVALTO+"')";

                }
            }else{
                // Fromだけで検索
                queryW = connectString(queryW);
                //queryW += " (tm.DATE_FROM between '"+dFrom+" "+SSERCHTIMEVALFR+"' AND '"+dTo+" "+SSERCHTIMEVALTO+"' )";
                queryW += " tm.DATE_FROM >= '"+dFrom+" "+SSERCHTIMEVALFR+"'";
            }
        }else if(dTo!=null && dTo.length()>0){
            dTo = dTo.replace('/', '-');
            sqlDaTo = Date.valueOf(dTo);
            // To だけ検索
            queryW = connectString(queryW);
            //queryW += " tm.DATE_FROM <= '"+dTo+" 09:00:00'";
            queryW += " tm.DATE_FROM <= '"+dTo+" "+SSERCHTIMEVALTO+"'";
        }
        //  記入者
         if( this.getWriteUserId1()!=null && this.writeUserId1.length()>0){
             if( this.getWriteUserId2()!=null && this.writeUserId2.length()>0){
                 // ダブルで検索
                queryW = connectString(queryW);
                // 更新ユーザが、１か２に引っかかれば
                queryW += " (tm.RENEWAL_USER like '%"+this.getWriteUserId1()+"%' or tm_RENEWAL_USER like '%"+this.getWriteUserId2()+"%')";
             }else{
                 // １だけで検索
                queryW = connectString(queryW);
                queryW += " tm.RENEWAL_USER like '%"+this.getWriteUserId1()+"%'";
             }
        }else if( this.getWriteUserId2()!=null && this.writeUserId2.length()>0){
            // １だけで検索
            queryW = connectString(queryW);
            queryW += " tm.RENEWAL_USER like '%"+this.getWriteUserId2()+"%'";
        }else{
            //  条件なし
        }
         // タグ1
        if( this.getMemoTag1()!=null && this.getMemoTag1().length()>0){
            queryW = connectString(queryW);
            queryW += " tm.Tag1 = '"+this.getMemoTag1()+"'";
         }
         // タグ2
        if( this.getMemoTag2()!=null && this.getMemoTag2().length()>0){
            //
            queryW = connectString(queryW);
            queryW += " tm.Tag2 = '"+this.getMemoTag2()+"'";
         }
         // タグ3
        if( this.getMemoTag3()!=null && this.getMemoTag3().length()>0){
            //
            queryW = connectString(queryW);
            queryW += " tm.Tag3 = '"+this.getMemoTag3()+"'";
         }
         // タイトル
        if( this.getTitle()!=null && this.getTitle().length()>0){
            // １だけで検索
            queryW = connectString(queryW);
            queryW += " tm.TITLE like '%"+this.getTitle()+"%'";
        }
        System.out.println("SQLオプション『"+queryW+"』");
        if( queryW != null ){
            query += queryW;
        }
        System.out.println("SQLクエリ完成『"+query+"』");
        //  検索クエリを実行
        //tmemos = tmdb.finds(query);
        tmemos = tmf.finds(query);
        //
        ArrayList<Memo> objList = new ArrayList<Memo>();

        System.out.println("◆◆セッション情報4【"+memos+"】");
        
       for( int i=0; i<tmemos.size(); i++){
           Memo obj = new Memo();
           TMemo tm = (TMemo)tmemos.get(i);
            //　メモID
            obj.setMemoId(tm.getMemoId());
            //   記入者　新規追加か更新か迷い中。
            obj.setUserId(tm.getRegisterUser());
//           obj.setUserId(((TMemo)tmemos.get(i)).getRenewaluser());
            //  企業コード
            obj.setBrandCd(tm.getBrandCd());
            //  証券コード
            obj.setStockCode(this.getStockCd()); // 証券コードは　メモデータにないので
            //  企業名
            obj.setBrandNm("");
            //  訪問先　部署名
            obj.setPostNm(tm.getPostNm());
            //  訪問先　担当者名
            obj.setPersonNm(tm.getPersonNm());
           //   訪問日付　yyyy/MM/dd 形式に表示側で変換する
           obj.setStartDate(((TMemo)tmemos.get(i)).getDateFrom());
           obj.setEndDate(((TMemo)tmemos.get(i)).getDateTo());
           //
           obj.setStartTime((((TMemo)tmemos.get(i)).getTimeFrom()));
           obj.setEndTime((((TMemo)tmemos.get(i)).getTimeTo()));
           //   タグの値をラベル値に変換して渡す
           obj.setTag1( getTagView(((TMemo)tmemos.get(i)).getTag1(), selMemoItems.getMemoTag1()));
           obj.setTag2( getTagView(((TMemo)tmemos.get(i)).getTag2(), selMemoItems.getMemoTag2()));
           obj.setTag3( getTagView(((TMemo)tmemos.get(i)).getTag3(), selMemoItems.getMemoTag3()));
           //   値も
           obj.setTag1_val(((TMemo)tmemos.get(i)).getTag1());
           obj.setTag2_val(((TMemo)tmemos.get(i)).getTag2());
           obj.setTag3_val(((TMemo)tmemos.get(i)).getTag3());
           //obj.setStatus("");
           //   タイトル
           obj.setTitle(((TMemo)tmemos.get(i)).getTitle());
           //   メモ
           obj.setMemo(((TMemo)tmemos.get(i)).getMemo());
           //   見出し用のメモ　２０文字制限
           obj.setMemoST();
           //   登録ユーザ
           obj.setRegisterUser(((TMemo)tmemos.get(i)).getRegisterUser());
           //   更新ユーザ
           obj.setRenewalUser(((TMemo)tmemos.get(i)).getRenewaluser());
           //   登録日付
           obj.setRegisterDateTime(((TMemo)tmemos.get(i)).getRegisterDatetime());
           //   更新日付
           obj.setRenewalDateTime(((TMemo)tmemos.get(i)).getRenewalDatetime());
           //
           objList.add(obj);
       }
       setMemos(objList);
        
        //  ダミー
       /* @TEST
        DummyFunc();
        */
        
        return ret;
    }

    public boolean SerchMemoListM()
    {
        boolean ret = true;
        
        System.out.println("◆MemoList:SerchMemoListM()　メモの検索開始");

        /* @NOTEST */
        //String query = TMemoDb.NATIVE_QUERY_BASE;
        String query = TMemoFacade.NATIVE_QUERY_BASE;
        String queryW=null;

        // 細かい検索条件をついか
        // 企業コード  <-メモ検索では入ってこない想定
        if( this.getBrandCd()!=null && this.getBrandCd().length()>0){
            queryW = connectString(queryW);
            queryW += " tm.BRAND_CD =  '"+this.getBrandCd()+"'";
        }
        //  証券コード
        if( this.getStockCd()!=null && this.getStockCd().length()>0){
            queryW = connectString(queryW);
            queryW += " tm.BRAND_CD =  "
                          + "( select bf.brand_cd from m_brand_fund bf "
                          + "where bf.stock_code = '"+this.getStockCd()+"')";
        }
        // 日付
        Date sqlDaFrom,sqlDaTo;
        String dFrom = this.getDateFrom();
        String dTo = this.getDateTo();
        if( dFrom!=null && dFrom.length()>0){
            // 入力の日付文字列を　yyyy-mm-dd の形に変換
            dFrom = dFrom.replace('/', '-');
            sqlDaFrom = Date.valueOf(dFrom);
            if( dTo!=null && dTo.length()>0 ){
                // 入力の日付文字列を　yyyy-mm-dd の形に変換
                dTo = dTo.replace('/', '-');
                sqlDaTo = Date.valueOf(dTo);
                //　From　To 　で検索
                System.out.println("検索条件：日付From　to　で検索!!");
                if( sqlDaFrom.after(sqlDaTo) ){
                    // From が to の後ろならNG
                    this.errMsg = "検索条件「日付:From」は「日付:To」より以前の日付を指定してください。";
                    System.out.println("検索条件日付："+this.errMsg);
                    return false;
                }else if( sqlDaFrom.equals(sqlDaTo)){
                    //  From　To が一致
                    System.out.println("検索条件日付：From To が同じ");
                    // SQL作成
                    queryW = connectString(queryW);
//                    queryW += " (tm.DATE_FROM = '"+dFrom+" 09:00:00' or tm.DATE_TO = '"+dTo+" 09:00:00')";
                    queryW += " (tm.DATE_FROM = '"+dFrom+" "+SSERCHTIMEVALFR+"' or tm.DATE_TO = '"+dTo+" "+SSERCHTIMEVALTO+"')";
                }else{
                    // SQL作成
                    queryW = connectString(queryW);
//                    queryW += " (tm.DATE_FROM between '"+dFrom+" 09:00:00' AND '"+dTo+" 09:00:00' or tm.DATE_TO between '"+dFrom+" 09:00:00' AND '"+dTo+" 09:00:00')";
                    queryW += " (tm.DATE_FROM between '"+dFrom+" "+SSERCHTIMEVALFR+"' AND '"+dTo+" "+SSERCHTIMEVALTO+"' or tm.DATE_TO between '"+dFrom+" "+SSERCHTIMEVALFR+"' AND '"+dTo+" "+SSERCHTIMEVALTO+"')";
                }
            }else{
                System.out.println("検索条件：日付From　で検索!!");
                // Fromだけで検索
                queryW = connectString(queryW);
//                queryW += " tm.DATE_FROM >= '"+dFrom+" 09:00:00'";
                queryW += " tm.DATE_FROM >= '"+dFrom+" "+SSERCHTIMEVALFR+"'";
            }
        }else if(dTo!=null && dTo.length()>0){
            dTo = dTo.replace('/', '-');
            sqlDaTo = Date.valueOf(dTo);
                System.out.println("検索条件：日付to　で検索!!");
            // To だけ検索
            queryW = connectString(queryW);
            //queryW += " tm.DATE_FROM <= '"+dTo+" 09:00:00'";
            queryW += " tm.DATE_FROM <= '"+dTo+" "+SSERCHTIMEVALTO+"'";
        }
        //  記入者
         if( this.getWriteUserId1()!=null && this.writeUserId1.length()>0){
             if( this.getWriteUserId2()!=null && this.writeUserId2.length()>0){
                 // ダブルで検索
                queryW = connectString(queryW);
                // 更新ユーザが、１か２に引っかかれば
                queryW += " (tm.RENEWAL_USER like '%"+this.getWriteUserId1()+"%' or tm_RENEWAL_USER like '%"+this.getWriteUserId2()+"%')";
             }else{
                 // １だけで検索
                queryW = connectString(queryW);
                queryW += " tm.RENEWAL_USER like '%"+this.getWriteUserId1()+"%'";
             }
        }else if( this.getWriteUserId2()!=null && this.writeUserId2.length()>0){
            // １だけで検索
            queryW = connectString(queryW);
            queryW += " tm.RENEWAL_USER like '%"+this.getWriteUserId2()+"%'";
        }else{
            //  条件なし
        }
         // タグ1
        if( this.getMemoTag1()!=null && this.getMemoTag1().length()>0){
            queryW = connectString(queryW);
            queryW += " tm.Tag1 = '"+this.getMemoTag1()+"'";
         }
         // タグ2
        if( this.getMemoTag2()!=null && this.getMemoTag2().length()>0){
            //
            queryW = connectString(queryW);
            queryW += " tm.Tag2 = '"+this.getMemoTag2()+"'";
         }
         // タグ3
        if( this.getMemoTag3()!=null && this.getMemoTag3().length()>0){
            //
            queryW = connectString(queryW);
            queryW += " tm.Tag3 = '"+this.getMemoTag3()+"'";
         }
         // タイトル
        if( this.getTitle()!=null && this.getTitle().length()>0){
            // １だけで検索
            queryW = connectString(queryW);
            queryW += " tm.TITLE like '%"+this.getTitle()+"%'";
        }
        //  上場市場
        if( this.getMarketNm()!=null && this.getMarketNm().length()>0 ){
            queryW = connectString(queryW);
            queryW += " tm.brand_cd in "
                          + "( select bf.brand_cd from m_brand_fund bf "
                          + "where bf.market_nm = '"+this.getMarketNm()+"')";
        }
        System.out.println("SQLオプション『"+queryW+"』");
        if( queryW != null ){
            query += queryW;
        }
        System.out.println("SQLクエリ完成『"+query+"』");
        //  検索クエリを実行
        //tmemos = tmdb.finds(query);
        tmemos = tmf.finds(query);
        //
        ArrayList<Memo> objList = new ArrayList<Memo>();

        System.out.println("◆◆セッション情報4【"+memos+"】");


        
       for( int i=0; i<tmemos.size(); i++){
           Memo obj = new Memo();
           TMemo tm = (TMemo)tmemos.get(i);
            //　メモID
            obj.setMemoId(tm.getMemoId());
            //   記入者　新規追加か更新か迷い中。
            obj.setUserId(tm.getRegisterUser());
//           obj.setUserId(((TMemo)tmemos.get(i)).getRenewaluser());
            //  証券コード
            obj.setStockCode(this.getStockCd()); //企業コードは　必須条件なので検索結果ではなく保持データから持ってくる
            //  企業名
            obj.setBrandNm("");
            //企業名と証券コードを取得する
            MBrandFund mbf = mBrandFundFacade.find(tm.getBrandCd());
            if( mbf==null){
            }else{
                obj.setBrandNm(mbf.getBrandNm());
            }
            //  企業コード
            obj.setBrandCd(tm.getBrandCd());
            //  訪問先　部署名
            obj.setPostNm(tm.getPostNm());
            //  訪問先　担当者名
            obj.setPersonNm(tm.getPersonNm());
           //   訪問日付　yyyy/MM/dd 形式に表示側で変換する
           obj.setStartDate(((TMemo)tmemos.get(i)).getDateFrom());
           obj.setEndDate(((TMemo)tmemos.get(i)).getDateTo());
           //
           obj.setStartTime((((TMemo)tmemos.get(i)).getTimeFrom()));
           obj.setEndTime((((TMemo)tmemos.get(i)).getTimeTo()));
           //   タグの値をラベル値に変換して渡す
           obj.setTag1( getTagView(((TMemo)tmemos.get(i)).getTag1(), selMemoItems.getMemoTag1()));
           obj.setTag2( getTagView(((TMemo)tmemos.get(i)).getTag2(), selMemoItems.getMemoTag2()));
           obj.setTag3( getTagView(((TMemo)tmemos.get(i)).getTag3(), selMemoItems.getMemoTag3()));
           //   値も
           obj.setTag1_val(((TMemo)tmemos.get(i)).getTag1());
           obj.setTag2_val(((TMemo)tmemos.get(i)).getTag2());
           obj.setTag3_val(((TMemo)tmemos.get(i)).getTag3());
           //obj.setStatus("");
           //   タイトル
           obj.setTitle(((TMemo)tmemos.get(i)).getTitle());
           //   メモ
           obj.setMemo(((TMemo)tmemos.get(i)).getMemo());
           //   見出し用のメモ　２０文字制限
           obj.setMemoST();
           //   登録ユーザ
           obj.setRegisterUser(((TMemo)tmemos.get(i)).getRegisterUser());
           //   更新ユーザ
           obj.setRenewalUser(((TMemo)tmemos.get(i)).getRenewaluser());
           //   登録日付
           obj.setRegisterDateTime(((TMemo)tmemos.get(i)).getRegisterDatetime());
           //   更新日付
           obj.setRenewalDateTime(((TMemo)tmemos.get(i)).getRenewalDatetime());
           //
           objList.add(obj);
       }
       setMemos(objList);
        
        //  ダミー
       /* @TEST
        DummyFunc();
        */
        
        return ret;
    }
    
    private String getTagView(String tagId, ArrayList<selectboxItem> tagItemList){
        String retLavel = "";
        
        if( tagId==null || tagId.length() <= 0 ){
            return "";
        }
        for( int i=0; i<tagItemList.size(); i++ ){
            if( tagId.equals(tagItemList.get(i).getValue())){
                retLavel = tagItemList.get(i).getLabel();
                break;
            }
        }
//        System.out.println("Tag1:Value["+tagId+"] Label["+retLavel+"]");
        return retLavel;
    }
    
    private void DummyFunc()
    {

        System.out.println("◆MemoList:DummyFunc()　試験用のダミー関数");
        //   ユーザID
       String[] dmUserID = new String[]{"1001","1002","1003","1004","1005"};
       //   会社コード
       String[] dmCompanyCode = new String[]{"2301","2302","2308","2309","2401"};
       // 　会社名
       String[] dmCompanyName = new String[] {"（株）SJI","（株）CAICA", "（株）日土　建設", "（株）金剛組","（株）試験組"};
       String[] dmPostName = new String[]{"総務部","営業推進部","CR部","CSS部","カスタマーサービス"};
       String[] dmPersonName = new String[] {"鈴木　一郎","山本　三郎","岸辺　城","五郎　丸走","八束　九"};
       //   開始日
       Date[] dmsDate = new Date[5];
       long days = ((100*60)*60)*60;
       long yars = days*365;
       dmsDate[0] = new Date((yars*10)+(days*90));
       dmsDate[1] = new Date((yars*10)+(days*91));
       dmsDate[2] = new Date((yars*10)+(days*92));
       dmsDate[3] = new Date((yars*10)+(days*93));
       dmsDate[4] = new Date((yars*10)+(days*94));
       
       //   終了日
       Date[] dmeDate = new Date[5];
       dmeDate[0] = new Date((yars*10)+(days*90));
       dmeDate[1] = new Date((yars*10)+(days*91));
       dmeDate[2] = new Date((yars*10)+(days*92));
       dmeDate[3] = new Date((yars*10)+(days*93));
       dmeDate[4] = new Date((yars*10)+(days*94));

       //   種別
        String[] dmKind1 = new String[]{"打合せ","営業","営業","会議","工程管理",};
        String[] dmKind2 = new String[]{"朝会","朝会","朝会","仕様検討","期限",};
        String[] dmKind3 = new String[]{"実績報告","商談","商談","ブレスト","実績報告",};
        //
        String[] dmStatus = new String[]{"完了","審議中鵜","未着手","着手","中止",};
       //   上場市場
       String[] dmTitle = new String[]{"■A社　打合せ　実績報告","■C社　打合せ　実績報告","■D社　打合せ　実績報告","■B社　打合せ　実績報告","■A社　打合せ　実績報告",};
       
        String[] dmMemo = new String[] { "本日は晴天なり。本日は晴天なり。",
       "試験試験。試験試験。試験試験。試験試験。\n試験試験。試験試験。",
       "ユーザ確認中　ユーザ確認中　ユーザ確認中　ユーザ確認中",
       "テストテスト。\nテストテスト。\nテストテスト。\nテストテスト。\n",
       "メモ機能試験"};
        
        String[] dmUpuser = new String[]{"鈴木　一郎","山田　次郎","佐藤　三郎","山本　四朗","田中　五郎"};
       

        //
        //tbCompanyInfo[] compList = new tbCompanyInfo[dmCompanyName.length];
        ArrayList<Memo> objList = new ArrayList<Memo>();

        System.out.println("◆◆セッション情報4【"+memos+"】");
        
       for( int i=0; i<dmUserID.length; i++){
           Memo obj = new Memo();
           
           obj.setMemoId(  String.valueOf(70001+i) );
           obj.setUserId(dmUserID[i]);
           obj.setStockCode(dmCompanyCode[i]);
           obj.setBrandNm(dmCompanyName[i]);
           obj.setPostNm(dmPostName[i]);
           obj.setPersonNm(dmPersonName[i]);
           obj.setStartDate(dmsDate[i]);
           obj.setEndDate(dmeDate[i]);
           obj.setTag1(dmKind1[i]);
           obj.setTag2(dmKind2[i]);
           obj.setTag3(dmKind3[i]);
           obj.setStatus(dmStatus[i]);
           obj.setTitle(dmTitle[i]);
           obj.setMemo(dmMemo[i]);
           obj.setMemoST();
           
           obj.setRegisterUser(dmUpuser[i]);
           obj.setRenewalUser(dmUpuser[i]);
           
           obj.setRegisterDateTime(new Date(System.currentTimeMillis()));
           obj.setRenewalDateTime(new Date(System.currentTimeMillis()));
           //
           objList.add(obj);
            /*
           compList[i] = new tbCompanyInfo();
           compList[i].setCompany_name(dmCompanyName[i]);
           compList[i].setTicker_code(dmStokCode[i]);
           compList[i].setIdsCode(dmIndustryCode[i]);
           compList[i].setClosemonth(dmCloseMonth[i]);
           compList[i].setMarketkind(dmUpMarket[i]);
            objList.add(obj);
           */
       }
       //setCompList(compList);
       setMemos(objList);
    }

    public int size(){
        int size = 0;
        
        if( IsMemoList() ){
            size = memos.size();
        }
        return size;
    }
    
}
