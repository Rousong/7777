package fisco.fiscoir;

import fisco.fiscoir.MMemotagPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T14:13:45")
@StaticMetamodel(MMemotag.class)
public class MMemotag_ { 

    public static volatile SingularAttribute<MMemotag, String> notes;
    public static volatile SingularAttribute<MMemotag, String> contents2;
    public static volatile SingularAttribute<MMemotag, Date> registerDatetime;
    public static volatile SingularAttribute<MMemotag, String> contents1;
    public static volatile SingularAttribute<MMemotag, MMemotagPK> mMemotagPK;
    public static volatile SingularAttribute<MMemotag, Date> renewalDatetime;
    public static volatile SingularAttribute<MMemotag, Short> seq;

}