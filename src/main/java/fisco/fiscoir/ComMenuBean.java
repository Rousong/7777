/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 共通メニュークラス
 * @author k00299
 */
@Named(value = "comMenuBean")
@SessionScoped
public class ComMenuBean implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    /**
     * ユーザ情報クラス
     */
    private UserInfo userinfo;
    @Inject
    /**
     * 
     */
    private SerchCompItems selItemList;

    /**
     * Creates a new instance of ComMenuBean
     */
    public ComMenuBean() {
        System.out.println("◆ComMenuBean::ComMenuBean");
    }
    
    /**
     * 機能選択　画面選択時の処理
     * @return 機能選択画面のURL
     */
    public String goFuncSel(){
        System.out.println("◆ComMenuBean::goFuncSel ");
        
        return "FunctionSelect.xhtml?faces-redirect=true";
    }
    /**
     * 企業検索　画面選択時の処理
     * @return 企業検索画面のURL
     */
    public String goSerchComp(){
        System.out.println("◆ComMenuBean::goSerchComp ");
        
         // 検索ページで利用するアイテムを先読みしておく
        selItemList.initial_selitems();
        
        return FunctionSelectBean.NPSERCHCMOP+"?faces-redirect=true";
    }
    /**
     * メモ検索　画面選択時の処理
     * @return メモ検索画面のURL
     */
    public String goSerchMemo(){
        System.out.println("◆ComMenuBean::goSerchMemo ");
        
        return FunctionSelectBean.NPSERCHMEMO+"?faces-redirect=true";
    }
    /**
     * 未使用
     * @return 
     */
    public String ChangeUserInfo(){
        System.out.println("◆ComMenuBean::ChangeUserInfo ");
        
        System.out.println("◆ユーザ名::"+userinfo.getName());
        System.out.println("◆会社ID::"+userinfo.getCompanyID());
        System.out.println("◆ユーザID::"+userinfo.getUserID());
        System.out.println("◆メールアドレス::"+userinfo.getMailAddr());
        
        return "";
    }
    /**
     * ログアウト　選択時の処理
     * @return ログイン画面のURL
     */
    public String LogOut(){
        System.out.println("◆ComMenuBean::Logout ");
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            System.out.println("◆◆ログアウト処理 >>");
            request.logout();
        } catch (ServletException ex) {
            System.out.println("◆◆ログアウト処理で例外発生　>>"+ex.getMessage());
        }
        return "login.xhtml?faces-redirect=true";
    }
}
