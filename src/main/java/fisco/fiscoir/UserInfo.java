/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.beans.*;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k00299
 */
@Named
@SessionScoped
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private PropertyChangeSupport propertySupport;

    /**
     * ユーザ氏名
     */
    private String name;

    /**
     * ユーザ固有のID　内部用
     */
    private String userID;
    
    /**
     * 所属会社の　会社ID
     */
    private String companyID;
    
    /**
     * 通常連絡用メールアドレス。（必須）
     * セキュリティコード送信用メールアドレスが未設定時はこちらに送付。
     */
    private String mailAddr;
    
    /* ------------------------ */
    /* セキュリティコード用        */
    /* 現状は未使用             */
    /* ------------------------ */
     // セキュリティコード送信用メールアドレス
    private String mailAddrSc;
    // 作成したセキュリティコードの箱
    private String securityCode;
    //  セキュリティコード作成時間保持
    private long ScCodeCreateTime;

    /* ------------------------ */
    /*  tb_user のエンティティ  */
    /* ------------------------ */
    /*   @NOTEST */
    @PersistenceContext(unitName="FIRScrmUnit")
    private EntityManager em;
    
    
    private String sessionID;
    
    
    public UserInfo() {
        propertySupport = new PropertyChangeSupport(this);
        System.out.println("◆UserInfo：UserInfo() チェンジプロパティの初期化。"+this);
        
        sessionID = null;
    }
    
//<editor-fold defaultstate="collapsed" desc="Setter/Getter">
    public String getName() {
        return name;
    }
    
    public void setName(String value) {
        System.out.println("◆UserInfo：setName() セッター>>Newval["+value+"] oldval["+name+"]");
        String oldValue = name;
        name = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, name);
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String UserID) {
        this.userID = UserID;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        System.out.println("◆UserInfo：addPropertyChangeListener() ");
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        System.out.println("◆UserInfo：removePropertyChangeListener() ");
        propertySupport.removePropertyChangeListener(listener);
    }
    
    public String getCompanyID() {
        return companyID;
    }
    
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
    
    public String getMailAddr() {
        return mailAddr;
    }
    
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }
    
    public String getMailAddrSc() {
        return mailAddrSc;
    }
    
    public void setMailAddrSc(String mailAddrSc) {
        this.mailAddrSc = mailAddrSc;
    }
    
    public String getSecurityCode() {
        return securityCode;
    }
    
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    
    public long getScCodeCreateTime() {
        return ScCodeCreateTime;
    }
    
    public void setScCodeCreateTime(long ScCodeCreateTime) {
        this.ScCodeCreateTime = ScCodeCreateTime;
    }
    
    public String getSessionID() {
        return sessionID;
    }
    
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
//</editor-fold>
    
    /**
     * ログインIDから個人の登録情報を取り出す
     * @param loginID
     * @return 
     */
    public boolean load_userinfo( String loginID)
    {
        System.out.println("◆UserInfo：load_userinfo() ");
        boolean ret = true;
    
        /* DBからユーザ情報各種を読込んでくる */
        // オブジェクト作成
        /* @NOTEST */
        try{
            // DBから該当ユーザの情報を取得
            MUserInfo tbuser;
            tbuser = this.em.find(MUserInfo.class, loginID);
            //ユーザ氏名
            setName(tbuser.getLname()+" "+tbuser.getFname());
            //ユーザID
            setUserID(tbuser.getUserId());
            //登録ユーザメールアドレス（ユーザIDと同じはず）
            this.setMailAddr(tbuser.getMailaddrReg());
            //通知用メールアドレス（なければ登録メールアドレスをセット）
            if( tbuser.getMailaddrNotice() != null ){
                this.setMailAddrSc(tbuser.getMailaddrNotice());
            }else{
                this.setMailAddrSc(tbuser.getMailaddrReg());
            }
        }catch(IllegalArgumentException iaEx){
            System.err.printf("　◆ユーザ情報のロードに失敗!! 【"+iaEx.toString()+"】");
            return false;
        }
        
        /* @TEST 
        setName("試験　太郎");
        setMailAddr("tairabune-kousuke@sji-inc.jp");
        */
        
        
        System.out.println("◆◆　Name["+name+"] UserID["+userID+"]");
       return ret;
    }
    
    public boolean IsSession()
    {
        boolean ret = false;
        if( this.sessionID != null ){
            ret = true;
        }
        return ret;
    }

    /**
     * 
     * @return 
     */
    public String getHashCode() 
    {
        return Integer.toHexString(this.hashCode());
    }

}
