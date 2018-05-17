/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.RandomStringUtils;

/**
 * ログイン画面のBean
 * @author k00299
 */
@ManagedBean
@Named(value = "loginBean")
@SessionScoped
//@WithLog
public class LoginBean  implements Serializable{
    
    private final String cookieValueSaveLoginID = "IsSaveLoginID"; 
    private final String cookieValueLoginID = "LoginID"; 
    private final String cookieValueLoginPW = "LoginPW";
    
    /**
     * ログインID
     */
    @NotNull(message="※ 『ログインID』が空です。入力して下さい。")
    private String loginID;
    /**
     * パスワード
     */
    @NotNull(message="※ 『パスワード』が空です。入力して下さい。")
    private String passwd;
    /**
     * ログインの成否フラグ
     */
    private boolean loginerr;
    /**
     * ログインID保存有無
     */
    private boolean saveLoginId;
    
    /**
     * 
     */
    @Inject
    private LoginCheckBean loginchk;
    /**
     * ログイン情報ビューのFacade
     */
    @EJB
    private VLoginuserFacade vLoginuserFacade;
    
    /**
     * ログイン成功後の遷移先アドレス
     */
//  final private String NEXTPAGEURL = "SerchComp.xhtml";
  final private String NEXTPAGEURL = "FunctionSelect.xhtml";
//  final private String NEXTPAGEURL = "CheckSecurityCode.xhtml";

    /**
     *  ユーザ情報
     */
    @Inject
    private UserInfo userinfo;
    
