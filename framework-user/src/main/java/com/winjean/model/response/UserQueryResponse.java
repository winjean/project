package com.winjean.model.response;

import lombok.Data;

@Data
public class UserQueryResponse {

    private int id;

    private String name;

    private String birthdate;

    private String sex;
}
