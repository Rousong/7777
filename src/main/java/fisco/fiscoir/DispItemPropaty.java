/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author k00299
 */
@Named
@SessionScoped
public class DispItemPropaty  implements Serializable{
    
//<editor-fold defaultstate="collapsed" desc="propaty">

    //  本番と開発の環境差が埋められない。なにか良い手があったら変更したい。
//  開発用
//    private final static String filename = "C:\\Users\\k00299\\Documents\\NetBeansProjects\\FiscoIR\\src\\main\\webapp\WEB-INF\\DispItem.properties";
 // 本場用
    private final static String filename = "/opt/glassfish4/glassfish/domains/domain1/applications/FIRScrm/WEB-INF/DispItem.properties";
    
    private final static String KEY_TAG1 = "disp_label_tag1";
    private final static String KEY_TAG2 = "disp_label_tag2";
    private final static String KEY_TAG3 = "disp_label_tag3";
    
    private String lbl_tag1;
    private String lbl_tag2;
    private String lbl_tag3;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getter/setter">
    
    public String getLbl_tag1() {
        return lbl_tag1;
    }
    
    public void setLbl_tag1(String lbl_tag1) {
        this.lbl_tag1 = lbl_tag1;
    }
    
    public String getLbl_tag2() {
        return lbl_tag2;
    }
    
    public void setLbl_tag2(String lbl_tag2) {
        this.lbl_tag2 = lbl_tag2;
    }
    
    public String getLbl_tag3() {
        return lbl_tag3;
    }
    
    public void setLbl_tag3(String lbl_tag3) {
        this.lbl_tag3 = lbl_tag3;
    }
//</editor-fold>

    public DispItemPropaty() {
        this.lbl_tag1 = "タグ1";
        this.lbl_tag2 = "タグ2";
        this.lbl_tag3 = "タグ3";
    }
    
    public void Read_propatyfile()
    {
        try{
            Properties properties = new Properties();
            
            InputStream inputStream = new FileInputStream(filename);
            properties.load(inputStream);
            inputStream.close();
            //
            this.setLbl_tag1(properties.getProperty(KEY_TAG1));
            this.setLbl_tag2(properties.getProperty(KEY_TAG2));
            this.setLbl_tag3(properties.getProperty(KEY_TAG3));
        }catch(Exception nex){
            System.err.printf(nex.getMessage());
        }
    }
    
}
