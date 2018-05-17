/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author k00299
 */
@Named(value = "serchCompBean")
//@SessionScoped
@ViewScoped
//@WithLog
public class SerchCompBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
//<editor-fold defaultstate="collapsed" desc="@Inject">
    /**
     *  ユーザ情報
     */
    @Inject
    private UserInfo userinfo;
    
    @Inject
    private SerchCompItems selItemList;
    
    @Inject
    private CompanyInfoList compInfos;
    
    @Inject
    private CompanyInfo viewCompInfo;
    
    @Inject
    private SerchMemoItems selMemoItems;

    @Inject
    private DispItemPropaty dspitemPropaty;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="エンティティ宣言">
    /* ------------------------ */
    /* エンティティの宣言  */
    /* ------------------------ */
    /* @NOTEST　*/
    @PersistenceContext(unitName="FIRScrmUnit")
    private EntityManager em;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="メンバ変数">
    @Size(max=64, message="※ 『企業名』は{max}文字（全角）までで入力して下さい!!。")
    private String companyName;         //  企業名
    
    @Size(min=4 ,max=4, message="※ 『証券番号』は{min}桁で入力して下さい!!。")
//    @Pattern(regexp="[0-9]+$", message="${formatter.format(\"『%s:%s』 ※ 『証券番号』は数値で入力して下さい!!。\",regexp,validatedValue)}")
    @Pattern(regexp="[0-9]+$", message="※ 『証券番号』は数値で入力して下さい!!。")
    private String stockCode;                 //  証券番号
    
    @Size(min=4 ,max=4, message="※ 『業種』は5桁で入力して下さい!!。")
    @Pattern(regexp="[0-9]+$", message="※ 『業種』は数値で入力して下さい!!。")
    private String industryCode;            //  業種
    
    @Size(min=2 ,max=2, message="※ 『決済月』が不正です。ドロップダウンリストのデータご利用下さい。")
    @Pattern(regexp="[0-9]+$", message="※ 『決済月』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String closeingMonth;          //  決済月
    
    //@Size(min=1 ,max=4, message="※ 『上場市場』が不正です。ドロップダウンリストのデータご利用下さい。")
    //@Pattern(regexp="[0-9]+$", message="※ 『上場市場』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String market;                     //  上場市場
    
//    @Size(min=4 ,max=4, message="※ 『上場状態』が不正です。ドロップダウンリストのデータご利用下さい。")
    @Pattern(regexp="[0-9]+$", message="※ 『上場状態』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String listed;                        //  上場状態
    
//    @Min(value=0,message="※『時価総額』Fromは「{value}」以上を設定してください。")
//    @Max(value=10000000,message="※『時価総額』Fromは「{value}」以下を設定してください。")
//    @Pattern(regexp="[0-9]+$", message="※ 『時価総額』Fromは数値で入力して下さい!!。")
//    @Size(min=4 ,max=4, message="※ 『時価総額From』が不正です。ドロップダウンリストのデータご利用下さい。")
    private long marketCapitalizationFrom;
    
//    @Min(value=0,message="※『時価総額』Toは「{value}」以上を設定してください。")
//    @Max(value=10000000,message="※『時価総額』Toは「{value}」以下を設定してください。")
//    @Pattern(regexp="[0-9]+$", message="※ 『時価総額』Toは数値で入力して下さい!!。")
//    @Size(min=4 ,max=4, message="※ 『時価総額To』が不正です。ドロップダウンリストのデータご利用下さい。")
    private long marketCapitalizationTo;
    
    /**
     * 表示用　企業検索　件数表示用
     */
    private int dsp_SerchCompanyNum;
    private int nowListIndex;
    private boolean backList;
    private boolean nextList;
    private List<CompanyInfo>  compViewList;
    private static final int LISTMAX = 50;

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    /**
     *
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     *
     * @param stockCode
     */
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    /**
     *
     * @param industryCode
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }
    /**
     *
     * @param closeingMonth
     */
    public void setCloseingMonth(String closeingMonth) {
        this.closeingMonth = closeingMonth;
    }
    /**
     *
     * @param market
     */
    public void setMarket(String market) {
        this.market = market;
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
    public String getStockCode() {
        return stockCode;
    }
    /**
     *
     * @return
     */
    public String getIndustryCode() {
        return industryCode;
    }
    /**
     *
     * @return
     */
    public String getCloseingMonth() {
        return closeingMonth;
    }
    /**
     *
     * @return
     */
    public String getMarket() {
        return market;
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
     * @return
     */
    public SerchCompItems getSelItemList() {
        //     System.out.println("◆SerchCompBean:getSelItemList()　geter SelItemList【"+this+"】");
        return selItemList;
    }
    /**
     *
     * @return
     */
    public CompanyInfoList getCompInfos() {
        return compInfos;
    }
    /**
     *
     * @param compInfos
     */
    public void setCompInfos(CompanyInfoList compInfos) {
        this.compInfos = compInfos;
    }
    
    /**
     *
     * @return
     */
    public long getMarketCapitalizationFrom() {
        return marketCapitalizationFrom;
    }
    /**
     *
     * @param marketCapitalizationFrom
     */
    public void setMarketCapitalizationFrom(long marketCapitalizationFrom) {
        this.marketCapitalizationFrom = marketCapitalizationFrom;
    }
    /**
     *
     * @return
     */
    public long getMarketCapitalizationTo() {
        return marketCapitalizationTo;
    }
    /**
     *
     * @param marketCapitalizationTo
     */
    public void setMarketCapitalizationTo(long marketCapitalizationTo) {
        this.marketCapitalizationTo = marketCapitalizationTo;
    }

    public int getDsp_SerchCompanyNum() {
        return dsp_SerchCompanyNum;
    }

    public void setDsp_SerchCompanyNum(int dsp_SerchCompanyNum) {
        this.dsp_SerchCompanyNum = dsp_SerchCompanyNum;
    }

    public boolean isBackList() {
        return backList;
    }

    public void setBackList(boolean backList) {
        this.backList = backList;
    }

    public boolean isNextList() {
        return nextList;
    }

    public void setNextList(boolean nextList) {
        this.nextList = nextList;
    }

    public int getNowListIndex() {
        return nowListIndex;
    }

    public void setNowListIndex(int nowListIndex) {
        this.nowListIndex = nowListIndex;
    }

    public List<CompanyInfo> getCompViewList() {
        return compViewList;
    }

    public void setCompViewList(List<CompanyInfo> compViewList) {
        this.compViewList = compViewList;
    }

    public DispItemPropaty getDspitemPropaty() {
        return dspitemPropaty;
    }
    
//</editor-fold>
    
    /**
     * Creates a new instance of Serch_CompBean
     */
    public SerchCompBean() {
        System.out.println("◆SerchCompBean:SerchCompBean()　コンストラクタ【"+this+"】");
        // 表示アイテムの初期化
        init_Listctrl();
    }
  
    /**
     * 
     */
    @PostConstruct
    private void postconstructer()
    {
        System.out.println("◆SerchCompBean:postconstructer::");
        //  リストをクリアする。
        this.compInfos.clear();
        // リストの件数もクリア
        init_Listctrl();
/*        
        // CRS対策？
        if( userinfo==null )
        {
            System.out.println("◆SerchCompBean:postconstructer 『UserInfo』が不正");
        }else{
            System.out.println("◆SerchCompBean:postconstructer 『"+userinfo.getHashCode()+"』");
            if(userinfo.getUserID()==null){
                System.out.println("◆SerchCompBean:postconstructer 『UserInfo』が不正");
            }else{
                System.out.println("★★処理対象ユーザは("+userinfo.getName()+"/"+ userinfo.getUserID()+"/"+userinfo.getCompanyID()+")");
            }
        }
*/
    }
    /**
     * 『検索』ボタン押下時の処理
     * @return 
     */
    public String serch()
    {
        System.out.println("★SerchCompBean::serch『UserInfo＞"+userinfo.getHashCode()+"』");
        System.out.println("◆SerchCompBean:serch()　検索ボタン押下処理【"+this+"】");
        System.out.println("◆★検索条件：企業名【"+this.companyName+"】");
        System.out.println("◆★検索条件：証券番号【"+this.stockCode+"】");
        System.out.println("◆★検索条件：業種【"+this.industryCode+"】");
        System.out.println("◆★検索条件：決済月【"+this.closeingMonth+"】");
        System.out.println("◆★検索条件：上場市場【"+this.market+"】");
        System.out.println("◆★検索条件：上場状態【"+this.listed+"】");
        System.out.println("◆★検索条件：時価総額From【"+this.marketCapitalizationFrom+"】");
        System.out.println("◆★検索条件：時価総額To【"+this.marketCapitalizationTo+"】");
        
        if( this.marketCapitalizationFrom>0 && this.marketCapitalizationTo >0){
            if( this.marketCapitalizationFrom >= this.marketCapitalizationTo ){
                //　異常
                System.out.println("時価総額の設定値が正しくない。　From　より　To　に大きい値をセットしてください。");
                FacesContext.getCurrentInstance().addMessage("marketCapF:market_CapF", new FacesMessage("【時価総額】では 『From』　より　『To』　に大きい値をセットしてください。") );
            }
            return "";
        }
        
        compInfos.setBrandNm(companyName);
        compInfos.setStockCd(stockCode);
        compInfos.setBizCdTse33(industryCode);
        compInfos.setSettleMm(closeingMonth);
        compInfos.setMarketNm(market);
        compInfos.setListed(listed);
        compInfos.setMcapF(marketCapitalizationFrom);
        compInfos.setMcapT(marketCapitalizationTo);
        
        /* @TEST */
 //       if( !compInfos.get_VBrandInfo_List() )
       if( !compInfos.SerchCompanyList() )
        {
            //  検索失敗時の処理を検討しておこう。
            System.out.println("◆◆！！企業検索　【失敗】 ");        
        }else {
            System.out.println("◆◆！！企業検索　【成功】 ");
            System.out.println("◆◆× 結果リスト情報1【"+compInfos.getCompList()+"】");
            if( compInfos.IsCompList() ==true){
                System.out.println("◆◆×結果リスト情報2-1【"+compInfos.getCompList()+"】");
                System.out.println("◆◆検索結果出力（確認用） ");
                for( int i=0; i<compInfos.getCompList().size(); i++)
                {
                        System.out.println("◆◆▼ 結果 企業名【"+compInfos.getCompList().get(i).getBrand_nm()+"】");
                }
            }else{
                System.out.println("◆◆×結果リスト情報2-2【"+compInfos.getCompList()+"】");
                //  あまりないと思うが一応
                System.out.println("◆◆▼検索結果出力　結果リストデータ【なし】");
            }
        }
       //   リストのコントロール開始
       set_ListCtrl();
        System.out.println("◆SerchCompBean:serch()　検索ボタン押下処理完了【SerchComp.xhtml】呼び出し");
//        return "SerchComp.xhtml";
        return "";
    }

//<editor-fold defaultstate="collapsed" desc="ListContrl">
    public String backList()
    {
        //
        nowListIndex -= LISTMAX;
        if(nowListIndex-LISTMAX < 1  ){
            backList = true;
        }
        //  戻ってきたんだから進めるはず
        nextList = false;
        compViewList = compInfos.getCompList().subList(nowListIndex-1, (nowListIndex+LISTMAX)-1);
        return "";
    }
    public String nextList()
    {
        nowListIndex += LISTMAX;
        if(nowListIndex+LISTMAX > dsp_SerchCompanyNum ){
            //　次へは進めない
            nextList = true;
            compViewList = compInfos.getCompList().subList(nowListIndex-1, dsp_SerchCompanyNum);
        }else{
            // 次に進める
            compViewList = compInfos.getCompList().subList(nowListIndex-1, (nowListIndex+LISTMAX)-1);
        }
        //  進んできたんだから戻るは絶対ある
        backList = false;
 

        return "";
    }
    private void  set_ListCtrl(){
        dsp_SerchCompanyNum = compInfos.size();
        if(dsp_SerchCompanyNum == 0){
            //  検索結果「０」件では制御できないようにする。
            nowListIndex = 0;
            backList = true;
            nextList = true;
            if( compViewList != null ){
                compViewList.clear();
            }else{
                compViewList = compInfos.getCompList();
            }
            return;
        }else if( dsp_SerchCompanyNum <= LISTMAX  ){
            //  検索結果が１回の表示上限以下なら　制御出来ないようにする。
            nowListIndex = dsp_SerchCompanyNum;
            backList = true;
            nextList = true;
            compViewList = compInfos.getCompList().subList(0, nowListIndex);
            return;
        }else{
            //  検索結果が１回の表示上限を上回る
            nowListIndex = 1;       //  先頭である事を指す
            backList = true;           //  先頭なので「戻る」はない
            nextList = false;
            compViewList = compInfos.getCompList().subList(0, LISTMAX);
        }
    }
    private void  init_Listctrl(){
        dsp_SerchCompanyNum = 0;
        nowListIndex = 0;
        backList = true;
        nextList = true;
        compViewList = null;
    }
//</editor-fold>
    
/**
 * 企業情報詳細画面へ移動処理
 * @param stockCode
 * @param editflag
 * @return 
 */    
    public String goCompDetail(String stockCode,boolean editflag ) throws CloneNotSupportedException
    {
        System.out.println("★SerchCompBean::goCompDetail『"+stockCode+"』『"+editflag+"』<"+viewCompInfo+">");

        CompanyInfo cmpinf  = compInfos.getCompanyInfo(stockCode);

        //連携用データセット
        //  証券コード
        viewCompInfo.setStock_code(stockCode);
        //  銘柄コード
        viewCompInfo.setBrand_cd(cmpinf.getBrand_cd());
        //  企業名
        viewCompInfo.setBrand_nm(cmpinf.getBrand_nm());
        //  企業名短縮
        viewCompInfo.setBrand_snm(cmpinf.getBrand_snm());
        //  市場
        viewCompInfo.setMarket_nm(cmpinf.getMarket_nm());
        //  決算期
        viewCompInfo.setSettlement_mm(cmpinf.getSettlement_mm());
        //  業種
        viewCompInfo.setBiz_cd_tse_33(cmpinf.getBiz_cd_tse_33());
        // 企業住所
        viewCompInfo.setOffice_address(cmpinf.getOffice_address());
        // 企業TEL
        viewCompInfo.setOffice_tel(cmpinf.getOffice_tel());
        //  創業
        viewCompInfo.setFound_yymm(cmpinf.getFound_yymm());
        //  上場年月
        viewCompInfo.setListed_date(cmpinf.getListed_date());
        //  時価総額
        viewCompInfo.setMarketCapitalization(cmpinf.getMarketCapitalization());
        //  HP
        viewCompInfo.setOffice_url(cmpinf.getOffice_url());
        // 代表者役職
        viewCompInfo.setRepresent_director_post_nm(cmpinf.getRepresent_director_post_nm());
        //  代表者名
        viewCompInfo.setRepresent_director_nm(cmpinf.getRepresent_director_nm());
        //  事業内容
        viewCompInfo.setBiz(cmpinf.getBiz());
        //　メモ最終更新日
        viewCompInfo.setDate_lastMemoUp(cmpinf.getDate_lastMemoUp());

        //  フラグ初期化
        viewCompInfo.setEditflag(editflag);
        // 各種初期化
        selMemoItems.initial_selitems();

        return "CompanyDetail.xhtml?faces-redirect=true";
    }
    
    /**
     * 「戻る」ボタン処理。機能選択画面へ遷移。
     * @return 
     */
    public String goBack(){
        return "FunctionSelect.xhtml?faces-redirect=true";
    }
}
