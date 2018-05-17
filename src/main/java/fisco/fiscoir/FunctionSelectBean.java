/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 機能選択画面のBean
 * @author k00299
 */
@Named(value = "functionSelectBean")
@Dependent
public class FunctionSelectBean {

    public static final String NPSERCHCMOP = "SerchComp.xhtml";
    public static final String NPSERCHMEMO = "SerchMemo.xhtml";
    public static final String NPLOGIN = "login.xhtml";
    
    /**
     * 検索用データ
     */
    @Inject
    private SerchCompItems selItemList;
   /**
    * 
    */
    @Inject
   private SerchMemoItems selSmtgItemList;
    
    @Inject
    private DispItemPropaty dspitemPropaty;
    
   /**
    * 画面表示時の初期処理
    */
    @PostConstruct
    private void postconstructer(){
        System.out.println("■FunctionSelect::goSerchComp");
        //  ログイン情報を取ってみたい
        
        // プロパティファイルを読込み
        dspitemPropaty.Read_propatyfile();
    }

    /**
     * Creates a new instance of FunctionSelect
     */
    public FunctionSelectBean() {
    }
    
    /**
     * 企業検索画面への遷移
     * @return 企業検索画面へのリクエスト文字列
     */
    public String goSerchComp()
    {
        System.out.println("■FunctionSelect::goSerchComp::"+selItemList);
        
         // 検索ページで利用するアイテムを先読みしておく
        selItemList.initial_selitems();
        
        return NPSERCHCMOP+"?faces-redirect=true";
    }
    
    /*  *
     * メモ検索画面への遷移
     * @return メモ画面へのリクエスト文字列
     */
    public String goSerchMemo()
    {
        System.out.println("■FunctionSelect::goSerchMemo");
        selItemList.initial_selitems();
        selSmtgItemList.initial_selitems();
        
        return NPSERCHMEMO+"?faces-redirect=true";
    }
    
    /**
     * ログアウト処理をしてログイン画面へ遷移させる
     * @return 
     */
    public String goLogout()
    {
        System.out.println("◆FunctionSelect::Logout ");
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            System.out.println("◆◆ログアウト処理 >>");
            request.logout();
        } catch (ServletException ex) {
            System.out.println("◆◆ログアウト処理で例外発生　>>"+ex.getMessage());
        }
        return NPLOGIN+"?faces-redirect=true";
    }
}
