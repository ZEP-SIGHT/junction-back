package com.junction.tonight.spark.dto.page1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StatisForAuth {

    public HashMap<String, List> dataForAuth;


}

enum AuthType {
    GUEST(-1),
    MEMBER(0),
    STAFF(2000),
    EDITOR(1000),
    MANAGER(3000),
    OWNER(3001);

    final private int code;

    AuthType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}