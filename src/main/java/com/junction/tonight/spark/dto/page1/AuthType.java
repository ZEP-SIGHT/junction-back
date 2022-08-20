package com.junction.tonight.spark.dto.page1;

public enum AuthType {
        GUEST("-1"),
        MEMBER("0"),
        STAFF("2000"),
        EDITOR("1000"),
        MANAGER("3000"),
        OWNER("3001");

    final private String code;


        AuthType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}