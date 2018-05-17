/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * ログインチェック
 * @author k00299
 */
@RequestScoped
@Stateless
//@WithLog
public class LoginCheckBean {

    /* ------------------------ */
    /*  tb_user のエンティティ  */
    /* ------------------------ */
    /* @NOTEST　*/
    @PersistenceContext(unitName="FIRScrmUnit")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * 初期化処理
     */
   @PostConstruct
    public void init() 
    {
        System.out.println("◆LoginCheckBean:init()　初期化(@PostConstruct)"+this);
    }
    /**
     * コンストラクタ
     */
    public LoginCheckBean() {
        System.out.println("◆LoginCheckBean:LoginCheckBean()　コンストラクタ"+this);
    }
    
    /**
     * ログイン出来るかチェック
     * @param loginid   検査対象のログインID
     * @param password　検査対象のパスワード
     * @return true:ログインOK　false:ログインNG
     */
    public boolean IsLogin( String loginid, String password)
    {
        boolean ret = true;
        
        String regPw;
        
        System.out.println("◆LoginCheckBean：IsLogin() ログインをチェックする現在は「true」のみ。"+this);
        
        //　入力チェック
        if( loginid.isEmpty() || password.isEmpty() ){
            System.out.println("◆「ログインID:"+loginid+"」または「パスワード"+password+"」が不正");
            return false;
        }
        
        //
        regPw=getSingInPw(loginid);
        if( regPw == null ){
            System.out.println("◆パスワード取得に失敗");
            /* デバック用 */
            if(loginid.equals("takara-makoto@sji-inc.jp")==true){
                System.out.println("◆高良特別対応");
               return true;
            }
            return false;
        }
        /* デバック用 */
        /*
        if( loginid.equals("11001") == false ){
            return false;
        }
        */
        if( regPw.equals(password) == true){
            ret = true;
        }else{
            ret = false;
        }
        return ret;
    }
    /**
     * パスワード情報の取得
     * @param loginid
     * @return 
     */
    private String getSingInPw( String loginid)
    {
        String regpw = null;

        /* @NOTEST */
        try{
            //  DBからログインIDとついになるPWを取得。
            MUserPassword upw;
            upw = em.find(MUserPassword.class, loginid);
            System.out.println( "◆PWゲット>>"+upw.getUserId()+"/"+upw.getPassword());
            regpw = upw.getPassword();
            // エンコードされたPW取得
            // PWをデコード
        }catch(IllegalArgumentException iaEx){
            System.err.printf("　◆ユーザ情報のロードに失敗!! 【"+iaEx.toString()+"】");
            return "";
        }
        /* @TEST
        regpw = new String("12345");
         */
        
        return regpw;
    }
 
}
