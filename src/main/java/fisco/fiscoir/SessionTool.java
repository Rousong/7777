/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k00299
 */
public class SessionTool {

    
    public final static String ATTRIBUTE_NM_ACKURL ="backUrl";
    
    public static String readBackUrl(){
         //  セッションに、戻り先が保存してあるので取り出す
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        HttpSession session = request.getSession(true);
        String backURL=(String)session.getAttribute(ATTRIBUTE_NM_ACKURL);
        
        return backURL;
    }

    public static boolean writeBackUrl( String backUrl){
        //  セッションに、リクエスト元の情報を埋め込む
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_NM_ACKURL, backUrl);
        
        return true;
    }

}


