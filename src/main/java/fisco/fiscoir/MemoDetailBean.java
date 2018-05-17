/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author k00299
 */
@Named(value = "memoDetailBean")
//@SessionScoped
@ViewScoped
public class MemoDetailBean  implements Serializable{

    private static final long serialVersionUID = 1L;
//<editor-fold defaultstate="collapsed" desc="プロパティ">
    
    private enum VIEWMODE { READONLY,  EDIT,  NEW}
    
//<editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    private UserInfo userinfo;
    
    @Inject
    private Memo editMemo;
    
    @Inject
    private MemoList memoList;
    
    @Inject
    private DispItemPropaty dspitemPropaty;
//</editor-fold>
    //
    private String readOnly;
    
    //  画面上のボタン制御用変数
    private boolean viewEditbtn;
    private boolean viewDelbtn;
    private boolean viewReMemobtn;
    private boolean viewUpdatebtn;
    private boolean viewBackbtn;
    
    private String labelUpdatebtn;  //　 2017.02.22 顧客要望 修正 新規と更新でボタンの表記を変更する
    private boolean updateed;
    private String updateMsg;
    
    //  処理モード
    private VIEWMODE vmode;
    
    //  何処から呼び出されたかを保持しておく。戻る時に判断材料にする
    private String requestURI;
    
//<editor-fold defaultstate="collapsed" desc="DBアクセスクラス">
    /* ------------------------ */
    /* DBアクセスクラス  */
    /* ------------------------ */
    /* @NOTEST　*/
    @EJB
            TMemoFacade tmfa;
    @EJB
            TNumberingFacade tnbfa;
//</editor-fold>
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    public Memo getEditMemo() {
        return editMemo;
    }
    
    public void setEditMemo(Memo editMemo) {
        this.editMemo = editMemo;
    }
    
    public String getReadOnly() {
//        System.out.println("※※getReadOnly="+readOnly+"※※");
return readOnly;
    }
    
    public void setReadOnly(String readOnly) {
//        System.out.println("※※setReadOnly="+readOnly+"※※");
this.readOnly = readOnly;
    }
    
    public boolean isViewEditbtn() {
        return viewEditbtn;
    }
    
    public void setViewEditbtn(boolean viewEditbtn) {
        this.viewEditbtn = viewEditbtn;
    }
    
    public boolean isViewDelbtn() {
        return viewDelbtn;
    }
    
    public void setViewDelbtn(boolean viewDelbtn) {
        this.viewDelbtn = viewDelbtn;
    }
    
    public boolean isViewUpdatebtn() {
        return viewUpdatebtn;
    }
    
    public void setViewUpdatebtn(boolean viewUpdatebtn) {
        this.viewUpdatebtn = viewUpdatebtn;
    }
    
    public boolean isViewBackbtn() {
        return viewBackbtn;
    }
    
    public void setViewBackbtn(boolean viewBackbtn) {
        this.viewBackbtn = viewBackbtn;
    }
    
    public boolean isViewReMemobtn() {
        return viewReMemobtn;
    }
    
    public void setViewReMemobtn(boolean viewReMemobtn) {
        this.viewReMemobtn = viewReMemobtn;
    }
    
    public String getLabelUpdatebtn() {
        return labelUpdatebtn;
    }

    public void setLabelUpdatebtn(String labelUpdatebtn) {
        this.labelUpdatebtn = labelUpdatebtn;
    }

    public boolean isUpdateed() {
        return updateed;
    }

    public void setUpdateed(boolean updateed) {
        this.updateed = updateed;
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }
    
    public DispItemPropaty getDspitemPropaty() {
        return dspitemPropaty;
    }

//</editor-fold>
    
    /**
     * Creates a new instance of MemoDetailBean
     */
    public MemoDetailBean() {
        System.out.println("◆MemoDetailBean::MemoDetailBean ");
        
        readOnly="";
        this.setUpdateed(false);
    }

