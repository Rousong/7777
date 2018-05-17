package fisco.fiscoir;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *企業情報の検索結果保持クラス
 * @author k00299
 */
@Named
//@ViewScoped
@SessionScoped
//@WithLog
public class CompanyInfoList implements Serializable{

    private static final long serialVersionUID = 1L;

    //private tbCompanyInfo[] compList;
    private ArrayList<CompanyInfo>  compList;
    
    /* @NOTEST　*/
    /* ------------------------ */
    /* DBアクセスクラス  */
    /* ------------------------ */
    /**
     * 
     */
    @EJB
    private MCodeFacade mcf;
    /**
     * 銘柄情報_基本　
     */
    @EJB
    private MBrandFundFacade mBrandFundFacade;
    /**
     * 銘柄情報_株価現値
     */
    @EJB
    private TBrandPriceCurrentFacade tBrandPriceCurrentFacade;
    /**
     * 企業情報ビュー
     */
    @EJB
    private VBrandInfoFacade vBrandInfoFacade;
    //  メモ情報
    @EJB
    private TMemoFacade tMemoFacade;

//<editor-fold defaultstate="collapsed" desc="プロパティ">
    /* 業種コード変換用 */
    private List<MCode> lsmcode_bct33;
    
    private String brandNm;
    private String stockCd;
    private String bizCdTse33;
    private String settleMm;
    private String marketNm;
    private String listed;
    private Long mcapF;
    private Long mcapT;
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    /**
     * 
     * @return 
     */
    public String getBrandNm() {
        return brandNm;
    }
    /**
     * 
     * @param brandNm 
     */
    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }
    /**
     * 
     * @return 
     */
    public String getStockCd() {
        return stockCd;
    }
    /**
     * 
     * @param stockCd 
     */
    public void setStockCd(String stockCd) {
        this.stockCd = stockCd;
    }
    /**
     * 
     * @return 
     */
    public String getBizCdTse33() {
        return bizCdTse33;
    }
    /**
     * 
     * @param bizCdTse33 
     */
    public void setBizCdTse33(String bizCdTse33) {
        this.bizCdTse33 = bizCdTse33;
    }
    /**
     * 
     * @return 
     */
    public String getSettleMm() {
        return settleMm;
    }
    /**
     * 
     * @param settleMm 
     */
    public void setSettleMm(String settleMm) {
        this.settleMm = settleMm;
    }
    /**
     * 
     * @return 
     */
    public String getMarketNm() {
        return marketNm;
    }
    /**
     * 
     * @param marketNm 
     */
    public void setMarketNm(String marketNm) {
        this.marketNm = marketNm;
    }
    /**
     * 
     * @return 
     */
    public String getListed() {
        return listed;
    }
    /**
     * 
     * @param listed 
     */
    public void setListed(String listed) {
        this.listed = listed;
    }
    /**
     * 
     * @return 
     */
    public Long getMcapF() {
        return mcapF;
    }
    /**
     * 
     * @param mcapF 
     */
    public void setMcapF(Long mcapF) {
        this.mcapF = mcapF;
    }
    /**
     * 
     * @return 
     */
    public Long getMcapT() {
        return mcapT;
    }
    /**
     * 
     */
    public void setMcapT(Long mcapT) {
        this.mcapT = mcapT;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<CompanyInfo> getCompList() {
         return compList;
    }

    /**
     * 
     * @param newval 
     */
    public void setCompList(ArrayList<CompanyInfo> newval) {
       System.out.println("◆CompanyInfoList:setCompList  compList のセッター");
        this.compList = newval;
    }

//</editor-fold>
    /**
     * コンストラクタ
     */
    public CompanyInfoList() {
        System.out.println("◆CompanyInfoList:CompanyInfoList()　企業情報リスト　コンストラクタ"+this);
        
        lsmcode_bct33 = null;
    }
    
    /**
     *  企業情報のリストをクリアする
     */
    public void clear()
    {
        System.out.println("◆CompanyInfoList:clear()　企業情報リスト　クリア");
        //  リストを初期化する。
        if( null!=compList ){
            compList.clear();
        }
    }
    /**
     * リストのデータが存在するか
     * @return true:データ有 false:データ無
     */
    public boolean IsCompList()
    {
        System.out.println("◆CompanyInfoList:IsCompList()　List 有無の確認");
        System.out.println("◆◆セッション情報3【"+compList+"】");
 
        boolean ret = true;
        if(compList==null)
        {
            System.out.println("◆◆リスト【無し】");
            ret = false;
        }else{
            System.out.println("◆◆リスト【有り】");
        }
        return ret;
    }
    /**
     * 検索結果件数を返す
     * @return 
     */
    public int size(){
        int size = 0;
        
        if( IsCompList() ){
            size = compList.size();
        }
        return size;
    }
    
    /**
     * 企業情報Listの取得
     * @return 
     */
    public boolean get_VBrandInfo_List()
    {
        boolean ret = true;
        System.out.println("◆CompanyInfoList:SerchCompanyViewList()　企業情報Viewの検索開始");
        //  クエリ作成
        String query = makeQuerySel_VBRANDINFO();
        System.out.println( "次のクエリを実行予定【"+query+"】");
        //
        List<VBrandInfo> vbis;
        vbis = vBrandInfoFacade.finds(query);
        if( vbis == null ){
            System.err.println(vBrandInfoFacade.toErrString());
        }
        //  取得したデータをリストに詰め込む
        ArrayList<CompanyInfo> objList = new ArrayList<CompanyInfo>();
        for( int i=0; i<vbis.size(); i++){
           /* ------------------------------------------------------------*/
           /*              検索結果をデータにセットする                            */
           /* ------------------------------------------------------------*/
           // 挿入用の箱作成
          CompanyInfo obj = new CompanyInfo();
           
           // 表示用の箱に入れ替え
           obj.setMBrandFund(vbis.get(i));

           //   リストに追加
           objList.add(obj);
       }
      setCompList(objList);

        return ret;
    }
    /**
     * 検索用クエリ作成
     * @return 検索クエリ
     */
    private String makeQuerySel_VBRANDINFO()
    {
        // JPQL作成
        String query;       //  全項目か、一覧に出す内容だけにするかは試案が必要。面倒でなければおいおいで一旦。全項目。
        // ベースとなるクエリを取得 select  ~ from MBrandFund まで
        query = CompanyInfo.NATIVE_QUERY_VIEW_BASE;
        String queryW=null;
        
        // 細かい検索条件をついか
        // 企業名
        if( this.getBrandNm()!=null && this.getBrandNm().length()>0){
            queryW = connectString(queryW);
            queryW += " vbi.BRAND_NM LIKE '%"+this.getBrandNm()+"%'";
        }
        // 証券コード
        if(this.getStockCd()!=null && this.getStockCd().length()>0){
            queryW = connectString(queryW);
            queryW += " vbi.STOCK_CODE = '"+this.getStockCd()+"'";
        }
        //  業種
        if(this.getBizCdTse33()!=null && this.getBizCdTse33().length()>0){
            queryW = connectString(queryW);
            queryW += " vbi.BIZ_CD_TSE_33 = '"+this.getBizCdTse33()+"'";
        }
        // 決算月
        if( this.getSettleMm()!=null && this.getSettleMm().length()>0 ){
            queryW = connectString(queryW);
            queryW += " vbi.SETTLEMENT_MM = '"+this.getSettleMm()+"'";
        }
        //  上場市場
        if( this.getMarketNm()!=null && this.getMarketNm().length()>0 ){
            queryW = connectString(queryW);
            queryW += " vbi.MARKET_NM = '"+this.getMarketNm()+"'";
        }
        //  上場状態
        if( this.getListed()!=null && this.getListed().length()>0){
            if(Integer.parseInt(this.getListed())==SerchCompItems.LISTED_NON){
                //　不問であれば特に何もしない。
            }else{
                queryW = connectString(queryW);
                if(Integer.parseInt(this.getListed())==SerchCompItems.LISTED_OK){
                    // 上場のみ
                   queryW += " (vbi.DELISTED_DATE is null || vbi.DELISTED_DATE > CURRENT_DATE() )";
                }else if(Integer.parseInt(this.getListed())==SerchCompItems.LISTED_NG){
                    // 未上場のみ
                   queryW += " (vbi.DELISTED_DATE is not null && vbi.DELISTED_DATE <= CURRENT_DATE() )";
                }
            }
        }
        // 時価総額
        long lzero = 0;
        // 時価総額の範囲チェック
        BigInteger mcapF = BigInteger.valueOf(this.getMcapF()*100);
        BigInteger mcapT = BigInteger.valueOf(this.getMcapT()*100);
        if( mcapF.compareTo(BigInteger.valueOf(lzero)) > 0 ){
            queryW = connectString(queryW);
            if( mcapT.compareTo(BigInteger.valueOf(lzero))>0){
                //From To 条件有り
                queryW += " ( between vid.MARKET_CAPITALIZATION "+mcapF+" AND "+mcapT+")";
            }else{
                // From 条件のみ
                queryW += " vid.MARKET_CAPITALIZATION >= "+mcapF;
            }
        }else if( mcapT.compareTo(BigInteger.valueOf(lzero))>0){
            queryW = connectString(queryW);
            // To条件のみ
            queryW += " vid.MARKET_CAPITALIZATION <= "+mcapT;
        }

        // 上の条件で一旦、『銘柄情報_基本』を検索
        System.out.println("SQLオプション『"+queryW+"』");
        if( queryW != null ){
            query += queryW;
        }
        System.out.println("SQLクエリ完成『"+query+"』");
        
        return query;
    }

    /**
     * 設定の条件で企業リストを作成する
     * @return リスト作成成功:true リスト作成失敗:false
     */
    public boolean SerchCompanyList()
    {
        boolean ret = true;
        
        System.out.println("◆CompanyInfoList:SerchCompanyList()　企業の検索開始");

        //
        //　企業情報を条件付きで検索（時価総額除く）
        //
        /* @NOTEST */
        //  SQLクエリ作成
        String query = makeQuerySel_MBRANDFUND();
        /* @NOTEST */
        List<MBrandFund> bfnds;
        //  クエリを実行して結果を取得する
        bfnds = mBrandFundFacade.finds(query);
        if( bfnds == null ){
            //  リストの取得失敗
            System.err.println(mBrandFundFacade.toErrString());
            return false;
        }
        //
        // 企業検索の条件を基に　時価総額取得
        //
        String queryCap;
        //  SQLクエリ作成
        queryCap = makeQuerySel_TBRANDPRICECURRENT(query);
        List<TBrandPriceCurrent> tbpc;
        //  クエリを実行して結果を取得する
        tbpc = tBrandPriceCurrentFacade.finds(queryCap);
        if(tbpc == null){
            //  リスト取得失敗
            System.err.println(tBrandPriceCurrentFacade.toErrString());
            return false;
        }
        
        //  メモの更新日付取得
        List<TMemo> tmem;
        //
        tmem = tMemoFacade.finds_lastUpdate();
        if(tmem == null){
            //  リスト取得失敗
            System.err.println(tMemoFacade.toErrString());
            return false;
        }
        /*　最終更新日、ビューを使う手もあるが....*/
                
        //  取得したデータをリストに詰め込む    
        ArrayList<CompanyInfo> objList = new ArrayList<CompanyInfo>();
        for( int i=0; i<bfnds.size(); i++){
           //   時価総額
           BigInteger mcap = getMarketCapR(((MBrandFund)bfnds.get(i)).getBrandCd(), tbpc); 
           if( mcap==null ){
               System.out.println( "※ 銘柄情報_株価現値 データなし 　銘柄コード：『"+((MBrandFund)bfnds.get(i)).getBrandCd()+"』");
           }
           //   時価総額の範囲チェック
           BigInteger mcapF = BigInteger.valueOf(this.getMcapF()*100), mcapT = BigInteger.valueOf(this.getMcapT()*100);
            long lzero = 0;
           /* From条件 */
           if( mcapF.compareTo(BigInteger.valueOf(lzero)) > 0 ){
               if(mcap==null){
                   System.out.println("※ 時価総額 データ無 検索対象外");
                   continue;
               }else if( mcapF.compareTo(mcap)>0 ){
                   System.out.println("※ 時価総額【"+mcap+"】　条件From::"+mcapF+" 以下");
                   continue;
               }
           }
           /* To条件 */
           if( mcapT.compareTo(BigInteger.valueOf(lzero))>0){
               if(mcap==null){
                   System.out.println("※ 時価総額 データ無 検索対象外");
                   continue;
               }else if( mcapT.compareTo(mcap)<0){
                   System.out.println("※ 時価総額【"+mcap+"】　条件To::"+mcapT+" 以上");
                   continue;
               }
           }
           /* ------------------------------------------------------------*/
           /*              検索結果をデータにセットする                            */
           /* ------------------------------------------------------------*/
           // 挿入用の箱作成
          CompanyInfo obj = new CompanyInfo();
           
          //　時価総額　セット
           long mcapl = 0;
           if( mcap==null ){
               // 未上場などは「０」もあり得る
               mcapl = 0;
           }else{
               mcapl = (long)mcap.longValue();
           }
           obj.setMarketCapitalization( CompanyInfo.conv_MarketCapitalizationV(mcapl) );
           
           // 業種別コードから表示用文字列に変換
           setBizcdtse33List();
           obj.setMBrandFund((MBrandFund)bfnds.get(i), lsmcode_bct33);
           
            java.util.Date utlDatew = null;
            java.util.Date utlDate = null;
           //   メモの最終更新日付
           for( int j=0; j<tmem.size(); j++)
           {
               if( ((TMemo)tmem.get(j)).getBrandCd().equals( ((MBrandFund)bfnds.get(i)).getBrandCd() ) ){
                   // 一致
                   utlDate = ((TMemo)tmem.get(j)).getRenewalDatetime();
                   break;
                   /*
                   utlDatew = ((TMemo)tmem.get(j)).getRenewalDatetime();
                   if( utlDate !=null){
                       if( utlDate.before(utlDatew)){
                           utlDate = utlDatew;
                       }
                   }else{
                       utlDate = utlDatew;
                   }
                   */
               }
           }
            java.sql.Date sqlToday = null;
           
            if( utlDate!=null){
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                String today = format.format(utlDate );
                try {
                    utlDate = format.parse(today);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sqlToday = new java.sql.Date(utlDate.getTime());
            }
//           String  finalupDT = getCompMemoFinalUpDate(((MBrandFund)bfnds.get(i)).getStockCode());
           obj.setDate_lastMemoUp( sqlToday );
           //
           objList.add(obj);
        }
        setCompList(objList);
        return ret;
    }
    
    /**
     * 未使用
     * @param capitalization
     * @return 
     */
    private String conv_MarketCapitalizationV(Long capitalization)
    {
        String cap = String.valueOf(capitalization);
        String vcap="";

        if( capitalization < 0 ){
            vcap = "----";
            return vcap;
        }
        // 『,』 をいれていく
        for( int i=cap.length(),cn=0; i>0; i--,cn++)
        {
            if( cn>0 && cn%3==0){
                vcap = ","+vcap;
            }
            vcap = cap.substring(i-1, i) + vcap;
        }
        return vcap;
    }

    /**
     * クエリの接続子追加処理
     * @param queryw 現状のクエリ
     * @return 接続子を追加したクエリ
     */
    public String connectString(String queryw)
    {
        if( null == queryw)
            return " where";
        else
            return queryw+" and";
    }
    /**
     * 検索用クエリ作成
     * @return  検索クエリ
     */
    private String makeQuerySel_MBRANDFUND()
    {
        // JPQL作成
        String query;       //  全項目か、一覧に出す内容だけにするかは試案が必要。面倒でなければおいおいで一旦。全項目。
        // ベースとなるクエリを取得 select  ~ from MBrandFund まで
        query = CompanyInfo.NATIVE_QUERY_BASE;
        String queryW=null;
        
        // 細かい検索条件をついか
        // 企業名
        if( this.getBrandNm()!=null && this.getBrandNm().length()>0){
            queryW = connectString(queryW);
            //queryW += " mbf.BRAND_NM LIKE '%"+this.getBrandNm()+"%'";
            queryW += " mbf.BRAND_NM_SEARCH LIKE '%"+this.getBrandNm()+"%'";
        }
        // 証券コード
        if(this.getStockCd()!=null && this.getStockCd().length()>0){
            queryW = connectString(queryW);
            queryW += " mbf.STOCK_CODE = '"+this.getStockCd()+"'";
        }
        //  業種
        if(this.getBizCdTse33()!=null && this.getBizCdTse33().length()>0){
            queryW = connectString(queryW);
            queryW += " mbf.BIZ_CD_TSE_33 = '"+this.getBizCdTse33()+"'";
        }
        // 決算月
        if( this.getSettleMm()!=null && this.getSettleMm().length()>0 ){
            queryW = connectString(queryW);
            queryW += " mbf.SETTLEMENT_MM = '"+this.getSettleMm()+"'";
        }
        //  上場市場
        if( this.getMarketNm()!=null && this.getMarketNm().length()>0 ){
            queryW = connectString(queryW);
            queryW += " mbf.MARKET_NM = '"+this.getMarketNm()+"'";
        }
        //  上場状態
        if( this.getListed()!=null && this.getListed().length()>0){
            if(Integer.parseInt(this.getListed())==SerchCompItems.LISTED_NON){
                //　不問であれば特に何もしない。
            }else{
                queryW = connectString(queryW);
                if(Integer.parseInt(this.getListed())==SerchCompItems.LISTED_OK){
                    // 上場のみ
                   queryW += " (mbf.DELISTED_DATE is null || mbf.DELISTED_DATE > CURRENT_DATE() )";
                }else if(Integer.parseInt(this.getListed())==SerchCompItems.LISTED_NG){
                    // 未上場のみ
                   queryW += " (mbf.DELISTED_DATE is not null && mbf.DELISTED_DATE <= CURRENT_DATE() )";
                }
            }
        }
        // 上の条件で一旦、『銘柄情報_基本』を検索
        System.out.println("SQLオプション『"+queryW+"』");
       if( queryW != null ){
           query += queryW;
       }
        System.out.println("SQLクエリ完成『"+query+"』");
        
        return query;
    }
    /**
     * 未使用　『銘柄情報_業績実績』から【時価総額】を取得するためのクエリ作成
     * @param selbrandfundQ
     * @return 
     */
    private String makeQuerySel_TBRANDEARNINGS(String selbrandfundQ)
    {
       String queryW;
       String queryCap;
       
       queryCap = "select" +
                            " tbe.BRAND_CD" +
                            ",tbe.SETTLEMENT_CNCT_ALON_KBN"+
                            ",tbe.SETTLEMENT_TERM" +
                            ",tbe.MARKET_CAPITALIZATION" +
                            " from t_brand_earnings tbe" +
                            " where tbe.SETTLEMENT_TERM = (" +
                            " select MAX(SETTLEMENT_TERM)" +
                            " from t_brand_earnings tbe2" +
                            " where tbe2.BRAND_CD = tbe.BRAND_CD" +
                            "  )" +
                            " and tbe.BRAND_CD in";
        
       queryW = "select mbf.BRAND_CD ";
       String queryCapW = selbrandfundQ.substring(selbrandfundQ.indexOf("from"));
       queryW += queryCapW;
       queryCap += "("+queryW+")";

       return queryCap;
    }
    /**
     * 銘柄情報_株価現値　検索用クエリ作成
     * @param selbrandfundQ　銘柄情報_基本を検索した際のクエリ。この検索条件を利用する
     * @return 検索用クエリ
     */
    private String makeQuerySel_TBRANDPRICECURRENT(String selbrandfundQ)
    {
       String queryW;
       String queryCap;
       queryCap = "select" +
                            " tbe.BRAND_CD" +
                            ",tbe.BRAND_MARKET_CD"+
                            ",tbe.PRICE_DATETIME" +
                            ",tbe.PRICE_CURRENT" +
                            ",tbe.PRICE_OPEN" +
                            ",tbe.PRICE_HIGH" +
                            ",tbe.PRICE_LOW" +
                            ",tbe.MARKET_CAPITALIZATION" +
                            " from t_brand_price_current tbe" +
                            " where tbe.BRAND_CD in";
       queryW = "select mbf.BRAND_CD ";
       String queryCapW = selbrandfundQ.substring(selbrandfundQ.indexOf("from"));
       queryW += queryCapW;
       queryCap += "("+queryW+")";

       return queryCap;
    }
    /**
     * 未使用
     * @param stockCode
     * @return 
     */
    private String getCompMemoFinalUpDate( String stockCode )
    {
        String updtime = null;
        
        //  メモを検索する
        
        // 検索者の権限によっても変えるか？
        
        return updtime;
    }
    /**
     * 業種コードの変換テーブル作成
     */
    private void setBizcdtse33List()
    {
//        System.out.println("◆CompanyInfoList:convBizCdTes33() 業種コードの変換テーブル作成" );
        // 業種３３種のリストを取得する。
        if( lsmcode_bct33 ==null ){
            lsmcode_bct33 = mcf.getList(MCodeFacade.GROUPID_BIZTSE33CD);
             //  データ取得有無を確認
            if( lsmcode_bct33==null){
                System.out.println("◆◆◆失敗『"+mcf.toString()+"』");
                //データなし。
            }
        }
    }
/**
 * 企業の時価総額取得
 * @param brandCd　対象企業のコード
 * @param tbpc  銘柄情報_株価現値　のリスト
 * @return 時価総額
 */
    private BigInteger getMarketCapR( String brandCd, List<TBrandPriceCurrent>tbpc )
    {
        BigInteger retmcap = null;
        
        for(TBrandPriceCurrent tbe : tbpc){
            //
            if( tbe.getBrandCd().equals(brandCd)){
                //  一致
                retmcap = tbe.getMarketCapitalization();
 //               System.out.println("時価総額="+retmcap+"//"+tbe.getMarketCapitalization());
                break;
            }
        }
        return retmcap;
    }

    /**
     * 未使用
     * @param delisted
     * @return 
     */
    private String getListedStae(String delisted)
    {
        String listeds = "";
        
        if( delisted==null ){
            listeds = "上場";
        }else{
            //現在日時を取得する
            Calendar nowCal1 = Calendar.getInstance();
            //フォーマットパターンを指定して表示する
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date nowDate = Date.valueOf(nowCal1.toString());
            Date deldate = Date.valueOf(delisted);

            if( true == nowDate.after(deldate) ){
                listeds = "上場";
            }else{
                listeds = "未上場";
            }
        }
        
        return listeds;
    }
    
    /**
     * 試験用
     */
    private void DummyFunc()
    {

        System.out.println("◆CompanyInfoList:DummyFunc()　試験用のダミー関数");
        
        
//   会社名
       String[] dmCompanyName = new String[]{"株式会社 SJI","株式会社 ABC","株式会社 DEF","株式会社 GHI","株式会社 JKL",};
       //   証券番号
       String[] dmStokCode = new String[]{"2301","2302","2308","2309","2401",};
       
       //   業務種別
       String[] dmIndustryCode = new String[]{"情報・通信業","卸売業","証券、商品先物取引","金属製品","ゴム製品",};
       
       //   決済月
        String[] dmCloseMonth = new String[]{"10月","3月","3月","1月","10月",};
      
       //   上場市場
       String[] dmUpMarket = new String[]{"JQグロース","東証１部","東証２部","マザーズ","マザーズ",};
       
       //
       String[] dmMarketCap = new String[] { "100000", "200000", "300000", "400000", "50001"};
       
        //
        //tbCompanyInfo[] compList = new tbCompanyInfo[dmCompanyName.length];
        ArrayList<CompanyInfo> objList = new ArrayList<CompanyInfo>();

        System.out.println("◆◆セッション情報4【"+compList+"】");
        
       for( int i=0; i<dmCompanyName.length; i++){
           CompanyInfo obj = new CompanyInfo();
           
           obj.setBrand_nm(dmCompanyName[i]);
           obj.setBrand_nm(dmCompanyName[i]);
           obj.setStock_code(dmStokCode[i]);
           obj.setBiz_cd_tse_33(dmIndustryCode[i]);
           obj.setSettlement_mm(dmCloseMonth[i]);
           obj.setMarket_nm(dmUpMarket[i]);
           obj.setMarketCapitalization(dmMarketCap[i]);
           obj.setDate_lastMemoUp( new Date(System.currentTimeMillis()) );
           objList.add(obj);
       }
       //setCompList(compList);
       setCompList(objList);
    }
    /**
     * リストから、証券コードが一致する企業情報を返す
     * @param StockCd   検索する証券コード
     * @return 
     */
    public CompanyInfo getCompanyInfo( String StockCd )
    {
        CompanyInfo retCompInfo;
        retCompInfo = null;
        
        for( CompanyInfo cinfo:compList ){
            if( cinfo.getStock_code().equals(StockCd) ){
                retCompInfo = cinfo;
            }
        }
        return retCompInfo;
    }
}
