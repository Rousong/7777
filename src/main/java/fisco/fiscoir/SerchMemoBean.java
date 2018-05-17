/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author k00299
 */
@Named(value = "serchMemoBean")
//@SessionScoped
@ViewScoped
public class SerchMemoBean implements Serializable {

    private static final long serialVersionUID = 1L;

//<editor-fold defaultstate="collapsed" desc="Inject/EJB">
    @Inject
    private UserInfo userinfo;
    
    // メモList
    @Inject
    private SerchMemoItems selSmtgItemList;
    
    @Inject
    private MemoList memoList;
    
    @Inject
    private Memo editMemo;
    
    //  基本企業情報取得用
    @EJB
    private MBrandFundFacade mBrandFundFacade;
    
    @Inject
    private DispItemPropaty dspitemPropaty;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Static">
    private final static String URL_MEMODITAIL= "MemoDetail.xhtml";
    private final static  String URL_THISPAGE = "SerchMemo.xhtml";
    private final static  String URL_BACKAGE = "FunctionSelect";
//</editor-fold>
   
//<editor-fold defaultstate="collapsed" desc="プロパティ">
   // メモ検索条件
   // 日付From
   //@CstmStringDateValidation(max=100, message="${formatter.format(\"日付フォーマットエラー：『%s』は不正です。『YYYY/MM/DD』 形式もしくは、正しい範囲で入力して下さい。\",validatedValue)}")
   private String dateFrom;
   // 日付to
   //@CstmStringDateValidation(max=100, message="${formatter.format(\"日付フォーマットエラー：『%s』は不正です。『YYYY/MM/DD』 形式もしくは、正しい範囲で入力して下さい。\",validatedValue)}")
   private String dateTo;
   //証券番号
   @Size(min=4 ,max=4, message="※ 『証券番号』は{min}桁で入力して下さい!!。")
   @Pattern(regexp="[0-9]+$", message="※ 『証券番号』は数値で入力して下さい!!。")
   private String stockCode;                 //  証券番号
   // 担当者１，２
   @Size(max=128, message="※ 『担当者１』は64文字（全角）までで入力して下さい!!。")
   private String chargeInPerson1;
   @Size(max=128, message="※ 『担当者２』は64文字（全角）までで入力して下さい!!。")
   private String chargeInPerson2;
   // タグ１~3
   @Size(min=4 ,max=4, message="※ 『タグ１』が不正です。ドロップダウンリストのデータご利用下さい。")
   @Pattern(regexp="[0-9]+$", message="※ 『タグ１』が不正です。ドロップダウンリストのデータご利用下さい。")
   private String memoTag1;
   @Size(min=4 ,max=4, message="※ 『タグ２』が不正です。ドロップダウンリストのデータご利用下さい。")
   @Pattern(regexp="[0-9]+$", message="※ 『タグ２』が不正です。ドロップダウンリストのデータご利用下さい。")
   private String memoTag2;
   @Size(min=4 ,max=4, message="※  『タグ３』が不正です。ドロップダウンリストのデータご利用下さい。")
   @Pattern(regexp="[0-9]+$", message="※  『タグ３』が不正です。ドロップダウンリストのデータご利用下さい。")
   private String memoTag3;
   // ステータス
   @Size(min=5 ,max=5, message="※ 『ステータス』が不正です。ドロップダウンリストのデータご利用下さい。")
   @Pattern(regexp="[0-9]+$", message="※ 『ステータス』が不正です。ドロップダウンリストのデータご利用下さい。")
   private String memoStat;
   
   //上場市場
   //@Pattern(regexp="[0-9]", message="※ 『上場市場』が不正です。ドロップダウンリストのデータご利用下さい。")
   private String market;
   
   // タイトル
   @Size(max=64, message="※ 『タイトル』は64文字（全角）までで入力して下さい!!。")
   private String memoTitle;
   // メモID
   @Size(max=14, message="※ 『メモID』は最大{min}桁です。")
   @Pattern(regexp="[0-9]+$", message="※ 『メモID』は数値で入力して下さい!!。")
   private String memoID;
   