    /**
     * Creates a new instance of login
     */
public LoginBean() {
        System.out.println("◆LoginBean:LoginBean コンストラクタ()"+this);
        loginerr =false;
        /* 初期 */
        LoadSaveUserID();
   }
/**
 * 『ログイン状態の保存』対応処理
 */
public void LoadSaveUserID() {
    if(loadIsSaveUserID()){
        //  Cookieに保存のデータをロードする
        this.setSaveLoginId(true);
        this.setLoginID(loadUserID());
        /* 保存が未完了なのでロードもしない
        this.setPasswd(loadUserPW());
        */
        this.setPasswd("");
    }else{
        //  何も表示させない
         this.setSaveLoginId(false);
        this.setLoginID("");
        this.setPasswd("");
    }
}
/**
 * 『singin』ボタン押下時の処理
 * @return 
 */
public String singin()
   {
        System.out.println("◆LoginBean:singin() サインインボタン押下イベント処理"+this);
        System.out.println("◆◆singin()　LoginID::"+loginID);
        System.out.println("◆◆singin()　PassWD::"+passwd);
        
        System.out.println("★LoginBean::singin『UserInfo＞"+userinfo.getHashCode()+"』");

        List<VLoginuser> loginuserlist;
        VLoginuser loginuser = null;
        
        loginuserlist = vLoginuserFacade.getList(loginID);
        if( loginuserlist == null ){
            System.err.println("◆◆singin()　Login：失敗。ログインIDと一致するユーザデータ無");
            // 画面にログインエラーを表示させるためのフラグ作成
            setLoginerr(true);
            // ログイン失敗時は保存しない
            this.setSaveLoginId(false);
            // 振り出しに戻る
            return "";
        }
        //  一致する情報を探す
        for(int i=0;  i < loginuserlist.size(); i++ ){
            loginuser = (VLoginuser)loginuserlist.get(i);
            if( loginuser.getPassword() != null && passwd.equals( loginuser.getPassword() )  ){
                System.out.println("★　★ユーザ情報一致。内部ID"+loginuser.getInternalId()+"★ ★" );
                // 一致する情報有
                initial_user(loginuser);
                break;
            }
            loginuser = null;
        }
        if(loginuser==null){
            System.err.println("◆◆singin()　Login：失敗。ログインPWと一致するユーザデータ無");
            // 画面にログインエラーを表示させるためのフラグ作成
            setLoginerr(true);
            // ログイン失敗時は保存しない
            this.setSaveLoginId(false);
            // 振り出しに戻る
            return "";
        }
        // Save 有無を保存
        saveIsSaveUserID(this.getSaveLoginId());
        if( this.getSaveLoginId()==true ){
            // LoginID を　記録。クッキーに。
            saveUserID(this.getLoginID());
            /* PWを平文でしか保存できないのでやめる。
            　　実施するなら暗号化して保存が必須と思われる。
            saveUserPW(this.getPasswd());
            */
        }
        
        /*  セキュリティコードの生成とメールの送信 */
        // SecurityRequest();   /* 一時お休み ?*/
        
        //  セッション情報を埋め込む
        SetSessionID();
        
        /* 　次ページへ遷移 */
        /*  次ページのURLを取得  */
        String NextPage = SelectNextPage();
        // リダイレクトを付ける
        NextPage = NextPage+"?faces-redirect=true";
        //
        return NextPage; 
   }
/**
 * ユーザ情報にセッションIDを保存する。
 */
private void SetSessionID()
{
        //
        //  セッションに、戻り先が保存してあるので取り出す
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        HttpSession session = request.getSession(true);
        
        userinfo.setSessionID(session.getId());
        System.out.println( "★★今回のセッションIDは＞"+userinfo.getSessionID());
}
/**
 * 未使用
 * セキュリティ　コードの作成と送信。
 */
private void SecurityRequest()
{
   System.out.println("LoginBean::SecurityRequest>ログインセキュリティ処理");

    /*  セキュリティコードの生成　*/
    String securitycode = RandomStringUtils.randomAscii(10);
    /* 試験用のセキュリティコードを固定にする*/
    // securitycode = "12345";
    
    System.out.println("★セキュリティコードは：["+securitycode+"]");
    
    userinfo.setSecurityCode(securitycode);
    userinfo.setScCodeCreateTime(System.currentTimeMillis());
    
    /*  メール本文の作成    */
    String[]  toAddress = new String[1];
    
    // セキュリティコード送信用アドレスの確認
    if( userinfo.getMailAddrSc()==null)
    {
        // 連絡用アドレスの確認
        if(userinfo.getMailAddr()==null){
            //  アドレス登録無
            return;
        }
        toAddress[0] = userinfo.getMailAddr();
    }else{
        toAddress[0] = userinfo.getMailAddrSc();
    }
    
    /*  メールの送信    */
    // メールは送らない。
    // MailSend(toAddress);
}
/**
 * 未使用
 * セキュリティメール送信処理
 * @param sendAddrs 
 */
private void MailSend(String[] sendAddrs)
{
    System.out.println("LoginBean::MailSend>セキュリティコードのメール送信");
    
    Properties properties = new Properties();
    properties.setProperty("mail.smtp.host","localhost");
    Session session = Session.getDefaultInstance(properties);
    
    MimeMessage mimeMessage = new MimeMessage(session);
    //
    InternetAddress[] toAddress = new InternetAddress[sendAddrs.length];

    try{
        for( int i=0; i<sendAddrs.length; i++){
            toAddress[i] = new InternetAddress(sendAddrs[i]);
        }
    }catch(AddressException e){
        System.err.println("★送信アドレス設定で例外エラー発生『"+e.getMessage()+"』");
//        e.printStackTrace();
        return;
    }
    // セキュリティコード用のメール送信
    try{
        //　宛先
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, toAddress);
        //　送信元
        mimeMessage.setFrom(new InternetAddress("no-reply@sji-inc.jp"));
        //　サブジェクトの設定
        mimeMessage.setSubject("FISCOIR セキュリティコード送付");
        //　本文
        String message;
        message = "セキュリティコードは以下です下\n";
        message = message+"『"+userinfo.getSecurityCode()+"』";
        mimeMessage.setText(message);
        
        //　設定の保存
        mimeMessage.saveChanges();
        //　送信
        Transport.send(mimeMessage);
        
    }catch(MessagingException e){
        System.err.println("★送信処理で例外エラー発生『"+e.getMessage()+"』");
//        e.printStackTrace();
    }
}

