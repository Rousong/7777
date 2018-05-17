/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 企業情報詳細画面のBeanクラス
 * @author k00299
 */
@Named(value = "companyDetailBean")
//@SessionScoped
@ViewScoped
//@WithLog
public class CompanyDetailBean implements Serializable{

//<editor-fold defaultstate="collapsed" desc="メンバ変数">
    private static final long serialVersionUID = 1L;
    
//<editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
    /**
     * ユーザ情報クラス
     */
    private UserInfo userinfo;
    
    @Inject
    /**
     * メモ検索結果格納用リスト
     */
    private MemoList memoList;
    
    @Inject
    /**
     * 編集対象のメモ情報格納
     */
    private Memo editMemo;
    
    //企業情報
    @Inject
    /**
     *  表示対処の企業情報格納
     */
    private CompanyInfo viewCompInfo;
    // メモ検索に必要なデータ

    @Inject
    /**
     * メモ検索用データ
     */
    private SerchMemoItems selMemoItems;
   
    @Inject
    private DispItemPropaty dspitemPropaty;

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="facese 変数">
    // メモ検索条件
    // 日付From
    //@CstmStringDateValidation(max=100, message="${formatter.format(\"日付フォーマットエラー：『%s』は不正です。『YYYY/MM/DD』 形式もしくは、正しい範囲で入力して下さい。\",validatedValue)}")
    private String dateFrom;
    // 日付to
    //@CstmStringDateValidation(max=100, message="${formatter.format(\"日付フォーマットエラー：『%s』は不正です。『YYYY/MM/DD』 形式もしくは、正しい範囲で入力して下さい。\",validatedValue)}")
    private String dateTo;
    // 担当者１，２
    @Size(max=128, message="※ 『担当者１』は64文字（全角）までで入力して下さい!!。")
    private String chargeInPerson1;
    @Size(max=128, message="※ 『担当者２』は64文字（全角）までで入力して下さい!!。")
    private String chargeInPerson2;
    // タグ１~3
    @Size(min=4 ,max=4, message="※ 『タグ１::{value}』が不正です。ドロップダウンリストのデータご利用下さい。")
    @Pattern(regexp="[0-9]+$", message="※ 『タグ１:{value}』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String memoTag1;
    @Size(min=4 ,max=4, message="※ 『タグ２::{value}』が不正です。ドロップダウンリストのデータご利用下さい。")
    @Pattern(regexp="[0-9]+$", message="※ 『タグ２:{value}』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String memoTag2;
    @Size(min=4 ,max=4, message="※  『タグ３::{value}』が不正です。ドロップダウンリストのデータご利用下さい。")
    @Pattern(regexp="[0-9]+$", message="※  『タグ３:{value}』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String memoTag3;
    // ステータス
    @Size(min=4 ,max=4, message="※ 『ステータス』が不正です。ドロップダウンリストのデータご利用下さい。")
    @Pattern(regexp="[0-9]+$", message="※ 『ステータス』が不正です。ドロップダウンリストのデータご利用下さい。")
    private String memoStat;
    // タイトル
    @Size(max=64, message="※ 『タイトル』は64文字（全角）までで入力して下さい!!。")
    private String memoTitle;
    
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
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    /**
     * 日付 From　getter
     * @return
     */
    public String getDateFrom() {
        return dateFrom;
    }
    /**
     * 日付 From　Setter
     * @param dateFrom
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    /**
     * 日付 To　getter
     * @return
     */
    public String getDateTo() {
        return dateTo;
    }
    /**
     * 日付 To Setter
     * @param dateTo
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    /**
     *担当者1 getter
     * @return
     */
    public String getChargeInPerson1() {
        return chargeInPerson1;
    }
    /**
     * 担当者1 Setter
     * @param chargeInPerson1
     */
    public void setChargeInPerson1(String chargeInPerson1) {
        this.chargeInPerson1 = chargeInPerson1;
    }
    /**
     * 担当者2 getter （未使用)
     * @return
     */
    public String getChargeInPerson2() {
        return chargeInPerson2;
    }
    /**
     * 担当者2 Setter　（未使用）
     * @param chargeInPerson2
     */
    public void setChargeInPerson2(String chargeInPerson2) {
        this.chargeInPerson2 = chargeInPerson2;
    }
    /**
     * メモタグ1　getter1
     * @return
     */
    public String getMemoTag1() {
        return memoTag1;
    }
    /**
     * メモタグ2　setter1
     * @param memoTag1
     */
    public void setMemoTag1(String memoTag1) {
        this.memoTag1 = memoTag1;
    }
    /**
     * メモタグ2　getter2
     * @return
     */
    public String getMemoTag2() {
        return memoTag2;
    }
    /**
     * メモタグ2　Setter2
     * @param memoTag2
     */
    public void setMemoTag2(String memoTag2) {
        this.memoTag2 = memoTag2;
    }
    /**
     *メモタグ3　getter3
     * @return
     */
    public String getMemoTag3() {
        return memoTag3;
    }
    /**
     *メモタグ3　Setter3
     * @param memoTag3
     */
    public void setMemoTag3(String memoTag3) {
        this.memoTag3 = memoTag3;
    }
    /**
     * ステータス getter（未使用）
     * @return
     */
    public String getMemoStat() {
        return memoStat;
    }
    /**
     * ステータス setter（未使用）
     * @param memoStat
     */
    public void setMemoStat(String memoStat) {
        this.memoStat = memoStat;
    }
    /**
     * メモタイトル getter
     * @return
     */
    public String getMemoTitle() {
        return memoTitle;
    }
    /**
     * メモタイトル setter
     * @param memoTitle
     */
    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }
    /**
     * メモリスト getter
     * @return
     */
    public MemoList getMemoList() {
        return memoList;
    }
    /**
     * ユーザ情報 getter
     * @return 
     */
    public UserInfo getUserinfo() {
        return userinfo;
    }
    /**
     *　ユーザ情報　setter
     * @param userinfo 
     */
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    
    /**
     * 表示対象企業情報　getter
     * @return 
     */
    public CompanyInfo getViewCompInfo() {
        return viewCompInfo;
    }
    /**
     * 表示対象企業情報 setter
     * @param viewCompInfo 
     */
    public void setViewCompInfo(CompanyInfo viewCompInfo) {
        this.viewCompInfo = viewCompInfo;
    }
    /**
     * メモ検索用項目の getter
     * @return 
     */
    public SerchMemoItems getSelMemoItems() {
        return selMemoItems;
    }
    /**
     * メモ検索用項目の setter
     * @param selMemoItems 
     */
    public void setSelMemoItems(SerchMemoItems selMemoItems) {
        this.selMemoItems = selMemoItems;
    }
    /**
     * 検索結果メモ数 getter
     * @return 
     */
    public int getDsp_SerchMemoNum() {
        return dsp_SerchMemoNum;
    }
    /**
     * 検索結果メモ数 setter
     * @param dsp_SerchMemoNum 
     */
    public void setDsp_SerchMemoNum(int dsp_SerchMemoNum) {
        this.dsp_SerchMemoNum = dsp_SerchMemoNum;
    }
    /**
     * 検索結果リストの表示位置 getter
     * @return 
     */
    public int getNowListIndex() {
        return nowListIndex;
    }
    /**
     * 検索結果リストの表示位置 setter
     * @param nowListIndex 
     */
    public void setNowListIndex(int nowListIndex) {
        this.nowListIndex = nowListIndex;
    }
    /**
     * 検索結果リスト「戻る」 表示有無 getter
     * @return 
     */
    public boolean isBackList() {
        return backList;
    }
    /**
     * 検索結果リスト「戻る」 表示有無 setter
     * @param backList 
     */
    public void setBackList(boolean backList) {
        this.backList = backList;
    }
    /**
     * 検索結果リスト「次へ」 表示有無 getter
     * @return 
     */
    public boolean isNextList() {
        return nextList;
    }
    /**
     * 検索結果リスト「次へ」 表示有無 setter
     * @param backList 
     */
    public void setNextList(boolean nextList) {
        this.nextList = nextList;
    }
    /**
     * 検索結果リストに表示するメモリスト getter
     * @return 
     */
    public List<Memo> getMemoViewList() {
        return memoViewList;
    }
    /**
     *  検索結果リストに表示するメモリスト setter
     * @param memoViewList 
     */
    public void setMemoViewList(List<Memo> memoViewList) {
        this.memoViewList = memoViewList;
    }
/**
 * 
 * @return 
 */
    public DispItemPropaty getDspitemPropaty() {
        return dspitemPropaty;
    }

//</editor-fold>
    
    /**
     * Creates a new instance of CompanyDetailBean
     */
    public CompanyDetailBean() {
        System.out.println("◆CompanyDetailBean:CompanyDetailBean　コンストラクタ");
        
        dateFrom = "";
        dateTo = "";
        chargeInPerson1="";
        chargeInPerson2="";
        //memoTag1="";
        //memoTag2="";
        //memoTag3="";
        memoTitle ="";
        
        //  メモ検索結果リストのコントロール初期化
        init_Listctrl();
        
    }
    
        public final static String URL_MEMODITAIL= "MemoDetail.xhtml";
        public final static String URL_THISPAGE= "CompanyDetail.xhtml";
        public final static String URL_BACKAGE = "SerchComp.xhtml";

    /**
     *  初期化処理
     */
    @PostConstruct
    private void postconstructer()
    {
        System.out.println("◆CompanyDetailBean:postconstructer 証券番号>"+this.viewCompInfo.getStock_code()+"< >"+this.viewCompInfo.isEditflag());
        //  企業情報の取得処理
        InitialCompanyInfo();
        //  メモ検索結果リストのコントロール初期化
        init_Listctrl();
    }
    /**
     * 表示対象の企業情報を収集
     * ※ダミー　正式な処理は入っていない※
     */
    public void InitialCompanyDetail()
    {
        System.out.println("◆CompanyDetailBean:InitialCompanyDetail　データ取得？");
        if( false == InitialCompanyInfo() )
        {
            System.out.println("◆◆CompanyDetailBean:InitialCompanyDetail　データ取得？【失敗】");
            //  失敗：エラー　ログオフしてしまうか、アラートを出したのち　検索ページに飛ばしてしまう？
        }else{
            System.out.println("◆◆CompanyDetailBean:InitialCompanyDetail　データ取得？【成功】");
            //　成功：　
        }
    }
    /**
     * 表示対象の企業情報を収集する
     * ※ダミー　正式な処理は入っていない※
     * @return 
     */
    private boolean InitialCompanyInfo()
    {
        boolean ret = true;
        //
        //this.CompanyCode = this.viewCompInfo.getStock_code();
        //
        System.out.println("◆CompanyDetailBean:InitialCompanyInfo　企業情報取得【"+this.viewCompInfo.getStock_code()+"】<"+viewCompInfo+">");
        return ret;
    }
    /**
     * 『メモ検索』ボタン押下時の処理
     * ※ダミー　正式な処理は入っていない※
     * @return 
     */
    public String SerchMemo()
    {
        System.err.println("◆CompanyDetailBean:SerchMemo企業情報取得 『"+viewCompInfo+"』" );

        System.out.println("◆CompanyDetailBean:SerchMemo　メモ検索");
        
        System.out.println("◆★検索条件：日付 From【"+this.dateFrom+"】");
        System.out.println("◆★検索条件：日付 To【"+this.dateTo+"】");
        System.out.println("◆★検索条件：記入者1To【"+this.chargeInPerson1+"】");
        System.out.println("◆★検索条件：記入者2To【"+this.chargeInPerson2+"】");
        System.out.println("◆★検索条件：タグ1 To【"+this.memoTag1+"】");
        System.out.println("◆★検索条件：タグ2 To【"+this.memoTag2+"】");
        System.out.println("◆★検索条件：タグ3 To【"+this.memoTag3+"】");
        System.out.println("◆★検索条件：ステータス To【"+this.memoStat+"】");
        System.out.println("◆★検索条件：タイトル To【"+this.memoTitle+"】");
        
        memoList.setBrandCd(this.viewCompInfo.getBrand_cd());
        memoList.setStockCd(viewCompInfo.getStock_code());
        memoList.setDateFrom(this.dateFrom);
        memoList.setDateTo(this.dateTo);
        memoList.setWriteUserId1(this.chargeInPerson1);
        memoList.setWriteUserId2(this.chargeInPerson2);
        memoList.setMemoTag1(this.memoTag1);
        memoList.setMemoTag2(this.memoTag2);
        memoList.setMemoTag3(this.memoTag3);
        memoList.setTitle(this.getMemoTitle());
        //  タグの変換用
        memoList.setSelMemoItems(selMemoItems);
        
        if( !memoList.SerchMemoList()){
            //  検索失敗時の処理を検討しておこう。
            System.out.println("◆◆！！メモ検索　【失敗】 ");        
            
        }
       //   リストのコントロール開始
       this.set_ListCtrl();

       return "";
    }
    
 //<editor-fold defaultstate="collapsed" desc="ListContrl">
    /**
     * 『戻る』ボタン処理。検索結果メモリストの表示を次の表示分にする
     * @return 画面再表示用に””を返す
     */
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
    /**
     * 『次へ』ボタン処理。検索結果メモリストの表示を次の表示分にする
     * @return 画面再表示用に””を返す 
     */
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
    /**
     * リスト表示コントロール処理
     */
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
    /**
     *  メモ検索処理後のリスト表示コントロールの初期処理。
     */
    private void  init_Listctrl(){
        this.dsp_SerchMemoNum = 0;
        this.nowListIndex = 0;
        this.backList = true;
        this.nextList = true;
        this.memoViewList = null;
    }
//</editor-fold>
    
    /**
     * 選択されたメモの詳細画面表示
     * @param memoID　詳細を開くメモのメモID
     * @param editFlag  編集するかしないかの判定フラグ 
     * @return メモ詳細画面へのURL
     */
    public String goMemoDetail(String memoID,boolean editFlag)
    {
        System.out.println("◆CompanyDetailBean:goMemoDetail メモ詳細ページへ");
        System.out.println("★MemoId『"+memoID+"』　EditFlag『"+editFlag+"』");

        Memo ememo = memoList.getMemo(memoID);
        ememo.setBrandNm(viewCompInfo.getBrand_nm());
        //  編集用のデータコピー　Object.Clone　でそのうち対応したい。
        //  ユーザID
        editMemo.setUserId(ememo.getUserId());
        //  企業コード
        editMemo.setBrandCd(ememo.getBrandCd());
        //  証券コード
        editMemo.setStockCode(ememo.getStockCode());
        //  企業名称
        editMemo.setBrandNm(ememo.getBrandNm());
        //  開始日
        editMemo.setStartDate(ememo.getStartDate());
        //  開始時間
        editMemo.setStartTime(ememo.getStartTime());
        //  終了日
        editMemo.setEndDate(ememo.getEndDate());
        //  終了時間
        editMemo.setEndTime(ememo.getEndTime());
        //  訪問先担当者名
        editMemo.setPersonNm(ememo.getPersonNm());
        //  訪問先部署名
        editMemo.setPostNm(ememo.getPostNm());
        //  タグ１
        editMemo.setTag1(ememo.getTag1());
        editMemo.setTag1_val(ememo.getTag1_val());
        //  タグ２
        editMemo.setTag2(ememo.getTag2());
        editMemo.setTag2_val(ememo.getTag2_val());
        //  タグ３
        editMemo.setTag3(ememo.getTag3());
        editMemo.setTag3_val(ememo.getTag3_val());
        //  タイトル
        editMemo.setTitle(ememo.getTitle());
        //  メモ
        editMemo.setMemo(ememo.getMemo());
        //  メモID
        editMemo.setMemoId(ememo.getMemoId());
        //  新規登録日時
        editMemo.setRegisterDateTime(ememo.getRegisterDateTime());
        //　新規登録ユーザ
        editMemo.setRegisterUser(ememo.getRegisterUser());
        //  更新日時
        editMemo.setRenewalDateTime(ememo.getRenewalDateTime());
        //  更新日時
        editMemo.setRenewalUser(ememo.getRenewalUser());
        
        //  セッションに、リクエスト元の情報を埋め込む
        setSession_backURL();
        
        return URL_MEMODITAIL+"?faces-redirect=true";
    }
    /**
     * 企業への新規メモ作成
     * @return  メモ詳細画面へのURL
     */
    public String goNewMemo()
    {
        System.out.println("◆CompanyDetailBean:goNewMemo メモ新規作成へ");
        
        //  ワークをクリア
        editMemo.clear();
        // メモIDは新規なので「０」
        editMemo.setMemoId("0");
        //  新規なので、編集モード
        editMemo.setReadOnly(false);
        //  作成対処企業の証券コード
        editMemo.setStockCode(viewCompInfo.getStock_code());
        //  作成対象企業の企業コード
        editMemo.setBrandCd(viewCompInfo.getBrand_cd());
        //  作成対象企業の企業名
        editMemo.setBrandNm(viewCompInfo.getBrand_nm());
        // 初期値として本日の日付をセット
        editMemo.setStartDate(new Date(System.currentTimeMillis()));
        // 初期値として本日の日付をセット
        editMemo.setEndDate(new Date(System.currentTimeMillis()));
        
        //  セッションに、リクエスト元の情報を埋め込む
        setSession_backURL();

        return URL_MEMODITAIL+"?faces-redirect=true";
    }
    /**
     * メモ詳細画面から戻る際のURL（企業情報詳細画面URL）設定
     */
    private void setSession_backURL(){
        SessionTool.writeBackUrl(URL_THISPAGE);
    }

}
