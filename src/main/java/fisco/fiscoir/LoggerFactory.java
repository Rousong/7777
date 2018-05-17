/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fisco.fiscoir;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author k00299
 */
@Dependent
public class LoggerFactory {
    @Produces @Dependent
    public Logger getLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass()
                 .getPackage().getName());
    }
}
