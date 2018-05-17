/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

/**
 *
 * @author k00299
 */
public class selectboxItem{
    
    private String label;       //  画面に表示させる　文字列
    private String value;       //  取得する値
    
    public selectboxItem() {
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String value) {
        label = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
