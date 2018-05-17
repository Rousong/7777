/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

/**
 *
 * @author k00299
 */
@Named
@SessionScoped
public class Memo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String memoId;                      //  メモID
    private String userId;                          //　記入者のID
    private String stockCode;             //　訪問先証券コード
    private String brandCd;             //　訪問先企業コード
    private String brandNm;             //  訪問先企業名
    private String postNm;                    //  訪問先部署名
    private String personNm;               //　訪問先担当者名
    private Date startDate;                   //　日付　from
    private Date endDate;                     //　日付　to
    @Size(max = 5,message="※ 『hh:mm』 の形式で入力して下さい。")
    private String startTime;                   //　時刻　from
    @Size(max = 5,message="※ 『hh:mm』 の形式で入力して下さい。")
    private String endTime;                     //　時刻　to
    private String tag1;                            //　タグ1
    private String tag1_val;
    private String tag2;                            //　タグ2
    private String tag2_val;
    private String tag3;                            //　タグ3
    private String tag3_val;
    private String status;                          //　ステータス（未使用）
    private String title;                             //   メモタイトル
    private String memo;                         //　メモ本文
    private String memoSt;                      //　メモの見出し　先頭２０文字用

    private Date    registerDateTime;       //  登録日
    private String  registerUser;               //  登録ユーザ
    private Date    renewalDateTime;      //    最終更新日
    private String  renewalUser;                //　最終更新ユーザ

    
    private boolean readOnly;                   //  更新ブロックフラグ（未使用）
    
//<editor-fold defaultstate="collapsed" desc="setter/getter">
    public String getMemoId() {
        return memoId;
    }
    
    public void setMemoId(String memoId) {
        this.memoId = memoId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getStockCode() {
        return stockCode;
    }
    
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    
    public String getBrandCd() {
        return brandCd;
    }
    
    public void setBrandCd(String brandCd) {
        this.brandCd = brandCd;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getTag1() {
        return tag1;
    }
    
    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
    
    public String getTag2() {
        return tag2;
    }
    
    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
    
    public String getTag3() {
        return tag3;
    }
    
    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag1_val() {
        return tag1_val;
    }

    public void setTag1_val(String tag1_val) {
        this.tag1_val = tag1_val;
    }

    public String getTag2_val() {
        return tag2_val;
    }

    public void setTag2_val(String tag2_val) {
        this.tag2_val = tag2_val;
    }

    public String getTag3_val() {
        return tag3_val;
    }

    public void setTag3_val(String tag3_val) {
        this.tag3_val = tag3_val;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getMemo() {
        return memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public Date getRegisterDateTime() {
        return registerDateTime;
    }
    
    public void setRegisterDateTime(Date registerDateTime) {
        this.registerDateTime = registerDateTime;
    }
    
    public String getRegisterUser() {
        return registerUser;
    }
    
    public void setRegisterUser(String registerUser) {
        this.registerUser = registerUser;
    }
    
    public Date getRenewalDateTime() {
        return renewalDateTime;
    }
    
    public void setRenewalDateTime(Date renewalDateTime) {
        this.renewalDateTime = renewalDateTime;
    }
    
    public String getRenewalUser() {
        return renewalUser;
    }
    
    public void setRenewalUser(String renewalUser) {
        this.renewalUser = renewalUser;
    }
    
    public boolean isReadOnly() {
        return readOnly;
    }
    
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    
    public String getBrandNm() {
        return brandNm;
    }
    
    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }
    
    public String getMemoSt() {
        return memoSt;
    }
    
    public void setMemoSt(String memoSt) {
        this.memoSt = memoSt;
    }
    
    public String getPostNm() {
        return postNm;
    }
    
    public void setPostNm(String postNm) {
        this.postNm = postNm;
    }
    
    public String getPersonNm() {
        return personNm;
    }
    
    public void setPersonNm(String personNm) {
        this.personNm = personNm;
    }
//</editor-fold>
    
    public void setMemoST(){
        String ret;
        
        if(memo==null)
            return;
        /* 文字列の長さ確認　*/
        int maxlen = memo.length();
        if(maxlen == 0){
            memoSt="";
        }
        if( maxlen >=10 ){
            maxlen = 10;
        }
        
        memoSt = memo.substring(0, maxlen);
    }

    public void clear()
    {
        this.setMemoId(null);                      //  メモID
        this.setUserId(null);                          //　記入者のID
        this.setStockCode(null);             //　訪問先証券コード
        this.setBrandCd(null);             //　訪問先企業コード
        this.setBrandNm(null);             //  訪問先企業名
        this.setPostNm(null);                    //  訪問先部署名
        this.setPersonNm(null);               //　訪問先担当者名
        this.setStartDate(null);                   //　日付　from
        this.setEndDate(null);                     //　日付　to
        this.setStartTime(null);                   //　時刻　from
        this.setEndTime(null);                     //　時刻　to
        this.setTag1(null);                            //　タグ1
        this.setTag1_val(null);
        this.setTag2(null);                            //　タグ2
        this.setTag2_val(null);
        this.setTag3(null);                            //　タグ3
        this.setTag3_val(null);
        this.setStatus(null);                          //　ステータス（未使用）
        this.setTitle(null);                             //   メモタイトル
        this.setMemo(null);                         //　メモ本文
        this.setMemoSt(null);                      //　メモの見出し　先頭２０文字用

        this.setRegisterDateTime(null);       //  登録日
        this.setRegisterUser(null);               //  登録ユーザ
        this.setRenewalDateTime(null);      //    最終更新日
        this.setRenewalUser(null);                //　最終更新ユーザ
    
        this.setReadOnly(false);                   //  更新ブロックフラグ（未使用）
        
    }
}