/**
 * ログイン後のページ選択処理
 * ログインしたユーザの権限等により選択する
 * ※ダミーで固定ページを返している※
 * @return 
 */
private String SelectNextPage(){
    String NextPage = "";
    
    //  ログインしたユーザの権限等で遷移先ページを変更する？
    
    // 企業？
    
    //　個人のロール？
    
    NextPage = NEXTPAGEURL;
    
    return NextPage;
}
/**
 * デバック用　リクエストの情報等を出力
 */
private void printRequest(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        
        System.out.println("getLocalAddr()    ≫"+request.getLocalAddr());
        System.out.println("getLocalName()  ≫"+request.getLocalName());
        System.out.println("getPathInfo()       ≫"+request.getPathInfo());
        System.out.println("getRemoteAddr()≫"+request.getRemoteAddr());
        System.out.println("getRemoteUser()≫"+request.getRemoteUser());
        System.out.println("getRequestURI() ≫"+request.getRequestURI());
        System.out.println("getSession()        ≫"+request.getSession());
        System.out.println("ExgetAuthType()                  ≫"+externalContext.getAuthType());
        System.out.println("ExgetRemoteUser()             ≫"+externalContext.getRemoteUser());
        System.out.println("ExgetRequestServerName()≫"+externalContext.getRequestServerName());
        System.out.println("ExgetRequestPathInfo()       ≫"+externalContext.getRequestPathInfo());
        System.out.println("ExgetRequestContextPath()≫"+externalContext.getRequestContextPath());
}
/**
 * Cookie へ、ユーザIDを保存する
 * @param value 
 */
private void saveUserID( String value )
{
        System.out.println("◆LoginBean::saveUserID");
        
        /* */
        printRequest();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        Cookie[] cookies = request.getCookies();
        Cookie saveuid_cookie = null;
        if( cookies!=null ){
            System.out.println("◆◆クッキーあり");
            //  ログインIDとセーブ情報状態を保存
            for(int i=0;i<cookies.length; i++){
                //  該当のクッキーが存在するか？
                if( cookies[i].getName().equals(this.cookieValueLoginID)){
                    saveuid_cookie = cookies[i];
                    break;
                }
            }
            if( saveuid_cookie != null ){
                // 既存に上書き
                System.out.println("◆◆既存データの上書き"+value);
                saveuid_cookie.setValue(value);
            }else{
                //  あたしく作る
                System.out.println("◆◆新規データの登録"+this.getSaveLoginId());
                saveuid_cookie = new Cookie(cookieValueLoginID, value);
            }
        }else{
            System.out.println("◆◆クッキーなし");
            System.out.println("◆◆新規データの登録"+this.getSaveLoginId());
            saveuid_cookie = new Cookie(cookieValueLoginID, value);
        }
        //
        response.addCookie(saveuid_cookie);
}
private void saveUserPW( String value )
{
        System.out.println("◆LoginBean::saveUserPW");
        /* */
        printRequest();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        Cookie[] cookies = request.getCookies();
        Cookie saveupw_cookie = null;
        if( cookies!=null ){
            System.out.println("◆◆クッキーあり");
            //  ログインIDとセーブ情報状態を保存
            for(int i=0;i<cookies.length; i++){
                //  該当のクッキーが存在するか？
                if( cookies[i].getName().equals(this.cookieValueLoginPW)){
                    saveupw_cookie = cookies[i];
                    break;
                }
            }
            if( saveupw_cookie != null ){
                // 既存に上書き
                System.out.println("◆◆既存データの上書き"+value);
                saveupw_cookie.setValue(value);
            }else{
                //  あたしく作る
                System.out.println("◆◆新規データの登録"+this.getSaveLoginId());
                saveupw_cookie = new Cookie(cookieValueLoginPW, value);
            }
        }else{
            System.out.println("◆◆クッキーなし");
            System.out.println("◆◆新規データの登録"+this.getSaveLoginId());
            saveupw_cookie = new Cookie(cookieValueLoginPW, value);
        }
        //
        response.addCookie(saveupw_cookie);
}
/**
 * Cookie から、ユーザIDを読み出す
 * @return 
 */
