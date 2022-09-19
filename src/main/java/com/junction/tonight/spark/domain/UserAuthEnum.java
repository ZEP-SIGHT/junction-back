package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  com.junction.tonight.spark.domain
 * fileName : UserAuthEnum
 * author :  shinmj
 * date : 22. 9. 17.
 * description :
 */

@Getter
public enum UserAuthEnum {
    GUEST(-1),
    MEMBER(0),
    EDITOR(1000),
    STAFF(2000),
    ADMIN(3000),
    MAP_OWNER(3001);


    private final int authCode;

    UserAuthEnum(int authCode) {
        this.authCode = authCode;
    }
}
