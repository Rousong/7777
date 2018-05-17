/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 * セキュリティチェック用クラス
 * セキュリティコード作成、保持、チェックを行う。
 * 現在は未使用。将来拡張用。
 * @author k00299
 */
@Named(value = "checkSecurityCode")
@SessionScoped
public class CheckSecurityCode  implements Serializable{

    @Inject
    /**
     * ユーザ情報クラス
     */
    private UserInfo userinfo;
    
    /**
     * タイムアウト時間変数
     */
    final long KIGENTIME = 10*60*1000; // 10分
    /**
     * セキュリティコードに問題が無い場合に遷移するページアドレス。
     */
    final String NEXTPAGE = "FunctionSelect.xhtml?faces-redirect=true";
    
    /**
     * 　セキュリティコードの格納用　変数
     */
    private String securityCode;

    /**
     * Creates a new instance of CheckSecurityCode
     */
    public CheckSecurityCode() {
    }
    /**
     * セキュリティコードのインターフェース（getter）
     * @return 
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * セキュリティコードのインターフェース（setter）
     * @param securityCode 
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    /**
     * セキュリティコードをチェックして処理を決める
     * @return チェック後の遷移先ページ
     */
    public String CheckSecurityCode()
    {
        String nextPage;

        System.out.println("■CheckSecurityCode::CheckSecurityCode();");
        if( !chkSecurityCode() ){
            /* コード不一致 　もう一度入力?*/
            nextPage = "";
            // エラーメッセージを表示させる
        }else if(!chkElapsedTime()){
            /* セッション時間切れ　エラー画面 */
            nextPage =  "";
        }else{
            /* 問題なし */
            nextPage =  NEXTPAGE;
        }
        return nextPage;
    }
    /**
     * セキュリティコードのチェック
     * @return 　true:問題無　false：問題あり
     */
    private boolean chkSecurityCode()
    {
        boolean ret = true;
        // 作ったセキュリティコード
        String createScCode = userinfo.getSecurityCode();
        //　入力されたセキュリティコード
        String inputScCode = this.getSecurityCode();
        
        /*コード確認*/
        if( createScCode.equals((inputScCode))){
            /* コード一致 時間確認 */
            System.out.println("★問題なし");
        }else{
            System.out.println("★入力コード["+ inputScCode +"] は、一致しない！！");
            ret = false;
        }

        return ret;
    }
    /**
     * セキュリティコードの入力待ち時間確認
     * @return true:入力時間内 false:入力時間切れ
     */
    private boolean chkElapsedTime()
    {
        boolean ret = true;
        
        // 現在時刻を取得
        long inputTime = System.currentTimeMillis();
        // 経過時間の取得
        long elapsedTime = inputTime - userinfo.getScCodeCreateTime();

        /* 待ち時間を経過しているか確認 */
        if( KIGENTIME >= elapsedTime )
        {
            System.out.println("★問題なし");
        }else{
            /* 10分以上*/
            System.out.println("★10分経過してます。NowTime[" +inputTime+"] CreTime["+userinfo.getScCodeCreateTime()+"]");
            ret = false;
        }
        
        return ret;
    }
    
}
