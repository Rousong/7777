package fisco.fiscoir;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-09T14:13:45")
@StaticMetamodel(MUserPassword.class)
public class MUserPassword_ { 

    public static volatile SingularAttribute<MUserPassword, String> internalId;
    public static volatile SingularAttribute<MUserPassword, String> password;
    public static volatile SingularAttribute<MUserPassword, String> renewalUser;
    public static volatile SingularAttribute<MUserPassword, String> registerUser;
    public static volatile SingularAttribute<MUserPassword, Date> registerDatetime;
    public static volatile SingularAttribute<MUserPassword, String> userId;
    public static volatile SingularAttribute<MUserPassword, Date> renewalDatetime;

}