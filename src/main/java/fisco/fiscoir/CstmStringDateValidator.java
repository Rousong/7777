/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * カスタムバリデータ　クラス
 * @author k00299
 */
public class CstmStringDateValidator implements ConstraintValidator<CstmStringDateValidation, String> {
    
    private String value;
    
    private int max;
    
    /**
     * 初期処理
     * @param constraintAnnotation 
     */
    @Override
    public void initialize(CstmStringDateValidation constraintAnnotation) {
        System.out.println("◆CstmStringDateValidation::initialize>"+hashCode());
//        throw new UnsupportedOperationException("Not supported yet.");

        //this.value = constraintAnnotation.value();
        this.max = constraintAnnotation.max();
    }
    /**
     * チェック処理
     * @param value
     * @param context
     * @return 
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("◆CstmStringDateValidation::isValid>"+hashCode());
        if( value==null)
        {
            return true;
        }
        
        String cval = value;
        cval = cval.replace('-', '/');
        DateFormat dfm = DateFormat.getDateInstance();
        dfm.setLenient(false);
        try{
            dfm.parse(cval);
            
            return cval.length() <= this.max;
        }catch(Exception e){
             return false;
        }
        
//        return Objects.equals(this.value,value);
        // throw new UnsupportedOperationException("Not supported yet.");
    }
}