private String loadUserID()
{
        System.out.println("◆LoginBean::loadUserID");
        String ret="";
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        Cookie[] cookies = request.getCookies();
        Cookie saveuid_cookie = null;

        if( cookies!=null ){
            System.out.println("◆◆クッキー【有り】");
            //  ログインIDとセーブ情報状態を保存
            for(int i=0;i<cookies.length; i++){
                //  該当のクッキーが存在するか？
                if( cookies[i].getName().equals(this.cookieValueLoginID)){
                    saveuid_cookie = cookies[i];
                    break;
                }
            }
            if( saveuid_cookie != null ){
                // データあり
                ret = saveuid_cookie.getValue();
                System.out.println("◆◆クッキーに保存データ【有り】");
            }else{
                System.out.println("◆◆クッキーに保存データ【無し】");
            }
        }else{
            System.out.println("◆◆クッキー【無し】");
        }
        System.out.println("◆◆ 【"+ret+"】　を返す");
        return ret;
}
private String loadUserPW()
{
         System.out.println("◆LoginBean::loadUserPW");
        String ret="";
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        Cookie[] cookies = request.getCookies();
        Cookie saveuPw_cookie = null;

        if( cookies!=null ){
            System.out.println("◆◆クッキー【有り】");
            //  ログインIDとセーブ情報状態を保存
            for(int i=0;i<cookies.length; i++){
                //  該当のクッキーが存在するか？
                if( cookies[i].getName().equals(this.cookieValueLoginPW)){
                    saveuPw_cookie = cookies[i];
                    break;
                }
            }
            if( saveuPw_cookie != null ){
                // データあり。
                ret = saveuPw_cookie.getValue();
                System.out.println("◆◆クッキーに保存データ【有り】");
            }else{
                System.out.println("◆◆クッキーに保存データ【無し】");
            }
        }else{
            System.out.println("◆◆クッキー【無し】");
        }
        System.out.println("◆◆ 【"+ret+"】　を返す");
        return ret;   
}
/**
 * Cookie へ、ログイン情報保存フラグを保存する
* @param value 
 */
private void saveIsSaveUserID( boolean value )
{
        System.out.println("◆LoginBean::saveIsSaveUserID");

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        Cookie[] cookies = request.getCookies();
        Cookie saveuid_cookie = null;
        if( cookies!=null ){
            System.out.println("◆◆クッキーあり");
            //  ログインIDとセーブ情報状態を保存
            for(int i=0;i<cookies.length; i++){
                //  該当のクッキーが存在するか？
                if( cookies[i].getName().equals(this.cookieValueSaveLoginID)){
                    saveuid_cookie = cookies[i];
                    break;
                }
            }
            if( saveuid_cookie != null ){
                // 既存に上書き
                System.out.println("◆◆既存データの上書き"+value);
                saveuid_cookie.setValue(Boolean.toString(value));
            }else{
                //  あたしく作る
                System.out.println("◆◆新規データの登録"+this.getSaveLoginId());
                saveuid_cookie = new Cookie(cookieValueSaveLoginID, Boolean.toString(value));
            }
        }else{
            System.out.println("◆◆クッキーなし");
            System.out.println("◆◆新規データの登録"+this.getSaveLoginId());
            saveuid_cookie = new Cookie(cookieValueSaveLoginID, Boolean.toString(value));
        }
        //
        response.addCookie(saveuid_cookie);
}
/**
 * Cookieから、ログイン情報保存フラグを読み出す
 * @return 
 */
