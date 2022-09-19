package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * packageName :  com.junction.tonight.spark.domain
 * fileName : UserAuth
 * author :  shinmj
 * date : 22. 9. 17.
 * description :
 */

@Data
@Entity
@Table(name = "user_auth", catalog = "zep")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {

    @Id
    @GeneratedValue
    private Long uAuthId;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private UserAuthEnum authCode;

    private String authName;

}
