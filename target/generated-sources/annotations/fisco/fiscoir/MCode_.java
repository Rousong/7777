package fisco.fiscoir;

import fisco.fiscoir.MCodePK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T14:13:45")
@StaticMetamodel(MCode.class)
public class MCode_ { 

    public static volatile SingularAttribute<MCode, MCodePK> mCodePK;
    public static volatile SingularAttribute<MCode, String> notes;
    public static volatile SingularAttribute<MCode, String> contents2;
    public static volatile SingularAttribute<MCode, Date> registerDatetime;
    public static volatile SingularAttribute<MCode, String> contents1;
    public static volatile SingularAttribute<MCode, Date> renewalDatetime;
    public static volatile SingularAttribute<MCode, Short> seq;

}