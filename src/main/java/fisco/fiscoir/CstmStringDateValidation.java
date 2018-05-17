/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *カスタムバリデーション　クラス
 * @author k00299
 */
@Documented
@Constraint(validatedBy = CstmStringDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CstmStringDateValidation {
    
    //String value();
    int max();

    String message() default "{fisco.fiscoir.CstmStringDateValidation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