    /**
     *  ポスト後に呼び出されるコンストラクタ
     *  呼び出し元からの編集状態に合わせて画面の表示を初期する。
     */
    @PostConstruct
    public void InitialMemoInfo(){
        System.out.println("◆MemoDetailBean::InitialMemoInfo ");
        
        System.out.println("◆◆USERINFO→"+userinfo.getHashCode()+"←");

        //  リードオンリーか否かをセット
        readOnly = Boolean.toString(editMemo.isReadOnly());
        
        System.out.println("◆◆メモID→"+editMemo.getMemoId()+"←");
        System.out.println("◆◆初期状態：ReadOnly→"+readOnly+"←");
        
        // リクエスト情報を保持しておく
        getRequestParam();
        
//        editMemo = new Memo();
        
        //  ダミーデータをセットする。
        long mid;
        mid = Long.parseLong(editMemo.getMemoId());

        if( mid == 0 ){
            System.out.println("◆◆　新規");
            //  メモIDをクリア
            editMemo.setMemoId("");
            // 日付には本日の日付をセット
            editMemo.setStartDate(new Date(System.currentTimeMillis()));
            editMemo.setEndDate(null); // 2017.02.22 顧客要望 修正　終了日は何もつけない
//            editMemo.setEndDate(new Date(System.currentTimeMillis()));　//　 2017.02.22 顧客要望 修正　終了日は何もつけない
            // 時刻は空
            editMemo.setStartTime("");
            editMemo.setEndTime("");
            // メモも空
            editMemo.setMemo("");
//            editMemo.setRegisterDateTime("");
            // ユーザIDにユーザのログインIDを
            editMemo.setRegisterUser(userinfo.getUserID());
            
//editMemo.setStatus("");

            editMemo.setTag1("");
            editMemo.setTag2("");
            editMemo.setTag3("");
            editMemo.setTitle("");
            editMemo.setUserId(userinfo.getUserID());
            
            setEditMode(VIEWMODE.NEW);
            setButtonView(VIEWMODE.NEW);
        }else{
            System.out.println("◆◆　閲覧");
            // メモID有はメモ編集
            
            /* 本当はDBからメモIDにそぐった情報を取得
                また、権限をチェックして、編集・削除ボタンを押せなくするのもあり
            */
            /*
//            editMemo.setCompanyCode("2103");
            editMemo.setCompanyName("(株)SJI");
            editMemo.setEndDate("2016/09/01");
            editMemo.setEndTime("11:30");
            editMemo.setMemo("試験試験試験試験試験試験試験試験試験試験試験試験試験試験試験");
            editMemo.setRegisterDateTime(new Date(System.currentTimeMillis()));
            editMemo.setRegisterUser("system");
            editMemo.setStartDate("2016/09/01");
            editMemo.setStartTime("10:30");
            editMemo.setStatus("完了");
            editMemo.setTag1("会議");
            editMemo.setTag2("");
            editMemo.setTag3("");
            editMemo.setTitle("【試験】　メモ詳細表示");
            editMemo.setUserId("11001");
            */
            setEditMode(VIEWMODE.READONLY);
            setButtonView(VIEWMODE.READONLY);
        }
        
    }
 