private boolean loadIsSaveUserID()
{
        System.out.println("◆LoginBean::loadIsSaveUserID");

        boolean ret;
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        Cookie[] cookies = request.getCookies();
        Cookie saveuid_cookie = null;

        if( cookies!=null ){
            System.out.println("◆◆クッキー【有り】");
            //  ログインIDとセーブ情報状態を保存
            for(int i=0;i<cookies.length; i++){
                //  該当のクッキーが存在するか？
                if( cookies[i].getName().equals(this.cookieValueSaveLoginID)){
                    saveuid_cookie = cookies[i];
                    break;
                }
            }
            if( saveuid_cookie != null ){
                // 既存に上書き
                ret = Boolean.valueOf(saveuid_cookie.getValue());
                System.out.println("◆◆クッキーに保存データ【有り】");
            }else{
                System.out.println("◆◆クッキーに保存データ【無し】");
                ret = false;
            }
        }else{
            System.out.println("◆◆クッキー【無し】");
            ret = false;
        }
        System.out.println("◆◆ 【"+ret+"】　を返す");
        return ret;
}
    
    /**
     * ログインしたユーザの各種情報を取得
     * @param loginID ユーザID
     * @return 
     */
private boolean initial_user( String loginID ){
        boolean ret = true;

        System.out.println("◆LoginBean:initial_user()　ユーザ情報取得"+this);
        
        //  個人情報を抜き出す。
        if( !userinfo.load_userinfo(loginID) ){
            // 失敗!!
            ret = false;
        }else if(false){            //  権限を抜き出す
            // 失敗
            ret = false;
        }else if(false){            //  権限を抜き出す
            //失敗
            ret = false;
        }
        return ret;
 }
/**
 * 未使用
 * ログインしたユーザ情報をセットする
 * @param vloginuser ユーザ情報ビュー
 * @return 
 */
private boolean initial_user(VLoginuser vloginuser){
    boolean ret = true;
    // ユーザID
    userinfo.setUserID(vloginuser.getUserId());
    // ユーザ氏名
    userinfo.setName(vloginuser.getLname()+" "+vloginuser.getFname());
    //録ユーザメールアドレス（ユーザIDと同じはず）
    userinfo.setMailAddr(vloginuser.getMailaddrReg());
    //通知用メールアドレス（なければ登録メールアドレスをセット）
    if( vloginuser.getMailaddrNotice() != null ){
        userinfo.setMailAddrSc(vloginuser.getMailaddrNotice());
    }else{
        userinfo.setMailAddrSc(vloginuser.getMailaddrReg());
    }
    if(false){            //  権限を抜き出す
        // 失敗
        ret = false;
    }else if(false){            //  権限を抜き出す
        //失敗
        ret = false;
    }
    return ret;
  }

//<editor-fold defaultstate="collapsed" desc="setter/getter">
/* geter Seter の定義 */
/**
 * ログインID getter
 * @return
 */
public String getLoginID(){
    return this.loginID;
}
/**
 * ログインID setter
 * @param newVal
 */
public void setLoginID(String newVal){
    this.loginID = newVal;
}
/**
 *
 * @return
 */
public String getPasswd(){
    return this.passwd;
}
/**
 *
 * @param newVal
 */
public void setPasswd(String newVal){
    this.passwd = newVal;
}
/**
 *
 * @return
 */
public boolean getLoginerr(){
    return this.loginerr;
}
/**
 *
 * @param newStat
 */
public void setLoginerr(boolean newStat){
    this.loginerr = newStat;
}
/**
 *
 * @return
 */
public boolean getSaveLoginId() {
    return this.saveLoginId;
}
/**
 *
 * @param saveLoginId
 */
public void setSaveLoginId(boolean saveLoginId) {
    this.saveLoginId = saveLoginId;
}
//</editor-fold>
      
}
