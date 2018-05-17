/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * 企業情報の保持クラス
 * @author k00299
 */
@Named
@SessionScoped
//@Dependent
public class CompanyInfo  implements Serializable {

    private static final long serialVersionUID = 1L;
    
//<editor-fold defaultstate="collapsed" desc="NATIVE_QUERY_BASE の定義">
    /**
     * 企業検索を行う際のSQL
     */
    public final static String NATIVE_QUERY_BASE = "select mbf.BRAND_CD,"
            + "mbf.STOCK_CODE, "
            + "mbf.BRAND_NM,"
            + "mbf.BIZ_CD_TSE_33,"
            + "mbf.SETTLEMENT_MM,"
            + "mbf.MARKET_NM,"
            + "mbf.FOUND_YYMM,"
            + "mbf.LISTED_DATE,"
            + "mbf.DELISTED_DATE,"
            + "mbf.COMMENT_EARNINGS,"
            + "mbf.OFFICE_ADDRESS,"
            + "mbf.OFFICE_TEL,"
            + "mbf.OFFICE_URL,"
            + "mbf.REPRESENT_DIRECTOR_POST_NM,"
            + "mbf.REPRESENT_DIRECTOR_NM,"
            + "mbf.BIZ,"
            + "mbf.RENEWAL_DATETIME,"
            + "mbf.REGISTER_DATETIME"
            + " from m_brand_fund mbf";
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="NATIVE_QUERY_VIEW_BASE の定義">
    /**
     * 企業情報を取得するためのSQL（view）
     */
    public final static String NATIVE_QUERY_VIEW_BASE = "SELECT * FROM firscrm.v_brand_info vbi ";
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="プロパティの宣言">
    //企業情報の項目
    private Date    update_time;                                //更新日時
    private String  stock_code;                                  //証券番号
    private String  brand_cd;                                    //会社番号(内部用？）
    private String  brand_nm;                                   //会社名称（正式）
    private String  found_yymm;                                 //創業年月日
    private String  listed_date;                                    //上場年月日
    private String  delisted_date;                              //上場停止年月日
    private String  brand_snm;                                  //会社略称
    private String  market_nm;                                  //上場市場
    private String  biz_cd_tse_33;                              //業種コード
    private String  office_address;                             //所在地
    private String  represent_director_post_nm;     //代表の肩書き
    private String  represent_director_nm;              //代表の氏名
    private String  irtitle;		//IR担当の肩書き
    private String  irname;		//IR担当の氏名
    private String  office_tel;		//電話番号
    private String  headoffice_tel;                      //代表番号
    private String  office_url;		//HPのアドレス
    private String  paridskind;		//
    private String  biz;        		//業務内容
    private String  settlement_mm;                  //決済月
    
    private String  marketCapitalization;           // 時価総額
    private String  listedState;                            // 上場状態
    private Date  date_lastMemoUp;                // メモ最終更新（追加）日
    
    private boolean editflag;
//</editor-fold>
    /**
     * コンストラクタ
     */
    public CompanyInfo() {
    }