    /**
     * 編集用のボタン制御
     * @param mode 編集モード :VIEWMODE{READONLY/EDIT/NEW}
     */
    private void setButtonView(VIEWMODE mode){

        System.out.println("◆MemoDetailBran:setButtonView >"+mode);
        
        vmode = mode;

        /* 「戻る」とボタンを有効に*/
        this.setViewBackbtn(true);

         switch(mode){
            case READONLY:
                /* 「編集」と「削除」と「返信(仮)」ボタンを有効*/
               this.setViewEditbtn(true);
               this.setViewDelbtn(true);
               this.setViewReMemobtn(true);
               /* [更新」ボタンを無効に    */
               this.setViewUpdatebtn(false);
               this.setLabelUpdatebtn("更新");  //　 2017.02.22 顧客要望 修正
               break;
            case EDIT:
                /* 「編集」と「削除」と「返信(仮)」ボタンを隠す*/
               this.setViewEditbtn(false);
               this.setViewDelbtn(false);
               this.setViewReMemobtn(false);
               /* [更新」ボタンを有効に    */
               this.setViewUpdatebtn(true);
               this.setLabelUpdatebtn("更新");  //　 2017.02.22 顧客要望 修正
                break;
            case NEW:
                /* 「編集」と「削除」と「返信(仮)」ボタンを隠す*/
               this.setViewEditbtn(false);
               this.setViewDelbtn(false);
               this.setViewReMemobtn(false);
               /* [更新」ボタンを有効に    */
               this.setViewUpdatebtn(true);
               this.setLabelUpdatebtn("登録");  //　 2017.02.22 顧客要望 修正
               break;
         }
    }
    /**
     * 画面のインプット項目の入力可否を編集モードによって操作
     * @param mode 編集モード :VIEWMODE{READONLY/EDIT/NEW}
     */
    private void setEditMode(VIEWMODE mode)
    {
        System.out.println("◆MemoDetailBran:setEditMode >"+mode);

         switch(mode){
            case READONLY:
               this.setReadOnly("true");
                break;
            case EDIT:
               this.setReadOnly("false");
                break;
            case NEW:
               this.setReadOnly("false");
               break;
         }
    }
    /**
     * 「編集」ボタン押下処理
     * 読み取り専用のフラグを「true」→「false」に変更
     */
    public void changeEditMode(){
         System.err.println("◆MemoDetailBean::changeEdit 【編集】　ボタン処理");
       // リードオンリーを解除する
       setEditMode(VIEWMODE.EDIT);
        //
        setButtonView(VIEWMODE.EDIT);
    }
    /**
     * 「削除」ボタン押下処理
     * @return 
     */
    public String memoDel(){
        System.err.println("◆MemoDetailBean::memoDel 【削除】　ボタン処理");
        
        /*　確認用のポップアップ必要かも  JSF　側でやるがよい*/
        if( null == this.editMemo.getMemoId() ){
            //  メモID無は削除しない
            return "";
        }
        //
        System.out.println("＞対象のメモID"+this.editMemo.getMemoId());
        if( false == tmfa.deleate(this.editMemo.getMemoId()) ){
            //  削除失敗
            System.err.println("削除失敗!! ＞"+tmfa.toErrString() );
        }
        String backURL = SessionTool.readBackUrl();
        backURL +="?faces-redirect=true";
        System.err.println("◆memoDel:: 【"+backURL+"】　");

        return backURL;
    }
    /**
     * 
     * @return 
     */
    public String memoRe(){
        System.err.println("◆MemoDetailBean::memoDel　【返信(仮)】　ボタン処理");
        
        /* 現在のメモの「訪問先企業名」「訪問先部署」「訪問先担当者」「内容」を引き継いだ新しいメモ作成　*/
        //  ユーザIDはそのまま
        //  作成対処企業の証券コード
        //  作成対象企業の企業コード
        //  作成対象企業の企業名
        //  上三つは既存データを利用する
        //  訪問先部署　クリア
        editMemo.setPostNm(null);
        //  訪問先担当者 クリア
        editMemo.setPersonNm(null);
        //  タグ1～３クリア
        editMemo.setTag1(null);
        editMemo.setTag1_val(null);
        editMemo.setTag2(null);
        editMemo.setTag2_val(null);
        editMemo.setTag3(null);
        editMemo.setTag3_val(null);
        // 初期値として本日の日付をセット
        editMemo.setStartDate(new java.sql.Date(System.currentTimeMillis()));
        // 初期値として本日の日付をセット
        editMemo.setEndDate(null);
        //  時間クリア
        editMemo.setStartTime(null);
        editMemo.setEndTime(null);

        //  登録者・時間、更新者・時間をクリア
        editMemo.setRegisterDateTime(null);
        editMemo.setRegisterUser(null);
        editMemo.setRenewalDateTime(null);
        editMemo.setRenewalUser(null);
        
        //  タイトルをクリア
        editMemo.setTitle("");
        
        // メモ短縮クリア
        editMemo.setMemoSt("");
        
        //  メモ内容を 引用して初期セット
        String reMemo = new String();
        reMemo = editMemo.getMemo();
        if( reMemo!=null){
            reMemo =                 "\n-------------メモID：「"+editMemo.getMemoId()+"」から引用-----------------------\n"+reMemo;
            reMemo = reMemo+"\n-------------メモID：「"+editMemo.getMemoId()+"」から引用おしまい---------------\n";
            editMemo.setMemo(reMemo);
        }else{
            //  メモが無いならやらない
        }
        //  メモIDはクリア
        editMemo.setMemoId("");

        //  新規扱いなので、編集モード
         setEditMode(this.vmode.NEW);
         setButtonView(this.vmode.NEW);

//        return CompanyDetailBean.URL_MEMODITAIL+"!?faces-redirect=true";
        return "";
    }
    /**
     * 新しいメモIDの取得
     * @return 
     */
    private String getNewMemoID()
    {
        int newId;
        /* 試験中 */
        newId = tnbfa.newNumber(TNumberingFacade.MEMOID);
        if( newId==0 ){
            //  新規作成してみる
            newId = tnbfa.createNumbering();
            if( newId == 0 )
            //  新規も失敗　諦める
            return null;
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(cal.getTime());
        
        String newMemoID = sdf.format(cal.getTime()) + String.format("%1$06d",newId);
        System.out.println(">新しい　メモIDは 『"+newMemoID+"』");
       return newMemoID;
    }
    /**
     * 「更新」ボタンの押下処理
     * @return 
     */
    public String Update(){
        System.err.println("◆MemoDetailBean::Update 【更新】　ボタン処理");

        boolean  newMemoflg =false;
        
        /*　結果表示用ポップアップをON */
        this.setUpdateed(true);
        
        TMemo memo;
        /* 内容の取り込み*/
        /* @NOTEST */
        //  メモIDのセット
 System.out.println("MemoID::"+editMemo.getMemoId());
        if(editMemo.getMemoId().length() > 0){
            //  更新時はメモを一旦取りだして更新する
            memo = tmfa.find(editMemo.getMemoId());
            if( null==memo ){
                //  検索に失敗
                System.err.println("更新データが存在しない！！　なにも出来ないので戻る");
                this.setUpdateMsg("警告：更新処理できませんでした。元となるメモが消されている可能性があります。");
                return "";
            }
            newMemoflg = false;
        }else{
            memo = new TMemo();
            // メモIDがないのでメモIDを作成する
            String newMemoID = getNewMemoID();
            if( newMemoID==null){
                System.err.println("新メモID取得に失敗！！　登録処理を中止。");
                this.setUpdateMsg("警告：登録処理できませんでした。新しいメモIDの取得に失敗した可能性があります。");
                 return "";
            }
            editMemo.setMemoId(newMemoID);
            memo.setMemoId(editMemo.getMemoId());
            //
            newMemoflg = true;
        }
        //  ユーザID
        memo.setUserId(editMemo.getUserId());
        //  銘柄コード
        memo.setBrandCd(editMemo.getBrandCd());
        //  訪問先担当部署名
        memo.setPostNm(editMemo.getPostNm());
        //  訪問先担当者名
        memo.setPersonNm(editMemo.getPersonNm());
        //  日付 From
//        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd");
        memo.setDateFrom(editMemo.getStartDate());
        //  日付 to
        memo.setDateTo(editMemo.getEndDate());
        //　時間 From
        memo.setTimeFrom(editMemo.getStartTime());
        //
        memo.setTimeTo(editMemo.getEndTime());
        //  メモタグ１-3
        memo.setTag1(editMemo.getTag1_val());
        memo.setTag2(editMemo.getTag2_val());
        memo.setTag3(editMemo.getTag3_val());
        //  メモタイトル
        memo.setTitle(editMemo.getTitle());
        //  メモ
        memo.setMemo(editMemo.getMemo());
        //  登録日付とユーザは新規であればセット
        if( newMemoflg ){
            //  登録日付
            memo.setRegisterDatetime(new Date(System.currentTimeMillis()));
            editMemo.setRegisterDateTime(memo.getRegisterDatetime());
            //  登録ユーザ
            memo.setRegisterUser(userinfo.getUserID());
            editMemo.setRegisterUser(memo.getRenewaluser());
        }
        //　更新日付 は今現在の値をセット
        memo.setRenewalDatetime(new Date(System.currentTimeMillis()));
        editMemo.setRenewalDateTime(memo.getRenewalDatetime());
        //  更新ユーザ
        memo.setRenewaluser(userinfo.getUserID());
        editMemo.setRenewalUser(memo.getRenewaluser());

        //  
        //TMemo tmemo;
        if( newMemoflg ){
            System.out.println( "◆新規登録 ◆");
            //  登録
            tmfa.create(memo);
            this.setUpdateMsg("情報：メモの新規登録が完了しました！！。");
        }else{
            System.out.println( "◆更新登録 ◆");
            tmfa.update(memo);
            this.setUpdateMsg("情報：メモの更新が完了しました！！。");
        }
        // リードオンリーを解除する
        setEditMode(VIEWMODE.EDIT);
        //
        setButtonView(VIEWMODE.EDIT);
        
        return "";
    }
    /**
     * 「戻る」ボタン押下処理
     * @return 
     */
    public String closeEdit(){
        System.err.println("◆MemoDetailBean::closeEdit 【戻る】　ボタン処理");
        
         //  セッションに、戻り先が保存してあるので取り出す
        //String backURL=readBackUrl();
        String backURL = SessionTool.readBackUrl();

        backURL +="?faces-redirect=true";
        System.err.println("◆MemoDetailBean:: 【"+backURL+"】　");

        return backURL;
    }
    /*
    public String readBackUrl(){
         //  セッションに、戻り先が保存してあるので取り出す
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        HttpSession session = request.getSession(true);
        String backURL=(String)session.getAttribute("backUrl");
        
        return backURL;
    }
    */
    private void getRequestParam(){
/*        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        System.out.println("getLocalAddr()    ≫"+request.getLocalAddr());
        System.out.println("getLocalName()  ≫"+request.getLocalName());
        System.out.println("getPathInfo()       ≫"+request.getPathInfo());
        System.out.println("getRemoteAddr()≫"+request.getRemoteAddr());
        //System.out.println("getRemoteUser()≫"+request.getRemoteUser());
        System.out.println("getRequestURI() ≫"+request.getRequestURI());
        System.out.println("getSession()        ≫"+request.getSession());
        System.out.println("getMethod()        ≫"+request.getMethod());
        System.out.println("getRequestURI()"+request.getRequestURI()+".......................");
        //System.out.println("ExgetAuthType()                  ≫"+externalContext.getAuthType());
        //System.out.println("ExgetRemoteUser()             ≫"+externalContext.getRemoteUser());
        System.out.println("ExgetRequestServerName()≫"+externalContext.getRequestServerName());
        System.out.println("ExgetRequestPathInfo()       ≫"+externalContext.getRequestPathInfo());
        System.out.println("ExgetRequestContextPath()≫"+externalContext.getRequestContextPath());
*/
    }
}