       /**
     * 表示用　メモ検索　件数表示用
     */
    private int dsp_SerchMemoNum;
    private int nowListIndex;
    private boolean backList;
    private boolean nextList;
    private List<Memo>  memoViewList;
    private static final int LISTMAX = 20;

//</editor-fold>

    /**
     * Creates a new instance of SerchMemo
     */
    public SerchMemoBean() {
        System.out.println("◆SerchMemoBean:コンストラクタ::");
        init_Listctrl();
    }

//<editor-fold defaultstate="collapsed" desc="Setter/getter">
    public UserInfo getUserinfo() {
        return userinfo;
    }
    
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    
    public SerchMemoItems getSelSmtgItemList() {
        return selSmtgItemList;
    }
    
    public void setSelSmtgItemList(SerchMemoItems selSmtgItemList) {
        this.selSmtgItemList = selSmtgItemList;
    }
    
    public MemoList getMemoList() {
        return memoList;
    }
    
    public void setMemoList(MemoList memoList) {
        this.memoList = memoList;
    }
    
    public Memo getEditMemo() {
        return editMemo;
    }
    
    public void setEditMemo(Memo editMemo) {
        this.editMemo = editMemo;
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
    
    public String getStockCode() {
        return stockCode;
    }
    
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    
    public String getChargeInPerson1() {
        return chargeInPerson1;
    }
    
    public void setChargeInPerson1(String chargeInPerson1) {
        this.chargeInPerson1 = chargeInPerson1;
    }
    
    public String getChargeInPerson2() {
        return chargeInPerson2;
    }
    
    public void setChargeInPerson2(String chargeInPerson2) {
        this.chargeInPerson2 = chargeInPerson2;
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
    
    public String getMemoStat() {
        return memoStat;
    }
    
    public void setMemoStat(String memoStat) {
        this.memoStat = memoStat;
    }
    
    public String getMarket() {
        return market;
    }
    
    public void setMarket(String market) {
        this.market = market;
    }
    
    public String getMemoTitle() {
        return memoTitle;
    }
    
    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }
    
    public String getMemoID() {
        return memoID;
    }
    
    public void setMemoID(String memoID) {
        this.memoID = memoID;
    }

    public int getDsp_SerchMemoNum() {
        return dsp_SerchMemoNum;
    }

    public void setDsp_SerchMemoNum(int dsp_SerchMemoNum) {
        this.dsp_SerchMemoNum = dsp_SerchMemoNum;
    }

    public int getNowListIndex() {
        return nowListIndex;
    }

    public void setNowListIndex(int nowListIndex) {
        this.nowListIndex = nowListIndex;
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

    public List<Memo> getMemoViewList() {
        return memoViewList;
    }

    public void setMemoViewList(List<Memo> memoViewList) {
        this.memoViewList = memoViewList;
    }

    public DispItemPropaty getDspitemPropaty() {
        return dspitemPropaty;
    }
    
//</editor-fold>
/*    
    @PostConstruct
    private void postconstructer()
    {
        System.out.println("◆SerchMemoBean:postconstructer::");
        //  リストをクリアする。
        this.editMemo.clear();
        // リストの件数もクリア
        init_Listctrl();
    }
    */
    
    public String SerchMemo()
    {
        System.out.println("◆SerchMemoBean:SerchMemo　メモ検索");
        
        System.out.println("◆★検索条件：日付 From【"+this.dateFrom+"】");
        System.out.println("◆★検索条件：日付 To【"+this.dateTo+"】");
        System.out.println("◆★検索条件　記入者１【"+this.chargeInPerson1+"】");
 //       System.out.println("◆★検索条件：記入者２【"+this.chargeInPerson2+"】");
        System.out.println("◆★検索条件：タグ１【"+this.memoTag1+"】");
        System.out.println("◆★検索条件：タブ２【"+this.memoTag2+"】");
        System.out.println("◆★検索条件：タグ３【"+this.memoTag3+"】");
//        System.out.println("◆★検索条件：ステータス【"+this.memoStat+"】");
        System.out.println("◆★検索条件：タイトル【"+this.memoTitle+"】");
        System.out.println("◆★検索条件：メモID【"+this.memoID+"]");
        System.out.println("◆★検索条件：証券コード【"+this.stockCode+"]");
        System.out.println("◆★検索条件：上場市場【"+this.market+"]");

        //  検索条件をセット
        //  日付From
        memoList.setDateFrom(dateFrom);
        //  日付To
        memoList.setDateTo(dateTo);
        //  記入者１
        memoList.setWriteUserId1(this.chargeInPerson1);
        //  タグ１-3
        memoList.setMemoTag1(memoTag1);
        memoList.setMemoTag2(memoTag2);
        memoList.setMemoTag3(memoTag3);
        //  タイトル
        memoList.setTitle(memoTitle);
        //  証券番号
        memoList.setStockCd(stockCode);
        // 上場市場
        memoList.setMarketNm(market);
        
        
        //  タグの変換用
        memoList.setSelMemoItems(selSmtgItemList);
        
        if( !memoList.SerchMemoListM()){
            //  検索失敗時の処理を検討しておこう。
            System.out.println("◆◆！！メモ検索　【失敗】 ");        
            // FacesContextを取得します。
            FacesContext context = FacesContext.getCurrentInstance();
            // メッセージを関連付けるUIコンポーネントを取得します。
            // ここでは、id="SerchMemo_Condition"を指定したフォームの中の、
            // id="serchMemobtn_VMsg"を指定したボタンを
            // 取得しています。
            UIComponent component = context.getViewRoot().findComponent("SerchMemo_Condition:date_from");
//            UIComponent component = context.getViewRoot().findComponent("SerchMemo_Condition:serchMemobtn");
            // UIコンポーネントのクライアントIDを取得します。
            String clientId = component.getClientId(context);
            // メッセージを作成します。
            FacesMessage message = new FacesMessage(memoList.getErrMsg());
            // メッセージをUIコンポーネントに関連付けます。
            context.addMessage(clientId, message);
        }
       //   リストのコントロール開始
       this.set_ListCtrl();
        return "";
    }

/**
 *  初期化時処理
 */    
    @PostConstruct
    public void InitialMemoInfo(){
    Date today = new Date(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    //dateFrom = sdf.format(today);
    memoTag1 = "0050";
    memoTag2 = "0050";
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
        memoViewList = memoList.getMemos().subList(nowListIndex-1, (nowListIndex+LISTMAX)-1);
        return "";
    }
    public String nextList()
    {
        nowListIndex += LISTMAX;
        if(nowListIndex+LISTMAX > dsp_SerchMemoNum ){
            //　次へは進めない
            nextList = true;
            memoViewList = memoList.getMemos().subList(nowListIndex-1, dsp_SerchMemoNum);
        }else{
            // 次に進める
            memoViewList = memoList.getMemos().subList(nowListIndex-1, (nowListIndex+LISTMAX)-1);
        }
        //  進んできたんだから戻るは絶対ある
        backList = false;
 

        return "";
    }
    private void  set_ListCtrl(){
        dsp_SerchMemoNum = memoList.size();
        if(dsp_SerchMemoNum == 0){
            //  検索結果「０」件では制御できないようにする。
            nowListIndex = 0;
            backList = true;
            nextList = true;
            if( memoViewList != null ){
                memoViewList.clear();
            }else{
                memoViewList = memoList.getMemos();
            }
            return;
        }else if( dsp_SerchMemoNum <= LISTMAX  ){
            //  検索結果が１回の表示上限以下なら　制御出来ないようにする。
            nowListIndex = dsp_SerchMemoNum;
            backList = true;
            nextList = true;
            memoViewList = memoList.getMemos().subList(0, nowListIndex);
            return;
        }else{
            //  検索結果が１回の表示上限を上回る
            nowListIndex = 1;       //  先頭である事を指す
            backList = true;           //  先頭なので「戻る」はない
            nextList = false;
            memoViewList = memoList.getMemos().subList(0, LISTMAX);
        }
    }
    private void  init_Listctrl(){
        this.dsp_SerchMemoNum = 0;
        this.nowListIndex = 0;
        this.backList = true;
        this.nextList = true;
        this.memoViewList = null;
    }
//</editor-fold>

    /**
     * 「編集」ボタン押下、メモ詳細画面への遷移処理
     * @param memoID　表示対象メモのメモID
     * @param editFlag　編集状態フラグ
     * @return 遷移先URL
     */
   
    public String goMemoDetail(String memoID,boolean editFlag)
    {
        System.out.println("◆SerchMemoBean:goMemoDetail メモ詳細ページへ");
        System.out.println("★MemoId『"+memoID+"』　EditFlag『"+editFlag+"』");

        Memo ememo = memoList.getMemo(memoID);
        //企業名と証券コードを取得する
        editMemo.setBrandCd(ememo.getBrandCd());
        MBrandFund mbf = mBrandFundFacade.find(ememo.getBrandCd());
        if( mbf==null){
            
        }else{
            editMemo.setBrandNm(mbf.getBrandNm());
            editMemo.setStockCode(mbf.getStockCode());
        }
        //  編集用のデータコピー　Object.Clone　でそのうち対応したい。
        //editMemo.setStockCode(ememo.getStockCode());
        //editMemo.setBrandNm(ememo.getBrandNm());
        editMemo.setEndDate(ememo.getEndDate());
        editMemo.setEndTime(ememo.getEndTime());
        editMemo.setMemo(ememo.getMemo());
        editMemo.setMemoId(ememo.getMemoId());
        editMemo.setPersonNm(ememo.getPersonNm());
        editMemo.setPostNm(ememo.getPostNm());
        editMemo.setRegisterDateTime(ememo.getRegisterDateTime());
        editMemo.setRegisterUser(ememo.getRegisterUser());
        editMemo.setRenewalDateTime(ememo.getRenewalDateTime());
        editMemo.setRenewalUser(ememo.getRenewalUser());
        editMemo.setStartDate(ememo.getStartDate());
        editMemo.setStartTime(ememo.getStartTime());
        editMemo.setTag1(ememo.getTag1());
        editMemo.setTag1_val(ememo.getTag1_val());
        editMemo.setTag2(ememo.getTag2());
        editMemo.setTag2_val(ememo.getTag2_val());
        editMemo.setTag3(ememo.getTag3());
        editMemo.setTag3_val(ememo.getTag3_val());
        editMemo.setTitle(ememo.getTitle());
        editMemo.setUserId(ememo.getUserId());
        
        //
        setSession_backURL();

        //  メモID
        //editMemo.setMemoId(memoID);
        //  リードオンリーフラグ
        //editMemo.setReadOnly(editFlag);

        //return "MemoDetail.xhtml?faces-redirect=true";
        return URL_MEMODITAIL+"?faces-redirect=true";
    }
    
    /**
     * 「戻る」ボタンクリック時の処理
     * @return 遷移先URL
     */
    public String back()
    {
        System.out.println("◆SerchMemoBean:back 戻るボタン処理");
        
        //return "FunctionSelect.xhtml?faces-redirect=true";
        return URL_BACKAGE+"?faces-redirect=true";
    }
    /**
     * メモ編集画面遷移前に呼び出し元URLを登録する処理
     */
    private void setSession_backURL(){
        //  セッションに、リクエスト元の情報を埋め込む
        /*
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        HttpSession session = request.getSession(true);
        session.setAttribute("backUrl", "SerchMemo.xhtml");
        */
        SessionTool.writeBackUrl(URL_THISPAGE);
    }

}