    /**
     * クローン
     * @return
     * @throws CloneNotSupportedException 
     */
    @Override
    public CompanyInfo clone()  throws CloneNotSupportedException{
        CompanyInfo b=null;
        
        try{
            b = (CompanyInfo)super.clone();
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }

    /**
    * @return update_time
    */
    public Date getUpdate_time() {
           return update_time;
    }
    /**
    * @param update_time セットする update_time
    */
    public void setUpdate_time(Date update_time) {
           this.update_time = update_time;
    }
    /**
    * @return stock_code
    */
    public String getStock_code() {
           return stock_code;
    }
    /**
    * @param stock_code セットする stock_code
    */
    public void setStock_code(String stock_code) {
           this.stock_code = stock_code;
    }
    /**
    * @return brand_cd
    */
    public String getBrand_cd() {
           return brand_cd;
    }
    /**
    * @param brand_cd セットする brand_cd
    */
    public void setBrand_cd(String brand_cd) {
           this.brand_cd = brand_cd;
    }
    /**
     * @return brand_nm
     */
    public String getBrand_nm() {
            return brand_nm;
    }
    /**
     * @param brand_nm セットする brand_nm
     */
    public void setBrand_nm(String brand_nm) {
            this.brand_nm = brand_nm;
    }
    /**
     * @return found_yymm
     */
    public String getFound_yymm() {
            return found_yymm;
    }
    /**
     * @param found_yymm セットする found_yymm
     */
    public void setFound_yymm(String found_yymm) {
            this.found_yymm = found_yymm;
    }
    /**
     * @return listed_date
     */
    public String getListed_date() {
            return listed_date;
    }
    /**
     * @param listed_date セットする listed_date
     */
    public void setListed_date(String listed_date) {
            this.listed_date = listed_date;
    }
    /**
     * @return delisted_date
     */
    public String getDelisted_date() {
            return delisted_date;
    }
    /**
     * @param delisted_date セットする delisted_date
     */
    public void setDelisted_date(String delisted_date) {
            this.delisted_date = delisted_date;
    }
    /**
     * @return brand_snm
     */
    public String getBrand_snm() {
            return brand_snm;
    }
    /**
     * @param brand_snm セットする brand_snm
     */
    public void setBrand_snm(String brand_snm) {
            this.brand_snm = brand_snm;
    }
    /**
     * @return market_nm
     */
    public String getMarket_nm() {
            return market_nm;
    }
    /**
     * @param market_nm セットする market_nm
     */
    public void setMarket_nm(String market_nm) {
            this.market_nm = market_nm;
    }
    /**
     * @return idsCode
     */
    public String getBiz_cd_tse_33() {
            return biz_cd_tse_33;
    }
    /**
     * @param idsCode セットする idsCode
     */
    public void setBiz_cd_tse_33(String idsCode) {
            biz_cd_tse_33 = idsCode;
    }
    /**
     * @return office_address
     */
    public String getOffice_address() {
            return office_address;
    }
    /**
     * @param office_address セットする office_address
     */
    public void setOffice_address(String office_address) {
            this.office_address = office_address;
    }
    /**
     * @return represent_director_post_nm
     */
    public String getRepresent_director_post_nm() {
            return represent_director_post_nm;
    }
    /**
     * @param represent_director_post_nm セットする represent_director_post_nm
     */
    public void setRepresent_director_post_nm(String represent_director_post_nm) {
            this.represent_director_post_nm = represent_director_post_nm;
    }
    /**
     * @return represent_director_nm
     */
    public String getRepresent_director_nm() {
            return represent_director_nm;
    }
    /**
     * @param represent_director_nm セットする represent_director_nm
     */
    public void setRepresent_director_nm(String represent_director_nm) {
            this.represent_director_nm = represent_director_nm;
    }
    /**
     * @return irtitle
     */
    public String getIrtitle() {
            return irtitle;
    }
    /**
     * @param irtitle セットする irtitle
     */
    public void setIrtitle(String irtitle) {
            this.irtitle = irtitle;
    }
    /**
     * @return irname
     */
    public String getIrname() {
            return irname;
    }
    /**
     * @param irname セットする irname
     */
    public void setIrname(String irname) {
            this.irname = irname;
    }
    /**
     * @return office_tel
     */
    public String getOffice_tel() {
            return office_tel;
    }
    /**
     * @param office_tel セットする office_tel
     */
    public void setOffice_tel(String office_tel) {
            this.office_tel = office_tel;
    }
    /**
     * 
     * @return 
     */
    public String getHeadoffice_tel() {
        return headoffice_tel;
    }
    /**
     * 
     * @param headoffice_tel 
     */
    public void setHeadoffice_tel(String headoffice_tel) {
        this.headoffice_tel = headoffice_tel;
    }
    /**
     * @return office_url
     */
    public String getOffice_url() {
            return office_url;
    }
    /**
     * @param office_url セットする office_url
     */
    public void setOffice_url(String office_url) {
            this.office_url = office_url;
    }
    /**
     * @return idskind
     */
    public String getParidskind() {
            return paridskind;
    }
    /**
     * @param idskind セットする idskind
     */
    public void setParidskind(String idskind) {
            this.paridskind = idskind;
    }
    /**
     * @return biz
     */
    public String getBiz() {
            return biz;
    }
    /**
     * @param biz セットする biz
     */
    public void setBiz(String biz) {
            this.biz = biz;
    }
    /**
     * @return settlement_mm
     */
    public String getSettlement_mm() {
            return settlement_mm;
    }
    /**
     * @param settlement_mm セットする settlement_mm
     */
    public void setSettlement_mm(String settlement_mm) {
            this.settlement_mm = settlement_mm;
    }
    /**
     * 
     * @return 
     */
    public boolean isEditflag() {
        return editflag;
    }
    /**
     * 
     * @param editflag 
     */
    public void setEditflag(boolean editflag) {
        this.editflag = editflag;
    }
    /**
     * 
     * @return 
     */
    public String getMarketCapitalization() {
        return marketCapitalization;
    }
    /**
     * 
     * @param marketCapitalization 
     */
    public void setMarketCapitalization(String marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }
    /**
     * 
     * @return 
     */
    public String getListedState() {
        return listedState;
    }
    /**
     * 
     * @param listedState 
     */
    public void setListedState(String listedState) {
        this.listedState = listedState;
    }
    /**
     * 
     * @return 
     */
    public Date getDate_lastMemoUp() {
        return date_lastMemoUp;
    }
    /**
     * 
     * @param date_lastMemoUp 
     */
    public void setDate_lastMemoUp(Date date_lastMemoUp) {
        this.date_lastMemoUp = date_lastMemoUp;
    }
    /**
     * 企業情報のセット
     * @param vbi 企業情報取得View の結果データ
     */
    public void setMBrandFund( VBrandInfo vbi )
    {
        //  企業コード
        this.setBrand_cd(vbi.getBrandCd());
        //   銘柄コード
        this.setStock_code( vbi.getStockCode());
        //   企業名
        this.setBrand_nm( vbi.getBrandNm() );
        //  企業名（省略）
        this.setBrand_snm(vbi.getBrandSnm());
        //   業種コード
        this.setBiz_cd_tse_33( vbi.getContents1() );
        //   決算月
        this.setSettlement_mm( vbi.getSettlementMm() );
        //  創業
        this.setFound_yymm(vbi.getFoundYymm());
        //   上場市場名
        this.setMarket_nm( vbi.getMarketNm() );
        //   上場状態
        String listedStat = getListedStae(vbi.getDelistedDate());
        this.setListedState(listedStat);
        //  上場年月
        this.setListed_date(vbi.getListedDate());
        //  上場廃止年月
        this.setDelisted_date(vbi.getDelistedDate());
        //  企業住所
        this.setOffice_address(vbi.getOfficeAddress());
        //  企業電話番号
        this.setOffice_tel(vbi.getOfficeTel());
        // 企業HP
        this.setOffice_url(vbi.getOfficeUrl());
        //  代表役職
        this.setRepresent_director_post_nm(vbi.getRepresentDirectorPostNm());
        //  代表氏名
        this.setRepresent_director_nm(vbi.getRepresentDirectorNm());
        // 事業内容
        this.setBiz(vbi.getBiz());
        //　時価総額　セット
        BigInteger capZ = vbi.getMarketCapitalization();
        if( capZ!=null){
            this.setMarketCapitalization( CompanyInfo.conv_MarketCapitalizationV(vbi.getMarketCapitalization().longValue() ));
        }else{
            Long L0 = new Long(0);
            this.setMarketCapitalization( CompanyInfo.conv_MarketCapitalizationV(L0));
        }
        //  メモ更新　最終日付
        //  時刻型の変換
        java.util.Date utlDate = vbi.getLastUpdate();
        if( utlDate!=null){
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
            String today = format.format(utlDate );
        try {
                utlDate = format.parse(today);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sqlToday = new java.sql.Date(utlDate.getTime());
            this.setDate_lastMemoUp(sqlToday);
        }else{
            this.setDate_lastMemoUp(null);
        }

    }
/**
 * 企業情報のセット
 * @param mbf
 * @param lsmcode_bct33 
 */
    public void setMBrandFund( MBrandFund mbf, List<MCode> lsmcode_bct33 )
    {
        //  企業コード
        this.setBrand_cd(mbf.getBrandCd());
        //   銘柄コード
        this.setStock_code( mbf.getStockCode());
        //   企業名
        this.setBrand_nm( mbf.getBrandNm() );
        //  企業名（省略）
        this.setBrand_snm(mbf.getBrandSnm());
        //   業種コード
        String indcode = convBizCdTes33(mbf.getBizCdTse33(), lsmcode_bct33);
        this.setBiz_cd_tse_33( indcode );
        //   決算月
        this.setSettlement_mm( mbf.getSettlementMm() );
        //  創業
        this.setFound_yymm(mbf.getFoundYymm());
        //   上場市場名
        this.setMarket_nm( mbf.getMarketNm() );
        //   上場状態
        String listedStat = getListedStae(mbf.getDelistedDate());
        this.setListedState(listedStat);
        //  上場年月
        this.setListed_date(mbf.getListedDate());
        //  上場廃止年月
        this.setDelisted_date(mbf.getDelistedDate());
        //  企業住所
        this.setOffice_address(mbf.getOfficeAddress());
        //  企業電話番号
        this.setOffice_tel(mbf.getOfficeTel());
        // 企業HP
        this.setOffice_url(mbf.getOfficeUrl());
        //  代表役職
        this.setRepresent_director_post_nm(mbf.getRepresentDirectorPostNm());
        //  代表氏名
        this.setRepresent_director_nm(mbf.getRepresentDirectorNm());
        // 事業内容
        this.setBiz(mbf.getBiz());
    }
 /**
  * 東証の業種コードから文字列への変換
  * @param bizcdtse33 業種コード
  * @param lsmcode_bct33　業種コード変換リスト
  * @return 業種コード　文字列
  */
    private String convBizCdTes33(String bizcdtse33, List<MCode> lsmcode_bct33 )
    {
        String codename = "";

        //System.out.println("◆CompanyInfoList:convBizCdTes33() 業種コードの変換 >>"+bizcdtse33 );
        
        //  変換リストが無ければ初期化処理
        if(lsmcode_bct33==null)
        {
            return "unKnown";
        }
        //
        for(MCode bct33 : lsmcode_bct33){
            if( bct33.getMCodePK().getCode().equals(bizcdtse33) ){
                codename = bct33.getContents1();
//                System.out.println("□変換結果 >>"+codename );
                break;
            }
        }
        return codename;
    }
    /**
     * 上場状態のコードから文字列変換
     * @param delisted 上場状態コード
     * @return 上場状態文字列
     */
    private String getListedStae(String delisted)
    {
        String listeds = "";
        
        if( delisted==null ){
            listeds = "上場";
        }else{
            // 上場廃止日が入っていても、日付が未来義であれば問題ないので
            listeds = "未上場";
        }
        
        return listeds;
    }
    
    /**
     * 金額を値から文字列に変換。（3桁毎「,」を入れていく
     * @param capitalization
     * @return 変換した　金額文字列
     */
    public static String conv_MarketCapitalizationV(Long capitalization)
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

}
