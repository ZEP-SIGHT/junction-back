package com.junction.tonight.spark.domain;

import javax.persistence.*;

/**
 * packageName :  com.junction.tonight.spark.domain
 * fileName : UserAuth
 * author :  shinmj
 * date : 22. 9. 17.
 * description :
 */
@Entity(name = "user_auth")
public class UserAuth {

    @Id
    @GeneratedValue
    private Long uAuthId;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private UserAuthEnum authCode;

    private String authName;

}
