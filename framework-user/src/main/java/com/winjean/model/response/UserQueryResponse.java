package com.winjean.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserQueryResponse {

    private String id;

    private String name;

    @JsonFormat(/*locale="zh", timezone="GMT+8",*/ pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String sex;

    private String email;
}
